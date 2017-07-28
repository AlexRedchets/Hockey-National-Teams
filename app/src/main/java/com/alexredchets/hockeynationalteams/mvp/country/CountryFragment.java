package com.alexredchets.hockeynationalteams.mvp.country;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alexredchets.hockeynationalteams.App;
import com.alexredchets.hockeynationalteams.R;
import com.alexredchets.hockeynationalteams.adapter.CountryAdapter;
import com.alexredchets.hockeynationalteams.model.Country;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class CountryFragment extends Fragment implements CountryInterface.CountryFragmentInterface, CountryAdapter.ClickListener {

    @BindView(R.id.recView_country) protected RecyclerView mRecyclerView;
    @Inject CountryPresenter mPresenter;

    private CountryAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Timber.i("onCreate");
        super.onCreate(savedInstanceState);

        ((App)getActivity()
                .getApplication())
                .provideCountryComponent(this)
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country, container, false);

        ButterKnife.bind(this, view);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.addItemDecoration(new CountryFragment.GridSpacingItemDecoration(1, dpToPx(10), true));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new CountryAdapter(getContext(), this) ;
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.fetchData();
    }

    @Override
    public void onComplete(List<Country> countryList) {
        mAdapter.updateAdapter(countryList);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((App)getActivity()
                .getApplication())
                .releaseCountryComponent();
    }

    @Override
    public void onClick(Country country) {
        Toast.makeText(getContext(), country.getName(), Toast.LENGTH_SHORT).show();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
