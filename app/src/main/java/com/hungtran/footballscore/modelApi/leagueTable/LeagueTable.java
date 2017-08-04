package com.hungtran.footballscore.modelApi.leagueTable;

import android.content.Context;

import com.hungtran.footballscore.modelApi.leagueTable.field.link.Link;
import com.hungtran.footballscore.modelApi.leagueTable.field.standing.Standing;
import com.hungtran.footballscore.restBase.ServiceGenerator;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hung Tran on 25/06/2017.
 */

public class LeagueTable implements Serializable {

    private static transient LeagueTable leagueTable;
    private static transient Context mContext;

    private Link _links;
    private String leagueCaption;
    private int matchday;
    private List<Standing> standing;

    public static LeagueTable newInstance(Context context) {
        mContext = context;
        if (leagueTable == null) {
            leagueTable = new LeagueTable();
        }
        return leagueTable;
    }

    public void getLeagueTable(int idLeague, final OnGetLeagueTableListener onGetLeagueTableListener) {
        Call<LeagueTable> call = ServiceGenerator.resquest(mContext, ServiceGenerator.TIMEOUT_LENGHT).getLeagueTable(ServiceGenerator.AU_TOKEN, idLeague);
        call.enqueue(new Callback<LeagueTable>() {
            @Override
            public void onResponse(Call<LeagueTable> call, Response<LeagueTable> response) {
                if (response.isSuccessful()) {
                    onGetLeagueTableListener.onGetLeagueTableSuccess(response.body());
                } else {
                    onGetLeagueTableListener.onGetLeagueTableFail();
                }
            }

            @Override
            public void onFailure(Call<LeagueTable> call, Throwable t) {
                onGetLeagueTableListener.onGetLeagueTableFail();
            }
        });
    }

    public interface OnGetLeagueTableListener {
        void onGetLeagueTableSuccess(LeagueTable leagueTable);

        void onGetLeagueTableFail();
    }


    public Link get_links() {
        return _links;
    }

    public String getLeagueCaption() {
        return leagueCaption;
    }

    public int getMatchday() {
        return matchday;
    }

    public List<Standing> getStanding() {
        return standing;
    }
}
