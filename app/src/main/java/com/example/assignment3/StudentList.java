package com.example.assignment3;

public class StudentList {


    private String  mName;
    private String mRollNo;
    private String mClass;

        public StudentList(String Name,String RollNo,String Class)
        {
            this.mName=Name;
            this.mRollNo=RollNo;
            this.mClass=Class;
        }


        public  String getName()
        {
            return mName;
        }


      public String getRollNo()
        {
            return mRollNo;
        }
          public String getmClass()
        {
            return mClass;
        }

}
