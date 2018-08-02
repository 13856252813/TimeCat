package com.cary.activity.timecat.manager.client.myorder;

/**
 * Author: create by Cary
 * Date: on 2018/6/23 16:55
 * Comment:
 */
public class ReturnOrderResult {
    public class Data {
        private int id;

        private String mobile;

        private String name;

        private int oid;

        private String questType;

        private String remark;

        private String type;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMobile() {
            return this.mobile;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }

        public int getOid() {
            return this.oid;
        }

        public void setQuestType(String questType) {
            this.questType = questType;
        }

        public String getQuestType() {
            return this.questType;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRemark() {
            return this.remark;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
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
