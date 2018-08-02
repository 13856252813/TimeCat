package com.cary.activity.timecat.manager.client.question;

import java.util.List;

/**
 * Author: create by Cary
 * Date: on 2018/6/23 00:37
 * Comment:
 */
public class QuestionDetialResult {
    public class Replies {
        private int id;

        private int feedbackId;

        private String content;

        private int uid;

        private String createTime;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setFeedbackId(int feedbackId) {
            this.feedbackId = feedbackId;
        }

        public int getFeedbackId() {
            return this.feedbackId;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTime() {
            return this.createTime;
        }

    }

    public class Data {
        private int id;

        private int uid;

        private String nickname;

        private String imgurl;

        private String name;

        private String content;

        private String mobile;

        private String questionType;

        private boolean hasAnswer;

        private String createTime;

        private boolean success;

        private List<Replies> replies;

        private int storeId;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNickname() {
            return this.nickname;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurl() {
            return this.imgurl;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMobile() {
            return this.mobile;
        }

        public void setQuestionType(String questionType) {
            this.questionType = questionType;
        }

        public String getQuestionType() {
            return this.questionType;
        }

        public void setHasAnswer(boolean hasAnswer) {
            this.hasAnswer = hasAnswer;
        }

        public boolean getHasAnswer() {
            return this.hasAnswer;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public boolean getSuccess() {
            return this.success;
        }

        public void setReplies(List<Replies> replies) {
            this.replies = replies;
        }

        public List<Replies> getReplies() {
            return this.replies;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getStoreId() {
            return this.storeId;
        }

    }

    private String code;

    private String msg;

    private Data data;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return this.data;
    }

}