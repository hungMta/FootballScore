package com.hungtran.footballscore.modelApi.leagueTeam;

import android.content.Context;

import com.hungtran.footballscore.modelApi.leagueTeam.TeamField.Link;
import com.hungtran.footballscore.modelApi.leagueTeam.TeamField.Team;
import com.hungtran.footballscore.restBase.ServiceGenerator;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hung Tran on 7/22/2017.
 */

public class LeagueTeam implements Serializable {

    private static transient LeagueTeam leagueTeam;
    private static transient Context mContext;

    private Link _links;
    private int count;
    private List<Team> teams;

    public static LeagueTeam newInstance(Context context) {
        mContext = context;
        if (leagueTeam == null) {
            leagueTeam = new LeagueTeam();
        }
        return leagueTeam;
    }

    public void getLeagueTeamFormServer(int idLeague, final OnGetLeagueTeamsListener onGetLeagueTeamsListener) {
        Call<LeagueTeam> call = ServiceGenerator.resquest(mContext, ServiceGenerator.TIMEOUT_LENGHT).getLeagueTeams(ServiceGenerator.AU_TOKEN, idLeague);
        call.enqueue(new Callback<LeagueTeam>() {
            @Override
            public void onResponse(Call<LeagueTeam> call, Response<LeagueTeam> response) {
                if (response.isSuccessful()) {
                    onGetLeagueTeamsListener.onGetLeagueTeamsSuccess(response.body());
                } else {
                    onGetLeagueTeamsListener.onGetLeagueTeamsFail();
                }
            }

            @Override
            public void onFailure(Call<LeagueTeam> call, Throwable t) {
                onGetLeagueTeamsListener.onGetLeagueTeamsFail();
            }
        });
    }

    public interface OnGetLeagueTeamsListener {
        void onGetLeagueTeamsSuccess(LeagueTeam leagueTeam);

        void onGetLeagueTeamsFail();
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

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
