package com.example.sevennews.videos;

import com.example.sevennews.base.BasePresenter;
import com.example.sevennews.base.BaseView;
import com.example.sevennews.data.News;

import java.util.List;

public interface VideoNewsListContract {

    interface View extends BaseView {
        void showVideoNewsList(List<News> newsList);
    }

    interface Presenter extends BasePresenter<View>{
        void loadVideoNewsList();
    }

}
