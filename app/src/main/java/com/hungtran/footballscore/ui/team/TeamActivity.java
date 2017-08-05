package com.hungtran.footballscore.ui.team;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hungtran.footballscore.R;
import com.hungtran.footballscore.modelApi.leagueTeam.LeagueTeam;
import com.hungtran.footballscore.modelApi.player.ListPlayers;
import com.hungtran.footballscore.modelApi.player.feild.Player;
import com.hungtran.footballscore.modelApi.team.Team;
import com.hungtran.footballscore.ui.team.fragment.ListPlayersFragment;
import com.hungtran.footballscore.ui.team.fragment.PlayerInfoFragment;
import com.hungtran.footballscore.ui.team.fragment.adapter.RecyclerListPlayersAdapter;
import com.hungtran.footballscore.ui.viewpager.leagueTable.LeagueTableFragment;
import com.hungtran.footballscore.utils.Logg;
import com.hungtran.footballscore.utils.PreferentUtil;
import com.squareup.picasso.Picasso;

/**
 * Created by Hung Tran on 8/4/2017.
 */

public class TeamActivity extends AppCompatActivity implements Team.OnGetTeamInfoListener, RecyclerListPlayersAdapter.OnItemPlayerClickListener, ListPlayers.OnGetListPlayersListener {
    private String teamHref;
    private LeagueTeam leagueTeam;
    private Team team;
    private TextView txtNameTeam;
    private TextView priceTeam;
    private ImageView imgLogo;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ListPlayersFragment listPlayersFragment;
    private PlayerInfoFragment playerInfoFragment;
    private int idTeam;
    private boolean isShowProfile;
    private ProgressBar progressBar;
    private ListPlayers listPlayers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        getIntentData();
        txtNameTeam = (TextView) findViewById(R.id.txt_team_name);
        priceTeam = (TextView) findViewById(R.id.txt_price);
        imgLogo = (ImageView) findViewById(R.id.img_logo_team);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        RecyclerListPlayersAdapter.setOnItemPlayerClickListener(this);
    }

    private void getIntentData() {
        teamHref = getIntent().getExtras().getString(LeagueTableFragment.TEAM_HREF, "");
        String[] str = teamHref.split("/");
        idTeam = Integer.valueOf(str[str.length - 1]);
        Team.newInstance(this).getTeamInfo(idTeam, this);
        ListPlayers.newInstance(this).getListPlayers(idTeam, this);
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
        ListPlayers.newInstance(this).getListPlayers(idTeam, this);
    }

    @Override
    public void onGetTeamInfoFail() {
        Toast.makeText(this, getString(R.string.load_fail), Toast.LENGTH_SHORT).show();
    }

    private void addFragmentListPlayers() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        listPlayersFragment = ListPlayersFragment.newInstance(idTeam,listPlayers);
        fragmentTransaction.replace(R.id.frameLayout, listPlayersFragment);
        fragmentTransaction.addToBackStack(ListPlayersFragment.class.getName());
        try {
            fragmentTransaction.commit();
        } catch (Exception e) {
            Logg.error(getClass(), "addFragmentListPlayers fail");
        }
    }

    private void addFragmentProfilePlayer(Player player) {
        playerInfoFragment = PlayerInfoFragment.newInstance(player);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, playerInfoFragment).addToBackStack(null);
        fragmentTransaction.addToBackStack(PlayerInfoFragment.class.getName());
        isShowProfile = true;
        try {
            fragmentTransaction.commit();
        } catch (Exception e) {
            Logg.error(getClass(), "addFragmentProfilePlayer fail");
        }
    }

    @Override
    public void onItemPlayerClick(Player player) {
        addFragmentProfilePlayer(player);
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager == null) {
            finish();
        }
        if (fragmentManager != null) {
            if (fragmentManager.getBackStackEntryCount() == 1) {
                finish();
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onGetListPlayersSuccess(ListPlayers list) {
        listPlayers = list;
        progressBar.setVisibility(View.GONE);
        addFragmentListPlayers();
    }

    @Override
    public void onGetListPlayersFail() {

    }
}