package com.sg.classroster.ui;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {

    Scanner console = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String prompt) {
        boolean invalidInput = true;
        double num = 0;

        while (invalidInput){
            try {
                //print the message prompt
                String stringValue = this.readString(prompt);
                //get the input line, and try and parse
                num = Double.parseDouble(stringValue);
                invalidInput = false; //or you can use break
            } catch (NumberFormatException e){
                //if it explodes, it'll go here and do this
                this.print("Input error. Please try again.");
            }
        }
        return num;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        return 0;
    }

    @Override
    public float readFloat(String prompt) {
        boolean invalidInput = true;
        float num = 0;

        while (invalidInput){
            try {
                //print the message prompt
                String stringValue = this.readString(prompt);
                //get the input line, and try and parse
                num = Float.parseFloat(stringValue);
                invalidInput = false; //or you can use break
            } catch (NumberFormatException e){
                //if it explodes, it'll go here and do this
                this.print("Input error. Please try again.");
            }
        }
        return num;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        return 0;
    }

    @Override
    public int readInt(String prompt) {
        boolean invalidInput = true;
        int num = 0;

        while (invalidInput){
            try {
                //print the message prompt
                String stringValue = this.readString(prompt);
                //get the input line, and try and parse
                num = Integer.parseInt(stringValue);
                invalidInput = false; //or you can use break
            } catch (NumberFormatException e){
                //if it explodes, it'll go here and do this
                this.print("Input error. Please try again.");
            }
        }
        return num;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int result;
        do {
            result = readInt(prompt);
        } while (result < min || result > max);
        return result;
    }

    @Override
    public long readLong(String prompt) {
        boolean invalidInput = true;
        long num = 0;

        while (invalidInput){
            try {
                //print the message prompt
                String stringValue = this.readString(prompt);
                //get the input line, and try and parse
                num = Long.parseLong(stringValue);
                invalidInput = false; //or you can use break
            } catch (NumberFormatException e){
                //if it explodes, it'll go here and do this
                this.print("Input error. Please try again.");
            }
        }
        return num;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        return 0;
    }

    @Override
    public String readString(String prompt) {
        this.print(prompt);
        return console.nextLine();
    }
}
