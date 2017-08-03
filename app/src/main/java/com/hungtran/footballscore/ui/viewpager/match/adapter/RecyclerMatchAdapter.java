package com.hungtran.footballscore.ui.viewpager.match.adapter;

import android.app.Activity;
import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.caverock.androidsvg.SVG;
import com.hungtran.footballscore.R;
import com.hungtran.footballscore.core.svg.SvgDecoder;
import com.hungtran.footballscore.core.svg.SvgDrawableTranscoder;
import com.hungtran.footballscore.core.svg.SvgSoftwareLayerSetter;
import com.hungtran.footballscore.modelApi.fixtures.Match;
import com.hungtran.footballscore.modelApi.leagueTeam.LeagueTeam;
import com.hungtran.footballscore.modelApi.leagueTeam.TeamField.Team;
import com.hungtran.footballscore.utils.CacheUtil;
import com.hungtran.footballscore.utils.ImageUtil;
import com.hungtran.footballscore.utils.TimeUtil;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Hung Tran on 7/21/2017.
 */

public class RecyclerMatchAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ITEM_MATCH = 1;
    private static final int TYPE_ITEM_LOAD_MORE = 2;

    private List<Match> listMatch;
    private Context mContext;
    private Activity activity;
    private static OnItemQuestionListener onItemQuestionListener;
    private int totalItemCount;
    private int lastVisibleItem;
    private boolean isLoading;
    private int visibleThreshold = 1;
    private LeagueTeam leagueTeam;
    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;
    private int matchday;


    public RecyclerMatchAdapter(Context context, final List<Match> listMatch, RecyclerView recyclerView, Activity activity, LeagueTeam leagueTeam) {
        this.mContext = context;
        this.listMatch = listMatch;
        this.activity = activity;
        this.leagueTeam = leagueTeam;
        requestBuilder = Glide.with(mContext)
                .using(Glide.buildStreamModelLoader(Uri.class, mContext), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .placeholder(R.drawable.ic_icon_ball)
                .error(R.drawable.ic_icon_ball)
                .animate(android.R.anim.fade_in)
                .listener(new SvgSoftwareLayerSetter<Uri>());
        initOnScrollRecyclerView(recyclerView);

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM_MATCH) {
            return new ItemMatch(View.inflate(mContext, R.layout.item_match_2, null));
        } else {
            return new ItemLoadMore(View.inflate(mContext, R.layout.item_loadmore, null));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemMatch) {
            ((ItemMatch) holder).txtHomeTeamName.setText(listMatch.get(position).getHomeTeamName());
            ((ItemMatch) holder).txtAwayTeamName.setText(listMatch.get(position).getAwayTeamName());
            ((ItemMatch) holder).txtStatus.setText(listMatch.get(position).getStatus());
            ((ItemMatch) holder).txtDate.setText(TimeUtil.newInstace(mContext).getDay(listMatch.get(position).getDate()));
            ((ItemMatch) holder).txtTime.setText(TimeUtil.newInstace(mContext).getTime(listMatch.get(position).getDate()));
            Team home = getTeams(listMatch.get(position).get_links().getHomeTeam().getHref());
            Team away = getTeams(listMatch.get(position).get_links().getAwayTeam().getHref());
            if (home != null) {
                if (home.getCrestUrl() != null) {
                    if (home.getCrestUrl().contains("svg")) {
                        setLogoBall(((ItemMatch) holder).imgHomeTeam, position);
                    } else {
                        Picasso.with(mContext).load(home.getCrestUrl()).into(((ItemMatch) holder).imgHomeTeam);
                    }
                } else {
                    setLogoBall(((ItemMatch) holder).imgHomeTeam, position);
                }
//                if (home.getCrestUrl().contains("svg")) {
//                    requestBuilder
//                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                            // SVG cannot be serialized so it's not worth to cache it
//                            .load(uri)
//                            .into(((ItemMatch) holder).imgHomeTeam);
//                } else if (home.getCrestUrl().contains("png")) {
//
//                }
            } else {
                ((ItemMatch) holder).imgHomeTeam.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_icon_ball));
            }
            if (away != null) {
                if (away.getCrestUrl() != null) {
                    if (away.getCrestUrl().contains("svg")) {
                        setLogoBall(((ItemMatch) holder).imgAwayTeam, position + 4);
                    } else {
                        Picasso.with(mContext).load(home.getCrestUrl()).into(((ItemMatch) holder).imgAwayTeam);
                    }
                } else {
                    setLogoBall(((ItemMatch) holder).imgAwayTeam, position + 4);
                }
//                if (away.getCrestUrl().contains("svg")) {
//                    requestBuilder
//                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                            // SVG cannot be serialized so it's not worth to cache it
//                            .load(uri)
//                            .into(((ItemMatch) holder).imgAwayTeam);
//                } else if (away.getCrestUrl().contains("png")) {
//
//                }
            } else {
                ((ItemMatch) holder).imgAwayTeam.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_icon_ball));
            }
            if (String.valueOf(listMatch.get(position).getResult().getGoalsAwayTeam()) != null) {
                ((ItemMatch) holder).txtAwayScore.setText(listMatch.get(position).getResult().getGoalsAwayTeam() + "");
                ((ItemMatch) holder).txtHomeScore.setText(listMatch.get(position).getResult().getGoalsHomeTeam() + "");
            }
            ((ItemMatch) holder).txtStatus.setText(listMatch.get(position).getStatus());
        } else if (holder instanceof ItemLoadMore) {

        }
    }

    public void notifyChange(List<Match> listMatch) {
        this.listMatch = listMatch;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listMatch == null ? 0 : listMatch.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (listMatch.get(position) == null) {
            return TYPE_ITEM_LOAD_MORE;
        } else
            return TYPE_ITEM_MATCH;

    }

    private void initOnScrollRecyclerView(RecyclerView recyclerView) {
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (linearLayoutManager == null)
                    return;
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold) && totalItemCount >= 10) {
                    if (onItemQuestionListener != null) {
                        isLoading = true;
                        onItemQuestionListener.onLoadMoreQuestion(totalItemCount);
                    }
                }
            }
        });
    }

    private void setLogoBall(ImageView imageView, int position) {
        switch (position % 9) {
            case 0:
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_ball_one));
                break;
            case 1:
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_ball_two));
                break;
            case 3:
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_ball_three));
                break;
            case 4:
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_ball_four));
                break;
            case 5:
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_champion_league_one));
                break;
            case 6:
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_champion_league_two));
                break;
            case 7:
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_champion_league_three));
                break;
            case 8:
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_champion_league_four));
                break;
            default:
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_champion_league_five));
                break;
        }
    }

    public void setLoaded() {
        isLoading = false;
    }

    private Team getTeams(String selfUrl) {
        for (Team team : leagueTeam.getTeams()) {
            if (team.get_links().getSelf().getHref().equals(selfUrl)) {
                return team;
            }
        }
        return null;
    }

    private static class ItemMatch extends RecyclerView.ViewHolder {

        private TextView txtDate;
        private TextView txtHomeTeamName;
        private TextView txtAwayTeamName;
        private ImageView imgHomeTeam;
        private ImageView imgAwayTeam;
        private TextView txtStatus;
        private TextView txtHomeScore;
        private TextView txtAwayScore;
        private TextView txtTime;

        public ItemMatch(View itemView) {
            super(itemView);
            txtDate = (TextView) itemView.findViewById(R.id.txt_day_match);
            txtTime = (TextView) itemView.findViewById(R.id.txt_time_match);
            txtHomeTeamName = (TextView) itemView.findViewById(R.id.name_home_team);
            txtAwayTeamName = (TextView) itemView.findViewById(R.id.name_away_team);
            txtStatus = (TextView) itemView.findViewById(R.id.status);
            txtHomeScore = (TextView) itemView.findViewById(R.id.home_score);
            txtAwayScore = (TextView) itemView.findViewById(R.id.away_score);
            imgHomeTeam = (ImageView) itemView.findViewById(R.id.logo_home_team);
            imgAwayTeam = (ImageView) itemView.findViewById(R.id.logo_away_team);
        }
    }

    private static class ItemLoadMore extends RecyclerView.ViewHolder {

        private ProgressBar progressBar;

        public ItemLoadMore(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }

    public interface OnItemQuestionListener {
        void onLoadMoreQuestion(int offset);

    }

    public static void setOnItemQuestionListener(OnItemQuestionListener listener) {
        onItemQuestionListener = listener;
    }

}
