package com.cary.activity.timecat.fragment.look.interact;

import java.util.List;

/**
 * Author: create by Cary
 * Date: on 2018/6/13 20:23
 * Comment:
 */
public class InteractListResult {
    public class Query {
        private int loginUid;

        public void setLoginUid(int loginUid) {
            this.loginUid = loginUid;
        }

        public int getLoginUid() {
            return this.loginUid;
        }

    }

    public class Pi {
        private int currentPage;

        private int pageSize;

        private Query query;

        private int totalPage;

        private int totalSize;

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getCurrentPage() {
            return this.currentPage;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageSize() {
            return this.pageSize;
        }

        public void setQuery(Query query) {
            this.query = query;
        }

        public Query getQuery() {
            return this.query;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalPage() {
            return this.totalPage;
        }

        public void setTotalSize(int totalSize) {
            this.totalSize = totalSize;
        }

        public int getTotalSize() {
            return this.totalSize;
        }

    }

    public class Evas {
        private String content;

        private int evaId;

        private String evaImgurl;

        private String evaNickname;

        private String evaTime;

        private int evaUid;

        private int id;

        private String imgurl;

        private int interactiveId;

        private String nickname;

        private int uid;

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setEvaId(int evaId) {
            this.evaId = evaId;
        }

        public int getEvaId() {
            return this.evaId;
        }

        public void setEvaImgurl(String evaImgurl) {
            this.evaImgurl = evaImgurl;
        }

        public String getEvaImgurl() {
            return this.evaImgurl;
        }

        public void setEvaNickname(String evaNickname) {
            this.evaNickname = evaNickname;
        }

        public String getEvaNickname() {
            return this.evaNickname;
        }

        public void setEvaTime(String evaTime) {
            this.evaTime = evaTime;
        }

        public String getEvaTime() {
            return this.evaTime;
        }

        public void setEvaUid(int evaUid) {
            this.evaUid = evaUid;
        }

        public int getEvaUid() {
            return this.evaUid;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurl() {
            return this.imgurl;
        }

        public void setInteractiveId(int interactiveId) {
            this.interactiveId = interactiveId;
        }

        public int getInteractiveId() {
            return this.interactiveId;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNickname() {
            return this.nickname;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
        }

    }

    public class Infos {
        private int id;

        private String imgurl;

        private int marketPrice;

        private int price;

        private int sellCount;

        private int starCount;

        private String title;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurl() {
            return this.imgurl;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public int getMarketPrice() {
            return this.marketPrice;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getPrice() {
            return this.price;
        }

        public void setSellCount(int sellCount) {
            this.sellCount = sellCount;
        }

        public int getSellCount() {
            return this.sellCount;
        }

        public void setStarCount(int starCount) {
            this.starCount = starCount;
        }

        public int getStarCount() {
            return this.starCount;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

    }

    public class Zans {
        private int id;

        private String imgurl;

        private int interactiveId;

        private String nickname;

        private int uid;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurl() {
            return this.imgurl;
        }

        public void setInteractiveId(int interactiveId) {
            this.interactiveId = interactiveId;
        }

        public int getInteractiveId() {
            return this.interactiveId;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNickname() {
            return this.nickname;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
        }

    }

    public class Data {
        private int barType;

        private String content;

        private int evaCount;

        private List<Evas> evas;

        private int externalId;

        private String headImgurl;

        private int id;

        private String imgurl;

        private List<Infos> infos;

        private int loginUid;

        private String nickname;

        private String releaseTime;

        private int type;

        private int uid;

        private String videoUrl;

        private boolean zan;

        private int zanCount;

        private List<Zans> zans;

        public void setBarType(int barType) {
            this.barType = barType;
        }

        public int getBarType() {
            return this.barType;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setEvaCount(int evaCount) {
            this.evaCount = evaCount;
        }

        public int getEvaCount() {
            return this.evaCount;
        }

        public void setEvas(List<Evas> evas) {
            this.evas = evas;
        }

        public List<Evas> getEvas() {
            return this.evas;
        }

        public void setExternalId(int externalId) {
            this.externalId = externalId;
        }

        public int getExternalId() {
            return this.externalId;
        }

        public void setHeadImgurl(String headImgurl) {
            this.headImgurl = headImgurl;
        }

        public String getHeadImgurl() {
            return this.headImgurl;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurl() {
            return this.imgurl;
        }

        public void setInfos(List<Infos> infos) {
            this.infos = infos;
        }

        public List<Infos> getInfos() {
            return this.infos;
        }

        public void setLoginUid(int loginUid) {
            this.loginUid = loginUid;
        }

        public int getLoginUid() {
            return this.loginUid;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNickname() {
            return this.nickname;
        }

        public void setReleaseTime(String releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getReleaseTime() {
            return this.releaseTime;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return this.type;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getVideoUrl() {
            return this.videoUrl;
        }

        public void setZan(boolean zan) {
            this.zan = zan;
        }

        public boolean getZan() {
            return this.zan;
        }

        public void setZanCount(int zanCount) {
            this.zanCount = zanCount;
        }

        public int getZanCount() {
            return this.zanCount;
        }

        public void setZans(List<Zans> zans) {
            this.zans = zans;
        }

        public List<Zans> getZans() {
            return this.zans;
        }

    }

    private String code;

    private List<Data> data;

    private String msg;

    private Pi pi;

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

    public void setPi(Pi pi) {
        this.pi = pi;
    }

    public Pi getPi() {
        return this.pi;
    }

}