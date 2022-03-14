package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;

import java.io.*;
import java.util.*;

public class ClassRosterDaoFileImpl implements ClassRosterDao {

    private Map<String, Student> students = new HashMap<>();
    //lines in the file will look like:
    //<studentid>::<first name>::<last name>::<cohort>
    //EX: 0001::Joe::Cool::Java - Jan 2016
    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";


    @Override
    public Student addStudent(String studentId, Student student) throws ClassRosterDaoException {
        loadRoster();
        Student newStudent = students.put(studentId, student);
        writeRoster();
        return newStudent;
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterDaoException {
        loadRoster();
        return new ArrayList<>(students.values());
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterDaoException{
        loadRoster();
        return students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterDaoException{
        loadRoster();
        Student removedStudent = students.remove(studentId);
        writeRoster();
        return removedStudent;
    }

    //getting a line of text from the file and converting it into a student object
    private Student unmarshallStudent(String studentAsText){
        String[] studentTokens = studentAsText.split(DELIMITER);
        String studentId = studentTokens[0];
        Student studentFromFile = new Student(studentId);
        studentFromFile.setFirstName(studentTokens[1]);
        studentFromFile.setLastName(studentTokens[2]);
        studentFromFile.setCohort(studentTokens[3]);
        return studentFromFile;
    }

    //scan file line by line and decode lines into students and add them to hashmap
    private void loadRoster() throws ClassRosterDaoException {
        Scanner scanner;

        try {
            //create scanner for reading the file
            scanner = new Scanner(new BufferedReader(
                    new FileReader(ROSTER_FILE)));
        }catch (FileNotFoundException e){
            throw new ClassRosterDaoException("Could not load roster data into memory.",e);
        }
        //hold the most recent line read from file
        String currentLine;
        //hold the most recent student unmarshalled
        Student currentStudent;

        //go though each line and decode into student objects
        while (scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentStudent = unmarshallStudent(currentLine);

            //add unmarshalled student to map
            students.put(currentStudent.getStudentID(), currentStudent);
        }

        scanner.close();
    }

    //getting a student and converting it into a line of text to be placed in file
    private String marshallStudent(Student aStudent){
        String studentAsText = aStudent.getStudentID() + DELIMITER;
        studentAsText += aStudent.getFirstName() + DELIMITER;
        studentAsText += aStudent.getLastName() + DELIMITER;
        studentAsText += aStudent.getCohort() + DELIMITER;

        return studentAsText;
    }

    //write all students in the roster out to the file
    private void writeRoster() throws ClassRosterDaoException{
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e){
            throw new ClassRosterDaoException("Could not save student data.", e);
        }

        String studentAsText;
        List<Student> studentList = this.getAllStudents();
        for (Student currentStudent: studentList) {
            //turn student into string using our method
            studentAsText = marshallStudent(currentStudent);
            //write student to the file
            out.println(studentAsText);
            //force PrintWriter to write line to the file
            out.flush();
        }
        out.close();
    }
}
