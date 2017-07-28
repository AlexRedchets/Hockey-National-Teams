package com.alexredchets.hockeynationalteams.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexredchets.hockeynationalteams.R;
import com.alexredchets.hockeynationalteams.model.Player;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private List<Player> playerList;
    private Context context;
    private ClickListener clickListener;

    public TeamAdapter(Context context, ClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void updateAdapter(List<Player> playerList){
        this.playerList = playerList;
        notifyDataSetChanged();
        Timber.i("Adapter is updated");
    }

    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_player, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamAdapter.ViewHolder holder, int position) {
        Player player = playerList.get(position);

        holder.mTextPlayerName.setText(player.getName());
        Picasso
                .with(context)
                .load(player.getImageUrl())
                .into(holder.mImgPlayer);
    }

    @Override
    public int getItemCount() {
        return playerList != null ? playerList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.img_player_cardView)
        ImageView mImgPlayer;
        @BindView(R.id.text_name_cardView)
        TextView mTextPlayerName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(playerList.get(getAdapterPosition()));
        }
    }

    public interface ClickListener {
        void onClick(Player player);
    }
}