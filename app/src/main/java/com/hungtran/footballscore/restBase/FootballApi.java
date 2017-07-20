package com.hungtran.footballscore.restBase;

import com.hungtran.footballscore.modelApi.competition.Competition;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Hung Tran on 25/06/2017.
 */

public interface FootballApi {

    @GET("/v1/competitions")
    Call<List<Competition>> getCompetion();
}
