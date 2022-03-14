package com.sg.classroster.ui;

import com.sg.classroster.dto.Student;

import java.util.List;

public class ClassRosterView {

    private UserIO io;

    public ClassRosterView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection(){
        io.print("Main Menu");
        io.print("1. List Student IDs");
        io.print("2. Create New Student");
        io.print("3. View a Student");
        io.print("4. Remove a Student");
        io.print("5. Exit");

        //prompts user to select a choice between 1 - 5
       int menuSelection = io.readInt("Please select from the above choices.",1,5);

       return menuSelection;
    }

    //view when user choose 2. Create New Student
    public Student getNewStudentInfo() {
        String studentID = io.readString("Please enter Student ID");
        String firstName = io.readString("Please enter First Name");
        String lastName = io.readString("Please enter Last Name");
        String cohort = io.readString("Please enter Cohort");

        Student currentStudent = new Student(studentID);
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setCohort(cohort);

        return currentStudent;
    }

    public void displayCreateStudentBanner(){
        io.print("=== Create Student ===");
    }

    public void displayCreateSuccessBanner(){
        //will read out prompt to user. press enter to get rid of
        io.readString("Student successfully created. Please hit enter to continue.");
    }

    //view when user presses 1. List Student Ids
    public void displayStudentList(List<Student> studentList){
        for (Student currentStudent: studentList) {
            String studentInfo = String.format("#%s : %s %s",
                    currentStudent.getStudentID(),
                    currentStudent.getFirstName(),
                    currentStudent.getLastName());
            io.print(studentInfo);
        }
        io.readString("Please hit enter to continue");
    }

    public void displayDisplayAllBanner(){
        io.print("=== Display All Students ===");
    }

    //view when user chooses 3. View a Student
    public void displayDisplayStudentBanner(){
        io.print("=== Display Student ===");
    }

    public String getStudentIdChoice() {
        //prompt user for student id and return value
        return  io.readString("Please enter the Student ID.");
    }

    public void displayStudent(Student student){
        if (student != null){
            io.print(student.getStudentID());
            io.print(student.getFirstName() + " " + student.getLastName());
            io.print(student.getCohort());
            io.print("");
        }
        else {
            io.print("No such student.");
        }
        io.readString("Please hit enter to continue.");
    }

    //view when user chooses 4. Remove a Student
    public void displayRemoveStudentBanner(){
        io.print("=== Remove Student ===");
    }

    public void displayRemoveResult(Student studentRecord){
        if(studentRecord != null){
            io.print("Student removed successfully!");
        }
        else{
            io.print("No such student.");
        }
        io.readString("Please hit enter to continue");
    }

    //view when user chooses 5. Exit
    public void displayExitBanner(){
        io.print("Good Bye!!!");
    }

    //view when an unknown command is chosen
    public void displayUnknownCommandBanner(){
        io.print("Unknown Command!!!");
    }

    //view when an error happens
    public void displayErrorMessage(String errorMsg){
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
