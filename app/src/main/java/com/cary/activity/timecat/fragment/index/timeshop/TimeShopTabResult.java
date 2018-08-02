package com.cary.activity.timecat.fragment.index.timeshop;

import java.util.List;

/**
 * Author: create by Cary
 * Date: on 2018/7/15 19:14
 * Comment:
 */
public class TimeShopTabResult {


    /**
     * code : 00
     * msg : Success
     * data : [{"id":14,"name":"相册"},{"id":15,"name":"修片"},{"id":16,"name":"其他"}]
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

    public static class DataBean {
        /**
         * id : 14
         * name : 相册
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
