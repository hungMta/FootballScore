package com.hungtran.footballscore.ui.viewpager.leagueTable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hungtran.footballscore.R;

/**
 * Created by Hung Tran on 02/07/2017.
 */

public class LeagueTableFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_table,container,false);
        return view;
    }

}
