package com.cary.activity.timecat.download;

/**
 * Created by Cary on 2018/3/29.
 */

public interface DownloadListener {
    void onProgress(long totalLength, long downloadLength, boolean isComplete);
}
