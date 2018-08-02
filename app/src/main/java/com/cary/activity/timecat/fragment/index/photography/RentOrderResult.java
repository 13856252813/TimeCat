package com.cary.activity.timecat.fragment.index.photography;

import java.util.List;

/**
 * Author: create by Cary
 * Date: on 2018/7/22 17:12
 * Comment: 服装租借
 */
public class RentOrderResult {

    /**
     * code : string
     * data : {"amount":0,"area":"string","city":"string","clothId":0,"clothes":{"amount":0,"attention":true,"catagory":0,"content":"string","hot":true,"id":0,"imgurl":"string","imgurls":"string","loginUid":0,"markerPrice":0,"norms":[{"clothId":0,"id":0,"items":[{"id":0,"imgurl":"string","inventory":0,"item":"string","normId":0,"price":0}],"norm":"string"}],"paiAmount":0,"postage":0,"sellCount":0,"sellType":0,"sendtype":"string","sex":0,"starCount":0,"store":{"attention":true,"city":"string","content":"string","createTime":"2018-07-22T04:16:33.394Z","detail":"string","hot":true,"id":0,"imgurl":"string","lat":0,"lng":0,"loginUid":0,"province":"string","serviceCount":0,"starCount":0,"storeName":"string","tel":"string","uid":0},"storeId":0,"title":"string"},"createTime":"2018-07-22T04:16:33.394Z","deposit":0,"detail":"string","expressCode":"string","expressName":"string","expressNum":"string","id":0,"mobile":"string","name":"string","normstr":"string","orderAmount":0,"orderCount":0,"ordernum":"string","payInfo":"string","payTime":"2018-07-22T04:16:33.394Z","payType":"string","province":"string","rent":0,"returnCode":"string","returnName":"string","returnNum":"string","status":0,"storeId":0,"uid":0}
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
         * amount : 0
         * area : string
         * city : string
         * clothId : 0
         * clothes : {"amount":0,"attention":true,"catagory":0,"content":"string","hot":true,"id":0,"imgurl":"string","imgurls":"string","loginUid":0,"markerPrice":0,"norms":[{"clothId":0,"id":0,"items":[{"id":0,"imgurl":"string","inventory":0,"item":"string","normId":0,"price":0}],"norm":"string"}],"paiAmount":0,"postage":0,"sellCount":0,"sellType":0,"sendtype":"string","sex":0,"starCount":0,"store":{"attention":true,"city":"string","content":"string","createTime":"2018-07-22T04:16:33.394Z","detail":"string","hot":true,"id":0,"imgurl":"string","lat":0,"lng":0,"loginUid":0,"province":"string","serviceCount":0,"starCount":0,"storeName":"string","tel":"string","uid":0},"storeId":0,"title":"string"}
         * createTime : 2018-07-22T04:16:33.394Z
         * deposit : 0
         * detail : string
         * expressCode : string
         * expressName : string
         * expressNum : string
         * id : 0
         * mobile : string
         * name : string
         * normstr : string
         * orderAmount : 0
         * orderCount : 0
         * ordernum : string
         * payInfo : string
         * payTime : 2018-07-22T04:16:33.394Z
         * payType : string
         * province : string
         * rent : 0
         * returnCode : string
         * returnName : string
         * returnNum : string
         * status : 0
         * storeId : 0
         * uid : 0
         */

        private double amount;
        private String area;
        private String city;
        private int clothId;
        private ClothesBean clothes;
        private String createTime;
        private int deposit;
        private String detail;
        private String expressCode;
        private String expressName;
        private String expressNum;
        private int id;
        private String mobile;
        private String name;
        private String normstr;
        private double orderAmount;
        private int orderCount;
        private String ordernum;
        private String payInfo;
        private String payTime;
        private String payType;
        private String province;
        private int rent;
        private String returnCode;
        private String returnName;
        private String returnNum;
        private int status;
        private int storeId;
        private int uid;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getClothId() {
            return clothId;
        }

        public void setClothId(int clothId) {
            this.clothId = clothId;
        }

        public ClothesBean getClothes() {
            return clothes;
        }

