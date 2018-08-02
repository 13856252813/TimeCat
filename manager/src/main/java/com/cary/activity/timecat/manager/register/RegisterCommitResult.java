package com.cary.activity.timecat.manager.register;

/**
 * Created by Cary on 2018/4/6.
 */

public class RegisterCommitResult {
    private String code;

    private Data data;

    private String msg;

    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
    public class Data {
        private int amount;

        private String cardAfter;

        private String cardBefore;

        private int credit;

        private int id;

        private String idCard;

        private String imgurl;

        private String lastLoginTime;

        private String mobile;

        private String nickname;

        private String password;

        private int pid;

        private String realName;

        private String registerTime;

        private int relatedUser;

        private String requestCode;

        private int score;

        private String type;

        public void setAmount(int amount){
            this.amount = amount;
        }
        public int getAmount(){
            return this.amount;
        }
        public void setCardAfter(String cardAfter){
            this.cardAfter = cardAfter;
        }
        public String getCardAfter(){
            return this.cardAfter;
        }
        public void setCardBefore(String cardBefore){
            this.cardBefore = cardBefore;
        }
        public String getCardBefore(){
            return this.cardBefore;
        }
        public void setCredit(int credit){
            this.credit = credit;
        }
        public int getCredit(){
            return this.credit;
        }
        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setIdCard(String idCard){
            this.idCard = idCard;
        }
        public String getIdCard(){
            return this.idCard;
        }
        public void setImgurl(String imgurl){
            this.imgurl = imgurl;
        }
        public String getImgurl(){
            return this.imgurl;
        }
        public void setLastLoginTime(String lastLoginTime){
            this.lastLoginTime = lastLoginTime;
        }
        public String getLastLoginTime(){
            return this.lastLoginTime;
        }
        public void setMobile(String mobile){
            this.mobile = mobile;
        }
        public String getMobile(){
            return this.mobile;
        }
        public void setNickname(String nickname){
            this.nickname = nickname;
        }
        public String getNickname(){
            return this.nickname;
        }
        public void setPassword(String password){
            this.password = password;
        }
        public String getPassword(){
            return this.password;
        }
        public void setPid(int pid){
            this.pid = pid;
        }
        public int getPid(){
            return this.pid;
        }
        public void setRealName(String realName){
            this.realName = realName;
        }
        public String getRealName(){
            return this.realName;
        }
        public void setRegisterTime(String registerTime){
            this.registerTime = registerTime;
        }
        public String getRegisterTime(){
            return this.registerTime;
        }
        public void setRelatedUser(int relatedUser){
            this.relatedUser = relatedUser;
        }
        public int getRelatedUser(){
            return this.relatedUser;
        }
        public void setRequestCode(String requestCode){
            this.requestCode = requestCode;
        }
        public String getRequestCode(){
            return this.requestCode;
        }
        public void setScore(int score){
            this.score = score;
        }
        public int getScore(){
            return this.score;
        }
        public void setType(String type){
            this.type = type;
        }
        public String getType(){
            return this.type;
        }

    }
}
