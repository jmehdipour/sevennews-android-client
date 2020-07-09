package com.example.sevennews.videos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sevennews.R;
import com.example.sevennews.base.BaseFragment;
import com.example.sevennews.data.News;
import com.example.sevennews.data.NewsRepository;
import com.example.sevennews.home.NewsAdapter;

import java.util.List;

public class VideoNewsListFragment extends BaseFragment implements VideoNewsListContract.View {
    private VideoNewsListContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter = new VideoNewsListPresenter(new NewsRepository(getContext()));

        return super.onCreateView(inflater, container, savedInstanceState);
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

    }

    @Override
    public int getLayoutRes() {
        return R.layout.
                fragment_video_news_list;
    }

    @Override
    public void showVideoNewsList(List<News> newsList) {
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_videos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new NewsAdapter(newsList));
    }

    @Override
    public void setProgressIndicator(boolean shouldShow) {
        getBaseActivity().setProgressIndicator(shouldShow);
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(rootView, errorMessage, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return null;
    }
}