        public void setClothes(ClothesBean clothes) {
            this.clothes = clothes;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDeposit() {
            return deposit;
        }

        public void setDeposit(int deposit) {
            this.deposit = deposit;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getExpressCode() {
            return expressCode;
        }

        public void setExpressCode(String expressCode) {
            this.expressCode = expressCode;
        }

        public String getExpressName() {
            return expressName;
        }

        public void setExpressName(String expressName) {
            this.expressName = expressName;
        }

        public String getExpressNum() {
            return expressNum;
        }

        public void setExpressNum(String expressNum) {
            this.expressNum = expressNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNormstr() {
            return normstr;
        }

        public void setNormstr(String normstr) {
            this.normstr = normstr;
        }

        public double getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(double orderAmount) {
            this.orderAmount = orderAmount;
        }

        public int getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(int orderCount) {
            this.orderCount = orderCount;
        }

        public String getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
        }

        public String getPayInfo() {
            return payInfo;
        }

        public void setPayInfo(String payInfo) {
            this.payInfo = payInfo;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public int getRent() {
            return rent;
        }

        public void setRent(int rent) {
            this.rent = rent;
        }

        public String getReturnCode() {
            return returnCode;
        }

        public void setReturnCode(String returnCode) {
            this.returnCode = returnCode;
        }

        public String getReturnName() {
            return returnName;
        }

        public void setReturnName(String returnName) {
            this.returnName = returnName;
        }

        public String getReturnNum() {
            return returnNum;
        }

        public void setReturnNum(String returnNum) {
            this.returnNum = returnNum;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public static class ClothesBean {
            /**
             * amount : 0
             * attention : true
             * catagory : 0
             * content : string
             * hot : true
             * id : 0
             * imgurl : string
             * imgurls : string
             * loginUid : 0
             * markerPrice : 0
             * norms : [{"clothId":0,"id":0,"items":[{"id":0,"imgurl":"string","inventory":0,"item":"string","normId":0,"price":0}],"norm":"string"}]
             * paiAmount : 0
             * postage : 0
             * sellCount : 0
             * sellType : 0
             * sendtype : string
             * sex : 0
             * starCount : 0
             * store : {"attention":true,"city":"string","content":"string","createTime":"2018-07-22T04:16:33.394Z","detail":"string","hot":true,"id":0,"imgurl":"string","lat":0,"lng":0,"loginUid":0,"province":"string","serviceCount":0,"starCount":0,"storeName":"string","tel":"string","uid":0}
             * storeId : 0
             * title : string
             */

            private double amount;
            private boolean attention;
            private int catagory;
            private String content;
            private boolean hot;
            private int id;
            private String imgurl;
            private String imgurls;
            private int loginUid;
            private double markerPrice;
            private double paiAmount;
            private int postage;
            private int sellCount;
            private int sellType;
            private String sendtype;
            private int sex;
            private int starCount;
            private StoreBean store;
            private int storeId;
            private String title;
            private List<NormsBean> norms;

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public boolean isAttention() {
                return attention;
            }

            public void setAttention(boolean attention) {
                this.attention = attention;
            }

            public int getCatagory() {
                return catagory;
            }

            public void setCatagory(int catagory) {
                this.catagory = catagory;
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

            public double getMarkerPrice() {
                return markerPrice;
            }

            public void setMarkerPrice(double markerPrice) {
                this.markerPrice = markerPrice;
            }

            public double getPaiAmount() {
                return paiAmount;
            }

            public void setPaiAmount(double paiAmount) {
                this.paiAmount = paiAmount;
            }

            public int getPostage() {
                return postage;
            }

            public void setPostage(int postage) {
                this.postage = postage;
            }

            public int getSellCount() {
                return sellCount;
            }

            public void setSellCount(int sellCount) {
                this.sellCount = sellCount;
            }

            public int getSellType() {
                return sellType;
            }

            public void setSellType(int sellType) {
                this.sellType = sellType;
            }

            public String getSendtype() {
                return sendtype;
            }

            public void setSendtype(String sendtype) {
                this.sendtype = sendtype;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
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

            public List<NormsBean> getNorms() {
                return norms;
            }

            public void setNorms(List<NormsBean> norms) {
                this.norms = norms;
            }

            public static class StoreBean {
                /**
                 * attention : true
                 * city : string
                 * content : string
                 * createTime : 2018-07-22T04:16:33.394Z
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
                private double lat;
                private double lng;
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

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
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

            public static class NormsBean {
                /**
                 * clothId : 0
                 * id : 0
                 * items : [{"id":0,"imgurl":"string","inventory":0,"item":"string","normId":0,"price":0}]
                 * norm : string
                 */

                private int clothId;
                private int id;
                private String norm;
                private List<ItemsBean> items;

                public int getClothId() {
                    return clothId;
                }

                public void setClothId(int clothId) {
                    this.clothId = clothId;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getNorm() {
                    return norm;
                }

                public void setNorm(String norm) {
                    this.norm = norm;
                }

                public List<ItemsBean> getItems() {
                    return items;
                }

                public void setItems(List<ItemsBean> items) {
                    this.items = items;
                }

                public static class ItemsBean {
                    /**
                     * id : 0
                     * imgurl : string
                     * inventory : 0
                     * item : string
                     * normId : 0
                     * price : 0
                     */

                    private int id;
                    private String imgurl;
                    private int inventory;
                    private String item;
                    private int normId;
                    private double price;

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

                    public int getInventory() {
                        return inventory;
                    }

                    public void setInventory(int inventory) {
                        this.inventory = inventory;
                    }

                    public String getItem() {
                        return item;
                    }

                    public void setItem(String item) {
                        this.item = item;
                    }

                    public int getNormId() {
                        return normId;
                    }

                    public void setNormId(int normId) {
                        this.normId = normId;
                    }

                    public double getPrice() {
                        return price;
                    }

                    public void setPrice(double price) {
                        this.price = price;
                    }
                }
            }
        }
    }
}
