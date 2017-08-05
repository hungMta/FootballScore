package com.hungtran.footballscore.ui.team.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hungtran.footballscore.R;
import com.hungtran.footballscore.modelApi.player.ListPlayers;
import com.hungtran.footballscore.modelApi.player.feild.Player;

/**
 * Created by Hung Tran on 8/5/2017.
 */

public class RecyclerListPlayersAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ListPlayers listPlayers;
    private static OnItemPlayerClickListener onItemPlayerClickListener;

    public RecyclerListPlayersAdapter(Context context, ListPlayers listPlayers) {
        this.mContext = context;
        this.listPlayers = listPlayers;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(View.inflate(mContext, R.layout.item_player_recycler, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ItemHolder) holder).stt.setText(position + 1 + "");
        ((ItemHolder) holder).name.setText(listPlayers.getPlayers().get(position).getName());
        ((ItemHolder) holder).pos.setText(listPlayers.getPlayers().get(position).getPosition());
        ((ItemHolder) holder).num.setText(listPlayers.getPlayers().get(position).getJerseyNumber() + "");
        ((ItemHolder)holder).linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemPlayerClickListener != null){
                    onItemPlayerClickListener.onItemPlayerClick(listPlayers.getPlayers().get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listPlayers.getCount();
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        private LinearLayout linearLayout;
        private TextView stt;
        private TextView name;
        private TextView pos;
        private TextView num;

        public ItemHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.item_player);
            stt = (TextView) itemView.findViewById(R.id.txt_stt);
            name = (TextView) itemView.findViewById(R.id.txt_name_player);
            pos = (TextView) itemView.findViewById(R.id.txt_pos);
            num = (TextView) itemView.findViewById(R.id.txt_number);
        }
    }

    public interface OnItemPlayerClickListener {
        void onItemPlayerClick(Player player);
    }

    public static void setOnItemPlayerClickListener(OnItemPlayerClickListener listener) {
        onItemPlayerClickListener = listener;
    }
}
