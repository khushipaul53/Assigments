package com.example.assignment5.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentList implements Parcelable {

    private String mName;
    private String mRollno;
    private String mStandard;


    //Empty Constructor.
    public StudentList() {
    }

    public StudentList(String Name, String Rollno, String Class) {
        this.mName = Name;
        this.mRollno = Rollno;
        this.mStandard = Class;

    }

    protected StudentList(Parcel in) {
        mName = in.readString();
        mRollno = in.readString();
        mStandard = in.readString();

    }

    public static final Creator<StudentList> CREATOR = new Creator<StudentList>() {
        @Override
        public StudentList createFromParcel(Parcel in) {
            return new StudentList(in);
        }

        @Override
        public StudentList[] newArray(int size) {
            return new StudentList[size];
        }
    };

    //Getter Methods.
    public String getName() {
        return mName;
    }

    public String getRollno() {
        return mRollno;
    }

    public String getmStandard() {
        return mStandard;
    }


    //Setter Methods.
    public void setName(String Name) {
        this.mName = Name;
    }

    public void setRollno(String Rollno) {
        this.mRollno = Rollno;
    }

    public void setStandard(String standard) {
        this.mStandard =standard;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mRollno);
        dest.writeString(mStandard);

    }
}

