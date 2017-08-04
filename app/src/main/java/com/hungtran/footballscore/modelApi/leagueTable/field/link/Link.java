package com.hungtran.footballscore.modelApi.leagueTable.field.link;

import com.hungtran.footballscore.modelApi.fixtures.MatchField.LinkField.Competition;
import com.hungtran.footballscore.modelApi.leagueTeam.TeamField.Self;

/**
 * Created by Hung Tran on 8/4/2017.
 */

public class Link {
    private Self self;
    private Competition competition;

    public Self getSelf() {
        return self;
    }

    public Competition getCompetition() {
        return competition;
    }
}
