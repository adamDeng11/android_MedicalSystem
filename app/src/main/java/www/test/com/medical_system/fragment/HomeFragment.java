package www.test.com.medical_system.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.test.com.medical_system.R;
import www.test.com.medical_system.activity.AppointmentActivity;
import www.test.com.medical_system.activity.GuideActivity;
import www.test.com.medical_system.activity.IntroActivity;
import www.test.com.medical_system.activity.RecordListActivity;
import www.test.com.medical_system.utils.SPUtils;
import www.test.com.medical_system.utils.UiUtils;
/*首页*/
public class HomeFragment extends Fragment implements View.OnClickListener {

    private boolean isLogin;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isLogin = SPUtils.getPrefBoolean(getActivity(), "isLogin", false);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        view.findViewById(R.id.ll_yyjs).setOnClickListener(this);
        view.findViewById(R.id.ll_yygh).setOnClickListener(this);
        view.findViewById(R.id.ll_zzdz).setOnClickListener(this);
        view.findViewById(R.id.ll_mzjl).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_yyjs:
                startActivity(new Intent(getActivity(), IntroActivity.class));
                break;
            case R.id.ll_zzdz:
                startActivity(new Intent(getActivity(), GuideActivity.class));
                break;
            case R.id.ll_yygh:
                if (!isLogin) {
                    UiUtils.showKnowDialog(getContext(), "提示", "请先登录");
                    return;
                }
                startActivity(new Intent(getActivity(), AppointmentActivity.class));
                break;
            case R.id.ll_mzjl:
                if (!isLogin) {
                    UiUtils.showKnowDialog(getContext(), "提示", "请先登录");
                    return;
                }
                startActivity(new Intent(getActivity(), RecordListActivity.class));
                break;
        }
    }
}
