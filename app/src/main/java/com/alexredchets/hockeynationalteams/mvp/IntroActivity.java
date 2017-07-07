package com.alexredchets.hockeynationalteams.mvp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alexredchets.hockeynationalteams.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class IntroActivity extends AppCompatActivity {

    @BindView(R.id.view_pager_intro) protected ViewPager mViewPager;
    @BindView(R.id.layout_dots) protected LinearLayout mDotsLayout;
    @BindView(R.id.button_next) protected Button mNextButton;
    @BindView(R.id.button_skip) protected Button mSkipButton;

    private int[] layouts;
    private TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.i("onCreate");
        super.onCreate(savedInstanceState);

        // Making notification bar transparent
        getWindow()
                .getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        setContentView(R.layout.activity_intro);

        ButterKnife.bind(this);

        layouts = new int[]{
                R.layout.slide_1_intro,
                R.layout.slide_2_intro,
                R.layout.slide_3_intro};

        addBottomDots(0);
        changeStatusBarColor();

        IntroViewPagerAdapter introViewPagerAdapter = new IntroViewPagerAdapter();
        mViewPager.setAdapter(introViewPagerAdapter);
        mViewPager.addOnPageChangeListener(viewPagerPageChangeListener);

    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        mDotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            mDotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private void changeStatusBarColor() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    @OnClick(R.id.button_next)
    public void nextButtonClicked() {
        // checking for last page
        // if last page home screen will be launched
        int current = getItem(+1);
        if (current < layouts.length) {
            // move to next screen
            mViewPager.setCurrentItem(current);
        } else {
            launchHomeScreen();
        }
    }

    @OnClick(R.id.button_skip)
    public void skipButtonClicked(){
        launchHomeScreen();
    }

    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        startActivity(new Intent(IntroActivity.this, LoginActivity.class));
        finish();
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                mNextButton.setText(getString(R.string.start_button));
                mSkipButton.setVisibility(View.GONE);
            } else {
                // still pages are left
                mNextButton.setText(getString(R.string.next_button));
                mSkipButton.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };


    public class IntroViewPagerAdapter extends PagerAdapter {

        private LayoutInflater mLayoutInflater;

        public IntroViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

            View view = mLayoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }


        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
