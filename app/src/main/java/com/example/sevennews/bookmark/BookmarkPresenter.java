package com.example.sevennews.bookmark;

import com.example.sevennews.R;
import com.example.sevennews.data.News;
import com.example.sevennews.data.NewsDataSource;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BookmarkPresenter implements BookmarkContract.Presenter {
    private BookmarkContract.View view;
    private NewsDataSource newsDataSource;
    private Disposable disposable;

    public BookmarkPresenter(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void loadBookmarkedNewsList() {
        view.setProgressIndicator(true);
        newsDataSource.getBookmarkedNews().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<News>>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onSuccess(List<News> newsList) {
                view.setProgressIndicator(false);
                if(newsList.isEmpty()){
                    view.showEmptyState();
                }else{
                    view.hideEmptyState();
                    view.showBookmarkedNewsList(newsList);
                }

            }

            @Override
            public void onError(Throwable e) {
                view.setProgressIndicator(false);
                view.showError(view.context().getString(R.string.all_unknownError));
            }
        });
    }

    @Override
    public void attachView(BookmarkContract.View view) {
        this.view = view;
        loadBookmarkedNewsList();
    }

    @Override
    public void detachView() {
        this.view = null;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

    }

}
