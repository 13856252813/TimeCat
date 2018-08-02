package com.cary.activity.timecat.fragment.look.interact;

/**
 * Author: create by Cary
 * Date: on 2018/6/14 22:34
 * Comment:  增加评论
 */
public class InteractCommentAddResult {
    public class Data {
        private int id;

        private int interactiveId;

        private int uid;

        private int evaUid;

        private String content;

        private String evaTime;

        private int evaId;

        private String nickname;

        private String imgurl;

        private String evaNickname;

        private String evaImgurl;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setInteractiveId(int interactiveId) {
            this.interactiveId = interactiveId;
        }

        public int getInteractiveId() {
            return this.interactiveId;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
        }

        public void setEvaUid(int evaUid) {
            this.evaUid = evaUid;
        }

        public int getEvaUid() {
            return this.evaUid;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setEvaTime(String evaTime) {
            this.evaTime = evaTime;
        }

        public String getEvaTime() {
            return this.evaTime;
        }

        public void setEvaId(int evaId) {
            this.evaId = evaId;
        }

        public int getEvaId() {
            return this.evaId;
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

        public void setEvaNickname(String evaNickname) {
            this.evaNickname = evaNickname;
        }

        public String getEvaNickname() {
            return this.evaNickname;
        }

        public void setEvaImgurl(String evaImgurl) {
            this.evaImgurl = evaImgurl;
        }

        public String getEvaImgurl() {
            return this.evaImgurl;
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
