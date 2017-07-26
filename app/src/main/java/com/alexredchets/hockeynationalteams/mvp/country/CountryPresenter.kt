package com.alexredchets.hockeynationalteams.mvp.country

import com.alexredchets.hockeynationalteams.CountryClient
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

class CountryPresenter @Inject
internal constructor(private val mRetrofit : Retrofit,
                     private val mView : CountryInterface.CountryFragmentInterface)
    : CountryInterface.CountryPresenterInterface {

    override fun fetchData() {
        Timber.i("Fetch data started")
        mRetrofit.create(CountryClient::class.java).country
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe({
                    countries ->
                    Timber.i("Successfully got data")

                    mView.onComplete(countries)

                }
                ){ throwable ->
                    Timber.e(throwable.message)
                    mView.onError(throwable.message)
                }
    }
}
