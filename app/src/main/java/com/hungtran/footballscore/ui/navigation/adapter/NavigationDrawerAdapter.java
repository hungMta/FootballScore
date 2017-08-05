package com.hungtran.footballscore.ui.navigation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hungtran.footballscore.R;
import com.hungtran.footballscore.modelApi.competition.Competition;

import java.util.List;

/**
 * Created by Hung Tran on 28/06/2017.
 */

public class NavigationDrawerAdapter extends RecyclerView.Adapter {

    private List<Competition> competitionList;
    private Context mContext;
    private static OnItemNavigationClickListener onItemNavigationClickListener;

    public NavigationDrawerAdapter(Context context, List<Competition> competition) {
        this.mContext = context;
        this.competitionList = competition;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(View.inflate(mContext, R.layout.item_menu_navigation, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (position == 0) {
            ((ItemView) holder).txtMenu.setText(mContext.getString(R.string.home));
            ((ItemView)holder).imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_home_black_24dp));
        } else {
            ((ItemView) holder).txtMenu.setText(competitionList.get(position).getCaption());
            ((ItemView)holder).imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_send_black_24dp));
        }
        ((ItemView) holder).linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemNavigationClickListener.onItemNavigationClick(position, competitionList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return competitionList == null ? 0 : competitionList.size();
    }

    public void notifyChange(List<Competition> list) {
        this.competitionList = list;
        notifyDataSetChanged();
    }

    private class ItemView extends RecyclerView.ViewHolder {

        public TextView txtMenu;
        private LinearLayout linearLayout;
        private ImageView imageView;

        public ItemView(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.layout_item_menu);
            txtMenu = (TextView) itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.img_item_navigation);
        }
    }

    public interface OnItemNavigationClickListener {
        void onItemNavigationClick(int pos, Competition competition);
    }

    public void setOnItemNavigationClickListener(OnItemNavigationClickListener listener) {
        onItemNavigationClickListener = listener;
    }


}
