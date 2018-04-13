package com.youtome.view.superadapter;

import java.util.List;

/**
 * Created by 10672 on 2018/4/12.
 */

public class University3 {
    private String status;
    public List<University3.Detail> results;
    public static   class Detail{
        public String university;
        public String location;
        public String batch;
        public int header;
        public Detail(String university,String location,String batch,int header) {
            this.university=university;
            this.location=location;
            this.batch=batch;
            this.header=header;
        }
        public String getUniversity() {
            return university;
        }
        public String getLocation() {
            return location;
        }
        public String getBatch() {
            return batch;
        }

        public int getHeader() {
            return header;
        }
    }
}
