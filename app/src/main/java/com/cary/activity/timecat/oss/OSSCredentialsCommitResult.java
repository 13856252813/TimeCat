package com.cary.activity.timecat.oss;

/**
 * Created by Cary on 2018/4/6.
 */

public class OSSCredentialsCommitResult {
    private String code;

    private Data data;

    private String msg;

    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }

    public class Data {
        private String accessKeyId;

        private String accessKeySecret;

        private String bucketName;

        private String endpoint;

        private String expiration;

        private String securityToken;

        public void setAccessKeyId(String accessKeyId){
            this.accessKeyId = accessKeyId;
        }
        public String getAccessKeyId(){
            return this.accessKeyId;
        }
        public void setAccessKeySecret(String accessKeySecret){
            this.accessKeySecret = accessKeySecret;
        }
        public String getAccessKeySecret(){
            return this.accessKeySecret;
        }
        public void setBucketName(String bucketName){
            this.bucketName = bucketName;
        }
        public String getBucketName(){
            return this.bucketName;
        }
        public void setEndpoint(String endpoint){
            this.endpoint = endpoint;
        }
        public String getEndpoint(){
            return this.endpoint;
        }
        public void setExpiration(String expiration){
            this.expiration = expiration;
        }
        public String getExpiration(){
            return this.expiration;
        }
        public void setSecurityToken(String securityToken){
            this.securityToken = securityToken;
        }
        public String getSecurityToken(){
            return this.securityToken;
        }

    }
}
