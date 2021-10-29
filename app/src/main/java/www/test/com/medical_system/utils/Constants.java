package www.test.com.medical_system.utils;

/**
 * 和后台的接口地址
 */
public class Constants {

    /*全局缓存token*/
    public final static  String TOKEN="token";
    /*后台交互token*/
    public final static  String PTOKEN="token";
    /*根地址名称*/
    public final static String BASE_URL="base_url";
    public static boolean DEBUG = false;
    public static boolean AT_HOME = false;

    /*登录验证*/
    public static String LOGIN = "/appLogin/doLogin";
    /*病人注册*/
    public static String REGISTER = "/appLogin/register";
    /*验证账号是否存在*/
    public static String CHECK_ACCOUNT = "/appLogin/checkAccount";
    /*修改密码*/
    public static String UPDATE_PWD = "/appUserInfo/modifyPassword";
    public static String LOGOUT = "/appLogin/logout";

    /*根据科室id查询所有医生*/
    public static String DOCTOR_LIST = "/appAppointment/getDocList";
    /*我的预约记录*/
    public static String APPOINTMENT_RECORD = "/appAppointment/myAppointmentList";
    /*根据医生id和日期按照时间段分组获取预约信息*/
    public static String GET_DOCTOR_TIME_LIST = "/appAppointment/getTimeList";
    /*提交挂号*/
    public static String MAKE_APPOINTMENT = "/appAppointment/appointment";
    /*根据病人id查询其他就诊人列表，仅返回其他就诊人id和名字*/
    public static String OTHERS_LIST_4_ORDER = "/appAppointment/relationPatientSel";

    /*其他就诊人列表*/
    public static String OTHERS_LIST = "/appUserInfo/relationPatientList";
    /*删除一个其他就诊人*/
    public static String DELETE_OTHERS = "/appUserInfo/deleteRelationPatient";
    /*新增其他就诊人保存*/
    public static String SAVE_OTHERS = "/appUserInfo/saveRelationPatient";
    /*根据病人id查看历史病例*/
    public static String CASE_LIST = "/appUserInfo/caseList";

    public static final String URL_KEY = "http://www.tuling123.com/openapi/api";
    public static final String APP_KEY = "75fb3f10bfd04db0976e1ebc72919729";//此处是你申请的Apikey


}
