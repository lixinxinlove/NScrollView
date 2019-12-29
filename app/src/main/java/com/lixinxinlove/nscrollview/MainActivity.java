package com.lixinxinlove.nscrollview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private final int H = 200;

    private NestedScrollView nestedScrollView;
    private LinearLayout fixed_layout;
    private LinearLayout inner_layout;
    private TextView tv_content;

    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fixed_layout = findViewById(R.id.fixed_layout);
        inner_layout = findViewById(R.id.inner_layout);
        tv_content = findViewById(R.id.tv_content);

        textView1 = findViewById(R.id.tv1);
        textView2 = findViewById(R.id.tv2);

        nestedScrollView = findViewById(R.id.nsv);
        nestedScrollView.setFillViewport(true);
        nestedScrollView.setOnScrollChangeListener(scrollChangeListener);

    }


    private NestedScrollView.OnScrollChangeListener scrollChangeListener = new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {


            //滑动到选择日期
            if (scrollY >= inner_layout.getY()) {
                if (tv_content.getParent() != fixed_layout) {
                    inner_layout.removeView(tv_content);
                    fixed_layout.addView(tv_content);
                }
            } else {
                if (tv_content.getParent() != inner_layout) {
                    fixed_layout.removeView(tv_content);
                    inner_layout.addView(tv_content);
                }
            }


            if (scrollY > textView2.getY()){
                int sh = (int) (scrollY - textView2.getY() > textView2.getY() ? textView2.getY() : scrollY - textView2.getY());
                Log.e("scrollChangeListener",sh+"");
                fixed_layout.scrollTo(0, sh);
            } else {
                fixed_layout.scrollTo(0, 0);
            }

        }
    };
}
