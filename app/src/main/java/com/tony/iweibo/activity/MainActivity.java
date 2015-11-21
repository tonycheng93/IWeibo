package com.tony.iweibo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tony.iweibo.R;
import com.tony.iweibo.fragment.FragmentController;
import com.tony.iweibo.utils.ToastUtils;

/**
 * Created by tonycheng on 2015/11/18.
 */
public class MainActivity extends FragmentActivity implements
        RadioGroup.OnCheckedChangeListener,View.OnClickListener {
    private RadioGroup rg_tab;
    private ImageView iv_add;
    private FragmentController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mController = FragmentController.getInstance(this,R.id.fl_content);
        mController.showFragment(0);

        initView();
    }

    private void initView() {
        rg_tab = (RadioGroup) findViewById(R.id.rg_tab);
        iv_add = (ImageView) findViewById(R.id.iv_add);
        rg_tab.setOnCheckedChangeListener(this);
        iv_add.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_home:
                mController.showFragment(0);
                break;
            case R.id.rb_meassage:
                mController.showFragment(1);
                break;
            case R.id.rb_search:
                mController.showFragment(2);
                break;
            case R.id.rb_user:
                mController.showFragment(3);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add:
                ToastUtils.showToast(this, "add", Toast.LENGTH_SHORT);
                break;

            default:
                break;
        }
    }
}
