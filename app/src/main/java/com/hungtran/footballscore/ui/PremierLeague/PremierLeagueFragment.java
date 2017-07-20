package com.hungtran.footballscore.ui.PremierLeague;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hungtran.footballscore.R;
import com.hungtran.footballscore.ui.viewpager.MainViewPagerAdapter;
import com.roughike.bottombar.BottomBar;

/**
 * Created by Hung Tran on 28/06/2017.
 */

public class PremierLeagueFragment extends Fragment {

    private ViewPager viewPager;
    private MainViewPagerAdapter mainViewPagerAdapter;
    private View view;
    private BottomBar bottomBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_premier_league, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_fragment);
        bottomBar = (BottomBar) view.findViewById(R.id.bottomBar);

        initViewPager();
    }

    private void initViewPager() {
        mainViewPagerAdapter = new MainViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(mainViewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomBar.selectTabAtPosition(position, false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
