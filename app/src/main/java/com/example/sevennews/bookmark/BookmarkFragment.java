package com.example.sevennews.bookmark;

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
import com.example.sevennews.data.NewsRepositoryProvider;
import com.example.sevennews.home.NewsAdapter;

import java.util.List;

public class BookmarkFragment extends BaseFragment implements BookmarkContract.View{
    private BookmarkContract.Presenter presenter;
    private View emptyState;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BookmarkPresenter(NewsRepositoryProvider.provideNewsDataSource(getContext()));

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
        emptyState = rootView.findViewById(R.id.frame_bookmark_emptyState);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_bookmarks;
    }

    @Override
    public void showBookmarkedNewsList(List<News> newsList) {
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_bookmarks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new NewsAdapter(newsList));
    }

    @Override
    public void showEmptyState() {
        emptyState.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyState() {
        emptyState.setVisibility(View.GONE);
    }

    @Override
    public void setProgressIndicator(boolean shouldShow) {
        getBaseActivity().setProgressIndicator(shouldShow);
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(rootView,errorMessage,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return getContext();
    }
}
