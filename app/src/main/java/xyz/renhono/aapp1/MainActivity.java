package xyz.renhono.aapp1;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import xyz.renhono.aapp1.fragment.BookstandFragment;
import xyz.renhono.aapp1.fragment.CategoryFragment;
import xyz.renhono.aapp1.fragment.CityFragment;
import xyz.renhono.aapp1.fragment.StackFragment;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private BookstandFragment bookstandFragment;
    private CategoryFragment categoryFragment;
    private CityFragment cityFragment;
    private StackFragment stackFragment;
    private FragmentTransaction transaction2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        radioGroup = (RadioGroup) findViewById(R.id.vmain);

        fragmentManager = getSupportFragmentManager();

        transaction2 = fragmentManager.beginTransaction();

        bookstandFragment = new BookstandFragment();
        categoryFragment = new CategoryFragment();
        cityFragment = new CityFragment();
        stackFragment = new StackFragment();


        transaction2.replace(R.id.llfrag, bookstandFragment).commit();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


               transaction = fragmentManager.beginTransaction();


                switch (checkedId) {

                    case R.id.rbbookstand:


                        transaction.replace(R.id.llfrag, bookstandFragment);
                        transaction.commit();


                        break;

                    case R.id.rbcategory:

                        transaction.replace(R.id.llfrag, categoryFragment);
                        transaction.commit();

                        break;
                    case R.id.rbcity_icon:

                        transaction.replace(R.id.llfrag, cityFragment);

                        transaction.commit();

                        break;
                    case R.id.rbstack:

                        transaction.replace(R.id.llfrag, stackFragment);

                        transaction.commit();
                        break;


                }




            }
        });

    }
}
