package com.hungtran.footballscore.ui.team.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Hung Tran on 8/4/2017.
 */

public class ListPlayersFragment extends Fragment {

    private static ListPlayersFragment listPlayersFragment;
    private static Context mContext;
    public static ListPlayersFragment newInstance(){
        if (listPlayersFragment == null){
            listPlayersFragment = new ListPlayersFragment();

        }
        return listPlayersFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
