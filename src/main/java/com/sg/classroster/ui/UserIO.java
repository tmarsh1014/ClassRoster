package com.sg.classroster.ui;

public interface UserIO {

    //can print a msg
    void print(String msg);

    //all the different ways we can get data

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max); //specifically want a double between min and max

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max); //specifically want a float between min and max

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    String readString(String prompt);
}
