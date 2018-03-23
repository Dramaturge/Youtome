package com.youtome.view.superadapter;

import java.util.List;

/**
 * Created by 10672 on 2018/3/18.
 */

public class Searchsuccess {
    private String status;
    public List<Detail> results;
        public static   class Detail{
        public String name;
        public String gender;
        public String grade;
        public String school;
        public String college;
        public String subject;
        public String experience;
        public String style;
        public String motto;
        public String getName() {
            return name;
        }
        public String getGender() {
            return gender;
        }
        public String getGrade() {
            return grade;
        }
        public String getSchool() {
            return school;
        }
        public String getCollege() {
            return college;
        }
        public String getSubject() {
            return subject;
        }
        public String getExperience() {
            return experience;
        }
        public String getStyle() {
            return style;
        }
        public String getMotto() {
            return motto;
        }
    }
}
