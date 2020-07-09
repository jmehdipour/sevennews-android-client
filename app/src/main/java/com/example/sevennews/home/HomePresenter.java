package com.example.sevennews.home;

import com.example.sevennews.R;
import com.example.sevennews.data.Banner;
import com.example.sevennews.data.News;
import com.example.sevennews.data.NewsDataSource;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter{
    private HomeContract.View view;
    private NewsDataSource newsDataSource;
    private CompositeDisposable compositeDisposable =  new CompositeDisposable();
    private boolean isViewRendered;
    private Subscription subscription;

    public HomePresenter(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void getNewsList() {
        view.setProgressIndicator(true);
        newsDataSource.getNews().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<List<News>>() {
                    @Override
                    public void accept(List<News> newsList) throws Exception {
                        view.showNews(newsList);
                        isViewRendered = true;
                        view.setProgressIndicator(false);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError(view.context().getString(R.string.all_unknownError));
                        view.setProgressIndicator(false);
                    }
                })
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        HomePresenter.this.subscription = subscription;
                    }
                })
                .subscribe();
    }

    @Override
    public void getBanners() {
        view.setProgressIndicator(true);
        newsDataSource.getBanner().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Banner>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Banner> banners) {
                        view.showBanners(banners);
                        view.setProgressIndicator(false);
                        isViewRendered = true;

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(view.context().getString(R.string.all_unknownError));
                        view.setProgressIndicator(false);
                    }
                });
    }

    @Override
    public void attachView(HomeContract.View view) {
        this.view = view;
        if(!isViewRendered){
            getNewsList();
            getBanners();
        }

    }

    @Override
    public void detachView() {
        this.view = null;
        if (compositeDisposable != null && compositeDisposable.size()>0 ){
            compositeDisposable.clear();
        }

        if(subscription !=  null){
            subscription.cancel();
        }
    }
}
