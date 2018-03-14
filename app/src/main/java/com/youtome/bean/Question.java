package com.youtome.bean;

public  class Question{
        private String QuestionTitle;//辩题标题
        private String QuestionBrief;//辩题简介
        private long QuestionWatch;//浏览量
        private long QuestionLike;//赞数
        private  boolean focus;//是否关注
        public Question(String questionTitle,String questionBrief,long questionWatch,long questionLike,boolean focus){
            this.QuestionBrief=questionBrief;
            this.QuestionWatch=questionWatch;
            this.QuestionLike=questionLike;
            this.QuestionTitle=questionTitle;
            this.focus=focus;
        }
        public String getQuestionBrief() {
            return QuestionBrief;
        }
        public boolean isFocus() {
            return focus;
        }
        public void setQuestionBrief(String questionBrief) {
            QuestionBrief = questionBrief;
        }
        public void setFocus(boolean focus) {
            this.focus = focus;
        }
        public long getQuestionLike() {
            return QuestionLike;
        }
        public void setQuestionLike(long questionLike) {
            QuestionLike = questionLike;
        }
        public long getQuestionWatch() {
            return QuestionWatch;
        }
        public String getQuestionTitle() {
            return QuestionTitle;
        }
        public void setQuestionTitle(String questionTitle) {
            QuestionTitle = questionTitle;
        }
        public void setQuestionWatch(long questionWatch) {
            QuestionWatch = questionWatch;
        }

    }