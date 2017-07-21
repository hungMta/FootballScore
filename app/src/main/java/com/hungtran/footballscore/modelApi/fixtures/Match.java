package com.hungtran.footballscore.modelApi.fixtures;

import com.hungtran.footballscore.modelApi.fixtures.MatchField.HeadToHead;
import com.hungtran.footballscore.modelApi.fixtures.MatchField.Link;
import com.hungtran.footballscore.modelApi.fixtures.MatchField.Result;

import java.util.Date;

/**
 * Created by Hung Tran on 7/21/2017.
 */

public class Match {

    private Link _links;
    private Result result;
    private String awayTeamName;
    private String homeTeamName;
    private Date date;
    private int matchday;
    private String status;
    private HeadToHead head2head;


    public Link get_links() {
        return _links;
    }

    public void set_links(Link _links) {
        this._links = _links;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMatchday() {
        return matchday;
    }

    public void setMatchday(int matchday) {
        this.matchday = matchday;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HeadToHead getHead2head() {
        return head2head;
    }

    public void setHead2head(HeadToHead head2head) {
        this.head2head = head2head;
    }
}
