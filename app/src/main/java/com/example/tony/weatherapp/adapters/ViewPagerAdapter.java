package com.example.tony.weatherapp.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.example.tony.weatherapp.view.weatherview.WeatherCityOneFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    String cityNames[];


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb, String mCityNames[]) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
        this.cityNames = mCityNames;

    }


    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if (position == 0) // if the position is 0 we are returning the First tab
        {
            WeatherCityOneFragment weatherCityOneFragment = new WeatherCityOneFragment();
            weatherCityOneFragment.setCityName(cityNames[position]);
            return weatherCityOneFragment;
        } else             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            WeatherCityOneFragment weatherCityTwoFragment = new WeatherCityOneFragment();
            weatherCityTwoFragment.setCityName(cityNames[position]);
            return weatherCityTwoFragment;

        }


    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
