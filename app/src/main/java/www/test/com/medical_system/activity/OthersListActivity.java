package www.test.com.medical_system.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BottomPopupView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.test.com.medical_system.R;
import www.test.com.medical_system.bean.Bean;
import www.test.com.medical_system.utils.Constants;
import www.test.com.medical_system.utils.NetUtils;
import www.test.com.medical_system.utils.TimeUtil;
import www.test.com.medical_system.utils.UiUtils;
import www.test.com.medical_system.utils.ValidateUtil;

/**
 * 其他就诊人
 */
public class OthersListActivity extends BaseActivity implements View.OnClickListener {

    /*其他就诊人列表*/
    private LinearLayout llContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_others_list);
        setTitle("管理其他就诊人");
        setRightIcon(R.mipmap.ic_add_white);
        llContent = findViewById(R.id.ll_content);
        getOthersList();
    }

    /*得到其他就诊人列表*/
    private void getOthersList() {
        Map<String, String> params = new HashMap<>();
        params.put("pageNumber", 1 + "");
        params.put("pageSize", 100 + "");
        NetUtils.request(context, Constants.OTHERS_LIST, params, result -> {
            llContent.removeAllViews();
            String rows = result.getRowsString();
            //Log.i("什么：",rows);
            if (ValidateUtil.isStringValid(rows)) {
                List<Bean> list = JSON.parseArray(rows, Bean.class);
                for (Bean bean : list) {
                    addOthersItem(bean);
                }
            } else {
                UiUtils.showKnowDialog(context, "提示", "查无其他就诊人，您可以点击右上角进行添加。");
            }

//            NoteListData data = JSON.toJavaObject(result, NoteListData.class);
//            if (data.getTotal() > 0) {
//                llContent.removeAllViews();
//                List<Bean> rows = data.getRows();
//                for (int i = 0; i < rows.size(); i++) {
//                    Bean bean = rows.get(i);
//                    addOthersItem(bean);
//                }
//            } else {
//                UiUtils.showInfo(context, "暂无日志");
//            }
        });
    }

    /*添加每一个其他就诊人*/
    private void addOthersItem(Bean bean) {
        View view = getLayoutInflater().inflate(R.layout.item_others, null);
        ((TextView) view.findViewById(R.id.tv_name)).setText(bean.getName());
        ((TextView) view.findViewById(R.id.tv_info)).setText("身份证号:" + bean.getId_num() + "   手机号码:" + bean.getPhone());
        ImageView iv = view.findViewById(R.id.iv_icon);
        Glide.with(context).load(bean.getSex() == 1 ? R.mipmap.ic_male : R.mipmap.ic_female).into(iv);
        view.findViewById(R.id.tv_delete).setOnClickListener(v -> deleteOthers(bean.getId()));
        UiUtils.setWidthAndHeight(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llContent.addView(view);
    }

    /*删除一个其他就诊人*/
    private void deleteOthers(int id) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id + "");
        NetUtils.startRequest(context, Constants.DELETE_OTHERS, params, result -> {
            UiUtils.showSuccess(context, "删除成功");
            getOthersList();
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_right:
                new XPopup.Builder(context)
                        .asCustom(new
                                AddOtherPopup(context)).show();
                break;
        }
    }

    public class AddOtherPopup extends BottomPopupView {

        private Context context;
        private EditText etName;
        private EditText etId;
        private TextView tvBirthday;
        private RadioGroup rg;
        private EditText etPhone;

        public AddOtherPopup(Context context) {
            super(context);
            this.context = context;
        }

        @Override
        protected int getImplLayoutId() {
            return R.layout.popup_add_others;
        }

        @Override
        protected void onCreate() {
            super.onCreate();

            etName = findViewById(R.id.et_name);
            etPhone = findViewById(R.id.et_phone);
            etId = findViewById(R.id.et_id);
            tvBirthday = findViewById(R.id.tv_birthday);
            tvBirthday.setOnClickListener(v -> TimeUtil.selectDateTime(OthersListActivity.this, "请选择出生年月", false, true, false, (d, view) -> tvBirthday.setText(TimeUtil.getDateTimeText(d, "yyyy-MM-dd"))));
            rg = findViewById(R.id.rg);

            findViewById(R.id.btn_submit).setOnClickListener(v -> {
                if (TextUtils.isEmpty(etName.getText())) {
                    UiUtils.showInfo(context, "请填写姓名");
                    return;
                }
                if (TextUtils.isEmpty(etPhone.getText())) {
                    UiUtils.showInfo(context, "请填写手机号码");
                    return;
                }
                if (TextUtils.isEmpty(etId.getText())) {
                    UiUtils.showInfo(context, "请填写身份证号");
                    return;
                }
                if (TextUtils.isEmpty(tvBirthday.getText())) {
                    UiUtils.showInfo(context, "请选择出生年月");
                    return;
                }
                saveOthers();
            });
        }


        /*保存其他就诊人*/
        private void saveOthers() {
            Map<String, String> params = new HashMap<>();
            params.put("name", etName.getText().toString());
            params.put("sex", rg.getCheckedRadioButtonId() == R.id.rb_male ? "1" : "0");
            params.put("id_num", etId.getText().toString());
            params.put("phone", etPhone.getText().toString());
            params.put("birthday", tvBirthday.getText().toString());
            NetUtils.startRequest(context, Constants.SAVE_OTHERS, params, result -> {
                dismiss();
                UiUtils.showSuccess(context, "新增成功");
                getOthersList();
            });
        }
    }
}
