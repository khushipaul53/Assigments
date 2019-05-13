package com.example.recyclerview;

public class recycler {

    public int count;
    private String  mName;
private String mAge;
private String mPhone;
  float mrating;
//private int mImage;


public recycler(String Name,String Age,String Phone)
{
   this.mName=Name;
   this.mAge=Age;
    this.mPhone=Phone;
}
    public  String getmName()
    {
        return mName;
    }
    public String getmAge()
    {
        return mAge;
    }
    public String getmPhone(){return mPhone;}
    void setRating(float rating)

    {
        this.mrating=rating;
    }



}
