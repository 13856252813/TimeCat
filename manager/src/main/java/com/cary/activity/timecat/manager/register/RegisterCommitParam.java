package com.cary.activity.timecat.manager.register;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cary on 2018/4/6.
 */

public class RegisterCommitParam implements Serializable {
    private String nickname;//昵称
    private String imgurl;//头像
    private String mobile;//手机号
    private String password;//密码
    private String requestCode;//邀请码
    private String realName;// 真实姓名
    private String idCard;//身份证号码
    private String cardBefore;//身份证正面
    private String cardAfter;//身份证反面
    private String relatedUser;//关联用户id
    private String type;//类型(0个人,1老师,2管理人员)
    private String smsCode;//验证码

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setCardBefore(String cardBefore) {
        this.cardBefore = cardBefore;
    }

    public void setCardAfter(String cardAfter) {
        this.cardAfter = cardAfter;
    }

    public void setRelatedUser(String relatedUser) {
        this.relatedUser = relatedUser;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getNickname() {
        return nickname;
    }

    public String getImgurl() {
        return imgurl;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public String getRealName() {
        return realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getCardBefore() {
        return cardBefore;
    }

    public String getCardAfter() {
        return cardAfter;
    }

    public String getRelatedUser() {
        return relatedUser;
    }

    public String getType() {
        return type;
    }

    public String getSmsCode() {
        return smsCode;
    }

    @Override
    public String toString() {
        return "RegisterCommitParam{" +
                "nickname='" + nickname + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", requestCode='" + requestCode + '\'' +
                ", realName='" + realName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", cardBefore='" + cardBefore + '\'' +
                ", cardAfter='" + cardAfter + '\'' +
                ", relatedUser='" + relatedUser + '\'' +
                ", type='" + type + '\'' +
                ", smsCode='" + smsCode + '\'' +
                '}';
    }
    public Map<String, String> createCommitParams(){
        Map<String, String> params = new HashMap<>();
        params.put("nickname", nickname);
        params.put("imgurl", imgurl);
        params.put("mobile", mobile);
        params.put("password", password);
        params.put("requestCode", requestCode);
        params.put("realName", realName);
        params.put("idCard", idCard);
        params.put("cardBefore", cardBefore);
        params.put("cardAfter", cardAfter);
        params.put("relatedUser", relatedUser);
        params.put("type", type);
        params.put("smsCode", smsCode);
        return params;
    }

}
