package com.youtome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Chliao on 2018/3/19.
 */

public class CirclesOfFriendsFragment extends Fragment implements View.OnClickListener {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View FindFragment = inflater.inflate(R.layout.act_main_frg_circle_of_friends ,container, false);
        return FindFragment;
    }
    @Override
    public void onClick(View v) {

    }
}