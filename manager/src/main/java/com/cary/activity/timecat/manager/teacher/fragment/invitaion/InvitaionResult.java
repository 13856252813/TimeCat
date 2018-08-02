package com.cary.activity.timecat.manager.teacher.fragment.invitaion;

import java.util.List;

/**
 * Author: create by Cary
 * Date: on 2018/6/16 14:14
 * Comment:
 */
public class InvitaionResult {
    public class Data {
        private int amount;

        private String cardAfter;

        private String cardBefore;

        private int credit;

        private int deposit;

        private String deviceToken;

        private String deviceType;

        private int extraAmount;

        private int freezeAmount;

        private int id;

        private String idCard;

        private String imgurl;

        private String lastLoginTime;

        private String level;

        private String mobile;

        private String nickname;

        private String openid;

        private String password;

        private int photoAmount;

        private int pid;

        private String position;

        private String qqid;

        private String realName;

        private String registerTime;

        private int relatedUser;

        private String requestCode;

        private int score;

        private int shopId;

        private String teacherType;

        private String token;

        private int totalAmount;

        private int totalDeposit;

        private String type;

        private String weiboid;

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getAmount() {
            return this.amount;
        }

        public void setCardAfter(String cardAfter) {
            this.cardAfter = cardAfter;
        }

        public String getCardAfter() {
            return this.cardAfter;
        }

        public void setCardBefore(String cardBefore) {
            this.cardBefore = cardBefore;
        }

        public String getCardBefore() {
            return this.cardBefore;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public int getCredit() {
            return this.credit;
        }

        public void setDeposit(int deposit) {
            this.deposit = deposit;
        }

        public int getDeposit() {
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

        public void setExtraAmount(int extraAmount) {
            this.extraAmount = extraAmount;
        }

        public int getExtraAmount() {
            return this.extraAmount;
        }

        public void setFreezeAmount(int freezeAmount) {
            this.freezeAmount = freezeAmount;
        }

        public int getFreezeAmount() {
            return this.freezeAmount;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getIdCard() {
            return this.idCard;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurl() {
            return this.imgurl;
        }

        public void setLastLoginTime(String lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getLastLoginTime() {
            return this.lastLoginTime;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLevel() {
            return this.level;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMobile() {
            return this.mobile;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNickname() {
            return this.nickname;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getOpenid() {
            return this.openid;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPhotoAmount(int photoAmount) {
            this.photoAmount = photoAmount;
        }

        public int getPhotoAmount() {
            return this.photoAmount;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getPid() {
            return this.pid;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getPosition() {
            return this.position;
        }

        public void setQqid(String qqid) {
            this.qqid = qqid;
        }

        public String getQqid() {
            return this.qqid;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getRealName() {
            return this.realName;
        }

        public void setRegisterTime(String registerTime) {
            this.registerTime = registerTime;
        }

        public String getRegisterTime() {
            return this.registerTime;
        }

        public void setRelatedUser(int relatedUser) {
            this.relatedUser = relatedUser;
        }

        public int getRelatedUser() {
            return this.relatedUser;
        }

        public void setRequestCode(String requestCode) {
            this.requestCode = requestCode;
        }

        public String getRequestCode() {
            return this.requestCode;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getScore() {
            return this.score;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getShopId() {
            return this.shopId;
        }

        public void setTeacherType(String teacherType) {
            this.teacherType = teacherType;
        }

        public String getTeacherType() {
            return this.teacherType;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getToken() {
            return this.token;
        }

        public void setTotalAmount(int totalAmount) {
            this.totalAmount = totalAmount;
        }

        public int getTotalAmount() {
            return this.totalAmount;
        }

        public void setTotalDeposit(int totalDeposit) {
            this.totalDeposit = totalDeposit;
        }

        public int getTotalDeposit() {
            return this.totalDeposit;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

        public void setWeiboid(String weiboid) {
            this.weiboid = weiboid;
        }

        public String getWeiboid() {
            return this.weiboid;
        }

    }

    private String code;

    private List<Data> data;

    private String msg;

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

}