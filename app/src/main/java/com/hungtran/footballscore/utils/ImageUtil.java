package com.hungtran.footballscore.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.hungtran.footballscore.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Hung Tran on 7/24/2017.
 */

public class ImageUtil {
    private static Context mContext;
    private static ImageUtil imageUtil;

    private Picasso picasso;
    private ImageView imageView;

    public static ImageUtil newInstance(Context context) {

        mContext = context;
        if (imageUtil == null)
            imageUtil = new ImageUtil();
        return imageUtil;
    }

    public void getImageFormUrl(String url, ImageView view) {

        if (!((Activity) mContext).isFinishing() && view != null && url != null && !url.isEmpty()) {
            Logg.debug(getClass(), "url: " + url);
            Picasso.with(mContext).load(url)
                    .error(R.drawable.ic_icon_ball)
                    .into(view);
        } else
            Logg.error(getClass(), "getImageFormUrl: url image or view is null");
    }

}
