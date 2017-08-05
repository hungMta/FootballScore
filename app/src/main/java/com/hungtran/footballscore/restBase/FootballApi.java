package com.hungtran.footballscore.restBase;

import android.graphics.Bitmap;

import com.hungtran.footballscore.modelApi.competition.Competition;
import com.hungtran.footballscore.modelApi.fixtures.FixturesLeague;
import com.hungtran.footballscore.modelApi.leagueTable.LeagueTable;
import com.hungtran.footballscore.modelApi.leagueTeam.LeagueTeam;
import com.hungtran.footballscore.modelApi.player.ListPlayers;
import com.hungtran.footballscore.modelApi.team.Team;

import java.util.List;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

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

    @GET
    Call<ResponseBody> downloadLogo(@Url String filepath);

    @GET("/v1/competitions/{id_league}/leagueTable")
    Call<LeagueTable> getLeagueTable(@Header("X-Auth-Token") String auth_token, @Path("id_league") int idLeague);

    @GET("/v1/teams/{id_team}")
    Call<Team> getTeamInfo(@Header("X-Auth-Token") String auth_token, @Path("id_team") int id_team);

    @GET("/v1/teams/{id_team}/players")
    Call<ListPlayers> getListPlayers(@Header("X-Auth-Token") String auth_token, @Path("id_team") int id_team);

}
