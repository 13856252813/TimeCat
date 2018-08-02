package com.cary.activity.timecat.fragment.index.timeshop;

import java.io.Serializable;
import java.util.List;

/**
 * Author: create by Cary
 * Date: on 2018/7/15 19:35
 * Comment:
 */
public class TimeShopDetialResult implements Serializable{

    /**
     * code : 00
     * msg : Success
     * data : {"id":12,"storeId":1,"title":"现代简约相册3本,1大2小","imgurl":"74bff08801f3409ba5fb22bd3ccc240e.jpg","imgurls":"516cab3cdd2a47faa1687d03886a9811.jpg,2e290876bd2a45fba02e60faed5253bc.jpg","sellCount":100,"starCount":5,"amount":1200,"content":"<p>阿萨飒飒<\/p>","hot":true,"catagory":15,"markerPrice":2288,"postage":15,"sendtype":"快递配送","norms":[{"id":13,"marketId":12,"norm":"规格3","items":[{"id":26,"normId":13,"item":"选项31","inventory":100,"price":10,"imgurl":"03b94d83e3934f7da56e0dcc7152030c.jpg"},{"id":27,"normId":13,"item":"选项32","inventory":100,"price":12,"imgurl":"8e0c397e61b642ee90075a7f521745b1.jpg"}]}]}
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

    public static class DataBean implements Serializable{
        /**
         * id : 12
         * storeId : 1
         * title : 现代简约相册3本,1大2小
         * imgurl : 74bff08801f3409ba5fb22bd3ccc240e.jpg
         * imgurls : 516cab3cdd2a47faa1687d03886a9811.jpg,2e290876bd2a45fba02e60faed5253bc.jpg
         * sellCount : 100
         * starCount : 5
         * amount : 1200
         * content : <p>阿萨飒飒</p>
         * hot : true
         * catagory : 15
         * markerPrice : 2288
         * postage : 15
         * sendtype : 快递配送
         * norms : [{"id":13,"marketId":12,"norm":"规格3","items":[{"id":26,"normId":13,"item":"选项31","inventory":100,"price":10,"imgurl":"03b94d83e3934f7da56e0dcc7152030c.jpg"},{"id":27,"normId":13,"item":"选项32","inventory":100,"price":12,"imgurl":"8e0c397e61b642ee90075a7f521745b1.jpg"}]}]
         */

        private int id;
        private int storeId;
        private String title;
        private String imgurl;
        private String imgurls;
        private int sellCount;
        private int starCount;
        private int amount;
        private String content;
        private boolean hot;
        private int catagory;
        private int markerPrice;
        private int postage;
        private String sendtype;
        private List<NormsBean> norms;

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

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
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

        public int getCatagory() {
            return catagory;
        }

        public void setCatagory(int catagory) {
            this.catagory = catagory;
        }

        public int getMarkerPrice() {
            return markerPrice;
        }

        public void setMarkerPrice(int markerPrice) {
            this.markerPrice = markerPrice;
        }

        public int getPostage() {
            return postage;
        }

        public void setPostage(int postage) {
            this.postage = postage;
        }

        public String getSendtype() {
            return sendtype;
        }

        public void setSendtype(String sendtype) {
            this.sendtype = sendtype;
        }

        public List<NormsBean> getNorms() {
            return norms;
        }

        public void setNorms(List<NormsBean> norms) {
            this.norms = norms;
        }

        public static class NormsBean implements Serializable{
            /**
             * id : 13
             * marketId : 12
             * norm : 规格3
             * items : [{"id":26,"normId":13,"item":"选项31","inventory":100,"price":10,"imgurl":"03b94d83e3934f7da56e0dcc7152030c.jpg"},{"id":27,"normId":13,"item":"选项32","inventory":100,"price":12,"imgurl":"8e0c397e61b642ee90075a7f521745b1.jpg"}]
             */

            private int id;
            private int marketId;
            private String norm;
            private List<ItemsBean> items;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMarketId() {
                return marketId;
            }

            public void setMarketId(int marketId) {
                this.marketId = marketId;
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

            public static class ItemsBean implements Serializable{
                /**
                 * id : 26
                 * normId : 13
                 * item : 选项31
                 * inventory : 100
                 * price : 10
                 * imgurl : 03b94d83e3934f7da56e0dcc7152030c.jpg
                 */

                private int id;
                private int normId;
                private String item;
                private int inventory;
                private int price;
                private String imgurl;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getNormId() {
                    return normId;
                }

                public void setNormId(int normId) {
                    this.normId = normId;
                }

                public String getItem() {
                    return item;
                }

                public void setItem(String item) {
                    this.item = item;
                }

                public int getInventory() {
                    return inventory;
                }

                public void setInventory(int inventory) {
                    this.inventory = inventory;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public String getImgurl() {
                    return imgurl;
                }

                public void setImgurl(String imgurl) {
                    this.imgurl = imgurl;
                }
            }
        }
    }
}
