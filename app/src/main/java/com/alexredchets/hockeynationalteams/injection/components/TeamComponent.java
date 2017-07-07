package com.alexredchets.hockeynationalteams.injection.components;

import com.alexredchets.hockeynationalteams.injection.modules.TeamModule;
import com.alexredchets.hockeynationalteams.injection.scopes.PerActivity;
import com.alexredchets.hockeynationalteams.mvp.TeamFragment;

import dagger.Component;
import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = TeamModule.class)
public interface TeamComponent {
    TeamFragment inject (TeamFragment fragment);
}
