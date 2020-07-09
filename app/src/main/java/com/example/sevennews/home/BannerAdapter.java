package com.example.sevennews.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sevennews.R;
import com.example.sevennews.data.Banner;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {
    private List<Banner> banners = new ArrayList<>();

    public BannerAdapter(List<Banner> banners) {
        this.banners = banners;
    }



    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BannerViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_banner,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder bannerViewHolder, int i) {
        bannerViewHolder.bindBanner(banners.get(i));
    }

    @Override
    public int getItemCount() {
        return banners.size();
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        private ImageView bannerImageView;
        private TextView sourceTextView;
        private TextView titleTextView;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerImageView = itemView.findViewById(R.id.iv_banner_image);
            sourceTextView = itemView.findViewById(R.id.tv_banner_source);
            titleTextView = itemView.findViewById(R.id.tv_banner_title);

        }

        public void bindBanner(Banner banner){
            Picasso.get().load(banner.getImage()).into(bannerImageView);
            sourceTextView.setText(banner.getSource());
            titleTextView.setText(banner.getTitle());
        }

    }
}
