package com.apppro.pikturesque.Utils;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/*This class is a pager adapter. Code for a section pager adapter will pretty much always look like this so
* whenever a fragment is necessary, this code can pretty much just be copied and pasted into a Sections
* Pager Adapter class in that app. This class can be called whenever a sections pager adapter is needed
* (like when an activity has a few fragments in it) */

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "SectionsPagerAdapter";
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addingFragment (Fragment fragment) {
        mFragmentList.add(fragment);
    }
}
