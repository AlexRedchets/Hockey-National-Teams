package com.alexredchets.hockeynationalteams.injection.components;

import com.alexredchets.hockeynationalteams.injection.modules.AppModule;
import com.alexredchets.hockeynationalteams.injection.modules.CountryModule;
import com.alexredchets.hockeynationalteams.injection.modules.TeamModule;
import com.alexredchets.hockeynationalteams.mvp.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject (SplashActivity activity);
    TeamComponent plus (TeamModule module);
    CountryComponent plus (CountryModule module);
}
