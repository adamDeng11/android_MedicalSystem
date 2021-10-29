package www.test.com.medical_system.utils;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import www.test.com.medical_system.bean.Bean;
import www.test.com.medical_system.bean.CommonResult;
import www.test.com.medical_system.bean.Result2;
import www.test.com.medical_system.bean.ResultNew;
import www.test.com.medical_system.bean.UploadResult;
import www.test.com.medical_system.callback.Callback;
import www.test.com.medical_system.callback.RequestCallback;
import www.test.com.medical_system.callback.RequestCallback2;
import www.test.com.medical_system.callback.RequestCallbackNew;

/**
 * 网络访问类
 */
public class NetUtils {

    /**
     * 访问后台
     * @param context
     * @param url
     * @param params
     * @param callback
     */
    public static void request(Context context, String url, Map<String, String> params, Callback callback) {
        BasePopupView loadingView = new XPopup.Builder(context).asLoading("网络请求中...").show();
        //从缓存中获取
        String token = SPUtils.getPrefString(context, "token", "");
        //如果有token,把它放到参数里
        if (ValidateUtil.isStringValid(token)) {
            if (params == null) {
                params = new HashMap<>();
            }
            params.put("token", token);
        }
        //访问后台
        OkHttpUtils
                .post()
                .url(getUrl(context, url))
//                .headers(header)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        loadingView.dismiss();
                        UiUtils.showError(context, "请求失败：" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        loadingView.dismiss();
                        CommonResult result = JSON.parseObject(response, CommonResult.class);
                        if (result.getState().equals("ok")) {
                            //访问成功回调，返回结果回传
                            callback.fun(result);
                        } else {
                            UiUtils.showKnowDialog(context, "请求失败", result.getMsg());
                        }
                    }
                });
    }

    public static void uploadFile(Context context, String fileName, String filePath, www.test.com.medical_system.callback.StringCallback callback) {
        BasePopupView loadingView = new XPopup.Builder(context).asLoading("正在上传...").show();

        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient client = httpBuilder
                .readTimeout(50, TimeUnit.SECONDS)
                .connectTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .build();
        OkHttpUtils.initClient(client);


        OkHttpUtils
                .post()
                .addFile("att_file", fileName, new File(filePath))
                .url(SPUtils.getPrefString(context, "base_url", "") + "/upload/fileupload")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        loadingView.dismiss();
                        UiUtils.showKnowDialog(context, "上传失败", e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        loadingView.dismiss();
                        UploadResult result = JSON.parseObject(response, UploadResult.class);
                        if (result.getState().equals("ok")) {
                            List<Bean> value = result.getValue();
                            if (ValidateUtil.isListValid(value)) {
                                UiUtils.showSuccess(context, "上传成功");
                                callback.fun(value.get(0).getPath());
                            }
                        } else {
                            UiUtils.showKnowDialog(context, "上传失败", result.getMsg());
                        }
                    }
                });
    }

    public static void startRequest(Context context, String url, Map<String, String> params, RequestCallback callback) {
//        ACProgressFlower loadingView = UiUtils.showLoadingDialog(context, "正在请求...");
        BasePopupView loadingView = new XPopup.Builder(context).asLoading("网络请求中...").show();
        String token = SPUtils.getPrefString(context, "token", "");
        if (ValidateUtil.isStringValid(token)) {
            if (params == null) {
                params = new HashMap<>();
            }
            params.put("token", token);
        }

//        Map<String, String> header = new HashMap<>();
//        String sessionId = SPUtils.getPrefString(context, "sessionId", "");
//        if (ValidateUtil.isStringValid(sessionId)){
//            header.put("Cookie", "JSESSIONID=" + sessionId);
//        }
        OkHttpUtils
                .post()
                .url(getUrl(context, url))
//                .headers(header)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        loadingView.dismiss();
                        UiUtils.showError(context, "请求失败：" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        loadingView.dismiss();
                        ResultNew result = JSON.parseObject(response, ResultNew.class);
                        if (result.getState().equals("ok")) {
                            callback.fun(result.getData());
                        } else {
                            UiUtils.showKnowDialog(context, "请求失败", result.getMsg());
                        }
                    }
                });
    }

    public static void startRequestNew(Context context, String url, Map<String, String> params, RequestCallbackNew callback) {
//        ACProgressFlower loadingView = UiUtils.showLoadingDialog(context, "正在请求...");
        BasePopupView loadingView = new XPopup.Builder(context).asLoading("网络请求中...").show();
        String token = SPUtils.getPrefString(context, "token", "");
        if (ValidateUtil.isStringValid(token)) {
            if (params == null) {
                params = new HashMap<>();
            }
            params.put("token", token);
        }

//        Map<String, String> header = new HashMap<>();
//        String sessionId = SPUtils.getPrefString(context, "sessionId", "");
//        if (ValidateUtil.isStringValid(sessionId)){
//            header.put("Cookie", "JSESSIONID=" + sessionId);
//        }
        OkHttpUtils
                .post()
                .url(getUrl(context, url))
//                .headers(header)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        loadingView.dismiss();
                        UiUtils.showError(context, "请求失败：" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        loadingView.dismiss();
                        Map<String, Object> result = JSON.parseObject(response, HashMap.class);
                        if (result.get("state").equals("ok")) {
                            callback.fun(result);
                        } else {
                            UiUtils.showKnowDialog(context, "请求失败11", (String) result.get("msg"));
                        }
                    }
                });
    }

    //给url加上根地址
    private static String getUrl(Context context, String url) {
        String baseUrl = SPUtils.getPrefString(context, "base_url", "");
        if (ValidateUtil.isStringValid(baseUrl)) {
            if (baseUrl.endsWith("/")) {
                baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
            }
        }
        return baseUrl + url;
    }
}
