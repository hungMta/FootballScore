package com.hungtran.footballscore.ui.navigation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.hungtran.footballscore.R;
import com.hungtran.footballscore.modelApi.competition.Competition;
import com.hungtran.footballscore.ui.navigation.adapter.NavigationDrawerAdapter;
import com.hungtran.footballscore.utils.PreferentUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Hung Tran on 28/06/2017.
 */

public class NavigationDrawerFragment extends Fragment implements Competition.OnLoadApiCompetitionsListener, NavigationDrawerAdapter.OnItemNavigationClickListener {

    private static final String KEY_COMPETITION = "COMPETITION";

    private Context mContext;
    private NavigationDrawerAdapter navigationDrawerAdapter;
    private RecyclerView recyclerView;
    private View containerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private static OnFragmentDrawerListener onFragmentDrawerListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.drawerList);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }


    @Override
    public void onLoadSuccessApiCompetitions(List<Competition> competitionList) {
        navigationDrawerAdapter.notifyChange(competitionList);
    }

    @Override
    public void onLoadFailedApiCompetitions(String message, int errorCode) {

    }

    @Override
    public void onItemNavigationClick(int position, Competition competition) {
        onFragmentDrawerListener.onDrawerItemSelected(position, competition);
        mDrawerLayout.closeDrawer(containerView);
    }


    private void initRecycler() {
        recyclerView.removeAllViews();
        navigationDrawerAdapter = new NavigationDrawerAdapter(mContext, null);
        Competition.newInstance(mContext).getListCompetition(this);
        navigationDrawerAdapter.setOnItemNavigationClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(navigationDrawerAdapter);
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

    }

    public interface OnFragmentDrawerListener {
        void onDrawerItemSelected(int position, Competition competition);
    }

    public static void setOnFragmentDrawerListener(OnFragmentDrawerListener listener) {
        onFragmentDrawerListener = listener;
    }


}
