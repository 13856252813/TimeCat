package com.cary.activity.timecat.manager.client.fulldress.fragment;

import java.util.List;

/**
 * 服装的列表
 */
public class FullDressColtheResult {
    public class Query {
        private int sex;

        private int sellType;

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getSex() {
            return this.sex;
        }

        public void setSellType(int sellType) {
            this.sellType = sellType;
        }

        public int getSellType() {
            return this.sellType;
        }

    }

    public class Pi {
        private int pageSize;

        private int totalPage;

        private int currentPage;

        private int totalSize;

        private Query query;

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageSize() {
            return this.pageSize;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalPage() {
            return this.totalPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getCurrentPage() {
            return this.currentPage;
        }

        public void setTotalSize(int totalSize) {
            this.totalSize = totalSize;
        }

        public int getTotalSize() {
            return this.totalSize;
        }

        public void setQuery(Query query) {
            this.query = query;
        }

        public Query getQuery() {
            return this.query;
        }

    }

    public class Data {
        private int id;

        private int storeId;

        private String title;

        private String imgurl;

        private String imgurls;

        private int sellCount;

        private int starCount;

        private int amount;

        private int paiAmount;

        private String content;

        private int sex;

        private boolean hot;

        private int catagory;

        private int sellType;

        private String norms;

        private String store;

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

        public void setNorms(String norms) {
            this.norms = norms;
        }

        public String getNorms() {
            return this.norms;
        }

        public void setStore(String store) {
            this.store = store;
        }

        public String getStore() {
            return this.store;
        }

    }

    private String code;

    private String msg;

    private List<Data> data;

    private Pi pi;

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

    public void setPi(Pi pi) {
        this.pi = pi;
    }

    public Pi getPi() {
        return this.pi;
    }

}
