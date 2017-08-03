package com.hungtran.footballscore.modelApi.leagueTable;

import java.io.Serializable;

/**
 * Created by Hung Tran on 25/06/2017.
 */

public class LeagueTable implements Serializable {

    private String leagueCaption;
    private int matchday;

    public String getLeagueCaption() {
        return leagueCaption;
    }

    public void setLeagueCaption(String leagueCaption) {
        this.leagueCaption = leagueCaption;
    }

    public int getMatchday() {
        return matchday;
    }

    public void setMatchday(int matchday) {
        this.matchday = matchday;
    }
}
