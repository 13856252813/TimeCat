package com.cary.activity.timecat.fragment.index.star;

import java.util.List;

/**
 * Author: create by Cary
 * Date: on 2018/7/16 00:01
 * Comment:
 */
public class StarListResult {

    /**
     * code : 00
     * msg : Success
     * data : {"stores":[{"id":1,"uid":17,"storeName":"上海旗舰店002","imgurl":"sgl/DC7A6926-3682-4021-9B46-51158D807499.jpg","province":"上海市","city":"上海市  上海市  黄浦区","detail":"浦东新区世纪大道","content":"<p>店铺简介<\/p>","createTime":"2018-04-14 10:59:24","lng":0,"lat":0,"starCount":5,"serviceCount":2,"tel":"","attention":false,"loginUid":null,"hot":true}],"storeManagers":[{"id":17,"nickname":"武者天","imgurl":"default_header.png","mobile":"18655250004","password":"E10ADC3949BA59ABBE56E057F20F883E","realName":"","idCard":"","cardBefore":"","cardAfter":"","totalAmount":0,"freezeAmount":0,"amount":0,"relatedUser":0,"type":"2","score":0,"credit":1,"requestCode":"18655250003","registerTime":"2018-05-12 13:10:59","lastLoginTime":"2018-07-13 15:11:04","pid":0,"photoAmount":0,"extraAmount":0,"teacherType":"","shopId":1,"level":"","position":"DZ","openid":"","qqid":"","weiboid":"","totalDeposit":0,"deposit":0,"deviceToken":"","deviceType":"android","token":null,"authCode":"mdxxgl,tcgl,cpgl,fzgl,lsgl,lsrzsq,yggl,tjyg,khgl","work":true,"storeName":"上海旗舰店002","hot":true}],"teachers":[{"id":3,"uid":2,"payAmount":10,"pay":false,"storeId":1,"store":{"id":1,"uid":17,"storeName":"上海旗舰店002","imgurl":"sgl/DC7A6926-3682-4021-9B46-51158D807499.jpg","province":"上海市","city":"上海市  上海市  黄浦区","detail":"浦东新区世纪大道","content":"<p>店铺简介<\/p>","createTime":"2018-04-14 10:59:24","lng":0,"lat":0,"starCount":5,"serviceCount":2,"tel":"","attention":true,"loginUid":null,"hot":true},"teacherType":"teacherType","applyType":"applyType","provice":"provice","city":"city","area":"area","detail":"detail","emergencyContact":"emergencyContact","emergencyMobile":"emergencyMobile","device":"device","selfIntroduction":"selfIntroduction","status":0,"works":[{"id":1,"uid":2,"address":"address","imgurls":"imgurls"},{"id":2,"uid":2,"address":"address","imgurls":"imgurls"}],"webUser":{"id":2,"nickname":"老师测试","imgurl":"default_header.png","mobile":"18655253156","password":"123456","realName":"","idCard":"","cardBefore":"","cardAfter":"","totalAmount":0,"freezeAmount":0,"amount":0,"relatedUser":0,"type":"1","score":0,"credit":1,"requestCode":"","registerTime":"2018-07-08 21:39:05","lastLoginTime":null,"pid":0,"photoAmount":0,"extraAmount":0,"teacherType":"摄影师","shopId":1,"level":"","position":"","openid":"","qqid":"","weiboid":"","totalDeposit":0,"deposit":0,"deviceToken":"","deviceType":"","token":null,"authCode":null,"work":false,"storeName":"上海旗舰店002","hot":true},"attention":null,"loginUid":null}]}
     */

    private String code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<StoresBean> stores;
        private List<StoreManagersBean> storeManagers;
        private List<TeachersBean> teachers;

        public List<StoresBean> getStores() {
            return stores;
        }

        public void setStores(List<StoresBean> stores) {
            this.stores = stores;
        }

        public List<StoreManagersBean> getStoreManagers() {
            return storeManagers;
        }

        public void setStoreManagers(List<StoreManagersBean> storeManagers) {
            this.storeManagers = storeManagers;
        }

        public List<TeachersBean> getTeachers() {
            return teachers;
        }

        public void setTeachers(List<TeachersBean> teachers) {
            this.teachers = teachers;
        }

