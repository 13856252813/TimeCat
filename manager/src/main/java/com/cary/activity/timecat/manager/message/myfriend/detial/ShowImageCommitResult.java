package com.cary.activity.timecat.manager.message.myfriend.detial;

import java.io.Serializable;
import java.util.List;

public class ShowImageCommitResult implements Serializable {
    public class Query {

    }

    public class Pi {
        private int currentPage;

        private int pageSize;

        private Query query;

        private int totalPage;

        private int totalSize;

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getCurrentPage() {
            return this.currentPage;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageSize() {
            return this.pageSize;
        }

        public void setQuery(Query query) {
            this.query = query;
        }

        public Query getQuery() {
            return this.query;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalPage() {
            return this.totalPage;
        }

        public void setTotalSize(int totalSize) {
            this.totalSize = totalSize;
        }

        public int getTotalSize() {
            return this.totalSize;
        }

    }

    public class Data {
        private int clothCount;

        private String content;

        private String createTime;

        private int evaStar;

        private int filmCount;

        private int frontMoney;

        private int id;

        private String imgurl;

        private String imgurls;

        private int marketPrice;

        private int packagePhotoType;

        private int packageType;

        private int price;

        private int rucheCount;

        private int sellCount;

        private int storeId;

        private String storeImgurl;

        private String storeName;

        private String title;

        private String type;

        public void setClothCount(int clothCount) {
            this.clothCount = clothCount;
        }

        public int getClothCount() {
            return this.clothCount;
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

        public void setEvaStar(int evaStar) {
            this.evaStar = evaStar;
        }

        public int getEvaStar() {
            return this.evaStar;
        }

        public void setFilmCount(int filmCount) {
            this.filmCount = filmCount;
        }

        public int getFilmCount() {
            return this.filmCount;
        }

        public void setFrontMoney(int frontMoney) {
            this.frontMoney = frontMoney;
        }

        public int getFrontMoney() {
            return this.frontMoney;
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

        public void setImgurls(String imgurls) {
            this.imgurls = imgurls;
        }

        public String getImgurls() {
            return this.imgurls;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public int getMarketPrice() {
            return this.marketPrice;
        }

        public void setPackagePhotoType(int packagePhotoType) {
            this.packagePhotoType = packagePhotoType;
        }

        public int getPackagePhotoType() {
            return this.packagePhotoType;
        }

        public void setPackageType(int packageType) {
            this.packageType = packageType;
        }

        public int getPackageType() {
            return this.packageType;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getPrice() {
            return this.price;
        }

        public void setRucheCount(int rucheCount) {
            this.rucheCount = rucheCount;
        }

        public int getRucheCount() {
            return this.rucheCount;
        }

        public void setSellCount(int sellCount) {
            this.sellCount = sellCount;
        }

        public int getSellCount() {
            return this.sellCount;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getStoreId() {
            return this.storeId;
        }

        public void setStoreImgurl(String storeImgurl) {
            this.storeImgurl = storeImgurl;
        }

        public String getStoreImgurl() {
            return this.storeImgurl;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getStoreName() {
            return this.storeName;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

    }

    private String code;

    private List<Data> data;

    private String msg;

    private Pi pi;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return this.data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setPi(Pi pi) {
        this.pi = pi;
    }

    public Pi getPi() {
        return this.pi;
    }
}
