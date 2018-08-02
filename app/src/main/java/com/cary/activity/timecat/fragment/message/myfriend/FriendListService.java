package com.cary.activity.timecat.fragment.message.myfriend;

import com.cary.activity.timecat.fragment.message.group.GroupChangeModel;
import com.cary.activity.timecat.util.modelbean.ModelBeanData;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 好友列表
 */
public interface FriendListService {

    // 好友分页列表
    @GET("api/Friend/Page")
    Call<FriendListResult> createCommitPage(@Header("access-token") String token,
                                            @Query("uid") int uid,
                                            @Query("currentPage") int orderId);

    //解散分组
    @DELETE("api/HuanxinGroup/{id}")
    Call<FriendListResult> createCommitDel(@Header("access-token") String token,
                                           @Path("id") int id);

    //分组添加成员
    @POST("api/HuanxinGroup/User/{id}")
    Call<FriendListResult> createCommitAddUser(@Header("access-token") String token,
                                               @Path("id") int id,
                                               @Field("uid") int uid);

    //分组删除成员
    @DELETE("api/HuanxinGroup/User/{id}")
    Call<ModelBeanData> createCommitDelUser(@Header("access-token") String token,
                                            @Path("id") int id,
                                            @Path("uid") int uid);

    //分组修改信息 名称
    @PUT("api/HuanxinGroup")
    Call<GroupChangeModel> createCommitChangeName(@Header("access-token") String token,
                                                  @Query("id") int id,
                                                  @Query("name") String name);

    //分组修改信息 名称 头像
    @PUT("api/HuanxinGroup")
    Call<GroupChangeModel> createCommitChange(@Header("access-token") String token,
                                                     @Query("id") int id,
                                                     @Query("name") String name,
                                                     @Query("imgurl") String imgurl);

}
