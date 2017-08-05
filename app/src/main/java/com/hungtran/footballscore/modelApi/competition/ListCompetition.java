package com.hungtran.footballscore.modelApi.competition;

import android.content.Context;
import android.util.Log;

import com.hungtran.footballscore.restBase.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hung Tran on 8/4/2017.
 */

public class ListCompetition {
    private transient static ListCompetition listCompetition;
    private transient static Context mContext;

    private List<Competition> competitionList;

    public static ListCompetition newInstance(Context context) {
        mContext = context;
        if (listCompetition == null) {
            listCompetition = new ListCompetition();
        }
        return listCompetition;
    }


    /**
     * call api to get list available competitions
     */
    public void getListCompetition(final OnLoadApiCompetitionsListener onLoadApiCompetitionsListener) {
        Call<List<Competition>> call = ServiceGenerator.resquest(mContext, ServiceGenerator.TIMEOUT_SHORT).getCompetion(ServiceGenerator.AU_TOKEN);
        call.enqueue(new Callback<List<Competition>>() {
            @Override
            public void onResponse(Call<List<Competition>> call, Response<List<Competition>> response) {
                if (response.isSuccessful()) {
                    onLoadApiCompetitionsListener.onLoadSuccessApiCompetitions(response.body());
//                    CacheUtil.newInstance(mContext).saveCache(response.body().toString(), CacheUtil.FILE_NAME_COMPETITION);
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

    public List<Competition> getCompetitionList() {
        return competitionList;
    }
}
