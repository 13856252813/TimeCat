package com.cary.activity.timecat.manager.client.selectstore.detial;

import java.util.List;

public class StoreDetialCommentResult {
    public class Data {
        private int id;

        private int packageId;

        private int orderId;

        private int storeId;

        private int uid;

        private String content;

        private String imgurl;

        private String evaTime;

        private int starCount;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setPackageId(int packageId) {
            this.packageId = packageId;
        }

        public int getPackageId() {
            return this.packageId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getOrderId() {
            return this.orderId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getStoreId() {
            return this.storeId;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
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

        public void setEvaTime(String evaTime) {
            this.evaTime = evaTime;
        }

        public String getEvaTime() {
            return this.evaTime;
        }

        public void setStarCount(int starCount) {
            this.starCount = starCount;
        }

        public int getStarCount() {
            return this.starCount;
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
