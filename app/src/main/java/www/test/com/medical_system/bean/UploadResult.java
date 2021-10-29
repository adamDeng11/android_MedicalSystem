package www.test.com.medical_system.bean;

import java.util.List;

public class UploadResult {
    String state;
    String msg;
    List<Bean> value;

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

    public List<Bean> getValue() {
        return value;
    }

    public void setValue(List<Bean> value) {
        this.value = value;
    }
}
