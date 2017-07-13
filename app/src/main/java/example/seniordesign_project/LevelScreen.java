package example.seniordesign_project;

import android.app.Dialog;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

import static example.seniordesign_project.Animationss.game_over_slide;
import static example.seniordesign_project.Animationss.level_sound;
import static example.seniordesign_project.Animationss.success_background_sound;
import static example.seniordesign_project.ControlLevelActivity.contactList;
import static example.seniordesign_project.R.raw.fairy;

public class LevelScreen extends AppCompatActivity {

    public static String level;
    MediaPlayer mp;
    public static  ImageButton level1,level2,level3,level4;
    Dialog level2_dialog,level_dialog;
    boolean isEntered=false;
    public static boolean level1_state=true,level2_state=true,level3_state=true,level4_state=true;
    TextView statusText,statusTextResult,scoreText,scoreTextResult,score_result_text;
    TextView score_level_text,score_level_result_text,level_text,level_result_text;
    ImageButton playButton,cancelButton,play_button,cancel_button;
    public static int count=0;
    ImageView level_image,change_image;
    static boolean isLevel1=false,isLevel2=false,isLevel3=false,isLevel4=false;
    boolean isFail=false;
    Dialog category_dialog;

    Typeface tf1;

    private static final String PREFS_LAST_IMG = "prefs_last_img";
    private SharedPreferences mPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_level_screen);
        level1 = (ImageButton) findViewById(R.id.level1_button);
        level2 = (ImageButton) findViewById(R.id.level2_button);
        level3 = (ImageButton) findViewById(R.id.level3_button);
        level4 = (ImageButton) findViewById(R.id.level4_button);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
        level2_dialog = new Dialog(this);
        level_dialog = new Dialog(this);

        tf1 = Typeface.createFromAsset(getAssets(), "fonts/BalooBhai-Regular.ttf");


        if (PlayScreen.isClick == true || PlayScreen.isGameOver==true) {
            /*level2.setOnClickListener(null);
            level2.setOnTouchListener(null);
            level3.setOnClickListener(null);
            level3_state=false;
            level4_state=false;
            level3.setOnTouchListener(null);
            level4.setOnClickListener(null);
            level4.setOnTouchListener(null);
            level1.setImageResource(R.drawable.level1_success);
            ControlLevelActivity.game_success = false;*/
            detectLevelScore();
             // setLevel_dialog();
        }


        if (SecondLevelActivity.isClick == true || SecondLevelActivity.isGameOver==true) {
            /*level2.setOnClickListener(null);
            level2.setOnTouchListener(null);
            level3.setOnClickListener(null);
            level3_state=false;
            level4_state=false;
            level3.setOnTouchListener(null);
            level4.setOnClickListener(null);
            level4.setOnTouchListener(null);
            level1.setImageResource(R.drawable.level1_success);
            ControlLevelActivity.game_success = false;*/
            detectLevelScore();
            // setLevel_dialog();
        }


        if (ThirdLevelActivity.isClick == true ||ThirdLevelActivity.isGameOver==true) {

          detectLevelScore();
        }

        if (FourthLevelActivity.isGameOver==true) {
           detectLevelScore();
        }
        if (ControlLevelActivity.game_success == true) {

            if (Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) < 30) {

                level2.setClickable(false);
                level3.setClickable(false);
                level4.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        setLevel_dialog();
                        Animationss.level_sound.start();
                    }
                }, 700);

            } else if (Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) >= 30 &&
                    Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) <= 60) {

                level1.setImageResource(R.drawable.level1_success);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        setLevel_dialog();
                        Animationss.level_sound.start();
                    }
                }, 700);

            } else if (Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) >= 70 &&
                    Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) <= 100) {

                level1.setImageResource(R.drawable.level1_success);
                level2.setImageResource(R.drawable.level2_success);


                level3.setClickable(false);
                level4.setClickable(false);
                isLevel3=true;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        setLevel_dialog();
                        Animationss.level_sound.start();
                    }
                }, 700);

            } else if (Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) >= 110 &&
                    Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) <= 130) {

                level1.setImageResource(R.drawable.level1_success);
                level2.setImageResource(R.drawable.level2_success);
                level3.setImageResource(R.drawable.level3_success);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        setLevel_dialog();
                        Animationss.level_sound.start();
                    }
                }, 700);

            }
        }
        if (level1_state == true) {

            level1.setOnTouchListener(new View.OnTouchListener() {
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


            level1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    level = "1";
                    mp.start();
                    isLevel1 = true;
                    Intent intent = new Intent(LevelScreen.this, PlayScreen.class);
                    startActivity(intent);
                }
            });
        }

        if (level2_state == true) {
            level2.setOnTouchListener(new View.OnTouchListener() {
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


            level2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    level = "2";
                    mp.start();
                    isLevel2 = true;

                    Intent intent = new Intent(LevelScreen.this, SecondLevelActivity.class);
                    startActivity(intent);
                }
            });
        }
        if (level3_state == true) {
            level3.setOnTouchListener(new View.OnTouchListener() {
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


            level3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    level = "3";
                    mp.start();
                    isLevel3 = true;

                    Intent intent = new Intent(LevelScreen.this, ThirdLevelActivity.class);
                    startActivity(intent);
                }
            });
        }

        if (level4_state == true) {

            level4.setOnTouchListener(new View.OnTouchListener() {
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


            level4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    level = "4";
                    mp.start();
                    isLevel4 = true;

                    Intent intent = new Intent(LevelScreen.this, FourthLevelActivity.class);
                    startActivity(intent);
                }
            });

        }
    }


    public void setLevel_dialog()
    {
        level2_dialog.setContentView(R.layout.activity_level_two);
        statusText=(TextView) level2_dialog.findViewById(R.id.level_status_text);
        statusTextResult=(TextView) level2_dialog.findViewById(R.id.level_status_result);
        scoreText=(TextView) level2_dialog.findViewById(R.id.score_text);
        scoreTextResult=(TextView) level2_dialog.findViewById(R.id.score_text_result);
        change_image=(ImageView) level2_dialog.findViewById(R.id.imageView27);

        playButton=(ImageButton) level2_dialog.findViewById(R.id.play_button);
        cancelButton=(ImageButton) level2_dialog.findViewById(R.id.cancel_button);

        statusText.setTextSize(15);
        statusTextResult.setTypeface(tf1);
        statusTextResult.setTextSize(15);
        statusText.setTypeface(tf1);

        scoreText.setTextSize(15);
        scoreText.setTypeface(tf1);
        scoreTextResult.setTextSize(15);
        scoreTextResult.setTypeface(tf1);

        scoreTextResult.setText(ControlLevelActivity.scoreSucResult.getText().toString());

        if (Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) < 30) {
            statusTextResult.setText("LEVEL 1");
            level="1";
            change_image.setImageResource(R.drawable.level_1_pink);

        }

        if (Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) >= 30 &&
                Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) <= 60   ) {
            statusTextResult.setText("LEVEL 2");
            level="2";
            change_image.setImageResource(R.drawable.level2_pink);


        }

        if (Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) >= 70 &&
                Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) <= 100   ) {
            statusTextResult.setText("LEVEL 3");
            level="3";
            change_image.setImageResource(R.drawable.level3_pink);

        }

        if (Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) >= 110 &&
                Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) <= 130   ) {
            statusTextResult.setText("LEVEL 4");
            level="4";
            change_image.setImageResource(R.drawable.level4_pink);

        }
        game_over_slide.start();
        level2_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        level2_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        level2_dialog.setCanceledOnTouchOutside(false);
        level2_dialog.setCancelable(false);
        level2_dialog.show();

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animationss.buttonSound.start();

                if (Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) < 30) {
                    Intent i=new Intent(LevelScreen.this,PlayScreen.class);
                    startActivity(i);



                }

                if (Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) >= 30 &&
                        Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) <= 60   ) {
                    Intent i=new Intent(LevelScreen.this,SecondLevelActivity.class);
                    startActivity(i);


                }

                if (Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) >= 70 &&
                        Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) <= 100   ) {
                    Intent i=new Intent(LevelScreen.this,ThirdLevelActivity.class);
                    startActivity(i);

                }

                if (Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) >= 110 &&
                        Integer.valueOf(ControlLevelActivity.scoreSucResult.getText().toString()) <= 130   ) {
                    Intent i=new Intent(LevelScreen.this,FourthLevelActivity.class);
                    startActivity(i);


                }



            }
        });

        playButton.setOnTouchListener(new View.OnTouchListener() {
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

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animationss.buttonSound.start();
                Intent i=new Intent(LevelScreen.this,MainActivity.class);
                startActivity(i);

            }
        });

        cancelButton.setOnTouchListener(new View.OnTouchListener() {
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


    public void detectLevelScore()
    {
        level_dialog.setContentView(R.layout.activity_level_dialog);
        level_text=(TextView) level_dialog.findViewById(R.id.level_text);
        level_result_text=(TextView) level_dialog.findViewById(R.id.level_result_text);
        score_level_text=(TextView)  level_dialog.findViewById(R.id.score_level_text);
        score_level_result_text=(TextView) level_dialog.findViewById(R.id.score_level_result);
        level_image=(ImageView) level_dialog.findViewById(R.id.level_image);

        play_button=(ImageButton) level_dialog.findViewById(R.id.play_level_button);
        cancel_button=(ImageButton) level_dialog.findViewById(R.id.cancel_level_button);

        level_text.setTextSize(16);
        level_text.setTypeface(tf1);
        score_level_text.setTextSize(16);
        score_level_text.setTypeface(tf1);
        score_level_result_text.setTypeface(tf1);
        score_level_result_text.setTextSize(16);
        level_result_text.setTextSize(16);
        level_result_text.setTypeface(tf1);
        ControlLevelActivity.game_success=false;;


        if(PlayScreen.isGameOver==true ) {
            if (Integer.valueOf(PlayScreen.total_score_result.getText().toString()) < 30) {
                //level_result_text.setText("LEVEL 1");


                    level_image.setImageResource(R.drawable.level_1_pink);
                    level = "1";

                //ControlLevelActivity.game_success = false;

                level_result_text.setText("1");
                score_level_result_text.setText("30");
                //level_image.setImageResource(R.drawable.level_1_pink);
                //level_result_text.setText("1");

                game_over_slide.start();
                level_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                level_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                level_dialog.setCanceledOnTouchOutside(false);
                level_dialog.setCancelable(false);
                level_dialog.show();

                play_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Animationss.buttonSound.start();
                        Intent intent = new Intent(LevelScreen.this,PlayScreen.class);
                        startActivity(intent);

                        //level_dialog.dismiss();
                       // level_dialog.dismiss();




                    }
                });

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

                cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Animationss.buttonSound.start();

                        Intent intent=new Intent(LevelScreen.this,MainActivity.class);
                        startActivity(intent);
                        level_dialog.dismiss();

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
        }
        if(PlayScreen.isClick==true ) {


            if (Integer.valueOf(PlayScreen.scoreSucResult.getText().toString()) >= 30) {


                level_image.setImageResource(R.drawable.level2_pink);
                level = "2";

//                ControlLevelActivity.game_success = false;

                level_result_text.setText("2");
                score_level_result_text.setText("40");
                //level_image.setImageResource(R.drawable.level_1_pink);
                //level_result_text.setText("1");

                game_over_slide.start();
                level_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                level_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                level_dialog.setCanceledOnTouchOutside(false);
                level_dialog.setCancelable(false);
                level_dialog.show();

                play_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Animationss.buttonSound.start();
                        isLevel2=true;
                        Intent intent = new Intent(LevelScreen.this,SecondLevelActivity.class);
                        startActivity(intent);
                       // level_dialog.dismiss();




                       // finish();


                    }
                });

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

                cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Animationss.buttonSound.start();

                        Intent intent=new Intent(LevelScreen.this,MainActivity.class);
                        startActivity(intent);
                        level_dialog.dismiss();

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

        }
        if(SecondLevelActivity.isGameOver==true) {
            if (Integer.valueOf(SecondLevelActivity.total_score_result.getText().toString()) < 40) {

                level = "1";


                level_image.setImageResource(R.drawable.level_1_pink);

                level_result_text.setText("1");
                score_level_result_text.setText("30");
                //level_image.setImageResource(R.drawable.level_1_pink);
                //level_result_text.setText("1");


                game_over_slide.start();
                level_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                level_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                level_dialog.setCanceledOnTouchOutside(false);
                level_dialog.setCancelable(false);
                level_dialog.show();

                play_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Animationss.buttonSound.start();
                        Intent intent = new Intent(LevelScreen.this, PlayScreen.class);
                        startActivity(intent);
                        //finish();
                        level_dialog.dismiss();
                        isLevel2=true;




                    }
                });

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

                cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Animationss.buttonSound.start();

                        Intent intent=new Intent(LevelScreen.this,MainActivity.class);
                        startActivity(intent);
                        level_dialog.dismiss();


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

        }
        if(SecondLevelActivity.isClick==true){

            if (Integer.valueOf(SecondLevelActivity.scoreSucResult.getText().toString()) >= 40) {

                level = "3";
                level_image.setImageResource(R.drawable.level3_pink);

                level_result_text.setText(level);
                score_level_result_text.setText("160");
                //level_image.setImageResource(R.drawable.level_1_pink);
                //level_result_text.setText("1");

                game_over_slide.start();
                level_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                level_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                level_dialog.setCanceledOnTouchOutside(false);
                level_dialog.setCancelable(false);
                level_dialog.show();

                play_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Animationss.buttonSound.start();
                        Intent intent = new Intent(LevelScreen.this,ThirdLevelActivity.class);
                        startActivity(intent);
                        isLevel2=true;
                        level_dialog.dismiss();


                    }
                });

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

                cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Animationss.buttonSound.start();
                        Intent intent=new Intent(LevelScreen.this,MainActivity.class);
                        startActivity(intent);
                       level_dialog.dismiss();
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

        }

        if(ThirdLevelActivity.isGameOver==true) {
            if (Integer.valueOf(ThirdLevelActivity.total_score_result.getText().toString()) < 160) {

                level = "2";

                level_image.setImageResource(R.drawable.level2_pink);

                level_result_text.setText(level);
                score_level_result_text.setText("40");
                //level_image.setImageResource(R.drawable.level_1_pink);
                //level_result_text.setText("1");

                game_over_slide.start();
                level_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                level_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                level_dialog.setCanceledOnTouchOutside(false);
                level_dialog.setCancelable(false);
                level_dialog.show();

                play_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Animationss.buttonSound.start();
                        Intent intent = new Intent(LevelScreen.this, SecondLevelActivity.class);
                        startActivity(intent);
                        isLevel3=true;
                        level_dialog.dismiss();


                    }
                });

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

                cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Animationss.buttonSound.start();

                        Intent intent=new Intent(LevelScreen.this,MainActivity.class);
                        startActivity(intent);
                        level_dialog.dismiss();

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

            if (Integer.valueOf(ThirdLevelActivity.total_score_result.getText().toString()) >= 160) {

                level = "4";

                level_image.setImageResource(R.drawable.level4_pink);

                level_result_text.setText(level);
                score_level_result_text.setText("120");
                //level_image.setImageResource(R.drawable.level_1_pink);
                //level_result_text.setText("1");

                game_over_slide.start();
                level_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                level_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                level_dialog.setCanceledOnTouchOutside(false);
                level_dialog.setCancelable(false);
                level_dialog.show();

                play_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Animationss.buttonSound.start();
                        Intent intent = new Intent(LevelScreen.this,FourthLevelActivity.class);
                        startActivity(intent);
                        isLevel3=true;level_dialog.dismiss();


                    }
                });

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

                cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Animationss.buttonSound.start();

                        Intent intent=new Intent(LevelScreen.this,MainActivity.class);
                        startActivity(intent);
                        level_dialog.dismiss();

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


        }



        if(ThirdLevelActivity.isClick==true){

            if (Integer.valueOf(ThirdLevelActivity.total_score_result.getText().toString()) >= 160) {

                level = "4";
                level_image.setImageResource(R.drawable.level4_pink);

                level_result_text.setText(level);
                score_level_result_text.setText("120");
                //level_image.setImageResource(R.drawable.level_1_pink);
                //level_result_text.setText("1");

                game_over_slide.start();
                level_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                level_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                level_dialog.setCanceledOnTouchOutside(false);
                level_dialog.setCancelable(false);
                level_dialog.show();

                play_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Animationss.buttonSound.start();
                        Intent intent = new Intent(LevelScreen.this,FourthLevelActivity.class);
                        startActivity(intent);
                        isLevel3=true;
                         level_dialog.dismiss();

                    }
                });

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

                cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Animationss.buttonSound.start();

                        Intent intent=new Intent(LevelScreen.this,MainActivity.class);
                        startActivity(intent);
                        level_dialog.dismiss();

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

        }

        if(FourthLevelActivity.isGameOver==true) {
            if (Integer.valueOf(FourthLevelActivity.total_score_result.getText().toString()) < 120) {

                level = "3";

                level_image.setImageResource(R.drawable.level3_pink);

                level_result_text.setText(level);
                score_level_result_text.setText("160");
                //level_image.setImageResource(R.drawable.level_1_pink);
                //level_result_text.setText("1");

                game_over_slide.start();
                level_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                level_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                level_dialog.setCanceledOnTouchOutside(false);
                level_dialog.setCancelable(false);
                level_dialog.show();

                play_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Animationss.buttonSound.start();
                        Intent intent = new Intent(LevelScreen.this, ThirdLevelActivity.class);
                        startActivity(intent);
                        level_dialog.dismiss();
                        isLevel4=true;

                    }
                });

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

                cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Animationss.buttonSound.start();

                        Intent intent=new Intent(LevelScreen.this,MainActivity.class);
                        startActivity(intent);
                        level_dialog.dismiss();

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

            if (Integer.valueOf(FourthLevelActivity.total_score_result.getText().toString()) >= 120 &&FourthLevelActivity.isClick==true) {

              successCategory();


            }

        }

        if(FourthLevelActivity.isClick==true){

            if (Integer.valueOf(FourthLevelActivity.scoreSucResult.getText().toString()) >= 120) {
                isLevel4=true;
                successCategory();


            }

        }


    }



    public void successCategory()
    {
        category_dialog=new Dialog(LevelScreen.this);
        category_dialog.setContentView(R.layout.category_success);

        ImageButton continue_button=(ImageButton) category_dialog.findViewById(R.id.continueButton);
        ImageButton cancel_button=(ImageButton) category_dialog.findViewById(R.id.cancelButton);
        ControlLevelActivity.game_success=false;;

        game_over_slide.start();
        category_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        category_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        category_dialog.setCanceledOnTouchOutside(false);
        category_dialog.setCancelable(false);
        category_dialog.show();


        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animationss.buttonSound.start();

                Intent intent=new Intent(LevelScreen.this,MainActivity.class);
                startActivity(intent);
                category_dialog.dismiss();



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
                Animationss.buttonSound.start();
                Intent i = new Intent(LevelScreen.this, MainActivity.class);
                startActivity(i);

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
}



