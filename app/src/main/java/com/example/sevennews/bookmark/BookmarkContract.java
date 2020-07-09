package com.example.sevennews.bookmark;

import com.example.sevennews.base.BasePresenter;
import com.example.sevennews.base.BaseView;
import com.example.sevennews.data.News;

import java.util.List;

public interface BookmarkContract {

    interface View extends BaseView {
        void showBookmarkedNewsList(List<News> newsList);
        void showEmptyState();
        void hideEmptyState();
    }

    interface Presenter extends BasePresenter<View> {
        void loadBookmarkedNewsList();
    }
}
