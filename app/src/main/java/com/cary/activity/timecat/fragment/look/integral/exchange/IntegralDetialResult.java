package com.cary.activity.timecat.fragment.look.integral.exchange;

import java.io.Serializable;
import java.util.List;

public class IntegralDetialResult implements Serializable {
    public class Items implements Serializable {
        private int id;

        private int pid;

        private int normId;

        private String name;

        private String imgurl;

        private int score;

        private int stockCount;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getPid() {
            return this.pid;
        }

        public void setNormId(int normId) {
            this.normId = normId;
        }

        public int getNormId() {
            return this.normId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurl() {
            return this.imgurl;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getScore() {
            return this.score;
        }

        public void setStockCount(int stockCount) {
            this.stockCount = stockCount;
        }

        public int getStockCount() {
            return this.stockCount;
        }

    }

    public class Norms implements Serializable {
        private int id;

        private int pid;

        private String norm;

        private List<Items> items;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getPid() {
            return this.pid;
        }

        public void setNorm(String norm) {
            this.norm = norm;
        }

        public String getNorm() {
            return this.norm;
        }

        public void setItems(List<Items> items) {
            this.items = items;
        }

        public List<Items> getItems() {
            return this.items;
        }

    }

    public class Data implements Serializable {
        private int id;

        private String title;

        private String content;

        private String imgurl;

        private String imgurls;

        private int exchangeCount;

        private int score;

        private int markerScore;

        private int storeId;

        private double postage;

        private String introduction;

        private List<Norms> norms;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
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

        public void setImgurls(String imgurls) {
            this.imgurls = imgurls;
        }

        public String getImgurls() {
            return this.imgurls;
        }

        public void setExchangeCount(int exchangeCount) {
            this.exchangeCount = exchangeCount;
        }

        public int getExchangeCount() {
            return this.exchangeCount;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getScore() {
            return this.score;
        }

        public void setMarkerScore(int markerScore) {
            this.markerScore = markerScore;
        }

        public int getMarkerScore() {
            return this.markerScore;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getStoreId() {
            return this.storeId;
        }

        public void setPostage(double postage) {
            this.postage = postage;
        }

        public double getPostage() {
            return this.postage;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getIntroduction() {
            return this.introduction;
        }

        public void setNorms(List<Norms> norms) {
            this.norms = norms;
        }

        public List<Norms> getNorms() {
            return this.norms;
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