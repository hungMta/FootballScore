package com.hungtran.footballscore.modelApi.leagueTable.field.standing;


import com.hungtran.footballscore.modelApi.leagueTable.field.standing.feild.Link;

/**
 * Created by Hung Tran on 8/4/2017.
 */

public class Standing {
    private Link _links;
    private int position;
    private String teamName;
    private String crestURI;
    private int playedGames = 0;
    private int points = 0;
    private int goals = 0;
    private int goalsAgainst = 0;
    private int goalDifference = 0;
    private int wins = 0;
    private int draws = 0;
    private int losses = 0;
    private Home home;
    private Away away;

    public Link get_links() {
        return _links;
    }

    public int getPosition() {
        return position;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getCrestURI() {
        return crestURI;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public int getPoints() {
        return points;
    }

    public int getGoals() {
        return goals;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getLosses() {
        return losses;
    }

    public Home getHome() {
        return home;
    }

    public Away getAway() {
        return away;
    }
}
