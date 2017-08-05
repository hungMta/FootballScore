package com.hungtran.footballscore.ui.team.fragment;

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
import com.hungtran.footballscore.modelApi.player.ListPlayers;
import com.hungtran.footballscore.ui.team.fragment.adapter.RecyclerListPlayersAdapter;

/**
 * Created by Hung Tran on 8/4/2017.
 */

public class ListPlayersFragment extends Fragment {

    private static final String BUNDLE_ID_TEAM = "BUNDLE_ID_TEAM";
    private static final String BUNDLE_LIST_PLAYER = "BUNDLE_LIST_PLAYER";
    private static ListPlayersFragment listPlayersFragment;
    private PlayerInfoFragment playerInfoFragment;
    private static Context mContext;
    private int idTeam;
    private RecyclerListPlayersAdapter recyclerListPlayersAdapter;
    private RecyclerView recyclerView;
    private ListPlayers listPlayers;

    public static ListPlayersFragment newInstance(int idTeam, ListPlayers listPlayers) {
        if (listPlayersFragment == null) {
            listPlayersFragment = new ListPlayersFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(BUNDLE_ID_TEAM, idTeam);
            bundle.putSerializable(BUNDLE_LIST_PLAYER, listPlayers);
            listPlayersFragment.setArguments(bundle);
        }
        return listPlayersFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view;
        view = inflater.inflate(R.layout.fragment_list_player, container, false);
        idTeam = getArguments().getInt(BUNDLE_ID_TEAM, 0);
        listPlayers = (ListPlayers) getArguments().getSerializable(BUNDLE_LIST_PLAYER);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyler_list_player);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler();
        //  ListPlayers.newInstance(mContext).getListPlayers(idTeam, this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

//    @Override
//    public void onGetListPlayersSuccess(ListPlayers list) {
//        //    this.listPlayers = list;
//
//    }
//
//    @Override
//    public void onGetListPlayersFail() {
//
//    }

    private void initRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerListPlayersAdapter = new RecyclerListPlayersAdapter(mContext, listPlayers);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerListPlayersAdapter);
    }

}
