package com.developers.roadsecure;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Jasbir Singh on 3/17/2016.
 */
public class ListAdapter extends BaseAdapter{

   Context mcontext;
    public ArrayList<HospItem> hospItem;

    public ListAdapter(Context context, ArrayList<HospItem> hospItem) {
        this.mcontext = context;
        this.hospItem = hospItem;
    }

    @Override
    public int getCount() {
        return hospItem.size();
    }

    @Override
    public Object getItem(int i) {
        return hospItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
