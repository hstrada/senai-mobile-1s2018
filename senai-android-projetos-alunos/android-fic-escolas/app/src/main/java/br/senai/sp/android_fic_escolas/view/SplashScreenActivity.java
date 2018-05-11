package br.senai.sp.android_fic_escolas.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import br.senai.sp.android_fic_escolas.MainActivity;
import br.senai.sp.android_fic_escolas.R;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        
        carregarSplashScreen();
        
    }

    private void carregarSplashScreen() {

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animacao_splashscreen);
        animation.reset();

        ImageView imageView = findViewById(R.id.logoSplashScreen);
        if (imageView != null) {
            imageView.clearAnimation();
            imageView.startAnimation(animation);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
