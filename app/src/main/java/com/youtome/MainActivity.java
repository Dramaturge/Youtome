package com.youtome;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;
import com.youtome.app.AppApplication;

public class MainActivity extends AppCompatActivity {

    private TextView main_title;
    private Fragment[] fragments;
    private CirclesOfFriendsFragment circlesOfFriendsFragment;
    private HomepageFragment homepageFragment;
    private UserFragment userFragment;
    private int lastShowFragment = 0;
    private com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        PrefTools.setBoolean(AppApplication.getContext(), "is_login", true);

        StatusBarUtil.setTranslucentForCoordinatorLayout(MainActivity.this,50);

//        StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.gray_theme), 100);

        bottomNavigationView = (com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.selectTab(0);
        int[] image = {R.drawable.homepage, R.drawable.friend_circle,
                R.drawable.me};
        int[] color = {ContextCompat.getColor(this, R.color.green), ContextCompat.getColor(this, R.color.qmui_config_color_red),
                ContextCompat.getColor(this, R.color.blue)};

        if (bottomNavigationView != null) {
            bottomNavigationView.isWithText(false);
            // bottomNavigationView.activateTabletMode();
            bottomNavigationView.isWithText(true);
            bottomNavigationView.isColoredBackground(false);
            bottomNavigationView.setTextActiveSize(getResources().getDimension(R.dimen.text_active));
            bottomNavigationView.setTextInactiveSize(getResources().getDimension(R.dimen.text_inactive));
            bottomNavigationView.setItemActiveColorWithoutColoredBackground(ContextCompat.getColor(this, R.color.black));
            bottomNavigationView.setFont(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Noh_normal.ttf"));
            bottomNavigationView.disableShadow();
        }
        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("主页", color[0], image[0]);
        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("朋友圈", color[1], image[1]);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("我", color[2], image[2]);

        bottomNavigationView.addTab(bottomNavigationItem);
        bottomNavigationView.addTab(bottomNavigationItem1);
        bottomNavigationView.addTab(bottomNavigationItem2);

        bottomNavigationView.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                switch (index) {
                    case 0:
                        if (lastShowFragment != 0) {
                            switchFrament(lastShowFragment, 0);
                            lastShowFragment = 0;
                        }
                        break;
                    case 1:
                        if (lastShowFragment != 1) {
                            switchFrament(lastShowFragment, 1);
                            lastShowFragment = 1;
                        }
                        break;
                    case 2:
                        if (lastShowFragment != 2) {
                            switchFrament(lastShowFragment, 2);
                            lastShowFragment = 2;

                        }
                        break;
                }
            }
        });


        initFragments();
    }




    /**
     * 切换Fragment
     *
     * @param lastIndex 上个显示Fragment的索引
     * @param index     需要显示的Fragment的索引
     */
    public void switchFrament(int lastIndex, int index) {
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.container, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();

        switch (index){
            case 0:break;
            case 1:break;
            case 2:break;
        }
    }




    private void initFragments() {

        homepageFragment =new HomepageFragment();
        circlesOfFriendsFragment = new CirclesOfFriendsFragment();
        userFragment=new UserFragment();
        fragments = new Fragment[]{homepageFragment, circlesOfFriendsFragment, userFragment};
        lastShowFragment = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, homepageFragment)
                .show(homepageFragment)
                .commit();
    }


    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    Toast.makeText(this,"再按一次退出",
                            Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
            return true;
        }
        //拦截MENU按钮事件，让他无任何操作
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
