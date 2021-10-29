package www.test.com.medical_system.bean;

public class Case {
    /**
     * content : asdfadfadsf ';
     'sd  hg k s
     * date : 2020-04-24 16:03:35
     * department : 1
     * department_text : 内科
     * doctor_id : 2
     * doctor_name : doc1
     * id : 3
     * is_self : true
     * patient_id : 2
     * real_patient_id : 2
     * real_patient_name : 张三
     */

    private String content;
    private String date;
    private int department;
    private String department_text;
    private int doctor_id;
    private String doctor_name;
    private int id;
    private boolean is_self;
    private int patient_id;
    private int real_patient_id;
    private String real_patient_name;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public String getDepartment_text() {
        return department_text;
    }

    public void setDepartment_text(String department_text) {
        this.department_text = department_text;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIs_self() {
        return is_self;
    }

    public void setIs_self(boolean is_self) {
        this.is_self = is_self;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getReal_patient_id() {
        return real_patient_id;
    }

    public void setReal_patient_id(int real_patient_id) {
        this.real_patient_id = real_patient_id;
    }

    public String getReal_patient_name() {
        return real_patient_name;
    }

    public void setReal_patient_name(String real_patient_name) {
        this.real_patient_name = real_patient_name;
    }
}
