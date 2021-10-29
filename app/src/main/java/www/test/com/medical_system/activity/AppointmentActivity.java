package www.test.com.medical_system.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.OnSelectListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.test.com.medical_system.R;
import www.test.com.medical_system.bean.AppointmentRecord;
import www.test.com.medical_system.bean.Bean;
import www.test.com.medical_system.bean.DoctorTime;
import www.test.com.medical_system.utils.Constants;
import www.test.com.medical_system.utils.DensityUtil;
import www.test.com.medical_system.utils.NetUtils;
import www.test.com.medical_system.utils.TimeUtil;
import www.test.com.medical_system.utils.UiUtils;
import www.test.com.medical_system.utils.Utils;
import www.test.com.medical_system.utils.ValidateUtil;

public class AppointmentActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout llLeft;
    private LinearLayout llRight;
    private BasePopupView popup;//显示时间弹出框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_appointment);
        setTitle("预约挂号");
        setRightText("预约记录");
        initLeft();
        initRight();
    }

    /*增加右侧医生列表*/
    private void initRight() {
        llRight = findViewById(R.id.ll_right);
        getDoctorList(1);
    }

    private void initLeft() {
        llLeft = findViewById(R.id.ll_left);
        addLeftItem("内科", 1);
        addLeftItem("外科", 2);
        addLeftItem("儿科", 3);
        addLeftItem("皮肤科", 4);
        addLeftItem("中西医结合科", 5);
        addLeftItem("眼科", 6);
        addLeftItem("耳鼻喉科", 7);
        addLeftItem("口腔科", 8);
        addLeftItem("急诊科", 9);
        addLeftItem("临床营养科", 10);
    }

    private void addLeftItem(String text, int id) {
        View view = getLayoutInflater().inflate(R.layout.item_left, null);
        TextView tvName = view.findViewById(R.id.tv_name);
        tvName.setText(text);
        if (text.equals("内科")) {
            tvName.setTextColor(0xff4da8ff);
        }
        view.setOnClickListener(v -> {
            for (int i = 0; i < llLeft.getChildCount(); i++) {
                View child = llLeft.getChildAt(i);
                TextView tv = child.findViewById(R.id.tv_name);
                if (tv.getText().toString().equals(text)) {
                    tv.setTextColor(0xff4da8ff);
                    child.setBackgroundColor(0xfff8f8f8);
                } else {
                    tv.setTextColor(0xff666666);
                    child.setBackgroundColor(0xffffffff);
                }
            }
            getDoctorList(id);
        });
        llLeft.addView(view);
    }

    /*根据科室id查询所有医生，并显示在右侧*/
    private void getDoctorList(int departmentId) {
        Map<String, String> params = new HashMap<>();
        params.put("department", departmentId + "");
        NetUtils.request(context, Constants.DOCTOR_LIST, params, result -> {
            String values = result.getValuesString();
            if (ValidateUtil.isStringValid(values)) {
                llRight.removeAllViews();
                List<Bean> list = JSON.parseArray(result.getValuesString(), Bean.class);
                for (int i = 0; i < list.size(); i++ ) {
                    addRightItem(departmentId, list.get(i));
                }
            } else {
                showEmpty();
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.tv_right) {
            getRecord();
        }
    }

    /*预约记录*/
    private void getRecord() {
        Map<String, String> params = new HashMap<>();
        params.put("pageNumber", 1 + "");
        params.put("pageSize", 500 + "");
        NetUtils.request(context, Constants.APPOINTMENT_RECORD, params, result -> {
            String rows = result.getRowsString();
            if (ValidateUtil.isStringValid(rows)) {
                List<AppointmentRecord> list = JSON.parseArray(rows, AppointmentRecord.class);
                String[] arr = Utils.list2StringArray(list);
                Utils.showSingleSelector(context, "预约记录", arr, null, -1, true, null);
            } else {
                UiUtils.showKnowDialog(context, "提示", "您暂无预约挂号记录");
            }
        });
    }

    /*查询不到医生，显示空*/
    private void showEmpty() {
        llRight.removeAllViews();
        View viewEmpty = getLayoutInflater().inflate(R.layout.view_empty_new, null);
        llRight.addView(viewEmpty);
    }


    /*右侧添加单个医生*/
    private void addRightItem(int departmentId, Bean data) {
        View view = getLayoutInflater().inflate(R.layout.item_doc, null);
        ((TextView) view.findViewById(R.id.tv_name)).setText(data.getName());
        ((TextView) view.findViewById(R.id.tv_time)).setText(data.getDate());
        view.findViewById(R.id.btn_self).setOnClickListener(v -> {
            Map<String, String> params = new HashMap<>();
            params.put("department", departmentId + "");
            params.put("doctor", data.getId() + "");
            params.put("isSelf", true + "");
            selectDate(params);
        });
        view.findViewById(R.id.btn_others).setOnClickListener(v -> {
            Map<String, String> params = new HashMap<>();
            params.put("department", departmentId + "");
            params.put("doctor", data.getId() + "");
            params.put("isSelf", false + "");
            getOthers(params);
        });
//        if (true) {
//            btn.setOnClickListener(v -> appointment(data.getId()));
//        } else {
//            btn.setVisibility(View.GONE);
//        }
        llRight.addView(view);
    }

    /*选择挂号时间*/
    private void selectDate(Map<String, String> params) {
        TimeUtil.selectDateTime(AppointmentActivity.this, "请先选择预约日期", false, true, false, (d, view) -> {
            String date = TimeUtil.getDateTimeText(d, "yyyy-MM-dd");
            params.put("date", date);
            selectTime(params);
        });
    }

    /*选择时间*/
    private void selectTime(Map<String, String> params) {
        Map<String, String> p = new HashMap<>();
        p.put("doctor", params.get("doctor"));
        p.put("date", params.get("date"));
        NetUtils.request(context, Constants.GET_DOCTOR_TIME_LIST, p, result -> {
            String values = result.getValuesString();
            if (ValidateUtil.isStringValid(values)) {
                List<DoctorTime> list = JSON.parseArray(values, DoctorTime.class);
                String[] arr = Utils.list2StringArray(list);
                popup = new XPopup.Builder(context).autoDismiss(false).maxHeight(DensityUtil.dip2px(context, 550)).asBottomList("请选择", arr, null, -1, new OnSelectListener() {
                    @Override
                    public void onSelect(int position, String text) {
                        int remainingNum = list.get(position).getRemainingNum();
                        if (remainingNum > 0) {
                            popup.dismiss();
                            int timeValue = list.get(position).getTimeValue();
                            params.put("time", timeValue + "");
                            appointment(params);
                        } else {
                            UiUtils.showKnowDialog(context, "提示", "该时段剩余预约数为0，请选择其他预约时段。");
                        }
                    }
                }).show();
            } else {
                UiUtils.showKnowDialog(context, "提示", "该天无可预约时间段，请重新选择日期。");
            }
        });
    }

    /*获取其他就诊人*/
    private void getOthers(Map<String, String> params) {
        NetUtils.request(context, Constants.OTHERS_LIST_4_ORDER, null, result -> {
            String values = result.getValuesString();
            if (ValidateUtil.isStringValid(values)) {
                List<Bean> list = JSON.parseArray(values, Bean.class);
                String[] arr = Utils.list2StringArray(list);
                Utils.showSingleSelector(context, "请选择就诊人", arr, null, -1, true, (int position, String text) -> {
                    params.put("patient", list.get(position).getId() + "");
                    selectDate(params);
                });
            } else {
                UiUtils.showKnowDialog(context, "提示", "查无其他就诊人，请先在个人中心添加其他就诊人。");
            }
        });
    }

    /*预约医生*/
    private void appointment(Map<String, String> params) {
        NetUtils.request(context, Constants.MAKE_APPOINTMENT, params, result -> UiUtils.showSuccess(context, "预约成功"));
    }
}
