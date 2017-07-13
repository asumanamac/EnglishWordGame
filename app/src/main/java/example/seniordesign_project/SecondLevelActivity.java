package example.seniordesign_project;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static example.seniordesign_project.Animationss.game_over_slide;
import static example.seniordesign_project.Animationss.game_over_sound;
import static example.seniordesign_project.Animationss.back;
import static example.seniordesign_project.Animationss.success_background_sound;
import static example.seniordesign_project.Animationss.sucess;
import static example.seniordesign_project.Animationss.fail;
import static example.seniordesign_project.Animationss.buttonSound;


public class SecondLevelActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    ImageView random_image;
    private static String url = "http://192.168.2.11:8080/android_login_api/images/getAllImages_music.php";
    String URL_POST= "http://192.168.2.11:8080/android_login_api/score.php";
    PostClass post = new PostClass();
    TextView word1, word2, word3, word4, word5, word6,word7,word8,word9,word10,word11,word12, name1, name2, name3, name4, name6, drag_word;
    FloatingActionButton setting, volume, out, home;
    Animation open, close, clockwise, anticlokwise, middle, image_anim;
    ArrayList<HashMap<String, String>> contactList, nameList;
    public static CountDownTimer countDownTimer;
    public static int count = 3, correct = 0, wrong = 0;
    public static TextView total_score_result, total_Score_text, wrong_text, wrong_result, correct_text, correct_result;
    public static TextView high_score_result, high_score_text;
    public static TextView level_text, level_result;
    public static ImageButton play_button, cancel_button, continue_button,arrowButton;
    public static TextView timerText, scoreSucess, scoreSucResult, helpText;
    ImageView imageView1, imageView2, imageView3, imageView4, right1, right2, right3;
    public static TextView scoreText;
    boolean match1 = false, match2 = false, match3 = false, match4 = false, match5 = false, match6 = false;
    public static boolean isPaused = false,isTrue=false ,isClick=false,game_over=false,game_success=false;
    public static boolean isCanceled = false;
    public long timeRemaining = 0;
    Dialog game_over_dialog,success_screen_dialog,information_box;
    Score score;
    public static String level;
    private static final String PREFS_LAST_IMG = "prefs_last_img";
    ArrayList second_list;
    private SharedPreferences mPreferences;
    Typeface tf1;
    ImageLoader imageLoader;
    RelativeLayout relative1;
    static boolean isGameOver=false,isLevel2=false;
    ArrayList<HashMap<String, String>> score_list;

    String url_high_score= "http://192.168.2.11:8080/android_login_api/highscore.php";;
    private static String vegetables_url="http://192.168.2.11:8080/android_login_api/vegetables_images/getAllImages_vegetables.php";
    private static String foods_url="http://192.168.2.11:8080/android_login_api/foods_images/getAllImages_foods.php";
    private static String clothes_url="http://192.168.2.11:8080/android_login_api/clothes_images/getAllImages_clothes.php";
    private static String hobbies_url="http://192.168.2.11:8080/android_login_api/hobbies_images/getAllImages_hobbies.php";
    private static String fruits_url="http://192.168.2.11:8080/android_login_api/fruits_images/getAllImages_fruits.php";
    private static String sports_url="http://192.168.2.11:8080/android_login_api/sports_images/getAllImages_sports.php";
    private static String colors_url="http://192.168.2.11:8080/android_login_api/colors_images/getAllImages_colors.php";
    private static String family_url="http://192.168.2.11:8080/android_login_api/family_images/getAllImages_families.php";
    private static String shapes_url="http://192.168.2.11:8080/android_login_api/shapes_images/getAllImages_shapes.php";
    private static String vehicles_url="http://192.168.2.11:8080/android_login_api/vehicles_images/getAllImages_vehicles.php";
    private static String places_url="http://192.168.2.11:8080/android_login_api/places_images/getAllImages_places.php";
    private static String animals_url="http://192.168.2.11:8080/android_login_api/animals_images/getAllImages_animals.php";
    private static String adjectives_url="http://192.168.2.11:8080/android_login_api/adjectives_images/getAllImages_adjectives.php";
    private static String numbers_url="http://192.168.2.11:8080/android_login_api/numbers_images/getAllImages_numbers.php";
    private static String countries_url="http://192.168.2.11:8080/android_login_api/countries_images/getAllImages_countries.php";
    private static String weathers_url="http://192.168.2.11:8080/android_login_api/weather_images/getAllImages_weathers.php";
    private static String jobs_url="http://192.168.2.11:8080/android_login_api/jobs_images/getAllImages_jobs.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_level);

        isLevel2=true;
        word1 = (TextView) findViewById(R.id.text_word1);
        word2 = (TextView) findViewById(R.id.text_word2);
        word3 = (TextView) findViewById(R.id.text_word3);
        word4 = (TextView) findViewById(R.id.text_word4);
        word5 = (TextView) findViewById(R.id.text_word5);
        word6 = (TextView) findViewById(R.id.text_word6);
        drag_word = (TextView) findViewById(R.id.text_name1);


        scoreText = (TextView) findViewById(R.id.score_text);
        timerText = (TextView) findViewById(R.id.timer_text);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/sky.ttf");
        scoreText.setTextColor(Color.rgb(125, 60, 152));
        scoreText.setTypeface(type);
        scoreText.setTextSize(25);


        random_image = (ImageView) findViewById(R.id.random_image);

        setting = (FloatingActionButton) findViewById(R.id.floatmain);
        volume = (FloatingActionButton) findViewById(R.id.volumeon);
        out = (FloatingActionButton) findViewById(R.id.out);
        home = (FloatingActionButton) findViewById(R.id.home);
        right1 =(ImageView) findViewById(R.id.heart3);
        right2 =(ImageView) findViewById(R.id.heart2);
        right3 =(ImageView) findViewById(R.id.heart1);
        arrowButton = (ImageButton) findViewById(R.id.righarrow_button);

        open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clokwise);
        anticlokwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);
        middle = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_middle);
        image_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_animation);
        middle.setInterpolator((new AccelerateDecelerateInterpolator()));
        middle.setFillAfter(true);
        setting.setOnClickListener(new SecondLevelActivity.FloatButtonAction());

        tf1 = Typeface.createFromAsset(getAssets(), "fonts/BalooBhai-Regular.ttf");

        contactList = new ArrayList<>();
        nameList = new ArrayList<>();
        second_list = new ArrayList();
        Animationss anim=new Animationss(getApplicationContext());

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);

        new GetContacts().execute();

        drag_word.setOnDragListener(new ChoiceDragListener());
        word1.setOnTouchListener(new ChoiceTouchListener());
        word2.setOnTouchListener(new ChoiceTouchListener());
        word3.setOnTouchListener(new ChoiceTouchListener());
        word4.setOnTouchListener(new ChoiceTouchListener());
        word5.setOnTouchListener(new ChoiceTouchListener());
        word6.setOnTouchListener(new ChoiceTouchListener());


        success_screen_dialog=new Dialog(SecondLevelActivity.this);
        information_box = new Dialog(SecondLevelActivity.this);
        scoreText.setText(""+Score.resetScore());

    }
    public void startCountdownTimer(final TextView count_timer) {
        countDownTimer = new CountDownTimer(35000, 1000) {

            public void onTick(long millisUntilFinished) {

                if(isPaused==true || isCanceled==true)
                {
                    //If the user request to cancel or paused the
                    //CountDownTimer we will cancel the current instance
                    cancel();
                }
                else {
                    count_timer.setText("" + millisUntilFinished / 1000);
                    timeRemaining = millisUntilFinished;
                }
            }

            public void onFinish() {
                back.stop();
                gameOver();

            }
        }.start();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if (game_over_dialog!=null && game_over_dialog.isShowing() ){
            game_over_dialog.cancel();
        }
    }

    public void gameOver()
    {
        if(back.isPlaying())
        {
            back.stop();
        }
        LevelScreen.isLevel2=true;


      countDownTimer.cancel();

        game_over_dialog=new Dialog(SecondLevelActivity.this);

        game_over_dialog.setContentView(R.layout.activity_game_over);

        total_score_result = (TextView) game_over_dialog.findViewById(R.id.total_score_result);
        total_Score_text = (TextView) game_over_dialog.findViewById(R.id.total_score_text);

        high_score_result =(TextView) game_over_dialog.findViewById(R.id.high_score_result);
        high_score_text = (TextView) game_over_dialog.findViewById(R.id.high_score_text);

        level_result =(TextView) game_over_dialog.findViewById(R.id.level_result);
        level_text=(TextView) game_over_dialog.findViewById(R.id.level_text);

        play_button = (ImageButton) game_over_dialog.findViewById(R.id.play_button);
        cancel_button = (ImageButton) game_over_dialog.findViewById(R.id.cancel_button);

        level="2";

        total_score_result.setTextSize(15);
        total_Score_text.setTextSize(15);
        total_score_result.setTypeface(tf1);
        total_Score_text.setTypeface(tf1);
        total_score_result.setText(scoreText.getText().toString());
        total_score_result.setTextColor(Color.rgb(125, 60, 152));
        total_Score_text.setTextColor(Color.rgb(125, 60, 152));

        level_result.setTextSize(15);
        level_text.setTextSize(15);
        level_result.setTypeface(tf1);
        level_text.setTypeface(tf1);
        level_result.setText(level);
        level_result.setTextColor(Color.rgb(125, 60, 152));
        level_text.setTextColor(Color.rgb(125, 60, 152));


        high_score_result.setTextSize(15);
        high_score_text.setTextSize(15);
        high_score_result.setTypeface(tf1);
        high_score_text.setTypeface(tf1);
        high_score_result.setTextColor(Color.rgb(125, 60, 152));
        high_score_text.setTextColor(Color.rgb(125, 60, 152));
        new GetHighScore().execute();
        game_over_slide.start();
        game_over_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        game_over_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        game_over_dialog.setCanceledOnTouchOutside(false);
        game_over_dialog.setCancelable(false);
        game_over_sound.start();
        game_over_dialog.show();
        new saveScore().execute();


        play_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        //overlay is black with transparency of 0x77 (119)
                        view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        //clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(game_over_sound.isPlaying())
                    game_over_sound.stop();
                buttonSound.start();
                scoreText.setText(""+Score.resetScore());
                Intent i = new Intent(SecondLevelActivity.this, LevelScreen.class);
                startActivity(i);
                count=3;
                correct=0;
                wrong=0;

                finish();

                //PlayScreen.isGameOver=true;
                //PlayScreen.isGameOver=true;
                ThirdLevelActivity.isGameOver=false;
               PlayScreen.isGameOver=false;
                isGameOver=true;

            }
        });

        cancel_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        //overlay is black with transparency of 0x77 (119)
                        view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        //clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(game_over_sound.isPlaying())
                    game_over_sound.stop();
                buttonSound.start();
                Intent mStartActivity = new Intent(SecondLevelActivity.this, MainActivity.class);
                startActivity(mStartActivity);
                timerText.setText("");
                scoreText.setText(""+Score.resetScore());
                back.stop();
                wrong=0;
                correct=0;
                Fragment mFragment = new SampleFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_second_level, mFragment).commit();
                game_over_dialog.dismiss();
                //scoreText.setText(""+Score.resetScore());


            }
        });



    }

    public void successScreen()
    {

        if(back.isPlaying())
        {
            back.stop();
        }

        countDownTimer.cancel();
        game_success=true;
        game_success=true;

        success_screen_dialog.setContentView(R.layout.activity_success_screen);
    level=LevelScreen.level;


        wrong_text=(TextView) success_screen_dialog.findViewById(R.id.wrong_text);
        wrong_result=(TextView) success_screen_dialog.findViewById(R.id.wrong_result);
        correct_text=(TextView) success_screen_dialog.findViewById(R.id.correct_text);
        correct_result=(TextView) success_screen_dialog.findViewById(R.id.correct_result);
        scoreSucess=(TextView) success_screen_dialog.findViewById(R.id.scoreSucText);
        scoreSucResult=(TextView) success_screen_dialog.findViewById(R.id.scoreSucResult);

        continue_button =(ImageButton) success_screen_dialog.findViewById(R.id.continue_button);
        cancel_button =(ImageButton) success_screen_dialog.findViewById(R.id.cancel_button);

        wrong_result.setTextSize(15);
        wrong_result.setTypeface(tf1);
        wrong_text.setTypeface(tf1);
        wrong_text.setTextSize(15);
        correct_result.setTextSize(15);
        correct_result.setTypeface(tf1);
        correct_text.setTypeface(tf1);
        correct_text.setTextSize(15);
        scoreSucess.setTypeface(tf1);
        scoreSucess.setTextSize(15);
        scoreSucResult.setTextSize(15);
        scoreSucResult.setTypeface(tf1);

        correct_result.setText(correct+"");
        wrong_result.setText(wrong +"");
        scoreSucResult.setText(scoreText.getText().toString());

        game_over_slide.start();
        success_screen_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        success_screen_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        success_screen_dialog.setCanceledOnTouchOutside(false);
        success_screen_dialog.setCancelable(false);
        success_screen_dialog.show();
        success_background_sound.start();
        new saveScore().execute();

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(success_background_sound.isPlaying())
                    success_background_sound.stop();
                buttonSound.start();
                isClick=true;
                Intent intent=new Intent(SecondLevelActivity.this, LevelScreen.class);
                startActivity(intent);
                scoreText.setText(""+Score.resetScore());
                finish();
               ThirdLevelActivity.isGameOver=false;
                count=3;
                correct=0;
                wrong=0;



            }
        });

        continue_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        //overlay is black with transparency of 0x77 (119)
                        view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        //clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(success_background_sound.isPlaying())
                    success_background_sound.stop();
                buttonSound.start();
                timerText.setText("");
                scoreText.setText(""+Score.resetScore());
                LevelScreen.level2.setImageResource(R.drawable.level2_success);
                back.stop();
                wrong=0;
                correct=0;

                Fragment mFragment = new SampleFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_second_level, mFragment).commit();
                success_screen_dialog.dismiss();


            }
        });

        cancel_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        //overlay is black with transparency of 0x77 (119)
                        view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        //clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return false;
            }
        });



    }

    public void clickVoice(View view) {
        buttonSound.start();
        if (back.isPlaying()) {
            back.pause();
            volume.setImageResource(R.drawable.volumeoff2);
        } else {

            back.start();
            volume.setImageResource(R.drawable.volumeon2);
        }
    }

    public void clickOut(View view) {
        buttonSound.start();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure to exit the game?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                System.exit(0);
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void clickHome(View view) {
        buttonSound.start();
        information_box.setContentView(R.layout.activity_informationbox);

        helpText = (TextView) information_box.findViewById(R.id.help_text);
        helpText.setTextSize(15);
        helpText.setTypeface(tf1);
        helpText.setTextColor(Color.rgb(125, 60, 152));

        game_over_slide.start();
        information_box.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        information_box.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        information_box.setCanceledOnTouchOutside(false);
        information_box.setCancelable(false);
        information_box.show();
        isPaused = true;

        new Handler().postDelayed(new Runnable(){
            public void run() {
                information_box.dismiss();
                isPaused = false;
                isCanceled = false;

                isPaused = false;
                isCanceled = false;

                //Initialize a new CountDownTimer instance
                long millisInFuture = timeRemaining;
                long countDownInterval = 1000;

                new CountDownTimer(millisInFuture, countDownInterval) {
                    public void onTick(long millisUntilFinished) {
                        if(isPaused || isCanceled)
                        {
                            //If user requested to pause or cancel the count down timer
                            cancel();
                        }
                        else {
                            timerText.setText("" + millisUntilFinished / 1000);
                            //Put count down timer remaining time in a variable
                            timeRemaining = millisUntilFinished;
                        }


                    }

                    @Override
                    public void onFinish() {
                        back.stop();
                        gameOver();

                    }
                }.start();
            }

        },2500);




    }

    private class FloatButtonAction implements View.OnClickListener {
        boolean isOpen = false;
        @Override
        public void onClick(View view) {


            if (isOpen) {
                buttonSound.start();
                home.startAnimation(close);
                out.startAnimation(close);
                volume.startAnimation(close);
                setting.startAnimation(anticlokwise);
                home.setClickable(false);
                out.setClickable(false);
                volume.setClickable(false);
                isOpen=false;



            }

            if(buttonSound.isPlaying())
            {
                buttonSound.stop();
            }
            else {
                buttonSound.start();
                home.startAnimation(open);
                out.startAnimation(open);
                volume.startAnimation(open);
                setting.startAnimation(open);
                home.setClickable(true);
                out.setClickable(true);
                volume.setClickable(true);
                isOpen=true;

            }

            volume.setVisibility(View.INVISIBLE);
            home.setVisibility(View.INVISIBLE);
            out.setVisibility(View.INVISIBLE);

        }
    }



    private final class ChoiceTouchListener implements View.OnTouchListener {


        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);

                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void onBackPressed() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure to exit the game?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                back.stop();
                dialog.dismiss();
                Intent mStartActivity = new Intent(SecondLevelActivity.this, MainActivity.class);
                int mPendingIntentId = 123456;
                PendingIntent mPendingIntent = PendingIntent.getActivity(SecondLevelActivity.this, mPendingIntentId, mStartActivity,
                        PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager) SecondLevelActivity.this.getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                System.exit(0);
                scoreText.setText(""+Score.resetScore());

            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }

    private class ChoiceDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:

                    break;
                case DragEvent.ACTION_DRAG_ENTERED:

                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    TextView dropTarget = (TextView) v;

                    if (event.getLocalState() == word1) {
                        if (dropTarget == drag_word &&
                                dropTarget.getText().toString().equals(word1.getText().toString())) {

                            sucess.start();
                            word1.setVisibility(View.INVISIBLE);
                            random_image.setImageResource(R.drawable.purple);
                            word1.clearAnimation();
                            word1.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word1.clearAnimation();
                            drag_word.setTextColor(Color.rgb(125, 60, 152));
                            drag_word.setText("");
                            drag_word.append(word1.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            //dropTarget.setOnDragListener(null);
                            correct++;
                            match1 = true;

                            arrowButton.setOnClickListener(new ButtonListener());
                            arrowButton.setOnTouchListener(new TouchListener());

                        }
                        else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();
                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match1 = false;
                            arrowButton.setOnClickListener(null);
                            arrowButton.setOnTouchListener(null);


                            if (count == 1) {
                                right2.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right1.setVisibility(View.INVISIBLE);

                            }


                            if (count == 0 || timerText.getText().equals(0 + "")) {
                                right3.setVisibility(View.INVISIBLE);
                                 gameOver();

                            }

                        }
                        if (fail.isPlaying()) {
                            buttonSound.stop();
                        }


                    }
                    if (event.getLocalState() == word2) {
                        if (dropTarget == drag_word &&
                                dropTarget.getText().toString().equals(word2.getText().toString())) {

                            sucess.start();
                            random_image.setImageResource(R.drawable.purple);
                            random_image.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            drag_word.clearAnimation();
                            word2.clearAnimation();
                            word2.setVisibility(View.INVISIBLE);
                            drag_word.setTextColor(Color.rgb(125, 60, 152));
                            drag_word.setText("");
                            drag_word.append(word2.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                           // dropTarget.setOnDragListener(null);
                            correct++;
                            match2 = true;
                            arrowButton.setOnClickListener(null);
                            arrowButton.setOnTouchListener(null);
                            if(match2==true) {
                                arrowButton.setOnClickListener(new ButtonListener());
                                arrowButton.setOnTouchListener(new TouchListener());

                            }



                        }
                        else {

                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match2 = false;
                            arrowButton.setOnClickListener(null);
                            arrowButton.setOnTouchListener(null);

                            if (count == 1) {
                                right2.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right1.setVisibility(View.INVISIBLE);

                            }

                            if (count == 0 || timerText.getText().equals(0 + "")) {
                                right3.setVisibility(View.INVISIBLE);
                                gameOver();
                            }
                        }

                        if (fail.isPlaying()) {
                            buttonSound.stop();
                        }


                    }
                    if (event.getLocalState() == word3) {
                        if (dropTarget == drag_word &&
                                dropTarget.getText().toString().equals(word3.getText().toString())) {

                            sucess.start();
                            random_image.setImageResource(R.drawable.purple);
                            word3.clearAnimation();
                            random_image.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            drag_word.clearAnimation();
                            word3.setVisibility(View.INVISIBLE);
                            drag_word.setTextColor(Color.rgb(125, 60, 152));
                            drag_word.setText("");
                            drag_word.append(word3.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            //dropTarget.setOnDragListener(null);
                            correct++;
                            match3 = true;
                            arrowButton.setOnClickListener(null);
                            arrowButton.setOnTouchListener(null);
                            if(match3==true) {
                                arrowButton.setOnClickListener(new ButtonListener());
                                arrowButton.setOnTouchListener(new TouchListener());

                            }


                        }
                        else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();
                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match3 = false;
                            arrowButton.setOnClickListener(null);
                            arrowButton.setOnTouchListener(null);

                            if (count == 1) {
                                right2.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 0 || timerText.getText().equals(0 + "")) {
                                right3.setVisibility(View.INVISIBLE);
                                 gameOver();
                            }
                        }
                        if (fail.isPlaying()) {
                            buttonSound.stop();
                        }

                    }

                    if (event.getLocalState() == word4) {
                        if (dropTarget == drag_word &&
                                dropTarget.getText().toString().equals(word4.getText().toString())) {

                            sucess.start();
                            random_image.setImageResource(R.drawable.purple);
                            word4.clearAnimation();
                            random_image.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            drag_word.clearAnimation();
                            word4.setVisibility(View.INVISIBLE);
                            drag_word.setTextColor(Color.rgb(125, 60, 152));
                            drag_word.setText("");
                            drag_word.append(word4.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            //dropTarget.setOnDragListener(null);
                            correct++;
                            match4 = true;
                            arrowButton.setOnClickListener(null);
                            arrowButton.setOnTouchListener(null);
                            if(match4==true) {
                                arrowButton.setOnClickListener(new ButtonListener());
                                arrowButton.setOnTouchListener(new TouchListener());

                            }




                        }
                        else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match4 = false;
                            arrowButton.setOnClickListener(null);
                            arrowButton.setOnTouchListener(null);

                            if (count == 1) {
                                right2.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right1.setVisibility(View.INVISIBLE);

                            }

                            if (count == 0 || timerText.getText().equals(0 + "")) {
                                right3.setVisibility(View.INVISIBLE);
                                gameOver();
                            }
                        }
                    }

                    if (event.getLocalState() == word5) {
                        if (dropTarget == drag_word &&
                                dropTarget.getText().toString().equals(word5.getText().toString())) {

                            sucess.start();
                            random_image.setImageResource(R.drawable.purple);
                            word5.clearAnimation();
                            random_image.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            drag_word.clearAnimation();
                            word5.setVisibility(View.INVISIBLE);
                            drag_word.setTextColor(Color.rgb(125, 60, 152));
                            drag_word.setText("");
                            drag_word.append(word5.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                           // dropTarget.setOnDragListener(null);
                            correct++;
                            match5 = true;
                            arrowButton.setOnClickListener(null);
                            arrowButton.setOnTouchListener(null);
                            if(match5==true) {
                                arrowButton.setOnClickListener(new ButtonListener());
                                arrowButton.setOnTouchListener(new TouchListener());

                            }


                        }
                        else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match5 = false;
                            arrowButton.setOnClickListener(null);
                            arrowButton.setOnTouchListener(null);

                            if (count == 1) {
                                right2.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right1.setVisibility(View.INVISIBLE);

                            }

                            if (count == 0 || timerText.getText().equals(0 + "")) {
                                right3.setVisibility(View.INVISIBLE);
                                gameOver();
                            }
                        }
                    }

                    if (event.getLocalState() == word6) {
                        if (dropTarget == drag_word &&
                                dropTarget.getText().toString().equals(word6.getText().toString())) {

                            sucess.start();
                            random_image.setImageResource(R.drawable.purple);
                            word6.clearAnimation();
                            drag_word.clearAnimation();
                            word6.setVisibility(View.INVISIBLE);
                            drag_word.setTextColor(Color.rgb(125, 60, 152));
                            drag_word.setText("");
                            drag_word.append(word6.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                          //  dropTarget.setOnDragListener(null);
                            correct++;
                            arrowButton.setOnClickListener(null);
                            arrowButton.setOnTouchListener(null);
                            match6=true;

                            if(match6==true) {
                                arrowButton.setOnClickListener(new ButtonListener());
                                arrowButton.setOnTouchListener(new TouchListener());

                            }

                        }


                        else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match6 = false;
                            arrowButton.setOnClickListener(null);
                            arrowButton.setOnTouchListener(null);

                            if (count == 1) {
                                right2.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right1.setVisibility(View.INVISIBLE);

                            }

                            if (count == 0 || timerText.getText().equals(0 + "")) {
                                right3.setVisibility(View.INVISIBLE);
                                gameOver();
                            }


                        }
                    }

                    if (match1 == true && match2 == true && match3 == true && match4 == true && match5 == true && match6 == true && count > 0 &&
                            Integer.valueOf(timerText.getText().toString()) > 0) {
                       successScreen();
                    }


                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                default:
                    break;
            }
            return true;
        }
    }



    class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {



            ArrayList<String> coming_datas = new ArrayList<String>();
            int counts=0;
            boolean enter=false;
            arrowButton.setOnClickListener(null);
            arrowButton.setOnTouchListener(null);

          for (int i = 0; i < contactList.size(); i++) {
                Log.d(" Alll ArrayLst elements are: ", contactList.get(i).toString());

            }


           Log.d("ARAYLIST ELEMNTS SIZE",String.valueOf(contactList.size()));

            for(int i=0; i<contactList.size() ; i++)
            {

                    drag_word.setText(contactList.get(0).get("word"));
                    drag_word.setTypeface(tf1);
                    drag_word.setTextColor(Color.TRANSPARENT);

                    imageLoader.displayImage(contactList.get(0).get("image_path"), random_image);
                    //drag_word.setTextColor(Color.TRANSPARENT);
                    enter=true;
                    Log.d("REMOVING ELEMNTS ", contactList.get(0).get("image_path").toString());
                    counts++;


            }

            if(counts>0)
            {
                contactList.remove(0);
                Collections.shuffle(contactList);
            }
        }

    }

    class TouchListener implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    ImageView view = (ImageView) v;
                    //overlay is black with transparency of 0x77 (119)
                    view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                    view.invalidate();
                    break;
                }
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL: {
                    ImageView view = (ImageView) v;
                    //clear the overlay
                    view.getDrawable().clearColorFilter();
                    view.invalidate();
                    break;
                }
            }
            return false;
        }
    }


            class GetContacts extends AsyncTask<Void, Void, Void> {
                private int progressStatus = 0;
                private Handler handler = new Handler();

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    // Showing progress dialog
                    pDialog = new ProgressDialog(SecondLevelActivity.this);
                    pDialog.setMessage("Loading...");
                    pDialog.setIndeterminate(true);
                    pDialog.setCancelable(false);
                    pDialog.show();
                }


                @Override
                protected Void doInBackground(Void... arg0) {
                    HttpHandler sh = new HttpHandler();
                    if (SampleFragment.music_category== true) {
                        String jsonStr = sh.makeServiceCall(url);


                    Log.e(TAG, "Response from url: " + jsonStr);

                    if (jsonStr != null) {
                        try {
                            JSONObject jsonObj = new JSONObject(jsonStr);

                            // Getting JSON Array node
                            JSONArray contacts = jsonObj.getJSONArray("result");
                            int resultSize = contacts.length();

                            RandomPermutation rp = new RandomPermutation(0, resultSize - 1);


                            for (int i = 0; i < 6; i++) {
                                JSONObject c1 = contacts.getJSONObject(rp.GetRandom());
                                String name = c1.getString("name");
                                String image_path = c1.getString("image_path");
                                String word = c1.getString("word");
                                HashMap<String, String> contact = new HashMap<>();
                                HashMap<String, String> name_map = new HashMap<>();
                                contact.put("name", name);
                                contact.put("image_path", image_path);
                                contact.put("word", word);

                                contactList.add(contact);
                                second_list.add(contact);
                                nameList.add(name_map);
                            }



                        } catch (final JSONException e) {
                            Log.e(TAG, "Json parsing error: " + e.getMessage());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),
                                            "Json parsing error: " + e.getMessage(),
                                            Toast.LENGTH_LONG)
                                            .show();
                                }
                            });
                        }

                        }
                    }
                    if (SampleFragment.vegetables_category== true) {
                        String jsonStr = sh.makeServiceCall(vegetables_url);


                        Log.e(TAG, "Response from url: " + jsonStr);

                        if (jsonStr != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(jsonStr);

                                // Getting JSON Array node
                                JSONArray contacts = jsonObj.getJSONArray("result");
                                int resultSize = contacts.length();

                                RandomPermutation rp = new RandomPermutation(0, resultSize - 1);


                                for (int i = 0; i < resultSize; i++) {
                                    JSONObject c1 = contacts.getJSONObject(rp.GetRandom());
                                    String name = c1.getString("name");
                                    String image_path = c1.getString("image_path");
                                    String word = c1.getString("word");
                                    HashMap<String, String> contact = new HashMap<>();
                                    HashMap<String, String> name_map = new HashMap<>();
                                    contact.put("name", name);
                                    contact.put("image_path", image_path);
                                    contact.put("word", word);

                                    contactList.add(contact);
                                    second_list.add(contact);
                                    nameList.add(name_map);
                                }



                            } catch (final JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Json parsing error: " + e.getMessage(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                            }

                        }
                    }

                    if (SampleFragment.foods_category== true) {
                        String jsonStr = sh.makeServiceCall(foods_url);


                        Log.e(TAG, "Response from url: " + jsonStr);

                        if (jsonStr != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(jsonStr);

                                // Getting JSON Array node
                                JSONArray contacts = jsonObj.getJSONArray("result");
                                int resultSize = contacts.length();

                                RandomPermutation rp = new RandomPermutation(0, resultSize - 1);


                                for (int i = 0; i <6; i++) {
                                    JSONObject c1 = contacts.getJSONObject(rp.GetRandom());
                                    String name = c1.getString("name");
                                    String image_path = c1.getString("image_path");
                                    String word = c1.getString("word");
                                    HashMap<String, String> contact = new HashMap<>();
                                    HashMap<String, String> name_map = new HashMap<>();
                                    contact.put("name", name);
                                    contact.put("image_path", image_path);
                                    contact.put("word", word);

                                    contactList.add(contact);
                                    second_list.add(contact);
                                    nameList.add(name_map);
                                }



                            } catch (final JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Json parsing error: " + e.getMessage(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                            }

                        }
                    }

                    if (SampleFragment.clothes_category== true) {
                        String jsonStr = sh.makeServiceCall(clothes_url);


                        Log.e(TAG, "Response from url: " + jsonStr);

                        if (jsonStr != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(jsonStr);

                                // Getting JSON Array node
                                JSONArray contacts = jsonObj.getJSONArray("result_clothes");
                                int resultSize = contacts.length();

                                RandomPermutation rp = new RandomPermutation(0, resultSize - 1);


                                for (int i = 0; i < 6; i++) {
                                    JSONObject c1 = contacts.getJSONObject(rp.GetRandom());
                                    String name = c1.getString("name");
                                    String image_path = c1.getString("image_path");
                                    String word = c1.getString("word");
                                    HashMap<String, String> contact = new HashMap<>();
                                    HashMap<String, String> name_map = new HashMap<>();
                                    contact.put("name", name);
                                    contact.put("image_path", image_path);
                                    contact.put("word", word);

                                    contactList.add(contact);
                                    second_list.add(contact);
                                    nameList.add(name_map);
                                }



                            } catch (final JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Json parsing error: " + e.getMessage(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                            }

                        }
                    }

                    if (SampleFragment.hobbies_category== true) {
                        String jsonStr = sh.makeServiceCall(hobbies_url);


                        Log.e(TAG, "Response from url: " + jsonStr);

                        if (jsonStr != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(jsonStr);

                                // Getting JSON Array node
                                JSONArray contacts = jsonObj.getJSONArray("result");
                                int resultSize = contacts.length();

                                RandomPermutation rp = new RandomPermutation(0, resultSize - 1);


                                for (int i = 0; i < 6; i++) {
                                    JSONObject c1 = contacts.getJSONObject(rp.GetRandom());
                                    String name = c1.getString("name");
                                    String image_path = c1.getString("image_path");
                                    String word = c1.getString("word");
                                    HashMap<String, String> contact = new HashMap<>();
                                    HashMap<String, String> name_map = new HashMap<>();
                                    contact.put("name", name);
                                    contact.put("image_path", image_path);
                                    contact.put("word", word);

                                    contactList.add(contact);
                                    second_list.add(contact);
                                    nameList.add(name_map);
                                }



                            } catch (final JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Json parsing error: " + e.getMessage(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                            }

                        }
                    }

                    if (SampleFragment.fruits_category== true) {
                        String jsonStr = sh.makeServiceCall(fruits_url);


                        Log.e(TAG, "Response from url: " + jsonStr);

                        if (jsonStr != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(jsonStr);

                                // Getting JSON Array node
                                JSONArray contacts = jsonObj.getJSONArray("result");
                                int resultSize = contacts.length();

                                RandomPermutation rp = new RandomPermutation(0, resultSize - 1);


                                for (int i = 0; i < 6; i++) {
                                    JSONObject c1 = contacts.getJSONObject(rp.GetRandom());
                                    String name = c1.getString("name");
                                    String image_path = c1.getString("image_path");
                                    String word = c1.getString("word");
                                    HashMap<String, String> contact = new HashMap<>();
                                    HashMap<String, String> name_map = new HashMap<>();
                                    contact.put("name", name);
                                    contact.put("image_path", image_path);
                                    contact.put("word", word);

                                    contactList.add(contact);
                                    second_list.add(contact);
                                    nameList.add(name_map);
                                }



                            } catch (final JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Json parsing error: " + e.getMessage(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                            }

                        }
                    }

                    if (SampleFragment.fruits_category== true) {
                        String jsonStr = sh.makeServiceCall(fruits_url);


                        Log.e(TAG, "Response from url: " + jsonStr);

                        if (jsonStr != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(jsonStr);

                                // Getting JSON Array node
                                JSONArray contacts = jsonObj.getJSONArray("result");
                                int resultSize = contacts.length();

                                RandomPermutation rp = new RandomPermutation(0, resultSize - 1);


                                for (int i = 0; i < 6; i++) {
                                    JSONObject c1 = contacts.getJSONObject(rp.GetRandom());
                                    String name = c1.getString("name");
                                    String image_path = c1.getString("image_path");
                                    String word = c1.getString("word");
                                    HashMap<String, String> contact = new HashMap<>();
                                    HashMap<String, String> name_map = new HashMap<>();
                                    contact.put("name", name);
                                    contact.put("image_path", image_path);
                                    contact.put("word", word);

                                    contactList.add(contact);
                                    second_list.add(contact);
                                    nameList.add(name_map);
                                }



                            } catch (final JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Json parsing error: " + e.getMessage(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                            }

                        }
                    }
                    if (SampleFragmentTwo.sports_category== true) {
                        String jsonStr = sh.makeServiceCall(sports_url);


                        Log.e(TAG, "Response from url: " + jsonStr);

                        if (jsonStr != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(jsonStr);

                                // Getting JSON Array node
                                JSONArray contacts = jsonObj.getJSONArray("result");
                                int resultSize = contacts.length();

                                RandomPermutation rp = new RandomPermutation(0, resultSize - 1);


                                for (int i = 0; i < 6; i++) {
                                    JSONObject c1 = contacts.getJSONObject(rp.GetRandom());
                                    String name = c1.getString("name");
                                    String image_path = c1.getString("image_path");
                                    String word = c1.getString("word");
                                    HashMap<String, String> contact = new HashMap<>();
                                    HashMap<String, String> name_map = new HashMap<>();
                                    contact.put("name", name);
                                    contact.put("image_path", image_path);
                                    contact.put("word", word);

                                    contactList.add(contact);
                                    second_list.add(contact);
                                    nameList.add(name_map);
                                }



                            } catch (final JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Json parsing error: " + e.getMessage(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                            }

                        }
                    }

                    if (SampleFragmentTwo.colors_category== true) {
                        String jsonStr = sh.makeServiceCall(colors_url);


                        Log.e(TAG, "Response from url: " + jsonStr);

                        if (jsonStr != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(jsonStr);

                                // Getting JSON Array node
                                JSONArray contacts = jsonObj.getJSONArray("result");
                                int resultSize = contacts.length();

                                RandomPermutation rp = new RandomPermutation(0, resultSize - 1);


                                for (int i = 0; i < 6; i++) {
                                    JSONObject c1 = contacts.getJSONObject(rp.GetRandom());
                                    String name = c1.getString("name");
                                    String image_path = c1.getString("image_path");
                                    String word = c1.getString("word");
                                    HashMap<String, String> contact = new HashMap<>();
                                    HashMap<String, String> name_map = new HashMap<>();
                                    contact.put("name", name);
                                    contact.put("image_path", image_path);
                                    contact.put("word", word);

                                    contactList.add(contact);
                                    second_list.add(contact);
                                    nameList.add(name_map);
                                }



                            } catch (final JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Json parsing error: " + e.getMessage(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                            }

                        }
                    }

                    if (SampleFragmentTwo.family_category== true) {
                        String jsonStr = sh.makeServiceCall(family_url);


                        Log.e(TAG, "Response from url: " + jsonStr);

                        if (jsonStr != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(jsonStr);

                                // Getting JSON Array node
                                JSONArray contacts = jsonObj.getJSONArray("result_families");
                                int resultSize = contacts.length();

                                RandomPermutation rp = new RandomPermutation(0, resultSize - 1);


                                for (int i = 0; i < 6; i++) {
                                    JSONObject c1 = contacts.getJSONObject(rp.GetRandom());
                                    String name = c1.getString("name");
                                    String image_path = c1.getString("image_path");
                                    String word = c1.getString("word");
                                    HashMap<String, String> contact = new HashMap<>();
                                    HashMap<String, String> name_map = new HashMap<>();
                                    contact.put("name", name);
                                    contact.put("image_path", image_path);
                                    contact.put("word", word);

                                    contactList.add(contact);
                                    second_list.add(contact);
                                    nameList.add(name_map);
                                }



                            } catch (final JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Json parsing error: " + e.getMessage(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                            }

                        }
                    }

                    if (SampleFragmentTwo.shapes_category== true) {
                        String jsonStr = sh.makeServiceCall(shapes_url);


                        Log.e(TAG, "Response from url: " + jsonStr);

                        if (jsonStr != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(jsonStr);

                                // Getting JSON Array node
                                JSONArray contacts = jsonObj.getJSONArray("result");
                                int resultSize = contacts.length();

                                RandomPermutation rp = new RandomPermutation(0, resultSize - 1);


                                for (int i = 0; i < 6; i++) {
                                    JSONObject c1 = contacts.getJSONObject(rp.GetRandom());
                                    String name = c1.getString("name");
                                    String image_path = c1.getString("image_path");
                                    String word = c1.getString("word");
                                    HashMap<String, String> contact = new HashMap<>();
                                    HashMap<String, String> name_map = new HashMap<>();
                                    contact.put("name", name);
                                    contact.put("image_path", image_path);
                                    contact.put("word", word);

                                    contactList.add(contact);
                                    second_list.add(contact);
                                    nameList.add(name_map);
                                }



                            } catch (final JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Json parsing error: " + e.getMessage(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                            }

                        }
                    }

                    if (SampleFragmentTwo.vehicles_category== true) {
                        String jsonStr = sh.makeServiceCall(vehicles_url);
                        //contactList.clear();

                        Log.e(TAG, "Response from url: " + jsonStr);

                        if (jsonStr != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(jsonStr);

                                // Getting JSON Array node
                                JSONArray contacts = jsonObj.getJSONArray("result_vehicles");
                                int resultSize = contacts.length();

                                RandomPermutation rp = new RandomPermutation(0, resultSize - 1);


                                for (int i = 0; i < 6; i++) {
                                    JSONObject c1 = contacts.getJSONObject(rp.GetRandom());
                                    String name = c1.getString("name");
                                    String image_path = c1.getString("image_path");
                                    String word = c1.getString("word");
                                    HashMap<String, String> contact = new HashMap<>();
                                    HashMap<String, String> name_map = new HashMap<>();
                                    contact.put("name", name);
                                    contact.put("image_path", image_path);
                                    contact.put("word", word);

                                    contactList.add(contact);
                                    second_list.add(contact);
                                    nameList.add(name_map);
                                }



                            } catch (final JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Json parsing error: " + e.getMessage(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                            }

                        }
                    }

                    if (SampleFragmentTwo.places_category== true) {
                        String jsonStr = sh.makeServiceCall(places_url);


                        Log.e(TAG, "Response from url: " + jsonStr);

                        if (jsonStr != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(jsonStr);

                                // Getting JSON Array node
                                JSONArray contacts = jsonObj.getJSONArray("result");
                                int resultSize = contacts.length();

                                RandomPermutation rp = new RandomPermutation(0, resultSize - 1);


                                for (int i = 0; i < 6; i++) {
                                    JSONObject c1 = contacts.getJSONObject(rp.GetRandom());
                                    String name = c1.getString("name");
                                    String image_path = c1.getString("image_path");
                                    String word = c1.getString("word");
                                    HashMap<String, String> contact = new HashMap<>();
                                    HashMap<String, String> name_map = new HashMap<>();
                                    contact.put("name", name);
                                    contact.put("image_path", image_path);
                                    contact.put("word", word);

                                    contactList.add(contact);
                                    second_list.add(contact);
                                    nameList.add(name_map);
                                }



                            } catch (final JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Json parsing error: " + e.getMessage(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                            }

                        }
                    }

                    if (SampleFragmentThree.animals_category== true) {
                        String jsonStr = sh.makeServiceCall(animals_url);


                        Log.e(TAG, "Response from url: " + jsonStr);

                        if (jsonStr != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(jsonStr);

                                // Getting JSON Array node
                                JSONArray contacts = jsonObj.getJSONArray("result");
                                int resultSize = contacts.length();

                                RandomPermutation rp = new RandomPermutation(0, resultSize - 1);


                                for (int i = 0; i < 6; i++) {
                                    JSONObject c1 = contacts.getJSONObject(rp.GetRandom());
                                    String name = c1.getString("name");
                                    String image_path = c1.getString("image_path");
                                    String word = c1.getString("word");
                                    HashMap<String, String> contact = new HashMap<>();
                                    HashMap<String, String> name_map = new HashMap<>();
                                    contact.put("name", name);
                                    contact.put("image_path", image_path);
                                    contact.put("word", word);

                                    contactList.add(contact);
                                    second_list.add(contact);
                                    nameList.add(name_map);
                                }



                            } catch (final JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Json parsing error: " + e.getMessage(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                            }

                        }
                    }

                    if (SampleFragmentThree.adjectives_category== true) {
                        String jsonStr = sh.makeServiceCall(adjectives_url);


                        Log.e(TAG, "Response from url: " + jsonStr);

                        if (jsonStr != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(jsonStr);

                                // Getting JSON Array node
                                JSONArray contacts = jsonObj.getJSONArray("result");
                                int resultSize = contacts.length();

                                RandomPermutation rp = new RandomPermutation(0, resultSize - 1);


                                for (int i = 0; i < 6; i++) {
                                    JSONObject c1 = contacts.getJSONObject(rp.GetRandom());
                                    String name = c1.getString("name");
                                    String image_path = c1.getString("image_path");
                                    String word = c1.getString("word");
                                    HashMap<String, String> contact = new HashMap<>();
                                    HashMap<String, String> name_map = new HashMap<>();
                                    contact.put("name", name);
                                    contact.put("image_path", image_path);
                                    contact.put("word", word);

                                    contactList.add(contact);
                                    second_list.add(contact);
                                    nameList.add(name_map);
                                }



                            } catch (final JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Json parsing error: " + e.getMessage(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                            }

                        }
                    }

                    if (SampleFragmentThree.numbers_category== true) {
                        String jsonStr = sh.makeServiceCall(numbers_url);


                        Log.e(TAG, "Response from url: " + jsonStr);

                        if (jsonStr != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(jsonStr);

                                // Getting JSON Array node
                                JSONArray contacts = jsonObj.getJSONArray("result");
                                int resultSize = contacts.length();

                                RandomPermutation rp = new RandomPermutation(0, resultSize - 1);


                                for (int i = 0; i < 6; i++) {
                                    JSONObject c1 = contacts.getJSONObject(rp.GetRandom());
                                    String name = c1.getString("name");
                                    String image_path = c1.getString("image_path");
                                    String word = c1.getString("word");
                                    HashMap<String, String> contact = new HashMap<>();
                                    HashMap<String, String> name_map = new HashMap<>();
                                    contact.put("name", name);
                                    contact.put("image_path", image_path);
                                    contact.put("word", word);

                                    contactList.add(contact);
                                    second_list.add(contact);
                                    nameList.add(name_map);
                                }



                            } catch (final JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Json parsing error: " + e.getMessage(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                            }

                        }
                    }

                    if (SampleFragmentThree.countries_category== true) {
                        String jsonStr = sh.makeServiceCall(countries_url);


                        Log.e(TAG, "Response from url: " + jsonStr);

                        if (jsonStr != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(jsonStr);

                                // Getting JSON Array node
                                JSONArray contacts = jsonObj.getJSONArray("result");
                                int resultSize = contacts.length();

                                RandomPermutation rp = new RandomPermutation(0, resultSize - 1);


                                for (int i = 0; i < 6; i++) {
                                    JSONObject c1 = contacts.getJSONObject(rp.GetRandom());
                                    String name = c1.getString("name");
                                    String image_path = c1.getString("image_path");
                                    String word = c1.getString("word");
                                    HashMap<String, String> contact = new HashMap<>();
                                    HashMap<String, String> name_map = new HashMap<>();
                                    contact.put("name", name);
                                    contact.put("image_path", image_path);
                                    contact.put("word", word);

                                    contactList.add(contact);
                                    second_list.add(contact);
                                    nameList.add(name_map);
                                }



                            } catch (final JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Json parsing error: " + e.getMessage(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                            }

                        }
                    }

                    if (SampleFragmentThree.weathers_category== true) {
                        String jsonStr = sh.makeServiceCall(weathers_url);


                        Log.e(TAG, "Response from url: " + jsonStr);

                        if (jsonStr != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(jsonStr);

                                // Getting JSON Array node
                                JSONArray contacts = jsonObj.getJSONArray("result");
                                int resultSize = contacts.length();

                                RandomPermutation rp = new RandomPermutation(0, resultSize - 1);


                                for (int i = 0; i < 6; i++) {
                                    JSONObject c1 = contacts.getJSONObject(rp.GetRandom());
                                    String name = c1.getString("name");
                                    String image_path = c1.getString("image_path");
                                    String word = c1.getString("word");
                                    HashMap<String, String> contact = new HashMap<>();
                                    HashMap<String, String> name_map = new HashMap<>();
                                    contact.put("name", name);
                                    contact.put("image_path", image_path);
                                    contact.put("word", word);

                                    contactList.add(contact);
                                    second_list.add(contact);
                                    nameList.add(name_map);
                                }



                            } catch (final JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Json parsing error: " + e.getMessage(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                            }

                        }
                    }

                    if (SampleFragmentThree.jobs_category== true) {
                        String jsonStr = sh.makeServiceCall(jobs_url);


                        Log.e(TAG, "Response from url: " + jsonStr);

                        if (jsonStr != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(jsonStr);

                                // Getting JSON Array node
                                JSONArray contacts = jsonObj.getJSONArray("result");
                                int resultSize = contacts.length();

                                RandomPermutation rp = new RandomPermutation(0, resultSize - 1);


                                for (int i = 0; i < 6; i++) {
                                    JSONObject c1 = contacts.getJSONObject(rp.GetRandom());
                                    String name = c1.getString("name");
                                    String image_path = c1.getString("image_path");
                                    String word = c1.getString("word");
                                    HashMap<String, String> contact = new HashMap<>();
                                    HashMap<String, String> name_map = new HashMap<>();
                                    contact.put("name", name);
                                    contact.put("image_path", image_path);
                                    contact.put("word", word);

                                    contactList.add(contact);
                                    second_list.add(contact);
                                    nameList.add(name_map);
                                }



                            } catch (final JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Json parsing error: " + e.getMessage(),
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });
                            }

                        }
                    }





                    else {
                        Log.e(TAG, "Couldn't get json from server.");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Couldn't get json from server. Check LogCat for possible errors!",
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });

                    }



                    return null;
                }

                @Override
                protected void onPostExecute(Void result) {
                    super.onPostExecute(result);
                    // Dismiss the progress dialog
                    if (pDialog.isShowing())
                        pDialog.dismiss();
                    // random_image.setAnimation(image_anim);
                    random_image.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                 //Collections.shuffle(contactList);
                   //Collections.shuffle(nameList);
                    int entered=0;

                    word1.setText(contactList.get(0).get("name"));
                    word1.setTypeface(tf1);
                    word1.setAnimation(image_anim);
                    word1.setTextSize(15);
                    word1.setTextColor(Color.rgb(125, 60, 152));
                    drag_word.setText(contactList.get(0).get("word"));
                    drag_word.setTypeface(tf1);
                    drag_word.setTextColor(Color.TRANSPARENT);
                    imageLoader.displayImage(contactList.get(0).get("image_path"), random_image);
                   // drag_word.setTextColor(Color.TRANSPARENT);

                 entered++;
                    contactList.remove(0);


                    word2.setText(contactList.get(0).get("name"));
                    word2.setTypeface(tf1);
                    word2.setAnimation(image_anim);
                    word2.setTextSize(15);
                    word2.setTextColor(Color.rgb(125, 60, 152));


                    word3.setText(contactList.get(1).get("name"));
                    word3.setTypeface(tf1);
                    word3.setAnimation(image_anim);
                    word3.setTextSize(15);
                    word3.setTextColor(Color.rgb(125, 60, 152));

                    word4.setText(contactList.get(2).get("name"));
                    word4.setTypeface(tf1);
                    word4.setAnimation(image_anim);
                    word4.setTextSize(15);
                    word4.setTextColor(Color.rgb(125, 60, 152));



                    word5.setText(contactList.get(3).get("name"));
                    word5.setTypeface(tf1);
                    word5.setAnimation(image_anim);
                    word5.setTextSize(15);
                    word5.setTextColor(Color.rgb(125, 60, 152));

                    word6.setText(contactList.get(4).get("name"));
                    word6.setTypeface(tf1);
                    word6.setAnimation(image_anim);
                    word6.setTextSize(15);
                    word6.setTextColor(Color.rgb(125, 60, 152));

                    back.start();
                    timerText.setTextColor(Color.rgb(125, 60, 152));
                    Typeface type = Typeface.createFromAsset(getAssets(), "fonts/sky.ttf");
                    timerText.setTypeface(type);
                    timerText.setTextSize(25);
                    startCountdownTimer(timerText);


                }
            }

    class saveScore extends AsyncTask<Void, Void, Void> {
        private String resultmessage;
        ProgressDialog pDialog;

        protected void onPreExecute() {

        }

        protected Void doInBackground(Void... unused) {
            // Building Parameters

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", MainActivity.name));
            params.add(new BasicNameValuePair("score",scoreText.getText().toString()));
            params.add(new BasicNameValuePair("level", LevelScreen.level));
            params.add(new BasicNameValuePair("email", MainActivity.email));

            String json = post.httpPost(URL_POST,"POST",params,20000);

            Log.d("Gelen Json",""+json);//Gelen veriyi logluyoruz.Log Catten kontrol edebiliriz

            return null;
        }
        // Sonu� ba�ar�l� ise bu kod �al��m�cak ��nk� Main activitye y�nlenmi� durumda
        protected void onPostExecute(Void unused) {

        }

    }

    public class GetHighScore extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url_high_score);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    score_list=new ArrayList<>();
                    // Getting JSON Array node
                    JSONArray scores = jsonObj.getJSONArray("result");
                    for (int i = 0; i <scores.length(); i++) {
                        JSONObject c = scores.getJSONObject(i);

                        String name = c.getString("name");
                        String score = c.getString("score");
                        String level = c.getString("level");
                        HashMap<String, String> score_map = new HashMap<>();
                        score_map.put("name", name);
                        score_map.put("score", score);
                        score_map.put("level", level);

                        score_list.add(score_map);
                    }
                    for(int i=0;i<scores.length();i++) {
                        Log.d("List elements are ", score_list.get(i).toString());
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);


            for (int i = 0; i < score_list.size(); i++) {
                Log.d("ELEMENTS ARE : ", score_list.get(i).toString());


                if (LevelScreen.isLevel2 == true) {
                    if (MainActivity.name.equals(score_list.get(i).get("name")) && score_list.get(i).get("level").equals("2")) {
                        Log.d("1IS IT TRUE?", "TRUE");
                        if (Integer.valueOf(scoreText.getText().toString()) > Integer.valueOf(score_list.get(i).get("score").toString())
                                ) {

                            Log.d("2IS IT TRUE?", "SECOND LEVEL FIRST CONDITION");
                            high_score_result.setText(scoreText.getText().toString());
                        } else {
                            // Log.d("Arraylist elemntsssss" ,score_list.get(i).get("score").toString());
                           high_score_result.setText(String.valueOf(score_list.get(i).get("score")));
                        }

                    }

                }

            }}
            }

        }
