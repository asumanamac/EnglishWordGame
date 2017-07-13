package example.seniordesign_project;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static example.seniordesign_project.Animationss.anticlokwise;
import static example.seniordesign_project.Animationss.back;
import static example.seniordesign_project.Animationss.buttonSound;
import static example.seniordesign_project.Animationss.close;
import static example.seniordesign_project.Animationss.fail;
import static example.seniordesign_project.Animationss.game_over_slide;
import static example.seniordesign_project.Animationss.game_over_sound;
import static example.seniordesign_project.Animationss.image_anim;
import static example.seniordesign_project.Animationss.open;
import static example.seniordesign_project.Animationss.success_background_sound;
import static example.seniordesign_project.Animationss.sucess;

public class ThirdLevelActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    ImageLoader imageLoader;
    public static TextView scoreText;
    TextView word1, word2, word3, word4,word5,word6,word7,word8,word9,word10,word11,word12,word13,word14,
            word15,word16,word17,word18,word19,word20,word21,word22,word23,word24, drag1, drag2, drag3,drag4,
            drag5,drag6,drag7, drag8,drag9,drag10,drag11,drag12,drag13,drag14,drag15,drag16,drag17,drag18,
            drag19,drag20,drag21,drag22,drag23,drag24,timerText, scoreSucess, helpText;

    ImageView imageView1, imageView2, imageView3, imageView4,imageView5,imageView6,imageView7,imageView8,
            imageView9,imageView10,imageView11,imageView12,imageView13,imageView14,imageView15,imageView16,
            imageView17,imageView18,imageView19,imageView20,imageView21,imageView22,imageView23,imageView24,
            right1,right2, right3;

    public static TextView scoreSucResult;
    FloatingActionButton setting, volume, out, home;
    private static String url = "http://192.168.2.11:8080/android_login_api/images/getAllImages_music.php";
    Typeface tf1;
    Score score;
    boolean match1 = false, match2 = false, match3 = false, match4 = false;
    boolean match5 = false, match6 = false, match7 = false, match8 = false;
    boolean match9 = false, match10 = false, match11 = false, match12 = false;
    boolean match13 = false, match14 = false, match15 = false, match16 = false;
    boolean match17 = false, match18 = false, match19 = false, match20 = false;
    boolean match21 = false, match22 = false, match23 = false, match24 = false;
    public static boolean isClick = false;
    Dialog game_over_dialog, success_screen_dialog, information_box;
    PostClass post = new PostClass();
    String URL_POST = "http://192.168.2.11:8080/android_login_api/score.php";

    Handler handler;
    public static TextView total_score_result, total_Score_text, wrong_text, wrong_result, correct_text, correct_result;
    public static TextView high_score_result, high_score_text;
    public static TextView level_text, level_result;
    public static ImageButton play_button, cancel_button, continue_button;
    private static final String PREFS_LAST_IMG = "prefs_last_img";
    private SharedPreferences mPreferences;

    public static CountDownTimer countDownTimer;
    public static int count = 3, correct = 0, wrong = 0;
    public static String level;
    public static boolean isPaused = false, game_over = false, game_success = false;
    public static boolean isCanceled = false;
    public long timeRemaining = 0;
   static  boolean isGameOver=false;

    RelativeLayout relativeLayout,relativeLayout2,relativeLayout3,relativeLayout4,relativeLayout5,relativeLayout6;
    ArrayList<HashMap<String, String>> contactList, nameList,second_list;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_level);

        relativeLayout = (RelativeLayout) findViewById(R.id.relative1);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.relative2);
        relativeLayout3 = (RelativeLayout) findViewById(R.id.relative3);
        relativeLayout4 = (RelativeLayout) findViewById(R.id.relative4);
        relativeLayout5 = (RelativeLayout) findViewById(R.id.relative5);
        relativeLayout6 = (RelativeLayout) findViewById(R.id.relative6);
        tf1 = Typeface.createFromAsset(getAssets(), "fonts/BalooBhai-Regular.ttf");

        setting = (FloatingActionButton) findViewById(R.id.floatmain);
        volume = (FloatingActionButton) findViewById(R.id.volumeon);
        out = (FloatingActionButton) findViewById(R.id.out);
        home = (FloatingActionButton) findViewById(R.id.home);
        right1 = (ImageView) findViewById(R.id.imageView8);
        right2 = (ImageView) findViewById(R.id.imageView9);
        right3 = (ImageView) findViewById(R.id.imageView6);

        word1 = (TextView) findViewById(R.id.word1);
        word2 = (TextView) findViewById(R.id.word2);
        word3 = (TextView) findViewById(R.id.word3);
        word4 = (TextView) findViewById(R.id.word4);
        word5 = (TextView) findViewById(R.id.word5);
        word6 = (TextView) findViewById(R.id.word6);
        word7 = (TextView) findViewById(R.id.word7);
        word8 = (TextView) findViewById(R.id.word8);
        word9 = (TextView) findViewById(R.id.word9);
        word10 = (TextView) findViewById(R.id.word10);
        word11 = (TextView) findViewById(R.id.word11);
        word12 = (TextView) findViewById(R.id.word12);
        word13 = (TextView) findViewById(R.id.word13);
        word14 = (TextView) findViewById(R.id.word14);
        word15 = (TextView) findViewById(R.id.word15);
        word16 = (TextView) findViewById(R.id.word16);
        word17 = (TextView) findViewById(R.id.word17);
        word18 = (TextView) findViewById(R.id.word18);
        word19 = (TextView) findViewById(R.id.word19);
        word20 = (TextView) findViewById(R.id.word20);
        word21 = (TextView) findViewById(R.id.word21);
        word22 = (TextView) findViewById(R.id.word22);
        word23 = (TextView) findViewById(R.id.word23);
        word24 = (TextView) findViewById(R.id.word24);

        drag1 = (TextView) findViewById(R.id.match_word1);
        drag2 = (TextView) findViewById(R.id.match_word2);
        drag3 = (TextView) findViewById(R.id.match_word3);
        drag4 = (TextView) findViewById(R.id.match_word4);
        drag5 = (TextView) findViewById(R.id.match_word5);
        drag6 = (TextView) findViewById(R.id.match_word6);
        drag7 = (TextView) findViewById(R.id.match_word7);
        drag8 = (TextView) findViewById(R.id.match_word8);
        drag9 = (TextView) findViewById(R.id.match_word9);
        drag10 = (TextView) findViewById(R.id.match_word10);
        drag11 = (TextView) findViewById(R.id.match_word11);
        drag12 = (TextView) findViewById(R.id.match_word12);
        drag13 = (TextView) findViewById(R.id.match_word13);
        drag14 = (TextView) findViewById(R.id.match_word14);
        drag15 = (TextView) findViewById(R.id.match_word15);
        drag16 = (TextView) findViewById(R.id.match_word16);
        drag17 = (TextView) findViewById(R.id.match_word17);
        drag18 = (TextView) findViewById(R.id.match_word18);
        drag19 = (TextView) findViewById(R.id.match_word19);
        drag20 = (TextView) findViewById(R.id.match_word20);
        drag21 = (TextView) findViewById(R.id.match_word21);
        drag22 = (TextView) findViewById(R.id.match_word22);
        drag23 = (TextView) findViewById(R.id.match_word23);
        drag24 = (TextView) findViewById(R.id.match_word24);


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
        imageView5 = (ImageView) findViewById(R.id.image5);
        imageView6 = (ImageView) findViewById(R.id.image6);
        imageView7 = (ImageView) findViewById(R.id.image7);
        imageView8 = (ImageView) findViewById(R.id.image8);
        imageView9 = (ImageView) findViewById(R.id.image9);
        imageView10 = (ImageView) findViewById(R.id.image10);
        imageView11 = (ImageView) findViewById(R.id.image11);
        imageView12 = (ImageView) findViewById(R.id.image12);
        imageView13 = (ImageView) findViewById(R.id.image13);
        imageView14 = (ImageView) findViewById(R.id.image14);
        imageView15 = (ImageView) findViewById(R.id.image15);
        imageView16 = (ImageView) findViewById(R.id.image16);
        imageView17 = (ImageView) findViewById(R.id.image17);
        imageView18 = (ImageView) findViewById(R.id.image18);
        imageView19 = (ImageView) findViewById(R.id.image19);
        imageView20 = (ImageView) findViewById(R.id.image20);
        imageView21 = (ImageView) findViewById(R.id.image21);
        imageView22 = (ImageView) findViewById(R.id.image22);
        imageView23 = (ImageView) findViewById(R.id.image23);
        imageView24 = (ImageView) findViewById(R.id.image24);


        contactList = new ArrayList<>();
        nameList = new ArrayList<>();
        second_list = new ArrayList();


        new GetContacts().execute();

        drag1.setOnDragListener(new ChoiceDragListener());
        word1.setOnTouchListener(new ChoiceTouchListener());

        drag2.setOnDragListener(new ChoiceDragListener());
        word2.setOnTouchListener(new ChoiceTouchListener());

        drag3.setOnDragListener(new ChoiceDragListener());
        word3.setOnTouchListener(new ChoiceTouchListener());

        drag4.setOnDragListener(new ChoiceDragListener());
        word4.setOnTouchListener(new ChoiceTouchListener());

        drag5.setOnDragListener(new ChoiceDragListener());
        word5.setOnTouchListener(new ChoiceTouchListener());

        drag6.setOnDragListener(new ChoiceDragListener());
        word6.setOnTouchListener(new ChoiceTouchListener());

        drag7.setOnDragListener(new ChoiceDragListener());
        word7.setOnTouchListener(new ChoiceTouchListener());

        drag8.setOnDragListener(new ChoiceDragListener());
        word8.setOnTouchListener(new ChoiceTouchListener());

        drag9.setOnDragListener(new ChoiceDragListener());
        word9.setOnTouchListener(new ChoiceTouchListener());

        drag10.setOnDragListener(new ChoiceDragListener());
        word10.setOnTouchListener(new ChoiceTouchListener());

        drag11.setOnDragListener(new ChoiceDragListener());
        word11.setOnTouchListener(new ChoiceTouchListener());

        drag12.setOnDragListener(new ChoiceDragListener());
        word12.setOnTouchListener(new ChoiceTouchListener());

        drag13.setOnDragListener(new ChoiceDragListener());
        word13.setOnTouchListener(new ChoiceTouchListener());

        drag14.setOnDragListener(new ChoiceDragListener());
        word14.setOnTouchListener(new ChoiceTouchListener());

        drag15.setOnDragListener(new ChoiceDragListener());
        word15.setOnTouchListener(new ChoiceTouchListener());

        drag16.setOnDragListener(new ChoiceDragListener());
        word16.setOnTouchListener(new ChoiceTouchListener());

        drag17.setOnDragListener(new ChoiceDragListener());
        word17.setOnTouchListener(new ChoiceTouchListener());

        drag18.setOnDragListener(new ChoiceDragListener());
        word18.setOnTouchListener(new ChoiceTouchListener());

        drag19.setOnDragListener(new ChoiceDragListener());
        word19.setOnTouchListener(new ChoiceTouchListener());

        drag20.setOnDragListener(new ChoiceDragListener());
        word20.setOnTouchListener(new ChoiceTouchListener());

        drag21.setOnDragListener(new ChoiceDragListener());
        word21.setOnTouchListener(new ChoiceTouchListener());

        drag22.setOnDragListener(new ChoiceDragListener());
        word22.setOnTouchListener(new ChoiceTouchListener());

        drag23.setOnDragListener(new ChoiceDragListener());
        word23.setOnTouchListener(new ChoiceTouchListener());

        drag24.setOnDragListener(new ChoiceDragListener());
        word24.setOnTouchListener(new ChoiceTouchListener());


        setting.setOnClickListener(new FloatButtonAction());

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();

        Animationss anim=new Animationss(getApplicationContext());

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
        game_over_dialog = new Dialog(ThirdLevelActivity.this);
        success_screen_dialog = new Dialog(ThirdLevelActivity.this);
        information_box = new Dialog(ThirdLevelActivity.this);
        scoreText.setText("" + Score.resetScore());


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
                Intent mStartActivity = new Intent(ThirdLevelActivity.this, MainActivity.class);
                int mPendingIntentId = 123456;
                PendingIntent mPendingIntent = PendingIntent.getActivity(ThirdLevelActivity.this, mPendingIntentId, mStartActivity,
                        PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager) ThirdLevelActivity.this.getSystemService(Context.ALARM_SERVICE);
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

        new Handler().postDelayed(new Runnable() {
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
                        if (isPaused || isCanceled) {
                            //If user requested to pause or cancel the count down timer
                            cancel();
                        } else {
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

        }, 4000);


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
                isOpen = false;


            }

            if (buttonSound.isPlaying()) {
                buttonSound.stop();
            } else {
                buttonSound.start();
                home.startAnimation(open);
                out.startAnimation(open);
                volume.startAnimation(open);
                setting.startAnimation(open);
                home.setClickable(true);
                out.setClickable(true);
                volume.setClickable(true);
                isOpen = true;

            }

            volume.setVisibility(View.INVISIBLE);
            home.setVisibility(View.INVISIBLE);
            out.setVisibility(View.INVISIBLE);

        }
    }

    public void startCountdownTimer(final TextView count_timer) {
        countDownTimer = new CountDownTimer(35000, 1500) {

            public void onTick(long millisUntilFinished) {

                if (isPaused == true || isCanceled == true) {
                    //If the user request to cancel or paused the
                    //CountDownTimer we will cancel the current instance
                    cancel();
                } else {
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
                Intent mStartActivity = new Intent(ThirdLevelActivity.this, MainActivity.class);
                startActivity(mStartActivity);
                System.exit(0);
                scoreText.setText("" + Score.resetScore());

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

    public void gameOver() {
        if (back.isPlaying()) {
            back.stop();
        }

        countDownTimer.cancel();
        game_over = true;

        LevelScreen.isLevel3=true;
        game_over_dialog.setContentView(R.layout.activity_game_over);

        total_score_result = (TextView) game_over_dialog.findViewById(R.id.total_score_result);
        total_Score_text = (TextView) game_over_dialog.findViewById(R.id.total_score_text);

        high_score_result = (TextView) game_over_dialog.findViewById(R.id.high_score_result);
        high_score_text = (TextView) game_over_dialog.findViewById(R.id.high_score_text);

        level_result = (TextView) game_over_dialog.findViewById(R.id.level_result);
        level_text = (TextView) game_over_dialog.findViewById(R.id.level_text);

        play_button = (ImageButton) game_over_dialog.findViewById(R.id.play_button);
        cancel_button = (ImageButton) game_over_dialog.findViewById(R.id.cancel_button);

        level = LevelScreen.level;

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
        game_over_dialog.show();
        game_over_sound.start();


        new ThirdLevelActivity.saveScore().execute();

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
                if (game_over_sound.isPlaying())
                    game_over_sound.stop();
                buttonSound.start();
                scoreText.setText("" + Score.resetScore());
                Intent i = new Intent(ThirdLevelActivity.this,LevelScreen.class);
                startActivity(i);

                finish();
                FourthLevelActivity.isGameOver=false;
                isGameOver=true;
                count = 3;
                correct = 0;
                wrong = 0;



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
                if (game_over_sound.isPlaying())
                    game_over_sound.stop();
                buttonSound.start();
                timerText.setText("");
                scoreText.setText("" + Score.resetScore());
                back.stop();
                wrong = 0;
                correct = 0;

                Fragment mFragment = new SampleFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_third_level, mFragment).commit();
                game_over_dialog.dismiss();


            }
        });


    }

    public void successScreen() {

        if (back.isPlaying()) {
            back.stop();
        }

        countDownTimer.cancel();
        game_success = true;

        success_screen_dialog.setContentView(R.layout.activity_success_screen);

        level = LevelScreen.level;

        wrong_text = (TextView) success_screen_dialog.findViewById(R.id.wrong_text);
        wrong_result = (TextView) success_screen_dialog.findViewById(R.id.wrong_result);
        correct_text = (TextView) success_screen_dialog.findViewById(R.id.correct_text);
        correct_result = (TextView) success_screen_dialog.findViewById(R.id.correct_result);
        scoreSucess = (TextView) success_screen_dialog.findViewById(R.id.scoreSucText);
        total_score_result = (TextView) success_screen_dialog.findViewById(R.id.scoreSucResult);

        continue_button = (ImageButton) success_screen_dialog.findViewById(R.id.continue_button);
        cancel_button = (ImageButton) success_screen_dialog.findViewById(R.id.cancel_button);

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
        total_score_result.setTextSize(15);
        total_score_result.setTypeface(tf1);

        correct_result.setText(correct + "");
        wrong_result.setText(wrong + "");
        total_score_result.setText(scoreText.getText().toString());

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
                if (success_background_sound.isPlaying())
                    success_background_sound.stop();
                buttonSound.start();
                isClick = true;
                Intent intent = new Intent(ThirdLevelActivity.this, LevelScreen.class);
                startActivity(intent);
                scoreText.setText("" + Score.resetScore());
                finish();
                FourthLevelActivity.isGameOver=false;
                count = 3;
                correct = 0;
                wrong = 0;


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
                if (success_background_sound.isPlaying())
                    success_background_sound.stop();
                buttonSound.start();

                timerText.setText("");
                scoreText.setText("" + Score.resetScore());
                back.stop();

                wrong = 0;
                correct = 0;

                Fragment mFragment = new SampleFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_third_level, mFragment).commit();
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


                    TextView dropTarget = (TextView) v;


                    if (event.getLocalState() == word1) {
                        if (dropTarget == drag1 &&
                                dropTarget.getText().toString().equals(word1.getText().toString())) {


                            sucess.start();

                            imageView1.setImageResource(R.drawable.purple);
                            imageView1.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word1.setVisibility(View.INVISIBLE);
                            word1.clearAnimation();
                            drag1.setTextColor(Color.rgb(125, 60, 152));
                            drag1.setText("");
                            drag1.append(word1.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match1 = true;
                        } else if (dropTarget == drag2 &&
                                dropTarget.getText().toString().equals(word1.getText().toString())) {

                            sucess.start();
                            imageView2.setImageResource(R.drawable.purple);
                            imageView2.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word1.clearAnimation();
                            word1.setVisibility(View.INVISIBLE);
                            drag2.setTextColor(Color.rgb(125, 60, 152));
                            drag2.setText("");
                            dropTarget.append(word1.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match1 = true;
                        } else if (dropTarget == drag3 &&
                                dropTarget.getText().toString().equals(word1.getText().toString())) {

                            sucess.start();
                            imageView3.setImageResource(R.drawable.purple);
                            imageView3.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word1.clearAnimation();
                            word1.setVisibility(View.INVISIBLE);
                            drag3.setTextColor(Color.rgb(125, 60, 152));
                            drag3.setText("");
                            dropTarget.append(word1.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match1 = true;
                        } else if (dropTarget == drag4 &&
                                dropTarget.getText().toString().equals(word1.getText().toString())) {

                            sucess.start();
                            imageView4.setImageResource(R.drawable.purple);
                            imageView4.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word1.clearAnimation();
                            word1.setVisibility(View.INVISIBLE);
                            drag4.setTextColor(Color.rgb(125, 60, 152));
                            drag4.setText("");
                            dropTarget.append(word1.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match1 = true;
                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();
                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match1 = false;


                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);

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
                        if (dropTarget == drag1 &&
                                dropTarget.getText().toString().equals(word2.getText().toString())) {

                            sucess.start();
                            imageView1.setImageResource(R.drawable.purple);
                            imageView1.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word2.clearAnimation();
                            word2.setVisibility(View.INVISIBLE);
                            drag1.setTextColor(Color.rgb(125, 60, 152));
                            drag1.setText("");
                            dropTarget.append(word2.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match2 = true;


                        } else if (dropTarget == drag2 &&
                                dropTarget.getText().toString().equals(word2.getText().toString())) {

                            sucess.start();
                            imageView2.setImageResource(R.drawable.purple);
                            imageView2.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word2.clearAnimation();
                            word2.setVisibility(View.INVISIBLE);
                            drag2.setTextColor(Color.rgb(125, 60, 152));
                            drag2.setText("");
                            dropTarget.append(word2.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match2 = true;


                        } else if (dropTarget == drag3 &&
                                dropTarget.getText().toString().equals(word2.getText().toString())) {

                            sucess.start();
                            imageView3.setImageResource(R.drawable.purple);
                            imageView3.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word2.clearAnimation();
                            word2.setVisibility(View.INVISIBLE);
                            drag3.setTextColor(Color.rgb(125, 60, 152));
                            drag3.setText("");
                            dropTarget.append(word2.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match2 = true;

                        } else if (dropTarget == drag4 &&
                                dropTarget.getText().toString().equals(word2.getText().toString())) {

                            sucess.start();
                            imageView4.setImageResource(R.drawable.purple);
                            imageView4.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word2.clearAnimation();
                            word2.setVisibility(View.INVISIBLE);
                            drag4.setTextColor(Color.rgb(125, 60, 152));
                            drag4.setText("");
                            dropTarget.append(word2.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match2 = true;
                        } else {

                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match2 = false;


                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);


                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);

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
                        if (dropTarget == drag1 &&
                                dropTarget.getText().toString().equals(word3.getText().toString())) {

                            sucess.start();
                            imageView1.setImageResource(R.drawable.purple);
                            imageView1.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word3.clearAnimation();
                            word3.setVisibility(View.INVISIBLE);
                            drag1.setTextColor(Color.rgb(125, 60, 152));
                            drag1.setText("");
                            dropTarget.append(word3.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match3 = true;

                        } else if (dropTarget == drag2 &&
                                dropTarget.getText().toString().equals(word3.getText().toString())) {

                            sucess.start();
                            imageView2.setImageResource(R.drawable.purple);
                            imageView2.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word3.clearAnimation();
                            word3.setVisibility(View.INVISIBLE);
                            drag2.setTextColor(Color.rgb(125, 60, 152));
                            drag2.setText("");
                            dropTarget.append(word3.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match3 = true;

                        } else if (dropTarget == drag3 &&
                                dropTarget.getText().toString().equals(word3.getText().toString())) {

                            sucess.start();
                            imageView3.setImageResource(R.drawable.purple);
                            imageView3.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word3.clearAnimation();
                            word3.setVisibility(View.INVISIBLE);
                            drag3.setTextColor(Color.rgb(125, 60, 152));
                            drag3.setText("");
                            dropTarget.append(word3.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match3 = true;

                        } else if (dropTarget == drag4 &&
                                dropTarget.getText().toString().equals(word3.getText().toString())) {

                            sucess.start();
                            imageView4.setImageResource(R.drawable.purple);
                            imageView4.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word3.clearAnimation();
                            word3.setVisibility(View.INVISIBLE);
                            drag4.setTextColor(Color.rgb(125, 60, 152));
                            drag4.setText("");
                            dropTarget.append(word3.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match3 = true;

                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();
                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match3 = false;

                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);

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
                        if (dropTarget == drag1 &&
                                dropTarget.getText().toString().equals(word4.getText().toString())) {

                            sucess.start();
                            imageView1.setImageResource(R.drawable.purple);
                            imageView1.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word4.clearAnimation();
                            word4.setVisibility(View.INVISIBLE);
                            drag1.setTextColor(Color.rgb(125, 60, 152));
                            drag1.setText("");
                            dropTarget.append(word4.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match4 = true;
                        } else if (dropTarget == drag2 &&
                                dropTarget.getText().toString().equals(word4.getText().toString())) {

                            sucess.start();
                            imageView2.setImageResource(R.drawable.purple);
                            imageView2.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word4.clearAnimation();
                            word4.setVisibility(View.INVISIBLE);
                            drag2.setTextColor(Color.rgb(125, 60, 152));
                            drag2.setText("");
                            dropTarget.append(word4.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match4 = true;

                        } else if (dropTarget == drag3 &&
                                dropTarget.getText().toString().equals(word4.getText().toString())) {

                            sucess.start();
                            imageView3.setImageResource(R.drawable.purple);
                            imageView3.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word4.clearAnimation();
                            word4.setVisibility(View.INVISIBLE);
                            drag3.setTextColor(Color.rgb(125, 60, 152));
                            drag3.setText("");
                            dropTarget.append(word4.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match4 = true;

                        } else if (dropTarget == drag4 &&
                                dropTarget.getText().toString().equals(word4.getText().toString())) {

                            sucess.start();
                            imageView4.setImageResource(R.drawable.purple);
                            imageView4.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word4.clearAnimation();
                            word4.setVisibility(View.INVISIBLE);
                            drag4.setTextColor(Color.rgb(125, 60, 152));
                            drag4.setText("");
                            dropTarget.append(word4.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match4 = true;

                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match4 = false;

                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);

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


                    if (event.getLocalState() == word5) {
                        if (dropTarget == drag5 &&
                                dropTarget.getText().toString().equals(word5.getText().toString())) {

                            sucess.start();
                            imageView5.setImageResource(R.drawable.purple);
                            imageView5.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word5.clearAnimation();
                            word5.setVisibility(View.INVISIBLE);
                            drag5.setTextColor(Color.rgb(125, 60, 152));
                            drag5.setText("");
                            dropTarget.append(word5.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match5 = true;
                        } else if (dropTarget == drag6 &&
                                dropTarget.getText().toString().equals(word5.getText().toString())) {

                            sucess.start();
                            imageView6.setImageResource(R.drawable.purple);
                            imageView6.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word5.clearAnimation();
                            word5.setVisibility(View.INVISIBLE);
                            drag6.setTextColor(Color.rgb(125, 60, 152));
                            drag6.setText("");
                            dropTarget.append(word5.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match5 = true;

                        } else if (dropTarget == drag7 &&
                                dropTarget.getText().toString().equals(word5.getText().toString())) {

                            sucess.start();
                            imageView7.setImageResource(R.drawable.purple);
                            imageView7.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word5.clearAnimation();
                            word5.setVisibility(View.INVISIBLE);
                            drag7.setTextColor(Color.rgb(125, 60, 152));
                            drag7.setText("");
                            dropTarget.append(word5.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match5 = true;

                        } else if (dropTarget == drag8 &&
                                dropTarget.getText().toString().equals(word5.getText().toString())) {

                            sucess.start();
                            imageView8.setImageResource(R.drawable.purple);
                            imageView8.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word5.clearAnimation();
                            word5.setVisibility(View.INVISIBLE);
                            drag8.setTextColor(Color.rgb(125, 60, 152));
                            drag8.setText("");
                            dropTarget.append(word5.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match5 = true;

                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match5 = false;

                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);

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

                    if (event.getLocalState() == word6) {
                        if (dropTarget == drag5 &&
                                dropTarget.getText().toString().equals(word6.getText().toString())) {

                            sucess.start();
                            imageView5.setImageResource(R.drawable.purple);
                            imageView5.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word6.clearAnimation();
                            word6.setVisibility(View.INVISIBLE);
                            drag5.setTextColor(Color.rgb(125, 60, 152));
                            drag5.setText("");
                            dropTarget.append(word6.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match6 = true;
                        } else if (dropTarget == drag6 &&
                                dropTarget.getText().toString().equals(word6.getText().toString())) {

                            sucess.start();
                            imageView6.setImageResource(R.drawable.purple);
                            imageView6.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word6.clearAnimation();
                            word6.setVisibility(View.INVISIBLE);
                            drag6.setTextColor(Color.rgb(125, 60, 152));
                            drag6.setText("");
                            dropTarget.append(word6.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match6 = true;

                        } else if (dropTarget == drag7 &&
                                dropTarget.getText().toString().equals(word6.getText().toString())) {

                            sucess.start();
                            imageView7.setImageResource(R.drawable.purple);
                            imageView7.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word6.clearAnimation();
                            word6.setVisibility(View.INVISIBLE);
                            drag7.setTextColor(Color.rgb(125, 60, 152));
                            drag7.setText("");
                            dropTarget.append(word6.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match6 = true;

                        } else if (dropTarget == drag8 &&
                                dropTarget.getText().toString().equals(word6.getText().toString())) {

                            sucess.start();
                            imageView8.setImageResource(R.drawable.purple);
                            imageView8.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word6.clearAnimation();
                            word6.setVisibility(View.INVISIBLE);
                            drag8.setTextColor(Color.rgb(125, 60, 152));
                            drag8.setText("");
                            dropTarget.append(word6.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match6 = true;

                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match6 = false;

                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);

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

                        if (event.getLocalState() == word7) {
                            if (dropTarget == drag5 &&
                                    dropTarget.getText().toString().equals(word7.getText().toString())) {

                                sucess.start();
                                imageView5.setImageResource(R.drawable.purple);
                                imageView5.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                                word7.clearAnimation();
                                word7.setVisibility(View.INVISIBLE);
                                drag5.setTextColor(Color.rgb(125, 60, 152));
                                drag5.setText("");
                                dropTarget.append(word7.getText().toString());
                                scoreText.setText("" + score.increaseScore());
                                dropTarget.setOnDragListener(null);
                                correct++;
                                match7 = true;
                            } else if (dropTarget == drag6 &&
                                    dropTarget.getText().toString().equals(word7.getText().toString())) {

                                sucess.start();
                                imageView6.setImageResource(R.drawable.purple);
                                imageView6.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                                word7.clearAnimation();
                                word7.setVisibility(View.INVISIBLE);
                                drag6.setTextColor(Color.rgb(125, 60, 152));
                                drag6.setText("");
                                dropTarget.append(word7.getText().toString());
                                scoreText.setText("" + score.increaseScore());
                                dropTarget.setOnDragListener(null);
                                correct++;
                                match7 = true;

                            } else if (dropTarget == drag7 &&
                                    dropTarget.getText().toString().equals(word7.getText().toString())) {

                                sucess.start();
                                imageView7.setImageResource(R.drawable.purple);
                                imageView7.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                                word7.clearAnimation();
                                word7.setVisibility(View.INVISIBLE);
                                drag7.setTextColor(Color.rgb(125, 60, 152));
                                drag7.setText("");
                                dropTarget.append(word7.getText().toString());
                                scoreText.setText("" + score.increaseScore());
                                dropTarget.setOnDragListener(null);
                                correct++;
                                match7 = true;

                            } else if (dropTarget == drag8 &&
                                    dropTarget.getText().toString().equals(word7.getText().toString())) {

                                sucess.start();
                                imageView8.setImageResource(R.drawable.purple);
                                imageView8.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                                word7.clearAnimation();
                                word7.setVisibility(View.INVISIBLE);
                                drag8.setTextColor(Color.rgb(125, 60, 152));
                                drag8.setText("");
                                dropTarget.append(word7.getText().toString());
                                scoreText.setText("" + score.increaseScore());
                                dropTarget.setOnDragListener(null);
                                correct++;
                                match7 = true;

                            } else {
                                scoreText.setText("" + score.decreaseScore());
                                fail.start();

                                count--;
                                wrong++;
                                if (wrong < 0)
                                    wrong = 0;
                                match7 = false;

                                if (count == 1) {
                                    right1.setVisibility(View.INVISIBLE);

                                }
                                if (count == 2) {
                                    right2.setVisibility(View.INVISIBLE);

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

                    if (event.getLocalState() == word8) {
                    if (dropTarget == drag5 &&
                            dropTarget.getText().toString().equals(word8.getText().toString())) {

                        sucess.start();
                        imageView5.setImageResource(R.drawable.purple);
                        imageView5.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                        word8.clearAnimation();
                        word8.setVisibility(View.INVISIBLE);
                        drag5.setTextColor(Color.rgb(125, 60, 152));
                        drag5.setText("");
                        dropTarget.append(word8.getText().toString());
                        scoreText.setText("" + score.increaseScore());
                        dropTarget.setOnDragListener(null);
                        correct++;
                        match8 = true;
                    } else if (dropTarget == drag6 &&
                            dropTarget.getText().toString().equals(word8.getText().toString())) {

                        sucess.start();
                        imageView6.setImageResource(R.drawable.purple);
                        imageView6.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                        word8.clearAnimation();
                        word8.setVisibility(View.INVISIBLE);
                        drag6.setTextColor(Color.rgb(125, 60, 152));
                        drag6.setText("");
                        dropTarget.append(word8.getText().toString());
                        scoreText.setText("" + score.increaseScore());
                        dropTarget.setOnDragListener(null);
                        correct++;
                        match8 = true;

                    } else if (dropTarget == drag7 &&
                            dropTarget.getText().toString().equals(word8.getText().toString())) {

                        sucess.start();
                        imageView7.setImageResource(R.drawable.purple);
                        imageView7.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                        word8.clearAnimation();
                        word8.setVisibility(View.INVISIBLE);
                        drag7.setTextColor(Color.rgb(125, 60, 152));
                        drag7.setText("");
                        dropTarget.append(word8.getText().toString());
                        scoreText.setText("" + score.increaseScore());
                        dropTarget.setOnDragListener(null);
                        correct++;
                        match8 = true;

                    } else if (dropTarget == drag8 &&
                            dropTarget.getText().toString().equals(word8.getText().toString())) {

                        sucess.start();
                        imageView8.setImageResource(R.drawable.purple);
                        imageView8.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                        word8.clearAnimation();
                        word8.setVisibility(View.INVISIBLE);
                        drag8.setTextColor(Color.rgb(125, 60, 152));
                        drag8.setText("");
                        dropTarget.append(word8.getText().toString());
                        scoreText.setText("" + score.increaseScore());
                        dropTarget.setOnDragListener(null);
                        correct++;
                        match8 = true;

                    } else {
                        scoreText.setText("" + score.decreaseScore());
                        fail.start();

                        count--;
                        wrong++;
                        if (wrong < 0)
                            wrong = 0;
                        match8 = false;

                        if (count == 1) {
                            right1.setVisibility(View.INVISIBLE);

                        }
                        if (count == 2) {
                            right2.setVisibility(View.INVISIBLE);

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

                    if (event.getLocalState() == word9) {
                        if (dropTarget == drag9 &&
                                dropTarget.getText().toString().equals(word9.getText().toString())) {

                            sucess.start();
                            imageView9.setImageResource(R.drawable.purple);
                            imageView9.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word9.clearAnimation();
                            word9.setVisibility(View.INVISIBLE);
                            drag9.setTextColor(Color.rgb(125, 60, 152));
                            drag9.setText("");
                            dropTarget.append(word9.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match9 = true;
                        } else if (dropTarget == drag10 &&
                                dropTarget.getText().toString().equals(word9.getText().toString())) {

                            sucess.start();
                            imageView10.setImageResource(R.drawable.purple);
                            imageView10.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word9.clearAnimation();
                            word9.setVisibility(View.INVISIBLE);
                            drag10.setTextColor(Color.rgb(125, 60, 152));
                            drag10.setText("");
                            dropTarget.append(word9.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match9 = true;

                        } else if (dropTarget == drag11 &&
                                dropTarget.getText().toString().equals(word9.getText().toString())) {

                            sucess.start();
                            imageView11.setImageResource(R.drawable.purple);
                            imageView11.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word9.clearAnimation();
                            word9.setVisibility(View.INVISIBLE);
                            drag11.setTextColor(Color.rgb(125, 60, 152));
                            drag11.setText("");
                            dropTarget.append(word9.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match9 = true;

                        } else if (dropTarget == drag12 &&
                                dropTarget.getText().toString().equals(word9.getText().toString())) {

                            sucess.start();
                            imageView12.setImageResource(R.drawable.purple);
                            imageView12.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word9.clearAnimation();
                            word9.setVisibility(View.INVISIBLE);
                            drag12.setTextColor(Color.rgb(125, 60, 152));
                            drag12.setText("");
                            dropTarget.append(word9.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match9 = true;

                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match9 = false;

                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);
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

                    if (event.getLocalState() == word10) {
                        if (dropTarget == drag9 &&
                                dropTarget.getText().toString().equals(word10.getText().toString())) {

                            sucess.start();
                            imageView9.setImageResource(R.drawable.purple);
                            imageView9.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word10.clearAnimation();
                            word10.setVisibility(View.INVISIBLE);
                            drag9.setTextColor(Color.rgb(125, 60, 152));
                            drag9.setText("");
                            dropTarget.append(word10.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match10 = true;
                        } else if (dropTarget == drag10 &&
                                dropTarget.getText().toString().equals(word10.getText().toString())) {

                            sucess.start();
                            imageView10.setImageResource(R.drawable.purple);
                            imageView10.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word10.clearAnimation();
                            word10.setVisibility(View.INVISIBLE);
                            drag10.setTextColor(Color.rgb(125, 60, 152));
                            drag10.setText("");
                            dropTarget.append(word10.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match10 = true;

                        } else if (dropTarget == drag11 &&
                                dropTarget.getText().toString().equals(word10.getText().toString())) {

                            sucess.start();
                            imageView11.setImageResource(R.drawable.purple);
                            imageView11.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word10.clearAnimation();
                            word10.setVisibility(View.INVISIBLE);
                            drag11.setTextColor(Color.rgb(125, 60, 152));
                            drag11.setText("");
                            dropTarget.append(word10.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match10 = true;

                        } else if (dropTarget == drag12 &&
                                dropTarget.getText().toString().equals(word10.getText().toString())) {

                            sucess.start();
                            imageView12.setImageResource(R.drawable.purple);
                            imageView12.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word10.clearAnimation();
                            word10.setVisibility(View.INVISIBLE);
                            drag12.setTextColor(Color.rgb(125, 60, 152));
                            drag12.setText("");
                            dropTarget.append(word10.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match10 = true;

                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match10 = false;

                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);
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

                    if (event.getLocalState() == word11) {
                        if (dropTarget == drag9 &&
                                dropTarget.getText().toString().equals(word11.getText().toString())) {


                            sucess.start();
                            imageView9.setImageResource(R.drawable.purple);
                            imageView9.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word11.clearAnimation();
                            word11.setVisibility(View.INVISIBLE);
                            drag9.setTextColor(Color.rgb(125, 60, 152));
                            drag9.setText("");
                            dropTarget.append(word11.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match11 = true;
                        } else if (dropTarget == drag10 &&
                                dropTarget.getText().toString().equals(word11.getText().toString())) {

                            sucess.start();
                            imageView10.setImageResource(R.drawable.purple);
                            imageView10.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word11.clearAnimation();
                            word11.setVisibility(View.INVISIBLE);
                            drag10.setTextColor(Color.rgb(125, 60, 152));
                            drag10.setText("");
                            dropTarget.append(word11.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match11 = true;

                        } else if (dropTarget == drag11 &&
                                dropTarget.getText().toString().equals(word11.getText().toString())) {

                            sucess.start();
                            imageView11.setImageResource(R.drawable.purple);
                            imageView11.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word11.clearAnimation();
                            word11.setVisibility(View.INVISIBLE);
                            drag11.setTextColor(Color.rgb(125, 60, 152));
                            drag11.setText("");
                            dropTarget.append(word11.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match11 = true;

                        } else if (dropTarget == drag12 &&
                                dropTarget.getText().toString().equals(word11.getText().toString())) {

                            sucess.start();
                            imageView12.setImageResource(R.drawable.purple);
                            imageView12.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word11.clearAnimation();
                            word11.setVisibility(View.INVISIBLE);
                            drag12.setTextColor(Color.rgb(125, 60, 152));
                            drag12.setText("");
                            dropTarget.append(word11.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match11 = true;

                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match11 = false;

                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);
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


                    if (event.getLocalState() == word12) {
                        if (dropTarget == drag9 &&
                                dropTarget.getText().toString().equals(word12.getText().toString())) {

                            sucess.start();
                            imageView9.setImageResource(R.drawable.purple);
                            imageView9.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word12.clearAnimation();
                            word12.setVisibility(View.INVISIBLE);
                            drag9.setTextColor(Color.rgb(125, 60, 152));
                            drag9.setText("");
                            dropTarget.append(word12.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match12 = true;

                        } else if (dropTarget == drag10 &&
                                dropTarget.getText().toString().equals(word12.getText().toString())) {

                            sucess.start();
                            imageView10.setImageResource(R.drawable.purple);
                            imageView10.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word12.clearAnimation();
                            word12.setVisibility(View.INVISIBLE);
                            drag10.setTextColor(Color.rgb(125, 60, 152));
                            drag10.setText("");
                            dropTarget.append(word12.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match12 = true;

                        } else if (dropTarget == drag11 &&
                                dropTarget.getText().toString().equals(word12.getText().toString())) {

                            sucess.start();
                            imageView11.setImageResource(R.drawable.purple);
                            imageView11.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word12.clearAnimation();
                            word12.setVisibility(View.INVISIBLE);
                            drag11.setTextColor(Color.rgb(125, 60, 152));
                            drag11.setText("");
                            dropTarget.append(word12.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match12 = true;

                        } else if (dropTarget == drag12 &&
                                dropTarget.getText().toString().equals(word12.getText().toString())) {

                            sucess.start();
                            imageView12.setImageResource(R.drawable.purple);
                            imageView12.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word12.clearAnimation();
                            word12.setVisibility(View.INVISIBLE);
                            drag12.setTextColor(Color.rgb(125, 60, 152));
                            drag12.setText("");
                            dropTarget.append(word12.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match12 = true;

                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match12 = false;

                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);
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

                    if (event.getLocalState() == word13) {
                        if (dropTarget == drag13 &&
                                dropTarget.getText().toString().equals(word13.getText().toString())) {

                            sucess.start();
                            imageView13.setImageResource(R.drawable.purple);
                            imageView13.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word13.clearAnimation();
                            word13.setVisibility(View.INVISIBLE);
                            drag13.setTextColor(Color.rgb(125, 60, 152));
                            drag13.setText("");
                            dropTarget.append(word13.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match13 = true;

                        } else if (dropTarget == drag14 &&
                                dropTarget.getText().toString().equals(word13.getText().toString())) {

                            sucess.start();
                            imageView14.setImageResource(R.drawable.purple);
                            imageView14.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word13.clearAnimation();
                            word13.setVisibility(View.INVISIBLE);
                            drag14.setTextColor(Color.rgb(125, 60, 152));
                            drag14.setText("");
                            dropTarget.append(word13.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match13 = true;

                        } else if (dropTarget == drag15 &&
                                dropTarget.getText().toString().equals(word13.getText().toString())) {

                            sucess.start();
                            imageView15.setImageResource(R.drawable.purple);
                            imageView15.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word13.clearAnimation();
                            word13.setVisibility(View.INVISIBLE);
                            drag15.setTextColor(Color.rgb(125, 60, 152));
                            drag15.setText("");
                            dropTarget.append(word13.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match13 = true;

                        } else if (dropTarget == drag16 &&
                                dropTarget.getText().toString().equals(word13.getText().toString())) {

                            sucess.start();
                            imageView16.setImageResource(R.drawable.purple);
                            imageView16.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word13.clearAnimation();
                            word13.setVisibility(View.INVISIBLE);
                            drag16.setTextColor(Color.rgb(125, 60, 152));
                            drag16.setText("");
                            dropTarget.append(word13.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match13 = true;

                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match13 = false;

                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);
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

                    if (event.getLocalState() == word14) {
                        if (dropTarget == drag13 &&
                                dropTarget.getText().toString().equals(word14.getText().toString())) {

                            sucess.start();
                            imageView13.setImageResource(R.drawable.purple);
                            imageView13.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word14.clearAnimation();
                            word14.setVisibility(View.INVISIBLE);
                            drag13.setTextColor(Color.rgb(125, 60, 152));
                            drag13.setText("");
                            dropTarget.append(word14.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match14 = true;

                        } else if (dropTarget == drag14 &&
                                dropTarget.getText().toString().equals(word14.getText().toString())) {

                            sucess.start();
                            imageView14.setImageResource(R.drawable.purple);
                            imageView14.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word14.clearAnimation();
                            word14.setVisibility(View.INVISIBLE);
                            drag14.setTextColor(Color.rgb(125, 60, 152));
                            drag14.setText("");
                            dropTarget.append(word14.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match14 = true;

                        } else if (dropTarget == drag15 &&
                                dropTarget.getText().toString().equals(word14.getText().toString())) {

                            sucess.start();
                            imageView15.setImageResource(R.drawable.purple);
                            imageView15.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word14.clearAnimation();
                            word14.setVisibility(View.INVISIBLE);
                            drag15.setTextColor(Color.rgb(125, 60, 152));
                            drag15.setText("");
                            dropTarget.append(word14.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match14 = true;

                        } else if (dropTarget == drag16 &&
                                dropTarget.getText().toString().equals(word14.getText().toString())) {

                            sucess.start();
                            imageView16.setImageResource(R.drawable.purple);
                            imageView16.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word14.clearAnimation();
                            word14.setVisibility(View.INVISIBLE);
                            drag16.setTextColor(Color.rgb(125, 60, 152));
                            drag16.setText("");
                            dropTarget.append(word14.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match14 = true;

                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match14 = false;

                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);
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

                    if (event.getLocalState() == word15) {
                        if (dropTarget == drag13 &&
                                dropTarget.getText().toString().equals(word15.getText().toString())) {

                            sucess.start();
                            imageView13.setImageResource(R.drawable.purple);
                            imageView13.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word15.clearAnimation();
                            word15.setVisibility(View.INVISIBLE);
                            drag13.setTextColor(Color.rgb(125, 60, 152));
                            drag13.setText("");
                            dropTarget.append(word15.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match15 = true;

                        } else if (dropTarget == drag14 &&
                                dropTarget.getText().toString().equals(word15.getText().toString())) {

                            sucess.start();
                            imageView14.setImageResource(R.drawable.purple);
                            imageView14.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word15.clearAnimation();
                            word15.setVisibility(View.INVISIBLE);
                            drag14.setTextColor(Color.rgb(125, 60, 152));
                            drag14.setText("");
                            dropTarget.append(word15.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match15 = true;

                        } else if (dropTarget == drag15 &&
                                dropTarget.getText().toString().equals(word15.getText().toString())) {

                            sucess.start();
                            imageView15.setImageResource(R.drawable.purple);
                            imageView15.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word15.clearAnimation();
                            word15.setVisibility(View.INVISIBLE);
                            drag15.setTextColor(Color.rgb(125, 60, 152));
                            drag15.setText("");
                            dropTarget.append(word15.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match15 = true;

                        } else if (dropTarget == drag16 &&
                                dropTarget.getText().toString().equals(word15.getText().toString())) {

                            sucess.start();
                            imageView16.setImageResource(R.drawable.purple);
                            imageView16.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word15.clearAnimation();
                            word15.setVisibility(View.INVISIBLE);
                            drag16.setTextColor(Color.rgb(125, 60, 152));
                            drag16.setText("");
                            dropTarget.append(word15.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match15 = true;

                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match15 = false;

                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);
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

                    if (event.getLocalState() == word16) {
                        if (dropTarget == drag13 &&
                                dropTarget.getText().toString().equals(word16.getText().toString())) {

                            sucess.start();
                            imageView13.setImageResource(R.drawable.purple);
                            imageView13.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word16.clearAnimation();
                            word16.setVisibility(View.INVISIBLE);
                            drag13.setTextColor(Color.rgb(125, 60, 152));
                            drag13.setText("");
                            dropTarget.append(word16.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match16 = true;

                        } else if (dropTarget == drag14 &&
                                dropTarget.getText().toString().equals(word16.getText().toString())) {

                            sucess.start();
                            imageView14.setImageResource(R.drawable.purple);
                            imageView14.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word16.clearAnimation();
                            word16.setVisibility(View.INVISIBLE);
                            drag14.setTextColor(Color.rgb(125, 60, 152));
                            drag14.setText("");
                            dropTarget.append(word16.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match16 = true;

                        } else if (dropTarget == drag15 &&
                                dropTarget.getText().toString().equals(word16.getText().toString())) {

                            sucess.start();
                            imageView15.setImageResource(R.drawable.purple);
                            imageView15.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word16.clearAnimation();
                            word16.setVisibility(View.INVISIBLE);
                            drag15.setTextColor(Color.rgb(125, 60, 152));
                            drag15.setText("");
                            dropTarget.append(word16.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match16 = true;

                        } else if (dropTarget == drag16 &&
                                dropTarget.getText().toString().equals(word16.getText().toString())) {

                            sucess.start();
                            imageView16.setImageResource(R.drawable.purple);
                            imageView16.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word16.clearAnimation();
                            word16.setVisibility(View.INVISIBLE);
                            drag16.setTextColor(Color.rgb(125, 60, 152));
                            drag16.setText("");
                            dropTarget.append(word16.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match16 = true;

                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match16 = false;

                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);
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

                    if (event.getLocalState() == word17) {
                        if (dropTarget == drag17 &&
                                dropTarget.getText().toString().equals(word17.getText().toString())) {

                            sucess.start();
                            imageView17.setImageResource(R.drawable.purple);
                            imageView17.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word17.clearAnimation();
                            word17.setVisibility(View.INVISIBLE);
                            drag17.setTextColor(Color.rgb(125, 60, 152));
                            drag17.setText("");
                            dropTarget.append(word17.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match17 = true;

                        } else if (dropTarget == drag18 &&
                                dropTarget.getText().toString().equals(word17.getText().toString())) {

                            sucess.start();
                            imageView18.setImageResource(R.drawable.purple);
                            imageView18.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word17.clearAnimation();
                            word17.setVisibility(View.INVISIBLE);
                            drag18.setTextColor(Color.rgb(125, 60, 152));
                            drag18.setText("");
                            dropTarget.append(word17.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match17 = true;

                        } else if (dropTarget == drag19 &&
                                dropTarget.getText().toString().equals(word17.getText().toString())) {

                            sucess.start();
                            imageView19.setImageResource(R.drawable.purple);
                            imageView19.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word17.clearAnimation();
                            word17.setVisibility(View.INVISIBLE);
                            drag19.setTextColor(Color.rgb(125, 60, 152));
                            drag19.setText("");
                            dropTarget.append(word17.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match17 = true;

                        } else if (dropTarget == drag20 &&
                                dropTarget.getText().toString().equals(word17.getText().toString())) {

                            sucess.start();
                            imageView20.setImageResource(R.drawable.purple);
                            imageView20.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word17.clearAnimation();
                            word17.setVisibility(View.INVISIBLE);
                            drag20.setTextColor(Color.rgb(125, 60, 152));
                            drag20.setText("");
                            dropTarget.append(word17.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match17 = true;

                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match17 = false;

                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);
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

                    if (event.getLocalState() == word18) {
                        if (dropTarget == drag17 &&
                                dropTarget.getText().toString().equals(word17.getText().toString())) {

                            sucess.start();
                            imageView17.setImageResource(R.drawable.purple);
                            imageView17.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word18.clearAnimation();
                            word18.setVisibility(View.INVISIBLE);
                            drag17.setTextColor(Color.rgb(125, 60, 152));
                            drag17.setText("");
                            dropTarget.append(word18.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match18 = true;

                        } else if (dropTarget == drag18 &&
                                dropTarget.getText().toString().equals(word18.getText().toString())) {

                            sucess.start();
                            imageView18.setImageResource(R.drawable.purple);
                            imageView18.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word18.clearAnimation();
                            word18.setVisibility(View.INVISIBLE);
                            drag18.setTextColor(Color.rgb(125, 60, 152));
                            drag18.setText("");
                            dropTarget.append(word18.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match18 = true;

                        } else if (dropTarget == drag19 &&
                                dropTarget.getText().toString().equals(word18.getText().toString())) {

                            sucess.start();
                            imageView19.setImageResource(R.drawable.purple);
                            imageView19.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word18.clearAnimation();
                            word18.setVisibility(View.INVISIBLE);
                            drag19.setTextColor(Color.rgb(125, 60, 152));
                            drag19.setText("");
                            dropTarget.append(word17.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match18 = true;

                        } else if (dropTarget == drag20 &&
                                dropTarget.getText().toString().equals(word18.getText().toString())) {

                            sucess.start();
                            imageView20.setImageResource(R.drawable.purple);
                            imageView20.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word18.clearAnimation();
                            word18.setVisibility(View.INVISIBLE);
                            drag20.setTextColor(Color.rgb(125, 60, 152));
                            drag20.setText("");
                            dropTarget.append(word18.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match18 = true;

                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match18 = false;

                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);
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

                    if (event.getLocalState() == word19) {
                        if (dropTarget == drag17 &&
                                dropTarget.getText().toString().equals(word19.getText().toString())) {

                            sucess.start();
                            imageView17.setImageResource(R.drawable.purple);
                            imageView17.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word19.clearAnimation();
                            word19.setVisibility(View.INVISIBLE);
                            drag17.setTextColor(Color.rgb(125, 60, 152));
                            drag17.setText("");
                            dropTarget.append(word19.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match19 = true;

                        } else if (dropTarget == drag18 &&
                                dropTarget.getText().toString().equals(word19.getText().toString())) {

                            sucess.start();
                            imageView18.setImageResource(R.drawable.purple);
                            imageView18.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word19.clearAnimation();
                            word19.setVisibility(View.INVISIBLE);
                            drag18.setTextColor(Color.rgb(125, 60, 152));
                            drag18.setText("");
                            dropTarget.append(word19.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match19 = true;

                        } else if (dropTarget == drag19 &&
                                dropTarget.getText().toString().equals(word19.getText().toString())) {

                            sucess.start();
                            imageView19.setImageResource(R.drawable.purple);
                            imageView19.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word19.clearAnimation();
                            word19.setVisibility(View.INVISIBLE);
                            drag19.setTextColor(Color.rgb(125, 60, 152));
                            drag19.setText("");
                            dropTarget.append(word19.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match19 = true;

                        } else if (dropTarget == drag20 &&
                                dropTarget.getText().toString().equals(word19.getText().toString())) {

                            sucess.start();
                            imageView20.setImageResource(R.drawable.purple);
                            imageView20.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word19.clearAnimation();
                            word19.setVisibility(View.INVISIBLE);
                            drag20.setTextColor(Color.rgb(125, 60, 152));
                            drag20.setText("");
                            dropTarget.append(word19.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match19 = true;

                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match19 = false;

                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);
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

                    if (event.getLocalState() == word20) {
                        if (dropTarget == drag17 &&
                                dropTarget.getText().toString().equals(word20.getText().toString())) {

                            sucess.start();
                            imageView17.setImageResource(R.drawable.purple);
                            imageView17.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word20.clearAnimation();
                            word20.setVisibility(View.INVISIBLE);
                            drag17.setTextColor(Color.rgb(125, 60, 152));
                            drag17.setText("");
                            dropTarget.append(word20.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match20 = true;

                        } else if (dropTarget == drag18 &&
                                dropTarget.getText().toString().equals(word20.getText().toString())) {

                            sucess.start();
                            imageView18.setImageResource(R.drawable.purple);
                            imageView18.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word20.clearAnimation();
                            word20.setVisibility(View.INVISIBLE);
                            drag18.setTextColor(Color.rgb(125, 60, 152));
                            drag18.setText("");
                            dropTarget.append(word20.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match20 = true;

                        } else if (dropTarget == drag19 &&
                                dropTarget.getText().toString().equals(word20.getText().toString())) {

                            sucess.start();
                            imageView19.setImageResource(R.drawable.purple);
                            imageView19.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word20.clearAnimation();
                            word20.setVisibility(View.INVISIBLE);
                            drag19.setTextColor(Color.rgb(125, 60, 152));
                            drag19.setText("");
                            dropTarget.append(word20.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match20 = true;

                        } else if (dropTarget == drag20 &&
                                dropTarget.getText().toString().equals(word20.getText().toString())) {

                            sucess.start();
                            imageView20.setImageResource(R.drawable.purple);
                            imageView20.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word20.clearAnimation();
                            word20.setVisibility(View.INVISIBLE);
                            drag20.setTextColor(Color.rgb(125, 60, 152));
                            drag20.setText("");
                            dropTarget.append(word20.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match20 = true;

                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match20 = false;

                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);
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

                    if (event.getLocalState() == word21) {
                        if (dropTarget == drag21 &&
                                dropTarget.getText().toString().equals(word21.getText().toString())) {

                            sucess.start();
                            imageView21.setImageResource(R.drawable.purple);
                            imageView21.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word21.clearAnimation();
                            word21.setVisibility(View.INVISIBLE);
                            drag21.setTextColor(Color.rgb(125, 60, 152));
                            drag21.setText("");
                            dropTarget.append(word21.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match21 = true;

                        } else if (dropTarget == drag22 &&
                                dropTarget.getText().toString().equals(word21.getText().toString())) {

                            sucess.start();
                            imageView22.setImageResource(R.drawable.purple);
                            imageView22.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word21.clearAnimation();
                            word21.setVisibility(View.INVISIBLE);
                            drag22.setTextColor(Color.rgb(125, 60, 152));
                            drag22.setText("");
                            dropTarget.append(word21.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match21 = true;

                        } else if (dropTarget == drag23 &&
                                dropTarget.getText().toString().equals(word21.getText().toString())) {

                            sucess.start();
                            imageView23.setImageResource(R.drawable.purple);
                            imageView23.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word21.clearAnimation();
                            word21.setVisibility(View.INVISIBLE);
                            drag23.setTextColor(Color.rgb(125, 60, 152));
                            drag23.setText("");
                            dropTarget.append(word21.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match21 = true;

                        } else if (dropTarget == drag24 &&
                                dropTarget.getText().toString().equals(word21.getText().toString())) {

                            sucess.start();
                            imageView24.setImageResource(R.drawable.purple);
                            imageView24.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word21.clearAnimation();
                            word21.setVisibility(View.INVISIBLE);
                            drag24.setTextColor(Color.rgb(125, 60, 152));
                            drag24.setText("");
                            dropTarget.append(word21.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match21 = true;

                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match21 = false;

                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);
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

                    if (event.getLocalState() == word22) {
                        if (dropTarget == drag21 &&
                                dropTarget.getText().toString().equals(word22.getText().toString())) {

                            sucess.start();
                            imageView21.setImageResource(R.drawable.purple);
                            imageView21.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word22.clearAnimation();
                            word22.setVisibility(View.INVISIBLE);
                            drag21.setTextColor(Color.rgb(125, 60, 152));
                            drag21.setText("");
                            dropTarget.append(word22.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match22 = true;

                        } else if (dropTarget == drag22 &&
                                dropTarget.getText().toString().equals(word22.getText().toString())) {

                            sucess.start();
                            imageView22.setImageResource(R.drawable.purple);
                            imageView22.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word22.clearAnimation();
                            word22.setVisibility(View.INVISIBLE);
                            drag22.setTextColor(Color.rgb(125, 60, 152));
                            drag22.setText("");
                            dropTarget.append(word22.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match22 = true;

                        } else if (dropTarget == drag23 &&
                                dropTarget.getText().toString().equals(word22.getText().toString())) {

                            sucess.start();
                            imageView23.setImageResource(R.drawable.purple);
                            imageView23.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word22.clearAnimation();
                            word22.setVisibility(View.INVISIBLE);
                            drag23.setTextColor(Color.rgb(125, 60, 152));
                            drag23.setText("");
                            dropTarget.append(word22.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match22 = true;

                        } else if (dropTarget == drag24 &&
                                dropTarget.getText().toString().equals(word22.getText().toString())) {

                            sucess.start();
                            imageView24.setImageResource(R.drawable.purple);
                            imageView24.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word22.clearAnimation();
                            word22.setVisibility(View.INVISIBLE);
                            drag24.setTextColor(Color.rgb(125, 60, 152));
                            drag24.setText("");
                            dropTarget.append(word22.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match22 = true;

                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match22 = false;

                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);
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

                    if (event.getLocalState() == word23) {
                        if (dropTarget == drag21 &&
                                dropTarget.getText().toString().equals(word23.getText().toString())) {


                            sucess.start();
                            imageView21.setImageResource(R.drawable.purple);
                            imageView21.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word23.clearAnimation();
                            word23.setVisibility(View.INVISIBLE);
                            drag21.setTextColor(Color.rgb(125, 60, 152));
                            drag21.setText("");
                            dropTarget.append(word23.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match23 = true;

                        } else if (dropTarget == drag22 &&
                                dropTarget.getText().toString().equals(word23.getText().toString())) {

                            sucess.start();
                            imageView22.setImageResource(R.drawable.purple);
                            imageView22.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word23.clearAnimation();
                            word23.setVisibility(View.INVISIBLE);
                            drag22.setTextColor(Color.rgb(125, 60, 152));
                            drag22.setText("");
                            dropTarget.append(word23.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match23 = true;

                        } else if (dropTarget == drag23 &&
                                dropTarget.getText().toString().equals(word23.getText().toString())) {

                            sucess.start();
                            imageView23.setImageResource(R.drawable.purple);
                            imageView23.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word23.clearAnimation();
                            word23.setVisibility(View.INVISIBLE);
                            drag23.setTextColor(Color.rgb(125, 60, 152));
                            drag23.setText("");
                            dropTarget.append(word23.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match23 = true;

                        } else if (dropTarget == drag24 &&
                                dropTarget.getText().toString().equals(word23.getText().toString())) {

                            sucess.start();
                            imageView24.setImageResource(R.drawable.purple);
                            imageView24.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                            word23.clearAnimation();
                            word23.setVisibility(View.INVISIBLE);
                            drag24.setTextColor(Color.rgb(125, 60, 152));
                            drag24.setText("");
                            dropTarget.append(word23.getText().toString());
                            scoreText.setText("" + score.increaseScore());
                            dropTarget.setOnDragListener(null);
                            correct++;
                            match23 = true;

                        } else {
                            scoreText.setText("" + score.decreaseScore());
                            fail.start();

                            count--;
                            wrong++;
                            if (wrong < 0)
                                wrong = 0;
                            match23 = false;

                            if (count == 1) {
                                right1.setVisibility(View.INVISIBLE);

                            }
                            if (count == 2) {
                                right2.setVisibility(View.INVISIBLE);
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
                        if (event.getLocalState() == word24) {
                            if (dropTarget == drag21 &&
                                    dropTarget.getText().toString().equals(word24.getText().toString())) {

                                sucess.start();
                                imageView21.setImageResource(R.drawable.purple);
                                imageView21.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                                word24.clearAnimation();
                                word24.setVisibility(View.INVISIBLE);
                                drag21.setTextColor(Color.rgb(125, 60, 152));
                                drag21.setText("");
                                dropTarget.append(word24.getText().toString());
                                scoreText.setText("" + score.increaseScore());
                                dropTarget.setOnDragListener(null);
                                correct++;
                                match24 = true;

                            } else if (dropTarget == drag22 &&
                                    dropTarget.getText().toString().equals(word24.getText().toString())) {

                                sucess.start();
                                imageView22.setImageResource(R.drawable.purple);
                                imageView22.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                                word24.clearAnimation();
                                word24.setVisibility(View.INVISIBLE);
                                drag22.setTextColor(Color.rgb(125, 60, 152));
                                drag22.setText("");
                                dropTarget.append(word24.getText().toString());
                                scoreText.setText("" + score.increaseScore());
                                dropTarget.setOnDragListener(null);
                                correct++;
                                match24 = true;

                            } else if (dropTarget == drag23 &&
                                    dropTarget.getText().toString().equals(word24.getText().toString())) {

                                sucess.start();
                                imageView23.setImageResource(R.drawable.purple);
                                imageView23.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                                word24.clearAnimation();
                                word24.setVisibility(View.INVISIBLE);
                                drag23.setTextColor(Color.rgb(125, 60, 152));
                                drag23.setText("");
                                dropTarget.append(word24.getText().toString());
                                scoreText.setText("" + score.increaseScore());
                                dropTarget.setOnDragListener(null);
                                correct++;
                                match24 = true;

                            } else if (dropTarget == drag24 &&
                                    dropTarget.getText().toString().equals(word24.getText().toString())) {

                                sucess.start();
                                imageView24.setImageResource(R.drawable.purple);
                                imageView24.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
                                word24.clearAnimation();
                                word24.setVisibility(View.INVISIBLE);
                                drag24.setTextColor(Color.rgb(125, 60, 152));
                                drag24.setText("");
                                dropTarget.append(word24.getText().toString());
                                scoreText.setText("" + score.increaseScore());
                                dropTarget.setOnDragListener(null);
                                correct++;
                                match24 = true;

                            } else {
                                scoreText.setText("" + score.decreaseScore());
                                fail.start();

                                count--;
                                wrong++;
                                if (wrong < 0)
                                    wrong = 0;
                                match24 = false;

                                if (count == 1) {
                                    right1.setVisibility(View.INVISIBLE);

                                }
                                if (count == 2) {
                                    right2.setVisibility(View.INVISIBLE);
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
                    if (match1 == true && match2 == true && match3 == true && match4 == true && count > 0 &&
                            Integer.valueOf(timerText.getText().toString()) > 0) {
                        //successScreen();
                        //relativeLayout.setAnimation(image_anim);
                        invisibleanimation(relativeLayout);
                        Animationss.game_over_slide.start();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {

                                visibleanimation(relativeLayout2);
                                Animationss.game_over_slide.start();
                            }
                        }, 400);

                        match1=false;
                        match2=false;
                        match3=false;
                        match4=false;

                    }

                    if (match5 == true && match6 == true && match7 == true && match8 == true && count > 0 &&
                            Integer.valueOf(timerText.getText().toString()) > 0) {

                        invisibleanimation(relativeLayout2);
                        Animationss.game_over_slide.start();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {

                                visibleanimation(relativeLayout3);
                                Animationss.game_over_slide.start();
                            }
                        }, 400);

                        match5=false;
                        match6=false;
                        match7=false;
                        match8=false;


                    }

                    if (match9 == true && match10 == true && match11 == true && match12 == true && count > 0 &&
                            Integer.valueOf(timerText.getText().toString()) > 0) {

                        invisibleanimation(relativeLayout3);
                        Animationss.game_over_slide.start();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {

                                visibleanimation(relativeLayout4);
                                Animationss.game_over_slide.start();
                            }
                        }, 400);


                        match9=false;
                        match10=false;
                        match11=false;
                        match12=false;


                    }

                    if (match13 == true && match14 == true && match15 == true && match16 == true && count > 0 &&
                            Integer.valueOf(timerText.getText().toString()) > 0) {
                        //successScreen();
                        //relativeLayout.setAnimation(image_anim);

                        invisibleanimation(relativeLayout4);
                        Animationss.game_over_slide.start();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {

                                visibleanimation(relativeLayout5);
                                Animationss.game_over_slide.start();

                            }
                        }, 400);

                        match13=false;
                        match14=false;
                        match15=false;
                        match16=false;

                    }

                    if (match17 == true && match18 == true && match19 == true && match20 == true && count > 0 &&
                            Integer.valueOf(timerText.getText().toString()) > 0) {
                        //successScreen();
                        //relativeLayout.setAnimation(image_anim);

                        invisibleanimation(relativeLayout5);
                        Animationss.game_over_slide.start();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {

                                visibleanimation(relativeLayout6);
                                Animationss.game_over_slide.start();

                            }
                        }, 400);

                        match17=false;
                        match18=false;
                        match19=false;
                        match20=false;

                    }
                    if (match21 == true && match22== true && match23 == true && match24 == true && count > 0 &&
                            Integer.valueOf(timerText.getText().toString()) > 0) {

                       // invisibleanimation(relativeLayout6);
                        Animationss.game_over_slide.start();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {

                                successScreen();
                                Animationss.success_background_sound.start();
                            }
                        }, 400);


                        match21=false;
                        match22=false;
                        match23=false;
                        match24=false;


                    }


                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                default:
                    break;
            }
            return true;
        }
public void invisibleanimation(View view)
{
    TranslateAnimation animate = new TranslateAnimation(0,view.getWidth(),0,0);
    animate.setDuration(500);
    animate.setFillAfter(false);
    view.setAnimation(animate);
    view.setVisibility(View.INVISIBLE);
}

        public void visibleanimation(View view) {

            TranslateAnimation animate = new TranslateAnimation(view.getWidth(),0,0,0);
            animate.setDuration(500);
            animate.setFillAfter(false);
            view.setAnimation(animate);
            view.setVisibility(View.VISIBLE);

        }

}

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        private int progressStatus = 0;
        private Handler handler = new Handler();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ThirdLevelActivity.this);
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

            if (SampleFragmentTwo.vehicles_category== true) {
                String jsonStr = sh.makeServiceCall(vehicles_url);
                contactList.clear();

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("result_vehicles");
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
            imageView5.setAnimation(image_anim);
            imageView6.setAnimation(image_anim);
            imageView7.setAnimation(image_anim);
            imageView8.setAnimation(image_anim);
            imageView9.setAnimation(image_anim);
            imageView10.setAnimation(image_anim);
            imageView11.setAnimation(image_anim);
            imageView12.setAnimation(image_anim);


            imageView1.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView2.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView3.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView4.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView5.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView6.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView7.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView8.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView9.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView10.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView11.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView12.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView13.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView14.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView15.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView16.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView17.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView18.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView19.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView20.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView21.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView22.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView23.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            imageView24.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));


            word2.setText(contactList.get(0).get("word"));
            word2.setTypeface(tf1);
            word2.setAnimation(image_anim);
            word2.setTextSize(16);
            drag1.setTextSize(16);
            word2.setTextColor(Color.rgb(125, 60, 152));
            drag1.setText(contactList.get(0).get("word"));
            drag1.setTextColor(Color.rgb(125, 60, 152));
            drag1.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView1);
            drag1.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word3.setText(contactList.get(0).get("word"));
            word3.setTypeface(tf1);
            word3.setAnimation(image_anim);
            word3.setTextColor(Color.rgb(125, 60, 152));
            word3.setTextSize(16);
            drag2.setTextSize(16);
            drag2.setTextColor(Color.rgb(125, 60, 152));
            drag2.setText(contactList.get(0).get("word"));
            drag2.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView2);
            drag2.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word1.setText(contactList.get(0).get("word"));
            word1.setTypeface(tf1);
            word1.setAnimation(image_anim);
            word1.setTextColor(Color.rgb(125, 60, 152));
            word1.setTextSize(16);
            drag3.setTextSize(16);
            drag3.setTypeface(tf1);
            drag3.setTextColor(Color.rgb(125, 60, 152));
            drag3.setText(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView3);
            drag3.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word4.setText(contactList.get(0).get("word"));
            word4.setTypeface(tf1);
            word4.setAnimation(image_anim);
            word4.setTextColor(Color.rgb(125, 60, 152));
            word4.setTextSize(16);
            drag4.setText(contactList.get(0).get("word"));
            drag4.setTextSize(16);
            drag4.setTextColor(Color.rgb(125, 60, 152));
            drag4.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView4);
            drag4.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word5.setText(contactList.get(0).get("word"));
            word5.setTypeface(tf1);
            word5.setAnimation(image_anim);
            word5.setTextSize(16);
            word5.setTextColor(Color.rgb(125, 60, 152));
            drag7.setText(contactList.get(0).get("word"));
            drag7.setTextColor(Color.rgb(125, 60, 152));
            drag7.setTypeface(tf1);
            drag7.setTextSize(16);
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView7);
            drag7.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word6.setText(contactList.get(0).get("word"));
            word6.setTypeface(tf1);
            word6.setAnimation(image_anim);
            word6.setTextColor(Color.rgb(125, 60, 152));
            word6.setTextSize(16);
            drag5.setTextSize(16);
            drag5.setTextColor(Color.rgb(125, 60, 152));
            drag5.setText(contactList.get(0).get("word"));
            drag5.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView5);
            drag5.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word7.setText(contactList.get(0).get("word"));
            word7.setTypeface(tf1);
            word7.setAnimation(image_anim);
            word7.setTextColor(Color.rgb(125, 60, 152));
            word7.setTextSize(16);
            drag6.setTextSize(16);
            drag6.setTypeface(tf1);
            drag6.setTextColor(Color.rgb(125, 60, 152));
            drag6.setText(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView6);
            drag6.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word8.setText(contactList.get(0).get("word"));
            word8.setTypeface(tf1);
            word8.setAnimation(image_anim);
            word8.setTextColor(Color.rgb(125, 60, 152));
            word8.setTextSize(16);
            drag8.setText(contactList.get(0).get("word"));
            drag8.setTextSize(16);
            drag8.setTextColor(Color.rgb(125, 60, 152));
            drag8.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView8);
            drag8.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word9.setText(contactList.get(0).get("word"));
            word9.setTypeface(tf1);
            word9.setAnimation(image_anim);
            word9.setTextSize(16);
            drag12.setTextSize(16);
            word9.setTextColor(Color.rgb(125, 60, 152));
            drag12.setText(contactList.get(0).get("word"));
            drag12.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView12);
            drag12.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word10.setText(contactList.get(0).get("word"));
            word10.setTypeface(tf1);
            word10.setAnimation(image_anim);
            word10.setTextColor(Color.rgb(125, 60, 152));
            word10.setTextSize(16);
            drag11.setTextSize(16);
            drag11.setTextColor(Color.rgb(125, 60, 152));
            drag11.setText(contactList.get(0).get("word"));
            drag11.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView11);
            drag11.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word11.setText(contactList.get(0).get("word"));
            word11.setTypeface(tf1);
            word11.setAnimation(image_anim);
            word11.setTextColor(Color.rgb(125, 60, 152));
            word11.setTextSize(16);
            drag10.setTextSize(16);
            drag10.setTypeface(tf1);
            drag10.setTextColor(Color.rgb(125, 60, 152));
            drag10.setText(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView10);
            drag10.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word12.setText(contactList.get(0).get("word"));
            word12.setTypeface(tf1);
            word12.setAnimation(image_anim);
            word12.setTextColor(Color.rgb(125, 60, 152));
            word12.setTextSize(16);
            drag9.setText(contactList.get(0).get("word"));
            drag9.setTextSize(16);
            drag9.setTextColor(Color.rgb(125, 60, 152));
            drag9.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView9);
            drag9.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word13.setText(contactList.get(0).get("word"));
            word13.setTypeface(tf1);
            word13.setAnimation(image_anim);
            word13.setTextSize(16);
            drag15.setTextSize(16);
            word13.setTextColor(Color.rgb(125, 60, 152));
            drag15.setText(contactList.get(0).get("word"));
            drag15.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView15);
            drag15.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word14.setText(contactList.get(0).get("word"));
            word14.setTypeface(tf1);
            word14.setAnimation(image_anim);
            word14.setTextColor(Color.rgb(125, 60, 152));
            word14.setTextSize(16);
            drag13.setTextSize(16);
            drag13.setTextColor(Color.rgb(125, 60, 152));
            drag13.setText(contactList.get(0).get("word"));
            drag13.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView13);
            drag13.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word15.setText(contactList.get(0).get("word"));
            word15.setTypeface(tf1);
            word15.setAnimation(image_anim);
            word15.setTextColor(Color.rgb(125, 60, 152));
            word15.setTextSize(16);
            drag14.setTextSize(16);
            drag14.setTypeface(tf1);
            drag14.setTextColor(Color.rgb(125, 60, 152));
            drag14.setText(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView14);
            drag14.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word16.setText(contactList.get(0).get("word"));
            word16.setTypeface(tf1);
            word16.setAnimation(image_anim);
            word16.setTextColor(Color.rgb(125, 60, 152));
            word16.setTextSize(16);
            drag16.setText(contactList.get(0).get("word"));
            drag16.setTextSize(16);
            drag16.setTextColor(Color.rgb(125, 60, 152));
            drag16.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView16);
            drag16.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word17.setText(contactList.get(0).get("word"));
            word17.setTypeface(tf1);
            word17.setAnimation(image_anim);
            word17.setTextSize(16);
            drag19.setTextSize(16);
            word17.setTextColor(Color.rgb(125, 60, 152));
            drag19.setText(contactList.get(0).get("word"));
            drag19.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView19);
            drag19.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word18.setText(contactList.get(0).get("word"));
            word18.setTypeface(tf1);
            word18.setAnimation(image_anim);
            word18.setTextColor(Color.rgb(125, 60, 152));
            word18.setTextSize(16);
            drag20.setTextSize(16);
            drag20.setTextColor(Color.rgb(125, 60, 152));
            drag20.setText(contactList.get(0).get("word"));
            drag20.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView20);
            drag20.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word19.setText(contactList.get(0).get("word"));
            word19.setTypeface(tf1);
            word19.setAnimation(image_anim);
            word19.setTextColor(Color.rgb(125, 60, 152));
            word19.setTextSize(16);
            drag17.setTextSize(16);
            drag17.setTypeface(tf1);
            drag17.setTextColor(Color.rgb(125, 60, 152));
            drag17.setText(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView17);
            drag17.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word20.setText(contactList.get(0).get("word"));
            word20.setTypeface(tf1);
            word20.setAnimation(image_anim);
            word20.setTextColor(Color.rgb(125, 60, 152));
            word20.setTextSize(16);
            drag18.setText(contactList.get(0).get("word"));
            drag18.setTextSize(16);
            drag18.setTextColor(Color.rgb(125, 60, 152));
            drag18.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView18);
            drag18.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word21.setText(contactList.get(0).get("word"));
            word21.setTypeface(tf1);
            word21.setAnimation(image_anim);
            word21.setTextSize(16);
            drag22.setTextSize(16);
            word21.setTextColor(Color.rgb(125, 60, 152));
            drag22.setText(contactList.get(0).get("word"));
            drag22.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView22);
            drag22.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word22.setText(contactList.get(0).get("word"));
            word22.setTypeface(tf1);
            word22.setAnimation(image_anim);
            word22.setTextColor(Color.rgb(125, 60, 152));
            word22.setTextSize(16);
            drag24.setTextSize(16);
            drag24.setTextColor(Color.rgb(125, 60, 152));
            drag24.setText(contactList.get(0).get("word"));
            drag24.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView24);
            drag24.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word23.setText(contactList.get(0).get("word"));
            word23.setTypeface(tf1);
            word23.setAnimation(image_anim);
            word23.setTextColor(Color.rgb(125, 60, 152));
            word23.setTextSize(16);
            drag23.setTextSize(16);
            drag23.setTypeface(tf1);
            drag23.setTextColor(Color.rgb(125, 60, 152));
            drag23.setText(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView23);
            drag23.setTextColor(Color.TRANSPARENT);

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word24.setText(contactList.get(0).get("word"));
            word24.setTypeface(tf1);
            word24.setAnimation(image_anim);
            word24.setTextColor(Color.rgb(125, 60, 152));
            word24.setTextSize(16);
            drag21.setText(contactList.get(0).get("word"));
            drag21.setTextSize(16);
            drag21.setTextColor(Color.rgb(125, 60, 152));
            drag21.setTypeface(tf1);
            imageLoader.displayImage(contactList.get(0).get("image_path"), imageView21);
            drag21.setTextColor(Color.TRANSPARENT);

            Animationss.back.start();
            timerText.setTextColor(Color.rgb(125, 60, 152));
            Typeface type = Typeface.createFromAsset(getAssets(), "fonts/sky.ttf");
            timerText.setTypeface(type);
            timerText.setTextSize(25);
            startCountdownTimer(timerText);


        }}

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
                    score_list = new ArrayList<>();
                    // Getting JSON Array node
                    JSONArray scores = jsonObj.getJSONArray("result");
                    for (int i = 0; i < scores.length(); i++) {
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
                    for (int i = 0; i < scores.length(); i++) {
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

                if (LevelScreen.isLevel3 == true) {
                    if (MainActivity.name.equals(score_list.get(i).get("name")) && score_list.get(i).get("level").equals("3")) {
                        if (Integer.valueOf(scoreText.getText().toString()) > Integer.valueOf(score_list.get(i).get("score").toString())
                                ) {
                            high_score_result.setText("" + scoreText.getText().toString());
                        } else {
                           high_score_result.setText("" + score_list.get(i).get("score").toString());
                        }
                    }


                }


            }

        }


    }
}