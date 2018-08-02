package com.cary.activity.timecat.manager.teacher.fragment.attention.teacher;

import java.util.List;

/**
 * Author: create by Cary
 * Date: on 2018/6/16 13:03
 * Comment:
 */
public class TeacherAttentionResult {
    public class Query {

    }

    public class Pi {
        private int pageSize;

        private int totalPage;

        private int currentPage;

        private int totalSize;

        private Query query;

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageSize() {
            return this.pageSize;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalPage() {
            return this.totalPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getCurrentPage() {
            return this.currentPage;
        }

        public void setTotalSize(int totalSize) {
            this.totalSize = totalSize;
        }

        public int getTotalSize() {
            return this.totalSize;
        }

        public void setQuery(Query query) {
            this.query = query;
        }

        public Query getQuery() {
            return this.query;
        }

    }

    public class Store {
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

        private String loginUid;

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

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getStoreName() {
            return this.storeName;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurl() {
            return this.imgurl;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getProvince() {
            return this.province;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity() {
            return this.city;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getDetail() {
            return this.detail;
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

        public void setLng(int lng) {
            this.lng = lng;
        }

        public int getLng() {
            return this.lng;
        }

        public void setLat(int lat) {
            this.lat = lat;
        }

        public int getLat() {
            return this.lat;
        }

        public void setStarCount(int starCount) {
            this.starCount = starCount;
        }

        public int getStarCount() {
            return this.starCount;
        }

        public void setServiceCount(int serviceCount) {
            this.serviceCount = serviceCount;
        }

        public int getServiceCount() {
            return this.serviceCount;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getTel() {
            return this.tel;
        }

        public void setAttention(boolean attention) {
            this.attention = attention;
        }

        public boolean getAttention() {
            return this.attention;
        }

        public void setLoginUid(String loginUid) {
            this.loginUid = loginUid;
        }

        public String getLoginUid() {
            return this.loginUid;
        }

    }

    public class WebUser {
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

        private String token;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
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

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMobile() {
            return this.mobile;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return this.password;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getRealName() {
            return this.realName;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getIdCard() {
            return this.idCard;
        }

        public void setCardBefore(String cardBefore) {
            this.cardBefore = cardBefore;
        }

        public String getCardBefore() {
            return this.cardBefore;
        }

        public void setCardAfter(String cardAfter) {
            this.cardAfter = cardAfter;
        }

        public String getCardAfter() {
            return this.cardAfter;
        }

        public void setTotalAmount(int totalAmount) {
            this.totalAmount = totalAmount;
        }

        public int getTotalAmount() {
            return this.totalAmount;
        }

        public void setFreezeAmount(int freezeAmount) {
            this.freezeAmount = freezeAmount;
        }

        public int getFreezeAmount() {
            return this.freezeAmount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getAmount() {
            return this.amount;
        }

        public void setRelatedUser(int relatedUser) {
            this.relatedUser = relatedUser;
        }

        public int getRelatedUser() {
            return this.relatedUser;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getScore() {
            return this.score;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public int getCredit() {
            return this.credit;
        }

        public void setRequestCode(String requestCode) {
            this.requestCode = requestCode;
        }

        public String getRequestCode() {
            return this.requestCode;
        }

        public void setRegisterTime(String registerTime) {
            this.registerTime = registerTime;
        }

        public String getRegisterTime() {
            return this.registerTime;
        }

        public void setLastLoginTime(String lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getLastLoginTime() {
            return this.lastLoginTime;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getPid() {
            return this.pid;
        }

        public void setPhotoAmount(int photoAmount) {
            this.photoAmount = photoAmount;
        }

        public int getPhotoAmount() {
            return this.photoAmount;
        }

        public void setExtraAmount(double extraAmount) {
            this.extraAmount = extraAmount;
        }

        public double getExtraAmount() {
            return this.extraAmount;
        }

        public void setTeacherType(String teacherType) {
            this.teacherType = teacherType;
        }

        public String getTeacherType() {
            return this.teacherType;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public int getShopId() {
            return this.shopId;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLevel() {
            return this.level;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getPosition() {
            return this.position;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getOpenid() {
            return this.openid;
        }

        public void setQqid(String qqid) {
            this.qqid = qqid;
        }

        public String getQqid() {
            return this.qqid;
        }

        public void setWeiboid(String weiboid) {
            this.weiboid = weiboid;
        }

        public String getWeiboid() {
            return this.weiboid;
        }

        public void setTotalDeposit(int totalDeposit) {
            this.totalDeposit = totalDeposit;
        }

        public int getTotalDeposit() {
            return this.totalDeposit;
        }

        public void setDeposit(int deposit) {
            this.deposit = deposit;
        }

        public int getDeposit() {
            return this.deposit;
        }

        public void setDeviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
        }

        public String getDeviceToken() {
            return this.deviceToken;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getDeviceType() {
            return this.deviceType;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getToken() {
            return this.token;
        }

    }

    public class Works {
        private int id;

        private int uid;

        private String address;

        private String imgurls;

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

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddress() {
            return this.address;
        }

        public void setImgurls(String imgurls) {
            this.imgurls = imgurls;
        }

        public String getImgurls() {
            return this.imgurls;
        }

    }

    public class TeacherInfo {
        private int id;

        private int uid;

        private int payAmount;

        private boolean pay;

        private int storeId;

        private Store store;

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

        private List<Works> works;

        private WebUser webUser;

        private boolean attention;

        private String loginUid;

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

        public void setPayAmount(int payAmount) {
            this.payAmount = payAmount;
        }

        public int getPayAmount() {
            return this.payAmount;
        }

        public void setPay(boolean pay) {
            this.pay = pay;
        }

        public boolean getPay() {
            return this.pay;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getStoreId() {
            return this.storeId;
        }

        public void setStore(Store store) {
            this.store = store;
        }

        public Store getStore() {
            return this.store;
        }

        public void setTeacherType(String teacherType) {
            this.teacherType = teacherType;
        }

        public String getTeacherType() {
            return this.teacherType;
        }

        public void setApplyType(String applyType) {
            this.applyType = applyType;
        }

        public String getApplyType() {
            return this.applyType;
        }

        public void setProvice(String provice) {
            this.provice = provice;
        }

        public String getProvice() {
            return this.provice;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity() {
            return this.city;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getArea() {
            return this.area;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getDetail() {
            return this.detail;
        }

        public void setEmergencyContact(String emergencyContact) {
            this.emergencyContact = emergencyContact;
        }

        public String getEmergencyContact() {
            return this.emergencyContact;
        }

        public void setEmergencyMobile(String emergencyMobile) {
            this.emergencyMobile = emergencyMobile;
        }

        public String getEmergencyMobile() {
            return this.emergencyMobile;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public String getDevice() {
            return this.device;
        }

        public void setSelfIntroduction(String selfIntroduction) {
            this.selfIntroduction = selfIntroduction;
        }

        public String getSelfIntroduction() {
            return this.selfIntroduction;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return this.status;
        }

        public void setWorks(List<Works> works) {
            this.works = works;
        }

        public List<Works> getWorks() {
            return this.works;
        }

        public void setWebUser(WebUser webUser) {
            this.webUser = webUser;
        }

        public WebUser getWebUser() {
            return this.webUser;
        }

        public void setAttention(boolean attention) {
            this.attention = attention;
        }

        public boolean getAttention() {
            return this.attention;
        }

        public void setLoginUid(String loginUid) {
            this.loginUid = loginUid;
        }

        public String getLoginUid() {
            return this.loginUid;
        }

    }

    public class Data {
        private int id;

        private int teacherId;

        private int uid;

        private String attentionTime;

        private TeacherInfo teacherInfo;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
        }

        public int getTeacherId() {
            return this.teacherId;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
        }

        public void setAttentionTime(String attentionTime) {
            this.attentionTime = attentionTime;
        }

        public String getAttentionTime() {
            return this.attentionTime;
        }

        public void setTeacherInfo(TeacherInfo teacherInfo) {
            this.teacherInfo = teacherInfo;
        }

        public TeacherInfo getTeacherInfo() {
            return this.teacherInfo;
        }

    }

    private String code;

    private String msg;

    private List<Data> data;

    private Pi pi;

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

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return this.data;
    }

    public void setPi(Pi pi) {
        this.pi = pi;
    }

    public Pi getPi() {
        return this.pi;
    }

}