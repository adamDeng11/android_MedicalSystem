package www.test.com.medical_system.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import www.test.com.medical_system.R;
import www.test.com.medical_system.adapter.HealRecycleAdapter;
import www.test.com.medical_system.bean.GoodsEntity;
import www.test.com.medical_system.bean.Heal;
import www.test.com.medical_system.callback.HealService;

/*健康新闻*/
public class HealFragment extends Fragment implements View.OnClickListener {

    private View view;
    public RecyclerView recyclerView;//定义RecyclerView
    //定义以goodsentity实体类为对象的数据集合
    private ArrayList<Heal.NewslistBean> goodsEntityList = new ArrayList<Heal.NewslistBean>();
    //自定义recyclerveiw的适配器
    private HealRecycleAdapter healRecycleAdapter;
    private HealService healService;
    private final String BASE_URL="https://api.tianapi.com/";



    public Context context;

    public HealFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_heal, container, false);
        Retrofit retrofit = new Retrofit.Builder()  //创建Retrofit实例
                .baseUrl(BASE_URL)    //url头部
                .addConverterFactory(GsonConverterFactory.create()) //返回的数据经过转换工厂转换成我们想要的数据，最常用的就是Gson
                .build();   //构建实例
        healService = retrofit.create(HealService.class);   //使用retrofit的create()方法实现请求接口类
        //对recycleview进行配置
        initRecyclerView();
        request();
        //模拟数据
        //initData();

    return view;

    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void request(){
        Call<Heal> healCall=healService.getHealth("f6025b326e9d9dcdde83b5cf72638ca0",30);
        healCall.enqueue(new Callback<Heal>() {
            @Override
            public void onResponse(Call<Heal> call, Response<Heal> response) {
                Log.e("啊啊啊啊啊啊",response.toString());
                Heal heal=response.body();
                goodsEntityList.addAll(heal.getNewslist());
                healRecycleAdapter.refresh(goodsEntityList);

            }

            @Override
            public void onFailure(Call<Heal> call, Throwable t) {
                Log.e("啊啊啊啊啊啊",goodsEntityList.toString());

            }
        });

    }

    private void initRecyclerView() {
        //获取RecyclerView
        recyclerView=(RecyclerView) view.findViewById(R.id.heals_recycleView);
        //创建adapter
        healRecycleAdapter = new HealRecycleAdapter(getActivity(), goodsEntityList);
        //给RecyclerView设置adapter
        recyclerView.setAdapter(healRecycleAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
        healRecycleAdapter.setOnItemClickListener(new HealRecycleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, Heal.NewslistBean data) {

            }


        });
    }

    /*private void initData() {
        for (int i = 0; i < 10; i++) {
            GoodsEntity goodsEntity = new GoodsEntity();
            goodsEntity.setGoodsName("模拟数据" + i);
            goodsEntity.setGoodsPrice("100" + i);
            goodsEntityList.add(goodsEntity);
        }
    }*/


}
