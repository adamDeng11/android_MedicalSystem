package www.test.com.medical_system.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.test.com.medical_system.R;
import www.test.com.medical_system.bean.Case;
import www.test.com.medical_system.utils.Constants;
import www.test.com.medical_system.utils.NetUtils;
import www.test.com.medical_system.utils.UiUtils;
import www.test.com.medical_system.utils.ValidateUtil;

/**
 * 门诊记录
 */
public class RecordListActivity extends BaseActivity implements View.OnClickListener {

    /*门诊记录列表*/
    private LinearLayout llContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_record_list);
        setTitle("我的门诊记录");
        llContent = findViewById(R.id.ll_content);
        getRecordList(true, null);
    }

    /*获取问诊记录列表*/
    private void getRecordList(boolean isSelf, Integer patientId) {
        Map<String, String> params = new HashMap<>();
        params.put("pageSize", 200 + "");
        params.put("pageNumber", 1 + "");
        params.put("isSelf", isSelf + "");
        if (!isSelf) {
            params.put("patient", patientId + "");
        }
        NetUtils.request(context, Constants.CASE_LIST, params, result -> {
            String rowsString = result.getRowsString();
            if (ValidateUtil.isStringValid(rowsString)) {
                List<Case> list = JSON.parseArray(rowsString, Case.class);
                for (Case bean : list) {
                    addRecord(bean);
                }
            } else {
                UiUtils.showKnowDialog(context, "提示", "您暂无门诊记录");
            }
        });
    }

    /*每个问诊记录小块*/
    private void addRecord(Case bean) {
        View view = getLayoutInflater().inflate(R.layout.item_record, null);
        ((TextView) view.findViewById(R.id.tv_doc)).setText(bean.getDoctor_name());
        ((TextView) view.findViewById(R.id.tv_dept)).setText(bean.getDepartment_text());
        ((TextView) view.findViewById(R.id.tv_result)).setText(bean.getContent());
        ((TextView) view.findViewById(R.id.tv_date)).setText(bean.getDate());
//        UiUtils.setWidthAndHeight(view, LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(context, 55));
        llContent.addView(view);
    }
}
