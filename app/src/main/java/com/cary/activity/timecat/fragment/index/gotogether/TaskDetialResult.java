package com.cary.activity.timecat.fragment.index.gotogether;

import java.util.List;

public class TaskDetialResult {
    public class Grabs {
        private int credit;

        private String grabTime;

        private int id;

        private String imgurl;

        private String nickname;

        private boolean selected;

        private int taskId;

        private int teacherId;

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public int getCredit() {
            return this.credit;
        }

        public void setGrabTime(String grabTime) {
            this.grabTime = grabTime;
        }

        public String getGrabTime() {
            return this.grabTime;
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

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public boolean getSelected() {
            return this.selected;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public int getTaskId() {
            return this.taskId;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
        }

        public int getTeacherId() {
            return this.teacherId;
        }

    }

    public class Data {
        private int amount;

        private String content;

        private String createTime;

        private int grabId;

        private List<Grabs> grabs;

        private boolean hasReply;

        private int id;

        private String ordernum;

        private boolean over;

        private String serviceCity;

        private String serviceTime;

        private boolean success;

        private int teacherId;

        private String type;

        private int uid;

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getAmount() {
            return this.amount;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public void setGrabId(int grabId) {
            this.grabId = grabId;
        }

        public int getGrabId() {
            return this.grabId;
        }

        public void setGrabs(List<Grabs> grabs) {
            this.grabs = grabs;
        }

        public List<Grabs> getGrabs() {
            return this.grabs;
        }

        public void setHasReply(boolean hasReply) {
            this.hasReply = hasReply;
        }

        public boolean getHasReply() {
            return this.hasReply;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        public String getOrdernum() {
            return this.ordernum;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public boolean getOver() {
            return this.over;
        }

        public void setServiceCity(String serviceCity) {
            this.serviceCity = serviceCity;
        }

        public String getServiceCity() {
            return this.serviceCity;
        }

        public void setServiceTime(String serviceTime) {
            this.serviceTime = serviceTime;
        }

        public String getServiceTime() {
            return this.serviceTime;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public boolean getSuccess() {
            return this.success;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
        }

        public int getTeacherId() {
            return this.teacherId;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
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
