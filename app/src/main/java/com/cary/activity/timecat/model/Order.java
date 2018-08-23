package com.cary.activity.timecat.model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {


    /**
     * code : string
     * data : {"amount":0,"attractions":[{"attractionsAmount":0,"attractionsId":0,"attractionsImgurl":"string","attractionsTitle":"string","id":0,"orderId":0}],"clothes":[{"back":true,"clothesAmount":0,"clothesId":0,"clothesImgurl":"string","clothesTitle":"string","id":0,"orderId":0,"sex":0,"success":true}],"createTime":"2018-08-22T15:01:00.311Z","earnestMoney":0,"earnestOrdernum":"string","earnestPay":true,"earnestPayType":"string","earnestPaytime":"2018-08-22T15:01:00.311Z","groupId":"string","id":0,"imgurl":"string","infos":[{"bride":"string","bridegroom":"string","duan":"string","id":0,"orderId":0,"photoTime":"2018-08-22T15:01:00.311Z","recevice":"string","weddingDate":"2018-08-22T15:01:00.311Z"}],"ordernum":"string","packages":[{"id":0,"orderId":0,"packageAmount":0,"packageId":0,"packageImgurl":"string","packageTitle":"string","packageType":"string","shopId":0,"shopName":"string"}],"payTime":"2018-08-22T15:01:00.311Z","payType":"string","status":0,"storeId":0,"teachers":[{"extraAmount":0,"id":0,"imgurl":"string","nickname":"string","orderId":0,"teacherAmount":0,"teacherId":0,"teacherType":"string"}],"title":"string","type":"string","uid":0}
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

    public static class DataBean implements Serializable {
        /**
         * amount : 0
         * attractions : [{"attractionsAmount":0,"attractionsId":0,"attractionsImgurl":"string","attractionsTitle":"string","id":0,"orderId":0}]
         * clothes : [{"back":true,"clothesAmount":0,"clothesId":0,"clothesImgurl":"string","clothesTitle":"string","id":0,"orderId":0,"sex":0,"success":true}]
         * createTime : 2018-08-22T15:01:00.311Z
         * earnestMoney : 0
         * earnestOrdernum : string
         * earnestPay : true
         * earnestPayType : string
         * earnestPaytime : 2018-08-22T15:01:00.311Z
         * groupId : string
         * id : 0
         * imgurl : string
         * infos : [{"bride":"string","bridegroom":"string","duan":"string","id":0,"orderId":0,"photoTime":"2018-08-22T15:01:00.311Z","recevice":"string","weddingDate":"2018-08-22T15:01:00.311Z"}]
         * ordernum : string
         * packages : [{"id":0,"orderId":0,"packageAmount":0,"packageId":0,"packageImgurl":"string","packageTitle":"string","packageType":"string","shopId":0,"shopName":"string"}]
         * payTime : 2018-08-22T15:01:00.311Z
         * payType : string
         * status : 0
         * storeId : 0
         * teachers : [{"extraAmount":0,"id":0,"imgurl":"string","nickname":"string","orderId":0,"teacherAmount":0,"teacherId":0,"teacherType":"string"}]
         * title : string
         * type : string
         * uid : 0
         */

        private int amount;
        private String createTime;
        private int earnestMoney;
        private String earnestOrdernum;
        private boolean earnestPay;
        private String earnestPayType;
        private String earnestPaytime;
        private String groupId;
        private int id;
        private String imgurl;
        private String ordernum;
        private String payTime;
        private String payType;
        private int status;
        private int storeId;
        private String title;
        private String type;
        private int uid;
        private List<AttractionsBean> attractions;
        private List<ClothesBean> clothes;
        private List<InfosBean> infos;
        private List<PackagesBean> packages;
        private List<TeachersBean> teachers;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getEarnestMoney() {
            return earnestMoney;
        }

        public void setEarnestMoney(int earnestMoney) {
            this.earnestMoney = earnestMoney;
        }

        public String getEarnestOrdernum() {
            return earnestOrdernum;
        }

        public void setEarnestOrdernum(String earnestOrdernum) {
            this.earnestOrdernum = earnestOrdernum;
        }

        public boolean isEarnestPay() {
            return earnestPay;
        }

        public void setEarnestPay(boolean earnestPay) {
            this.earnestPay = earnestPay;
        }

        public String getEarnestPayType() {
            return earnestPayType;
        }

        public void setEarnestPayType(String earnestPayType) {
            this.earnestPayType = earnestPayType;
        }

        public String getEarnestPaytime() {
            return earnestPaytime;
        }

        public void setEarnestPaytime(String earnestPaytime) {
            this.earnestPaytime = earnestPaytime;
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

        public String getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(String ordernum) {
            this.ordernum = ordernum;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public List<AttractionsBean> getAttractions() {
            return attractions;
        }

        public void setAttractions(List<AttractionsBean> attractions) {
            this.attractions = attractions;
        }

        public List<ClothesBean> getClothes() {
            return clothes;
        }

        public void setClothes(List<ClothesBean> clothes) {
            this.clothes = clothes;
        }

        public List<InfosBean> getInfos() {
            return infos;
        }

        public void setInfos(List<InfosBean> infos) {
            this.infos = infos;
        }

        public List<PackagesBean> getPackages() {
            return packages;
        }

        public void setPackages(List<PackagesBean> packages) {
            this.packages = packages;
        }

        public List<TeachersBean> getTeachers() {
            return teachers;
        }

        public void setTeachers(List<TeachersBean> teachers) {
            this.teachers = teachers;
        }

        public static class AttractionsBean {
            /**
             * attractionsAmount : 0
             * attractionsId : 0
             * attractionsImgurl : string
             * attractionsTitle : string
             * id : 0
             * orderId : 0
             */

            private int attractionsAmount;
            private int attractionsId;
            private String attractionsImgurl;
            private String attractionsTitle;
            private int id;
            private int orderId;

            public int getAttractionsAmount() {
                return attractionsAmount;
            }

            public void setAttractionsAmount(int attractionsAmount) {
                this.attractionsAmount = attractionsAmount;
            }

            public int getAttractionsId() {
                return attractionsId;
            }

            public void setAttractionsId(int attractionsId) {
                this.attractionsId = attractionsId;
            }

            public String getAttractionsImgurl() {
                return attractionsImgurl;
            }

            public void setAttractionsImgurl(String attractionsImgurl) {
                this.attractionsImgurl = attractionsImgurl;
            }

            public String getAttractionsTitle() {
                return attractionsTitle;
            }

            public void setAttractionsTitle(String attractionsTitle) {
                this.attractionsTitle = attractionsTitle;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }
        }

        public static class ClothesBean {
            /**
             * back : true
             * clothesAmount : 0
             * clothesId : 0
             * clothesImgurl : string
             * clothesTitle : string
             * id : 0
             * orderId : 0
             * sex : 0
             * success : true
             */

            private boolean back;
            private int clothesAmount;
            private int clothesId;
            private String clothesImgurl;
            private String clothesTitle;
            private int id;
            private int orderId;
            private int sex;
            private boolean success;

            public boolean isBack() {
                return back;
            }

            public void setBack(boolean back) {
                this.back = back;
            }

            public int getClothesAmount() {
                return clothesAmount;
            }

            public void setClothesAmount(int clothesAmount) {
                this.clothesAmount = clothesAmount;
            }

            public int getClothesId() {
                return clothesId;
            }

            public void setClothesId(int clothesId) {
                this.clothesId = clothesId;
            }

            public String getClothesImgurl() {
                return clothesImgurl;
            }

            public void setClothesImgurl(String clothesImgurl) {
                this.clothesImgurl = clothesImgurl;
            }

            public String getClothesTitle() {
                return clothesTitle;
            }

            public void setClothesTitle(String clothesTitle) {
                this.clothesTitle = clothesTitle;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public boolean isSuccess() {
                return success;
            }

            public void setSuccess(boolean success) {
                this.success = success;
            }
        }

        public static class InfosBean {
            /**
             * bride : string
             * bridegroom : string
             * duan : string
             * id : 0
             * orderId : 0
             * photoTime : 2018-08-22T15:01:00.311Z
             * recevice : string
             * weddingDate : 2018-08-22T15:01:00.311Z
             */

            private String bride;
            private String bridegroom;
            private String duan;
            private int id;
            private int orderId;
            private String photoTime;
            private String recevice;
            private String weddingDate;

            public String getBride() {
                return bride;
            }

            public void setBride(String bride) {
                this.bride = bride;
            }

            public String getBridegroom() {
                return bridegroom;
            }

            public void setBridegroom(String bridegroom) {
                this.bridegroom = bridegroom;
            }

            public String getDuan() {
                return duan;
            }

            public void setDuan(String duan) {
                this.duan = duan;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public String getPhotoTime() {
                return photoTime;
            }

            public void setPhotoTime(String photoTime) {
                this.photoTime = photoTime;
            }

            public String getRecevice() {
                return recevice;
            }

            public void setRecevice(String recevice) {
                this.recevice = recevice;
            }

            public String getWeddingDate() {
                return weddingDate;
            }

            public void setWeddingDate(String weddingDate) {
                this.weddingDate = weddingDate;
            }
        }

        public static class PackagesBean {
            /**
             * id : 0
             * orderId : 0
             * packageAmount : 0
             * packageId : 0
             * packageImgurl : string
             * packageTitle : string
             * packageType : string
             * shopId : 0
             * shopName : string
             */

            private int id;
            private int orderId;
            private int packageAmount;
            private int packageId;
            private String packageImgurl;
            private String packageTitle;
            private String packageType;
            private int shopId;
            private String shopName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public int getPackageAmount() {
                return packageAmount;
            }

            public void setPackageAmount(int packageAmount) {
                this.packageAmount = packageAmount;
            }

            public int getPackageId() {
                return packageId;
            }

            public void setPackageId(int packageId) {
                this.packageId = packageId;
            }

            public String getPackageImgurl() {
                return packageImgurl;
            }

            public void setPackageImgurl(String packageImgurl) {
                this.packageImgurl = packageImgurl;
            }

            public String getPackageTitle() {
                return packageTitle;
            }

            public void setPackageTitle(String packageTitle) {
                this.packageTitle = packageTitle;
            }

            public String getPackageType() {
                return packageType;
            }

            public void setPackageType(String packageType) {
                this.packageType = packageType;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }
        }

        public static class TeachersBean {
            /**
             * extraAmount : 0
             * id : 0
             * imgurl : string
             * nickname : string
             * orderId : 0
             * teacherAmount : 0
             * teacherId : 0
             * teacherType : string
             */

            private int extraAmount;
            private int id;
            private String imgurl;
            private String nickname;
            private int orderId;
            private int teacherAmount;
            private int teacherId;
            private String teacherType;

            public int getExtraAmount() {
                return extraAmount;
            }

            public void setExtraAmount(int extraAmount) {
                this.extraAmount = extraAmount;
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

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public int getTeacherAmount() {
                return teacherAmount;
            }

            public void setTeacherAmount(int teacherAmount) {
                this.teacherAmount = teacherAmount;
            }

            public int getTeacherId() {
                return teacherId;
            }

            public void setTeacherId(int teacherId) {
                this.teacherId = teacherId;
            }

            public String getTeacherType() {
                return teacherType;
            }

            public void setTeacherType(String teacherType) {
                this.teacherType = teacherType;
            }
        }
    }
}
