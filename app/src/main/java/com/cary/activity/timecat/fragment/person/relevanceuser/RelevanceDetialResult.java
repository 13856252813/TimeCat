package com.cary.activity.timecat.fragment.person.relevanceuser;

/**
 * Author: create by Cary
 * Date: on 2018/6/16 14:57
 * Comment:
 */
public class RelevanceDetialResult {
    public class Data {
        private String createTime;

        private int id;

        private String imgurl;

        private String nickname;

        private int relatedUid;

        private int uid;

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTime() {
            return this.createTime;
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

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNickname() {
            return this.nickname;
        }

        public void setRelatedUid(int relatedUid) {
            this.relatedUid = relatedUid;
        }

        public int getRelatedUid() {
            return this.relatedUid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
        }

    }

    private String code;

    private Data data;

    private String msg;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return this.data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

}
