package com.cary.activity.timecat.fragment.person.receiveadd;

import java.io.Serializable;

public class NewReceivedResult implements Serializable {
    public class Data implements Serializable{
        private int id;

        private int uid;

        private String name;

        private String mobile;

        private String province;

        private String city;

        private String area;

        private String detail;

        private boolean isDefault;

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

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMobile() {
            return this.mobile;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getProvince() {
            return this.province;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity() {
            return this.city;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getArea() {
            return this.area;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getDetail() {
            return this.detail;
        }

        public void setIsDefault(boolean isDefault) {
            this.isDefault = isDefault;
        }

        public boolean getIsDefault() {
            return this.isDefault;
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
