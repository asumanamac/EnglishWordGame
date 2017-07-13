package example.seniordesign_project;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SwitchesActivities extends AppCompatActivity {
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switches_activities);

        mViewPager = (ViewPager) findViewById(R.id.pager);


        mViewPager.setAdapter(new SamplePagerAdapter(getSupportFragmentManager()));

    }

    public class SamplePagerAdapter extends FragmentPagerAdapter {

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            /** Show a Fragment based on the position of the current screen */

            if (position == 0) {
                return new SampleFragment();
            }
            else if(position ==1) {
                return new SampleFragmentTwo();
            }

            else {
                return new SampleFragmentThree();
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 3;
        }


    }

}
