package com.alexredchets.hockeynationalteams.mvp.team

import com.alexredchets.hockeynationalteams.PlayerClient

import javax.inject.Inject

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import timber.log.Timber

class TeamPresenter @Inject
internal constructor(private val mRetrofit: Retrofit,
                     private val mView: TeamInterface.TeamFragmentInterface)
    : TeamInterface.TeamPresenterInterface {

    override fun fetchData() {

        Timber.i("Fetch data started")

        mRetrofit.create(PlayerClient::class.java).getPlayers("Russia")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe({ repos ->
                    Timber.i("Successfully got data")

                    mView.onComplete(repos)
                }
                ) { throwable ->
                    Timber.e(throwable.message)
                    mView.onError(throwable.message)
                }


    }
}
