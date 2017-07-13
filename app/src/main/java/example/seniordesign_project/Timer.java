package example.seniordesign_project;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by - on 25.1.2017.
 */

public class Timer extends Activity{

    CountDownTimer countDownTimer;

    public Timer(TextView textView)
    {
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/BalooBhai-Regular.ttf");
       // textView.setTypeface(type);
        textView.setTextScaleX(17);

    }

    public void startCountdownTimer(final TextView count_timer){
        countDownTimer = new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                count_timer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {


            }
        }.start();
    }



}
