package com.example.sevennews.data;

import android.content.Context;

import com.example.sevennews.setting.SettingSharedPrefManager;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NewsRepository implements NewsDataSource {
    private LocalDataSource localDataSource ;
    private CloudDataSource cloudDataSource;

    public NewsRepository(Context context) {
        this.localDataSource = AppDatabase.getInstance(context).getLocalDataSource();
        if (SettingSharedPrefManager.getInstance(context).getDefaultLanguage().equalsIgnoreCase("fa")){
            cloudDataSource = new FarsiCloudDataSource();
        }else{
            cloudDataSource = new EnglishCloudDataSource();
        }

    }

    @Override
    public Flowable<List<News>> getNews() {
        cloudDataSource.getNews().subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread()).doOnNext(new Consumer<List<News>>() {
            @Override
            public void accept(List<News> newsList) throws Exception {
                localDataSource.saveNewsList(newsList);
            }
        }).subscribe();
        return localDataSource.getNews();
    }

    @Override
    public Single<List<Banner>> getBanner() {
        return cloudDataSource.getBanner();
    }

    @Override
    public Single<List<News>> getVideoNews() {
        return cloudDataSource.getVideoNews();
    }

    @Override
    public void bookmark(News news) {
        localDataSource.bookmark(news);
    }

    @Override
    public Single<List<News>> getBookmarkedNews() {
        return localDataSource.getBookmarkedNews();
    }

    @Override
    public Single<List<News>> search(String keyword) {
        return localDataSource.search(keyword);
    }

    public void clearCache(){
        localDataSource.removeAllRows();
    }
}
