package test.wd.com.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import test.wd.com.demo.R;
import test.wd.com.demo.fragment.AllSignUpFragment;
import test.wd.com.demo.fragment.EmployFragmnet;
import test.wd.com.demo.fragment.OnJobFragment;
import test.wd.com.demo.fragment.SignUpFragment;

/**
 * Created by 92457 on 2018/3/31.
 */

public class PermissionActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private String[] mTitle = new String[]{"全部", "已报名", "已录用", "已到岗"};

        public ViewPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new AllSignUpFragment();
            } else if (position == 1) {
                return new SignUpFragment();
            } else if (position == 2) {
                return new EmployFragmnet();
            } else {
                return new OnJobFragment();
            }
        }

        @Override
        public int getCount() {
            return mTitle.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];
        }
    }

}
