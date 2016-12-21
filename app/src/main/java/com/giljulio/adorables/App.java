package com.giljulio.adorables;

import android.app.Application;

import com.giljulio.adorables.dagger.component.AppComponent;
import com.giljulio.adorables.dagger.component.DaggerAppComponent;
import com.giljulio.adorables.dagger.module.AppModule;
import com.giljulio.adorables.dagger.module.DataModule;
import com.giljulio.adorables.net.FakeApiService;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule(FakeApiService.BASE_URL))
                .build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
