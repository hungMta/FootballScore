package com.hungtran.footballscore.ui.viewpager.match.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hungtran.footballscore.R;
import com.hungtran.footballscore.modelApi.fixtures.Match;

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
    private OnItemQuestionListener onItemQuestionListener;
    private int totalItemCount;
    private int lastVisibleItem;
    private boolean isLoading;
    private int visibleThreshold = 1;

    public RecyclerMatchAdapter(Context context, final List<Match> listMatch, RecyclerView recyclerView, Activity activity) {
        this.mContext = context;
        this.listMatch = listMatch;
        this.activity = activity;
        initOnScrollRecyclerView(recyclerView);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM_MATCH) {
            return new ItemMatch(View.inflate(mContext, R.layout.item_match_recycler, null));
        } else {
            return new ItemLoadMore(View.inflate(mContext, R.layout.item_loadmore, null));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemMatch) {
//            ((ItemMatch) holder).txtHomeTeamName.setText(listMatch.get(position).getHomeTeamName());
//            ((ItemMatch) holder).txtAwayTeamName.setText(listMatch.get(position).getAwayTeamName());
//            ((ItemMatch) holder).txtStatus.setText(listMatch.get(position).getStatus());
//            ((ItemMatch) holder).txtHomeScore.setText(listMatch.get(position).getResult().getGoalsHomeTeam());
//            ((ItemMatch) holder).txtAwayScore.setText(listMatch.get(position).getResult().getGoalsAwayTeam());
        } else if (holder instanceof ItemLoadMore) {

        }
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
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold) && totalItemCount >= 5) {
                    if (onItemQuestionListener != null) {
                        isLoading = true;
                        onItemQuestionListener.onLoadMoreQuestion(totalItemCount);
                    }
                }
            }
        });
    }

    public void setLoaded() {
        isLoading = false;
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
//            txtDate = (TextView) itemView.findViewById(R.id.txt_day_match);
//            txtTime = (TextView) itemView.findViewById(R.id.txt_time_match);
//            txtHomeTeamName = (TextView) itemView.findViewById(R.id.name_home_team);
//            txtAwayTeamName = (TextView) itemView.findViewById(R.id.name_away_team);
//            txtStatus = (TextView) itemView.findViewById(R.id.status);
//            txtHomeScore = (TextView) itemView.findViewById(R.id.home_score);
//            txtAwayScore = (TextView) itemView.findViewById(R.id.away_score);
//            imgHomeTeam = (ImageView) itemView.findViewById(R.id.logo_home_team);
//            imgAwayTeam = (ImageView) itemView.findViewById(R.id.logo_away_team);
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

    public void setOnItemQuestionListener(OnItemQuestionListener listener) {
        this.onItemQuestionListener = listener;
    }

}
