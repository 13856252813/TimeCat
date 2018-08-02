package com.cary.activity.timecat.fragment.message.group;

import java.util.List;

/**
 * Author: create by Cary
 * Date: on 2018/8/2 10:58
 * Comment:
 */
public class GroupChangeModel {

    /**
     * code : string
     * data : {"createTime":"2018-08-02T02:36:01.928Z","groupId":"string","id":0,"imgurl":"string","name":"string","owner":0,"users":[{"gid":0,"groupId":"string","id":0,"imgurl":"string","nickname":"string","owner":true,"uid":0}]}
     * msg : string
     */

    private String code;
    private DataBean data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * createTime : 2018-08-02T02:36:01.928Z
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
        private List<UsersBean> users;

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

        public List<UsersBean> getUsers() {
            return users;
        }

        public void setUsers(List<UsersBean> users) {
            this.users = users;
        }

        public static class UsersBean {
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
