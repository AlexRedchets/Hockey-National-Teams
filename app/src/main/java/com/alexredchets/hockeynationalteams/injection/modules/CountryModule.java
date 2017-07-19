package com.alexredchets.hockeynationalteams.injection.modules;

import com.alexredchets.hockeynationalteams.mvp.country.CountryInterface;

import dagger.Module;
import dagger.Provides;

@Module
public class CountryModule {

    private CountryInterface.CountryFragmentInterface view;

    public CountryModule(CountryInterface.CountryFragmentInterface view) {
        this.view = view;
    }

    @Provides
    CountryInterface.CountryFragmentInterface provideView(){
        return view;
    }
}
