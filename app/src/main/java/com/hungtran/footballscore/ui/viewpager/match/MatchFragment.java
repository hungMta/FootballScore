package com.hungtran.footballscore.ui.viewpager.match;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hungtran.footballscore.R;
import com.hungtran.footballscore.modelApi.competition.Competition;
import com.hungtran.footballscore.ui.viewpager.match.adapter.RecyclerMatchdaysAdapter;

/**
 * Created by Hung Tran on 02/07/2017.
 */

public class MatchFragment extends Fragment {

    public static String BUNDLE_COMPETITION = "COMPETITION";

    private RecyclerView recyclerView;
    private RecyclerMatchdaysAdapter recyclerMatchdaysAdapter;
    private Context mContext;

    private static Competition competition;
    private static MatchFragment matchFragment;

    public static MatchFragment newInstance(Competition compe) {
        //if (matchFragment == null){
        matchFragment = new MatchFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_COMPETITION, compe);
        matchFragment.setArguments(bundle);
        // }
        return matchFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_match, container, false);
        competition = (Competition) getArguments().getSerializable(BUNDLE_COMPETITION);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyler_mathdays);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        RecyclerMatchdaysAdapter recyclerMatchdaysAdapter = new RecyclerMatchdaysAdapter(mContext, competition);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerMatchdaysAdapter);

    }
}
