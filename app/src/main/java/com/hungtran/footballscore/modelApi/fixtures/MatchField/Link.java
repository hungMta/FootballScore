package com.hungtran.footballscore.modelApi.fixtures.MatchField;

import com.hungtran.footballscore.modelApi.fixtures.MatchField.LinkField.AwayTeam;
import com.hungtran.footballscore.modelApi.fixtures.MatchField.LinkField.HomeTeam;
import com.hungtran.footballscore.modelApi.fixtures.MatchField.LinkField.Self;

import java.io.Serializable;

/**
 * Created by Hung Tran on 7/21/2017.
 */

public class Link implements Serializable {

    private AwayTeam awayTeam;
    private Competition competition;
    private HomeTeam homeTeam;
    private Self self;

    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(AwayTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(HomeTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }
}
