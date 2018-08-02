package com.cary.activity.timecat.manager.client.showimage;

public class ImageDataResult {
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
