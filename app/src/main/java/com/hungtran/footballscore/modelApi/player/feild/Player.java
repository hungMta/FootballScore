package com.hungtran.footballscore.modelApi.player.feild;

import java.io.Serializable;

/**
 * Created by Hung Tran on 8/5/2017.
 */

public class Player implements Serializable {
    private String name;
    private String position;
    private int jerseyNumber;
    private String dateOfBirth;
    private String nationality;
    private String contractUntil;
    private String marketValue;

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public String getContractUntil() {
        return contractUntil;
    }

    public String getMarketValue() {
        return marketValue;
    }
}
