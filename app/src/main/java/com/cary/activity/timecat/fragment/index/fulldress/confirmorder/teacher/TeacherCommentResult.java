package com.cary.activity.timecat.fragment.index.fulldress.confirmorder.teacher;

import java.util.List;

public class TeacherCommentResult {
    public class Data {
        private int id;

        private int uid;

        private int storeId;

        private int teacherId;

        private int starCount;

        private String content;

        private String imgurl;

        private String createTime;

        private int technology;

        private int service;

        private int image;

        private int communication;

        private int punctual;

        private boolean dynamic;

        private String orderId;

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

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getStoreId() {
            return this.storeId;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
        }

        public int getTeacherId() {
            return this.teacherId;
        }

        public void setStarCount(int starCount) {
            this.starCount = starCount;
        }

        public int getStarCount() {
            return this.starCount;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurl() {
            return this.imgurl;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public void setTechnology(int technology) {
            this.technology = technology;
        }

        public int getTechnology() {
            return this.technology;
        }

        public void setService(int service) {
            this.service = service;
        }

        public int getService() {
            return this.service;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public int getImage() {
            return this.image;
        }

        public void setCommunication(int communication) {
            this.communication = communication;
        }

        public int getCommunication() {
            return this.communication;
        }

        public void setPunctual(int punctual) {
            this.punctual = punctual;
        }

        public int getPunctual() {
            return this.punctual;
        }

        public void setDynamic(boolean dynamic) {
            this.dynamic = dynamic;
        }

        public boolean getDynamic() {
            return this.dynamic;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderId() {
            return this.orderId;
        }

    }

    private String code;

    private String msg;

    private List<Data> data;

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

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return this.data;
    }

}
