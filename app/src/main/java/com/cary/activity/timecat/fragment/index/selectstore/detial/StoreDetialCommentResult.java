package com.cary.activity.timecat.fragment.index.selectstore.detial;

import java.util.List;

public class StoreDetialCommentResult {

    /**
     * code : 00
     * msg : Success
     * data : [{"id":3,"packageId":1,"orderId":11,"storeId":1,"uid":1,"content":"评价内容","imgurl":"图片1,图片2","evaTime":"2018-05-25 23:24:39","starCount":5,"webUser":{"id":1,"nickname":"李鹏","imgurl":"default_header.png","mobile":"18655253157","password":"DC483E80A7A0BD9EF71D8CF973673924","realName":"","idCard":"","cardBefore":"","cardAfter":"","totalAmount":2000,"freezeAmount":0,"amount":1950,"relatedUser":49,"type":"0","score":4113,"credit":1,"requestCode":"18655253157","registerTime":"2018-04-05 01:15:10","lastLoginTime":"2018-06-14 21:55:43","pid":1,"photoAmount":0,"extraAmount":0,"teacherType":"摄影师","shopId":1,"level":"","position":"","openid":"","qqid":"","weiboid":"","totalDeposit":4301,"deposit":4300,"deviceToken":"AlELEsQOT8Zg1Ds468blMeDAtHb36ILhkF4IujhxSMRG","deviceType":"android","token":null,"authCode":null,"work":true,"storeName":"上海旗舰店002","hot":false}},{"id":4,"packageId":1,"orderId":109,"storeId":1,"uid":41,"content":"世纪金源的一","imgurl":"sgl/EB86C120-CC2A-446E-8E05-55DE2938AFCC.jpg","evaTime":"2018-07-08 20:45:14","starCount":5,"webUser":{"id":41,"nickname":"家里","imgurl":"default_header.png","mobile":"13211111111","password":"96E79218965EB72C92A549DD5A330112","realName":"","idCard":"","cardBefore":"","cardAfter":"","totalAmount":1000,"freezeAmount":165,"amount":835,"relatedUser":0,"type":"0","score":0,"credit":1,"requestCode":"132111111110","registerTime":"2018-06-04 21:26:03","lastLoginTime":"2018-07-22 16:03:44","pid":0,"photoAmount":0,"extraAmount":0,"teacherType":"化妆师","shopId":1,"level":"","position":"","openid":"","qqid":"","weiboid":"","totalDeposit":10000,"deposit":10000,"deviceToken":"5b0a78eb8f4a9d1f5e000085","deviceType":"ios-苹果","token":null,"authCode":null,"work":false,"storeName":"上海旗舰店002","hot":false}},{"id":5,"packageId":1,"orderId":116,"storeId":1,"uid":41,"content":"合肥话","imgurl":"sgl/6B2F501B-DEE2-4351-9255-A923E6C49356.jpg","evaTime":"2018-07-21 22:55:54","starCount":3,"webUser":{"id":41,"nickname":"家里","imgurl":"default_header.png","mobile":"13211111111","password":"96E79218965EB72C92A549DD5A330112","realName":"","idCard":"","cardBefore":"","cardAfter":"","totalAmount":1000,"freezeAmount":165,"amount":835,"relatedUser":0,"type":"0","score":0,"credit":1,"requestCode":"132111111110","registerTime":"2018-06-04 21:26:03","lastLoginTime":"2018-07-22 16:03:44","pid":0,"photoAmount":0,"extraAmount":0,"teacherType":"化妆师","shopId":1,"level":"","position":"","openid":"","qqid":"","weiboid":"","totalDeposit":10000,"deposit":10000,"deviceToken":"5b0a78eb8f4a9d1f5e000085","deviceType":"ios-苹果","token":null,"authCode":null,"work":false,"storeName":"上海旗舰店002","hot":false}}]
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

    public static class Data {
        /**
         * id : 3
         * packageId : 1
         * orderId : 11
         * storeId : 1
         * uid : 1
         * content : 评价内容
         * imgurl : 图片1,图片2
         * evaTime : 2018-05-25 23:24:39
         * starCount : 5
         * webUser : {"id":1,"nickname":"李鹏","imgurl":"default_header.png","mobile":"18655253157","password":"DC483E80A7A0BD9EF71D8CF973673924","realName":"","idCard":"","cardBefore":"","cardAfter":"","totalAmount":2000,"freezeAmount":0,"amount":1950,"relatedUser":49,"type":"0","score":4113,"credit":1,"requestCode":"18655253157","registerTime":"2018-04-05 01:15:10","lastLoginTime":"2018-06-14 21:55:43","pid":1,"photoAmount":0,"extraAmount":0,"teacherType":"摄影师","shopId":1,"level":"","position":"","openid":"","qqid":"","weiboid":"","totalDeposit":4301,"deposit":4300,"deviceToken":"AlELEsQOT8Zg1Ds468blMeDAtHb36ILhkF4IujhxSMRG","deviceType":"android","token":null,"authCode":null,"work":true,"storeName":"上海旗舰店002","hot":false}
         */

        private int id;
        private int packageId;
        private int orderId;
        private int storeId;
        private int uid;
        private String content;
        private String imgurl;
        private String evaTime;
        private int starCount;
        private WebUserBean webUser;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPackageId() {
            return packageId;
        }

