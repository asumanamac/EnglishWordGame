package example.seniordesign_project;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private TextView txtName;
    private TextView txtEmail;
    public static String name,email;
    private SQLiteHandler db;
    private SessionManager session;

    ImageButton playButton,logoutButton,btnLogout,scoreButton,exitButton,helpButton;
    Animation animation;
    TextView textView;
    MediaPlayer mp;
    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    String name1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = (TextView) findViewById(R.id.textView7);
        btnLogout = (ImageButton) findViewById(R.id.logoutButton);

        playButton =(ImageButton) findViewById(R.id.playButton);
        scoreButton =(ImageButton) findViewById(R.id.scoreButton);
        helpButton=(ImageButton) findViewById(R.id.helpButton);
        exitButton=(ImageButton) findViewById(R.id.exitButton);


        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_alpha);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }


        HashMap<String, String> user = db.getUserDetails();

        name = user.get("name");
        email=user.get("email");

        // Displaying the user details on the screen
        txtName.setText(name);

        /**final AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("You can click Play button for playing the game");
        dlgAlert.setTitle("Information Box");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setIcon(R.drawable.logo);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });**/

        playButton .setOnTouchListener(new View.OnTouchListener() {

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
                        ImageButton view = (ImageButton) v;
                        //clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }

                return false;
            }
        });


        playButton.setOnClickListener(new View.OnClickListener()
                                      {

                                          @Override
                                          public void onClick(View v) {
                                              mp.start();

                                              Intent i=new Intent(MainActivity.this,SwitchesActivities.class);
                                              startActivity(i);
                                          }
                                      }
        );

        scoreButton .setOnTouchListener(new View.OnTouchListener() {

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
                        ImageButton view = (ImageButton) v;
                        //clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }

                return false;
            }
        });


        scoreButton.setOnClickListener(new View.OnClickListener()
                                      {

            @Override
            public void onClick(View v) {
                mp.start();
             Intent i=new Intent(MainActivity.this,ScoreActivity.class);
              startActivity(i);
            }
           }
        );

        helpButton .setOnTouchListener(new View.OnTouchListener() {

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
                        ImageButton view = (ImageButton) v;
                        //clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }

                return false;
            }
        });


      helpButton.setOnClickListener(new View.OnClickListener()
                                       {

                                           @Override
                                           public void onClick(View v) {
                                               mp.start();
                                               Intent i=new Intent(MainActivity.this,HelpActivity.class);
                                               startActivity(i);
                                           }
                                       }
        );

       exitButton .setOnTouchListener(new View.OnTouchListener() {

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
                        ImageButton view = (ImageButton) v;
                        //clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }

                return false;
            }
        });


        exitButton.setOnClickListener(new View.OnClickListener()
                                       {

                                           @Override
                                           public void onClick(View v) {
                                               mp.start();
                                               System.exit(0);
                                           }
                                       }
        );

        btnLogout .setOnTouchListener(new View.OnTouchListener() {

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
                        ImageButton view = (ImageButton) v;
                        //clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }

                return false;
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.resetTables();
                mp.start();
                logoutUser();

            }
        });

    }


    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
      Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }




}