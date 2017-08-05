package com.hungtran.footballscore.ui.viewpager.leagueTable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hungtran.footballscore.R;
import com.hungtran.footballscore.modelApi.competition.Competition;
import com.hungtran.footballscore.modelApi.leagueTable.LeagueTable;
import com.hungtran.footballscore.ui.team.TeamActivity;
import com.hungtran.footballscore.ui.viewpager.leagueTable.adapter.RecyclerLeagueTableAdapter;

/**
 * Created by Hung Tran on 02/07/2017.
 */

public class LeagueTableFragment extends Fragment implements LeagueTable.OnGetLeagueTableListener, RecyclerLeagueTableAdapter.OnItemClickListener {
    public static final String TEAM_HREF = "TEAM_HREF";
    public static String BUNDLE_COMPETITION = "COMPETITION";
    private static Competition competition;
    private static LeagueTableFragment leagueTableFragment;
    private RecyclerLeagueTableAdapter recyclerLeagueTableAdapter;
    private Context mContext;
    private LeagueTable leagueTable;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public static LeagueTableFragment newInstance(Competition comp) {
        leagueTableFragment = new LeagueTableFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_COMPETITION, comp);
        leagueTableFragment.setArguments(bundle);
        return leagueTableFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_table, container, false);
        competition = (Competition) getArguments().getSerializable(BUNDLE_COMPETITION);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyler_league_table);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        LeagueTable.newInstance(mContext).getLeagueTable(competition.getId(), this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerLeagueTableAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void initRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerLeagueTableAdapter = new RecyclerLeagueTableAdapter(leagueTable, mContext);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerLeagueTableAdapter);
    }

    @Override
    public void onGetLeagueTableSuccess(LeagueTable leagueTable) {
        this.leagueTable = leagueTable;
        initRecycler();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onGetLeagueTableFail() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClicked(String teamHref) {
        Intent intent = new Intent(mContext, TeamActivity.class);
        intent.putExtra(TEAM_HREF, teamHref);
        startActivity(intent);
    }
}
