package www.test.com.medical_system.bean;


import com.alibaba.fastjson.JSONArray;

public class Result2 {
    int code;
    String msg;
    JSONArray data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONArray getData() {
        return data;
    }

    public void setData(JSONArray data) {
        this.data = data;
    }
}
