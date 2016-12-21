package com.giljulio.adorables.dagger.component;

import com.giljulio.adorables.dagger.module.AppModule;
import com.giljulio.adorables.dagger.module.DataModule;
import com.giljulio.adorables.ui.MainActivityPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {

    void inject(MainActivityPresenter __);
}
