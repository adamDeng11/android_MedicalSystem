package www.test.com.medical_system.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import www.test.com.medical_system.fragment.HealFragment;
import www.test.com.medical_system.fragment.HomeFragment;
import www.test.com.medical_system.fragment.IntelligentFragment;
import www.test.com.medical_system.fragment.MineFragment;


public class HomePageAdapter extends FragmentPagerAdapter {
    private Fragment mCurrentFragment;
    private Context context;

    public HomePageAdapter(Context context, android.support.v4.app.FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
            case 0:
                return new HomeFragment();
            case 1:
                return new HealFragment();
            case 2:
                return new IntelligentFragment();
            case 3:
                return new MineFragment();
        }
        return new HomeFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentFragment = (Fragment) object;
        super.setPrimaryItem(container, position, object);
    }

    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }
}

