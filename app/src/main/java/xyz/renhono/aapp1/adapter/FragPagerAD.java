package xyz.renhono.aapp1.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by GT70 on 2016/10/24 0024.
 */

public class FragPagerAD extends FragmentPagerAdapter {

    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList;
    private List<String> stringList;


    public FragPagerAD(FragmentManager fm, List<Fragment> fragmentList, List<String> stringList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.stringList = stringList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position % stringList.size());
    }
}
