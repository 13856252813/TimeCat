package com.cary.activity.timecat.model;

import java.io.Serializable;
import java.util.List;

public class BasicService implements Serializable {


    /**
     * code : string
     * data : [{"id":0,"serviceDetail":"string","serviceName":"string","storeId":0}]
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

    public static class DataBean {
        /**
         * id : 0
         * serviceDetail : string
         * serviceName : string
         * storeId : 0
         */

        private int id;
        private String serviceDetail;
        private String serviceName;
        private int storeId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getServiceDetail() {
            return serviceDetail;
        }

        public void setServiceDetail(String serviceDetail) {
            this.serviceDetail = serviceDetail;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }
    }
}
