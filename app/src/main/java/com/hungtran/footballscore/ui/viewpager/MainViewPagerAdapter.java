package com.hungtran.footballscore.ui.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hungtran.footballscore.ui.viewpager.leagueTable.LeagueTableFragment;
import com.hungtran.footballscore.ui.viewpager.match.MatchFragment;
import com.hungtran.footballscore.ui.viewpager.personal.PersonalFragment;

/**
 * Created by Hung Tran on 02/07/2017.
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new MatchFragment();
                break;
            case 1:
                fragment = new LeagueTableFragment();
                break;
            case 2:
                fragment = new PersonalFragment();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
