package com.example.sevennews.data;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface NewsDataSource {

    Flowable<List<News>> getNews();

    Single<List<Banner>> getBanner();

    Single<List<News>> getVideoNews();

    void bookmark(News news);

    //Completable unBookmark(News news); // Completable => onSuccess without data parameter

    Single<List<News>> getBookmarkedNews();

    Single<List<News>> search(String keyword); // Single => onSuccess with data parameter


}
