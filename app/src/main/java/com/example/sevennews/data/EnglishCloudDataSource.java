package com.example.sevennews.data;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class EnglishCloudDataSource extends CloudDataSource {
    private EnglishApiService englishApiService;

    public EnglishCloudDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/bins/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        englishApiService = retrofit.create(EnglishApiService.class);
    }

    @Override
    public Flowable<List<News>> getNews() {
        return englishApiService.getNews();
    }

    @Override
    public Single<List<Banner>> getBanner() {
        return englishApiService.getBanners();
    }

    @Override
    public Single<List<News>> getVideoNews() {
        return englishApiService.getVideoNews();
    }

    @Override
    public void bookmark(News news) {

    }

    @Override
    public Single<List<News>> getBookmarkedNews() {
        return null;
    }

    @Override
    public Single<List<News>> search(String keyword) {
        return null;
    }
}
