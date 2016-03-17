package com.developers.roadsecure;

/**
 * Created by Jasbir Singh on 3/17/2016.
 */
public class HospItem {
    String HospName;
    public HospItem(String HospName)
    {
        this.HospName=HospName;
    }

    public String getName()
    {
     return HospName;
    }
    public void setName(String HospName)
    {
        this.HospName=HospName;
    }

}
