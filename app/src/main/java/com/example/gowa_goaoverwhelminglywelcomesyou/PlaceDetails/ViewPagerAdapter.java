package com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> listFragment= new ArrayList<>();
    private final List<String> listTitle=new ArrayList<>();


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return listFragment.get(i);
    }

    @Override
    public int getCount() {
        return listTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }


    public void addFragment(Fragment fragment, String title){
        listFragment.add(fragment);
        listTitle.add(title);
    }

    public void clear(){
        listFragment.clear();
        listTitle.clear();
    }
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    /*@Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
*/

}
