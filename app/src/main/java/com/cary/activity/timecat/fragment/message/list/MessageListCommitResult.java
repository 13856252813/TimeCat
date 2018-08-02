package com.cary.activity.timecat.fragment.message.list;

import java.io.Serializable;
import java.util.List;

public class MessageListCommitResult implements Serializable{

    /**
     * code : string
     * data : [{"createTime":"2018-07-28T05:25:42.739Z","groupId":"string","id":0,"imgurl":"string","name":"string","owner":0,"users":[{"gid":0,"groupId":"string","id":0,"imgurl":"string","nickname":"string","owner":true,"uid":0}]}]
     * msg : string
     */

    private String code;
    private String msg;
    private List<Data> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data implements Serializable{
        /**
         * createTime : 2018-07-28T05:25:42.739Z
         * groupId : string
         * id : 0
         * imgurl : string
         * name : string
         * owner : 0
         * users : [{"gid":0,"groupId":"string","id":0,"imgurl":"string","nickname":"string","owner":true,"uid":0}]
         */

        private String createTime;
        private String groupId;
        private int id;
        private String imgurl;
        private String name;
        private int owner;
        private List<Users> users;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOwner() {
            return owner;
        }

        public void setOwner(int owner) {
            this.owner = owner;
        }

        public List<Users> getUsers() {
            return users;
        }

        public void setUsers(List<Users> users) {
            this.users = users;
        }

        public static class Users implements Serializable{
            /**
             * gid : 0
             * groupId : string
             * id : 0
             * imgurl : string
             * nickname : string
             * owner : true
             * uid : 0
             */

            private int gid;
            private String groupId;
            private int id;
            private String imgurl;
            private String nickname;
            private boolean owner;
            private int uid;

            public int getGid() {
                return gid;
            }

            public void setGid(int gid) {
                this.gid = gid;
            }

            public String getGroupId() {
                return groupId;
            }

            public void setGroupId(String groupId) {
                this.groupId = groupId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public boolean isOwner() {
                return owner;
            }

            public void setOwner(boolean owner) {
                this.owner = owner;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }
        }
    }
}
