package com.example.sevennews.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sevennews.R;
import com.example.sevennews.base.BaseFragment;
import com.example.sevennews.data.Banner;
import com.example.sevennews.data.News;
import com.example.sevennews.data.NewsRepository;
import com.example.sevennews.list.NewsListActivity;
import com.example.sevennews.search.SearchActivity;
import com.example.sevennews.setting.SettingActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements HomeContract.View {
    private HomeContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HomePresenter(new NewsRepository(getContext()));
    }

    @Override
    public void showNews(final List<News> newsList) {

        RecyclerView newsRecyclerView = rootView.findViewById(R.id.rv_home_newsList);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        newsRecyclerView.setAdapter(new NewsAdapter(newsList));
        newsRecyclerView.setNestedScrollingEnabled(false);

        View viewAllButton = rootView.findViewById(R.id.tv_home_viewAll);
        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewsListActivity.class);
                intent.putParcelableArrayListExtra(NewsListActivity.EXTRA_KEY_NEWS, (ArrayList<? extends Parcelable>) newsList);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showBanners(List<Banner> banners) {

        RecyclerView bannerRecyclerView = rootView.findViewById(R.id.rv_home_banners);
        bannerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(bannerRecyclerView);
        bannerRecyclerView.setAdapter(new BannerAdapter(banners));
        bannerRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void setProgressIndicator(boolean shouldShow) {
        getBaseActivity().setProgressIndicator(shouldShow);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void setupViews() {

        View settingButton = rootView.findViewById(R.id.iv_home_setting);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });

        View searchButton = rootView.findViewById(R.id.iv_home_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }
}
