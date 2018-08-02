package com.cary.activity.timecat.fragment.person.attention.meal;

import java.util.List;

/**
 * Author: create by Cary
 * Date: on 2018/6/16 12:48
 * Comment:
 */
public class MealAttentionResult {

    /**
     * code : 00
     * msg : Success
     * data : [{"id":3,"packageId":1,"uid":41,"attentionTime":"2018-06-06 23:13:29","photoPackage":{"id":1,"storeId":1,"storeName":"上海旗舰店002","storeImgurl":"sgl/DC7A6926-3682-4021-9B46-51158D807499.jpg","tel":"","imgurl":"8c3962516efd4957bafecf7157e741ca.jpg","imgurls":"114f45ffd5304ed889eccded7c623083.jpg,ea26af77fd114f04be9da44beb9a7b8d.jpg","title":"三亚总监+4星","price":5999,"marketPrice":12999,"sellCount":13,"evaStar":5,"clothCount":5,"filmCount":120,"rucheCount":50,"content":"<p>详情<\/p>","createTime":"2018-04-14 11:00:58","type":"wedding","packageType":1,"packagePhotoType":1,"frontMoney":0.01,"attention":true,"loginUid":null}}]
     * pi : {"pageSize":30,"totalPage":1,"currentPage":1,"totalSize":1,"query":{"uid":41}}
     */

    private String code;
    private String msg;
    private Pi pi;
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

    public Pi getPi() {
        return pi;
    }

    public void setPi(Pi pi) {
        this.pi = pi;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Pi {
        /**
         * pageSize : 30
         * totalPage : 1
         * currentPage : 1
         * totalSize : 1
         * query : {"uid":41}
         */

        private int pageSize;
        private int totalPage;
        private int currentPage;
        private int totalSize;
        private Query query;

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getTotalSize() {
            return totalSize;
        }

        public void setTotalSize(int totalSize) {
            this.totalSize = totalSize;
        }

        public Query getQuery() {
            return query;
        }

        public void setQuery(Query query) {
            this.query = query;
        }

        public static class Query {
            /**
             * uid : 41
             */

            private int uid;

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }
        }
    }

    public static class Data {
        /**
         * id : 3
         * packageId : 1
         * uid : 41
         * attentionTime : 2018-06-06 23:13:29
         * photoPackage : {"id":1,"storeId":1,"storeName":"上海旗舰店002","storeImgurl":"sgl/DC7A6926-3682-4021-9B46-51158D807499.jpg","tel":"","imgurl":"8c3962516efd4957bafecf7157e741ca.jpg","imgurls":"114f45ffd5304ed889eccded7c623083.jpg,ea26af77fd114f04be9da44beb9a7b8d.jpg","title":"三亚总监+4星","price":5999,"marketPrice":12999,"sellCount":13,"evaStar":5,"clothCount":5,"filmCount":120,"rucheCount":50,"content":"<p>详情<\/p>","createTime":"2018-04-14 11:00:58","type":"wedding","packageType":1,"packagePhotoType":1,"frontMoney":0.01,"attention":true,"loginUid":null}
         */

        private int id;
        private int packageId;
        private int uid;
        private String attentionTime;
        private PhotoPackage photoPackage;

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

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getAttentionTime() {
            return attentionTime;
        }

        public void setAttentionTime(String attentionTime) {
            this.attentionTime = attentionTime;
        }

        public PhotoPackage getPhotoPackage() {
            return photoPackage;
        }

        public void setPhotoPackage(PhotoPackage photoPackage) {
            this.photoPackage = photoPackage;
        }

        public static class PhotoPackage {
            /**
             * id : 1
             * storeId : 1
             * storeName : 上海旗舰店002
             * storeImgurl : sgl/DC7A6926-3682-4021-9B46-51158D807499.jpg
             * tel :
             * imgurl : 8c3962516efd4957bafecf7157e741ca.jpg
             * imgurls : 114f45ffd5304ed889eccded7c623083.jpg,ea26af77fd114f04be9da44beb9a7b8d.jpg
             * title : 三亚总监+4星
             * price : 5999.0
             * marketPrice : 12999.0
             * sellCount : 13
             * evaStar : 5.0
             * clothCount : 5
             * filmCount : 120
             * rucheCount : 50
             * content : <p>详情</p>
             * createTime : 2018-04-14 11:00:58
             * type : wedding
             * packageType : 1
             * packagePhotoType : 1
             * frontMoney : 0.01
             * attention : true
             * loginUid : null
             */

            private int id;
            private int storeId;
            private String storeName;
            private String storeImgurl;
            private String tel;
            private String imgurl;
            private String imgurls;
            private String title;
            private double price;
            private double marketPrice;
            private int sellCount;
            private double evaStar;
            private int clothCount;
            private int filmCount;
            private int rucheCount;
            private String content;
            private String createTime;
            private String type;
            private int packageType;
            private int packagePhotoType;
            private double frontMoney;
            private boolean attention;
            private Object loginUid;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getStoreId() {
                return storeId;
            }

            public void setStoreId(int storeId) {
                this.storeId = storeId;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getStoreImgurl() {
                return storeImgurl;
            }

            public void setStoreImgurl(String storeImgurl) {
                this.storeImgurl = storeImgurl;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getMarketPrice() {
                return marketPrice;
            }

            public void setMarketPrice(double marketPrice) {
                this.marketPrice = marketPrice;
            }

            public int getSellCount() {
                return sellCount;
            }

            public void setSellCount(int sellCount) {
                this.sellCount = sellCount;
            }

            public double getEvaStar() {
                return evaStar;
            }

            public void setEvaStar(double evaStar) {
                this.evaStar = evaStar;
            }

            public int getClothCount() {
                return clothCount;
            }

            public void setClothCount(int clothCount) {
                this.clothCount = clothCount;
            }

            public int getFilmCount() {
                return filmCount;
            }

            public void setFilmCount(int filmCount) {
                this.filmCount = filmCount;
            }

            public int getRucheCount() {
                return rucheCount;
            }

            public void setRucheCount(int rucheCount) {
                this.rucheCount = rucheCount;
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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getPackageType() {
                return packageType;
            }

            public void setPackageType(int packageType) {
                this.packageType = packageType;
            }

            public int getPackagePhotoType() {
                return packagePhotoType;
            }

            public void setPackagePhotoType(int packagePhotoType) {
                this.packagePhotoType = packagePhotoType;
            }

            public double getFrontMoney() {
                return frontMoney;
            }

            public void setFrontMoney(double frontMoney) {
                this.frontMoney = frontMoney;
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
        }
    }
}
