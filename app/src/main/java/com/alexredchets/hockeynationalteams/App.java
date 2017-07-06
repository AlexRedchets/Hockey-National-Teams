package com.alexredchets.hockeynationalteams;

import android.app.Application;

import timber.log.Timber;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

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
}
