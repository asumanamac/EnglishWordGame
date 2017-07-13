package example.seniordesign_project;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;


public class SampleFragmentTwo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    public static boolean sports_category=false,colors_category=false,family_category=false,shapes_category=false,
                          vehicles_category=false,places_category=false;
    static int position;

    MediaPlayer mp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sample_fragment_two, container,
                false);

        mp = MediaPlayer.create(getContext(), R.raw.buttonsound);

        ImageButton sportsButton = (ImageButton) rootView.findViewById(R.id.sports_button);
        ImageButton colorsButton = (ImageButton) rootView.findViewById(R.id.colors_button);
        ImageButton familyButton = (ImageButton) rootView.findViewById(R.id.family_button);
        ImageButton shapesButton = (ImageButton) rootView.findViewById(R.id.shapes_button);
        ImageButton vehiclesButton = (ImageButton) rootView.findViewById(R.id.vehicles_button);
        ImageButton placesButton = (ImageButton) rootView.findViewById(R.id.places_button);


        sportsButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                sports_category=true;
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

        sportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                position=7;
                Intent i=new Intent(getActivity(),ControlLevelActivity.class);
                startActivity(i);

                sports_category=true;
                vehicles_category=false;
                SampleFragment.music_category=false;
                SampleFragment.vegetables_category=false;
                SampleFragment.fruits_category=false;
                SampleFragment.clothes_category=false;
                SampleFragment.foods_category=false;
                SampleFragment.hobbies_category=false;
                family_category=false;
                shapes_category=false;
                colors_category=false;
                places_category=false;
                SampleFragmentThree.adjectives_category=false;
                SampleFragmentThree.numbers_category=false;
                SampleFragmentThree.animals_category=false;
                SampleFragmentThree.weathers_category=false;
                SampleFragmentThree.jobs_category=false;
                SampleFragmentThree.animals_category=false;


                getActivity().finish();


            }
        });

        colorsButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               colors_category=true;
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

        colorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                position=8;
                Intent i=new Intent(getActivity(),ControlLevelActivity.class);
                startActivity(i);

                colors_category=true;
                vehicles_category=false;
                SampleFragment.music_category=false;
                SampleFragment.vegetables_category=false;
                SampleFragment.fruits_category=false;
                SampleFragment.clothes_category=false;
                SampleFragment.foods_category=false;
                SampleFragment.hobbies_category=false;
                family_category=false;
                shapes_category=false;
                sports_category=false;
                places_category=false;
                SampleFragmentThree.adjectives_category=false;
                SampleFragmentThree.numbers_category=false;
                SampleFragmentThree.animals_category=false;
                SampleFragmentThree.weathers_category=false;
                SampleFragmentThree.jobs_category=false;
                SampleFragmentThree.animals_category=false;


                getActivity().finish();




            }
        });

        familyButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               family_category=true;
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

        familyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                position=9;
                Intent i=new Intent(getActivity(),ControlLevelActivity.class);
                startActivity(i);
                family_category=true;
                vehicles_category=false;
                SampleFragment.music_category=false;
                SampleFragment.vegetables_category=false;
                SampleFragment.fruits_category=false;
                SampleFragment.clothes_category=false;
                SampleFragment.foods_category=false;
                SampleFragment.hobbies_category=false;
                shapes_category=false;
                sports_category=false;
                colors_category=false;
                places_category=false;
                SampleFragmentThree.adjectives_category=false;
                SampleFragmentThree.numbers_category=false;
                SampleFragmentThree.animals_category=false;
                SampleFragmentThree.weathers_category=false;
                SampleFragmentThree.jobs_category=false;
                SampleFragmentThree.animals_category=false;


                getActivity().finish();


                Log.d("FAMILY :", String.valueOf(family_category));






            }
        });

        shapesButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                shapes_category=true;
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

        shapesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                position=10;
                Intent i=new Intent(getActivity(),ControlLevelActivity.class);
                startActivity(i);
                shapes_category=true;
                family_category=false;
                vehicles_category=false;
                SampleFragment.music_category=false;
                SampleFragment.vegetables_category=false;
                SampleFragment.fruits_category=false;
                SampleFragment.clothes_category=false;
                SampleFragment.foods_category=false;
                SampleFragment.hobbies_category=false;
                sports_category=false;
                colors_category=false;
                places_category=false;
                SampleFragmentThree.adjectives_category=false;
                SampleFragmentThree.numbers_category=false;
                SampleFragmentThree.animals_category=false;
                SampleFragmentThree.weathers_category=false;
                SampleFragmentThree.jobs_category=false;
                SampleFragmentThree.animals_category=false;

                getActivity().finish();




            }
        });

        vehiclesButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                vehicles_category=true;
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

        vehiclesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                position=11;
                Intent i=new Intent(getActivity(),ControlLevelActivity.class);
                startActivity(i);
                Log.d("VEHICLES" +
                        " :", String.valueOf(vehicles_category));

                vehicles_category=true;
                family_category=false;
                SampleFragment.music_category=false;
                SampleFragment.vegetables_category=false;
                SampleFragment.fruits_category=false;
                SampleFragment.clothes_category=false;
                SampleFragment.foods_category=false;
                SampleFragment.hobbies_category=false;
                shapes_category=false;
                sports_category=false;
                colors_category=false;
                places_category=false;
                SampleFragmentThree.adjectives_category=false;
                SampleFragmentThree.numbers_category=false;
                SampleFragmentThree.animals_category=false;
                SampleFragmentThree.weathers_category=false;
                SampleFragmentThree.jobs_category=false;
                SampleFragmentThree.animals_category=false;

                getActivity().finish();



            }
        });

        placesButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                places_category=true;
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

        placesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                position=12;
                Intent i=new Intent(getActivity(),ControlLevelActivity.class);
                startActivity(i);

                places_category=true;
                family_category=false;
                vehicles_category=false;
                SampleFragment.music_category=false;
                SampleFragment.vegetables_category=false;
                SampleFragment.fruits_category=false;
                SampleFragment.clothes_category=false;
                SampleFragment.foods_category=false;
                SampleFragment.hobbies_category=false;
                shapes_category=false;
                sports_category=false;
                colors_category=false;
                SampleFragmentThree.adjectives_category=false;
                SampleFragmentThree.numbers_category=false;
                SampleFragmentThree.animals_category=false;
                SampleFragmentThree.weathers_category=false;
                SampleFragmentThree.jobs_category=false;
                SampleFragmentThree.animals_category=false;


                getActivity().finish();


            }
        });


        return rootView;
    }


}
