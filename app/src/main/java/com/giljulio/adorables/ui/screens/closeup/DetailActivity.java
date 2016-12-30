package com.giljulio.adorables.ui.screens.closeup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.giljulio.adorables.App;
import com.giljulio.adorables.R;
import com.giljulio.adorables.net.AdorableImageFetcher;
import com.giljulio.adorables.ui.model.Adorable;
import com.giljulio.adorables.utils.ColorUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {


    public static final String KEY_ADORABLE = "adorable";
    @Bind(R.id.appbar) AppBarLayout appBarLayout;
    @Bind(R.id.backdrop) ImageView appBarBackdrop;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.comments_list) RecyclerView recyclerView;
    @Bind(R.id.color_backdrop) View colorBackdrop;

    @Inject AdorableImageFetcher adorableImageFetcher;

    public static Intent createIntent(Context context, Adorable adorable) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_ADORABLE, adorable);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
        ((App) getApplication()).getAppComponent().inject(this);

        Adorable adorable = getIntent().getParcelableExtra(KEY_ADORABLE);

        colorBackdrop.setTransitionName(getString(R.string.transition_background, adorable.getId()));
        appBarBackdrop.setTransitionName(getString(R.string.transition_thumbnail, adorable.getId()));

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(adorable.getName());

        postponeEnterTransition();

        adorableImageFetcher.fetch(adorable.getEmail(), appBarBackdrop)
                .map(ColorUtils::extractColor)
                .subscribe(color -> {
                    colorBackdrop.setBackgroundColor(color);
                    collapsingToolbarLayout.setStatusBarScrimColor(ColorUtils.darker(color, 0.2F));
                    startPostponedEnterTransition();
                });
    }
}
