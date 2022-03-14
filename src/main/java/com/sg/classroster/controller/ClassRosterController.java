package com.sg.classroster.controller;

import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterDaoException;
import com.sg.classroster.dto.Student;
import com.sg.classroster.ui.ClassRosterView;
//import com.sg.classroster.ui.UserIO;
//import com.sg.classroster.ui.UserIOConsoleImpl;
//import com.sg.classroster.dao.ClassRosterDaoFileImpl;

import java.util.List;

public class ClassRosterController {

    //private UserIO io = new UserIOConsoleImpl();
    private ClassRosterView view;
    private ClassRosterDao dao;

    public ClassRosterController(ClassRosterView view, ClassRosterDao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            //being displayed to user until they exit
            while ((keepGoing)) {

                //talking to the view
                menuSelection = getMenuSelection();

                //switch case based off of choices
                switch (menuSelection) {
                    case 1:
                        listStudents();
                        break;
                    case 2:
                        createStudent();
                        break;
                    case 3:
                        viewStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand(); //in case they input an invalid choice
                }
            }

            //exit message
            exitMessage();
        } catch (ClassRosterDaoException e){
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createStudent() throws ClassRosterDaoException{
        view.displayCreateStudentBanner();
        Student newStudent = view.getNewStudentInfo();
        dao.addStudent(newStudent.getStudentID(), newStudent);
        view.displayCreateSuccessBanner();
    }

    private void listStudents() throws ClassRosterDaoException{
        view.displayDisplayAllBanner();
        List<Student> studentList = dao.getAllStudents();
        view.displayStudentList(studentList);
    }

    private void viewStudent() throws ClassRosterDaoException{
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = dao.getStudent(studentId);
        view.displayStudent(student);
    }

    private void removeStudent() throws ClassRosterDaoException{
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student removedStudent = dao.removeStudent(studentId);
        view.displayRemoveResult(removedStudent);
    }

    private void unknownCommand(){
        view.displayUnknownCommandBanner();
    }

    private void exitMessage(){
        view.displayExitBanner();
    }
}
