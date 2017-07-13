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


public class SampleFragment extends Fragment {

    MediaPlayer mp;

    static boolean music_category=false,vegetables_category=false,foods_category=false,clothes_category=false,
            hobbies_category=false,fruits_category=false;
    static int position;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sample, container,false);
        mp = MediaPlayer.create(getContext(), R.raw.buttonsound);


        ImageButton musicButton = (ImageButton) rootView.findViewById(R.id.musicButton);
        ImageButton vegetablesButton =(ImageButton) rootView.findViewById(R.id.vegetablesButton);
        ImageButton foodsButton =(ImageButton) rootView.findViewById(R.id.foodsButton);
        ImageButton clothesButton = (ImageButton) rootView.findViewById(R.id.clothesButton);
        ImageButton hobbiesButton = (ImageButton) rootView.findViewById(R.id.hobbiesButton);
        ImageButton fruitsButton = (ImageButton) rootView.findViewById(R.id.fruitssButton);



        musicButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                music_category=true;
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

        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                music_category=true;
                position=1;

                Intent i=new Intent(getActivity(),ControlLevelActivity.class);
                startActivity(i);

                vegetables_category=false;
                fruits_category=false;
                clothes_category=false;
                foods_category=false;
                hobbies_category=false;
                SampleFragmentTwo.shapes_category=false;
                SampleFragmentTwo.sports_category=false;
                SampleFragmentTwo.colors_category=false;
                SampleFragmentTwo.places_category=false;
                SampleFragmentTwo.family_category=false;
                SampleFragmentTwo.vehicles_category=false;
                SampleFragmentThree.adjectives_category=false;
                SampleFragmentThree.numbers_category=false;
                SampleFragmentThree.animals_category=false;
                SampleFragmentThree.weathers_category=false;
                SampleFragmentThree.jobs_category=false;
                SampleFragmentThree.animals_category=false;

                getActivity().finish();






            }
        });

        vegetablesButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                vegetables_category=true;
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

        vegetablesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                vegetables_category=true;
                position=2;
                Intent i=new Intent(getActivity(),ControlLevelActivity.class);
                startActivity(i);

                music_category=false;
                fruits_category=false;
                clothes_category=false;
                foods_category=false;
                hobbies_category=false;
                SampleFragmentTwo.shapes_category=false;
                SampleFragmentTwo.sports_category=false;
                SampleFragmentTwo.colors_category=false;
                SampleFragmentTwo.places_category=false;
                SampleFragmentTwo.family_category=false;
                SampleFragmentTwo.vehicles_category=false;
                SampleFragmentThree.adjectives_category=false;
                SampleFragmentThree.numbers_category=false;
                SampleFragmentThree.animals_category=false;
                SampleFragmentThree.weathers_category=false;
                SampleFragmentThree.jobs_category=false;
                SampleFragmentThree.animals_category=false;

                getActivity().finish();

            }
        });

        foodsButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                foods_category=true;
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

        foodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                foods_category=true;
                position=3;
                Intent i=new Intent(getActivity(),ControlLevelActivity.class);
                startActivity(i);

                vegetables_category=false;
                fruits_category=false;
                clothes_category=false;
                music_category=false;
                hobbies_category=false;
                SampleFragmentTwo.shapes_category=false;
                SampleFragmentTwo.sports_category=false;
                SampleFragmentTwo.colors_category=false;
                SampleFragmentTwo.places_category=false;
                SampleFragmentTwo.family_category=false;
                SampleFragmentTwo.vehicles_category=false;
                SampleFragmentThree.adjectives_category=false;
                SampleFragmentThree.numbers_category=false;
                SampleFragmentThree.animals_category=false;
                SampleFragmentThree.weathers_category=false;
                SampleFragmentThree.jobs_category=false;
                SampleFragmentThree.animals_category=false;

                getActivity().finish();






            }
        });

        clothesButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                clothes_category=true;
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

        clothesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                clothes_category=true;
                position=4;
                Intent i=new Intent(getActivity(),ControlLevelActivity.class);
                startActivity(i);

                vegetables_category=false;
                fruits_category=false;
                music_category=false;
                foods_category=false;
                hobbies_category=false;
                SampleFragmentTwo.shapes_category=false;
                SampleFragmentTwo.sports_category=false;
                SampleFragmentTwo.colors_category=false;
                SampleFragmentTwo.places_category=false;
                SampleFragmentTwo.family_category=false;
                SampleFragmentTwo.vehicles_category=false;
                SampleFragmentThree.adjectives_category=false;
                SampleFragmentThree.numbers_category=false;
                SampleFragmentThree.animals_category=false;
                SampleFragmentThree.weathers_category=false;
                SampleFragmentThree.jobs_category=false;
                SampleFragmentThree.animals_category=false;

                getActivity().finish();

            }
        });

        hobbiesButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hobbies_category=true;
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

        hobbiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                hobbies_category=true;
                position=5;
                Intent i=new Intent(getActivity(),ControlLevelActivity.class);
                startActivity(i);

                vegetables_category=false;
                fruits_category=false;
                clothes_category=false;
                foods_category=false;
                music_category=false;
                SampleFragmentTwo.shapes_category=false;
                SampleFragmentTwo.sports_category=false;
                SampleFragmentTwo.colors_category=false;
                SampleFragmentTwo.places_category=false;
                SampleFragmentTwo.family_category=false;
                SampleFragmentTwo.vehicles_category=false;
                SampleFragmentThree.adjectives_category=false;
                SampleFragmentThree.numbers_category=false;
                SampleFragmentThree.animals_category=false;
                SampleFragmentThree.weathers_category=false;
                SampleFragmentThree.jobs_category=false;
                SampleFragmentThree.animals_category=false;

                getActivity().finish();


            }
        });

        fruitsButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                fruits_category=true;
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

        fruitsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                fruits_category=true;
                position=6;
                Intent i=new Intent(getActivity(),ControlLevelActivity.class);
                startActivity(i);

                vegetables_category=false;
                music_category=false;
                clothes_category=false;
                foods_category=false;
                hobbies_category=false;
                SampleFragmentTwo.shapes_category=false;
                SampleFragmentTwo.sports_category=false;
                SampleFragmentTwo.colors_category=false;
                SampleFragmentTwo.places_category=false;
                SampleFragmentTwo.family_category=false;
                SampleFragmentTwo.vehicles_category=false;
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
