package www.test.com.medical_system.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BottomPopupView;

import java.util.HashMap;
import java.util.Map;

import www.test.com.medical_system.R;
import www.test.com.medical_system.activity.LoginActivity;
import www.test.com.medical_system.activity.OthersListActivity;
import www.test.com.medical_system.utils.ActivityManager;
import www.test.com.medical_system.utils.Constants;
import www.test.com.medical_system.utils.NetUtils;
import www.test.com.medical_system.utils.SPUtils;
import www.test.com.medical_system.utils.UiUtils;

/*个人中心*/
public class MineFragment extends Fragment implements View.OnClickListener {

    public Context context;

    public MineFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean isLogin = SPUtils.getPrefBoolean(getActivity(), "isLogin", false);
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        view.findViewById(R.id.btn_logout).setOnClickListener(this);
        view.findViewById(R.id.btn_update_pwd).setOnClickListener(this);
        view.findViewById(R.id.btn_manage_others).setOnClickListener(this);
        //未登录
        if (!isLogin) {
            view.findViewById(R.id.ll_button).setVisibility(View.GONE);
            view.findViewById(R.id.btn_login).setVisibility(View.VISIBLE);
            view.findViewById(R.id.btn_login).setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout:
                UiUtils.showConfirmDialog(context, getChildFragmentManager(),
                        "提示", "确定退出登录？", "确定", v2 -> logout());
                break;
            case R.id.btn_manage_others:
                startActivity(new Intent(context, OthersListActivity.class));
                break;
            case R.id.btn_login:
                startActivity(new Intent(context, LoginActivity.class));
                getActivity().finish();
                ActivityManager.finishAll();
                break;
            case R.id.btn_update_pwd:
                new XPopup.Builder(context)
                        .asCustom(new UpdatePwdPopup()).show();
                break;
        }
    }

    private void logout() {
        startActivity(new Intent(context, LoginActivity.class));
        getActivity().finish();
        ActivityManager.finishAll();
    }

    public class UpdatePwdPopup extends BottomPopupView {

        private EditText etPwd1;
        private EditText etPwd2;
        private EditText etOld;

        public UpdatePwdPopup() {
            super(context);
        }

        @Override
        protected int getImplLayoutId() {
            return R.layout.popup_update_pwd;
        }

        @Override
        protected void onCreate() {
            super.onCreate();
            etOld = findViewById(R.id.et_pwd_old);
            etPwd1 = findViewById(R.id.et_pwd_1);
            etPwd2 = findViewById(R.id.et_pwd_2);
            findViewById(R.id.btn_submit).setOnClickListener(v -> {
                if (TextUtils.isEmpty(etOld.getText())) {
                    UiUtils.showInfo(context, "请先输入旧密码");
                    return;
                }
                if (TextUtils.isEmpty(etPwd1.getText()) || TextUtils.isEmpty(etPwd2.getText())) {
                    UiUtils.showInfo(context, "请输入新密码");
                    return;
                }
                updatePwd();
            });
        }

        private void updatePwd() {
            Map<String, String> params = new HashMap<>();
            params.put("old_password", etOld.getText().toString());
            params.put("password", etPwd1.getText().toString());
            params.put("confirm_password", etPwd2.getText().toString());
            NetUtils.startRequest(context, Constants.UPDATE_PWD, params, result -> {
                UiUtils.showSuccess(context, "密码修改成功,请重新登录");
                dismiss();
                startActivity(new Intent(context, LoginActivity.class));
                getActivity().finish();
                ActivityManager.finishAll();
            });
        }
    }
}
