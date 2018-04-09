package com.youtome;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import de.hdodenhof.circleimageview.CircleImageView;

/***
 *我Fragment
 */
public class UserFragment extends Fragment implements View.OnClickListener {

    private AppBarLayout app_bar;
    private android.widget.ImageView collapsingbar_background_image;
    private de.hdodenhof.circleimageview.CircleImageView profile_image;
    private android.widget.TextView user_name;
    private android.widget.TextView edit_personal_signal;
    private android.widget.TextView edit_personal_brief;
    private android.widget.TextView recommend_count;
    private android.widget.TextView read_count;
    private android.widget.TextView fans_count;
    private android.widget.ImageView to_history;
    private android.widget.ImageView to_personalinformation;
    private android.widget.ImageView to_ccounts;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View FindFragment = inflater.inflate(R.layout.act_main_frg_user, container, false);

        app_bar=(AppBarLayout)FindFragment.findViewById(R.id.app_bar);
        this.to_ccounts = (ImageView) FindFragment.findViewById(R.id.to_ccounts);
        this.to_personalinformation = (ImageView) FindFragment.findViewById(R.id.to_personal_information);
        this.to_history = (ImageView) FindFragment.findViewById(R.id.to_history);
        this.fans_count = (TextView) FindFragment.findViewById(R.id.fans_count);
        this.read_count = (TextView) FindFragment.findViewById(R.id.read_count);
        this.recommend_count = (TextView) FindFragment.findViewById(R.id.recommend_count);
        this.edit_personal_brief = (TextView) FindFragment.findViewById(R.id.edit_personal_brief);
        this.edit_personal_signal = (TextView) FindFragment.findViewById(R.id.edit_personal_signal);
        this.user_name = (TextView) FindFragment.findViewById(R.id.user_name);
        this.profile_image = (CircleImageView) FindFragment.findViewById(R.id.profile_image);
        this.collapsingbar_background_image = (ImageView) FindFragment.findViewById(R.id.collapsing_bar_background_image);


        return FindFragment;
    }
    @Override
    public void onClick(View v) {

    }
}
