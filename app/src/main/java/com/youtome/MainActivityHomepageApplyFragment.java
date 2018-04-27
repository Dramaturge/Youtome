package com.youtome;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.youtome.view.RecyclerViewCommonTool.CommonAdapter;
import com.youtome.view.RecyclerViewCommonTool.ViewHolder;
import com.youtome.view.superadapter.University4;

import java.util.ArrayList;

/**
 * Created by Chliao on 2018/3/19.
 * 主页→报考
 */


public class MainActivityHomepageApplyFragment extends Fragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private CollapsingToolbarLayoutState state;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout app_bar;
    private CommonAdapter adapter;

    private ArrayList<University4> UniversityArrayList;
    private com.rengwuxian.materialedittext.MaterialEditText met_score;
    private com.rengwuxian.materialedittext.MaterialEditText met_region;
    private com.rengwuxian.materialedittext.MaterialEditText met_major;
    private android.widget.RadioButton rb_arts;
    private android.widget.RadioButton rb_sciences;
    private android.widget.RadioGroup rg_subject;
    private com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton btn_query;

    static public String Fenshu;
    static public String Diqu;
    static public String Zhuanye;
    static public String Wenke;

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.act_main_frg_homepage_apply, container, false);
        this.btn_query = (QMUIRoundButton) rootView.findViewById(R.id.btn_query);//查询按钮
        this.rg_subject = (RadioGroup) rootView.findViewById(R.id.rg_subject);
        this.rb_sciences = (RadioButton) rootView.findViewById(R.id.rb_sciences);//理科;
        this.rb_arts = (RadioButton) rootView.findViewById(R.id.rb_arts);//文科
        this.met_major = (MaterialEditText) rootView.findViewById(R.id.met_major);//专业
        this.met_region = (MaterialEditText) rootView.findViewById(R.id.met_region);//地区
        this.met_score = (MaterialEditText) rootView.findViewById(R.id.met_score);//分数
        //MaterialEditText用法和普通的EditText完全相同

        app_bar=(AppBarLayout)rootView.findViewById(R.id.app_bar);
        collapsingToolbarLayout=(CollapsingToolbarLayout)rootView.findViewById(R.id.tool_bar_layout);

        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        collapsingToolbarLayout.setTitle("");
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        collapsingToolbarLayout.setTitle("报考推荐");//设置title不显示
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if(state == CollapsingToolbarLayoutState.COLLAPSED){
                        }
                        collapsingToolbarLayout.setTitle("");
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });




        UniversityArrayList=new ArrayList<University4>();
        UniversityArrayList.add(new University4("西安电子科技大学 211 研","陕西","本科一批",R.drawable.xidian));
        UniversityArrayList.add(new University4("西北工业大学 985 211 研","陕西","本科一批",R.drawable.xigong));
        UniversityArrayList.add(new University4("西安交通大学 985  211 研","陕西","本科一批",R.drawable.xijiao));
        UniversityArrayList.add(new University4("西北大学 211","陕西","本科一批",R.drawable.xibei));

        mRecyclerView= (RecyclerView) rootView.findViewById(R.id.apply_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration mDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider_bg));
        mRecyclerView.addItemDecoration(mDecoration);

        adapter=new CommonAdapter<University4>(getContext(),
                R.layout.item_university, UniversityArrayList){
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, int position) {
                super.onBindViewHolder(viewHolder, position);
            }
            @Override
            public void convert(final ViewHolder holder, final University4 data) {
                TextView university = holder.getView(R.id.university);
                TextView location = holder.getView(R.id.location);
                TextView batch = holder.getView(R.id.batch);
                ImageView logo=holder.getView(R.id.university_logo);

                CardView cardView=holder.getView(R.id.cardlist_item);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://baike.baidu.com/item/%E8%A5%BF%E5%AE%89%E7%94%B5%E5%AD%90%E7%A7%91%E6%8A%80%E5%A4%A7%E5%AD%A6/160910?fr=aladdin");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });

                university.setText(data.university);
                location.setText(data.location);
                batch.setText(data.batch);
                Drawable drawable=getResources().getDrawable(data.header);
                if(drawable!=null){
                    logo.setImageDrawable(drawable);
                }

            }
        };


        mRecyclerView.setAdapter(adapter);

        return rootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_query=(com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton) getActivity().findViewById(R.id.btn_query);
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb_arts=(RadioButton) getActivity().findViewById(R.id.rb_arts);
                rb_sciences=(RadioButton) getActivity().findViewById(R.id.rb_sciences);
                met_score=(MaterialEditText) getActivity().findViewById(R.id.met_score);
                met_region=(MaterialEditText)  getActivity().findViewById(R.id.met_region);
                met_major=(MaterialEditText) getActivity().findViewById(R.id.met_major);
                if(rb_arts.isChecked()==true){
                    Wenke="文科";
                }else {Wenke="理科";}
                Fenshu=met_score.getText().toString();
                Diqu=met_region.getText().toString();
                Zhuanye=met_major.getText().toString();
                Intent intent=new Intent(getContext(), RecommendResultActivity.class)  ;
                startActivity(intent);
            }
        });
    }
    @Override
    public void onClick(View v) {

    }
}
