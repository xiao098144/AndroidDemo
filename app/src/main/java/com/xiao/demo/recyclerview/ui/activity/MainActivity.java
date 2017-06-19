package com.xiao.demo.recyclerview.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.animation.AnimationActivity;
import com.xiao.demo.recyclerview.widget.customView.BezierLineActivity;
import com.xiao.demo.recyclerview.widget.customView.IrreguralClickActivity;
import com.xiao.demo.recyclerview.widget.customView.IrreguralClickActivity2;
import com.xiao.demo.recyclerview.design.CardViewActivity;
import com.xiao.demo.recyclerview.design.CollapsingToolBarLayoutActivity;
import com.xiao.demo.recyclerview.design.DesignActivity;
import com.xiao.demo.recyclerview.design.NestedScrollActivity;
import com.xiao.demo.recyclerview.design.ToolBarActivity;
import com.xiao.demo.recyclerview.lazyload.LazyLoadActivity;
import com.xiao.demo.recyclerview.ndk.NDKActivity;
import com.xiao.demo.recyclerview.netutil.okhttp.OkHttpActivity;
import com.xiao.demo.recyclerview.picture.PicassoActivity;
import com.xiao.demo.recyclerview.rxjava.RxjavaActivity;
import com.xiao.demo.recyclerview.test.JSTestActivity;
import com.xiao.demo.recyclerview.test.TestActivity;
import com.xiao.demo.recyclerview.test.TestActivity2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    Button btn_recyclerview, btn_picasso, btn_flexbox, btn_roundprogress, btn_wheelview,
            btn_tablayout, btn_card;
    @BindView(R.id.btn_rxjava)
    Button btn_rxjava;
    @BindView(R.id.btn_webview)
    Button btn_webview;
    @BindView(R.id.btn_nestscroll)
    Button btn_nestscroll;
    @BindView(R.id.btn_toolbar)
    Button btn_toolbar;
    @BindView(R.id.btn_collapse)
    Button btn_collapse;
    @BindView(R.id.btn_test)
    Button btn_test;
    @BindView(R.id.btn_test2)
    Button btn_test2;
    @BindView(R.id.btn_okhttp)
    Button btn_okhttp;
    @BindView(R.id.btn_animation)
    Button btn_animation;
    @BindView(R.id.btn_bezier)
    Button btn_bezier;
    @BindView(R.id.btn_ndk)
    Button btn_ndk;

    @BindView(R.id.btn_drawerlayout)
    Button btn_drawerlayout;
    @BindView(R.id.btn_jstest)
    Button btn_jstes;
    @BindView(R.id.btn_fragmenthost)
    Button btn_fragmenttabhost;
    @BindView(R.id.btn_dynamic_tabhost)
    Button btn_dynatabhost;
    @BindView(R.id.btn_stickyrecycler)
    Button btn_stickyrecycler;
    @BindView(R.id.btn_grid)
    Button btn_grid;

    @BindView(R.id.btn_irreguralclcik)
    Button btn_irregrual;

    @BindView(R.id.btn_irreguralclcik2)
    Button btn_irregrual2;

    @BindView(R.id.btn_lazyload)
    Button btn_lazyload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        btn_recyclerview = (Button) findViewById(R.id.btn_recyclerview);
        btn_picasso = (Button) findViewById(R.id.btn_picasso);
        btn_flexbox = (Button) findViewById(R.id.btn_flexbox);
        btn_roundprogress = (Button) findViewById(R.id.btn_roundprogress);
        btn_wheelview = (Button) findViewById(R.id.btn_wheelview);
        btn_tablayout = (Button) findViewById(R.id.btn_tablayout);
        btn_card = (Button) findViewById(R.id.btn_card);
        btn_picasso.setOnClickListener((v) -> {
            PicassoActivity.start(MainActivity.this);
        });
        btn_recyclerview.setOnClickListener((v) -> {
            RecyclerViewActivity.start(MainActivity.this);
        });
        btn_flexbox.setOnClickListener((v) -> {
            FlexBoxActivity.start(MainActivity.this);
        });
        btn_roundprogress.setOnClickListener((v) -> {
            RoundProgressActivity.start(MainActivity.this);
        });
        btn_wheelview.setOnClickListener((v) -> {
            WheelViewActivity.start(MainActivity.this);
        });
        btn_tablayout.setOnClickListener((v) -> {
            DesignActivity.start(MainActivity.this);
        });
        btn_card.setOnClickListener((v) -> {
            CardViewActivity.start(MainActivity.this);
        });
        btn_rxjava.setOnClickListener((v) -> {
            RxjavaActivity.start(MainActivity.this);
        });
        btn_webview.setOnClickListener((v) -> {
            WebviewActivity.start(MainActivity.this);
        });
        btn_nestscroll.setOnClickListener((v) -> {
            NestedScrollActivity.start(MainActivity.this);
        });
        btn_toolbar.setOnClickListener((v) -> {
            ToolBarActivity.start(MainActivity.this);
        });
        btn_collapse.setOnClickListener((v) -> {
            CollapsingToolBarLayoutActivity.start(MainActivity.this);
        });
        btn_test.setOnClickListener((v) -> {
            TestActivity.start(MainActivity.this);
        });
        btn_test2.setOnClickListener(v -> TestActivity2.start(MainActivity.this));

        btn_okhttp.setOnClickListener(v -> OkHttpActivity.start(MainActivity.this));

        btn_animation.setOnClickListener(v -> AnimationActivity.start(MainActivity.this));

        btn_bezier.setOnClickListener(v -> BezierLineActivity.start(MainActivity.this));

        btn_ndk.setOnClickListener(v -> NDKActivity.start(MainActivity.this));

        btn_drawerlayout.setOnClickListener(v -> DrawerLayoutActivity.start(MainActivity.this));

        btn_jstes.setOnClickListener(v -> JSTestActivity.start(MainActivity.this));

        btn_fragmenttabhost.setOnClickListener(v -> FragmentHostActivity.start(MainActivity.this));
        btn_dynatabhost.setOnClickListener(v -> DynamicTabActivity.start(MainActivity.this));
        btn_stickyrecycler.setOnClickListener(v -> StickyRecyclerViewActivity.start(MainActivity.this));

        btn_grid.setOnClickListener(v -> GridViewActivity.start(MainActivity.this));

        btn_irregrual.setOnClickListener(v -> IrreguralClickActivity.start(MainActivity.this));

        btn_irregrual2.setOnClickListener(v -> IrreguralClickActivity2.start(MainActivity.this));

        btn_lazyload.setOnClickListener(v -> LazyLoadActivity.start(MainActivity.this));

    }

}
