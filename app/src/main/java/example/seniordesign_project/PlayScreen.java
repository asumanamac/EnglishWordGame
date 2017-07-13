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
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;



import static example.seniordesign_project.Animationss.anticlokwise;
import static example.seniordesign_project.Animationss.close;
import static example.seniordesign_project.Animationss.game_over_slide;
import static example.seniordesign_project.Animationss.game_over_sound;
import static example.seniordesign_project.Animationss.image_anim;
import static example.seniordesign_project.Animationss.open;
import static example.seniordesign_project.Animationss.success_background_sound;
import static example.seniordesign_project.Animationss.back;
import static example.seniordesign_project.Animationss.sucess;
import static example.seniordesign_project.Animationss.fail;
import static example.seniordesign_project.Animationss.buttonSound;



public class PlayScreen extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    ImageLoader imageLoader;
    public static TextView scoreText;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, timerText,scoreSucess,
            helpText;
    ImageView imageView1, imageView2, imageView3, imageView4,right1,right2,right3;
    public static TextView scoreSucResult;
    FloatingActionButton setting, volume, out, home;
    private static String url = "http://192.168.2.11:8080/android_login_api/mages/getAllImages_music.php";
    Typeface tf1;
    Score score;
    boolean match1=false,match2=false,match3=false,match4=false;
    public static boolean isClick=false;
    Dialog game_over_dialog,success_screen_dialog,information_box;
    PostClass post = new PostClass();
    String URL_POST= "http://192.168.2.11:8080/android_login_api/score.php";

    Handler handler;
    public static TextView total_score_result,total_Score_text,wrong_text,wrong_result,correct_text,correct_result;
    public static TextView high_score_result,high_score_text;
    public static TextView level_text,level_result;
    public static ImageButton play_button,cancel_button,continue_button;
    private static final String PREFS_LAST_IMG = "prefs_last_img";
    private SharedPreferences mPreferences;

    public static CountDownTimer countDownTimer;
    public static int count=3,correct=0,wrong=0;
    public static String level;
    public static boolean isPaused = false, game_over=false,game_success=false;
    public static boolean isCanceled = false;
    public long timeRemaining = 0;

    RelativeLayout relativeLayout;
    ArrayList<HashMap<String, String>> contactList;
    HashMap<String, String> contact = new HashMap<>();
    HashMap<String, String> contact1 = new HashMap<>();
    HashMap<String, String> contact2 = new HashMap<>();
    HashMap<String, String> contact3 = new HashMap<>();
    HashMap<String, String> contact4 = new HashMap<>();
    static boolean isGameOver=false;

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

    ArrayList<HashMap<String, String>> score_list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);


        relativeLayout = (RelativeLayout) findViewById(R.id.play_relative);
        tf1 = Typeface.createFromAsset(getAssets(), "fonts/BalooBhai-Regular.ttf");

        setting = (FloatingActionButton) findViewById(R.id.floatmain);
        volume = (FloatingActionButton) findViewById(R.id.volumeon);
        out = (FloatingActionButton) findViewById(R.id.out);
        home = (FloatingActionButton) findViewById(R.id.home);
        right1 =(ImageView) findViewById(R.id.imageView6);
        right2 =(ImageView) findViewById(R.id.imageView8);
        right3 =(ImageView) findViewById(R.id.imageView9);


        textView1 = (TextView) findViewById(R.id.word1);
        textView2 = (TextView) findViewById(R.id.word2);
        textView3 = (TextView) findViewById(R.id.word3);
        textView4 = (TextView) findViewById(R.id.word4);

        textView5 = (TextView) findViewById(R.id.match_word1);
        textView6 = (TextView) findViewById(R.id.match_word2);
        textView7 = (TextView) findViewById(R.id.match_word3);
        textView8 = (TextView) findViewById(R.id.match_word4);

        scoreText = (TextView) findViewById(R.id.score_text);
        timerText = (TextView) findViewById(R.id.timer_text);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/sky.ttf");
        scoreText.setTextColor(Color.rgb(125, 60, 152));
        scoreText.setTypeface(type);
        scoreText.setTextSize(25);

        imageView1 = (ImageView) findViewById(R.id.image1);
        imageView2 = (ImageView) findViewById(R.id.image2);
        imageView3 = (ImageView) findViewById(R.id.image3);
        imageView4 = (ImageView) findViewById(R.id.image4);

        contactList = new ArrayList<>();

        imageView1.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
        imageView2.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
        imageView3.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
        imageView4.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));

        new GetContacts().execute();

        textView5.setOnDragListener(new ChoiceDragListener());

        textView1.setOnTouchListener(new ChoiceTouchListener());

        textView6.setOnDragListener(new ChoiceDragListener());
        textView2.setOnTouchListener(new ChoiceTouchListener());

        textView7.setOnDragListener(new ChoiceDragListener());
        textView3.setOnTouchListener(new ChoiceTouchListener());

        textView8.setOnDragListener(new ChoiceDragListener());
        textView4.setOnTouchListener(new ChoiceTouchListener());

        setting.setOnClickListener(new FloatButtonAction());

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
        game_over_dialog=new Dialog(PlayScreen.this);
        success_screen_dialog=new Dialog(PlayScreen.this);
        information_box = new Dialog(PlayScreen.this);
        scoreText.setText(""+Score.resetScore());

        Animationss anim=new Animationss(getApplicationContext());

        LevelScreen.isLevel1=true;

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
                Intent mStartActivity = new Intent(PlayScreen.this, MainActivity.class);
                int mPendingIntentId = 123456;
                PendingIntent mPendingIntent = PendingIntent.getActivity(PlayScreen.this, mPendingIntentId, mStartActivity,
                        PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager) PlayScreen.this.getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
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

    public void startCountdownTimer(final TextView count_timer) {
        countDownTimer = new CountDownTimer(15000, 1500) {

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
    public void onBackPressed() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure to exit the game?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                back.stop();
                dialog.dismiss();
                Intent mStartActivity = new Intent(PlayScreen.this, MainActivity.class);
               startActivity(mStartActivity);
                finish();
                Intent i = new Intent(PlayScreen.this, MainActivity.class);
                startActivity(i);
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

    public void gameOver()
    {
        if(back.isPlaying())
        {
            back.stop();
        }

        LevelScreen.isLevel1=true;

        PlayScreen.countDownTimer.cancel();
        game_over=true;
        HighScoreActivity.play_screen=true;
        game_over_dialog.setContentView(R.layout.activity_game_over);

        total_score_result = (TextView) game_over_dialog.findViewById(R.id.total_score_result);
        total_Score_text = (TextView) game_over_dialog.findViewById(R.id.total_score_text);

        high_score_result =(TextView) game_over_dialog.findViewById(R.id.high_score_result);
        high_score_text = (TextView) game_over_dialog.findViewById(R.id.high_score_text);

        level_result =(TextView) game_over_dialog.findViewById(R.id.level_result);
        level_text=(TextView) game_over_dialog.findViewById(R.id.level_text);

        play_button = (ImageButton) game_over_dialog.findViewById(R.id.play_button);
        cancel_button = (ImageButton) game_over_dialog.findViewById(R.id.cancel_button);

        level=LevelScreen.level;

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


        high_score_result.setTextSize(13);
        high_score_text.setTextSize(13);
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
        game_over_dialog.show();
        game_over_sound.start();




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
                Intent i = new Intent(PlayScreen.this, LevelScreen.class);
                startActivity(i);
                finish();

               // SecondLevelActivity.isLevel2=true;
                SecondLevelActivity.isGameOver=false;
                count=3;
                correct=0;
                wrong=0;


                game_over_dialog.dismiss();

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
                timerText.setText("");
                scoreText.setText(""+Score.resetScore());
                back.stop();
                wrong=0;
                correct=0;

                Fragment mFragment = new SampleFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.play_relative, mFragment).commit();
                game_over_dialog.dismiss();


            }
        });



    }

    public void successScreen()
    {

        if(back.isPlaying())
        {
            back.stop();
        }

        PlayScreen.countDownTimer.cancel();
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
        isClick=true;
        new saveScore().execute();

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(success_background_sound.isPlaying())
                    success_background_sound.stop();
                buttonSound.start();

                Intent intent=new Intent(PlayScreen.this, LevelScreen.class);
                startActivity(intent);
                scoreText.setText(""+Score.resetScore());
                //finish();
                isClick=true;
                SecondLevelActivity.isGameOver=false;
                count=3;
                correct=0;
                wrong=0;


               // success_screen_dialog.dismiss();


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
                back.stop();

                wrong=0;
                correct=0;

                Fragment mFragment = new SampleFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.play_relative, mFragment).commit();
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
                    View view = (View) event.getLocalState();
                    //view dragged item is being dropped on
                       /* TextView dropTarget = (TextView) v;
                    TextView dropped = (TextView) view;

                if(dropTarget.getText().toString().equals(dropped.getText().toString())) {
                        view.setVisibility(View.INVISIBLE);
                        dropTarget.setText(dropped.getText().toString());

                        dropTarget.setOnDragListener(null);

                    }*/

                     TextView dropTarget = (TextView) v;



                   if (event.getLocalState() == textView1) {
                        if (dropTarget==textView5 &&
                                dropTarget.getText().toString().equals(textView1.getText().toString())) {

                            sucess.start();
                            textView1.setVisibility(View.INVISIBLE);
                            imageView1.setImageResource(R.drawable.purple);
                            imageView1.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            textView1.clearAnimation();
                            textView5.setTextColor(Color.rgb(125, 60, 152));
                            textView5.setText("");
                            textView5.append(textView1.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match1=true;
                        }

                        else if (dropTarget==textView6 &&
                                dropTarget.getText().toString().equals(textView1.getText().toString())) {

                            sucess.start();
                            imageView2.setImageResource(R.drawable.purple);
                            imageView2.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            textView1.clearAnimation();
                            textView1.setVisibility(View.INVISIBLE);
                            textView6.setTextColor(Color.rgb(125, 60, 152));
                            textView6.setText("");
                            dropTarget.append(textView1.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match1=true;
                        }

                        else if (dropTarget==textView7 &&
                                dropTarget.getText().toString().equals(textView1.getText().toString())) {

                            sucess.start();
                            imageView3.setImageResource(R.drawable.purple);
                            imageView3.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            textView1.clearAnimation();
                            textView1.setVisibility(View.INVISIBLE);
                            textView7.setTextColor(Color.rgb(125, 60, 152));
                            textView7.setText("");
                            dropTarget.append(textView1.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match1=true;
                        }

                        else if (dropTarget==textView8 &&
                                dropTarget.getText().toString().equals(textView1.getText().toString())) {

                            sucess.start();
                            imageView4.setImageResource(R.drawable.purple);
                            imageView4.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            textView1.clearAnimation();
                            textView1.setVisibility(View.INVISIBLE);
                            textView8.setTextColor(Color.rgb(125, 60, 152));
                            textView8.setText("");
                            dropTarget.append(textView1.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match1=true;
                        }
                        else
                        {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();
                            count--;
                            wrong++;
                            if(wrong<0)
                                wrong=0;
                            match1=false;


                            if(count==1)
                            {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if(count==2)
                            {
                                right2.setVisibility(View.INVISIBLE);

                            }


                            if(count==0 || timerText.getText().equals(0+""))
                            {
                                right3.setVisibility(View.INVISIBLE);
                                gameOver();

                            }

                        }
                        if(fail.isPlaying())
                        {
                            buttonSound.stop();
                        }



                    }
                    if (event.getLocalState() == textView2) {
                        if (dropTarget==textView5 &&
                                dropTarget.getText().toString().equals(textView2.getText().toString())) {

                            sucess.start();
                            imageView1.setImageResource(R.drawable.purple);
                            imageView1.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            textView2.clearAnimation();
                            textView2.setVisibility(View.INVISIBLE);
                            textView5.setTextColor(Color.rgb(125, 60, 152));
                            textView5.setText("");
                            dropTarget.append(textView2.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match2=true;


                        }

                        else if (dropTarget==textView6 &&
                                dropTarget.getText().toString().equals(textView2.getText().toString())) {

                            sucess.start();
                            imageView2.setImageResource(R.drawable.purple);
                            imageView2.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            textView2.clearAnimation();
                            textView2.setVisibility(View.INVISIBLE);
                            textView6.setTextColor(Color.rgb(125, 60, 152));
                            textView6.setText("");
                            dropTarget.append(textView2.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match2=true;


                        }

                        else if (dropTarget==textView7 &&
                                dropTarget.getText().toString().equals(textView2.getText().toString())) {

                            sucess.start();
                            imageView3.setImageResource(R.drawable.purple);
                            imageView3.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            textView2.clearAnimation();
                            textView2.setVisibility(View.INVISIBLE);
                            textView7.setTextColor(Color.rgb(125, 60, 152));
                            textView7.setText("");
                            dropTarget.append(textView2.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match2=true;

                        }
                        else if (dropTarget==textView8 &&
                                dropTarget.getText().toString().equals(textView2.getText().toString())) {

                            sucess.start();
                            imageView4.setImageResource(R.drawable.purple);
                            imageView4.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            textView2.clearAnimation();
                            textView2.setVisibility(View.INVISIBLE);
                            textView8.setTextColor(Color.rgb(125, 60, 152));
                            textView8.setText("");
                            dropTarget.append(textView2.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match2=true;
                        }
                        else
                        {

                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if(wrong<0)
                                wrong=0;
                            match2=false;


                            if(count==1)
                            {
                                right1.setVisibility(View.INVISIBLE);


                            }
                            if(count==2)
                            {
                                right2.setVisibility(View.INVISIBLE);

                            }

                            if(count==0 || timerText.getText().equals(0+""))
                            {
                                right3.setVisibility(View.INVISIBLE);
                                gameOver();
                            }
                        }

                        if(fail.isPlaying())
                        {
                            buttonSound.stop();
                        }


                    }
                    if (event.getLocalState() == textView3) {
                        if (dropTarget==textView5 &&
                                dropTarget.getText().toString().equals(textView3.getText().toString())) {

                            sucess.start();
                            imageView1.setImageResource(R.drawable.purple);
                            imageView1.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            textView3.clearAnimation();
                            textView3.setVisibility(View.INVISIBLE);
                            textView5.setTextColor(Color.rgb(125, 60, 152));
                            textView5.setText("");
                            dropTarget.append(textView3.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match3=true;

                        }

                        else if (dropTarget==textView6 &&
                                dropTarget.getText().toString().equals(textView3.getText().toString())) {

                            sucess.start();
                            imageView2.setImageResource(R.drawable.purple);
                            imageView2.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            textView3.clearAnimation();
                            textView3.setVisibility(View.INVISIBLE);
                            textView6.setTextColor(Color.rgb(125, 60, 152));
                            textView6.setText("");
                            dropTarget.append(textView3.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match3=true;

                        }

                        else if (dropTarget==textView7 &&
                                dropTarget.getText().toString().equals(textView3.getText().toString())) {

                            sucess.start();
                            imageView3.setImageResource(R.drawable.purple);
                            imageView3.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            textView3.clearAnimation();
                            textView3.setVisibility(View.INVISIBLE);
                            textView7.setTextColor(Color.rgb(125, 60, 152));
                            textView7.setText("");
                            dropTarget.append(textView3.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match3=true;

                        }

                        else if (dropTarget==textView8 &&
                                dropTarget.getText().toString().equals(textView3.getText().toString())) {

                            sucess.start();
                            imageView4.setImageResource(R.drawable.purple);
                            imageView4.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            textView3.clearAnimation();
                            textView3.setVisibility(View.INVISIBLE);
                            textView8.setTextColor(Color.rgb(125, 60, 152));
                            textView8.setText("");
                            dropTarget.append(textView3.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match3=true;

                        }
                        else
                        {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();
                            count--;
                            wrong++;
                            if(wrong<0)
                                wrong=0;
                            match3=false;

                            if(count==1)
                            {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if(count==2)
                            {
                                right2.setVisibility(View.INVISIBLE);

                            }
                            if(count==0 || timerText.getText().equals(0+""))
                            {
                                right3.setVisibility(View.INVISIBLE);
                                gameOver();
                            }
                        }
                        if(fail.isPlaying())
                        {
                            buttonSound.stop();
                        }

                    }

                    if (event.getLocalState() == textView4) {
                        if (dropTarget==textView5 &&
                                dropTarget.getText().toString().equals(textView4.getText().toString())) {

                            sucess.start();
                            imageView1.setImageResource(R.drawable.purple);
                            imageView1.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            textView4.clearAnimation();
                            textView4.setVisibility(View.INVISIBLE);
                            textView5.setTextColor(Color.rgb(125, 60, 152));
                            textView5.setText("");
                            dropTarget.append(textView4.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match4=true;
                        }

                        else if (dropTarget==textView6 &&
                                dropTarget.getText().toString().equals(textView4.getText().toString())) {

                            sucess.start();
                            imageView2.setImageResource(R.drawable.purple);
                            imageView2.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            textView4.clearAnimation();
                            textView4.setVisibility(View.INVISIBLE);
                            textView6.setTextColor(Color.rgb(125, 60, 152));
                            textView6.setText("");
                            dropTarget.append(textView4.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match4=true;

                        }

                        else if (dropTarget==textView7 &&
                                dropTarget.getText().toString().equals(textView4.getText().toString())) {

                            sucess.start();
                            imageView3.setImageResource(R.drawable.purple);
                            imageView3.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            textView4.clearAnimation();
                            textView4.setVisibility(View.INVISIBLE);
                            textView7.setTextColor(Color.rgb(125, 60, 152));
                            textView7.setText("");
                            dropTarget.append(textView4.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match4=true;

                        }

                        else if (dropTarget==textView8 &&
                                dropTarget.getText().toString().equals(textView4.getText().toString())) {

                            sucess.start();
                            imageView4.setImageResource(R.drawable.purple);
                            imageView4.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            textView4.clearAnimation();
                            textView4.setVisibility(View.INVISIBLE);
                            textView8.setTextColor(Color.rgb(125, 60, 152));
                            textView8.setText("");
                            dropTarget.append(textView4.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match4=true;

                        }
                        else
                        {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if(wrong<0)
                                wrong=0;
                            match4=false;

                            if(count==1)
                            {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if(count==2)
                            {
                                right2.setVisibility(View.INVISIBLE);

                            }

                            if(count==0 || timerText.getText().equals(0+""))
                            {
                                right3.setVisibility(View.INVISIBLE);
                                gameOver();
                            }
                        }
                        if(fail.isPlaying())
                        {
                            buttonSound.stop();
                        }


                    }

                    if(match1==true && match2==true && match3==true && match4==true && count>0 &&
                            Integer.valueOf(timerText.getText().toString())>0)
                    {
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


    private class GetContacts extends AsyncTask<Void, Void, Void> {
        private int progressStatus = 0;
        private Handler handler = new Handler();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(PlayScreen.this);
            pDialog.setMessage("Loading...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.show();
        }



        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response

            if (SampleFragment.music_category == true) {


                String jsonStr = sh.makeServiceCall(url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("result");
                        int resultSize = contacts.length();

                        RandomPermutation rp = new RandomPermutation(0, resultSize - 1);
                        JSONObject c1 = contacts.getJSONObject(rp.GetRandom());

                        String name1 = c1.getString("name");
                        String image_path1 = c1.getString("image_path");
                        String word1 = c1.getString("word");
                        contact.put("name1", name1);
                        contact1.put("word", word1);
                        contact1.put("image_path", image_path1);

                        JSONObject c2 = contacts.getJSONObject(rp.GetRandom());
                        String name2 = c2.getString("name");
                        String image_path2 = c2.getString("image_path");
                        String word2 = c2.getString("word");
                        contact.put("name2", name2);
                        contact2.put("word", word2);
                        contact2.put("image_path", image_path2);

                        JSONObject c3 = contacts.getJSONObject(rp.GetRandom());
                        String name3 = c3.getString("name");
                        String image_path3 = c3.getString("image_path");
                        String word3 = c3.getString("word");
                        contact.put("name3", name3);
                        contact3.put("word", word3);
                        contact3.put("image_path", image_path3);

                        JSONObject c4 = contacts.getJSONObject(rp.GetRandom());
                        String name4 = c4.getString("name");
                        String image_path4 = c4.getString("image_path");
                        String word4 = c4.getString("word");
                        contact.put("name4", name4);
                        contact4.put("word", word4);
                        contact4.put("image_path", image_path4);

                        contactList.add(contact1);
                        contactList.add(contact2);
                        contactList.add(contact3);
                        contactList.add(contact4);

                        for (int i = 0; i < contactList.size(); i++) {
                            Log.d("ArrayLst elements are: ", contactList.get(i).toString());
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
                }

            else if (SampleFragment.vegetables_category== true) {

                String jsonStr = sh.makeServiceCall(vegetables_url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("result");
                        int resultSize = contacts.length();

                        RandomPermutation rp = new RandomPermutation(0, resultSize - 1);
                        JSONObject c1 = contacts.getJSONObject(rp.GetRandom());

                        String name1 = c1.getString("name");
                        String image_path1 = c1.getString("image_path");
                        String word1 = c1.getString("word");
                        contact.put("name1", name1);
                        contact1.put("word", word1);
                        contact1.put("image_path", image_path1);

                        JSONObject c2 = contacts.getJSONObject(rp.GetRandom());
                        String name2 = c2.getString("name");
                        String image_path2 = c2.getString("image_path");
                        String word2 = c2.getString("word");
                        contact.put("name2", name2);
                        contact2.put("word", word2);
                        contact2.put("image_path", image_path2);

                        JSONObject c3 = contacts.getJSONObject(rp.GetRandom());
                        String name3 = c3.getString("name");
                        String image_path3 = c3.getString("image_path");
                        String word3 = c3.getString("word");
                        contact.put("name3", name3);
                        contact3.put("word", word3);
                        contact3.put("image_path", image_path3);

                        JSONObject c4 = contacts.getJSONObject(rp.GetRandom());
                        String name4 = c4.getString("name");
                        String image_path4 = c4.getString("image_path");
                        String word4 = c4.getString("word");
                        contact.put("name4", name4);
                        contact4.put("word", word4);
                        contact4.put("image_path", image_path4);

                        contactList.add(contact1);
                        contactList.add(contact2);
                        contactList.add(contact3);
                        contactList.add(contact4);

                        for (int i = 0; i < contactList.size(); i++) {
                            Log.d("ArrayLst elements are: ", contactList.get(i).toString());
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
            }

           else if (SampleFragment.foods_category== true) {



                String jsonStr = sh.makeServiceCall(foods_url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("result");
                        int resultSize = contacts.length();

                        RandomPermutation rp = new RandomPermutation(0, resultSize - 1);
                        JSONObject c1 = contacts.getJSONObject(rp.GetRandom());

                        String name1 = c1.getString("name");
                        String image_path1 = c1.getString("image_path");
                        String word1 = c1.getString("word");
                        contact.put("name1", name1);
                        contact1.put("word", word1);
                        contact1.put("image_path", image_path1);

                        JSONObject c2 = contacts.getJSONObject(rp.GetRandom());
                        String name2 = c2.getString("name");
                        String image_path2 = c2.getString("image_path");
                        String word2 = c2.getString("word");
                        contact.put("name2", name2);
                        contact2.put("word", word2);
                        contact2.put("image_path", image_path2);

                        JSONObject c3 = contacts.getJSONObject(rp.GetRandom());
                        String name3 = c3.getString("name");
                        String image_path3 = c3.getString("image_path");
                        String word3 = c3.getString("word");
                        contact.put("name3", name3);
                        contact3.put("word", word3);
                        contact3.put("image_path", image_path3);

                        JSONObject c4 = contacts.getJSONObject(rp.GetRandom());
                        String name4 = c4.getString("name");
                        String image_path4 = c4.getString("image_path");
                        String word4 = c4.getString("word");
                        contact.put("name4", name4);
                        contact4.put("word", word4);
                        contact4.put("image_path", image_path4);

                        contactList.add(contact1);
                        contactList.add(contact2);
                        contactList.add(contact3);
                        contactList.add(contact4);

                        for (int i = 0; i < contactList.size(); i++) {
                            Log.d("ArrayLst elements are: ", contactList.get(i).toString());
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
            }

            else if (SampleFragment.clothes_category== true) {

                Log.d("SAMPLEFARGMENT STATUS : " ,String.valueOf(SampleFragmentTwo.vehicles_category));
                Log.d("SAMPLEFARGMENT STATUS : " ,String.valueOf(SampleFragmentTwo.family_category));
                Log.d("SAMPLEFARGMENT STATUS : " ,String.valueOf(SampleFragment.clothes_category));

                String jsonStr = sh.makeServiceCall(clothes_url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("result_clothes");
                        int resultSize = contacts.length();

                        RandomPermutation rp = new RandomPermutation(0, resultSize - 1);
                        JSONObject c1 = contacts.getJSONObject(rp.GetRandom());

                        String name1 = c1.getString("name");
                        String image_path1 = c1.getString("image_path");
                        String word1 = c1.getString("word");
                        contact.put("name1", name1);
                        contact1.put("word", word1);
                        contact1.put("image_path", image_path1);

                        JSONObject c2 = contacts.getJSONObject(rp.GetRandom());
                        String name2 = c2.getString("name");
                        String image_path2 = c2.getString("image_path");
                        String word2 = c2.getString("word");
                        contact.put("name2", name2);
                        contact2.put("word", word2);
                        contact2.put("image_path", image_path2);

                        JSONObject c3 = contacts.getJSONObject(rp.GetRandom());
                        String name3 = c3.getString("name");
                        String image_path3 = c3.getString("image_path");
                        String word3 = c3.getString("word");
                        contact.put("name3", name3);
                        contact3.put("word", word3);
                        contact3.put("image_path", image_path3);

                        JSONObject c4 = contacts.getJSONObject(rp.GetRandom());
                        String name4 = c4.getString("name");
                        String image_path4 = c4.getString("image_path");
                        String word4 = c4.getString("word");
                        contact.put("name4", name4);
                        contact4.put("word", word4);
                        contact4.put("image_path", image_path4);

                        contactList.add(contact1);
                        contactList.add(contact2);
                        contactList.add(contact3);
                        contactList.add(contact4);

                        for (int i = 0; i < contactList.size(); i++) {
                            Log.d("ArrayLst elements are: ", contactList.get(i).toString());
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
            }

           else  if (SampleFragment.hobbies_category== true) {

                String jsonStr = sh.makeServiceCall(hobbies_url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("result");
                        int resultSize = contacts.length();

                        RandomPermutation rp = new RandomPermutation(0, resultSize - 1);
                        JSONObject c1 = contacts.getJSONObject(rp.GetRandom());

                        String name1 = c1.getString("name");
                        String image_path1 = c1.getString("image_path");
                        String word1 = c1.getString("word");
                        contact.put("name1", name1);
                        contact1.put("word", word1);
                        contact1.put("image_path", image_path1);

                        JSONObject c2 = contacts.getJSONObject(rp.GetRandom());
                        String name2 = c2.getString("name");
                        String image_path2 = c2.getString("image_path");
                        String word2 = c2.getString("word");
                        contact.put("name2", name2);
                        contact2.put("word", word2);
                        contact2.put("image_path", image_path2);

                        JSONObject c3 = contacts.getJSONObject(rp.GetRandom());
                        String name3 = c3.getString("name");
                        String image_path3 = c3.getString("image_path");
                        String word3 = c3.getString("word");
                        contact.put("name3", name3);
                        contact3.put("word", word3);
                        contact3.put("image_path", image_path3);

                        JSONObject c4 = contacts.getJSONObject(rp.GetRandom());
                        String name4 = c4.getString("name");
                        String image_path4 = c4.getString("image_path");
                        String word4 = c4.getString("word");
                        contact.put("name4", name4);
                        contact4.put("word", word4);
                        contact4.put("image_path", image_path4);

                        contactList.add(contact1);
                        contactList.add(contact2);
                        contactList.add(contact3);
                        contactList.add(contact4);

                        for (int i = 0; i < contactList.size(); i++) {
                            Log.d("ArrayLst elements are: ", contactList.get(i).toString());
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
            }

           else  if (SampleFragment.fruits_category== true) {


                String jsonStr = sh.makeServiceCall(fruits_url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("result");
                        int resultSize = contacts.length();

                        RandomPermutation rp = new RandomPermutation(0, resultSize - 1);
                        JSONObject c1 = contacts.getJSONObject(rp.GetRandom());

                        String name1 = c1.getString("name");
                        String image_path1 = c1.getString("image_path");
                        String word1 = c1.getString("word");
                        contact.put("name1", name1);
                        contact1.put("word", word1);
                        contact1.put("image_path", image_path1);

                        JSONObject c2 = contacts.getJSONObject(rp.GetRandom());
                        String name2 = c2.getString("name");
                        String image_path2 = c2.getString("image_path");
                        String word2 = c2.getString("word");
                        contact.put("name2", name2);
                        contact2.put("word", word2);
                        contact2.put("image_path", image_path2);

                        JSONObject c3 = contacts.getJSONObject(rp.GetRandom());
                        String name3 = c3.getString("name");
                        String image_path3 = c3.getString("image_path");
                        String word3 = c3.getString("word");
                        contact.put("name3", name3);
                        contact3.put("word", word3);
                        contact3.put("image_path", image_path3);

                        JSONObject c4 = contacts.getJSONObject(rp.GetRandom());
                        String name4 = c4.getString("name");
                        String image_path4 = c4.getString("image_path");
                        String word4 = c4.getString("word");
                        contact.put("name4", name4);
                        contact4.put("word", word4);
                        contact4.put("image_path", image_path4);

                        contactList.add(contact1);
                        contactList.add(contact2);
                        contactList.add(contact3);
                        contactList.add(contact4);

                        for (int i = 0; i < contactList.size(); i++) {
                            Log.d("ArrayLst elements are: ", contactList.get(i).toString());
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
            }

           else if (SampleFragmentTwo.sports_category== true) {


                String jsonStr = sh.makeServiceCall(sports_url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("result");
                        int resultSize = contacts.length();

                        RandomPermutation rp = new RandomPermutation(0, resultSize - 1);
                        JSONObject c1 = contacts.getJSONObject(rp.GetRandom());

                        String name1 = c1.getString("name");
                        String image_path1 = c1.getString("image_path");
                        String word1 = c1.getString("word");
                        contact.put("name1", name1);
                        contact1.put("word", word1);
                        contact1.put("image_path", image_path1);

                        JSONObject c2 = contacts.getJSONObject(rp.GetRandom());
                        String name2 = c2.getString("name");
                        String image_path2 = c2.getString("image_path");
                        String word2 = c2.getString("word");
                        contact.put("name2", name2);
                        contact2.put("word", word2);
                        contact2.put("image_path", image_path2);

                        JSONObject c3 = contacts.getJSONObject(rp.GetRandom());
                        String name3 = c3.getString("name");
                        String image_path3 = c3.getString("image_path");
                        String word3 = c3.getString("word");
                        contact.put("name3", name3);
                        contact3.put("word", word3);
                        contact3.put("image_path", image_path3);

                        JSONObject c4 = contacts.getJSONObject(rp.GetRandom());
                        String name4 = c4.getString("name");
                        String image_path4 = c4.getString("image_path");
                        String word4 = c4.getString("word");
                        contact.put("name4", name4);
                        contact4.put("word", word4);
                        contact4.put("image_path", image_path4);

                        contactList.add(contact1);
                        contactList.add(contact2);
                        contactList.add(contact3);
                        contactList.add(contact4);

                        for (int i = 0; i < contactList.size(); i++) {
                            Log.d("ArrayLst elements are: ", contactList.get(i).toString());
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
            }


            else if (SampleFragmentTwo.colors_category== true) {


                String jsonStr = sh.makeServiceCall(colors_url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("result");
                        int resultSize = contacts.length();

                        RandomPermutation rp = new RandomPermutation(0, resultSize - 1);
                        JSONObject c1 = contacts.getJSONObject(rp.GetRandom());

                        String name1 = c1.getString("name");
                        String image_path1 = c1.getString("image_path");
                        String word1 = c1.getString("word");
                        contact.put("name1", name1);
                        contact1.put("word", word1);
                        contact1.put("image_path", image_path1);

                        JSONObject c2 = contacts.getJSONObject(rp.GetRandom());
                        String name2 = c2.getString("name");
                        String image_path2 = c2.getString("image_path");
                        String word2 = c2.getString("word");
                        contact.put("name2", name2);
                        contact2.put("word", word2);
                        contact2.put("image_path", image_path2);

                        JSONObject c3 = contacts.getJSONObject(rp.GetRandom());
                        String name3 = c3.getString("name");
                        String image_path3 = c3.getString("image_path");
                        String word3 = c3.getString("word");
                        contact.put("name3", name3);
                        contact3.put("word", word3);
                        contact3.put("image_path", image_path3);

                        JSONObject c4 = contacts.getJSONObject(rp.GetRandom());
                        String name4 = c4.getString("name");
                        String image_path4 = c4.getString("image_path");
                        String word4 = c4.getString("word");
                        contact.put("name4", name4);
                        contact4.put("word", word4);
                        contact4.put("image_path", image_path4);

                        contactList.add(contact1);
                        contactList.add(contact2);
                        contactList.add(contact3);
                        contactList.add(contact4);

                        for (int i = 0; i < contactList.size(); i++) {
                            Log.d("ArrayLst elements are: ", contactList.get(i).toString());
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
            }

           else if (SampleFragmentTwo.family_category==true && ControlLevelActivity.cat9==true) {
                Log.d("SAMPLEFARGMENT STATUS : " ,String.valueOf(SampleFragmentTwo.vehicles_category));
                Log.d("SAMPLEFARGMENT STATUS : " ,String.valueOf(SampleFragmentTwo.family_category));
                Log.d("SAMPLEFARGMENT STATUS : " ,String.valueOf(SampleFragment.clothes_category));


                String jsonStr = sh.makeServiceCall(family_url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("result_families");
                        int resultSize = contacts.length();

                        RandomPermutation rp = new RandomPermutation(0, resultSize - 1);
                        JSONObject c1 = contacts.getJSONObject(rp.GetRandom());

                        String name1 = c1.getString("name");
                        String image_path1 = c1.getString("image_path");
                        String word1 = c1.getString("word");
                        contact.put("name1", name1);
                        contact1.put("word", word1);
                        contact1.put("image_path", image_path1);

                        JSONObject c2 = contacts.getJSONObject(rp.GetRandom());
                        String name2 = c2.getString("name");
                        String image_path2 = c2.getString("image_path");
                        String word2 = c2.getString("word");
                        contact.put("name2", name2);
                        contact2.put("word", word2);
                        contact2.put("image_path", image_path2);

                        JSONObject c3 = contacts.getJSONObject(rp.GetRandom());
                        String name3 = c3.getString("name");
                        String image_path3 = c3.getString("image_path");
                        String word3 = c3.getString("word");
                        contact.put("name3", name3);
                        contact3.put("word", word3);
                        contact3.put("image_path", image_path3);

                        JSONObject c4 = contacts.getJSONObject(rp.GetRandom());
                        String name4 = c4.getString("name");
                        String image_path4 = c4.getString("image_path");
                        String word4 = c4.getString("word");
                        contact.put("name4", name4);
                        contact4.put("word", word4);
                        contact4.put("image_path", image_path4);

                        contactList.add(contact1);
                        contactList.add(contact2);
                        contactList.add(contact3);
                        contactList.add(contact4);

                        for (int i = 0; i < contactList.size(); i++) {
                            Log.d("ArrayLst elements are: ", contactList.get(i).toString());
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
            }

           else if (SampleFragmentTwo.shapes_category== true && ControlLevelActivity.cat10==true) {


                String jsonStr = sh.makeServiceCall(shapes_url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("result");
                        int resultSize = contacts.length();

                        RandomPermutation rp = new RandomPermutation(0, resultSize - 1);
                        JSONObject c1 = contacts.getJSONObject(rp.GetRandom());

                        String name1 = c1.getString("name");
                        String image_path1 = c1.getString("image_path");
                        String word1 = c1.getString("word");
                        contact.put("name1", name1);
                        contact1.put("word", word1);
                        contact1.put("image_path", image_path1);

                        JSONObject c2 = contacts.getJSONObject(rp.GetRandom());
                        String name2 = c2.getString("name");
                        String image_path2 = c2.getString("image_path");
                        String word2 = c2.getString("word");
                        contact.put("name2", name2);
                        contact2.put("word", word2);
                        contact2.put("image_path", image_path2);

                        JSONObject c3 = contacts.getJSONObject(rp.GetRandom());
                        String name3 = c3.getString("name");
                        String image_path3 = c3.getString("image_path");
                        String word3 = c3.getString("word");
                        contact.put("name3", name3);
                        contact3.put("word", word3);
                        contact3.put("image_path", image_path3);

                        JSONObject c4 = contacts.getJSONObject(rp.GetRandom());
                        String name4 = c4.getString("name");
                        String image_path4 = c4.getString("image_path");
                        String word4 = c4.getString("word");
                        contact.put("name4", name4);
                        contact4.put("word", word4);
                        contact4.put("image_path", image_path4);

                        contactList.add(contact1);
                        contactList.add(contact2);
                        contactList.add(contact3);
                        contactList.add(contact4);

                        for (int i = 0; i < contactList.size(); i++) {
                            Log.d("ArrayLst elements are: ", contactList.get(i).toString());
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
            }

           else if (SampleFragmentTwo.vehicles_category==true && ControlLevelActivity.cat11==true) {

                Log.d("SAMPLEFARGMENT STATUS : " ,String.valueOf(SampleFragmentTwo.vehicles_category));
                Log.d("SAMPLEFARGMENT STATUS : " ,String.valueOf(SampleFragmentTwo.family_category));
                Log.d("SAMPLEFARGMENT STATUS : " ,String.valueOf(SampleFragment.clothes_category));




                String jsonStr = sh.makeServiceCall(vehicles_url);

                Log.e(TAG, "Response from url: " + jsonStr);


                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("result_vehicles");
                        int resultSize = contacts.length();

                        RandomPermutation rp = new RandomPermutation(0, resultSize - 1);
                        JSONObject c1 = contacts.getJSONObject(rp.GetRandom());

                        String name1 = c1.getString("name");
                        String image_path1 = c1.getString("image_path");
                        String word1 = c1.getString("word");
                        contact.put("name1", name1);
                        contact1.put("word", word1);
                        contact1.put("image_path", image_path1);

                        JSONObject c2 = contacts.getJSONObject(rp.GetRandom());
                        String name2 = c2.getString("name");
                        String image_path2 = c2.getString("image_path");
                        String word2 = c2.getString("word");
                        contact.put("name2", name2);
                        contact2.put("word", word2);
                        contact2.put("image_path", image_path2);

                        JSONObject c3 = contacts.getJSONObject(rp.GetRandom());
                        String name3 = c3.getString("name");
                        String image_path3 = c3.getString("image_path");
                        String word3 = c3.getString("word");
                        contact.put("name3", name3);
                        contact3.put("word", word3);
                        contact3.put("image_path", image_path3);

                        JSONObject c4 = contacts.getJSONObject(rp.GetRandom());
                        String name4 = c4.getString("name");
                        String image_path4 = c4.getString("image_path");
                        String word4 = c4.getString("word");
                        contact.put("name4", name4);
                        contact4.put("word", word4);
                        contact4.put("image_path", image_path4);

                        contactList.add(contact1);
                        contactList.add(contact2);
                        contactList.add(contact3);
                        contactList.add(contact4);

                        for (int i = 0; i < contactList.size(); i++) {
                            Log.d("ArrayLst elements are: ", contactList.get(i).toString());
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
            }

            else if (SampleFragmentTwo.places_category== true && ControlLevelActivity.cat12==true) {


                String jsonStr = sh.makeServiceCall(places_url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("result");
                        int resultSize = contacts.length();

                        RandomPermutation rp = new RandomPermutation(0, resultSize - 1);
                        JSONObject c1 = contacts.getJSONObject(rp.GetRandom());

                        String name1 = c1.getString("name");
                        String image_path1 = c1.getString("image_path");
                        String word1 = c1.getString("word");
                        contact.put("name1", name1);
                        contact1.put("word", word1);
                        contact1.put("image_path", image_path1);

                        JSONObject c2 = contacts.getJSONObject(rp.GetRandom());
                        String name2 = c2.getString("name");
                        String image_path2 = c2.getString("image_path");
                        String word2 = c2.getString("word");
                        contact.put("name2", name2);
                        contact2.put("word", word2);
                        contact2.put("image_path", image_path2);

                        JSONObject c3 = contacts.getJSONObject(rp.GetRandom());
                        String name3 = c3.getString("name");
                        String image_path3 = c3.getString("image_path");
                        String word3 = c3.getString("word");
                        contact.put("name3", name3);
                        contact3.put("word", word3);
                        contact3.put("image_path", image_path3);

                        JSONObject c4 = contacts.getJSONObject(rp.GetRandom());
                        String name4 = c4.getString("name");
                        String image_path4 = c4.getString("image_path");
                        String word4 = c4.getString("word");
                        contact.put("name4", name4);
                        contact4.put("word", word4);
                        contact4.put("image_path", image_path4);

                        contactList.add(contact1);
                        contactList.add(contact2);
                        contactList.add(contact3);
                        contactList.add(contact4);

                        for (int i = 0; i < contactList.size(); i++) {
                            Log.d("ArrayLst elements are: ", contactList.get(i).toString());
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
            }

           else if (SampleFragmentThree.animals_category== true) {
                String jsonStr = sh.makeServiceCall(animals_url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("result");
                        int resultSize = contacts.length();

                        RandomPermutation rp = new RandomPermutation(0, resultSize - 1);
                        JSONObject c1 = contacts.getJSONObject(rp.GetRandom());

                        String name1 = c1.getString("name");
                        String image_path1 = c1.getString("image_path");
                        String word1 = c1.getString("word");
                        contact.put("name1", name1);
                        contact1.put("word", word1);
                        contact1.put("image_path", image_path1);

                        JSONObject c2 = contacts.getJSONObject(rp.GetRandom());
                        String name2 = c2.getString("name");
                        String image_path2 = c2.getString("image_path");
                        String word2 = c2.getString("word");
                        contact.put("name2", name2);
                        contact2.put("word", word2);
                        contact2.put("image_path", image_path2);

                        JSONObject c3 = contacts.getJSONObject(rp.GetRandom());
                        String name3 = c3.getString("name");
                        String image_path3 = c3.getString("image_path");
                        String word3 = c3.getString("word");
                        contact.put("name3", name3);
                        contact3.put("word", word3);
                        contact3.put("image_path", image_path3);

                        JSONObject c4 = contacts.getJSONObject(rp.GetRandom());
                        String name4 = c4.getString("name");
                        String image_path4 = c4.getString("image_path");
                        String word4 = c4.getString("word");
                        contact.put("name4", name4);
                        contact4.put("word", word4);
                        contact4.put("image_path", image_path4);

                        contactList.add(contact1);
                        contactList.add(contact2);
                        contactList.add(contact3);
                        contactList.add(contact4);

                        for (int i = 0; i < contactList.size(); i++) {
                            Log.d("ArrayLst elements are: ", contactList.get(i).toString());
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
            }

           else if (SampleFragmentThree.adjectives_category== true) {
                String jsonStr = sh.makeServiceCall(adjectives_url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("result");
                        int resultSize = contacts.length();

                        RandomPermutation rp = new RandomPermutation(0, resultSize - 1);
                        JSONObject c1 = contacts.getJSONObject(rp.GetRandom());

                        String name1 = c1.getString("name");
                        String image_path1 = c1.getString("image_path");
                        String word1 = c1.getString("word");
                        contact.put("name1", name1);
                        contact1.put("word", word1);
                        contact1.put("image_path", image_path1);

                        JSONObject c2 = contacts.getJSONObject(rp.GetRandom());
                        String name2 = c2.getString("name");
                        String image_path2 = c2.getString("image_path");
                        String word2 = c2.getString("word");
                        contact.put("name2", name2);
                        contact2.put("word", word2);
                        contact2.put("image_path", image_path2);

                        JSONObject c3 = contacts.getJSONObject(rp.GetRandom());
                        String name3 = c3.getString("name");
                        String image_path3 = c3.getString("image_path");
                        String word3 = c3.getString("word");
                        contact.put("name3", name3);
                        contact3.put("word", word3);
                        contact3.put("image_path", image_path3);

                        JSONObject c4 = contacts.getJSONObject(rp.GetRandom());
                        String name4 = c4.getString("name");
                        String image_path4 = c4.getString("image_path");
                        String word4 = c4.getString("word");
                        contact.put("name4", name4);
                        contact4.put("word", word4);
                        contact4.put("image_path", image_path4);

                        contactList.add(contact1);
                        contactList.add(contact2);
                        contactList.add(contact3);
                        contactList.add(contact4);

                        for (int i = 0; i < contactList.size(); i++) {
                            Log.d("ArrayLst elements are: ", contactList.get(i).toString());
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
            }

           else if (SampleFragmentThree.numbers_category== true) {
                String jsonStr = sh.makeServiceCall(numbers_url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("result");
                        int resultSize = contacts.length();

                        RandomPermutation rp = new RandomPermutation(0, resultSize - 1);
                        JSONObject c1 = contacts.getJSONObject(rp.GetRandom());

                        String name1 = c1.getString("name");
                        String image_path1 = c1.getString("image_path");
                        String word1 = c1.getString("word");
                        contact.put("name1", name1);
                        contact1.put("word", word1);
                        contact1.put("image_path", image_path1);

                        JSONObject c2 = contacts.getJSONObject(rp.GetRandom());
                        String name2 = c2.getString("name");
                        String image_path2 = c2.getString("image_path");
                        String word2 = c2.getString("word");
                        contact.put("name2", name2);
                        contact2.put("word", word2);
                        contact2.put("image_path", image_path2);

                        JSONObject c3 = contacts.getJSONObject(rp.GetRandom());
                        String name3 = c3.getString("name");
                        String image_path3 = c3.getString("image_path");
                        String word3 = c3.getString("word");
                        contact.put("name3", name3);
                        contact3.put("word", word3);
                        contact3.put("image_path", image_path3);

                        JSONObject c4 = contacts.getJSONObject(rp.GetRandom());
                        String name4 = c4.getString("name");
                        String image_path4 = c4.getString("image_path");
                        String word4 = c4.getString("word");
                        contact.put("name4", name4);
                        contact4.put("word", word4);
                        contact4.put("image_path", image_path4);

                        contactList.add(contact1);
                        contactList.add(contact2);
                        contactList.add(contact3);
                        contactList.add(contact4);

                        for (int i = 0; i < contactList.size(); i++) {
                            Log.d("ArrayLst elements are: ", contactList.get(i).toString());
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
            }

           else if (SampleFragmentThree.countries_category== true) {
                String jsonStr = sh.makeServiceCall(countries_url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("result");
                        int resultSize = contacts.length();

                        RandomPermutation rp = new RandomPermutation(0, resultSize - 1);
                        JSONObject c1 = contacts.getJSONObject(rp.GetRandom());

                        String name1 = c1.getString("name");
                        String image_path1 = c1.getString("image_path");
                        String word1 = c1.getString("word");
                        contact.put("name1", name1);
                        contact1.put("word", word1);
                        contact1.put("image_path", image_path1);

                        JSONObject c2 = contacts.getJSONObject(rp.GetRandom());
                        String name2 = c2.getString("name");
                        String image_path2 = c2.getString("image_path");
                        String word2 = c2.getString("word");
                        contact.put("name2", name2);
                        contact2.put("word", word2);
                        contact2.put("image_path", image_path2);

                        JSONObject c3 = contacts.getJSONObject(rp.GetRandom());
                        String name3 = c3.getString("name");
                        String image_path3 = c3.getString("image_path");
                        String word3 = c3.getString("word");
                        contact.put("name3", name3);
                        contact3.put("word", word3);
                        contact3.put("image_path", image_path3);

                        JSONObject c4 = contacts.getJSONObject(rp.GetRandom());
                        String name4 = c4.getString("name");
                        String image_path4 = c4.getString("image_path");
                        String word4 = c4.getString("word");
                        contact.put("name4", name4);
                        contact4.put("word", word4);
                        contact4.put("image_path", image_path4);

                        contactList.add(contact1);
                        contactList.add(contact2);
                        contactList.add(contact3);
                        contactList.add(contact4);

                        for (int i = 0; i < contactList.size(); i++) {
                            Log.d("ArrayLst elements are: ", contactList.get(i).toString());
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
            }

           else if (SampleFragmentThree.weathers_category== true) {
                String jsonStr = sh.makeServiceCall(weathers_url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("result");
                        int resultSize = contacts.length();

                        RandomPermutation rp = new RandomPermutation(0, resultSize - 1);
                        JSONObject c1 = contacts.getJSONObject(rp.GetRandom());

                        String name1 = c1.getString("name");
                        String image_path1 = c1.getString("image_path");
                        String word1 = c1.getString("word");
                        contact.put("name1", name1);
                        contact1.put("word", word1);
                        contact1.put("image_path", image_path1);

                        JSONObject c2 = contacts.getJSONObject(rp.GetRandom());
                        String name2 = c2.getString("name");
                        String image_path2 = c2.getString("image_path");
                        String word2 = c2.getString("word");
                        contact.put("name2", name2);
                        contact2.put("word", word2);
                        contact2.put("image_path", image_path2);

                        JSONObject c3 = contacts.getJSONObject(rp.GetRandom());
                        String name3 = c3.getString("name");
                        String image_path3 = c3.getString("image_path");
                        String word3 = c3.getString("word");
                        contact.put("name3", name3);
                        contact3.put("word", word3);
                        contact3.put("image_path", image_path3);

                        JSONObject c4 = contacts.getJSONObject(rp.GetRandom());
                        String name4 = c4.getString("name");
                        String image_path4 = c4.getString("image_path");
                        String word4 = c4.getString("word");
                        contact.put("name4", name4);
                        contact4.put("word", word4);
                        contact4.put("image_path", image_path4);

                        contactList.add(contact1);
                        contactList.add(contact2);
                        contactList.add(contact3);
                        contactList.add(contact4);

                        for (int i = 0; i < contactList.size(); i++) {
                            Log.d("ArrayLst elements are: ", contactList.get(i).toString());
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
            }

           else if (SampleFragmentThree.jobs_category== true) {
                String jsonStr = sh.makeServiceCall(jobs_url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("result");
                        int resultSize = contacts.length();

                        RandomPermutation rp = new RandomPermutation(0, resultSize - 1);
                        JSONObject c1 = contacts.getJSONObject(rp.GetRandom());

                        String name1 = c1.getString("name");
                        String image_path1 = c1.getString("image_path");
                        String word1 = c1.getString("word");
                        contact.put("name1", name1);
                        contact1.put("word", word1);
                        contact1.put("image_path", image_path1);

                        JSONObject c2 = contacts.getJSONObject(rp.GetRandom());
                        String name2 = c2.getString("name");
                        String image_path2 = c2.getString("image_path");
                        String word2 = c2.getString("word");
                        contact.put("name2", name2);
                        contact2.put("word", word2);
                        contact2.put("image_path", image_path2);

                        JSONObject c3 = contacts.getJSONObject(rp.GetRandom());
                        String name3 = c3.getString("name");
                        String image_path3 = c3.getString("image_path");
                        String word3 = c3.getString("word");
                        contact.put("name3", name3);
                        contact3.put("word", word3);
                        contact3.put("image_path", image_path3);

                        JSONObject c4 = contacts.getJSONObject(rp.GetRandom());
                        String name4 = c4.getString("name");
                        String image_path4 = c4.getString("image_path");
                        String word4 = c4.getString("word");
                        contact.put("name4", name4);
                        contact4.put("word", word4);
                        contact4.put("image_path", image_path4);

                        contactList.add(contact1);
                        contactList.add(contact2);
                        contactList.add(contact3);
                        contactList.add(contact4);

                        for (int i = 0; i < contactList.size(); i++) {
                            Log.d("ArrayLst elements are: ", contactList.get(i).toString());
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
            }










            return null;
            }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            imageView1.setAnimation(image_anim);
            imageView2.setAnimation(image_anim);
            imageView3.setAnimation(image_anim);
            imageView4.setAnimation(image_anim);


            imageView1.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView2.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView3.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView4.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));

            List nameList = new ArrayList<>(contact.values());
            Collections.shuffle(nameList);

            textView1.setText(nameList.get(0).toString());
            textView1.setTypeface(tf1);
            textView1.setAnimation(image_anim);
            textView1.setTextSize(15);
            textView5.setTextSize(15);
            textView1.setTextColor(Color.rgb(125, 60, 152));
            textView5.setText(contactList.get(0).get("word"));
            textView5.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView1);
            textView5.setTextColor(Color.TRANSPARENT);

            textView2.setText(nameList.get(1).toString());
            textView2.setTypeface(tf1);
            textView2.setAnimation(image_anim);
            textView2.setTextColor(Color.rgb(125, 60, 152));
            textView2.setTextSize(15);
            textView6.setTextSize(15);
            textView6.setTextColor(Color.rgb(125, 60, 152));
            textView6.setText(contactList.get(1).get("word"));
            textView6.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(1).get("image_path"), imageView2);
            textView6.setTextColor(Color.TRANSPARENT);

            textView3.setText(nameList.get(2).toString());
            textView3.setTypeface(tf1);
            textView3.setAnimation(image_anim);
            textView3.setTextColor(Color.rgb(125, 60, 152));
            textView3.setTextSize(15);
            textView7.setTextSize(15);
            textView7.setTypeface(tf1);
            textView7.setTextColor(Color.rgb(125, 60, 152));
            textView7.setText(contactList.get(2).get("word"));
            imageLoader.displayImage(contactList.get(2).get("image_path"), imageView3);
            textView7.setTextColor(Color.TRANSPARENT);

            textView4.setText(nameList.get(3).toString());
            textView4.setTypeface(tf1);
            textView4.setAnimation(image_anim);
            textView4.setTextColor(Color.rgb(125, 60, 152));
            textView4.setTextSize(15);
            textView8.setText(contactList.get(3).get("word"));
            textView8.setTextSize(15);
            textView8.setTextColor(Color.rgb(125, 60, 152));
            textView8.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(3).get("image_path"), imageView4);
            textView8.setTextColor(Color.TRANSPARENT);

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
            params.add(new BasicNameValuePair("score", scoreText.getText().toString()));
            params.add(new BasicNameValuePair("level",LevelScreen.level));
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
                if (LevelScreen.isLevel1 == true) {
                    Log.d("IS IT TRUE?", "TRUE1");
                    if (MainActivity.name.equals(score_list.get(i).get("name")) && score_list.get(i).get("level").equals("1")) {
                        Log.d("IS IT TRUE?", "TRUE2");
                        if (Integer.valueOf(scoreText.getText().toString()) > Integer.valueOf(score_list.get(i).get("score").toString())
                                ) {
                            Log.d("IS IT TRUE?", "TRUE");
                            high_score_result.setText(String.valueOf(scoreText.getText()));
                        } else {
                            high_score_result.setText(String.valueOf(score_list.get(i).get("score")));
                            // Log.d("4IS IT TRUE?", "TRUE3");

                        }

                    }


                }

            }}}



                }











