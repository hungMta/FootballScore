package com.hungtran.footballscore.model.link;

import com.hungtran.footballscore.model.link.field.Fixtures;
import com.hungtran.footballscore.model.link.field.LeagueTable;
import com.hungtran.footballscore.model.link.field.Seft;
import com.hungtran.footballscore.model.link.field.Teams;

/**
 * Created by Hung Tran on 30/06/2017.
 */

public class Link {

    /**
     * link seft
     */
    private Seft seft;

    /**
     * team
     */
    private Teams teams;

    /**
     * a scheduled football
     */
    private Fixtures fixtures;

    /**
     * link leagueatable
     */
    private LeagueTable leagueTable;

    public Seft getSeft() {
        return seft;
    }

    public void setSeft(Seft seft) {
        this.seft = seft;
    }

    public Teams getTeams() {
        return teams;
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
    }

    public Fixtures getFixtures() {
        return fixtures;
    }

    public void setFixtures(Fixtures fixtures) {
        this.fixtures = fixtures;
    }

    public LeagueTable getLeagueTable() {
        return leagueTable;
    }

    public void setLeagueTable(LeagueTable leagueTable) {
        this.leagueTable = leagueTable;
    }
}
