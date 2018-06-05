package com.youtome.view.superadapter;

import java.util.List;

/**
 * Created by 10672 on 2018/4/12.
 */

public class Articlesuccess {
    public String status;
    public List<Articlesuccess.Detail> results;
    public static   class Detail{
        public String id;
        public String username;
        public String sourcer;
        public String time;
        public String title;
        public String content;
        public String good;
        public String comment;
        public String transmit;

    }
}
