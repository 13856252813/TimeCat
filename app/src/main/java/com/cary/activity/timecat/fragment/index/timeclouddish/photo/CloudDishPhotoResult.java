package com.cary.activity.timecat.fragment.index.timeclouddish.photo;

import java.util.List;

/**
 * Created by Cary on 2018/4/9.
 */

public class CloudDishPhotoResult {
    public class Query {

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

        private int folderId;

        private String key;

        private String note;

        private String lastUpdateTime;

        private String fileName;

        private String bucketName;

        private int fileSize;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setFolderId(int folderId) {
            this.folderId = folderId;
        }

        public int getFolderId() {
            return this.folderId;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getKey() {
            return this.key;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getNote() {
            return this.note;
        }

        public void setLastUpdateTime(String lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }

        public String getLastUpdateTime() {
            return this.lastUpdateTime;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return this.fileName;
        }

        public void setBucketName(String bucketName) {
            this.bucketName = bucketName;
        }

        public String getBucketName() {
            return this.bucketName;
        }

        public void setFileSize(int fileSize) {
            this.fileSize = fileSize;
        }

        public int getFileSize() {
            return this.fileSize;
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