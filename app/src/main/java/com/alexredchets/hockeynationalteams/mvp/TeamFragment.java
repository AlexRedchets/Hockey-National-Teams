package com.alexredchets.hockeynationalteams.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexredchets.hockeynationalteams.App;
import com.alexredchets.hockeynationalteams.R;

public class TeamFragment extends Fragment implements TeamInterface.TeamFragmentInterface {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((App)getActivity()
                .getApplication())
                .provideTeamComponent(this)
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_team, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((App)getActivity()
                .getApplicationContext())
                .releaseTeamComponent();
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError() {

    }
}
