package com.cary.activity.timecat.manager.guide;

import android.annotation.SuppressLint;

@SuppressLint("Registered")
public class WelcomeActivity{
//        extends AppCompatActivity {
//
//    public static final String TAG = "WelcomeActivity";
//
//    private ViewPager viewPager;
//    private MyViewPagerAdapter myViewPagerAdapter;
//    private LinearLayout dotsLayout;
//    private TextView[] dots;
////    private int[] layouts;
//    private Button btnSkip,btnNext;
//    private PrefManager prefManager;
//    private TimeUtil tu;
//    private GestureDetector gestureDetector; // 用户滑动
//    private int currentItem = 0; // 当前图片的索引号
//    private int flaggingWidth;// 互动翻页所需滚动的长度是当前屏幕宽度的1/3
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        //在setContentView()前检查是否第一次运行
//        prefManager = new PrefManager(this);
//        tu = new TimeUtil();
//        if(!prefManager.isFirstTimeLaunch()){
//            launchHomeScreen();
//            finish();
//        }
//
//        //让状态栏透明
//        if(Build.VERSION.SDK_INT >= 21){
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        }
//
//        setContentView(R.layout.activity_welcome);
//
//        viewPager = (ViewPager)findViewById(R.id.view_pager);
//        dotsLayout = (LinearLayout)findViewById(R.id.layoutDots);
//        btnNext = (Button) findViewById(R.id.btn_next);
//        btnSkip = (Button) findViewById(R.id.btn_skip);
//
//        //添加欢迎页面
//        layouts = new int[]{
//                R.layout.welcome_slide1,
//                R.layout.welcome_slide2,
//                R.layout.welcome_slide3,
//                R.layout.welcome_slide4,
//                R.layout.welcome_slide5
//        };
//        //添加点
//        addBottomDots(0);
//
//        //让状态栏透明
//        changeStatusBarColor();
//
//        gestureDetector = new GestureDetector(new GuideViewTouch());
//        // 获取分辨率
//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        flaggingWidth = dm.widthPixels / 3;
//
//
//        myViewPagerAdapter = new MyViewPagerAdapter();
//        viewPager.setAdapter(myViewPagerAdapter);
//        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
//
//        btnSkip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                launchHomeScreen();
//            }
//        });
//
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int current = getItem(+1);
//                if(current < layouts.length){
//                    viewPager.setCurrentItem(current);
//                }else{
//                    launchHomeScreen();
//                }
//            }
//        });
//
//    }
//
//    private void addBottomDots(int currentPage){
//        dots = new TextView[layouts.length];
//
//        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
//        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);
//
//        dotsLayout.removeAllViews();
//        for(int i = 0; i < dots.length; i++){
//            dots[i] = new TextView(this);
//            dots[i].setText(Html.fromHtml("&#8226;"));//圆点
//            dots[i].setTextSize(35);
//            dots[i].setTextColor(colorsInactive[currentPage]);
//            dotsLayout.addView(dots[i]);
//        }
//
//        if(dots.length > 0){
//            dots[currentPage].setTextColor(colorsActive[currentPage]);
//        }
//    }
//
//    private int getItem(int i){
//        return viewPager.getCurrentItem() + i;
//    }
//
//    private void launchHomeScreen(){
//        //此处判断是否跳转到什么地方
//        //此处加非 可以跳转到登录界面
//        LogUtils.v("145 tu.compareTwoTime3(prefManager.getSaveTime()):"+tu.compareTwoTime3(prefManager.getSaveTime())
//                +"-prefManager.getSaveTime():"+prefManager.getSaveTime());
//
//        if(tu.compareTwoTime3(prefManager.getSaveTime())) {
//            startActivity(new Intent(this, MainActivity.class));
//        }else{
//            startActivity(new Intent(this, LoginActivity.class));
//        }
//        finish();
//    }
//
//    /**
//     * 让状态栏变透明
//     */
//    private void changeStatusBarColor(){
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
//    }
//
//    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener(){
//
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
////            if(position == layouts.length - 1){
////                launchHomeScreen();
////            }else
////            {
////                Log.v(TAG,"144postion:"+position);
////            }
//
//        }
//
//        @Override
//        public void onPageSelected(int position) {
//            addBottomDots(position);
//            currentItem = position;
//            //改变下一步按钮text  “NEXT”或“GOT IT”
//            if(position == layouts.length - 1){
//                btnNext.setText(getString(R.string.start));
//                btnSkip.setVisibility(View.GONE);
//            }
//            else{
//                btnNext.setText(getString(R.string.next));
//                btnSkip.setVisibility(View.VISIBLE);
//            }
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int state) {
//
//        }
//    };
//
//    public class MyViewPagerAdapter extends PagerAdapter {
//
//        private LayoutInflater layoutInflater;
//
//        public MyViewPagerAdapter(){}
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//            View view = layoutInflater.inflate(layouts[position],container,false);
//            container.addView(view);
//            return view;
//        }
//
//        @Override
//        public int getCount() {
//            return layouts.length;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            View view = (View)object;
//            container.removeView(view);
//        }
//    }
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event)
//    {
//        if (gestureDetector.onTouchEvent(event))
//        {
//            event.setAction(MotionEvent.ACTION_CANCEL);
//        }
//        return super.dispatchTouchEvent(event);
//    }
//    private class GuideViewTouch extends GestureDetector.SimpleOnGestureListener
//    {
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
//        {
//            Log.v(TAG,"242currentItem:"+currentItem);
//            if (currentItem == 4)
//            {
//                if (Math.abs(e1.getX() - e2.getX()) > Math.abs(e1.getY() - e2.getY()) && (e1.getX() - e2.getX() <= (-flaggingWidth) || e1.getX() - e2.getX() >= flaggingWidth))
//                {
//                    if (e1.getX() - e2.getX() >= flaggingWidth)
//                    {
//                        launchHomeScreen();
//                        return true;
//                    }
//                }
//            }
//            return false;
//        }
//    }
}
