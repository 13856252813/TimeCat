package com.cary.activity.timecat;

import android.app.ActivityManager;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.cary.activity.timecat.util.SharedPreferencesHelper;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.PlatformConfig;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Cary on 2018/3/29.
 */

public class MyRealApplication extends MultiDexApplication {

    private static final String TAG = MyRealApplication.class.getSimpleName();
    //com.cary.activity.timecat   com.umeng.message.example
    public static final String UPDATE_STATUS_ACTION = "com.cary.activity.timecat.action.UPDATE_STATUS";
    private Handler handler;

    public static Context applicationContext;
    private static MyRealApplication instance;
    /**
     * nickname for current user, the nickname instead of ID be shown when user receive notification from APNs
     */
    public static String currentUserNick = "";
    private SharedPreferencesHelper sharedPreferencesHelper;

    // 记录是否已经初始化
    private boolean isInit = false;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);//启用矢量图兼容
//        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
//            @NonNull
//            @Override
//            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
//                return new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormat("更新于 %s"));
//            }
//        });
    }

    public static MyRealApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
//        MultiDex.install(this);
        super.onCreate();
        applicationContext = this;
        instance = this;

        sharedPreferencesHelper = new SharedPreferencesHelper(this);
// 初始化环信SDK
        initEasemob();
        //设置LOG开关，默认为false
        UMConfigure.setLogEnabled(true);
        try {
            Class<?> aClass = Class.forName("com.umeng.commonsdk.UMConfigure");
            Field[] fs = aClass.getDeclaredFields();
            for (Field f : fs) {
                Log.e(TAG, "ff=" + f.getName() + "   " + f.getType().getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:友盟 app key
         * 参数3:友盟 channel
         * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数5:Push推送业务的secret
         */
        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        UMConfigure.init(this, "5b0a78eb8f4a9d1f5e000085", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                "3eebb5ce9e81d1424d06122a18b85f1f");
        //PushSDK初始化(如使用推送SDK，必须调用此方法)
        initUpush();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    private void initUpush() {
        final PushAgent mPushAgent = PushAgent.getInstance(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                //注册推送服务 每次调用register都会回调该接口
                mPushAgent.register(new IUmengRegisterCallback() {
                    @Override
                    public void onSuccess(String deviceToken) {
                        Log.i(TAG, "device token: " + deviceToken);
                        sendBroadcast(new Intent(UPDATE_STATUS_ACTION));
                        String token = mPushAgent.getRegistrationId();
                        sharedPreferencesHelper.put("deviceToken", deviceToken);

                    }

                    @Override
                    public void onFailure(String s, String s1) {
                        Log.i(TAG, "register failed: " + s + " " + s1);
                        sendBroadcast(new Intent(UPDATE_STATUS_ACTION));
                    }
                });

                handler = new Handler(getMainLooper());

                //sdk开启通知声音
                mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
                // sdk关闭通知声音
                // mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
                // 通知声音由服务端控制
                // mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER);

                // mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
                // mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);

                UmengMessageHandler messageHandler = new UmengMessageHandler() {

                    /**
                     * 通知的回调方法（通知送达时会回调）
                     */
                    @Override
                    public void dealWithNotificationMessage(Context context, UMessage msg) {
                        //调用super，会展示通知，不调用super，则不展示通知。
                        super.dealWithNotificationMessage(context, msg);
                    }

                    /**
                     * 自定义消息的回调方法
                     */
                    @Override
                    public void dealWithCustomMessage(final Context context, final UMessage msg) {

                        handler.post(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                // 对自定义消息的处理方式，点击或者忽略
                                boolean isClickOrDismissed = true;
                                if (isClickOrDismissed) {
                                    //自定义消息的点击统计
                                    UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
                                } else {
                                    //自定义消息的忽略统计
                                    UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
                                }
//                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    /**
                     * 自定义通知栏样式的回调方法
                     */
                    @Override
                    public Notification getNotification(Context context, UMessage msg) {
                        switch (msg.builder_id) {
                            case 1:
                                Notification.Builder builder = new Notification.Builder(context);
                                RemoteViews myNotificationView = new RemoteViews(context.getPackageName(),
                                        R.layout.notification_view);
                                myNotificationView.setTextViewText(R.id.notification_title, msg.title);
                                myNotificationView.setTextViewText(R.id.notification_text, msg.text);
                                myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
                                myNotificationView.setImageViewResource(R.id.notification_small_icon,
                                        getSmallIconId(context, msg));
                                builder.setContent(myNotificationView)
                                        .setSmallIcon(getSmallIconId(context, msg))
                                        .setTicker(msg.ticker)
                                        .setAutoCancel(true);

                                return builder.getNotification();
                            default:
                                //默认为0，若填写的builder_id并不存在，也使用默认。
                                return super.getNotification(context, msg);
                        }
                    }
                };
                mPushAgent.setMessageHandler(messageHandler);

                /**
                 * 自定义行为的回调处理，参考文档：高级功能-通知的展示及提醒-自定义通知打开动作
                 * UmengNotificationClickHandler是在BroadcastReceiver中被调用，故
                 * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
                 * */
                UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {

                    @Override
                    public void launchApp(Context context, UMessage msg) {
                        super.launchApp(context, msg);
                    }

                    @Override
                    public void openUrl(Context context, UMessage msg) {
                        super.openUrl(context, msg);
                    }

                    @Override
                    public void openActivity(Context context, UMessage msg) {
                        super.openActivity(context, msg);
                    }

                    @Override
                    public void dealWithCustomAction(Context context, UMessage msg) {
                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                    }
                };
                //使用自定义的NotificationHandler
                mPushAgent.setNotificationClickHandler(notificationClickHandler);

                //使用完全自定义处理
                //mPushAgent.setPushIntentServiceClass(UmengNotificationService.class);

                //小米通道
                //MiPushRegistar.register(this, XIAOMI_ID, XIAOMI_KEY);
                //华为通道
                //HuaWeiRegister.register(this);
                //魅族通道
                //MeizuRegister.register(this, MEIZU_APPID, MEIZU_APPKEY);
            }
        }).start();

    }

    {
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
        PlatformConfig.setAlipay("2015111700822536");
        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
        PlatformConfig.setPinterest("1439206");
        PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
        PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
        PlatformConfig.setVKontakte("5764965", "5My6SNliAaLxEm3Lyd9J");
        PlatformConfig.setDropbox("oz8v5apet3arcdy", "h7p2pjbzkkxt02a");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void initEasemob() {
        // 获取当前进程 id 并取得进程名
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        /**
         * 如果app启用了远程的service，此application:onCreate会被调用2次
         * 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
         * 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process name就立即返回
         */
        if (processAppName == null || !processAppName.equalsIgnoreCase(applicationContext.getPackageName())) {
            // 则此application的onCreate 是被service 调用的，直接返回
            return;
        }
        if (isInit) {
            return;
        }

        // 调用初始化方法初始化sdk
        EMClient.getInstance().init(this, initOptions());
        // 设置开启debug模式
        EMClient.getInstance().setDebugMode(true);

        // 设置初始化已经完成
        isInit = true;
    }

    /**
     * SDK初始化的一些配置
     * 关于 EMOptions 可以参考官方的 API 文档
     * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1chat_1_1_e_m_options.html
     */
    private EMOptions initOptions() {

        EMOptions options = new EMOptions();
        // 设置Appkey，如果配置文件已经配置，这里可以不用设置
//         options.setAppKey("1135180519177002#shiguangmao");
        // 设置自动登录
        options.setAutoLogin(true);
        // 设置是否需要发送已读回执
        options.setRequireAck(true);
        // 设置是否需要发送回执，
        options.setRequireDeliveryAck(true);
        // 设置是否根据服务器时间排序，默认是true
        options.setSortMessageByServerTime(false);
        // 收到好友申请是否自动同意，如果是自动同意就不会收到好友请求的回调，因为sdk会自动处理，默认为true
        options.setAcceptInvitationAlways(false);
        // 设置是否自动接收加群邀请，如果设置了当收到群邀请会自动同意加入
        options.setAutoAcceptGroupInvitation(false);
        // 设置（主动或被动）退出群组时，是否删除群聊聊天记录
        options.setDeleteMessagesAsExitGroup(false);
        // 设置是否允许聊天室的Owner 离开并删除聊天室的会话
        options.allowChatroomOwnerLeave(true);
        // 设置google GCM推送id，国内可以不用设置
        // options.setGCMNumber(MLConstants.ML_GCM_NUMBER);
        // 设置集成小米推送的appid和appkey
        // options.setMipushConfig(MLConstants.ML_MI_APP_ID, MLConstants.ML_MI_APP_KEY);

        return options;
    }

    /**
     * 根据Pid获取当前进程的名字，一般就是当前app的包名
     *
     * @param pid 进程的id
     * @return 返回进程的名字
     */
    private String getAppName(int pid) {
        String processName = null;
        ActivityManager activityManager =
                (ActivityManager) applicationContext.getSystemService(Context.ACTIVITY_SERVICE);
        List list = activityManager.getRunningAppProcesses();
        Iterator i = list.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info =
                    (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pid) {
                    // 根据进程的信息获取当前进程的名字
                    processName = info.processName;
                    // 返回当前进程名
                    return processName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 没有匹配的项，返回为null
        return null;
    }
}

