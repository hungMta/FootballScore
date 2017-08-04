package com.hungtran.footballscore.modelApi.team;

import android.content.Context;

import com.hungtran.footballscore.modelApi.leagueTeam.LeagueTeam;
import com.hungtran.footballscore.restBase.ServiceGenerator;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hung Tran on 7/22/2017.
 */

public class Team implements Serializable {

    private transient static Team team;
    private transient static Context mContext;

    private Link _links;
    private String name;
    private String code;
    private String shortName;
    private String squadMarketValue;
    private String crestUrl;

    public static Team newInstance(Context context) {
        mContext = context;
        if (team == null) {
            team = new Team();
        }
        return team;
    }

    public void getTeamInfo(int idTeam, final OnGetTeamInfoListener onGetTeamInfoListener) {
        Call<Team> call = ServiceGenerator.resquest(mContext, ServiceGenerator.TIMEOUT_LENGHT).getTeamInfo(ServiceGenerator.AU_TOKEN, idTeam);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if (response.isSuccessful()) {
                    onGetTeamInfoListener.onGetTeamInfoSuccess(response.body());
                } else {
                    onGetTeamInfoListener.onGetTeamInfoFail();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                onGetTeamInfoListener.onGetTeamInfoFail();
            }
        });
    }

    public interface OnGetTeamInfoListener {
        void onGetTeamInfoSuccess(Team team);

        void onGetTeamInfoFail();
    }

    public Link get_links() {
        return _links;
    }

    public void set_links(Link _links) {
        this._links = _links;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSquadMarketValue() {
        return squadMarketValue;
    }

    public void setSquadMarketValue(String squadMarketValue) {
        this.squadMarketValue = squadMarketValue;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

}
