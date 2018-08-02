package com.cary.activity.timecat.manager.oss.put;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cary on 2018/4/6.
 */

public class OSSPutCommitParam {

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "OSSPutCommitParam{" +
                "filePath='" + filePath + '\'' +
                '}';
    }
    public Map<String, String> createCommitParams(){
        Map<String, String> params = new HashMap<>();
        params.put("filePath", filePath);
        return params;
    }
}
