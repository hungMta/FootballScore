package com.hungtran.footballscore.restBase;

import com.hungtran.footballscore.modelApi.competition.Competition;
import com.hungtran.footballscore.modelApi.fixtures.FixturesLeague;
import com.hungtran.footballscore.modelApi.leagueTeam.LeagueTeam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Hung Tran on 25/06/2017.
 */

public interface FootballApi {

    @GET("/v1/competitions")
    Call<List<Competition>> getCompetion(@Header("X-Auth-Token") String auth_token);

    @GET("/v1/competitions/{id_competition}/fixtures")
    Call<FixturesLeague> getFixturesCompetition(@Header("X-Auth-Token") String auth_token, @Path("id_competition") int idCompetition);

    @GET("/v1/competitions/{id_league}/teams")
    Call<LeagueTeam> getLeagueTeams(@Header("X-Auth-Token") String auth_token, @Path("id_league") int idLeague);
}
