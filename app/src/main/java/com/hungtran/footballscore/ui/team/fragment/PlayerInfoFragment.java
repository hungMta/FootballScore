package com.hungtran.footballscore.ui.team.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hungtran.footballscore.R;
import com.hungtran.footballscore.modelApi.player.feild.Player;

/**
 * Created by Hung Tran on 8/4/2017.
 */

public class PlayerInfoFragment extends Fragment {

    private static final String KEY_PLAYER = "KEY_PLAYER";
    private static PlayerInfoFragment playerInfoFragment;
    private Player player;
    public static PlayerInfoFragment newInstance(Player player){
        playerInfoFragment = new PlayerInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_PLAYER,player);
        return playerInfoFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_player_profile, container, false);
        return view;
    }
}
