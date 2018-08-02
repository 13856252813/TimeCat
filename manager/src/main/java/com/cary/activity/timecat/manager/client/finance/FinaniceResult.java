package com.cary.activity.timecat.manager.client.finance;

/**
 * Author: create by Cary
 * Date: on 2018/6/26 19:21
 * Comment:
 */
public class FinaniceResult {
    public class Data {
        private int storeId;

        private String queryTimeType;

        private String queryTime;

        private String queryType;

        private int userCount;

        private int orderCount;

        private double income;

        private double earnest;

        private int withdrawCount;

        private double withdrawAmount;

        private double depositAmout;

        private String begin;

        private String end;

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getStoreId() {
            return this.storeId;
        }

        public void setQueryTimeType(String queryTimeType) {
            this.queryTimeType = queryTimeType;
        }

        public String getQueryTimeType() {
            return this.queryTimeType;
        }

        public void setQueryTime(String queryTime) {
            this.queryTime = queryTime;
        }

        public String getQueryTime() {
            return this.queryTime;
        }

        public void setQueryType(String queryType) {
            this.queryType = queryType;
        }

        public String getQueryType() {
            return this.queryType;
        }

        public void setUserCount(int userCount) {
            this.userCount = userCount;
        }

        public int getUserCount() {
            return this.userCount;
        }

        public void setOrderCount(int orderCount) {
            this.orderCount = orderCount;
        }

        public int getOrderCount() {
            return this.orderCount;
        }

        public void setIncome(int income) {
            this.income = income;
        }

        public double getIncome() {
            return this.income;
        }

        public void setEarnest(int earnest) {
            this.earnest = earnest;
        }

        public double getEarnest() {
            return this.earnest;
        }

        public void setWithdrawCount(int withdrawCount) {
            this.withdrawCount = withdrawCount;
        }

        public int getWithdrawCount() {
            return this.withdrawCount;
        }

        public void setWithdrawAmount(int withdrawAmount) {
            this.withdrawAmount = withdrawAmount;
        }

        public double getWithdrawAmount() {
            return this.withdrawAmount;
        }

        public void setDepositAmout(int depositAmout) {
            this.depositAmout = depositAmout;
        }

        public double getDepositAmout() {
            return this.depositAmout;
        }

        public void setBegin(String begin) {
            this.begin = begin;
        }

        public String getBegin() {
            return this.begin;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getEnd() {
            return this.end;
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
