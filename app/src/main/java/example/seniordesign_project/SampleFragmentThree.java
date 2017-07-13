package example.seniordesign_project;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;


public class SampleFragmentThree extends Fragment {

    MediaPlayer mp;

    static boolean animals_category=false,adjectives_category=false,numbers_category=false,countries_category=false,
            weathers_category=false,jobs_category=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sample_fragment_three, container, false);
        mp = MediaPlayer.create(getContext(), R.raw.buttonsound);



        ImageButton animalsButton = (ImageButton) rootView.findViewById(R.id.animals_button);
        ImageButton adjectivesButton = (ImageButton) rootView.findViewById(R.id.adjectives_button);
        ImageButton numbersButton = (ImageButton) rootView.findViewById(R.id.numbers_button);
        ImageButton countriesButton = (ImageButton) rootView.findViewById(R.id.countries_button);
        ImageButton weathersButton = (ImageButton) rootView.findViewById(R.id.weather_button);
        ImageButton jobsButton = (ImageButton) rootView.findViewById(R.id.jobs_button);

        animalsButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                animals_category=true;
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

        animalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                animals_category=true;
                Intent i=new Intent(getActivity(),ControlLevelActivity.class);
                startActivity(i);

                SampleFragment.vegetables_category=false;
                SampleFragment.music_category=false;
                SampleFragment. clothes_category=false;
                SampleFragment.foods_category=false;
                SampleFragment.fruits_category=false;
                SampleFragment. hobbies_category=false;
                SampleFragmentTwo.shapes_category=false;
                SampleFragmentTwo.sports_category=false;
                SampleFragmentTwo.colors_category=false;
                SampleFragmentTwo.places_category=false;
                SampleFragmentTwo.family_category=false;
                SampleFragmentTwo.vehicles_category=false;
                adjectives_category=false;
                numbers_category=false;
                countries_category=false;
                weathers_category=false;
                jobs_category=false;

                getActivity().finish();


            }
        });


        adjectivesButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                adjectives_category=true;
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

        adjectivesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                adjectives_category=true;
                Intent i=new Intent(getActivity(),ControlLevelActivity.class);
                startActivity(i);

                SampleFragment.vegetables_category=false;
                SampleFragment.music_category=false;
                SampleFragment. clothes_category=false;
                SampleFragment.foods_category=false;
                SampleFragment.fruits_category=false;
                SampleFragment. hobbies_category=false;
                SampleFragmentTwo.shapes_category=false;
                SampleFragmentTwo.sports_category=false;
                SampleFragmentTwo.colors_category=false;
                SampleFragmentTwo.places_category=false;
                SampleFragmentTwo.family_category=false;
                SampleFragmentTwo.vehicles_category=false;
                animals_category=false;
                numbers_category=false;
                countries_category=false;
                weathers_category=false;
                jobs_category=false;
                getActivity().finish();

            }
        });

        numbersButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                numbers_category=true;
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

        numbersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                numbers_category=true;
                Intent i=new Intent(getActivity(),ControlLevelActivity.class);
                startActivity(i);

                SampleFragment.vegetables_category=false;
                SampleFragment.music_category=false;
                SampleFragment. clothes_category=false;
                SampleFragment.foods_category=false;
                SampleFragment.fruits_category=false;
                SampleFragment. hobbies_category=false;
                SampleFragmentTwo.shapes_category=false;
                SampleFragmentTwo.sports_category=false;
                SampleFragmentTwo.colors_category=false;
                SampleFragmentTwo.places_category=false;
                SampleFragmentTwo.family_category=false;
                SampleFragmentTwo.vehicles_category=false;
                adjectives_category=false;
                animals_category=false;
                countries_category=false;
                weathers_category=false;
                jobs_category=false;

                getActivity().finish();


            }
        });

        countriesButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                countries_category=true;
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

        countriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                countries_category=true;
                Intent i=new Intent(getActivity(),ControlLevelActivity.class);
                startActivity(i);

                SampleFragment.vegetables_category=false;
                SampleFragment.music_category=false;
                SampleFragment. clothes_category=false;
                SampleFragment.foods_category=false;
                SampleFragment.fruits_category=false;
                SampleFragment. hobbies_category=false;
                SampleFragmentTwo.shapes_category=false;
                SampleFragmentTwo.sports_category=false;
                SampleFragmentTwo.colors_category=false;
                SampleFragmentTwo.places_category=false;
                SampleFragmentTwo.family_category=false;
                SampleFragmentTwo.vehicles_category=false;
                adjectives_category=false;
                numbers_category=false;
               animals_category=false;
                weathers_category=false;
                jobs_category=false;

                getActivity().finish();


            }
        });

        weathersButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                weathers_category=true;
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

        weathersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                weathers_category=true;
                Intent i=new Intent(getActivity(),ControlLevelActivity.class);
                startActivity(i);

                SampleFragment.vegetables_category=false;
                SampleFragment.music_category=false;
                SampleFragment. clothes_category=false;
                SampleFragment.foods_category=false;
                SampleFragment.fruits_category=false;
                SampleFragment. hobbies_category=false;
                SampleFragmentTwo.shapes_category=false;
                SampleFragmentTwo.sports_category=false;
                SampleFragmentTwo.colors_category=false;
                SampleFragmentTwo.places_category=false;
                SampleFragmentTwo.family_category=false;
                SampleFragmentTwo.vehicles_category=false;
                adjectives_category=false;
                numbers_category=false;
                countries_category=false;
               animals_category=false;
                jobs_category=false;

                getActivity().finish();

            }
        });

        jobsButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                jobs_category=true;
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

        jobsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                jobs_category=true;
                Intent i=new Intent(getActivity(),ControlLevelActivity.class);
                startActivity(i);

                SampleFragment.vegetables_category=false;
                SampleFragment.music_category=false;
                SampleFragment. clothes_category=false;
                SampleFragment.foods_category=false;
                SampleFragment.fruits_category=false;
                SampleFragment. hobbies_category=false;
                SampleFragmentTwo.shapes_category=false;
                SampleFragmentTwo.sports_category=false;
                SampleFragmentTwo.colors_category=false;
                SampleFragmentTwo.places_category=false;
                SampleFragmentTwo.family_category=false;
                SampleFragmentTwo.vehicles_category=false;
                adjectives_category=false;
                numbers_category=false;
                countries_category=false;
                weathers_category=false;
                animals_category=false;

                getActivity().finish();


            }
        });

        return rootView;

    }


}
