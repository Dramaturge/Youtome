package com.youtome;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youtome.bean.Article;
import com.youtome.view.RecyclerViewCommonTool.CommonAdapter;
import com.youtome.view.RecyclerViewCommonTool.ViewHolder;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Chliao on 2018/3/19.
 * 主页 →家教
 */

public class MainActivityHomepageTutorFragment extends Fragment implements View.OnClickListener {


    private RecyclerView mRecyclerView;
    private CollapsingToolbarLayoutState state;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout app_bar;
    private CommonAdapter adapter;

    private android.widget.LinearLayout yuwen;
    private android.widget.LinearLayout shuxue;
    private android.widget.LinearLayout yingyu;
    private android.widget.LinearLayout wuli;
    private android.widget.LinearLayout huaxue;
    private android.widget.LinearLayout shengwu;
    private android.widget.LinearLayout lishi;
    private android.widget.LinearLayout zhengzhi;
    private android.widget.LinearLayout dili;


    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }


    private ArrayList<Article> ArticleArrayList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.act_main_frg_homepage_tutor, container, false);

        this.dili = (LinearLayout) rootView.findViewById(R.id.dili);
        this.zhengzhi = (LinearLayout) rootView.findViewById(R.id.zhengzhi);
        this.lishi = (LinearLayout) rootView.findViewById(R.id.lishi);
        this.shengwu = (LinearLayout) rootView.findViewById(R.id.shengwu);
        this.huaxue = (LinearLayout) rootView.findViewById(R.id.huaxue);
        this.wuli = (LinearLayout) rootView.findViewById(R.id.wuli);
        this.yingyu = (LinearLayout) rootView.findViewById(R.id.yingyu);
        this.shuxue = (LinearLayout) rootView.findViewById(R.id.shuxue);
        this.yuwen = (LinearLayout) rootView.findViewById(R.id.yuwen);
        yuwen.setOnClickListener(this);
        shuxue.setOnClickListener(this);
        yingyu.setOnClickListener(this);
        wuli.setOnClickListener(this);
        huaxue.setOnClickListener(this);
        shengwu.setOnClickListener(this);
        lishi.setOnClickListener(this);
        zhengzhi.setOnClickListener(this);
        dili.setOnClickListener(this);

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
                    if (state !=CollapsingToolbarLayoutState.COLLAPSED) {
                        collapsingToolbarLayout.setTitle("课程推荐");//设置title不显示
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




        ArticleArrayList=new ArrayList<Article>();
        ArticleArrayList.add(new Article(
                R.drawable.header2,
                "刘一林(老师)",
                "4-13",
                "考上好大学，你需要3个“本”",
                "    第一本:笔记本，要巧记重点\n" +
                        "    在课上，做笔记是必要的。往年，有些学生认为，课堂上只要认真听讲，就没必要记笔记了。其实，这是个误区。俗话说，“好记性不如烂笔头”，课堂上记笔记有助于增强考生对知识框架的总体把握。\n" +
                        "    高三这一年要记的知识点很多。一般来说，老师在课堂上会讲解很多知识内容，有些在课堂上消化不了的，需要先记录下来，课下再慢慢消化、理解。有时老师在课堂上的一句话，可能就是关键问题。学生在课上记住了，但很可能到了课下就忘记了老师说过的重点。因此，记课堂笔记非常重要。\n" +
                        "    做笔记要讲究方法。老师在课上讲的内容很多，如果只顾做笔记，也会影响听课效率。所以，上课做笔记的前提是不能影响听讲和思考，这就要求考生把握好时机。一般情况下，老师在黑板上写东西时，高三生可以抓紧时间记笔记；老师讲到重点内容时，高三生要认真听讲，课后再补记笔记。提醒考生注意的是，记笔记不是一字不落地全记上，而要简明扼要，利用短语、数字、图案等适合自己的方式把重点、难点、疑点等内容记下，课后再认真整理。\n" +
                        "    第二本:错题本，要善于归纳\n" +
                        "    月考、期中考、期末考、“一模”、“二模”……高三一年，学生要经历大大小小的考试。总结好每次考试中遇到的问题，对高三生提高成绩非常重要。建立错题本，进行归纳分析，是一种非常好的做法。\n" +
                        "    在学习中，高三生要善于把自己做过的作业、习题、试卷中的错题抄在错题本上，便于日后随时翻阅，查找知识漏洞。只有有效地使用错题本，才能使学习更有针对性，同时提高学习效率。以往，有的高三生对错题的认识不深刻，认为做错题集很浪费时间，很大的原因是：做了错题集而不去回顾复习。所以这些同学没有建立错题本，不善于对错题进行归纳总结，即使重新再做一遍，也还是答不出或者答错。\n" +
                        "    那么问题来了，这么多科目，每一科都要建立错题集吗？\n" +
                        "    在文科里，语文和英语都是语言类的科目。题目的主观性、随机性和偶然性都很强，就算有解题方法，基本上也是靠平时多加积累才能总结得到的。历史和政治，客观题需要练好自己的理解力和观察力，主观题则基本靠背诵记忆加上结合材料。因此，这四科不太建议做错题本。地理算是文科之中最具理科性质的科目，其能力要求集文理之大成。不仅记忆量比较大，而且很多知识点都是动态的，所以地理这科还是很有必要建立错题本的。\n" +
                        "    再说理科，高考改革之后，化学和生物的教学无论是在质和量上都下降了，很多细碎的命题热门知识点只能靠记忆，大部分题目都注重于考查基础知识。因此，这两科做错题集也不太有必要。\n" +
                        "    但相比之下，数学和物理则完全是理科思维的集大成者。光记得公式定理是不行的，还得在题目中找出理论依据，需要非常灵活的思维方法。更何况，数学题型千变万化，解题方法及其组合也是千差万别。所以，对数学错题的研究和反思是十分有价值的。\n" +
                        "    综上所述，数学是最需要做错题集的科目，物理、地理次之。\n" +
                        "    建立了错题本，还要用好错题本。高三生可将自己发现的错题或不会做的题收集起来，分析一下做错或不会做的原因，并把正确解题答案和思路注在旁边。高考前，高三生可着重针对错题本上的题目查缺补漏，也可把在学习中的体会记录下来，经常翻看，能够使自己对知识的理解更深刻，对知识掌握得更牢固。\n" +
                        "    第三本:口袋词汇本，课余时间常翻看\n" +
                        "    在备战高考的过程中，英语作为一大学科，对高考总成绩有举足轻重的作用。若想提高英语成绩，词汇量是必不可缺的。\n" +
                        "    首先，将单词分为原型和派生词。例如happiness为高兴、幸福的名词，建议将它的形容词、副词或者动词形式一并记住，可完善对这个单词整体运用的理解，对写作也会有所帮助。其次，高三生还可以将字母拼写相似的词进行总结，就像语文的形近字一样，例如similar和familiar，认准单词便于考生在阅读中理解文章大意。最后，对常见的词组做整理，尤其是出现在单选和完形当中的、那些仅仅依靠字面翻译不出来的“肉眼无法识别”的词组。\n" +
                        "    如果条件允许，高三生可用一个便于携带的小本子记录词汇笔记，在等车、上课的间隙等课余时间勤于翻看。此外，还可将背单词当作娱乐休闲，几个同学比赛谁找出的好词组好句子多，在讨论中对高频词汇的印象会更加深刻。听力近几年增加了听写单词的题型，对于常考的月份、数字、天气、星期等词汇，高考生可加以归类，在考试前抽出几分钟进行复习。\n",
                "15k"
        ));

        ArticleArrayList.add(new Article(
                R.drawable.header3,
                "白静(老师)",
                "4-11",
                "我们为什么要上一所好大学？",
                "        这个显然是一个假命题，或者说一个没有任何疑问的命题：我们高考那么苦，为的就是上一个好大学，好的大学意味着名气、意味着将来有一份好工作，意味着人的交际圈子会有更高的品质，意味着。。。。\n" +
                        "       普通的中国人，有两个出身，一个出身是你真正的家庭，另一个出身是你的学校。虽然许多人在批判这种出身论，认为——英雄不问出路。我们也见过许多出身学校一般的人，可以做出非常好的成绩的。但是这些不能成为我们的理由和自我慰藉的理由：遇到任何问题的时候，第一个想法是给自己找理由，找借口，然后继续着以前的生活和经历。\n" +
                        "        但是当我们把眼光放到一些优秀的学校的时候，就会发现这样一个现象：越是好的学校，有着良好的氛围，有着优秀的师资，有着优秀的同学，从而形成的这种氛围，成为影响一个人成长成功的长久的有效的环境。当你知道一个北大或清华最优秀的同学，依然每天学习工作十几个小时，当你看到哈佛大学图书馆凌晨4点的时候亮着灯的时候，当你看到哪怕一个能进入投行的所谓其它人可望不可及的“牛人”的时候，只看到他们“年薪百万”的光鲜外表时，却不知他们每周至少工作100个小时。对比现在绝大多数大学生的生活，我们应该感到那么一点点的汗颜。\n" +
                        "       所以优秀的大学给我们到底带来什么不一样的东西，如果你只能选一样，那就是：环境以及在这个基础上影响人的思维习惯，或者独立思考的意识。这些年去全国各大高校讲了无数场了，过来人也帮助那么多的年轻人找到自己的未来，我一直在思考什么是影响和决定我们未来更重要的东西。或许今天在大连理工同学们的身上，让我看到了这一点，那就是求知、思考和行动。      \n" +
                        "      当同一个机会摆在不同的人面前的时候，有人一脸茫然，觉得那些机会好象从来跟自己没有关系，哪怕你告诉他有人成功了，他却认为自己没有能力。而另外一些人，非常热烈地过来和你讨论，并且愿意马上为之实施去奋斗去努力。当你身边都是优秀的人时，不知不觉你也会被成优秀的人。而当你激情澎湃想做一番努力的时候，周边的人却一脸茫然，过着自己“快乐的大学生活“时，不知不觉，你也会被同化——人都是凡人，有着超强毅力的都是少数人。\n",
                "10k"
        ));

//        ArticleArrayList.add(new Article(
//                R.drawable.header4,
//                "张丽娟(老师)",
//                "4-9",
//                "。。",
//                "。。",
//                "15k"
//        ));
//        ArticleArrayList.add(new Article(
//                R.drawable.header5,
//                "吴佳明(老师)",
//                "4-1",
//                "..",
//                "？？？？",
//                "15k"
//        ));

        mRecyclerView= (RecyclerView) rootView.findViewById(R.id.tutor_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration mDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider_bg));
        mRecyclerView.addItemDecoration(mDecoration);


        adapter=new CommonAdapter<Article>(getContext(), R.layout.item_article, ArticleArrayList){
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, int position) {
                super.onBindViewHolder(viewHolder, position);
            }

            @Override
            public void convert(ViewHolder holder, final Article data) {
                LinearLayout skip_to_others_homepage=holder.getView(R.id.skip_to_others_homepage);
                CircleImageView profile_image = holder.getView(R.id.profile_image);//头像
                TextView tv_Article_publisher =holder.getView(R.id.tv_status_publisher);//发布人
                TextView tv_Article_publish_time =holder.getView(R.id.tv_status_publish_time);//发布时间
                LinearLayout skip_to_detail=holder.getView(R.id.skip_to_detail);
                TextView tv_Article_title =holder.getView(R.id.tv_status_title);//标题
                TextView tv_Article_brief =holder.getView(R.id.tv_status_brief);//简介

                profile_image.setImageDrawable(getResources().getDrawable(data.getHeader()));
                tv_Article_publisher.setText(data.getPublisher());
                tv_Article_publish_time.setText(data.getPublish_time());
                tv_Article_title.setText(data.getTitle());
                tv_Article_brief.setText(data.getContent());


                skip_to_others_homepage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getContext(),OtherHomepageActivity.class);//跳转到发布人的个人主页
                        startActivity(intent);
                    }
                });


                skip_to_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getContext(),CommentsActivity.class);//跳转到这条状态的详细内容
                        intent.putExtra("content",data.getContent());
                        intent.putExtra("title",data.getTitle());
                        intent.putExtra("name",data.getPublisher());
                        intent.putExtra("time",data.getPublish_time());
                        startActivity(intent);
                    }
                });


            }


        };

        mRecyclerView.setAdapter(adapter);

        return rootView;

    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(),SearchActivity.class);
        intent.putExtra("auto",true);
        switch (v.getId()) {
            case R.id.yuwen:
                intent.putExtra("subject", "语文");
                startActivity(intent);
                break;
            case R.id.shuxue:
                intent.putExtra("subject", "数学");
                startActivity(intent);
                break;
            case R.id.yingyu:
                intent.putExtra("subject", "英语");
                startActivity(intent);
                break;
            case R.id.wuli:
                intent.putExtra("subject", "物理");
                startActivity(intent);
                break;
            case R.id.huaxue:
                intent.putExtra("subject", "化学");
                startActivity(intent);
                break;
            case R.id.shengwu:
                intent.putExtra("subject", "生物");
                startActivity(intent);
                break;
            case R.id.lishi:
                intent.putExtra("subject", "历史");
                startActivity(intent);
                break;
            case R.id.zhengzhi:
                intent.putExtra("subject", "政治");
                startActivity(intent);
                break;
            case R.id.dili:
                intent.putExtra("subject", "地理");
                startActivity(intent);
                break;
        }

    }
}