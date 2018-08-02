package com.cary.activity.timecat.manager.client.fulldress.detial;

import java.io.Serializable;
import java.util.List;

public class FullDressDetialResult implements Serializable {
    public class Store implements Serializable {
        private int id;

        private int uid;

        private String storeName;

        private String imgurl;

        private String province;

        private String city;

        private String detail;

        private String content;

        private String createTime;

        private int lng;

        private int lat;

        private int starCount;

        private int serviceCount;

        private String tel;

        private boolean attention;

        private String loginUid;

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

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getStoreName() {
            return this.storeName;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurl() {
            return this.imgurl;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getProvince() {
            return this.province;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity() {
            return this.city;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getDetail() {
            return this.detail;
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

        public void setLng(int lng) {
            this.lng = lng;
        }

        public int getLng() {
            return this.lng;
        }

        public void setLat(int lat) {
            this.lat = lat;
        }

        public int getLat() {
            return this.lat;
        }

        public void setStarCount(int starCount) {
            this.starCount = starCount;
        }

        public int getStarCount() {
            return this.starCount;
        }

        public void setServiceCount(int serviceCount) {
            this.serviceCount = serviceCount;
        }

        public int getServiceCount() {
            return this.serviceCount;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getTel() {
            return this.tel;
        }

        public void setAttention(boolean attention) {
            this.attention = attention;
        }

        public boolean getAttention() {
            return this.attention;
        }

        public void setLoginUid(String loginUid) {
            this.loginUid = loginUid;
        }

        public String getLoginUid() {
            return this.loginUid;
        }

    }

    public class Items implements Serializable {
        private int id;

        private int normId;

        private String item;

        private int inventory;

        private int price;

        private String imgurl;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setNormId(int normId) {
            this.normId = normId;
        }

        public int getNormId() {
            return this.normId;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getItem() {
            return this.item;
        }

        public void setInventory(int inventory) {
            this.inventory = inventory;
        }

        public int getInventory() {
            return this.inventory;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getPrice() {
            return this.price;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurl() {
            return this.imgurl;
        }

    }

    public class Norms implements Serializable {
        private int id;

        private int clothId;

        private String norm;

        private List<Items> items;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setClothId(int clothId) {
            this.clothId = clothId;
        }

        public int getClothId() {
            return this.clothId;
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

        private int storeId;

        private String title;

        private String imgurl;

        private String imgurls;

        private int sellCount;

        private int starCount;

        private int amount;

        private int markerPrice;

        private int paiAmount;

        private String content;

        private int sex;

        private boolean hot;

        private int catagory;

        private int sellType;

        private List<Norms> norms;

        private Store store;

        private boolean attention;

        private String loginUid;

        private int postage;

        private String sendtype;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
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

        public void setSellCount(int sellCount) {
            this.sellCount = sellCount;
        }

        public int getSellCount() {
            return this.sellCount;
        }

        public void setStarCount(int starCount) {
            this.starCount = starCount;
        }

        public int getStarCount() {
            return this.starCount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getAmount() {
            return this.amount;
        }

        public void setMarkerPrice(int markerPrice) {
            this.markerPrice = markerPrice;
        }

        public int getMarkerPrice() {
            return this.markerPrice;
        }

        public void setPaiAmount(int paiAmount) {
            this.paiAmount = paiAmount;
        }

        public int getPaiAmount() {
            return this.paiAmount;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getSex() {
            return this.sex;
        }

        public void setHot(boolean hot) {
            this.hot = hot;
        }

        public boolean getHot() {
            return this.hot;
        }

        public void setCatagory(int catagory) {
            this.catagory = catagory;
        }

        public int getCatagory() {
            return this.catagory;
        }

        public void setSellType(int sellType) {
            this.sellType = sellType;
        }

        public int getSellType() {
            return this.sellType;
        }

        public void setNorms(List<Norms> norms) {
            this.norms = norms;
        }

        public List<Norms> getNorms() {
            return this.norms;
        }

        public void setStore(Store store) {
            this.store = store;
        }

        public Store getStore() {
            return this.store;
        }

        public void setAttention(boolean attention) {
            this.attention = attention;
        }

        public boolean getAttention() {
            return this.attention;
        }

        public void setLoginUid(String loginUid) {
            this.loginUid = loginUid;
        }

        public String getLoginUid() {
            return this.loginUid;
        }

        public void setPostage(int postage) {
            this.postage = postage;
        }

        public int getPostage() {
            return this.postage;
        }

        public void setSendtype(String sendtype) {
            this.sendtype = sendtype;
        }

        public String getSendtype() {
            return this.sendtype;
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