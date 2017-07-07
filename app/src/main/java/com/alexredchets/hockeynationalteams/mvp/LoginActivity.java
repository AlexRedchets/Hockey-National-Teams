package com.alexredchets.hockeynationalteams.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alexredchets.hockeynationalteams.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.i("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_login_sign_in)
    public void signInClicked(View view) {
        Intent i = new Intent(this, IntroActivity.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.button_login_sign_up)
    public void signUpClicked(View view) {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }
}
