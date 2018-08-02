package com.cary.activity.timecat.manager.client.selectstore.detial;

public class StoreDetialCollect {
    public class Store {
        private String city;

        private String content;

        private String createTime;

        private String detail;

        private int id;

        private String imgurl;

        private int lat;

        private int lng;

        private String province;

        private int serviceCount;

        private int starCount;

        private String storeName;

        private int uid;

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity() {
            return this.city;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getDetail() {
            return this.detail;
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

        public void setLat(int lat) {
            this.lat = lat;
        }

        public int getLat() {
            return this.lat;
        }

        public void setLng(int lng) {
            this.lng = lng;
        }

        public int getLng() {
            return this.lng;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getProvince() {
            return this.province;
        }

        public void setServiceCount(int serviceCount) {
            this.serviceCount = serviceCount;
        }

        public int getServiceCount() {
            return this.serviceCount;
        }

        public void setStarCount(int starCount) {
            this.starCount = starCount;
        }

        public int getStarCount() {
            return this.starCount;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getStoreName() {
            return this.storeName;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
        }

    }

    public class Data {
        private String attentionTime;

        private int id;

        private Store store;

        private int storeId;

        private int uid;

        public void setAttentionTime(String attentionTime) {
            this.attentionTime = attentionTime;
        }

        public String getAttentionTime() {
            return this.attentionTime;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setStore(Store store) {
            this.store = store;
        }

        public Store getStore() {
            return this.store;
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