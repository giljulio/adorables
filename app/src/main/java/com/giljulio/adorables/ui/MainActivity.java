package com.giljulio.adorables.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.giljulio.adorables.App;
import com.giljulio.adorables.R;
import com.giljulio.adorables.dagger.component.AppComponent;
import com.giljulio.adorables.model.Adorable;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {

    @Bind(R.id.swipe_to_refresh) SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.card_list) RecyclerView recyclerView;

    private MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        AppComponent appComponent = ((App) getApplication()).getAppComponent();
        mainActivityPresenter = new MainActivityPresenter(this);
        appComponent.inject(mainActivityPresenter);

        mainActivityPresenter.bind();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainActivityPresenter.unbind();
    }

    @Override
    public void showAdorables(List<Adorable> adorable) {

    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError() {
        Snackbar.make(recyclerView, "Unable to fetch the adorables", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", view -> mainActivityPresenter.fetchAdorables())
                .show();
    }
}
