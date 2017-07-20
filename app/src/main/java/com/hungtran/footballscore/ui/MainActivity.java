package com.hungtran.footballscore.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.hungtran.footballscore.R;
import com.hungtran.footballscore.modelApi.competition.Competition;
import com.hungtran.footballscore.ui.home.HomeFragment;
import com.hungtran.footballscore.ui.PremierLeague.PremierLeagueFragment;
import com.hungtran.footballscore.ui.navigation.NavigationDrawerFragment;


public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.OnFragmentDrawerListener {

    private Toolbar mToolbar;
    private NavigationDrawerFragment navigationDrawerFragment;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private HomeFragment homeFragment;
    private PremierLeagueFragment leagueFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        initFragmentNavigation();
        addFragmentHome();
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
                fragment = new PremierLeagueFragment();
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

    @Override
    public void onDrawerItemSelected(int position, Competition competition) {
        displayView(position, competition);
    }
}
