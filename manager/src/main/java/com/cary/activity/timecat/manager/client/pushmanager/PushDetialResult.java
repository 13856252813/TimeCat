package com.cary.activity.timecat.manager.client.pushmanager;

/**
 * Author: create by Cary
 * Date: on 2018/6/23 14:36
 * Comment:
 */
public class PushDetialResult {
    public class Data {
        private String content;

        private int id;

        private String imgurl;

        private String nickname;

        private String pushTime;

        private int pushUid;

        private int storeId;

        private String title;

        private int type;

        private int uid;

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurl() {
            return this.imgurl;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNickname() {
            return this.nickname;
        }

        public void setPushTime(String pushTime) {
            this.pushTime = pushTime;
        }

        public String getPushTime() {
            return this.pushTime;
        }

        public void setPushUid(int pushUid) {
            this.pushUid = pushUid;
        }

        public int getPushUid() {
            return this.pushUid;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getStoreId() {
            return this.storeId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return this.type;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
        }

    }

    private String code;

    private Data data;

    private String msg;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return this.data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

}