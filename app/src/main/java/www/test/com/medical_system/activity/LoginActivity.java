package www.test.com.medical_system.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.BottomPopupView;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;

import www.test.com.medical_system.R;
import www.test.com.medical_system.utils.Constants;
import www.test.com.medical_system.utils.NetUtils;
import www.test.com.medical_system.utils.SPUtils;
import www.test.com.medical_system.utils.TimeUtil;
import www.test.com.medical_system.utils.UiUtils;
import www.test.com.medical_system.utils.ValidateUtil;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private MaterialEditText metUsername;
    private MaterialEditText metPassword;
    private BasePopupView settingPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_login);
        //沉浸式
        ImmersionBar.with(this).init();
        initView();
    }

    private void initView() {
        TextView tvYouke = findViewById(R.id.tv_youke);

        Switch swRemember = findViewById(R.id.sw_remember_pwd);
        boolean rememberPwd = SPUtils.getPrefBoolean(context, "remember_pwd", false);
        swRemember.setChecked(rememberPwd);
        //点击状态发生改变
        swRemember.setOnCheckedChangeListener((buttonView, isChecked) -> SPUtils.setPrefBoolean(context, "remember_pwd", isChecked));

        metUsername = findViewById(R.id.met_username);
        metPassword = findViewById(R.id.met_pwd);
        if (rememberPwd) {
            metUsername.setText(SPUtils.getPrefString(this, "username", ""));
            metPassword.setText(SPUtils.getPrefString(this, "password", ""));
        }

        tvYouke.setOnClickListener(this);
        findViewById(R.id.tv_register).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.tv_setting).setOnClickListener(this);

        if (Constants.DEBUG) {
            metUsername.setText("test1");
            metPassword.setText("123123");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_youke:
                SPUtils.setPrefBoolean(context, "isBuyer", true);
                SPUtils.setPrefBoolean(context, "isLogin", false);
                startActivity(new Intent(context, MainActivity.class));
                finish();
                break;
            case R.id.btn_login:
                String baseUrl = SPUtils.getPrefString(context, "base_url", "http://1.12.234.232:8080/");
                if (ValidateUtil.isStringValid(baseUrl)) {
                    if (!TextUtils.isEmpty(metUsername.getText()) && !TextUtils.isEmpty(metPassword.getText())) {
                        login();
                    } else {
                        UiUtils.showError(context, "请先填写用户名和密码");
                    }
                } else {
                    UiUtils.showKnowDialog(context, "提示", "请先填写服务器地址(该页面的右下角)");
                }
                break;
            case R.id.tv_setting:
                showSettingPopup();
                break;
            case R.id.tv_register:
                new XPopup.Builder(context)
                        .asCustom(new RegisterPopup()).show();
                break;
        }
    }

    /*设置地址*/
    private void showSettingPopup() {
        String url;
        if (Constants.AT_HOME) {
            url = "http://30m168f641.zicp.vip/";
        } else {
            url = SPUtils.getPrefString(context, "base_url", "http://1.12.234.232:8080/");
        }
        settingPopup = new XPopup.Builder(context).autoDismiss(false).asInputConfirm(
                "请填写服务器地址",
                "", url,
                "请填写",
                text -> {
                    SPUtils.setPrefString(context, "base_url", text);
                    UiUtils.showSuccess(context, "设置成功");
                    settingPopup.dismiss();
//                    if (ValidateUtil.isIPAddress(text)) {
//                        SPUtils.setPrefString(context, "base_url", text);
//                        UiUtils.showSuccess(context, "设置成功");
//                        settingPopup.dismiss();
//                    } else {
//                        UiUtils.showError(context, "地址不合法，请检验");
//                    }
                }).show();
    }

    /*登录*/
    private void login() {
        String username = metUsername.getText().toString();
        String password = metPassword.getText().toString();
        Map<String, String> params = new HashMap<>();
        params.put("userName", username);
        params.put("password", password);
        NetUtils.startRequestNew(context, Constants.LOGIN, params, (Map<String, Object> result) -> {
            SPUtils.setPrefString(context, "token", (String) result .get("token"));
            SPUtils.setPrefString(context, "username", username);
            SPUtils.setPrefString(context, "password", password);
            Intent intent = new Intent(context, MainActivity.class);
            SPUtils.setPrefBoolean(context, "isLogin", true);
//            SPUtils.setPrefString(context, "name", data.getUser().getName());
            startActivity(intent);
            if (!Constants.DEBUG) {
                finish();
            }

        });
    }

    /*弹出注册对话框*/
    public class RegisterPopup extends BottomPopupView {

        private EditText etUsername;
        private EditText etPwd1;
        private EditText etPwd2;
        private EditText etName;
        private RadioGroup rg;
        private EditText etIdNum;
        private TextView tvBirthday;
        private EditText etPhone;

        public RegisterPopup() {
            super(context);
        }

        @Override
        protected int getImplLayoutId() {
            return R.layout.popup_register;
        }

        @Override
        protected void onCreate() {
            super.onCreate();
            rg = findViewById(R.id.rg);
            etName = findViewById(R.id.et_name);
            etPhone = findViewById(R.id.et_phone);
            etIdNum = findViewById(R.id.et_id_num);
            tvBirthday = findViewById(R.id.tv_birthday);
            tvBirthday.setOnClickListener(v -> {
                selectDate();
            });

            etUsername = findViewById(R.id.et_username);
            etPwd1 = findViewById(R.id.et_pwd_1);
            etPwd2 = findViewById(R.id.et_pwd_2);

            if (Constants.DEBUG) {
                etName.setText("张三");
                etUsername.setText("test1");
                etPwd1.setText("123123");
                etPwd2.setText("123123");
            }

            findViewById(R.id.btn_submit).setOnClickListener(v -> {
                if (TextUtils.isEmpty(etName.getText())) {
                    UiUtils.showError(context, "请填写姓名");
                    return;
                }
                if (TextUtils.isEmpty(etIdNum.getText())) {
                    UiUtils.showError(context, "请填写身份证号");
                    return;
                }
                if (TextUtils.isEmpty(etPhone.getText())) {
                    UiUtils.showError(context, "请填写手机号码");
                    return;
                }
                if (TextUtils.isEmpty(tvBirthday.getText())) {
                    UiUtils.showError(context, "请选择出生年月");
                    return;
                }
                if (TextUtils.isEmpty(etUsername.getText())) {
                    UiUtils.showError(context, "请填写用户名");
                    return;
                }
                if (TextUtils.isEmpty(etPwd1.getText()) || TextUtils.isEmpty(etPwd2.getText())) {
                    UiUtils.showError(context, "请输入密码");
                    return;
                }
                if (!etPwd1.getText().toString().equals(etPwd2.getText().toString())) {
                    UiUtils.showError(context, "两次输入的密码不一致");
                    return;
                }
                checkAccount();
            });
        }

        private void selectDate() {
            TimeUtil.selectDateTime(LoginActivity.this, "请选择出生年月", false, true, false, (d, view) -> {
                tvBirthday.setText(TimeUtil.getDateTimeText(d, "yyyy-MM-dd"));
            });
        }

        /*检查账号是否存在*/
        private void checkAccount() {

            Map<String, String> params = new HashMap<>();
            params.put("account", etUsername.getText().toString());
            NetUtils.startRequestNew(context, Constants.CHECK_ACCOUNT, params, result -> register());
        }

        /*注册*/
        private void register() {
            Map<String, String> params = new HashMap<>();
            params.put("account", etUsername.getText().toString());
            params.put("password", etPwd1.getText().toString());
            params.put("name", etName.getText().toString());
            params.put("phone", etPhone.getText().toString());
            params.put("id_num", etIdNum.getText().toString());
            params.put("sex", rg.getCheckedRadioButtonId() == R.id.rb_male ? "1" : "0");
            params.put("birthday", tvBirthday.getText().toString());
            NetUtils.startRequestNew(context, Constants.REGISTER, params, result -> {
                UiUtils.showSuccess(context, "注册成功");
                dismiss();
            });
        }
    }
}