        public static class StoresBean {
            /**
             * id : 1
             * uid : 17
             * storeName : 上海旗舰店002
             * imgurl : sgl/DC7A6926-3682-4021-9B46-51158D807499.jpg
             * province : 上海市
             * city : 上海市  上海市  黄浦区
             * detail : 浦东新区世纪大道
             * content : <p>店铺简介</p>
             * createTime : 2018-04-14 10:59:24
             * lng : 0
             * lat : 0
             * starCount : 5
             * serviceCount : 2
             * tel :
             * attention : false
             * loginUid : null
             * hot : true
             */

            private int id;
            private int uid;
            private String storeName;
            private String imgurl;
            private String province;
            private String city;
            private String detail;
            private String content;
            private String createTime;
            private int lng;
            private int lat;
            private int starCount;
            private int serviceCount;
            private String tel;
            private boolean attention;
            private Object loginUid;
            private boolean hot;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getLng() {
                return lng;
            }

            public void setLng(int lng) {
                this.lng = lng;
            }

            public int getLat() {
                return lat;
            }

            public void setLat(int lat) {
                this.lat = lat;
            }

            public int getStarCount() {
                return starCount;
            }

            public void setStarCount(int starCount) {
                this.starCount = starCount;
            }

            public int getServiceCount() {
                return serviceCount;
            }

            public void setServiceCount(int serviceCount) {
                this.serviceCount = serviceCount;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public boolean isAttention() {
                return attention;
            }

            public void setAttention(boolean attention) {
                this.attention = attention;
            }

            public Object getLoginUid() {
                return loginUid;
            }

            public void setLoginUid(Object loginUid) {
                this.loginUid = loginUid;
            }

            public boolean isHot() {
                return hot;
            }

            public void setHot(boolean hot) {
                this.hot = hot;
            }
        }

        public static class StoreManagersBean {
            /**
             * id : 17
             * nickname : 武者天
             * imgurl : default_header.png
             * mobile : 18655250004
             * password : E10ADC3949BA59ABBE56E057F20F883E
             * realName :
             * idCard :
             * cardBefore :
             * cardAfter :
             * totalAmount : 0
             * freezeAmount : 0
             * amount : 0
             * relatedUser : 0
             * type : 2
             * score : 0
             * credit : 1
             * requestCode : 18655250003
             * registerTime : 2018-05-12 13:10:59
             * lastLoginTime : 2018-07-13 15:11:04
             * pid : 0
             * photoAmount : 0
             * extraAmount : 0
             * teacherType :
             * shopId : 1
             * level :
             * position : DZ
             * openid :
             * qqid :
             * weiboid :
             * totalDeposit : 0
             * deposit : 0
             * deviceToken :
             * deviceType : android
             * token : null
             * authCode : mdxxgl,tcgl,cpgl,fzgl,lsgl,lsrzsq,yggl,tjyg,khgl
             * work : true
             * storeName : 上海旗舰店002
             * hot : true
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
            private int totalAmount;
            private int freezeAmount;
            private int amount;
            private int relatedUser;
            private String type;
            private int score;
            private int credit;
            private String requestCode;
            private String registerTime;
            private String lastLoginTime;
            private int pid;
            private int photoAmount;
            private int extraAmount;
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
            private String authCode;
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

            public int getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(int totalAmount) {
                this.totalAmount = totalAmount;
            }

            public int getFreezeAmount() {
                return freezeAmount;
            }

            public void setFreezeAmount(int freezeAmount) {
                this.freezeAmount = freezeAmount;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
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

            public int getPhotoAmount() {
                return photoAmount;
            }

            public void setPhotoAmount(int photoAmount) {
                this.photoAmount = photoAmount;
            }

            public int getExtraAmount() {
                return extraAmount;
            }

            public void setExtraAmount(int extraAmount) {
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

            public String getAuthCode() {
                return authCode;
            }

            public void setAuthCode(String authCode) {
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

        public static class TeachersBean {
            /**
             * id : 3
             * uid : 2
             * payAmount : 10
             * pay : false
             * storeId : 1
             * store : {"id":1,"uid":17,"storeName":"上海旗舰店002","imgurl":"sgl/DC7A6926-3682-4021-9B46-51158D807499.jpg","province":"上海市","city":"上海市  上海市  黄浦区","detail":"浦东新区世纪大道","content":"<p>店铺简介<\/p>","createTime":"2018-04-14 10:59:24","lng":0,"lat":0,"starCount":5,"serviceCount":2,"tel":"","attention":true,"loginUid":null,"hot":true}
             * teacherType : teacherType
             * applyType : applyType
             * provice : provice
             * city : city
             * area : area
             * detail : detail
             * emergencyContact : emergencyContact
             * emergencyMobile : emergencyMobile
             * device : device
             * selfIntroduction : selfIntroduction
             * status : 0
             * works : [{"id":1,"uid":2,"address":"address","imgurls":"imgurls"},{"id":2,"uid":2,"address":"address","imgurls":"imgurls"}]
             * webUser : {"id":2,"nickname":"老师测试","imgurl":"default_header.png","mobile":"18655253156","password":"123456","realName":"","idCard":"","cardBefore":"","cardAfter":"","totalAmount":0,"freezeAmount":0,"amount":0,"relatedUser":0,"type":"1","score":0,"credit":1,"requestCode":"","registerTime":"2018-07-08 21:39:05","lastLoginTime":null,"pid":0,"photoAmount":0,"extraAmount":0,"teacherType":"摄影师","shopId":1,"level":"","position":"","openid":"","qqid":"","weiboid":"","totalDeposit":0,"deposit":0,"deviceToken":"","deviceType":"","token":null,"authCode":null,"work":false,"storeName":"上海旗舰店002","hot":true}
             * attention : null
             * loginUid : null
             */

