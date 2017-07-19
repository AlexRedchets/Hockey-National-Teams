package com.alexredchets.hockeynationalteams.mvp.country;

import com.alexredchets.hockeynationalteams.model.Country;

import java.util.List;

public interface CountryInterface {

    interface CountryFragmentInterface {
        void onComplete(List<Country> countryList);
        void onError(String message);
    }

    interface CountryPresenterInterface {
        void fetchData();
    }


}
