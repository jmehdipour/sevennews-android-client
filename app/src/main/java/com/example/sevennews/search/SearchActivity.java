package com.example.sevennews.search;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


import com.example.sevennews.R;
import com.example.sevennews.base.BaseActivity;
import com.example.sevennews.data.News;
import com.example.sevennews.data.NewsRepositoryProvider;
import com.example.sevennews.home.NewsAdapter;

import java.util.List;

public class SearchActivity extends BaseActivity implements SearchContract.View, TextWatcher {
    private EditText searchEditTextView;
    private ImageView clearButton;
    private ImageView backButton;
    private RecyclerView searchRecyclerView;
    private NewsAdapter newsAdapter;
    private View noResultMessage;
    private CoordinatorLayout root;
    private SearchContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        presenter = new SearchPresenter(NewsRepositoryProvider.provideNewsDataSource(this));
        presenter.attachView(this);
        setupViews();
    }

    @Override
    public void setProgressIndicator(boolean shouldShow) {

    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(root, errorMessage, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return null;
    }

    public void setupViews(){
        searchRecyclerView = findViewById(R.id.rv_search);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        newsAdapter = new NewsAdapter();
        searchRecyclerView.setAdapter(newsAdapter);

        searchEditTextView = findViewById(R.id.et_search);
        searchEditTextView.addTextChangedListener(this);

        noResultMessage = findViewById(R.id.tv_search_noResultMessage);

        root = findViewById(R.id.coordinator_search);

        clearButton = findViewById(R.id.iv_search_clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEditTextView.setText("");
            }
        });

        backButton = findViewById(R.id.iv_search_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 0){
            presenter.search(s.toString());
            clearButton.setVisibility(View.VISIBLE);
        }else{
            searchRecyclerView.setVisibility(View.GONE);
            noResultMessage.setVisibility(View.INVISIBLE);
            clearButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void showResults(List<News> newsList) {
        searchRecyclerView.setVisibility(View.VISIBLE);
        noResultMessage.setVisibility(View.INVISIBLE);
        newsAdapter.setNewsList(newsList);
    }

    @Override
    public void showNoResultMessage() {
        searchRecyclerView.setVisibility(View.INVISIBLE);
        noResultMessage.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
