package com.github.dddpaul.netcat;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.Locale;

public class SectionsPagerAdapter extends FragmentPagerAdapter
{
    private MainActivity activity;
    private SparseArray<Fragment> registeredFragments = new SparseArray<>( );

    public SectionsPagerAdapter( MainActivity mainActivity, FragmentManager fm )
    {
        super( fm );
        this.activity = mainActivity;
    }

    @Override
    public Fragment getItem( int position )
    {
        switch( position ) {
            case 0:
                return MainFragment.newInstance();
            case 1:
                return ResultFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return 2;
    }

    @Override
    public CharSequence getPageTitle( int position )
    {
        Locale l = Locale.getDefault();
        switch( position ) {
            case 0:
                return activity.getString( R.string.title_section1 ).toUpperCase( l );
            case 1:
                return activity.getString( R.string.title_section2 ).toUpperCase( l );
        }
        return null;
    }

    @Override
    public Object instantiateItem( ViewGroup container, int position )
    {
        Fragment fragment = ( Fragment ) super.instantiateItem( container, position );
        registeredFragments.put( position, fragment );
        return fragment;
    }

    @Override
    public void destroyItem( ViewGroup container, int position, Object object )
    {
        registeredFragments.remove( position );
        super.destroyItem( container, position, object );
    }

    public Fragment getRegisteredFragment( int position )
    {
        return registeredFragments.get( position );
    }

}
