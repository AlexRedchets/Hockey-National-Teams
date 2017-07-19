package com.alexredchets.hockeynationalteams.mvp.team;

import com.alexredchets.hockeynationalteams.PlayerClient;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import timber.log.Timber;

public class TeamPresenter implements TeamInterface.TeamPresenterInterface{

    private Retrofit mRetrofit;
    private TeamInterface.TeamFragmentInterface mView;

    @Inject
    TeamPresenter(Retrofit mRetrofit, TeamInterface.TeamFragmentInterface mView) {
        this.mRetrofit = mRetrofit;
        this.mView = mView;
    }

    @Override
    public void fetchData() {

        Timber.i("Fetch data started");

        mRetrofit.create(PlayerClient.class).getPlayers("Russia")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(repos -> {
                            Timber.i("Successfully got data");

                            mView.onComplete(repos);
                        },
                        throwable -> {
                            Timber.e(throwable.getMessage());
                            mView.onError(throwable.getMessage());
                        });


    }
}
