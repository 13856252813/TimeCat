package com.cary.activity.timecat.manager.teacher.fragment.systemmessage;

import java.util.List;

public class SysMsgResult {
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

    public class External {

    }

    public class Data {
        private String content;

        private String createTime;

        private External external;

        private int id;

        private String messageTime;

        private boolean read;

        private String targetId;

        private String title;

        private String type;

        private int uid;

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

        public void setExternal(External external) {
            this.external = external;
        }

        public External getExternal() {
            return this.external;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setMessageTime(String messageTime) {
            this.messageTime = messageTime;
        }

        public String getMessageTime() {
            return this.messageTime;
        }

        public void setRead(boolean read) {
            this.read = read;
        }

        public boolean getRead() {
            return this.read;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public String getTargetId() {
            return this.targetId;
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