package com.example.sevennews.search;

import com.example.sevennews.R;
import com.example.sevennews.data.News;
import com.example.sevennews.data.NewsDataSource;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View view;
    private NewsDataSource newsDataSource;
    private Disposable disposable;

    public SearchPresenter(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }


    @Override
    public void search(String keyword) {
        newsDataSource.search(keyword).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(List<News> newsList) {

                        if (newsList.isEmpty()){
                            view.showNoResultMessage();
                        }else{
                            view.showResults(newsList);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(view.context().getString(R.string.all_unknownError));
                    }
                });
    }

    @Override
    public void attachView(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;

        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
