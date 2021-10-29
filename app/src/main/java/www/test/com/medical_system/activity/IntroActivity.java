package www.test.com.medical_system.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import www.test.com.medical_system.R;
import www.test.com.medical_system.fragment.IntroFragment;

public class IntroActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_intro);
        setTitle("医院介绍");

        Bundle bundle0 = new Bundle();
        bundle0.putInt("type", 0);
        Bundle bundle1 = new Bundle();
        bundle1.putInt("type", 1);
        Bundle bundle2 = new Bundle();
        bundle2.putInt("type", 2);
        Bundle bundle3 = new Bundle();
        bundle3.putInt("type", 3);
        Bundle bundle4 = new Bundle();
        bundle4.putInt("type", 4);

        FragmentPagerItems fragmentPagerItems = FragmentPagerItems.with(context)
                .add("预约挂号流程", IntroFragment.class, bundle0)
                .add("门诊取药流程", IntroFragment.class, bundle1)
                .add("门诊楼层分布", IntroFragment.class, bundle2)
                .add("停车场分布", IntroFragment.class, bundle3)
                .add("名医介绍", IntroFragment.class, bundle4)
                .create();
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), fragmentPagerItems);
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        SmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }
}
