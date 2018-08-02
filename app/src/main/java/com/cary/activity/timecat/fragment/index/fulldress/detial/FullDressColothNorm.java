package com.cary.activity.timecat.fragment.index.fulldress.detial;

import java.util.List;

/**
 * 服装规格
 */
public class FullDressColothNorm {
    public class Data {
        private int id;

        private int clothId;

        private String norm;

        private List<Items> items;

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

        public void setNorm(String norm) {
            this.norm = norm;
        }

        public String getNorm() {
            return this.norm;
        }

        public void setItems(List<Items> items) {
            this.items = items;
        }

        public List<Items> getItems() {
            return this.items;
        }

        public class Items {
            private int id;

            private int normId;

            private String item;

            private int inventory;

            private int price;

            private String imgurl;

            public void setId(int id) {
                this.id = id;
            }

            public int getId() {
                return this.id;
            }

            public void setNormId(int normId) {
                this.normId = normId;
            }

            public int getNormId() {
                return this.normId;
            }

            public void setItem(String item) {
                this.item = item;
            }

            public String getItem() {
                return this.item;
            }

            public void setInventory(int inventory) {
                this.inventory = inventory;
            }

            public int getInventory() {
                return this.inventory;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getPrice() {
                return this.price;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public String getImgurl() {
                return this.imgurl;
            }
        }
    }

    private String code;

    private String msg;

    private List<Data> data;

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

}
