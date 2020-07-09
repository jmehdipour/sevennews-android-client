package com.example.sevennews.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.sevennews.R;
import com.example.sevennews.data.News;
import com.example.sevennews.home.NewsAdapter;
import com.example.sevennews.search.SearchActivity;

import java.util.List;

public class NewsListActivity extends AppCompatActivity {
    public static final String EXTRA_KEY_NEWS = "news";
    private List<News> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getExtras() !=  null){
            newsList = getIntent().getExtras().getParcelableArrayList(EXTRA_KEY_NEWS);
            if(newsList == null){
                finish();
            }

        }else{
            finish();
        }


        setContentView(R.layout.activity_news_list);
        setupToolbar();
        setupViews();


    }


    private void setupViews(){
        setupToolbar();
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new NewsAdapter(newsList));


    }

    private void setupToolbar(){
        View backButton = findViewById(R.id.iv_list_back);
        View searchButton = findViewById(R.id.iv_list_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsListActivity.this, SearchActivity.class));
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }




}
