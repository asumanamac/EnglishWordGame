package example.seniordesign_project;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import static android.R.attr.visible;

/**
 * Created by - on 7.2.2017.
 */

public class Animationss {
    public static Animation open, close, clockwise, anticlokwise,middle,image_anim,fade_in,fade_out;
    FloatingActionButton setting, volume, out, home;
    public static MediaPlayer sucess, fail, back, buttonSound;
    public static MediaPlayer game_over_slide,game_over_sound,success_background_sound,level_sound;
    public static Typeface tf1;
    public static CountDownTimer countDownTimer;
    public static boolean isPaused = false;
    public static boolean isCanceled = false;
    public long timeRemaining = 0;
    Context context;
    public Animationss(Context context)
    {
        this.context=context;

        back = MediaPlayer.create(context, R.raw.background);
        fail = MediaPlayer.create(context, R.raw.fail);
        buttonSound = MediaPlayer.create(context, R.raw.buttonsound);
        sucess = MediaPlayer.create(context, R.raw.win);
        game_over_slide = MediaPlayer.create(context, R.raw.gameoversound);
        game_over_sound = MediaPlayer.create(context, R.raw.gameoverbackground);
        success_background_sound = MediaPlayer.create(context, R.raw.successbackground);
        level_sound=MediaPlayer.create(context,R.raw.fairy);

        open = AnimationUtils.loadAnimation(context, R.anim.fab_open);
        close = AnimationUtils.loadAnimation(context, R.anim.fab_close);
        clockwise = AnimationUtils.loadAnimation(context, R.anim.rotate_clokwise);
        anticlokwise = AnimationUtils.loadAnimation(context, R.anim.rotate_anticlockwise);
        middle = AnimationUtils.loadAnimation(context, R.anim.anim_middle);
        image_anim=AnimationUtils.loadAnimation(context, R.anim.image_animation);
        fade_in =AnimationUtils.loadAnimation(context, R.anim.fade_in);
        fade_out =AnimationUtils.loadAnimation(context, R.anim.fade_out);
        middle.setInterpolator((new AccelerateDecelerateInterpolator()));
        middle.setFillAfter(true);


    }



    public static void animateViewFromBottomToTop(final View view){

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {

                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                final int TRANSLATION_Y = view.getHeight();
                view.setTranslationY(TRANSLATION_Y);
                view.setVisibility(View.GONE);
                view.animate()
                        .translationYBy(-TRANSLATION_Y)
                        .setDuration(500)
                        .setStartDelay(200)
                        .setListener(new AnimatorListenerAdapter() {

                            @Override
                            public void onAnimationStart(final Animator animation) {

                                view.setVisibility(View.VISIBLE);
                            }
                        })
                        .start();
            }
        });
    }
    }




