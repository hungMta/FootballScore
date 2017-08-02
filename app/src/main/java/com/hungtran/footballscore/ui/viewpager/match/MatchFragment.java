package com.hungtran.footballscore.ui.viewpager.match;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hungtran.footballscore.R;
import com.hungtran.footballscore.modelApi.competition.Competition;
import com.hungtran.footballscore.modelApi.fixtures.FixturesLeague;
import com.hungtran.footballscore.modelApi.fixtures.Match;
import com.hungtran.footballscore.modelApi.leagueTeam.LeagueTeam;
import com.hungtran.footballscore.ui.viewpager.match.adapter.RecyclerMatchAdapter;
import com.hungtran.footballscore.ui.viewpager.match.adapter.RecyclerMatchdaysAdapter;
import com.hungtran.footballscore.utils.PreferentUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hung Tran on 02/07/2017.
 */

public class MatchFragment extends Fragment implements RecyclerMatchdaysAdapter.OnItemMatchdaysClickListener, FixturesLeague.OnLoadFixturesLeagueListener, RecyclerMatchAdapter.OnItemQuestionListener, SwipeRefreshLayout.OnRefreshListener {

    public static String BUNDLE_COMPETITION = "COMPETITION";

    private RecyclerView recyclerView;
    private RecyclerMatchdaysAdapter recyclerMatchdaysAdapter;
    private Context mContext;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerMatch;
    private static Competition competition;
    private static MatchFragment matchFragment;
    private static List<int[]> listMathdays;
    private RecyclerMatchAdapter recyclerMatchAdapter;
    private List<Match> listMatchs = new ArrayList<>();
    private FixturesLeague fixturesLeague;
    private ProgressBar progressBar;
    private LeagueTeam leagueTeam;


    public static MatchFragment newInstance(Competition compe) {
        matchFragment = new MatchFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_COMPETITION, compe);
        matchFragment.setArguments(bundle);
        return matchFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_match, container, false);
        competition = (Competition) getArguments().getSerializable(BUNDLE_COMPETITION);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyler_mathdays);
        recyclerMatch = (RecyclerView) view.findViewById(R.id.recyler_match);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar_match_fragment);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Gson gson = new Gson();
        leagueTeam = gson.fromJson(PreferentUtil.newInstance(mContext).getString(String.valueOf(competition.get_links().getTeams().getHref()), ""), LeagueTeam.class);
        RecyclerMatchdaysAdapter.SetOnItemMatchdaysClickListener(this);
        initRecyclerViewMatchdays();
        getFixtureLeague();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void OnItemMatchdaysClick(int positition) {
        getListMatchsDay();
        if (positition + 1 != competition.getCurrentMatchday()) {
            listMathdays.get(positition)[0] = 3;
        }
        recyclerMatchdaysAdapter.notifyDataSetchanged(listMathdays);
    }

    private void getFixtureLeague() {
        FixturesLeague.newInstance(mContext).getFixturesLeague(competition.getId(), this);
    }

    private void initRecyclerViewMatchdays() {
        getListMatchsDay();
        linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerMatchdaysAdapter = new RecyclerMatchdaysAdapter(mContext, listMathdays);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerMatchdaysAdapter);
    }

    private void initRecyclerViewMatch() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerMatch.setLayoutManager(layoutManager);
        recyclerMatchAdapter = new RecyclerMatchAdapter(mContext, listMatchs, recyclerMatch, getActivity(), leagueTeam);
        recyclerMatch.setHasFixedSize(false);
        recyclerMatch.setAdapter(recyclerMatchAdapter);
        recyclerMatchAdapter.setOnItemQuestionListener(this);
    }

    private void getListMatchsDay() {
        listMathdays = new ArrayList<>();
        for (int i = 0; i < competition.getNumberOfMatchdays(); i++) {
            if (i + 1 < competition.getCurrentMatchday()) {
                int[] item = new int[1];
                item[0] = 0;
                listMathdays.add(item);
            } else if (i + 1 == competition.getCurrentMatchday()) {
                int[] item = new int[1];
                item[0] = 1;
                listMathdays.add(item);
            } else if (i + 1 > competition.getCurrentMatchday()) {
                int[] item = new int[1];
                item[0] = 2;
                listMathdays.add(item);
            }
        }
    }

    private void loadMoreMatch(int limit, int offset) {
        if (listMatchs.size() == 1 && listMatchs.get(0) == null) {
            listMatchs.remove(0);
        }
        if (listMatchs.size() > 0) {
            listMatchs.remove(listMatchs.size() - 1);
            recyclerMatch.post(new Runnable() {
                @Override
                public void run() {
                    recyclerMatchAdapter.notifyItemRemoved(listMatchs.size());
                }
            });
        }
        for (int i = offset; i < offset + limit; i++) {
            if (i >= fixturesLeague.getFixtures().size())
                continue;
            listMatchs.add(fixturesLeague.getFixtures().get(i));
        }
        recyclerMatchAdapter.notifyDataSetChanged();
        recyclerMatchAdapter.setLoaded();
        //swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadFixturesLeagueSuccess(FixturesLeague fixturesLeague) {

        Toast.makeText(mContext, "Load fixtures success!", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        this.fixturesLeague = fixturesLeague;
        for (int i = 0; i < 10; i++) {
            listMatchs.add(fixturesLeague.getFixtures().get(i));
        }
        initRecyclerViewMatch();
    }

    @Override
    public void onLoadFixturesLeagueFail() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(mContext, "Load fixtures failed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreQuestion(final int offset) {
        if (listMatchs.size() < fixturesLeague.getFixtures().size()) {
            listMatchs.add(null);

            recyclerMatch.post(new Runnable() {
                @Override
                public void run() {
                    recyclerMatchAdapter.notifyItemInserted(listMatchs.size() - 1);
                }
            });

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadMoreMatch(10, offset);
                }
            }, 1000);
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
