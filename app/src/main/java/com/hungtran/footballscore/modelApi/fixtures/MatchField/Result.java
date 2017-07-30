package com.hungtran.footballscore.modelApi.fixtures.MatchField;

import com.hungtran.footballscore.modelApi.fixtures.MatchField.ResultField.ExtraTime;
import com.hungtran.footballscore.modelApi.fixtures.MatchField.ResultField.HalfTime;
import com.hungtran.footballscore.modelApi.fixtures.MatchField.ResultField.PenaltyShootout;

/**
 * Created by Hung Tran on 7/21/2017.
 */

public class Result {
    private int goalsHomeTeam;
    private int goalsAwayTeam;
    private HalfTime halfTime;
    private ExtraTime extraTime;
    private PenaltyShootout penaltyShootout;




    public HalfTime getHalfTime() {
        return halfTime;
    }

    public void setHalfTime(HalfTime halfTime) {
        this.halfTime = halfTime;
    }

    public ExtraTime getExtraTime() {
        return extraTime;
    }

    public void setExtraTime(ExtraTime extraTime) {
        this.extraTime = extraTime;
    }

    public PenaltyShootout getPenaltyShootout() {
        return penaltyShootout;
    }

    public void setPenaltyShootout(PenaltyShootout penaltyShootout) {
        this.penaltyShootout = penaltyShootout;
    }

    public int getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public void setGoalsHomeTeam(int goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    public int getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public void setGoalsAwayTeam(int goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
    }
}
