package com.cary.activity.timecat.manager.client.setmealdetial;

public class SetMealCollectResult {
    public class PhotoPackage {
        private int id;

        private int storeId;

        private String storeName;

        private String storeImgurl;

        private String imgurl;

        private String imgurls;

        private String title;

        private int price;

        private int marketPrice;

        private int sellCount;

        private int evaStar;

        private int clothCount;

        private int filmCount;

        private int rucheCount;

        private String content;

        private String createTime;

        private String type;

        private int packageType;

        private int packagePhotoType;

        private int frontMoney;

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

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getStoreName() {
            return this.storeName;
        }

        public void setStoreImgurl(String storeImgurl) {
            this.storeImgurl = storeImgurl;
        }

        public String getStoreImgurl() {
            return this.storeImgurl;
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

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getPrice() {
            return this.price;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public int getMarketPrice() {
            return this.marketPrice;
        }

        public void setSellCount(int sellCount) {
            this.sellCount = sellCount;
        }

        public int getSellCount() {
            return this.sellCount;
        }

        public void setEvaStar(int evaStar) {
            this.evaStar = evaStar;
        }

        public int getEvaStar() {
            return this.evaStar;
        }

        public void setClothCount(int clothCount) {
            this.clothCount = clothCount;
        }

        public int getClothCount() {
            return this.clothCount;
        }

        public void setFilmCount(int filmCount) {
            this.filmCount = filmCount;
        }

        public int getFilmCount() {
            return this.filmCount;
        }

        public void setRucheCount(int rucheCount) {
            this.rucheCount = rucheCount;
        }

        public int getRucheCount() {
            return this.rucheCount;
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

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

        public void setPackageType(int packageType) {
            this.packageType = packageType;
        }

        public int getPackageType() {
            return this.packageType;
        }

        public void setPackagePhotoType(int packagePhotoType) {
            this.packagePhotoType = packagePhotoType;
        }

        public int getPackagePhotoType() {
            return this.packagePhotoType;
        }

        public void setFrontMoney(int frontMoney) {
            this.frontMoney = frontMoney;
        }

        public int getFrontMoney() {
            return this.frontMoney;
        }

    }

    public class Data {
        private int id;

        private int packageId;

        private int uid;

        private String attentionTime;

        private PhotoPackage photoPackage;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setPackageId(int packageId) {
            this.packageId = packageId;
        }

        public int getPackageId() {
            return this.packageId;
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

        public void setPhotoPackage(PhotoPackage photoPackage) {
            this.photoPackage = photoPackage;
        }

        public PhotoPackage getPhotoPackage() {
            return this.photoPackage;
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