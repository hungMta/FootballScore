package com.hungtran.footballscore.modelApi.player;

import android.content.Context;
import android.os.Bundle;

import com.hungtran.footballscore.modelApi.player.feild.Link;
import com.hungtran.footballscore.modelApi.player.feild.Player;
import com.hungtran.footballscore.restBase.ServiceGenerator;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hung Tran on 8/5/2017.
 */

public class ListPlayers implements Serializable {

    private transient static ListPlayers listPlayers;
    private transient static Context mContext;

    private Link _links;
    private int count;
    private List<Player> players;

    public static ListPlayers newInstance(Context context) {
        mContext = context;
        if (listPlayers == null) {
            listPlayers = new ListPlayers();
        }
        return listPlayers;
    }

    public void getListPlayers(int idTeam, final OnGetListPlayersListener onGetListPlayersListener) {
        Call<ListPlayers> call = ServiceGenerator.resquest(mContext, ServiceGenerator.TIMEOUT_LENGHT).getListPlayers(ServiceGenerator.AU_TOKEN, idTeam);
        call.enqueue(new Callback<ListPlayers>() {
            @Override
            public void onResponse(Call<ListPlayers> call, Response<ListPlayers> response) {
                if (response.isSuccessful()) {
                    onGetListPlayersListener.onGetListPlayersSuccess(response.body());
                } else {
                    onGetListPlayersListener.onGetListPlayersFail();
                }
            }

            @Override
            public void onFailure(Call<ListPlayers> call, Throwable t) {
                onGetListPlayersListener.onGetListPlayersFail();
            }
        });
    }

    public interface OnGetListPlayersListener {
        void onGetListPlayersSuccess(ListPlayers list);

        void onGetListPlayersFail();
    }


    public Link get_links() {
        return _links;
    }

    public void set_links(Link _links) {
        this._links = _links;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
