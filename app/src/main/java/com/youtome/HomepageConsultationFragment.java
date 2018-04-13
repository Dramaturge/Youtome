package com.youtome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.youtome.app.AppApplication;
import com.youtome.bean.Article;
import com.youtome.view.RecyclerViewCommonTool.CommonAdapter;
import com.youtome.view.RecyclerViewCommonTool.ViewHolder;
import  com.youtome.view.superadapter.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Transformer;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;


/***
 *主页→咨询
 */

public class HomepageConsultationFragment extends Fragment implements View.OnClickListener{
    private TextView test1;  //xpt


    private RecyclerView mRecyclerView;
    private  CommonAdapter adapter;

    private View rootView = null;//缓存Fragment view
    private List<LayoutWrapper> data;
    private SwipeRefreshLayout lay_fresh;

    private  ArrayList<Article> ArticleArrayList;
    private String Username;
    private  String Token;
    private ConvenientBanner convenientBanner;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    public class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, Integer data) {
            imageView.setImageResource(data);
        }
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.act_main_frg_homepage_consultation, container, false);
        Username = PrefTools.getString(
                AppApplication.getContext(), "User","");
        Token = PrefTools.getString(
                AppApplication.getContext(), "Token","");

//        slideShowView=(SlideShowView)this.rootView.findViewById(R.id.homepage_slide_show);


        int[] imageSrcs = new int[]{
                R.drawable.banner1,
                R.drawable.banner2,
                R.drawable.banner3,
                R.drawable.banner4
        };

            ArrayList<Integer> localImages = new ArrayList<Integer>();
            //获取本地的图片
            localImages.add(R.drawable.banner1);
            localImages.add(R.drawable.banner2);
            localImages.add(R.drawable.banner3);
            localImages.add(R.drawable.banner4);

            convenientBanner=(ConvenientBanner)this.rootView.findViewById(R.id.convenientBanner);
            //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
            convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.shape_oval_dot_orage,R.drawable.shape_oval_dot_dark})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);



        ArticleArrayList=new ArrayList<Article>();
        ArticleArrayList.add(new Article(
                R.drawable.header8,
                "游世隆(老师)",
                "4-10",
                "西北工业大学",
                "    开学一个月说说自己上大学的感受赛。\n" +
                        "    不要信学长们说的，现在全校大一新生男女比例有三比一了好吗。\n" +
                        "    不过，我在的院还是妥妥的八比一。\n" +
                        "    一节大课很容易分辨出稀稀落落的女生。\n" +
                        "    1.课业比较重。\n" +
                        "    由于这届改革，我们学时被压缩了，所以好多课大一上学期就开了。c语言，思修，近代史。所以导致我们课业很重。基本上每天六点起，准备跑步，签到，上课。我是学探测制导的，课比其他专业多。有时候，一天早8:30到晚8:40都是满的。\n" +
                        "    2.学风\n" +
                        "    我上大学前所有的美好梦想都被毁了。早上7:30i跑操打卡，加签到。你能看到好多人读英语，背单词。晚上自习室基本都有人，距离宿舍越近，人越多。像图书馆，我就没占到座。大家自觉学习的意识也很强。我觉得女生少是一个原因，但还是学风优厚吧。\n" +
                        "    3.地理位置\n" +
                        "    有学长提到风景，优美。那是一定的。但地方偏。不过，其实现在东大也算建的不错了。甜品店，面包房都有。网吧饭馆更不用说。就是可能治安不大好，\n" +
                        "    4.出国\n" +
                        "    可能是我不太了解，不过我觉得出国机会还挺多的，但其实工大好多专业不好出国深造。比如我们航天学院。我认识的学长又准备考雅思托福的去德国，有申请到台湾交换生的。不过工大这方面做得还是有欠缺，两个地方学分不兼容。\n" +
                        "    5.机会\n" +
                        "    怎么说呢？因为我是北京的，所以对这方面比较关注。其实基本上，我们会失去不少机会。比如讲座，交流。地理位置偏僻，人家不愿意来。但我来这目的很单纯，专心学习，考研。所以这种程度还是可以忍。\n" +
                        "    6.社团\n" +
                        "    怎么说呢，其实工大社团的确水。但也有很多想把社团做好的人。比如我知道的军协，貌似是土豪聚集地，还有摄影协会。而我所在的社团，前辈也真是热爱话剧。而且，学长学姐学分绩都很靠前啊。所以大概取决于怎么看吧。也和自己实力有关。\n" +
                        "    7.其他\n" +
                        "    我还只是新来的学妹一只，有很多不了解的地方。但是我觉得，工大其实在一步步变化。包括上面提到的选修课。你可以不去水过，但也可以选择认真听，学到东西，一切还是取决于自己。\n" +
                        "    我很喜欢校歌。\n",
                "10k"
        ));

        ArticleArrayList.add(new Article(
                R.drawable.header7,
                "张丽(老师)",
                "4-11",
                "电子科技大学",
                "    一．\n" +
                        "    1.沙河校区是电子科技大学的老校区，一般称之为本部。但需要注意的是，大部分学生已搬迁至清水河校区学习。\n" +
                        "    2.位于沙河校区的学院有：信息与软件工程学院（新生第一年在清水河校区，第二年搬回沙河），微固学院，光电学院，物电学院，生命学院等部分学院在大二大三时会搬回沙河校区。\n" +
                        "    3.关于沙河校区分数较低的说明\n" +
                        "    2011年沙河校区首次单独招生。由于考生对沙河校区的了解不够，部分导致了录取分数较低的情况。此外，沙河校区的信软学院学费达到了9800/年，这也部分导致了一些考生不敢报考。\n" +
                        "    4.沙河校区的地位\n" +
                        "    沙河校区同清水河校区共享师资。学生在沙河校区信软学院毕业后，颁发和清水河校区一样的毕业证和学位证，即同属985，211工程。\n" +
                        "    5.沙河校区信软学院八个方向的说明\n" +
                        "    软件工程下设八个方向：软件技术、信息工程、大型机和嵌入式系统网络安全 数字动漫，信息获取，计算机辅助设计与工程\n" +
                        "    二．\n" +
                        "    沙河校区有四个食堂：风华餐厅、万友餐厅、阳光餐厅、欣村学生公寓桂苑餐厅。食堂提供的菜品味道可口、品种繁多、价格合理，可满足来自不同地区的同学们的口味。\n" +
                        "    四个餐厅各有差异：桂苑方便，炒菜、饺子、包子、面等等都在二楼，环境不错。风华离学院楼最近，这里菜价是最便宜的。万友在风华二楼，菜都是用小碗装的，可以聚餐，有北方的大米呦~阳光有点贵，但是菜很全，也比较好吃。同学们自己体会哦~\n" +
                        "     电子科大周围有着无数令你流连忘返的各色成都小吃。如果你想品尝成都特色，欣村门口有百吃不厌的串串，一环路上有成都特色火锅；如果你是日迷，建设路的伊藤有你最爱的日式美食；如果你偏爱西餐，财富又一城有脍炙人口的KFC快餐、小资情调的咖啡屋为你准备着。爱吃的你是不是心动了？\n" +
                        "    沙河校区体育馆拥有一个标准篮球场，除了举办CUBA、五人制室内足球赛等各大体育赛事外，也是举办校内外各种大型活动的场所。在田径场周围，还有游泳池、露天篮球场、乒乓球台、健身器材等各种体育设施，面向全校师生开放。爱运动的你怎么能错过？\n" +
                        "\n" +
                        "    亲，玩累的时候记得上自习哦\n" +
                        "    沙河校区主要有四幢教学楼，包括：\n" +
                        "    一教：共两层，为多功能学术交流厅和大型会议厅。\n" +
                        "    二教：共七层，是利用率最高的教学楼。主要为本科教学所用。拥有较多的大型多媒体阶梯教室。\n" +
                        "    三教：共五层，主要为高年级和研究生教学所用。有很多小教室，是自习使用率较高的教学楼之一。\n" +
                        "    四教：共三层，均为200人左右大型多媒体阶梯教室，可提供较多人数的全校公共课之用。\n" +
                        "    （他们说三教是避暑圣地，最凉快）\n"
                ,"10k"
        ));

        ArticleArrayList.add(new Article(
                R.drawable.header6,
                "黄玉杰(老师)",
                "4-9",
                "山西大学",
                "    我们山西大学位于山西省省会太原市，是山西省人民政府与教育部共同建设的国家重点建设大学，国家“中西部高校基础能力建设工程”重点建设大学，国家“中西部高校综合实力提升工程”（一省一校）重点建设的14所院校之一。伴随着改革开放的时代脉搏，山西大学用新的发展谱写了绚丽的华章，以骄人的业绩书写了学府的辉煌，成为位列全国百强大学的佼佼者，中国地方高校的排头兵，山西高等教育的领头雁，综合实力为列全国60位左右，山西高校首位，在山西高等教育中具有引领、示范地位。\n" +
                        "    学校有很多社团呐，自行车协会，曾经获得过全国十佳社团，他们会定期组织出外活动，假期还有远行，还挺有意思的。还有光明社，是搞义务劳动的，周末照顾老教授什么的，这个比较有教育意义，还有吉他社、舞蹈团体，是艺术类的，都挺好的。\n" +
                        "    可以说是山西省内最好的大学了，非常推荐！\n",
                "15k"
        ));
        mRecyclerView= (RecyclerView) this.rootView.findViewById(R.id.homepage_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration mDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider_bg));
        mRecyclerView.addItemDecoration(mDecoration);


        adapter=new CommonAdapter<Article>(getContext(), R.layout.article_item, ArticleArrayList){
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
                    TextView tv_Article_likes =holder.getView(R.id.tv_status_likes);//赞同数

                profile_image.setImageDrawable(getResources().getDrawable(data.getHeader()));
                tv_Article_publisher.setText(data.getPublisher());
                tv_Article_publish_time.setText(data.getPublish_time());
                tv_Article_title.setText(data.getTitle());
                tv_Article_brief.setText(data.getContent());
                tv_Article_likes.setText(data.getLike_number());


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
                        Intent intent=new Intent(getContext(),DetailActivity.class);//跳转到这条状态的详细内容
                        intent.putExtra("content",data.getContent());
                        intent.putExtra("title",data.getTitle());
                        startActivity(intent);
                    }
                });


            }


        };

        mRecyclerView.setAdapter(adapter);
        return rootView;
    }


    //xpt
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        test1=(TextView) getActivity().findViewById(R.id.homepage1_test);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    RequestBody requestBody = new FormBody.Builder().add("username", Username).add("token", Token).add("who", "mine").build();
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://118.24.120.57:8080/education/showArticle.php").post(requestBody).build();
                    final Response response = client.newCall(request).execute();
                    final String reponseData = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //UI
                            test1.setText("测试:"+reponseData);
                            test1.setVisibility(View.GONE);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }
    //xpt
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

}






