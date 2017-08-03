package com.hungtran.footballscore.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hungtran.footballscore.R;
import com.hungtran.footballscore.modelApi.competition.Competition;
import com.hungtran.footballscore.modelApi.leagueTeam.LeagueTeam;
import com.hungtran.footballscore.ui.home.HomeFragment;
import com.hungtran.footballscore.ui.competition.CompetitionFragment;
import com.hungtran.footballscore.ui.navigation.NavigationDrawerFragment;
import com.hungtran.footballscore.utils.PreferentUtil;

import java.io.File;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.OnFragmentDrawerListener, NavigationDrawerFragment.OnGetCompetitionListener, LeagueTeam.OnGetLeagueTeamsListener {

    private static final String KEY_GET_LEAGUE_TEAM = "KEY_GET_LEAGUE_TEAM";
    public static final String LINK_LOCAL_IMAGE = Environment.getExternalStorageDirectory() + File.separator + "football/logo";
    private Toolbar mToolbar;
    private NavigationDrawerFragment navigationDrawerFragment;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private HomeFragment homeFragment;
    private CompetitionFragment leagueFragment;
    private ProgressDialog dialog;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true); // ----> enable use svg icon
        NavigationDrawerFragment.setOnGetCompetitionListener(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        dialog = ProgressDialog.show(this, "", getString(R.string.prepare_data), true);
        dialog.show();
        initFragmentNavigation();
        addFragmentHome();

    }

    @Override
    public void onDrawerItemSelected(int position, Competition competition) {
        displayView(position, competition);
    }

    @Override
    public void onGetCompetitionSuccess(List<Competition> list) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        if (!PreferentUtil.newInstance(this).getBoolean(KEY_GET_LEAGUE_TEAM, false)) {
            for (Competition competition : list) {
                LeagueTeam.newInstance(mContext).getLeagueTeamFormServer(competition.getId(), this);
            }
        }
        PreferentUtil.newInstance(this).putBoolean(KEY_GET_LEAGUE_TEAM, true);
    }

    @Override
    public void onGetCompetitionFail() {
        Toast.makeText(this, "Can't get data from server", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetLeagueTeamsSuccess(LeagueTeam leagueTeam) {
        Gson gson = new Gson();
        String json = gson.toJson(leagueTeam);
        PreferentUtil.newInstance(this).putString(leagueTeam.get_links().getSelf().getHref(), json);
//        downloadLogo();
    }

    @Override
    public void onGetLeagueTeamsFail() {

    }

    private void initFragmentNavigation() {
        navigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        navigationDrawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        navigationDrawerFragment.setOnFragmentDrawerListener(this);
    }

    private void addFragmentHome() {
        homeFragment = new HomeFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_body, homeFragment);
        fragmentTransaction.commit();
    }

    private void displayView(int position, Competition competition) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            default:
                fragment = CompetitionFragment.newInstance(competition);
                break;
        }
        getSupportActionBar().setTitle(competition.getCaption());

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment);
        fragmentTransaction.commit();
    }

//    private void downloadLogo() {
//        dialog = ProgressDialog.show(this, "", getString(R.string.prepare_data));
//        dialog.show();
////        http://api.football-data.org/v1/competitions/445/teams
//        Gson gson = new Gson();
//        OkHttpClient client = new OkHttpClient();
//        LeagueTeam leagueTeam = gson.fromJson(PreferentUtil.newInstance(this).getString("http://api.football-data.org/v1/competitions/445/teams", ""), LeagueTeam.class);
//        for (final Team team : leagueTeam.getTeams()) {
//            Request request = new Request.Builder()
//                    .url(team.getCrestUrl())
//                    .build();
//            client.newCall(request).enqueue(new okhttp3.Callback() {
//                @Override
//                public void onFailure(okhttp3.Call call, IOException e) {
//
//                }
//
//                @Override
//                public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
//                    response.body().byteStream(); // Read the data from the stream
//                    Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
//                    CacheUtil.newInstance(mContext).saveLogoBitmap(bmp, CacheUtil.FILE_PATH_LOGO, team.getCrestUrl(), new CacheUtil.SaveImageToDeviceListener() {
//                        @Override
//                        public void onComplete() {
//                            if (dialog.isShowing()){
//                                dialog.dismiss();
//                            }
//                        }
//
//                        @Override
//                        public void onFail(Throwable e) {
//                            if (dialog.isShowing()){
//                                dialog.dismiss();
//                            }
//                        }
//                    });
//                }
//            });
//        }
//    }


}
