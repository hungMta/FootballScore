package com.hungtran.footballscore.ui.PremierLeague;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hungtran.footballscore.R;
import com.hungtran.footballscore.modelApi.competition.Competition;
import com.hungtran.footballscore.ui.viewpager.MainViewPagerAdapter;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

/**
 * Created by Hung Tran on 28/06/2017.
 */

public class PremierLeagueFragment extends Fragment {

    private static final String BUNDLE_COMPETITION = "COMPETITION";

    private ViewPager viewPager;
    private MainViewPagerAdapter mainViewPagerAdapter;
    private View view;
    private BottomBar bottomBar;
    private static Competition competition;
    private static PremierLeagueFragment premierLeagueFragment;

    public static PremierLeagueFragment newInstance(Competition compe) {
        premierLeagueFragment = new PremierLeagueFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_COMPETITION, compe);
        premierLeagueFragment.setArguments(bundle);

        return premierLeagueFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        competition = (Competition) getArguments().getSerializable(BUNDLE_COMPETITION);
        view = inflater.inflate(R.layout.fragment_premier_league, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_fragment);
        bottomBar = (BottomBar) view.findViewById(R.id.bottomBar);
        initViewPager();

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_ball:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.tab_table:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.tab_star:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });

    }

    private void initViewPager() {
        mainViewPagerAdapter = new MainViewPagerAdapter(getFragmentManager(), competition);
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
