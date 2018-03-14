package com.youtome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/***
 *我Fragment
 */
public class UserFragment extends Fragment implements View.OnClickListener {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View FindFragment = inflater.inflate(R.layout.act_main_frg_user ,container, false);
        return FindFragment;
    }
    @Override
    public void onClick(View v) {

    }
}
