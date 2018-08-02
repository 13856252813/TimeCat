package com.cary.activity.timecat.fragment.index.photography;

public class PayOrderResult {
    public class Data {
        private int id;

        private int uid;

        private String title;

        private String imgurl;

        private double amount;

        private String earnestOrdernum;

        private String ordernum;

        private String createTime;

        private double earnestMoney;

        private boolean earnestPay;

        private String type;

        private int status;

        private int storeId;

        private String attractions;

        private String clothes;

        private String infos;

        private String packages;

        private String teachers;

        private String earnestPaytime;

        private String payTime;

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

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurl() {
            return this.imgurl;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public double getAmount() {
            return this.amount;
        }

        public void setEarnestOrdernum(String earnestOrdernum) {
            this.earnestOrdernum = earnestOrdernum;
        }

        public String getEarnestOrdernum() {
            return this.earnestOrdernum;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        public String getOrdernum() {
            return this.ordernum;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public void setEarnestMoney(int earnestMoney) {
            this.earnestMoney = earnestMoney;
        }

        public double getEarnestMoney() {
            return this.earnestMoney;
        }

        public void setEarnestPay(boolean earnestPay) {
            this.earnestPay = earnestPay;
        }

        public boolean getEarnestPay() {
            return this.earnestPay;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getStoreId() {
            return this.storeId;
        }

        public void setAttractions(String attractions) {
            this.attractions = attractions;
        }

        public String getAttractions() {
            return this.attractions;
        }

        public void setClothes(String clothes) {
            this.clothes = clothes;
        }

        public String getClothes() {
            return this.clothes;
        }

        public void setInfos(String infos) {
            this.infos = infos;
        }

        public String getInfos() {
            return this.infos;
        }

        public void setPackages(String packages) {
            this.packages = packages;
        }

        public String getPackages() {
            return this.packages;
        }

        public void setTeachers(String teachers) {
            this.teachers = teachers;
        }

        public String getTeachers() {
            return this.teachers;
        }

        public void setEarnestPaytime(String earnestPaytime) {
            this.earnestPaytime = earnestPaytime;
        }

        public String getEarnestPaytime() {
            return this.earnestPaytime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getPayTime() {
            return this.payTime;
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
