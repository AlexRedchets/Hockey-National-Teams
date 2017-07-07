package com.alexredchets.hockeynationalteams.mvp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alexredchets.hockeynationalteams.App;
import com.alexredchets.hockeynationalteams.R;

import timber.log.Timber;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.i("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ((App)getApplication()).provideAppComponent().inject(this);

        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashActivity.this,
                    IntroActivity.class);
            startActivity(i);
            finish();
        }, 3000);
    }
}
