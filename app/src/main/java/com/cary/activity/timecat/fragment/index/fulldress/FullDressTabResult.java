package com.cary.activity.timecat.fragment.index.fulldress;

import java.util.List;

/***
 * 礼服馆的title
 */
public class FullDressTabResult {

    public class Data {
        private int id;

        private String name;

        private int sex;

        private int type;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getSex() {
            return this.sex;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return this.type;
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
