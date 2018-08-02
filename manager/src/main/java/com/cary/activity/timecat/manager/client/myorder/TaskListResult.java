package com.cary.activity.timecat.manager.client.myorder;

import java.util.List;

public class TaskListResult {
    public class Query {
        private boolean over;

        private int uid;

        public void setOver(boolean over) {
            this.over = over;
        }

        public boolean getOver() {
            return this.over;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
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

        private int uid;

        private String serviceTime;

        private String serviceCity;

        private String content;

        private int amount;

        private boolean success;

        private boolean over;

        private boolean hasReply;

        private String type;

        private String createTime;

        private String teacherId;

        private String grabId;

        private String grabs;

        private String ordernum;

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

        public void setServiceTime(String serviceTime) {
            this.serviceTime = serviceTime;
        }

        public String getServiceTime() {
            return this.serviceTime;
        }

        public void setServiceCity(String serviceCity) {
            this.serviceCity = serviceCity;
        }

        public String getServiceCity() {
            return this.serviceCity;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getAmount() {
            return this.amount;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public boolean getSuccess() {
            return this.success;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public boolean getOver() {
            return this.over;
        }

        public void setHasReply(boolean hasReply) {
            this.hasReply = hasReply;
        }

        public boolean getHasReply() {
            return this.hasReply;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public void setTeacherId(String teacherId) {
            this.teacherId = teacherId;
        }

        public String getTeacherId() {
            return this.teacherId;
        }

        public void setGrabId(String grabId) {
            this.grabId = grabId;
        }

        public String getGrabId() {
            return this.grabId;
        }

        public void setGrabs(String grabs) {
            this.grabs = grabs;
        }

        public String getGrabs() {
            return this.grabs;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        public String getOrdernum() {
            return this.ordernum;
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
