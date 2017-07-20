package com.hungtran.footballscore.ui.viewpager.match.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hungtran.footballscore.R;
import com.hungtran.footballscore.modelApi.competition.Competition;

/**
 * Created by PC on 20/07/2017.
 */

public class RecyclerMatchdaysAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private Competition competition;

    public RecyclerMatchdaysAdapter(Context context, Competition competition) {
        this.mContext = context;
        this.competition = competition;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(View.inflate(mContext, R.layout.item_matchdays_reycler, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ItemView) holder).txtMatchdays.setText(position + 1 + "");
        ((ItemView) holder).txtMatchdays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return competition.getNumberOfMatchdays();
    }

    private class ItemView extends RecyclerView.ViewHolder {

        private TextView txtMatchdays;

        public ItemView(View itemView) {
            super(itemView);
            txtMatchdays = (TextView) itemView.findViewById(R.id.txt_match_days);
        }
    }
}
