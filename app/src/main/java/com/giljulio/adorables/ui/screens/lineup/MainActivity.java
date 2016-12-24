package com.giljulio.adorables.ui.screens.lineup;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;

import com.giljulio.adorables.App;
import com.giljulio.adorables.R;
import com.giljulio.adorables.dagger.component.AppComponent;
import com.giljulio.adorables.net.model.User;
import com.giljulio.adorables.ui.model.Adorable;
import com.giljulio.adorables.ui.model.AdorableDiffUtilCallback;
import com.giljulio.adorables.net.ImageLoader;
import com.giljulio.adorables.ui.widget.InkPageIndicator;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {

    @Bind(R.id.swipe_to_refresh) SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.card_list) RecyclerView recyclerView;
    @Bind(R.id.indicator) InkPageIndicator inkPageIndicator;

    @Inject ImageLoader imageLoader;

    private MainActivityPresenter mainActivityPresenter;
    private LineUpAdapter lineUpAdapter;
    private PagerSnapHelper pagerSnapHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        AppComponent appComponent = ((App) getApplication()).getAppComponent();
        appComponent.inject(this);

        // Setup presenter
        mainActivityPresenter = new MainActivityPresenter(this);
        appComponent.inject(mainActivityPresenter);

        // Setup recycler view
        lineUpAdapter = new LineUpAdapter(imageLoader);
        recyclerView.setAdapter(lineUpAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerView);


        mainActivityPresenter.bind();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainActivityPresenter.unbind();
    }

    @Override
    public void showAdorables(List<Adorable> adorables) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new AdorableDiffUtilCallback(lineUpAdapter.getItems(), adorables));
        lineUpAdapter.setItems(adorables);
        diffResult.dispatchUpdatesTo(lineUpAdapter);
        /*inkPageIndicator.setRecyclerView(recyclerView, pagerSnapHelper);*/
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