        public void setPackageId(int packageId) {
            this.packageId = packageId;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getEvaTime() {
            return evaTime;
        }

        public void setEvaTime(String evaTime) {
            this.evaTime = evaTime;
        }

        public int getStarCount() {
            return starCount;
        }

        public void setStarCount(int starCount) {
            this.starCount = starCount;
        }

        public WebUserBean getWebUser() {
            return webUser;
        }

        public void setWebUser(WebUserBean webUser) {
            this.webUser = webUser;
        }

        public static class WebUserBean {
            /**
             * id : 1
             * nickname : 李鹏
             * imgurl : default_header.png
             * mobile : 18655253157
             * password : DC483E80A7A0BD9EF71D8CF973673924
             * realName :
             * idCard :
             * cardBefore :
             * cardAfter :
             * totalAmount : 2000
             * freezeAmount : 0
             * amount : 1950
             * relatedUser : 49
             * type : 0
             * score : 4113
             * credit : 1
             * requestCode : 18655253157
             * registerTime : 2018-04-05 01:15:10
             * lastLoginTime : 2018-06-14 21:55:43
             * pid : 1
             * photoAmount : 0
             * extraAmount : 0
             * teacherType : 摄影师
             * shopId : 1
             * level :
             * position :
             * openid :
             * qqid :
             * weiboid :
             * totalDeposit : 4301
             * deposit : 4300
             * deviceToken : AlELEsQOT8Zg1Ds468blMeDAtHb36ILhkF4IujhxSMRG
             * deviceType : android
             * token : null
             * authCode : null
             * work : true
             * storeName : 上海旗舰店002
             * hot : false
             */

            private int id;
            private String nickname;
            private String imgurl;
            private String mobile;
            private String password;
            private String realName;
            private String idCard;
            private String cardBefore;
            private String cardAfter;
            private double totalAmount;
            private double freezeAmount;
            private double amount;
            private int relatedUser;
            private String type;
            private int score;
            private int credit;
            private String requestCode;
            private String registerTime;
            private String lastLoginTime;
            private int pid;
            private double photoAmount;
            private double extraAmount;
            private String teacherType;
            private int shopId;
            private String level;
            private String position;
            private String openid;
            private String qqid;
            private String weiboid;
            private int totalDeposit;
            private int deposit;
            private String deviceToken;
            private String deviceType;
            private Object token;
            private Object authCode;
            private boolean work;
            private String storeName;
            private boolean hot;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getIdCard() {
                return idCard;
            }

            public void setIdCard(String idCard) {
                this.idCard = idCard;
            }

            public String getCardBefore() {
                return cardBefore;
            }

            public void setCardBefore(String cardBefore) {
                this.cardBefore = cardBefore;
            }

            public String getCardAfter() {
                return cardAfter;
            }

            public void setCardAfter(String cardAfter) {
                this.cardAfter = cardAfter;
            }

            public double getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(double totalAmount) {
                this.totalAmount = totalAmount;
            }

            public double getFreezeAmount() {
                return freezeAmount;
            }

            public void setFreezeAmount(double freezeAmount) {
                this.freezeAmount = freezeAmount;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public int getRelatedUser() {
                return relatedUser;
            }

            public void setRelatedUser(int relatedUser) {
                this.relatedUser = relatedUser;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getCredit() {
                return credit;
            }

            public void setCredit(int credit) {
                this.credit = credit;
            }

            public String getRequestCode() {
                return requestCode;
            }

            public void setRequestCode(String requestCode) {
                this.requestCode = requestCode;
            }

            public String getRegisterTime() {
                return registerTime;
            }

            public void setRegisterTime(String registerTime) {
                this.registerTime = registerTime;
            }

            public String getLastLoginTime() {
                return lastLoginTime;
            }

            public void setLastLoginTime(String lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public double getPhotoAmount() {
                return photoAmount;
            }

            public void setPhotoAmount(double photoAmount) {
                this.photoAmount = photoAmount;
            }

            public double getExtraAmount() {
                return extraAmount;
            }

            public void setExtraAmount(double extraAmount) {
                this.extraAmount = extraAmount;
            }

            public String getTeacherType() {
                return teacherType;
            }

            public void setTeacherType(String teacherType) {
                this.teacherType = teacherType;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getQqid() {
                return qqid;
            }

            public void setQqid(String qqid) {
                this.qqid = qqid;
            }

            public String getWeiboid() {
                return weiboid;
            }

            public void setWeiboid(String weiboid) {
                this.weiboid = weiboid;
            }

            public int getTotalDeposit() {
                return totalDeposit;
            }

            public void setTotalDeposit(int totalDeposit) {
                this.totalDeposit = totalDeposit;
            }

            public int getDeposit() {
                return deposit;
            }

            public void setDeposit(int deposit) {
                this.deposit = deposit;
            }

            public String getDeviceToken() {
                return deviceToken;
            }

            public void setDeviceToken(String deviceToken) {
                this.deviceToken = deviceToken;
            }

            public String getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(String deviceType) {
                this.deviceType = deviceType;
            }

            public Object getToken() {
                return token;
            }

            public void setToken(Object token) {
                this.token = token;
            }

            public Object getAuthCode() {
                return authCode;
            }

            public void setAuthCode(Object authCode) {
                this.authCode = authCode;
            }

            public boolean isWork() {
                return work;
            }

            public void setWork(boolean work) {
                this.work = work;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public boolean isHot() {
                return hot;
            }

            public void setHot(boolean hot) {
                this.hot = hot;
            }
        }
    }
}
