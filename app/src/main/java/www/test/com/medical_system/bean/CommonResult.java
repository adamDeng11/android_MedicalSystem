package www.test.com.medical_system.bean;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import www.test.com.medical_system.utils.ValidateUtil;

/**
 * 网络返回的对象
 */
public class CommonResult {
    String state;//状态
    String msg;//描述
    String token;//临时标记，表明用户是否登录
    JSONObject data;
    JSONArray rows;
    JSONArray values;
    JSONArray value;
    int total;//array的数量

    public JSONArray getValue() {
        return value;
    }

    public void setValue(JSONArray value) {
        this.value = value;
    }

    public JSONArray getValues() {
        return values;
    }

    /*values转换成字符串*/
    public String getValuesString() {
        return ValidateUtil.isJaValid(values) ? JSON.toJSONString(values) : "";
    }
    /*value转换成字符串*/
    public String getValueString() {
        return ValidateUtil.isJaValid(value) ? JSON.toJSONString(value) : "";
    }

    public void setValues(JSONArray values) {
        this.values = values;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public JSONArray getRows() {
        return rows;
    }

    /*rows转换成字符串*/
    public String getRowsString() {
        return rows == null ? "" : JSON.toJSONString(rows);
    }

    public void setRows(JSONArray rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
