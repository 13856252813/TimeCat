package com.cary.activity.timecat.manager.client.myorder.confirmorder;


import com.cary.activity.timecat.manager.client.myorder.confirmorder.teacher.TeacherCommentResult;
import com.cary.activity.timecat.manager.client.myorder.confirmorder.teacher.TeacherDetialResult;
import com.cary.activity.timecat.manager.client.myorder.confirmorder.teacher.TeacherWorkResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Cary on 2018/4/6.
 */

public interface TeacherService {
    @GET("api/TeacherInfo/List")
    Call<TeacherListResult> createCommitList(@Header("access-token") String token,
                                             @Query("storeId") String storeId,//门店ID
                                             @Query("teacherType") String teacherType  //摄影师
    );

    @GET("api/TeacherInfo/Page")
    Call<TeacherListResult> createCommitPage(@Header("access-token") String token,
                                             @Query("storeId") int storeId,//门店ID
                                             @Query("teacherType") String teacherType, //摄影师
                                             @Query("currentPage") int currentPage
    );

    //获取老师详情
    @GET("api/TeacherInfo/{id}")
    Call<TeacherDetialResult> createCommitId(@Header("access-token") String token,
                                             @Path("id") int id
    );

    //留言评价
    @GET("api/TeacherEva/List")
    Call<TeacherCommentResult> createCommitComment(@Header("access-token") String token,
                                                   @Query("teacherId") int teacherId
    );


    //老师作品
    @GET("api/TeacherWorks/List")
    Call<TeacherWorkResult> createCommitTeachWork(@Header("access-token") String token,
                                                  @Query("teacherId") int teacherId
    );

    //老师作品翻页
    @GET("api/TeacherWorks/Page")
    Call<TeacherWorkResult> createCommitTeachWorkPage(@Header("access-token") String token,
                                                      @Query("teacherId") int teacherId,
                                                      @Query("currentPage") int currentpage
    );
}
