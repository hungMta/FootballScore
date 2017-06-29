package com.hungtran.footballscore.model.competition;

import android.content.Context;
import android.util.Log;

import com.hungtran.footballscore.model.link.Link;
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
    private transient static OnLoadApiCompetitionsListener onLoadApiCompetitionsListener;
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

    public void getListCompetition() {
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
        onLoadApiCompetitionsListener = listener;
    }
}
