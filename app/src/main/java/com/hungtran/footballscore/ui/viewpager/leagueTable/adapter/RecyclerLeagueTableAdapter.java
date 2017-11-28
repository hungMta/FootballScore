package com.hungtran.footballscore.ui.viewpager.leagueTable.adapter;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.hungtran.footballscore.R;
import com.hungtran.footballscore.core.svg.SvgDecoder;
import com.hungtran.footballscore.core.svg.SvgDrawableTranscoder;
import com.hungtran.footballscore.core.svg.SvgSoftwareLayerSetter;
import com.hungtran.footballscore.modelApi.leagueTable.LeagueTable;
import com.squareup.picasso.Picasso;

import java.io.InputStream;

/**
 * Created by Hung Tran on 8/4/2017.
 */

public class RecyclerLeagueTableAdapter extends RecyclerView.Adapter {

    private LeagueTable leagueTable;
    private Context mContext;
    private static OnItemClickListener onItemClickListener;
    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;

    public RecyclerLeagueTableAdapter(LeagueTable leagueTable, Context context) {
        this.leagueTable = leagueTable;
        this.mContext = context;
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
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(View.inflate(mContext, R.layout.item_league_table, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ItemHolder) holder).stt.setText(position + 1 + "");
        ((ItemHolder) holder).teamName.setText(leagueTable.getStanding().get(position).getTeamName());
        ((ItemHolder) holder).MP.setText(String.valueOf(leagueTable.getStanding().get(position).getPlayedGames()));
        ((ItemHolder) holder).W.setText(String.valueOf(leagueTable.getStanding().get(position).getWins()));
        ((ItemHolder) holder).D.setText(String.valueOf(leagueTable.getStanding().get(position).getDraws()));
        ((ItemHolder) holder).L.setText(String.valueOf(leagueTable.getStanding().get(position).getLosses()));
        ((ItemHolder) holder).PTS.setText(String.valueOf(leagueTable.getStanding().get(position).getPoints()));
        String path = leagueTable.getStanding().get(position).getCrestURI();
        if (path != null)
            if (path.contains("svg")) {
                requestBuilder
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        // SVG cannot be serialized so it's not worth to cache it
                        .load(Uri.parse(path))
                        .into(((ItemHolder) holder).imgLogo);
            } else if (path.contains("png")) {
                Picasso.with(mContext).load(path).into(((ItemHolder) holder).imgLogo);
            }
//        if (path != null) {
//            if (path.contains("svg")) {
//                setLogoBall(((ItemHolder) holder).imgLogo, position);
//            } else {
//                try {
//                    Picasso.with(mContext).load(path).into(((ItemHolder) holder).imgLogo);
//                } catch (Exception e) {
//                    setLogoBall(((ItemHolder) holder).imgLogo, position);
//                }
//            }
//        } else {
//            setLogoBall(((ItemHolder) holder).imgLogo, position);
//        }
        if (position + 1 == 1) {
            ((ItemHolder) holder).stt.setBackgroundColor(mContext.getResources().getColor(R.color.blueDark));
            ((ItemHolder) holder).teamName.setBackgroundColor(mContext.getResources().getColor(R.color.blueDark));
            ((ItemHolder) holder).W.setBackgroundColor(mContext.getResources().getColor(R.color.blueDark));
            ((ItemHolder) holder).MP.setBackgroundColor(mContext.getResources().getColor(R.color.blueDark));
            ((ItemHolder) holder).D.setBackgroundColor(mContext.getResources().getColor(R.color.blueDark));
            ((ItemHolder) holder).L.setBackgroundColor(mContext.getResources().getColor(R.color.blueDark));
            ((ItemHolder) holder).PTS.setBackgroundColor(mContext.getResources().getColor(R.color.blueDark));
            ((ItemHolder) holder).imgLogo.setBackgroundColor(mContext.getResources().getColor(R.color.blueDark));
        } else if (position + 1 > 1 && position + 1 <= 4) {
            ((ItemHolder) holder).stt.setBackgroundColor(mContext.getResources().getColor(R.color.blueLight));
            ((ItemHolder) holder).teamName.setBackgroundColor(mContext.getResources().getColor(R.color.blueLight));
            ((ItemHolder) holder).W.setBackgroundColor(mContext.getResources().getColor(R.color.blueLight));
            ((ItemHolder) holder).MP.setBackgroundColor(mContext.getResources().getColor(R.color.blueLight));
            ((ItemHolder) holder).D.setBackgroundColor(mContext.getResources().getColor(R.color.blueLight));
            ((ItemHolder) holder).L.setBackgroundColor(mContext.getResources().getColor(R.color.blueLight));
            ((ItemHolder) holder).PTS.setBackgroundColor(mContext.getResources().getColor(R.color.blueLight));
            ((ItemHolder) holder).imgLogo.setBackgroundColor(mContext.getResources().getColor(R.color.blueLight));
        } else if (position + 1 > 4 && position + 1 <= 6) {
            ((ItemHolder) holder).stt.setBackgroundColor(mContext.getResources().getColor(R.color.orangeLight));
            ((ItemHolder) holder).teamName.setBackgroundColor(mContext.getResources().getColor(R.color.orangeLight));
            ((ItemHolder) holder).W.setBackgroundColor(mContext.getResources().getColor(R.color.orangeLight));
            ((ItemHolder) holder).MP.setBackgroundColor(mContext.getResources().getColor(R.color.orangeLight));
            ((ItemHolder) holder).D.setBackgroundColor(mContext.getResources().getColor(R.color.orangeLight));
            ((ItemHolder) holder).L.setBackgroundColor(mContext.getResources().getColor(R.color.orangeLight));
            ((ItemHolder) holder).PTS.setBackgroundColor(mContext.getResources().getColor(R.color.orangeLight));
            ((ItemHolder) holder).imgLogo.setBackgroundColor(mContext.getResources().getColor(R.color.orangeLight));
        } else if (position + 1 > leagueTable.getStanding().size() - 3) {
            ((ItemHolder) holder).stt.setBackgroundColor(mContext.getResources().getColor(R.color.pinkLight));
            ((ItemHolder) holder).teamName.setBackgroundColor(mContext.getResources().getColor(R.color.pinkLight));
            ((ItemHolder) holder).W.setBackgroundColor(mContext.getResources().getColor(R.color.pinkLight));
            ((ItemHolder) holder).MP.setBackgroundColor(mContext.getResources().getColor(R.color.pinkLight));
            ((ItemHolder) holder).D.setBackgroundColor(mContext.getResources().getColor(R.color.pinkLight));
            ((ItemHolder) holder).L.setBackgroundColor(mContext.getResources().getColor(R.color.pinkLight));
            ((ItemHolder) holder).PTS.setBackgroundColor(mContext.getResources().getColor(R.color.pinkLight));
            ((ItemHolder) holder).imgLogo.setBackgroundColor(mContext.getResources().getColor(R.color.pinkLight));
        } else {
            ((ItemHolder) holder).stt.setBackgroundColor(mContext.getResources().getColor(R.color.colorFA));
            ((ItemHolder) holder).teamName.setBackgroundColor(mContext.getResources().getColor(R.color.colorFA));
            ((ItemHolder) holder).W.setBackgroundColor(mContext.getResources().getColor(R.color.colorFA));
            ((ItemHolder) holder).MP.setBackgroundColor(mContext.getResources().getColor(R.color.colorFA));
            ((ItemHolder) holder).D.setBackgroundColor(mContext.getResources().getColor(R.color.colorFA));
            ((ItemHolder) holder).L.setBackgroundColor(mContext.getResources().getColor(R.color.colorFA));
            ((ItemHolder) holder).PTS.setBackgroundColor(mContext.getResources().getColor(R.color.colorFA));
            ((ItemHolder) holder).imgLogo.setBackgroundColor(mContext.getResources().getColor(R.color.colorFA));
        }
        ((ItemHolder) holder).linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClicked(leagueTable.getStanding().get(position).get_links().getTeam().getHref());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return leagueTable.getStanding() == null ? 0 : leagueTable.getStanding().size();
    }

    public interface OnItemClickListener {
        void onItemClicked(String teamHref);
    }

    public static void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
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

    private class ItemHolder extends RecyclerView.ViewHolder {

        private TextView stt;
        private TextView teamName;
        private ImageView imgLogo;
        private TextView MP;
        private TextView W;
        private TextView D;
        private TextView L;
        private TextView GF;
        private TextView GA;
        private TextView GD;
        private TextView PTS;
        private LinearLayout linearLayout;


        public ItemHolder(View itemView) {
            super(itemView);
            stt = (TextView) itemView.findViewById(R.id.txt_stt);
            teamName = (TextView) itemView.findViewById(R.id.txt_team_name);
            imgLogo = (ImageView) itemView.findViewById(R.id.img_logo_team);
            MP = (TextView) itemView.findViewById(R.id.txt_mp);
            W = (TextView) itemView.findViewById(R.id.txt_w);
            D = (TextView) itemView.findViewById(R.id.txt_d);
            L = (TextView) itemView.findViewById(R.id.txt_l);
            PTS = (TextView) itemView.findViewById(R.id.txt_pts);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.item_league_table);
        }
    }
}
