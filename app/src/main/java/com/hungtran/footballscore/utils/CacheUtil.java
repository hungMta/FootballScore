package com.hungtran.footballscore.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Looper;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hungtran.footballscore.modelApi.competition.Competition;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 20/07/2017.
 */

public class CacheUtil {
    public static final String FILE_NAME_COMPETITION = "/sdcard/competition.txt";
    public static final String FILE_PATH_LOGO = Environment.getExternalStorageDirectory() + File.separator + "footballSocre/logo/";
    private static Context mContext;
    private static CacheUtil cacheUtil;

    public static CacheUtil newInstance(Context context) {
        mContext = context;
        if (cacheUtil == null) {
            cacheUtil = new CacheUtil();
            initFolderLogo();
        }
        return cacheUtil;
    }

    public CacheUtil() {
        File myDirectory = new File(Environment.getExternalStorageDirectory(), "footballSocre/logo");
        if (!myDirectory.exists()) {
            myDirectory.mkdirs();
        }
    }


    public static void initFolderLogo() {
        File myDirectory = new File(Environment.getExternalStorageDirectory(), "footballSocre/logo");
        if (!myDirectory.exists()) {
            myDirectory.mkdirs();
        }
    }

    public boolean checkFolderEmpty() {
        File directory = new File("footballSocre/logo/");
        File[] contents = directory.listFiles();
        // the directory file is not really a directory..
        if (contents == null) {
            return false;
        }
        // Folder is empty
        else if (contents.length == 0) {
            return false;
        }
        // Folder contains files
        return true;
    }

    public synchronized void saveLogoBitmap(final Bitmap bm, final String filePath, final String fileName,final SaveImageToDeviceListener saveImageToDeviceListener) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                FileOutputStream outputStream;
                File f;
                try {
                    Looper.prepare();
                    f = new File(filePath + "logo");
                    outputStream = new FileOutputStream(f);
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();
                    saveImageToDeviceListener.onComplete();
                    Logg.debug(getClass(), "save success");
                } catch (Throwable e) {
                    saveImageToDeviceListener.onFail(e);
                }
            }
        });
        thread.start();
    }

    public interface SaveImageToDeviceListener {

        void onComplete();

        void onFail(Throwable e);
    }
    public synchronized static boolean writeFileText(String fileName, String content) {
        FileOutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        boolean success = false;
        try {
            outputStream = new FileOutputStream(fileName);
            outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(content);
            success = true;
            Logg.debug(mContext.getClass(), " writeFileText success");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Logg.error(mContext.getClass(), "writeFileText " + ex.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Logg.error(mContext.getClass(), "writeFileText " + e.toString());
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public synchronized static String readFileText(String fileName) {
        String line;
        StringBuilder content = new StringBuilder();
        File file = new File(mContext.getCacheDir(), fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return content.toString();
    }

    public static boolean saveCache(String content, String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
                return writeFileText(fileName, content);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Logg.error(mContext.getClass(), "saveCache " + e.toString());
        }
        return false;
    }

    public static List<Competition> getListCompetion(String content) {
        List<Competition> competitionList = new ArrayList<>();

        Gson gson = new Gson();
        competitionList = gson.fromJson(content, new TypeToken<List<Competition>>() {
        }.getType());

        return competitionList;
    }
}
