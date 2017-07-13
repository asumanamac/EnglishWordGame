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
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
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

import static example.seniordesign_project.Animationss.back;
import static example.seniordesign_project.Animationss.buttonSound;
import static example.seniordesign_project.Animationss.fail;
import static example.seniordesign_project.Animationss.game_over_slide;
import static example.seniordesign_project.Animationss.game_over_sound;
import static example.seniordesign_project.Animationss.success_background_sound;
import static example.seniordesign_project.Animationss.sucess;

public class FourthLevelActivity extends AppCompatActivity {

    private String TAG = FourthLevelActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9,image10,image11,image12,image13,image14,image15,image16,
            image17,image18,image19,image20,image21,image22,image23,image24,image25,image26,image27,image28,image29,image30,image31,
            image32,image33,image34,image35,image36,image37,image38,image39,image40,image41,image42,image43,image44,image45,image46,
            image47,image48,image49,image50,image51,image52,image53,image54,image55,image56,image57,image58,image59,image60;

    RelativeLayout relative_layout1,relative_layout2,relative_layout3,relative_layout4,relative_layout5,relative_layout6,
            relative_layout7,relative_layout8,relative_layout9,relative_layout10,relative_layout11,relative_layout12,relative_layout13,
            relative_layout14,relative_layout15;
    private static String url = "http://192.168.2.11:8080/android_login_api/images/getAllImages_music.php";
    String URL_POST= "http://192.168.2.11:8080/android_login_api/score.php";
    PostClass post = new PostClass();
    TextView word,word2,word3,word4,word5,word6,word7,word8,word9,word10,word11,word12,word13,word14,word15;
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
    ImageView  right1, right2, right3;
    public static TextView scoreText;
    boolean match1 = false, match2 = false, match3 = false, match4 = false, match5 = false, match6 = false, match7=false,match8=false,
            match9=false,match10=false,match11=false,match12=false,match13=false,match14=false,match15=false;
    public static boolean isPaused = false,isTrue=false ,isClick=false,game_over=false,game_success=false;
    public static boolean isCanceled = false,level2_status=false;
    public long timeRemaining = 0;
    Dialog game_over_dialog,success_screen_dialog,information_box,level2_dialog;
    Score score;
    public static String level;
    private static final String PREFS_LAST_IMG = "prefs_last_img";
    ArrayList second_list;
    private SharedPreferences mPreferences;
    Typeface tf1;
    ImageLoader imageLoader;
    static boolean isGameOver=false;
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
        setContentView(R.layout.activity_fourth_level);

        image1=(ImageView) findViewById(R.id.image1);
        image2=(ImageView) findViewById(R.id.image2);
        word = (TextView) findViewById(R.id.word_text);

        image3=(ImageView) findViewById(R.id.image3);
        image4=(ImageView) findViewById(R.id.image4);
        word2 = (TextView) findViewById(R.id.word_text2);

        image5=(ImageView) findViewById(R.id.image5);
        image6=(ImageView) findViewById(R.id.image6);
        word3 = (TextView) findViewById(R.id.word_text3);

        image7=(ImageView) findViewById(R.id.image7);
        image8=(ImageView) findViewById(R.id.image8);
        word4 = (TextView) findViewById(R.id.word_text4);

        image9=(ImageView) findViewById(R.id.image9);
        image10=(ImageView) findViewById(R.id.image10);
        word5 = (TextView) findViewById(R.id.word_text5);

        image11=(ImageView) findViewById(R.id.image11);
        image12=(ImageView) findViewById(R.id.image12);
        image13=(ImageView) findViewById(R.id.image13);
        image14=(ImageView) findViewById(R.id.image14);
        word6 = (TextView) findViewById(R.id.word_text6);

        image15=(ImageView) findViewById(R.id.image15);
        image16=(ImageView) findViewById(R.id.image16);
        image17=(ImageView) findViewById(R.id.image17);
        image18=(ImageView) findViewById(R.id.image18);
        word7 = (TextView) findViewById(R.id.word_text7);

        image19=(ImageView) findViewById(R.id.image19);
        image20=(ImageView) findViewById(R.id.image20);
        image21=(ImageView) findViewById(R.id.image21);
        image22=(ImageView) findViewById(R.id.image22);
        word8 = (TextView) findViewById(R.id.word_text8);

        image23=(ImageView) findViewById(R.id.image23);
        image24=(ImageView) findViewById(R.id.image24);
        image25=(ImageView) findViewById(R.id.image25);
        image26=(ImageView) findViewById(R.id.image26);
        word9 = (TextView) findViewById(R.id.word_text9);

        image27=(ImageView) findViewById(R.id.image27);
        image28=(ImageView) findViewById(R.id.image28);
        image29=(ImageView) findViewById(R.id.image29);
        image30=(ImageView) findViewById(R.id.image30);
        word10 = (TextView) findViewById(R.id.word_text10);

        image31=(ImageView) findViewById(R.id.image31);
        image32=(ImageView) findViewById(R.id.image32);
        image33=(ImageView) findViewById(R.id.image33);
        image34=(ImageView) findViewById(R.id.image34);
        image35=(ImageView) findViewById(R.id.image35);
        image36=(ImageView) findViewById(R.id.image36);
        word11 = (TextView) findViewById(R.id.text_word11);

