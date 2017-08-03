package com.hungtran.footballscore.modelApi.fixtures;

import android.content.Context;

import com.hungtran.footballscore.modelApi.fixtures.MatchField.Link;
import com.hungtran.footballscore.modelApi.link.field.Fixtures;
import com.hungtran.footballscore.restBase.ServiceGenerator;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hung Tran on 7/21/2017.
 */

public class FixturesLeague implements Serializable {

    private transient static FixturesLeague fixturesLeague;
    private transient static Context mContext;

    private int count;
    private List<Match> fixtures;
    private Link _links;

    public static FixturesLeague newInstance(Context context) {
        mContext = context;
        if (fixturesLeague == null) {
            fixturesLeague = new FixturesLeague();
        }
        return fixturesLeague;
    }

    public void getFixturesLeague(final int idLeague, final OnLoadFixturesLeagueListener onLoadFixturesLeagueListener) {
        Call<FixturesLeague> call = ServiceGenerator.resquest(mContext, ServiceGenerator.TIMEOUT_LENGHT).getFixturesCompetition(ServiceGenerator.AU_TOKEN,idLeague);
        call.enqueue(new Callback<FixturesLeague>() {
            @Override
            public void onResponse(Call<FixturesLeague> call, Response<FixturesLeague> response) {
                if (response.isSuccessful()) {
                    onLoadFixturesLeagueListener.onLoadFixturesLeagueSuccess(response.body());
                } else {
                    onLoadFixturesLeagueListener.onLoadFixturesLeagueFail();
                }
            }

            @Override
            public void onFailure(Call<FixturesLeague> call, Throwable t) {
                onLoadFixturesLeagueListener.onLoadFixturesLeagueFail();
            }
        });
    }

    public interface OnLoadFixturesLeagueListener {
        void onLoadFixturesLeagueSuccess(FixturesLeague fixturesLeague);

        void onLoadFixturesLeagueFail();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Match> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Match> fixtures) {
        this.fixtures = fixtures;
    }

    public Link get_links() {
        return _links;
    }

    public void set_links(Link _links) {
        this._links = _links;
    }
}
