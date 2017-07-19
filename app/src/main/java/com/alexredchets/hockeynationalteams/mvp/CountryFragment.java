package com.alexredchets.hockeynationalteams.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexredchets.hockeynationalteams.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryFragment extends Fragment {

    @BindView(R.id.recView_country) protected RecyclerView mRecyclerView;
    @Inject CountryPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country, container, false);

        ButterKnife.bind(this, view);
        return view;
    }
}
