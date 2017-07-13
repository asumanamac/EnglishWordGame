package example.seniordesign_project;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView imageView=(ImageView) findViewById(R.id.imageView2);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_animation);
        imageView.setAnimation(animation);

        mp = MediaPlayer.create(getApplicationContext(), R.raw.fairy);
        mp.start();



        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                finish();
                mp.stop();
                Intent intent =new Intent(new Intent(getApplicationContext(),MainActivity.class));
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                /*ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this);
                Intent intent =new Intent(new Intent(getApplicationContext(),TransitionActivity.class));
                intent.putExtra(Constants.KEY_ANIM_TYPE, Constants.TransitionType.SlideXML);
                intent.putExtra(Constants.KEY_TITLE,"English Word Game");
                startActivity(intent,options.toBundle());**/



            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
}





