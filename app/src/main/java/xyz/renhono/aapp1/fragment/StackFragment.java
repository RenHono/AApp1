package xyz.renhono.aapp1.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.renhono.aapp1.R;
import xyz.renhono.aapp1.adapter.FragPagerAD;


/**
 * A simple {@link Fragment} subclass.
 */
public class StackFragment extends Fragment {

    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> stringList;
    private TabLayout tabLayout;

    private String url1 = "http://android.reader.qq.com/v6_2/queryOperation?categoryFlag=3";
    private String url2 = "http://android.reader.qq.com/v6_2/queryOperation?categoryFlag=1";

    private String[] titles = {"出版图书", "原创文学"};

    public StackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stack, container, false);

//        stringList.clear();
//        stringList.add("出版图书");
//        stringList.add("原创文学");
        stringList = new ArrayList<>();


        for (int i = 0; i < titles.length; i++) {


            stringList.add(titles[i]);

        }


        Stack_childFragment stackChildFragment1 = new Stack_childFragment();
        Stack_childFragment stackChildFragment2 = new Stack_childFragment();


        Bundle bundle1 = new Bundle();
        bundle1.putString("url", url1);
        bundle1.putInt("page", 1);

        Bundle bundle2 = new Bundle();
        bundle2.putString("url", url2);
        bundle2.putInt("page", 2);

        stackChildFragment1.setArguments(bundle1);
        stackChildFragment2.setArguments(bundle2);

        fragmentList.clear();

        fragmentList.add(stackChildFragment1);
        fragmentList.add(stackChildFragment2);

        FragPagerAD fragPagerAD = new FragPagerAD(getChildFragmentManager(), fragmentList, stringList);


        viewPager = (ViewPager) view.findViewById(R.id.vpstack);
        viewPager.setAdapter(fragPagerAD);

        tabLayout = (TabLayout) view.findViewById(R.id.tlstack);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);


        return view;


    }

}
