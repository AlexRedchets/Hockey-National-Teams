package com.alexredchets.hockeynationalteams.mvp;

public interface TeamInterface {

    interface TeamFragmentInterface {
        void onComplete();
        void onError();
    }

    interface TeamPresenterInterface {
        void fetchData();
    }

}
