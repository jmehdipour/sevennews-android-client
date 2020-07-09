package com.example.sevennews.home;

import com.example.sevennews.base.BasePresenter;
import com.example.sevennews.base.BaseView;
import com.example.sevennews.data.Banner;
import com.example.sevennews.data.News;

import java.util.List;

public interface HomeContract {

    interface View extends BaseView {
        void showNews(List<News> newsList);
        void showBanners(List<Banner> bannerList);
    }

    interface Presenter extends BasePresenter<View> {
        void getNewsList();
        void getBanners();
    }

}
