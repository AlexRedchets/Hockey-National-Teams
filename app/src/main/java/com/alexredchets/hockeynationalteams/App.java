package com.alexredchets.hockeynationalteams;

import android.app.Application;

import com.alexredchets.hockeynationalteams.injection.components.AppComponent;
import com.alexredchets.hockeynationalteams.injection.components.CountryComponent;
import com.alexredchets.hockeynationalteams.injection.components.DaggerAppComponent;
import com.alexredchets.hockeynationalteams.injection.components.TeamComponent;
import com.alexredchets.hockeynationalteams.injection.modules.AppModule;
import com.alexredchets.hockeynationalteams.injection.modules.CountryModule;
import com.alexredchets.hockeynationalteams.injection.modules.TeamModule;
import com.alexredchets.hockeynationalteams.mvp.country.CountryInterface;
import com.alexredchets.hockeynationalteams.mvp.team.TeamInterface;

import timber.log.Timber;

public class App extends Application {

    private AppComponent mAppComponent;
    private TeamComponent mTeamComponent;
    private CountryComponent mCountryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initConfig();

        provideAppComponent();
    }

    private void initConfig() {
        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree(){
                //Add the line number to the tag
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) + ": Line " + element.getLineNumber();
                }
            });
        }
        else {
            //Release mode
            //Fabric.with(this, new Crashlytics());
            Timber.plant(new ReleaseTree());
        }
    }

    public AppComponent provideAppComponent() {

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this,
                        "http://104.131.37.13:8080/"))
                .build();
        return mAppComponent;

    }

    public void releaseAppComponent(){
        mAppComponent = null;
    }

    public TeamComponent provideTeamComponent(TeamInterface.TeamFragmentInterface view){
        if (mAppComponent == null){
            provideAppComponent();
        }
        mTeamComponent = mAppComponent.plus(new TeamModule(view));
        return mTeamComponent;
    }

    public void releaseTeamComponent(){
        mTeamComponent = null;
    }

    public CountryComponent provideCountryComponent(CountryInterface.CountryFragmentInterface view){
        if (mAppComponent == null){
            provideAppComponent();
        }
        mCountryComponent = mAppComponent.plus(new CountryModule(view));
        return mCountryComponent;
    }

    public void releaseCountryComponent(){
        mCountryComponent = null;
    }

}
