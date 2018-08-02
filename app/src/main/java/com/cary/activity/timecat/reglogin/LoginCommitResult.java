package com.cary.activity.timecat.reglogin;

import java.io.Serializable;

/**
 * Created by Cary on 2018/4/6.
 */

public class LoginCommitResult implements Serializable{
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

    public class Data implements Serializable{
        private int id;

        private String nickname;

        private String imgurl;

        private String mobile;

        private String password;

        private String realName;

        private String idCard;

        private String cardBefore;

        private String cardAfter;

        private int amount;

        private int relatedUser;

        private String type;

        private String registerTime;

        private String lastLoginTime;

        private String requestCode;

        private int score;

        private int pid;

        private int credit;

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

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

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getAmount() {
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

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getPid() {
            return this.pid;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public int getCredit() {
            return this.credit;
        }

    }
}
