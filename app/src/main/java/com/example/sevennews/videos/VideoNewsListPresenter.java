package com.example.sevennews.videos;

import android.content.Context;

import com.example.sevennews.R;
import com.example.sevennews.data.News;
import com.example.sevennews.data.NewsDataSource;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VideoNewsListPresenter implements VideoNewsListContract.Presenter {
    private VideoNewsListContract.View view;
    private Disposable disposable;
    private NewsDataSource newsDataSource;
    private boolean isViewRendered;

    public VideoNewsListPresenter(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void loadVideoNewsList() {
        view.setProgressIndicator(true);
        newsDataSource.getVideoNews().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<News>>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onSuccess(List<News> newsList) {
                view.setProgressIndicator(false);
                view.showVideoNewsList(newsList);
                isViewRendered = true;

            }

            @Override
            public void onError(Throwable e) {
                view.setProgressIndicator(false);
                view.showError(view.context().getString(R.string.all_unknownError));
            }
        });
    }

    @Override
    public void attachView(VideoNewsListContract.View view) {
        this.view = view;
        if (! isViewRendered ){
            loadVideoNewsList();
        }
    }

    @Override
    public void detachView() {
        this.view = null;
        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