            private int id;
            private int uid;
            private int payAmount;
            private boolean pay;
            private int storeId;
            private StoreBean store;
            private String teacherType;
            private String applyType;
            private String provice;
            private String city;
            private String area;
            private String detail;
            private String emergencyContact;
            private String emergencyMobile;
            private String device;
            private String selfIntroduction;
            private int status;
            private WebUserBean webUser;
            private Object attention;
            private Object loginUid;
            private List<WorksBean> works;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public int getPayAmount() {
                return payAmount;
            }

            public void setPayAmount(int payAmount) {
                this.payAmount = payAmount;
            }

            public boolean isPay() {
                return pay;
            }

            public void setPay(boolean pay) {
                this.pay = pay;
            }

            public int getStoreId() {
                return storeId;
            }

            public void setStoreId(int storeId) {
                this.storeId = storeId;
            }

            public StoreBean getStore() {
                return store;
            }

            public void setStore(StoreBean store) {
                this.store = store;
            }

            public String getTeacherType() {
                return teacherType;
            }

            public void setTeacherType(String teacherType) {
                this.teacherType = teacherType;
            }

            public String getApplyType() {
                return applyType;
            }

            public void setApplyType(String applyType) {
                this.applyType = applyType;
            }

            public String getProvice() {
                return provice;
            }

