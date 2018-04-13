package com.youtome.view.superadapter;

import java.util.List;

/**
 * Created by 10672 on 2018/4/12.
 */

public class Articlesuccess {
    private String status;
    public List<Articlesuccess.Detail> results;
    public static   class Detail{
        public String name;
        public String time;
        public String title;
        public String content;
        public String getName() {
            return name;
        }
        public String getTime() {
            return time;
        }
        public String getTitle() {
            return title;
        }
        public String getContent() {
            return content;
        }
    }
}
