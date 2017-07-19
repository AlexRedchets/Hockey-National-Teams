package com.alexredchets.hockeynationalteams.injection.modules;

import com.alexredchets.hockeynationalteams.injection.scopes.PerActivity;
import com.alexredchets.hockeynationalteams.mvp.team.TeamInterface;

import dagger.Module;
import dagger.Provides;

@Module
public class TeamModule {

    private TeamInterface.TeamFragmentInterface mView;

    public TeamModule(TeamInterface.TeamFragmentInterface mView) {
        this.mView = mView;
    }

    @PerActivity
    @Provides
    TeamInterface.TeamFragmentInterface provideView(){
        return mView;
    }
}
