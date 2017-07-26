package com.alexredchets.hockeynationalteams.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexredchets.hockeynationalteams.R;
import com.alexredchets.hockeynationalteams.model.Country;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private List<Country> countryList;
    private Context context;
    private ClickListener clickListener;

    public CountryAdapter(Context context, ClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void updateAdapter(List<Country> countryList){
        this.countryList = countryList;
        notifyDataSetChanged();
        Timber.i("Adapter is updated");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_country, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Country country = countryList.get(position);

        holder.mTextCountryName.setText(country.getName());
        Picasso
                .with(context)
                .load(country.getFlag())
                .into(holder.mImgCountry);
    }

    @Override
    public int getItemCount() {
        return countryList != null ? countryList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.img_country_cardView)
        ImageView mImgCountry;
        @BindView(R.id.text_country_name_cardView)
        TextView mTextCountryName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(countryList.get(getAdapterPosition()));
        }
    }

    public interface ClickListener {
        void onClick(Country country);
    }

}
