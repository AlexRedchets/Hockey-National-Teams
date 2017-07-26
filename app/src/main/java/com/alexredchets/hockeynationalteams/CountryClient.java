package com.alexredchets.hockeynationalteams;

import com.alexredchets.hockeynationalteams.model.Country;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CountryClient {

    @GET("/api/country")
    Observable<List<Country>> getCountry();
}
