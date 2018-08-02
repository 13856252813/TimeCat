package com.cary.activity.timecat.fragment.person.attention.news;

import java.util.List;

/**
 * Author: create by Cary
 * Date: on 2018/6/16 11:08
 * Comment:
 */
public class NewsAttentionResult {
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

    public class Information {
        private String content;

        private String createTime;

        private int id;

        private String imgurl;

        private String title;

        private int type;

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

    }

    public class Data {
        private String attentionTime;

        private int id;

        private Information information;

        private int informationId;

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

        public void setInformation(Information information) {
            this.information = information;
        }

        public Information getInformation() {
            return this.information;
        }

        public void setInformationId(int informationId) {
            this.informationId = informationId;
        }

        public int getInformationId() {
            return this.informationId;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
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
