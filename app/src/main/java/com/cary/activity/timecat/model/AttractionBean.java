package com.cary.activity.timecat.model;

import java.io.Serializable;
import java.util.List;

public class AttractionBean implements Serializable {


    /**
     * code : string
     * data : [{"address":"string","amount":0,"attention":true,"content":"string","hot":true,"id":0,"imgurl":"string","imgurls":"string","loginUid":0,"markerPrice":0,"sellCount":0,"starCount":0,"store":{"attention":true,"city":"string","content":"string","createTime":"2018-08-20T03:52:58.035Z","detail":"string","hot":true,"id":0,"imgurl":"string","lat":0,"lng":0,"loginUid":0,"province":"string","serviceCount":0,"starCount":0,"storeName":"string","tel":"string","uid":0},"storeId":0,"title":"string"}]
     * msg : string
     */

    private String code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * address : string
         * amount : 0
         * attention : true
         * content : string
         * hot : true
         * id : 0
         * imgurl : string
         * imgurls : string
         * loginUid : 0
         * markerPrice : 0
         * sellCount : 0
         * starCount : 0
         * store : {"attention":true,"city":"string","content":"string","createTime":"2018-08-20T03:52:58.035Z","detail":"string","hot":true,"id":0,"imgurl":"string","lat":0,"lng":0,"loginUid":0,"province":"string","serviceCount":0,"starCount":0,"storeName":"string","tel":"string","uid":0}
         * storeId : 0
         * title : string
         */

        private String address;
        private int amount;
        private boolean attention;
        private String content;
        private boolean hot;
        private int id;
        private String imgurl;
        private String imgurls;
        private int loginUid;
        private int markerPrice;
        private int sellCount;
        private int starCount;
        private StoreBean store;
        private int storeId;
        private String title;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public boolean isAttention() {
            return attention;
        }

        public void setAttention(boolean attention) {
            this.attention = attention;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isHot() {
            return hot;
        }

        public void setHot(boolean hot) {
            this.hot = hot;
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

        public String getImgurls() {
            return imgurls;
        }

        public void setImgurls(String imgurls) {
            this.imgurls = imgurls;
        }

        public int getLoginUid() {
            return loginUid;
        }

        public void setLoginUid(int loginUid) {
            this.loginUid = loginUid;
        }

        public int getMarkerPrice() {
            return markerPrice;
        }

        public void setMarkerPrice(int markerPrice) {
            this.markerPrice = markerPrice;
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

        public StoreBean getStore() {
            return store;
        }

        public void setStore(StoreBean store) {
            this.store = store;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public static class StoreBean implements Serializable{
            /**
             * attention : true
             * city : string
             * content : string
             * createTime : 2018-08-20T03:52:58.035Z
             * detail : string
             * hot : true
             * id : 0
             * imgurl : string
             * lat : 0
             * lng : 0
             * loginUid : 0
             * province : string
             * serviceCount : 0
             * starCount : 0
             * storeName : string
             * tel : string
             * uid : 0
             */

            private boolean attention;
            private String city;
            private String content;
            private String createTime;
            private String detail;
            private boolean hot;
            private int id;
            private String imgurl;
            private int lat;
            private int lng;
            private int loginUid;
            private String province;
            private int serviceCount;
            private int starCount;
            private String storeName;
            private String tel;
            private int uid;

            public boolean isAttention() {
                return attention;
            }

            public void setAttention(boolean attention) {
                this.attention = attention;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
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

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public boolean isHot() {
                return hot;
            }

            public void setHot(boolean hot) {
                this.hot = hot;
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

            public int getLat() {
                return lat;
            }

            public void setLat(int lat) {
                this.lat = lat;
            }

            public int getLng() {
                return lng;
            }

            public void setLng(int lng) {
                this.lng = lng;
            }

            public int getLoginUid() {
                return loginUid;
            }

            public void setLoginUid(int loginUid) {
                this.loginUid = loginUid;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public int getServiceCount() {
                return serviceCount;
            }

            public void setServiceCount(int serviceCount) {
                this.serviceCount = serviceCount;
            }

            public int getStarCount() {
                return starCount;
            }

            public void setStarCount(int starCount) {
                this.starCount = starCount;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
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
