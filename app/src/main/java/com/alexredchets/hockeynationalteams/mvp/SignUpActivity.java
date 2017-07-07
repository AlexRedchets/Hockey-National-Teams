package com.alexredchets.hockeynationalteams.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alexredchets.hockeynationalteams.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_signup_sign_up)
    public void signUpClicked(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
