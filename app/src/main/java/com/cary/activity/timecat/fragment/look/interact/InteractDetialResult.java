package com.cary.activity.timecat.fragment.look.interact;

import java.util.List;

/**
 * Author: create by Cary
 * Date: on 2018/6/14 20:14
 * Comment:
 */
public class InteractDetialResult {

    public class Zans {
        private int id;

        private int interactiveId;

        private int uid;

        private String nickname;

        private String imgurl;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setInteractiveId(int interactiveId) {
            this.interactiveId = interactiveId;
        }

        public int getInteractiveId() {
            return this.interactiveId;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
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

    }

    public class Evas {
        private int id;

        private int interactiveId;

        private int uid;

        private int evaUid;

        private String content;

        private String evaTime;

        private int evaId;

        private String nickname;

        private String imgurl;

        private String evaNickname;

        private String evaImgurl;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setInteractiveId(int interactiveId) {
            this.interactiveId = interactiveId;
        }

        public int getInteractiveId() {
            return this.interactiveId;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
        }

        public void setEvaUid(int evaUid) {
            this.evaUid = evaUid;
        }

        public int getEvaUid() {
            return this.evaUid;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setEvaTime(String evaTime) {
            this.evaTime = evaTime;
        }

        public String getEvaTime() {
            return this.evaTime;
        }

        public void setEvaId(int evaId) {
            this.evaId = evaId;
        }

        public int getEvaId() {
            return this.evaId;
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

        public void setEvaNickname(String evaNickname) {
            this.evaNickname = evaNickname;
        }

        public String getEvaNickname() {
            return this.evaNickname;
        }

        public void setEvaImgurl(String evaImgurl) {
            this.evaImgurl = evaImgurl;
        }

        public String getEvaImgurl() {
            return this.evaImgurl;
        }

    }

    public class Data {
        private int id;

        private int uid;

        private String nickname;

        private String headImgurl;

        private String content;

        private String imgurl;

        private String releaseTime;

        private int zanCount;

        private int evaCount;

        private int externalId;

        private int type;

        private List<Zans> zans;

        private List<Evas> evas;

        private String videoUrl;

        private int barType;

        private boolean zan;

        private String loginUid;

        private String infos;

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

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNickname() {
            return this.nickname;
        }

        public void setHeadImgurl(String headImgurl) {
            this.headImgurl = headImgurl;
        }

        public String getHeadImgurl() {
            return this.headImgurl;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurl() {
            return this.imgurl;
        }

        public void setReleaseTime(String releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getReleaseTime() {
            return this.releaseTime;
        }

        public void setZanCount(int zanCount) {
            this.zanCount = zanCount;
        }

        public int getZanCount() {
            return this.zanCount;
        }

        public void setEvaCount(int evaCount) {
            this.evaCount = evaCount;
        }

        public int getEvaCount() {
            return this.evaCount;
        }

        public void setExternalId(int externalId) {
            this.externalId = externalId;
        }

        public int getExternalId() {
            return this.externalId;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return this.type;
        }

        public void setZans(List<Zans> zans) {
            this.zans = zans;
        }

        public List<Zans> getZans() {
            return this.zans;
        }

        public void setEvas(List<Evas> evas) {
            this.evas = evas;
        }

        public List<Evas> getEvas() {
            return this.evas;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getVideoUrl() {
            return this.videoUrl;
        }

        public void setBarType(int barType) {
            this.barType = barType;
        }

        public int getBarType() {
            return this.barType;
        }

        public void setZan(boolean zan) {
            this.zan = zan;
        }

        public boolean getZan() {
            return this.zan;
        }

        public void setLoginUid(String loginUid) {
            this.loginUid = loginUid;
        }

        public String getLoginUid() {
            return this.loginUid;
        }

        public void setInfos(String infos) {
            this.infos = infos;
        }

        public String getInfos() {
            return this.infos;
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