        image37=(ImageView) findViewById(R.id.image37);
        image38=(ImageView) findViewById(R.id.image38);
        image39=(ImageView) findViewById(R.id.image39);
        image40=(ImageView) findViewById(R.id.image40);
        image41=(ImageView) findViewById(R.id.image41);
        image42=(ImageView) findViewById(R.id.image42);
        word12 = (TextView) findViewById(R.id.text_word12);

        image43=(ImageView) findViewById(R.id.image43);
        image44=(ImageView) findViewById(R.id.image44);
        image45=(ImageView) findViewById(R.id.image45);
        image46=(ImageView) findViewById(R.id.image46);
        image47=(ImageView) findViewById(R.id.image47);
        image48=(ImageView) findViewById(R.id.image48);
        word13 = (TextView) findViewById(R.id.text_word13);

        image49=(ImageView) findViewById(R.id.image49);
        image50=(ImageView) findViewById(R.id.image50);
        image51=(ImageView) findViewById(R.id.image51);
        image52=(ImageView) findViewById(R.id.image52);
        image53=(ImageView) findViewById(R.id.image53);
        image54=(ImageView) findViewById(R.id.image54);
        word14 = (TextView) findViewById(R.id.text_word14);

        image55=(ImageView) findViewById(R.id.image55);
        image56=(ImageView) findViewById(R.id.image56);
        image57=(ImageView) findViewById(R.id.image57);
        image58=(ImageView) findViewById(R.id.image58);
        image59=(ImageView) findViewById(R.id.image59);
        image60=(ImageView) findViewById(R.id.image60);
        word15 = (TextView) findViewById(R.id.text_word15);


        relative_layout1 =(RelativeLayout) findViewById(R.id.relative_layout1);
        relative_layout2 =(RelativeLayout) findViewById(R.id.relative_layout2);
        relative_layout3 =(RelativeLayout) findViewById(R.id.relative_layout3);
        relative_layout4 =(RelativeLayout) findViewById(R.id.relative_layout4);
        relative_layout5 =(RelativeLayout) findViewById(R.id.relative_layout5);
        relative_layout6 =(RelativeLayout) findViewById(R.id.relative_layout6);
        relative_layout7 =(RelativeLayout) findViewById(R.id.relative_layout7);
        relative_layout8 =(RelativeLayout) findViewById(R.id.relative_layout8);
        relative_layout9 =(RelativeLayout) findViewById(R.id.relative_layout9);
        relative_layout10 =(RelativeLayout) findViewById(R.id.relative_layout10);
        relative_layout11 =(RelativeLayout) findViewById(R.id.relative_layout11);
        relative_layout12 =(RelativeLayout) findViewById(R.id.relative_layout12);
        relative_layout13 =(RelativeLayout) findViewById(R.id.relative_layout13);
        relative_layout14 =(RelativeLayout) findViewById(R.id.relative_layout14);
        relative_layout15 =(RelativeLayout) findViewById(R.id.relative_layout15);



        scoreText = (TextView) findViewById(R.id.score_text);
        timerText = (TextView) findViewById(R.id.timer_text);

        scoreText = (TextView) findViewById(R.id.score_text);
        timerText = (TextView) findViewById(R.id.timer_text);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/sky.ttf");
        scoreText.setTextColor(Color.rgb(125, 60, 152));
        scoreText.setTypeface(type);
        scoreText.setTextSize(25);

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
        setting.setOnClickListener(new FloatButtonAction());

        tf1 = Typeface.createFromAsset(getAssets(), "fonts/BalooBhai-Regular.ttf");

