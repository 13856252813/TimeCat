package com.cary.activity.timecat.manager.client.selectstore.detial;

import java.util.List;

//时光老师
public class ShowTeacherComResult {
    public class Works {
        private int id;

        private int uid;

        private String address;

        private String imgurls;

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

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddress() {
            return this.address;
        }

        public void setImgurls(String imgurls) {
            this.imgurls = imgurls;
        }

        public String getImgurls() {
            return this.imgurls;
        }

    }

    public class Data {
        private int id;

        private int uid;

        private int payAmount;

        private boolean pay;

        private int storeId;

        private String teacherType;

        private String applyType;

        private String provice;

        private String city;

        private String area;

        private String detail;

        private String emergencyContact;

        private String emergencyMobile;

        private String device;

        private String selfIntroduction;

        private int status;

        private List<Works> works;

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

        public void setPayAmount(int payAmount) {
            this.payAmount = payAmount;
        }

        public int getPayAmount() {
            return this.payAmount;
        }

        public void setPay(boolean pay) {
            this.pay = pay;
        }

        public boolean getPay() {
            return this.pay;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getStoreId() {
            return this.storeId;
        }

        public void setTeacherType(String teacherType) {
            this.teacherType = teacherType;
        }

        public String getTeacherType() {
            return this.teacherType;
        }

        public void setApplyType(String applyType) {
            this.applyType = applyType;
        }

        public String getApplyType() {
            return this.applyType;
        }

        public void setProvice(String provice) {
            this.provice = provice;
        }

        public String getProvice() {
            return this.provice;
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

        public void setEmergencyContact(String emergencyContact) {
            this.emergencyContact = emergencyContact;
        }

        public String getEmergencyContact() {
            return this.emergencyContact;
        }

        public void setEmergencyMobile(String emergencyMobile) {
            this.emergencyMobile = emergencyMobile;
        }

        public String getEmergencyMobile() {
            return this.emergencyMobile;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public String getDevice() {
            return this.device;
        }

        public void setSelfIntroduction(String selfIntroduction) {
            this.selfIntroduction = selfIntroduction;
        }

        public String getSelfIntroduction() {
            return this.selfIntroduction;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return this.status;
        }

        public void setWorks(List<Works> works) {
            this.works = works;
        }

        public List<Works> getWorks() {
            return this.works;
        }

    }

    private String code;

    private String msg;

    private List<Data> data;

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

}
