package com.alexredchets.hockeynationalteams.injection.components;

import com.alexredchets.hockeynationalteams.injection.modules.AppModule;
import com.alexredchets.hockeynationalteams.injection.modules.TeamModule;
import com.alexredchets.hockeynationalteams.mvp.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    TeamComponent plus (TeamModule module);
    void inject (SplashActivity activity);
}
