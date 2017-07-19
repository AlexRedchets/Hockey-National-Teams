package com.alexredchets.hockeynationalteams.injection.components;

import com.alexredchets.hockeynationalteams.injection.modules.CountryModule;
import com.alexredchets.hockeynationalteams.injection.scopes.PerActivity;
import com.alexredchets.hockeynationalteams.mvp.country.CountryFragment;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = CountryModule.class)
public interface CountryComponent {
    CountryFragment inject (CountryFragment fragment);
}
