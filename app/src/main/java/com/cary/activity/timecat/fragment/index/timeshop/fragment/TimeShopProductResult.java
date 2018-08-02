package com.cary.activity.timecat.fragment.index.timeshop.fragment;

import java.util.List;

/**
 * Author: create by Cary
 * Date: on 2018/7/15 19:22
 * Comment:
 */
public class TimeShopProductResult {

    /**
     * code : 00
     * msg : Success
     * data : [{"id":11,"storeId":1,"title":"韩式简约相册2本,1大1小","imgurl":"55f923ea0c524e319eca9d7f668648bd.jpg","imgurls":"d53aabffeafb483a8215e27291803b7b.jpg","sellCount":50,"starCount":5,"amount":1000,"content":"<p>这是详情<\/p>","hot":true,"catagory":14,"markerPrice":1100,"postage":0,"sendtype":"快递配送","norms":[{"id":11,"marketId":11,"norm":"规格","items":[{"id":22,"normId":11,"item":"选项11","inventory":101,"price":1,"imgurl":"c808bd694e4a4281aeb96cdde3868462.jpg"},{"id":23,"normId":11,"item":"选项12","inventory":11,"price":0.05,"imgurl":"bc8db4e0d3534d55a9cb08eabfbcefe8.jpg"}]},{"id":12,"marketId":11,"norm":"规格2","items":[{"id":24,"normId":12,"item":"选项21","inventory":101,"price":12,"imgurl":"a6fc027e0f1f41099f2e1230ce7871c1.jpg"},{"id":25,"normId":12,"item":"选项22","inventory":100,"price":0.1,"imgurl":"70d397a3856c4e45a1acc5b60b71a1ee.jpg"}]}]},{"id":12,"storeId":1,"title":"现代简约相册3本,1大2小","imgurl":"74bff08801f3409ba5fb22bd3ccc240e.jpg","imgurls":"516cab3cdd2a47faa1687d03886a9811.jpg,2e290876bd2a45fba02e60faed5253bc.jpg","sellCount":100,"starCount":5,"amount":1200,"content":"<p>阿萨飒飒<\/p>","hot":true,"catagory":15,"markerPrice":2288,"postage":15,"sendtype":"快递配送","norms":[{"id":13,"marketId":12,"norm":"规格3","items":[{"id":26,"normId":13,"item":"选项31","inventory":100,"price":10,"imgurl":"03b94d83e3934f7da56e0dcc7152030c.jpg"},{"id":27,"normId":13,"item":"选项32","inventory":100,"price":12,"imgurl":"8e0c397e61b642ee90075a7f521745b1.jpg"}]}]}]
     * pi : {"pageSize":30,"totalPage":1,"currentPage":1,"totalSize":2,"query":{}}
     */

    private String code;
    private String msg;
    private PiBean pi;
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

    public PiBean getPi() {
        return pi;
    }

    public void setPi(PiBean pi) {
        this.pi = pi;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PiBean {
        /**
         * pageSize : 30
         * totalPage : 1
         * currentPage : 1
         * totalSize : 2
         * query : {}
         */

        private int pageSize;
        private int totalPage;
        private int currentPage;
        private int totalSize;
        private QueryBean query;

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

        public QueryBean getQuery() {
            return query;
        }

        public void setQuery(QueryBean query) {
            this.query = query;
        }

        public static class QueryBean {
        }
    }

    public static class DataBean {
        /**
         * id : 11
         * storeId : 1
         * title : 韩式简约相册2本,1大1小
         * imgurl : 55f923ea0c524e319eca9d7f668648bd.jpg
         * imgurls : d53aabffeafb483a8215e27291803b7b.jpg
         * sellCount : 50
         * starCount : 5
         * amount : 1000
         * content : <p>这是详情</p>
         * hot : true
         * catagory : 14
         * markerPrice : 1100
         * postage : 0
         * sendtype : 快递配送
         * norms : [{"id":11,"marketId":11,"norm":"规格","items":[{"id":22,"normId":11,"item":"选项11","inventory":101,"price":1,"imgurl":"c808bd694e4a4281aeb96cdde3868462.jpg"},{"id":23,"normId":11,"item":"选项12","inventory":11,"price":0.05,"imgurl":"bc8db4e0d3534d55a9cb08eabfbcefe8.jpg"}]},{"id":12,"marketId":11,"norm":"规格2","items":[{"id":24,"normId":12,"item":"选项21","inventory":101,"price":12,"imgurl":"a6fc027e0f1f41099f2e1230ce7871c1.jpg"},{"id":25,"normId":12,"item":"选项22","inventory":100,"price":0.1,"imgurl":"70d397a3856c4e45a1acc5b60b71a1ee.jpg"}]}]
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

        public static class NormsBean {
            /**
             * id : 11
             * marketId : 11
             * norm : 规格
             * items : [{"id":22,"normId":11,"item":"选项11","inventory":101,"price":1,"imgurl":"c808bd694e4a4281aeb96cdde3868462.jpg"},{"id":23,"normId":11,"item":"选项12","inventory":11,"price":0.05,"imgurl":"bc8db4e0d3534d55a9cb08eabfbcefe8.jpg"}]
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

            public static class ItemsBean {
                /**
                 * id : 22
                 * normId : 11
                 * item : 选项11
                 * inventory : 101
                 * price : 1
                 * imgurl : c808bd694e4a4281aeb96cdde3868462.jpg
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
