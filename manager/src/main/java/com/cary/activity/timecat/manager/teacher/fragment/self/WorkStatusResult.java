package com.cary.activity.timecat.manager.teacher.fragment.self;

/**
 * Author: create by Cary
 * Date: on 2018/6/29 11:25
 * Comment:
 */
public class WorkStatusResult {
    public class Data {
        private int id;

        private int uid;

        private boolean work;

        private String createTime;

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

        public void setWork(boolean work) {
            this.work = work;
        }

        public boolean getWork() {
            return this.work;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTime() {
            return this.createTime;
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
