package com.hungtran.footballscore.ui;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Looper;
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
import com.hungtran.footballscore.restBase.ServiceGenerator;
import com.hungtran.footballscore.ui.home.HomeFragment;
import com.hungtran.footballscore.ui.PremierLeague.PremierLeagueFragment;
import com.hungtran.footballscore.ui.navigation.NavigationDrawerFragment;
import com.hungtran.footballscore.utils.CacheUtil;
import com.hungtran.footballscore.utils.Logg;
import com.hungtran.footballscore.utils.PreferentUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;


public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.OnFragmentDrawerListener, NavigationDrawerFragment.OnGetCompetitionListener, LeagueTeam.OnGetLeagueTeamsListener {

    private static final String KEY_GET_LEAGUE_TEAM = "KEY_GET_LEAGUE_TEAM";
    public static final String LINK_LOCAL_IMAGE = Environment.getExternalStorageDirectory() + File.separator + "football/logo";
    private Toolbar mToolbar;
    private NavigationDrawerFragment navigationDrawerFragment;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private HomeFragment homeFragment;
    private PremierLeagueFragment leagueFragment;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CacheUtil.newInstance(this);
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
        if (!PreferentUtil.newInstance(this).getBoolean(KEY_GET_LEAGUE_TEAM, false)) {
            for (Competition competition : list) {
                LeagueTeam.newInstance(this).getLeagueTeamFormServer(competition.getId(), this);
            }
        }
        PreferentUtil.newInstance(this).putBoolean(KEY_GET_LEAGUE_TEAM, true);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
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
            case 1:
                fragment = PremierLeagueFragment.newInstance(competition);
                break;
            default:
                return;
        }
        getSupportActionBar().setTitle(competition.getCaption());

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment);
        fragmentTransaction.commit();
    }

    private void downloadLogo() {

    }

    private void checkIsDownloadedLogo() {
        boolean isDownloaded = CacheUtil.newInstance(this).checkFolderEmpty();
        if (!isDownloaded) {

        }
    }


}
