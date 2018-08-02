package com.cary.activity.timecat.manager.client.fulldress.detial;

import java.io.Serializable;

public class DefaultRecAddResult implements Serializable {
    public class Data implements Serializable {
        private String area;

        private String city;

        private String detail;

        private int id;

        private boolean isDefault;

        private String mobile;

        private String name;

        private String province;

        private int uid;

        public void setArea(String area) {
            this.area = area;
        }

        public String getArea() {
            return this.area;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity() {
            return this.city;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getDetail() {
            return this.detail;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setIsDefault(boolean isDefault) {
            this.isDefault = isDefault;
        }

        public boolean getIsDefault() {
            return this.isDefault;
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

        public void setProvince(String province) {
            this.province = province;
        }

        public String getProvince() {
            return this.province;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
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
