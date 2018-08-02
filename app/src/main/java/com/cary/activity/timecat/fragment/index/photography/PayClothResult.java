package com.cary.activity.timecat.fragment.index.photography;

public class PayClothResult {
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

    public class Clothes {
        private int id;

        private int storeId;

        private String title;

        private String imgurl;

        private String imgurls;

        private int sellCount;

        private int starCount;

        private int amount;

        private int markerPrice;

        private int paiAmount;

        private String content;

        private int sex;

        private boolean hot;

        private int catagory;

        private int sellType;

        private String norms;

        private Store store;

        private boolean attention;

        private String loginUid;

        private int postage;

        private String sendtype;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getStoreId() {
            return this.storeId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurl() {
            return this.imgurl;
        }

        public void setImgurls(String imgurls) {
            this.imgurls = imgurls;
        }

        public String getImgurls() {
            return this.imgurls;
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

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getAmount() {
            return this.amount;
        }

        public void setMarkerPrice(int markerPrice) {
            this.markerPrice = markerPrice;
        }

        public int getMarkerPrice() {
            return this.markerPrice;
        }

        public void setPaiAmount(int paiAmount) {
            this.paiAmount = paiAmount;
        }

        public int getPaiAmount() {
            return this.paiAmount;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getSex() {
            return this.sex;
        }

        public void setHot(boolean hot) {
            this.hot = hot;
        }

        public boolean getHot() {
            return this.hot;
        }

        public void setCatagory(int catagory) {
            this.catagory = catagory;
        }

        public int getCatagory() {
            return this.catagory;
        }

        public void setSellType(int sellType) {
            this.sellType = sellType;
        }

        public int getSellType() {
            return this.sellType;
        }

        public void setNorms(String norms) {
            this.norms = norms;
        }

        public String getNorms() {
            return this.norms;
        }

        public void setStore(Store store) {
            this.store = store;
        }

        public Store getStore() {
            return this.store;
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

        public void setPostage(int postage) {
            this.postage = postage;
        }

        public int getPostage() {
            return this.postage;
        }

        public void setSendtype(String sendtype) {
            this.sendtype = sendtype;
        }

        public String getSendtype() {
            return this.sendtype;
        }

    }

    public class Data {
        private int id;

        private int clothId;

        private String ordernum;

        private int price;

        private String normstr;

        private int uid;

        private int status;

        private String province;

        private String city;

        private String area;

        private String detail;

        private String name;

        private String mobile;

        private int orderCount;

        private String createTime;

        private String payTime;

        private int amount;

        private int orderAmount;

        private String expressCode;

        private String expressName;

        private String expressNum;

        private String returnCode;

        private String returnName;

        private String returnNum;

        private int storeId;

        private Clothes clothes;

        private String payInfo;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setClothId(int clothId) {
            this.clothId = clothId;
        }

        public int getClothId() {
            return this.clothId;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        public String getOrdernum() {
            return this.ordernum;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getPrice() {
            return this.price;
        }

        public void setNormstr(String normstr) {
            this.normstr = normstr;
        }

        public String getNormstr() {
            return this.normstr;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return this.status;
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

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMobile() {
            return this.mobile;
        }

        public void setOrderCount(int orderCount) {
            this.orderCount = orderCount;
        }

        public int getOrderCount() {
            return this.orderCount;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getPayTime() {
            return this.payTime;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getAmount() {
            return this.amount;
        }

        public void setOrderAmount(int orderAmount) {
            this.orderAmount = orderAmount;
        }

        public int getOrderAmount() {
            return this.orderAmount;
        }

        public void setExpressCode(String expressCode) {
            this.expressCode = expressCode;
        }

        public String getExpressCode() {
            return this.expressCode;
        }

        public void setExpressName(String expressName) {
            this.expressName = expressName;
        }

        public String getExpressName() {
            return this.expressName;
        }

        public void setExpressNum(String expressNum) {
            this.expressNum = expressNum;
        }

        public String getExpressNum() {
            return this.expressNum;
        }

        public void setReturnCode(String returnCode) {
            this.returnCode = returnCode;
        }

        public String getReturnCode() {
            return this.returnCode;
        }

        public void setReturnName(String returnName) {
            this.returnName = returnName;
        }

        public String getReturnName() {
            return this.returnName;
        }

        public void setReturnNum(String returnNum) {
            this.returnNum = returnNum;
        }

        public String getReturnNum() {
            return this.returnNum;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getStoreId() {
            return this.storeId;
        }

        public void setClothes(Clothes clothes) {
            this.clothes = clothes;
        }

        public Clothes getClothes() {
            return this.clothes;
        }

        public void setPayInfo(String payInfo) {
            this.payInfo = payInfo;
        }

        public String getPayInfo() {
            return this.payInfo;
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
