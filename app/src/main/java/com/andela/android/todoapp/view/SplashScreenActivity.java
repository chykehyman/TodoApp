package com.andela.android.todoapp.view;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.andela.android.todoapp.R;

import static java.lang.Thread.sleep;

public class SplashScreenActivity extends AppCompatActivity {
    private ImageView splashImage;
    private TextView splashText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        splashImage = findViewById(R.id.image_splash);
        splashText = findViewById(R.id.text_splash);

        Animation mAnimation = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        splashImage.startAnimation(mAnimation);
        splashText.startAnimation(mAnimation);

        final Intent mIntent = new Intent(this, MainActivity.class);
        Thread timer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(mIntent);
                    finish();
                }
            }
        });
        timer.start();
    }
}
