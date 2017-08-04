package com.hungtran.footballscore.ui.team.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hungtran.footballscore.R;

/**
 * Created by Hung Tran on 8/4/2017.
 */

public class ListPlayersFragment extends Fragment {

    private static final String BUNDLE_ID_TEAM = "BUNDLE_ID_TEAM";
    private static ListPlayersFragment listPlayersFragment;
    private static Context mContext;
    private int idTeam;

    public static ListPlayersFragment newInstance(int idTeam) {
        if (listPlayersFragment == null) {
            listPlayersFragment = new ListPlayersFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(BUNDLE_ID_TEAM, idTeam);
        }
        return listPlayersFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_list_player, container, false);
        idTeam = getArguments().getInt(BUNDLE_ID_TEAM, 0);
        return view;
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
