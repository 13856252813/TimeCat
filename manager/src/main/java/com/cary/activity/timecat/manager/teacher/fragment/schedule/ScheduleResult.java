package com.cary.activity.timecat.manager.teacher.fragment.schedule;

import java.util.List;

/**
 * Author: create by Cary
 * Date: on 2018/6/29 11:49
 * Comment:
 */
public class ScheduleResult {
    public class Query {
        private String dateMonth;

        public void setDateMonth(String dateMonth) {
            this.dateMonth = dateMonth;
        }

        public String getDateMonth() {
            return this.dateMonth;
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

        private int teacherId;

        private String scheduleDate;

        private int taskId;

        private int uid;

        private String nickname;

        private String imgurl;

        private boolean success;

        private String type;

        private String itemType;

        private int amount;

        private int status;

        private String createTime;

        private String dateMonth;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
        }

        public int getTeacherId() {
            return this.teacherId;
        }

        public void setScheduleDate(String scheduleDate) {
            this.scheduleDate = scheduleDate;
        }

        public String getScheduleDate() {
            return this.scheduleDate;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public int getTaskId() {
            return this.taskId;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNickname() {
            return this.nickname;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurl() {
            return this.imgurl;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public boolean getSuccess() {
            return this.success;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public String getItemType() {
            return this.itemType;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getAmount() {
            return this.amount;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return this.status;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public void setDateMonth(String dateMonth) {
            this.dateMonth = dateMonth;
        }

        public String getDateMonth() {
            return this.dateMonth;
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