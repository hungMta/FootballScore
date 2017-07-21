package com.hungtran.footballscore.ui.viewpager.match.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hungtran.footballscore.R;
import com.hungtran.footballscore.modelApi.competition.Competition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PC on 20/07/2017.
 */

public class RecyclerMatchdaysAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private static List<int[]> listMatchdays;
    private static OnItemMatchdaysClickListener onItemMatchdaysClickListener;

    public RecyclerMatchdaysAdapter(Context context, List<int[]> list) {
        this.mContext = context;
        this.listMatchdays = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(View.inflate(mContext, R.layout.item_matchdays_reycler, null));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((ItemView) holder).txtMatchdays.setText(position + 1 + "");
        if (listMatchdays.get(position)[0] == 0) {
            ((ItemView) holder).txtMatchdays.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_item_match_days_passed));
        } else if (listMatchdays.get(position)[0] == 1) {
            ((ItemView) holder).txtMatchdays.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_item_match_days_current));
        } else if (listMatchdays.get(position)[0] == 2) {
            ((ItemView) holder).txtMatchdays.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_item_match_days_future));
        }
        if (listMatchdays.get(position)[0] == 3) {
            ((ItemView) holder).txtMatchdays.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_item_match_days_chosing));
        }
    }

    @Override
    public int getItemCount() {
        return listMatchdays.size();
    }

    public void notifyDataSetchanged(List<int[]> list) {
        this.listMatchdays = list;
        notifyDataSetChanged();
    }

    private class ItemView extends RecyclerView.ViewHolder {

        private TextView txtMatchdays;

        public ItemView(View itemView) {
            super(itemView);
            txtMatchdays = (TextView) itemView.findViewById(R.id.txt_match_days);
            txtMatchdays.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemMatchdaysClickListener != null) {
                        onItemMatchdaysClickListener.OnItemMatchdaysClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface OnItemMatchdaysClickListener {
        void OnItemMatchdaysClick(int positition);
    }

    public static void SetOnItemMatchdaysClickListener(OnItemMatchdaysClickListener onClick) {
        onItemMatchdaysClickListener = onClick;
    }
}
