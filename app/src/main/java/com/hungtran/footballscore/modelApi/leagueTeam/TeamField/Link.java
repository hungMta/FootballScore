package com.hungtran.footballscore.modelApi.leagueTeam.TeamField;

import com.hungtran.footballscore.modelApi.fixtures.MatchField.LinkField.Competition;

/**
 * Created by Hung Tran on 7/22/2017.
 */

public class Link {

    private Fixtures fixtures;
    private Players players;
    private Self self;
    private Competition competition;

    public Fixtures getFixtures() {
        return fixtures;
    }

    public void setFixtures(Fixtures fixtures) {
        this.fixtures = fixtures;
    }

    public Players getPlayers() {
        return players;
    }

    public void setPlayers(Players players) {
        this.players = players;
    }

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