        contactList = new ArrayList<>();
        nameList = new ArrayList<>();
        second_list = new ArrayList();
        Animationss anim=new Animationss(getApplicationContext());

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);

        new GetContacts().execute();

       
        image1.setOnClickListener(new ButtonListener());
        image2.setOnClickListener(new ButtonListener());
        image1.setOnTouchListener(new TouchListener());
        image2.setOnTouchListener(new TouchListener());

        image3.setOnClickListener(new ButtonListener());
        image4.setOnClickListener(new ButtonListener());
        image3.setOnTouchListener(new TouchListener());
        image4.setOnTouchListener(new TouchListener());


        image29.setOnClickListener(new ButtonListener());
        image30.setOnClickListener(new ButtonListener());
        image29.setOnTouchListener(new TouchListener());
        image30.setOnTouchListener(new TouchListener());

        image5.setOnClickListener(new ButtonListener());
        image6.setOnClickListener(new ButtonListener());
        image5.setOnTouchListener(new TouchListener());
        image6.setOnTouchListener(new TouchListener());
        image7.setOnClickListener(new ButtonListener());
        image8.setOnClickListener(new ButtonListener());
        image7.setOnTouchListener(new TouchListener());
        image8.setOnTouchListener(new TouchListener());

        image9.setOnClickListener(new ButtonListener());
        image10.setOnClickListener(new ButtonListener());
        image9.setOnTouchListener(new TouchListener());
        image10.setOnTouchListener(new TouchListener());
        image11.setOnClickListener(new ButtonListener());
        image12.setOnClickListener(new ButtonListener());
        image11.setOnTouchListener(new TouchListener());
        image12.setOnTouchListener(new TouchListener());

        image25.setOnClickListener(new ButtonListener());
        image26.setOnClickListener(new ButtonListener());
        image25.setOnTouchListener(new TouchListener());
        image26.setOnTouchListener(new TouchListener());
        image27.setOnClickListener(new ButtonListener());
        image28.setOnClickListener(new ButtonListener());
        image27.setOnTouchListener(new TouchListener());
        image28.setOnTouchListener(new TouchListener());

        image13.setOnClickListener(new ButtonListener());
        image14.setOnClickListener(new ButtonListener());
        image13.setOnTouchListener(new TouchListener());
        image14.setOnTouchListener(new TouchListener());
        image15.setOnClickListener(new ButtonListener());
        image16.setOnClickListener(new ButtonListener());
        image15.setOnTouchListener(new TouchListener());
        image16.setOnTouchListener(new TouchListener());
        image17.setOnClickListener(new ButtonListener());
        image18.setOnClickListener(new ButtonListener());
        image17.setOnTouchListener(new TouchListener());
        image18.setOnTouchListener(new TouchListener());

        image19.setOnClickListener(new ButtonListener());
        image20.setOnClickListener(new ButtonListener());
        image19.setOnTouchListener(new TouchListener());
        image20.setOnTouchListener(new TouchListener());
        image21.setOnClickListener(new ButtonListener());
        image22.setOnClickListener(new ButtonListener());
        image21.setOnTouchListener(new TouchListener());
        image22.setOnTouchListener(new TouchListener());
        image23.setOnClickListener(new ButtonListener());
        image24.setOnClickListener(new ButtonListener());
        image23.setOnTouchListener(new TouchListener());
        image24.setOnTouchListener(new TouchListener());

        image25.setOnClickListener(new ButtonListener());
        image26.setOnClickListener(new ButtonListener());
        image25.setOnTouchListener(new TouchListener());
        image26.setOnTouchListener(new TouchListener());
        image27.setOnClickListener(new ButtonListener());
        image28.setOnClickListener(new ButtonListener());
        image27.setOnTouchListener(new TouchListener());
        image28.setOnTouchListener(new TouchListener());
        image29.setOnClickListener(new ButtonListener());
        image30.setOnClickListener(new ButtonListener());
        image29.setOnTouchListener(new TouchListener());
        image30.setOnTouchListener(new TouchListener());

        image31.setOnClickListener(new ButtonListener());
        image32.setOnClickListener(new ButtonListener());
        image31.setOnTouchListener(new TouchListener());
        image32.setOnTouchListener(new TouchListener());
        image33.setOnClickListener(new ButtonListener());
        image34.setOnClickListener(new ButtonListener());
        image33.setOnTouchListener(new TouchListener());
        image34.setOnTouchListener(new TouchListener());
        image35.setOnClickListener(new ButtonListener());
        image36.setOnClickListener(new ButtonListener());
        image35.setOnTouchListener(new TouchListener());
        image36.setOnTouchListener(new TouchListener());

        image37.setOnClickListener(new ButtonListener());
        image38.setOnClickListener(new ButtonListener());
        image37.setOnTouchListener(new TouchListener());
        image38.setOnTouchListener(new TouchListener());
        image39.setOnClickListener(new ButtonListener());
        image40.setOnClickListener(new ButtonListener());
        image39.setOnTouchListener(new TouchListener());
        image40.setOnTouchListener(new TouchListener());
        image41.setOnClickListener(new ButtonListener());
        image42.setOnClickListener(new ButtonListener());
        image41.setOnTouchListener(new TouchListener());
        image42.setOnTouchListener(new TouchListener());

        image43.setOnClickListener(new ButtonListener());
        image44.setOnClickListener(new ButtonListener());
        image43.setOnTouchListener(new TouchListener());
        image44.setOnTouchListener(new TouchListener());
        image45.setOnClickListener(new ButtonListener());
        image46.setOnClickListener(new ButtonListener());
        image45.setOnTouchListener(new TouchListener());
        image46.setOnTouchListener(new TouchListener());
        image47.setOnClickListener(new ButtonListener());
        image48.setOnClickListener(new ButtonListener());
        image47.setOnTouchListener(new TouchListener());
        image48.setOnTouchListener(new TouchListener());

        image49.setOnClickListener(new ButtonListener());
        image50.setOnClickListener(new ButtonListener());
        image49.setOnTouchListener(new TouchListener());
        image50.setOnTouchListener(new TouchListener());
        image51.setOnClickListener(new ButtonListener());
        image52.setOnClickListener(new ButtonListener());
        image51.setOnTouchListener(new TouchListener());
        image52.setOnTouchListener(new TouchListener());
        image53.setOnClickListener(new ButtonListener());
        image54.setOnClickListener(new ButtonListener());
        image53.setOnTouchListener(new TouchListener());
        image54.setOnTouchListener(new TouchListener());

        image55.setOnClickListener(new ButtonListener());
        image56.setOnClickListener(new ButtonListener());
        image55.setOnTouchListener(new TouchListener());
        image56.setOnTouchListener(new TouchListener());
        image57.setOnClickListener(new ButtonListener());
        image58.setOnClickListener(new ButtonListener());
        image57.setOnTouchListener(new TouchListener());
        image58.setOnTouchListener(new TouchListener());
        image59.setOnClickListener(new ButtonListener());
        image60.setOnClickListener(new ButtonListener());
        image59.setOnTouchListener(new TouchListener());
        image60.setOnTouchListener(new TouchListener());


        game_over_dialog=new Dialog(FourthLevelActivity.this);
        success_screen_dialog=new Dialog(FourthLevelActivity.this);
        information_box = new Dialog(FourthLevelActivity.this);
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
                successScreen();

            }
        }.start();
    }

    public void gameOver()
    {
        if(back.isPlaying())
        {
            back.stop();
        }

        HighScoreActivity.second_level=true;
        game_over=true;
        countDownTimer.cancel();
        LevelScreen.isLevel4=true;

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

        new saveScore().execute();
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
                Intent i = new Intent(FourthLevelActivity.this,LevelScreen.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
                count=3;
                correct=0;
                wrong=0;


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
                Intent i = new Intent(FourthLevelActivity.this, MainActivity.class);
                startActivity(i);
                game_over_dialog.dismiss();
                timerText.setText("");
                scoreText.setText(""+Score.resetScore());
                back.stop();
                wrong=0;
                correct=0;

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


        game_success=true;
        game_success=true;

        success_screen_dialog.setContentView(R.layout.activity_success_screen_intitial);



        wrong_text=(TextView) success_screen_dialog.findViewById(R.id.wrong_text2);
        wrong_result=(TextView) success_screen_dialog.findViewById(R.id.wrong_result2);
        correct_text=(TextView) success_screen_dialog.findViewById(R.id.correct_text2);
        correct_result=(TextView) success_screen_dialog.findViewById(R.id.correct_result2);
        scoreSucess=(TextView) success_screen_dialog.findViewById(R.id.score_text2);
        scoreSucResult=(TextView) success_screen_dialog.findViewById(R.id.score_result2);

        continue_button =(ImageButton) success_screen_dialog.findViewById(R.id.continue_button2);
        cancel_button =(ImageButton) success_screen_dialog.findViewById(R.id.cancel_button2);

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
        countDownTimer.cancel();
        success_background_sound.start();

        new saveScore().execute();
        // new ControlLevelActivity.saveScore().execute();

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(success_background_sound.isPlaying())
                    success_background_sound.stop();
                buttonSound.start();

                scoreText.setText(""+Score.resetScore());
                Intent i = new Intent(FourthLevelActivity.this, LevelScreen.class);
                startActivity(i);
                finish();
                count=3;
                correct=0;
                wrong=0;
                success_screen_dialog.dismiss();
                isClick=true;

                level2_status=true;



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
                Intent i = new Intent(FourthLevelActivity.this, MainActivity.class);
                startActivity(i);
                success_screen_dialog.dismiss();
                timerText.setText("");
                scoreText.setText(""+Score.resetScore());
                back.stop();
                wrong=0;
                correct=0;


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
                Intent mStartActivity = new Intent(FourthLevelActivity.this, MainActivity.class);
                int mPendingIntentId = 123456;
                PendingIntent mPendingIntent = PendingIntent.getActivity(FourthLevelActivity.this, mPendingIntentId, mStartActivity,
                        PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager) FourthLevelActivity.this.getSystemService(Context.ALARM_SERVICE);
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
                Intent mStartActivity = new Intent(FourthLevelActivity.this, MainActivity.class);
                startActivity(mStartActivity);
                finish();
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

    class ButtonListener implements View.OnClickListener{

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

        @Override
        public void onClick(View view) {
            final ImageView image=(ImageView) view;
            boolean first=false,first2=false,first3=false;



            if(image==image1 || image==image2){

                if(word.getTag().toString().equals(image.getTag().toString())) {

                    Log.d(TAG, "FOR 1");
                    Log.d(TAG, word.getTag().toString());
                    Log.d(TAG, image.getTag().toString());
                    //image.setImageResource(R.drawable.purple);
                    scoreText.setText("" + score.increaseScore());
                    match1 = true;

                    image.setImageResource(R.drawable.purple);
                    sucess.start();

                    correct++;

                    invisibleanimation(relative_layout1);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            visibleanimation(relative_layout2);
                            Animationss.game_over_slide.start();

                        }
                    }, 400);


                }

                if(!(word.getTag().toString().equals(image.getTag().toString()))){
                    scoreText.setText("" + score.decreaseScore());
                    wrong++;
                    count--;
                    fail.start();
                    if (wrong < 0)
                        wrong = 0;

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


            if(image==image3 || image==image4){
                if(word2.getTag().toString().equals(image.getTag().toString())) {


                    scoreText.setText("" + score.increaseScore());
                    match2 = true;
                    image.setImageResource(R.drawable.purple);
                    sucess.start();
                    correct++;

                    sucess.start();

                    invisibleanimation(relative_layout2);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            visibleanimation(relative_layout3);
                            Animationss.game_over_slide.start();

                        }
                    }, 400);



                }

                if(!(word2.getTag().toString().equals(image.getTag().toString()))){
                    scoreText.setText("" + score.decreaseScore());
                    wrong++;
                    count--;
                    fail.start();
                    if (wrong < 0)
                        wrong = 0;

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



            if(image==image5 || image==image6) {
                if (word3.getTag().toString().equals(image.getTag().toString())) {


                    scoreText.setText("" + score.increaseScore());
                    match3 = true;
                    correct++;
                    image.setImageResource(R.drawable.purple);
                    sucess.start();

                    invisibleanimation(relative_layout3);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            visibleanimation(relative_layout4);
                            Animationss.game_over_slide.start();

                        }
                    }, 400);
                }

                if(!(word3.getTag().toString().equals(image.getTag().toString()))){
                    scoreText.setText("" + score.decreaseScore());
                    wrong++;
                    count--;
                    fail.start();
                    if (wrong < 0)
                        wrong = 0;

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

            if(image==image7 || image==image8) {
                if (word4.getTag().toString().equals(image.getTag().toString())) {


                    scoreText.setText("" + score.increaseScore());
                    match4 = true;
                    correct++;
                    image.setImageResource(R.drawable.purple);
                    sucess.start();

                    invisibleanimation(relative_layout4);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            visibleanimation(relative_layout5);
                            Animationss.game_over_slide.start();

                        }
                    }, 400);

                }

                if(!(word4.getTag().toString().equals(image.getTag().toString()))){
                    scoreText.setText("" + score.decreaseScore());
                    wrong++;
                    count--;
                    fail.start();
                    if (wrong < 0)
                        wrong = 0;

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



            if(image==image9 || image==image10) {
                if (word5.getTag().toString().equals(image.getTag().toString())) {

                    scoreText.setText("" + score.increaseScore());
                    match5 = true;
                    correct++;
                    image.setImageResource(R.drawable.purple);
                    sucess.start();

                    invisibleanimation(relative_layout5);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            visibleanimation(relative_layout6);
                            Animationss.game_over_slide.start();

                        }
                    }, 400);

                }

                if(!(word5.getTag().toString().equals(image.getTag().toString()))){
                    scoreText.setText("" + score.decreaseScore());
                    wrong++;
                    count--;
                    fail.start();
                    if (wrong < 0)
                        wrong = 0;

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



            if(image==image11 || image==image12 || image==image13 || image==image14) {
                if (word6.getTag().toString().equals(image.getTag().toString())) {


                    scoreText.setText("" + score.increaseScore());
                    match6 = true;
                    correct++;
                    image.setImageResource(R.drawable.purple);
                    sucess.start();

                    invisibleanimation(relative_layout6);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            visibleanimation(relative_layout7);
                            Animationss.game_over_slide.start();

                        }
                    }, 400);

                }

                if(!(word6.getTag().toString().equals(image.getTag().toString()))){
                    scoreText.setText("" + score.decreaseScore());
                    wrong++;
                    count--;
                    fail.start();
                    if (wrong < 0)
                        wrong = 0;

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


            if(image==image15 || image==image16 || image==image17 || image==image18) {
                if (word7.getTag().toString().equals(image.getTag().toString())) {

                    scoreText.setText("" + score.increaseScore());
                    match7 = true;
                    correct++;
                    image.setImageResource(R.drawable.purple);
                    sucess.start();

                    invisibleanimation(relative_layout7);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            visibleanimation(relative_layout8);
                            Animationss.game_over_slide.start();

                        }
                    }, 400);

                }

                if(!(word7.getTag().toString().equals(image.getTag().toString()))){
                    scoreText.setText("" + score.decreaseScore());
                    wrong++;
                    count--;
                    fail.start();
                    if (wrong < 0)
                        wrong = 0;

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

            if(image==image19 || image==image20 || image==image21 || image==image22) {
                if (word8.getTag().toString().equals(image.getTag().toString())) {

                    scoreText.setText("" + score.increaseScore());
                    match8 = true;
                    correct++;
                    image.setImageResource(R.drawable.purple);
                    sucess.start();

                    invisibleanimation(relative_layout8);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            visibleanimation(relative_layout9);
                            Animationss.game_over_slide.start();

                        }
                    }, 400);

                }

                if(!(word8.getTag().toString().equals(image.getTag().toString()))){
                    scoreText.setText("" + score.decreaseScore());
                    wrong++;
                    count--;
                    fail.start();
                    if (wrong < 0)
                        wrong = 0;

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

            if(image==image23 || image==image24 || image==image25 || image==image26) {
                if (word9.getTag().toString().equals(image.getTag().toString())) {

                    scoreText.setText("" + score.increaseScore());
                    match9= true;
                    correct++;
                    image.setImageResource(R.drawable.purple);
                    sucess.start();

                    invisibleanimation(relative_layout9);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            visibleanimation(relative_layout10);
                            Animationss.game_over_slide.start();

                        }
                    }, 400);

                }

                if(!(word9.getTag().toString().equals(image.getTag().toString()))){
                    scoreText.setText("" + score.decreaseScore());
                    wrong++;
                    count--;
                    fail.start();
                    if (wrong < 0)
                        wrong = 0;

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

            if(image==image27 || image==image28 || image==image29 || image==image30) {
                if (word10.getTag().toString().equals(image.getTag().toString())) {

                    scoreText.setText("" + score.increaseScore());
                    match10 = true;
                    correct++;
                    image.setImageResource(R.drawable.purple);
                    sucess.start();

                    invisibleanimation(relative_layout10);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            visibleanimation(relative_layout11);
                            Animationss.game_over_slide.start();

                        }
                    }, 400);

                }

                if(!(word10.getTag().toString().equals(image.getTag().toString()))){
                    scoreText.setText("" + score.decreaseScore());
                    wrong++;
                    count--;
                    fail.start();
                    if (wrong < 0)
                        wrong = 0;

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


            if(image==image31 || image==image32 || image==image33 || image==image34 || image==image35 || image==image36) {
                if (word11.getTag().toString().equals(image.getTag().toString())) {


                    scoreText.setText("" + score.increaseScore());
                    match11 = true;
                    correct++;
                    image.setImageResource(R.drawable.purple);
                    sucess.start();

                    invisibleanimation(relative_layout11);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            visibleanimation(relative_layout12);
                            Animationss.game_over_slide.start();

                        }
                    }, 400);
                }

                if(!(word11.getTag().toString().equals(image.getTag().toString()))){
                    scoreText.setText("" + score.decreaseScore());
                    wrong++;
                    count--;
                    fail.start();
                    if (wrong < 0)
                        wrong = 0;

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



            if(image==image37 || image==image38 || image==image39 || image==image40 || image==image41 || image==image42) {
                if (word12.getTag().toString().equals(image.getTag().toString())) {


                    scoreText.setText("" + score.increaseScore());
                    match12 = true;
                    correct++;
                    image.setImageResource(R.drawable.purple);
                    sucess.start();

                    invisibleanimation(relative_layout12);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            visibleanimation(relative_layout13);
                            Animationss.game_over_slide.start();

                        }
                    }, 400);
                }

                if(!(word12.getTag().toString().equals(image.getTag().toString()))){
                    scoreText.setText("" + score.decreaseScore());
                    wrong++;
                    count--;
                    fail.start();
                    if (wrong < 0)
                        wrong = 0;

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


            if(image==image43 || image==image44 || image==image45 || image==image46 || image==image47 || image==image48) {
                if (word13.getTag().toString().equals(image.getTag().toString())) {


                    scoreText.setText("" + score.increaseScore());
                    match13 = true;
                    correct++;
                    image.setImageResource(R.drawable.purple);
                    sucess.start();

                    invisibleanimation(relative_layout13);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            visibleanimation(relative_layout14);
                            Animationss.game_over_slide.start();

                        }
                    }, 400);
                }

                if(!(word13.getTag().toString().equals(image.getTag().toString()))){
                    scoreText.setText("" + score.decreaseScore());
                    wrong++;
                    count--;
                    fail.start();
                    if (wrong < 0)
                        wrong = 0;

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


            if(image==image49 || image==image50 || image==image51 || image==image52 || image==image53 || image==image54) {
                if (word14.getTag().toString().equals(image.getTag().toString())) {


                    scoreText.setText("" + score.increaseScore());
                    match14 = true;
                    correct++;
                    image.setImageResource(R.drawable.purple);
                    sucess.start();


                    invisibleanimation(relative_layout14);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            visibleanimation(relative_layout15);
                            Animationss.game_over_slide.start();

                        }
                    }, 400);
                }

                if(!(word14.getTag().toString().equals(image.getTag().toString()))){
                    scoreText.setText("" + score.decreaseScore());
                    wrong++;
                    count--;
                    fail.start();
                    if (wrong < 0)
                        wrong = 0;

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

            if(image==image55 || image==image56 || image==image57 || image==image58 || image==image59 || image==image60) {
                if (word15.getTag().toString().equals(image.getTag().toString())) {

                    scoreText.setText("" + score.increaseScore());
                    match15 = true;
                    correct++;
                    image.setImageResource(R.drawable.purple);
                    sucess.start();

                    invisibleanimation(relative_layout15);



                }

                if(!(word15.getTag().toString().equals(image.getTag().toString()))){
                    scoreText.setText("" + score.decreaseScore());
                    wrong++;
                    count--;
                    fail.start();
                    if (wrong < 0)
                        wrong = 0;

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





            if (match1 == true && match2 == true && match3 == true && match4 == true && match5 == true && match6 == true &&  match7 == true &&
                    count > 0 &&  match8 == true &&  match9 == true &&  match10 == true &&  match11 == true &&  match12 == true &&
                    match13 == true && match14 == true && match15 == true) {
                successScreen();
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
            pDialog = new ProgressDialog(FourthLevelActivity.this);
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

                            Log.d("FOODS ELEMENTS ARE:",contactList.get(i).toString());
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
            image1.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image2.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image3.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image4.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image5.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image6.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image7.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image8.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image9.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image10.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image11.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image12.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image13.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image14.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image15.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image16.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image17.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image18.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image19.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image20.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image21.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image22.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image23.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image24.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image25.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image26.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image27.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image28.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image29.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image30.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image31.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image32.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image33.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image34.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image35.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image36.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image37.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image38.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image39.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image40.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image41.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image42.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image43.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image44.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image45.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image46.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image47.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image48.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image49.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image50.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image51.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image52.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image53.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image54.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image55.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image56.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image57.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image58.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image59.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));
            image60.setBackgroundDrawable(getResources().getDrawable(R.drawable.board3));





            //Collections.shuffle(contactList);
            //Collections.shuffle(nameList);
            int entered=0;
            Collections.shuffle(contactList);
            word.setText(contactList.get(0).get("word"));
            word.setTag(contactList.get(0).get("word"));
            word.setTypeface(tf1);
            word.setAnimation(image_anim);
            word.setTextSize(15);
            word.setTextColor(Color.rgb(125, 60, 152));
            imageLoader.displayImage(contactList.get(0).get("image_path"), image1);
            image1.setTag(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(1).get("image_path"), image2);
            image2.setTag("");


            entered++;

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);


            word2.setText(contactList.get(0).get("word"));
            word2.setTag(contactList.get(0).get("word"));
            word2.setTypeface(tf1);
            word2.setAnimation(image_anim);
            word2.setTextSize(15);
            word2.setTextColor(Color.rgb(125, 60, 152));
            imageLoader.displayImage(contactList.get(0).get("image_path"), image4);
            image4.setTag(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(1).get("image_path"), image3);
            image3.setTag("");

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);


            word3.setText(contactList.get(0).get("word"));
            word3.setTag(contactList.get(0).get("word"));
            word3.setTypeface(tf1);
            word3.setAnimation(image_anim);
            word3.setTextSize(15);
            word3.setTextColor(Color.rgb(125, 60, 152));
            imageLoader.displayImage(contactList.get(0).get("image_path"), image6);
            image6.setTag(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(1).get("image_path"), image5);
            image5.setTag("");

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);


            word4.setText(contactList.get(0).get("word"));
            word4.setTag(contactList.get(0).get("word"));
            word4.setTypeface(tf1);
            word4.setAnimation(image_anim);
            word4.setTextSize(15);
            word4.setTextColor(Color.rgb(125, 60, 152));
            imageLoader.displayImage(contactList.get(0).get("image_path"), image7);
            image7.setTag(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(1).get("image_path"), image8);
            image8.setTag("");

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);


            word5.setText(contactList.get(0).get("word"));
            word5.setTag(contactList.get(0).get("word"));
            word5.setTypeface(tf1);
            word5.setAnimation(image_anim);
            word5.setTextSize(15);
            word5.setTextColor(Color.rgb(125, 60, 152));
            imageLoader.displayImage(contactList.get(0).get("image_path"), image10);
            image10.setTag(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(1).get("image_path"), image9);
            image9.setTag("");

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word6.setText(contactList.get(0).get("word"));
            word6.setTag(contactList.get(0).get("word"));
            word6.setTypeface(tf1);
            word6.setAnimation(image_anim);
            word6.setTextSize(15);
            word6.setTextColor(Color.rgb(125, 60, 152));
            imageLoader.displayImage(contactList.get(0).get("image_path"), image13);
            image13.setTag(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(2).get("image_path"), image12);
            image12.setTag("");

            imageLoader.displayImage(contactList.get(3).get("image_path"), image11);
            image11.setTag("");
            imageLoader.displayImage(contactList.get(4).get("image_path"), image14);
            image14.setTag("");

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);


            word7.setText(contactList.get(0).get("word"));
            word7.setTag(contactList.get(0).get("word"));
            word7.setTypeface(tf1);
            word7.setAnimation(image_anim);
            word7.setTextSize(15);
            word7.setTextColor(Color.rgb(125, 60, 152));
            imageLoader.displayImage(contactList.get(0).get("image_path"), image18);
            image18.setTag(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(1).get("image_path"), image16);
            image16.setTag("");

            imageLoader.displayImage(contactList.get(4).get("image_path"), image17);
            image17.setTag("");
            imageLoader.displayImage(contactList.get(5).get("image_path"), image15);
            image15.setTag("");

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);


            word8.setText(contactList.get(0).get("word"));
            word8.setTag(contactList.get(0).get("word"));
            word8.setTypeface(tf1);
            word8.setAnimation(image_anim);
            word8.setTextSize(15);
            word8.setTextColor(Color.rgb(125, 60, 152));
            imageLoader.displayImage(contactList.get(0).get("image_path"), image19);
            image19.setTag(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(1).get("image_path"), image20);
            image20.setTag("");

            imageLoader.displayImage(contactList.get(4).get("image_path"), image21);
            image21.setTag("");
            imageLoader.displayImage(contactList.get(5).get("image_path"), image22);
            image22.setTag("");

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);


            word9.setText(contactList.get(0).get("word"));
            word9.setTag(contactList.get(0).get("word"));
            word9.setTypeface(tf1);
            word9.setAnimation(image_anim);
            word9.setTextSize(15);
            word9.setTextColor(Color.rgb(125, 60, 152));
            imageLoader.displayImage(contactList.get(0).get("image_path"), image24);
            image24.setTag(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(1).get("image_path"), image23);
            image23.setTag("");

            imageLoader.displayImage(contactList.get(4).get("image_path"), image25);
            image25.setTag("");
            imageLoader.displayImage(contactList.get(5).get("image_path"), image26);
            image26.setTag("");

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);


            word10.setText(contactList.get(0).get("word"));
            word10.setTag(contactList.get(0).get("word"));
            word10.setTypeface(tf1);
            word10.setAnimation(image_anim);
            word10.setTextSize(15);
            word10.setTextColor(Color.rgb(125, 60, 152));
            imageLoader.displayImage(contactList.get(0).get("image_path"), image29);
            image29.setTag(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(1).get("image_path"), image28);
            image28.setTag("");

            imageLoader.displayImage(contactList.get(4).get("image_path"), image27);
            image27.setTag("");
            imageLoader.displayImage(contactList.get(5).get("image_path"), image30);
            image30.setTag("");

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word11.setText(contactList.get(0).get("word"));
            word11.setTag(contactList.get(0).get("word"));
            word11.setTypeface(tf1);
            word11.setAnimation(image_anim);
            word11.setTextSize(15);
            word11.setTextColor(Color.rgb(125, 60, 152));
            imageLoader.displayImage(contactList.get(0).get("image_path"), image34);
            image34.setTag(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(1).get("image_path"), image32);
            image32.setTag("");

            imageLoader.displayImage(contactList.get(4).get("image_path"), image33);
            image33.setTag("");
            imageLoader.displayImage(contactList.get(5).get("image_path"), image31);
            image31.setTag("");
            imageLoader.displayImage(contactList.get(6).get("image_path"), image35);
            image35.setTag("");
            imageLoader.displayImage(contactList.get(7).get("image_path"), image36);
            image36.setTag("");

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word12.setText(contactList.get(0).get("word"));
            word12.setTag(contactList.get(0).get("word"));
            word12.setTypeface(tf1);
            word12.setAnimation(image_anim);
            word12.setTextSize(15);
            word12.setTextColor(Color.rgb(125, 60, 152));
            imageLoader.displayImage(contactList.get(0).get("image_path"), image41);
            image41.setTag(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(1).get("image_path"), image38);
            image38.setTag("");

            imageLoader.displayImage(contactList.get(4).get("image_path"), image39);
            image39.setTag("");
            imageLoader.displayImage(contactList.get(5).get("image_path"), image40);
            image40.setTag("");
            imageLoader.displayImage(contactList.get(6).get("image_path"), image37);
            image37.setTag("");
            imageLoader.displayImage(contactList.get(7).get("image_path"), image42);
            image42.setTag("");

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word13.setText(contactList.get(0).get("word"));
            word13.setTag(contactList.get(0).get("word"));
            word13.setTypeface(tf1);
            word13.setAnimation(image_anim);
            word13.setTextSize(15);
            word13.setTextColor(Color.rgb(125, 60, 152));
            imageLoader.displayImage(contactList.get(0).get("image_path"), image43);
            image43.setTag(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(1).get("image_path"), image44);
            image44.setTag("");

            imageLoader.displayImage(contactList.get(4).get("image_path"), image45);
            image45.setTag("");
            imageLoader.displayImage(contactList.get(5).get("image_path"), image46);
            image46.setTag("");
            imageLoader.displayImage(contactList.get(6).get("image_path"), image47);
            image47.setTag("");
            imageLoader.displayImage(contactList.get(7).get("image_path"), image48);
            image48.setTag("");

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word14.setText(contactList.get(0).get("word"));
            word14.setTag(contactList.get(0).get("word"));
            word14.setTypeface(tf1);
            word14.setAnimation(image_anim);
            word14.setTextSize(15);
            word14.setTextColor(Color.rgb(125, 60, 152));
            imageLoader.displayImage(contactList.get(0).get("image_path"), image50);
            image50.setTag(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(1).get("image_path"), image49);
            image49.setTag("");

            imageLoader.displayImage(contactList.get(4).get("image_path"), image51);
            image51.setTag("");
            imageLoader.displayImage(contactList.get(5).get("image_path"), image52);
            image52.setTag("");
            imageLoader.displayImage(contactList.get(6).get("image_path"), image53);
            image53.setTag("");
            imageLoader.displayImage(contactList.get(7).get("image_path"), image54);
            image54.setTag("");

            contactList.remove(0);
            nameList.remove(0);
            Collections.shuffle(contactList);

            word15.setText(contactList.get(0).get("word"));
            word15.setTag(contactList.get(0).get("word"));
            word15.setTypeface(tf1);
            word15.setAnimation(image_anim);
            word15.setTextSize(15);
            word15.setTextColor(Color.rgb(125, 60, 152));
            imageLoader.displayImage(contactList.get(0).get("image_path"), image60);
            image60.setTag(contactList.get(0).get("word"));
            imageLoader.displayImage(contactList.get(1).get("image_path"), image56);
            image56.setTag("");

            imageLoader.displayImage(contactList.get(4).get("image_path"), image57);
            image57.setTag("");
            imageLoader.displayImage(contactList.get(5).get("image_path"), image58);
            image58.setTag("");
            imageLoader.displayImage(contactList.get(6).get("image_path"), image59);
            image59.setTag("");
            imageLoader.displayImage(contactList.get(7).get("image_path"), image55);
            image55.setTag("");

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

                if (LevelScreen.isLevel4 == true) {
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
