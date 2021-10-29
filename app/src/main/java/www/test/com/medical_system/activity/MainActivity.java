package www.test.com.medical_system.activity;

import www.test.com.medical_system.R;
import www.test.com.medical_system.adapter.HomePageAdapter;
import www.test.com.medical_system.utils.ActivityManager;
import www.test.com.medical_system.utils.Constants;
import www.test.com.medical_system.utils.NetUtils;
import www.test.com.medical_system.utils.SPUtils;
import www.test.com.medical_system.utils.UiUtils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BottomPopupView;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private Boolean is2CallBack = false;
    public boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_main);
        ivBack.setVisibility(View.GONE);
//        setRightIcon(R.mipmap.ic_logout);
        isLogin = SPUtils.getPrefBoolean(context, "isLogin", false);
        setTitle("首页");
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(1);
        HomePageAdapter adapter = new HomePageAdapter(context, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        AlphaTabsIndicator alphaTabsIndicator = findViewById(R.id.alphaIndicator);
        alphaTabsIndicator.setViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_right:

                break;
            case R.id.iv_back:
                new XPopup.Builder(context)
                        .asCustom(new ModifyPwdPopup()).show();
                break;
        }
    }


    /*两次后退退出app*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!Constants.DEBUG) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (!is2CallBack) {
                    is2CallBack = true;
                    UiUtils.showInfo(context, "再按一次返回退出app");
                    //两秒后执行
                    new Handler().postDelayed(() -> is2CallBack = false, 2000);
                } else {
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }
        }
        return true;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                setTitle("首页");
                break;
            case 1:
                setTitle("健康");
                break;
            case 2:
                setTitle("智能问诊");
                break;
            case 3:
                setTitle("个人中心");
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public class ModifyPwdPopup extends BottomPopupView {

        private EditText etPwd1;
        private EditText etPwd2;

        public ModifyPwdPopup() {
            super(context);
        }

        @Override
        protected int getImplLayoutId() {
            return R.layout.popup_update_pwd;
        }

        @Override
        protected void onCreate() {
            super.onCreate();
            etPwd1 = findViewById(R.id.et_pwd_1);
            etPwd2 = findViewById(R.id.et_pwd_2);
            findViewById(R.id.btn_submit).setOnClickListener(v -> {
                modifyPwd();
            });
        }

        private void modifyPwd() {
            Map<String, String> params = new HashMap<>();
            params.put("password", etPwd1.getText().toString());
            params.put("confirm_password", etPwd2.getText().toString());
            NetUtils.startRequest(context, Constants.UPDATE_PWD, params, result -> {
                UiUtils.showSuccess(context, "密码修改成功,请重新登录");
                dismiss();
                startActivity(new Intent(context, LoginActivity.class));
                finish();
                ActivityManager.finishAll();
            });
        }
    }
}
