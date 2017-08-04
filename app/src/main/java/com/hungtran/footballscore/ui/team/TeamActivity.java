package com.hungtran.footballscore.ui.team;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hungtran.footballscore.R;
import com.hungtran.footballscore.modelApi.leagueTeam.LeagueTeam;
import com.hungtran.footballscore.modelApi.team.Team;
import com.hungtran.footballscore.ui.viewpager.leagueTable.LeagueTableFragment;
import com.hungtran.footballscore.utils.PreferentUtil;
import com.squareup.picasso.Picasso;

/**
 * Created by Hung Tran on 8/4/2017.
 */

public class TeamActivity extends AppCompatActivity implements Team.OnGetTeamInfoListener {
    private String teamHref;
    private LeagueTeam leagueTeam;
    private Team team;
    private TextView txtNameTeam;
    private TextView priceTeam;
    private ImageView imgLogo;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        getIntentData();
        txtNameTeam = (TextView) findViewById(R.id.txt_team_name);
        priceTeam = (TextView) findViewById(R.id.txt_price);
        imgLogo = (ImageView) findViewById(R.id.img_logo_team);
    }

    private void getIntentData() {
        teamHref = getIntent().getExtras().getString(LeagueTableFragment.TEAM_HREF, "");
        String[] str = teamHref.split("/");
        int idTeam = Integer.valueOf(str[str.length - 1]);
        Team.newInstance(this).getTeamInfo(idTeam, this);
    }

    @Override
    public void onGetTeamInfoSuccess(Team team) {
        txtNameTeam.setText(team.getName());
        if (team.getCrestUrl().contains("png")) {
            Picasso.with(this).load(team.getCrestUrl()).into(imgLogo);
        }
        if (team.getSquadMarketValue() != null) {
            priceTeam.setText(team.getSquadMarketValue() + " £");
        } else {
            priceTeam.setText("100.100 £");
        }
    }

    @Override
    public void onGetTeamInfoFail() {
        Toast.makeText(this, getString(R.string.load_fail), Toast.LENGTH_SHORT).show();
    }
}
