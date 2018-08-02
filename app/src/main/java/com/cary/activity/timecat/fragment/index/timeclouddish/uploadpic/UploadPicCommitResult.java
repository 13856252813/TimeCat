package com.cary.activity.timecat.fragment.index.timeclouddish.uploadpic;

public class UploadPicCommitResult {
    public class Data {
        private String bucketName;

        private String fileName;

        private int folderId;

        private int id;

        private String key;

        private String lastUpdateTime;

        private String note;

        public void setBucketName(String bucketName) {
            this.bucketName = bucketName;
        }

        public String getBucketName() {
            return this.bucketName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return this.fileName;
        }

        public void setFolderId(int folderId) {
            this.folderId = folderId;
        }

        public int getFolderId() {
            return this.folderId;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getKey() {
            return this.key;
        }

        public void setLastUpdateTime(String lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }

        public String getLastUpdateTime() {
            return this.lastUpdateTime;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getNote() {
            return this.note;
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