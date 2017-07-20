package com.hungtran.footballscore.modelApi.competition;

import android.content.Context;
import android.util.Log;

import com.hungtran.footballscore.modelApi.link.Link;
import com.hungtran.footballscore.restBase.ServiceGenerator;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * available competitions for the current season.
 */

public class Competition {

    private transient static Competition competition;
    private transient static Context mContext;

    public Link get_links() {
        return _links;
    }

    public void set_links(Link _links) {
        this._links = _links;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getCurrentMatchday() {
        return currentMatchday;
    }

    public void setCurrentMatchday(int currentMatchday) {
        this.currentMatchday = currentMatchday;
    }

    public int getNumberOfMatchdays() {
        return numberOfMatchdays;
    }

    public void setNumberOfMatchdays(int numberOfMatchdays) {
        this.numberOfMatchdays = numberOfMatchdays;
    }

    public int getNumberOfTeams() {
        return numberOfTeams;
    }

    public void setNumberOfTeams(int numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    private Link _links;

    private int id;

    private String caption;

    private String league;

    private String year;

    private int currentMatchday;

    private int numberOfMatchdays;

    private int numberOfTeams;

    private int numberOfGames;

    private Date lastUpdated;

    public static Competition newInstance(Context context) {
        mContext = context;
        if (competition == null) {
            competition = new Competition();
        }
        return competition;
    }

    /**
     * call api to get list available competitions
     */
    public void getListCompetition(final OnLoadApiCompetitionsListener onLoadApiCompetitionsListener) {
        Call<List<Competition>> call = ServiceGenerator.resquest(mContext, ServiceGenerator.TIMEOUT_SHORT).getCompetion();
        call.enqueue(new Callback<List<Competition>>() {
            @Override
            public void onResponse(Call<List<Competition>> call, Response<List<Competition>> response) {
                if (response.isSuccessful()) {
                    onLoadApiCompetitionsListener.onLoadSuccessApiCompetitions(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Competition>> call, Throwable t) {
                Log.d("api", "onFailure: " + t.getMessage());
                onLoadApiCompetitionsListener.onLoadFailedApiCompetitions(t.getMessage(), 0);
            }
        });
    }

    public interface OnLoadApiCompetitionsListener {
        void onLoadSuccessApiCompetitions(List<Competition> competitionList);

        void onLoadFailedApiCompetitions(String message, int errorCode);
    }

    public static void setOnLoadApiCompetitionsListener(OnLoadApiCompetitionsListener listener) {
     //   onLoadApiCompetitionsListener = listener;
    }
}
