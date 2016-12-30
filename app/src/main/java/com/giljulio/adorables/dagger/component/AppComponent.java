package com.giljulio.adorables.dagger.component;

import com.giljulio.adorables.dagger.module.AppModule;
import com.giljulio.adorables.dagger.module.DataModule;
import com.giljulio.adorables.ui.screens.closeup.DetailActivity;
import com.giljulio.adorables.ui.screens.lineup.MainActivity;
import com.giljulio.adorables.ui.screens.lineup.MainActivityPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {

    void inject(MainActivity __);

    void inject(MainActivityPresenter __);

    void inject(DetailActivity __);
}
