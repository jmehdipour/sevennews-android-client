package com.example.sevennews.search;

import com.example.sevennews.base.BasePresenter;
import com.example.sevennews.base.BaseView;
import com.example.sevennews.data.News;

import java.util.List;

public interface SearchContract {
    interface View extends BaseView {
        void showResults(List<News> newsList);
        void showNoResultMessage();
    }

    interface Presenter extends BasePresenter<View> {
        void search(String keyword);
    }
}
