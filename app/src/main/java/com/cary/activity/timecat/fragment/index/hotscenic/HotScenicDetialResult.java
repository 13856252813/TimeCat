package com.cary.activity.timecat.fragment.index.hotscenic;

/**
 * Author: create by Cary
 * Date: on 2018/7/15 23:01
 * Comment:
 */
public class HotScenicDetialResult {

    /**
     * code : 00
     * msg : Success
     * data : {"id":1,"title":"土耳其","imgurl":"c5e0068ffeee4c7da40d1b79e8e4b2e3.jpg","imgurls":"1359c4595a744aa0862d4072c0e4da81.jpg,4941c5a1b41443228fc0272324f94069.jpg","sellCount":23,"starCount":3,"amount":2800,"markerPrice":2288,"content":"<p>这是详情<\/p><p><img src=\"http://timecats-yunpan.oss-cn-hangzhou.aliyuncs.com/ac328dda6e194b568af1ad4bc44463b8.jpg\" _src=\"http://timecats-yunpan.oss-cn-hangzhou.aliyuncs.com/ac328dda6e194b568af1ad4bc44463b8.jpg\"/><\/p>","storeId":1,"address":"家里蹲78号左拐直行右走500米","hot":true,"store":{"id":1,"uid":17,"storeName":"上海旗舰店002","imgurl":"sgl/DC7A6926-3682-4021-9B46-51158D807499.jpg","province":"上海市","city":"上海市  上海市  黄浦区","detail":"浦东新区世纪大道","content":"<p>店铺简介<\/p>","createTime":"2018-04-14 10:59:24","lng":0,"lat":0,"starCount":5,"serviceCount":2,"tel":"","attention":null,"loginUid":null,"hot":true},"attention":false,"loginUid":null}
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
        /**
         * id : 1
         * title : 土耳其
         * imgurl : c5e0068ffeee4c7da40d1b79e8e4b2e3.jpg
         * imgurls : 1359c4595a744aa0862d4072c0e4da81.jpg,4941c5a1b41443228fc0272324f94069.jpg
         * sellCount : 23
         * starCount : 3
         * amount : 2800
         * markerPrice : 2288
         * content : <p>这是详情</p><p><img src="http://timecats-yunpan.oss-cn-hangzhou.aliyuncs.com/ac328dda6e194b568af1ad4bc44463b8.jpg" _src="http://timecats-yunpan.oss-cn-hangzhou.aliyuncs.com/ac328dda6e194b568af1ad4bc44463b8.jpg"/></p>
         * storeId : 1
         * address : 家里蹲78号左拐直行右走500米
         * hot : true
         * store : {"id":1,"uid":17,"storeName":"上海旗舰店002","imgurl":"sgl/DC7A6926-3682-4021-9B46-51158D807499.jpg","province":"上海市","city":"上海市  上海市  黄浦区","detail":"浦东新区世纪大道","content":"<p>店铺简介<\/p>","createTime":"2018-04-14 10:59:24","lng":0,"lat":0,"starCount":5,"serviceCount":2,"tel":"","attention":null,"loginUid":null,"hot":true}
         * attention : false
         * loginUid : null
         */

        private int id;
        private String title;
        private String imgurl;
        private String imgurls;
        private int sellCount;
        private int starCount;
        private double amount;
        private double markerPrice;
        private String content;
        private int storeId;
        private String address;
        private boolean hot;
        private StoreBean store;
        private boolean attention;
        private Object loginUid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurls() {
            return imgurls;
        }

        public void setImgurls(String imgurls) {
            this.imgurls = imgurls;
        }

        public int getSellCount() {
            return sellCount;
        }

        public void setSellCount(int sellCount) {
            this.sellCount = sellCount;
        }

        public int getStarCount() {
            return starCount;
        }

        public void setStarCount(int starCount) {
            this.starCount = starCount;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getMarkerPrice() {
            return markerPrice;
        }

        public void setMarkerPrice(double markerPrice) {
            this.markerPrice = markerPrice;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public boolean isHot() {
            return hot;
        }

        public void setHot(boolean hot) {
            this.hot = hot;
        }

        public StoreBean getStore() {
            return store;
        }

        public void setStore(StoreBean store) {
            this.store = store;
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
             * attention : null
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
            private double lng;
            private double lat;
            private int starCount;
            private int serviceCount;
            private String tel;
            private Object attention;
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

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
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

            public boolean isHot() {
                return hot;
            }

            public void setHot(boolean hot) {
                this.hot = hot;
            }
        }
    }
}
