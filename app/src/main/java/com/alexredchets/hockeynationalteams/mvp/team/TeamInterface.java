package com.alexredchets.hockeynationalteams.mvp.team;

import com.alexredchets.hockeynationalteams.model.Player;

import java.util.List;

public interface TeamInterface {

    interface TeamFragmentInterface {
        void onComplete(List<Player> playerList);
        void onError(String message);
    }

    interface TeamPresenterInterface {
        void fetchData();
    }

}
