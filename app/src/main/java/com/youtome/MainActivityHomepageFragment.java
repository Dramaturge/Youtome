package com.youtome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.youtome.view.ClearEditText;

import java.util.ArrayList;
import java.util.List;

/***
 *朋友圈Fragment
 */
public class MainActivityHomepageFragment extends Fragment implements View.OnClickListener {



    ViewPager mContentViewPager;
    QMUITabSegment mTabSegment;
    private ClearEditText clearEditText;






    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.act_main_frg_homepage, null);
        mContentViewPager=(ViewPager)rootView.findViewById(R.id.contentViewPager);
        mTabSegment=(QMUITabSegment)rootView.findViewById(R.id.tabSegment);
        mTabSegment.setHasIndicator(true);

        clearEditText=(ClearEditText)rootView.findViewById(R.id.clearEditText);
        clearEditText.setCursorVisible(false);
        clearEditText.setFocusable(false);
        clearEditText.setFocusableInTouchMode(false);
        clearEditText.setOnClickListener(this);

        List<Fragment> fragments= new ArrayList<>();
        MainActivityHomepageConsultationFragment mainActivityHomepageConsultationFragment = new MainActivityHomepageConsultationFragment();
        MainActivityHomepageApplyFragment mainActivityHomepageApplyFragment =new MainActivityHomepageApplyFragment();
        MainActivityHomepageTutorFragment mainActivityHomepageTutorFragment =new MainActivityHomepageTutorFragment();
        fragments.add(mainActivityHomepageConsultationFragment);
        fragments.add(mainActivityHomepageApplyFragment);
        fragments.add(mainActivityHomepageTutorFragment);
        BaseFragmentPagerAdapter adapter = new BaseFragmentPagerAdapter(getFragmentManager(), fragments);
        mContentViewPager.setAdapter(adapter);




        QMUITabSegment.Tab tab1=new QMUITabSegment.Tab("咨询");
        QMUITabSegment.Tab tab2=new QMUITabSegment.Tab("报考");
        QMUITabSegment.Tab tab3=new QMUITabSegment.Tab("家教");


        tab1.setTextColor(getResources().getColor(R.color.qmui_config_color_gray_1),
                getResources().getColor(R.color.black));
        tab2.setTextColor(getResources().getColor(R.color.qmui_config_color_gray_1),
                getResources().getColor(R.color.black));
        tab3.setTextColor(getResources().getColor(R.color.qmui_config_color_gray_1),
                getResources().getColor(R.color.black));

        mTabSegment.addTab(tab1);
        mTabSegment.addTab(tab2);
        mTabSegment.addTab(tab3);
        mTabSegment.setupWithViewPager(mContentViewPager, false);
        mTabSegment.setMode(QMUITabSegment.MODE_FIXED);
        mTabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                mTabSegment.hideSignCountView(index);
            }

            @Override
            public void onTabUnselected(int index) {

            }

            @Override
            public void onTabReselected(int index) {
                mTabSegment.hideSignCountView(index);
            }

            @Override
            public void onDoubleTap(int index) {

            }
        });

        return rootView;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.clearEditText:
                Intent intent=new Intent(getContext(),SearchActivity.class);
                startActivity(intent);
                break;
        }
    }


    private class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mDataList;

        public BaseFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public BaseFragmentPagerAdapter(FragmentManager fm, List<Fragment> dataList) {
            super(fm);
            mDataList = dataList;
        }

        @Override
        public Fragment getItem(int position) {
            return mDataList.get(position);
        }

        @Override
        public int getCount() {
            return mDataList.size();
        }
    }


}
