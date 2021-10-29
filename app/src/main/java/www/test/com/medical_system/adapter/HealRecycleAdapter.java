package www.test.com.medical_system.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


import www.test.com.medical_system.R;
import www.test.com.medical_system.bean.GoodsEntity;
import www.test.com.medical_system.bean.Heal;

/**
 * Created by adamDeng
 * on 2021-09-22
 */
public class HealRecycleAdapter extends RecyclerView.Adapter<HealRecycleAdapter.myViewHodler> {
    private Context context;
    private ArrayList<Heal.NewslistBean> healsEntityList;

    //创建构造函数
    public HealRecycleAdapter(Context context, ArrayList<Heal.NewslistBean> goodsEntityList) {
        //将传递过来的数据，赋值给本地变量
        this.context = context;//上下文
        this.healsEntityList = goodsEntityList;//实体类数据ArrayList
    }

    /**
     * 创建viewhodler，相当于listview中getview中的创建view和viewhodler
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public myViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建自定义布局
        View itemView = View.inflate(context, R.layout.item_heals, null);
        return new myViewHodler(itemView);
    }

    /**
     * 绑定数据，数据与view绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(myViewHodler holder, int position) {
        //根据点击位置绑定数据
        Heal.NewslistBean data = healsEntityList.get(position);
//        holder.mItemGoodsImg;
        holder.mItemGoodsName.setText(data.getTitle());//获取实体类中的name字段并设置
        holder.mItemGoodsPrice.setText(data.getCtime());//获取实体类中的price字段并设置
        Glide.with(context).load(data.getPicUrl()).into(holder.mItemGoodsImg);
    }
    public void refresh(List<Heal.NewslistBean> list){
        //这个方法是我们自己手写的，主要是对适配器的一个刷新
        this.healsEntityList.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 得到总条数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return healsEntityList.size();
    }

    //自定义viewhodler
    class myViewHodler extends RecyclerView.ViewHolder {
        private ImageView mItemGoodsImg;
        private TextView mItemGoodsName;
        private TextView mItemGoodsPrice;

        public myViewHodler(View itemView) {
            super(itemView);
            mItemGoodsImg = (ImageView) itemView.findViewById(R.id.heals_photo);
            mItemGoodsName = (TextView) itemView.findViewById(R.id.heals_name);
            mItemGoodsPrice = (TextView) itemView.findViewById(R.id.heals_time);
            //点击事件放在adapter中使用，也可以写个接口在activity中调用
            //方法一：在adapter中设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //可以选择直接在本位置直接写业务处理
                    //Toast.makeText(context,"点击了xxx",Toast.LENGTH_SHORT).show();
                    //此处回传点击监听事件
                    if(onItemClickListener!=null){
                        onItemClickListener.OnItemClick(v, healsEntityList.get(getLayoutPosition()));
                    }
                }
            });

        }
    }

    /**
     * 设置item的监听事件的接口
     */
    public interface OnItemClickListener {
        /**
         * 接口中的点击每一项的实现方法，参数自己定义
         *
         * @param view 点击的item的视图
         * @param data 点击的item的数据
         */
        public void OnItemClick(View view, Heal.NewslistBean data);
    }

    //需要外部访问，所以需要设置set方法，方便调用
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