            public void setProvice(String provice) {
                this.provice = provice;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getEmergencyContact() {
                return emergencyContact;
            }

            public void setEmergencyContact(String emergencyContact) {
                this.emergencyContact = emergencyContact;
            }

            public String getEmergencyMobile() {
                return emergencyMobile;
            }

            public void setEmergencyMobile(String emergencyMobile) {
                this.emergencyMobile = emergencyMobile;
            }

            public String getDevice() {
                return device;
            }

            public void setDevice(String device) {
                this.device = device;
            }

            public String getSelfIntroduction() {
                return selfIntroduction;
            }

            public void setSelfIntroduction(String selfIntroduction) {
                this.selfIntroduction = selfIntroduction;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public WebUserBean getWebUser() {
                return webUser;
            }

            public void setWebUser(WebUserBean webUser) {
                this.webUser = webUser;
            }

            public Object getAttention() {
                return attention;
            }

            public void setAttention(Object attention) {
                this.attention = attention;
            }

            public Object getLoginUid() {
                return loginUid;
            }

            public void setLoginUid(Object loginUid) {
                this.loginUid = loginUid;
            }

            public List<WorksBean> getWorks() {
                return works;
            }

            public void setWorks(List<WorksBean> works) {
                this.works = works;
            }

            public static class StoreBean {
                /**
                 * id : 1
                 * uid : 17
                 * storeName : 上海旗舰店002
                 * imgurl : sgl/DC7A6926-3682-4021-9B46-51158D807499.jpg
                 * province : 上海市
                 * city : 上海市  上海市  黄浦区
                 * detail : 浦东新区世纪大道
                 * content : <p>店铺简介</p>
                 * createTime : 2018-04-14 10:59:24
                 * lng : 0
                 * lat : 0
                 * starCount : 5
                 * serviceCount : 2
                 * tel :
                 * attention : true
                 * loginUid : null
                 * hot : true
                 */

                private int id;
                private int uid;
                private String storeName;
                private String imgurl;
                private String province;
                private String city;
                private String detail;
                private String content;
                private String createTime;
                private int lng;
                private int lat;
                private int starCount;
                private int serviceCount;
                private String tel;
                private boolean attention;
                private Object loginUid;
                private boolean hot;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getUid() {
                    return uid;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }

                public String getStoreName() {
                    return storeName;
                }

                public void setStoreName(String storeName) {
                    this.storeName = storeName;
                }

                public String getImgurl() {
                    return imgurl;
                }

                public void setImgurl(String imgurl) {
                    this.imgurl = imgurl;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getDetail() {
                    return detail;
                }

                public void setDetail(String detail) {
                    this.detail = detail;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public int getLng() {
                    return lng;
                }

                public void setLng(int lng) {
                    this.lng = lng;
                }

                public int getLat() {
                    return lat;
                }

                public void setLat(int lat) {
                    this.lat = lat;
                }

                public int getStarCount() {
                    return starCount;
                }

                public void setStarCount(int starCount) {
                    this.starCount = starCount;
                }

                public int getServiceCount() {
                    return serviceCount;
                }

                public void setServiceCount(int serviceCount) {
                    this.serviceCount = serviceCount;
                }

                public String getTel() {
                    return tel;
                }

                public void setTel(String tel) {
                    this.tel = tel;
                }

                public boolean isAttention() {
                    return attention;
                }

                public void setAttention(boolean attention) {
                    this.attention = attention;
                }

                public Object getLoginUid() {
                    return loginUid;
                }

                public void setLoginUid(Object loginUid) {
                    this.loginUid = loginUid;
                }

                public boolean isHot() {
                    return hot;
                }

                public void setHot(boolean hot) {
                    this.hot = hot;
                }
            }

            public static class WebUserBean {
                /**
                 * id : 2
                 * nickname : 老师测试
                 * imgurl : default_header.png
                 * mobile : 18655253156
                 * password : 123456
                 * realName :
                 * idCard :
                 * cardBefore :
                 * cardAfter :
                 * totalAmount : 0
                 * freezeAmount : 0
                 * amount : 0
                 * relatedUser : 0
                 * type : 1
                 * score : 0
                 * credit : 1
                 * requestCode :
                 * registerTime : 2018-07-08 21:39:05
                 * lastLoginTime : null
                 * pid : 0
                 * photoAmount : 0
                 * extraAmount : 0
                 * teacherType : 摄影师
                 * shopId : 1
                 * level :
                 * position :
                 * openid :
                 * qqid :
                 * weiboid :
                 * totalDeposit : 0
                 * deposit : 0
                 * deviceToken :
                 * deviceType :
                 * token : null
                 * authCode : null
                 * work : false
                 * storeName : 上海旗舰店002
                 * hot : true
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
                private int totalAmount;
                private int freezeAmount;
                private int amount;
                private int relatedUser;
                private String type;
                private int score;
                private int credit;
                private String requestCode;
                private String registerTime;
                private Object lastLoginTime;
                private int pid;
                private int photoAmount;
                private int extraAmount;
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

                public int getTotalAmount() {
                    return totalAmount;
                }

                public void setTotalAmount(int totalAmount) {
                    this.totalAmount = totalAmount;
                }

                public int getFreezeAmount() {
                    return freezeAmount;
                }

                public void setFreezeAmount(int freezeAmount) {
                    this.freezeAmount = freezeAmount;
                }

                public int getAmount() {
                    return amount;
                }

                public void setAmount(int amount) {
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

                public Object getLastLoginTime() {
                    return lastLoginTime;
                }

                public void setLastLoginTime(Object lastLoginTime) {
                    this.lastLoginTime = lastLoginTime;
                }

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }

                public int getPhotoAmount() {
                    return photoAmount;
                }

                public void setPhotoAmount(int photoAmount) {
                    this.photoAmount = photoAmount;
                }

                public int getExtraAmount() {
                    return extraAmount;
                }

                public void setExtraAmount(int extraAmount) {
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

            public static class WorksBean {
                /**
                 * id : 1
                 * uid : 2
                 * address : address
                 * imgurls : imgurls
                 */

                private int id;
                private int uid;
                private String address;
                private String imgurls;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getUid() {
                    return uid;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getImgurls() {
                    return imgurls;
                }

                public void setImgurls(String imgurls) {
                    this.imgurls = imgurls;
                }
            }
        }
    }
}
