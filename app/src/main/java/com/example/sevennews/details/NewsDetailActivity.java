package com.example.sevennews.details;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sevennews.R;
import com.example.sevennews.base.BaseActivity;
import com.example.sevennews.data.News;
import com.example.sevennews.data.NewsDataSource;
import com.example.sevennews.data.NewsRepositoryProvider;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;


public class NewsDetailActivity extends BaseActivity {
    public static final String EXTRA_KEY_NEWS = "news";
    private News news;
    private static final int VIDEO_HEIGHT = 254;
    private static final int VIDEO_WIDTH = 400;
    private NewsDataSource newsDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        news = getIntent().getParcelableExtra(EXTRA_KEY_NEWS);
        newsDataSource = NewsRepositoryProvider.provideNewsDataSource(this);

        setupViews();
    }

    private void setupViews() {

        Toolbar toolbar = findViewById(R.id.toolbar_details);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setExpandedTitleTextColor(ContextCompat.getColorStateList(this,android.R.color.transparent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColorStateList(this,android.R.color.white));
        collapsingToolbarLayout.setTitle(news.getTitle());


        if(news.isVideoNews()){
            final FrameLayout frameLayout = findViewById(R.id.frame_details_videoContainer);

            frameLayout.post(new Runnable() {
                @Override
                public void run() {
                    frameLayout.getLayoutParams().height = frameLayout.getWidth() * VIDEO_HEIGHT/VIDEO_WIDTH;
                }
            });

            frameLayout.setVisibility(View.VISIBLE);
            JZVideoPlayerStandard jzVideoPlayerStandard = findViewById(R.id.videoPlayer_details);
            jzVideoPlayerStandard.setUp(news.getVideo(), JZVideoPlayer.SCREEN_WINDOW_NORMAL);
            Picasso.get().load(news.getImage()).into(jzVideoPlayerStandard.thumbImageView);
            jzVideoPlayerStandard.fullscreenButton.setVisibility(View.GONE);

        }else {
            ImageView imageView = findViewById(R.id.iv_details_newsImage);
            Picasso.get().load(news.getImage()).into(imageView);
            imageView.setVisibility(View.VISIBLE);
        }

        TextView newsTitleTextView = findViewById(R.id.tv_details_title);
        newsTitleTextView.setText(news.getTitle());

        TextView newsContentTextView = findViewById(R.id.tv_details_content);
        newsContentTextView.setText(news.getContent());

        TextView dateTextView = findViewById(R.id.tv_details_date);
        dateTextView.setText(news.getDate());

        final ImageView bookmarkImageView = findViewById(R.id.iv_details_bookmark);
        bookmarkImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news.setBookmarked(!news.isBookmarked());
                newsDataSource.bookmark(news);
                EventBus.getDefault().post(news);
                bookmarkImageView.setImageResource(news.isBookmarked() ? R.drawable.bookmark_checked : R.drawable.bookmark_outline);

            }
        });


        bookmarkImageView.setImageResource(news.isBookmarked() ? R.drawable.bookmark_checked : R.drawable.bookmark_outline);


    }


    @Override
    public void onBackPressed() {
        if (JZVideoPlayerStandard.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayerStandard.releaseAllVideos();
    }

    @Override
    public void setProgressIndicator(boolean shouldShow) {

    }
}
