package com.cary.activity.timecat.fragment.person.self;

/**
 * Author: create by Cary
 * Date: on 2018/6/15 23:15
 * Comment:
 */
public class PersonSelfResult {
    public class Data {
        private int id;

        private String nickname;

        private String imgurl;

        private String mobile;

        private String password;

        private String realName;

        private String idCard;

        private String cardBefore;

        private String cardAfter;

        private double totalAmount;

        private double freezeAmount;

        private double amount;

        private int relatedUser;

        private String type;

        private int score;

        private int credit;

        private String requestCode;

        private String registerTime;

        private String lastLoginTime;

        private int pid;

        private double photoAmount;

        private double extraAmount;

        private String teacherType;

        private int shopId;

        private String level;

        private String position;

        private String openid;

        private String qqid;

        private String weiboid;

        private double totalDeposit;

        private double deposit;

        private String deviceToken;

        private String deviceType;

        private String token;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNickname() {
            return this.nickname;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurl() {
            return this.imgurl;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMobile() {
            return this.mobile;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return this.password;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getRealName() {
            return this.realName;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getIdCard() {
            return this.idCard;
        }

        public void setCardBefore(String cardBefore) {
            this.cardBefore = cardBefore;
        }

        public String getCardBefore() {
            return this.cardBefore;
        }

        public void setCardAfter(String cardAfter) {
            this.cardAfter = cardAfter;
        }

        public String getCardAfter() {
            return this.cardAfter;
        }

        public void setTotalAmount(int totalAmount) {
            this.totalAmount = totalAmount;
        }

        public double getTotalAmount() {
            return this.totalAmount;
        }

        public void setFreezeAmount(int freezeAmount) {
            this.freezeAmount = freezeAmount;
        }

        public double getFreezeAmount() {
            return this.freezeAmount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public double getAmount() {
            return this.amount;
        }

        public void setRelatedUser(int relatedUser) {
            this.relatedUser = relatedUser;
        }

        public int getRelatedUser() {
            return this.relatedUser;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getScore() {
            return this.score;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public int getCredit() {
            return this.credit;
        }

        public void setRequestCode(String requestCode) {
            this.requestCode = requestCode;
        }

        public String getRequestCode() {
            return this.requestCode;
        }

        public void setRegisterTime(String registerTime) {
            this.registerTime = registerTime;
        }

        public String getRegisterTime() {
            return this.registerTime;
        }

        public void setLastLoginTime(String lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getLastLoginTime() {
            return this.lastLoginTime;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getPid() {
            return this.pid;
        }

        public void setPhotoAmount(int photoAmount) {
            this.photoAmount = photoAmount;
        }

        public double getPhotoAmount() {
            return this.photoAmount;
        }

        public void setExtraAmount(int extraAmount) {
            this.extraAmount = extraAmount;
        }

        public double getExtraAmount() {
            return this.extraAmount;
        }

        public void setTeacherType(String teacherType) {
            this.teacherType = teacherType;
        }

        public String getTeacherType() {
            return this.teacherType;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getShopId() {
            return this.shopId;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLevel() {
            return this.level;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getPosition() {
            return this.position;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getOpenid() {
            return this.openid;
        }

        public void setQqid(String qqid) {
            this.qqid = qqid;
        }

        public String getQqid() {
            return this.qqid;
        }

        public void setWeiboid(String weiboid) {
            this.weiboid = weiboid;
        }

        public String getWeiboid() {
            return this.weiboid;
        }

        public void setTotalDeposit(int totalDeposit) {
            this.totalDeposit = totalDeposit;
        }

        public double getTotalDeposit() {
            return this.totalDeposit;
        }

        public void setDeposit(double deposit) {
            this.deposit = deposit;
        }

        public double getDeposit() {
            return this.deposit;
        }

        public void setDeviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
        }

        public String getDeviceToken() {
            return this.deviceToken;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getDeviceType() {
            return this.deviceType;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getToken() {
            return this.token;
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
