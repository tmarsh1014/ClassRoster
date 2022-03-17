package com.sg.classroster.dao;

import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterDaoFileImpl;
import com.sg.classroster.dto.Student;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClassRosterDaoFileImplTest {

    ClassRosterDao testDao;

    public ClassRosterDaoFileImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testroster.txt";
        // make the file blank before using it each time
        new FileWriter(testFile);
        testDao = new ClassRosterDaoFileImpl(testFile);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddGetStudent() throws Exception{
        //ARRANGE
        String studentId = "0001";
        Student student = new Student(studentId);
        student.setFirstName("Ada");
        student.setLastName("Lovelace");
        student.setCohort("Java-May-1845");

        //ACT
        testDao.addStudent(student.getStudentID(), student);

        Student retrieveStudent = testDao.getStudent(studentId);

        //ASSERT
        assertEquals(student.getStudentID(), retrieveStudent.getStudentID(), "Checking student id");
        assertEquals(student.getFirstName(), retrieveStudent.getFirstName(), "Checking student first name");
        assertEquals(student.getLastName(), retrieveStudent.getLastName(), "Checking student last name");
        assertEquals(student.getCohort(), retrieveStudent.getCohort(), "Checking student cohort");
    }

    @Test
    public void testAddGetAllStudents() throws Exception {
        //Create first student
        Student firstStudent = new Student("0001");
        firstStudent.setFirstName("Ada");
        firstStudent.setLastName("Lovelace");
        firstStudent.setCohort("Java-May-1845");

        //Create second student
        Student secondStudent = new Student("0002");
        secondStudent.setFirstName("Charles");
        secondStudent.setLastName("Babbage");
        secondStudent.setCohort(".NET-May-1845");

        //Add students to dao
        testDao.addStudent(firstStudent.getStudentID(), firstStudent);
        testDao.addStudent(secondStudent.getStudentID(), secondStudent);

        //Retrieve the list of all students within the dao
        List<Student> allStudents = testDao.getAllStudents();

        //test general contents of the list
        assertNotNull(allStudents, "The list of students must not be null");
        assertEquals(2, allStudents.size(), "List of students should have 2 students.");

        //test specifics
        assertTrue(testDao.getAllStudents().contains(firstStudent), "The list of students should include Ada");
        assertTrue(testDao.getAllStudents().contains(secondStudent), "The list of students should include Charles");
    }

    @Test
    public void testRemoveStudent() throws Exception {
        //Create two new students
        Student firstStudent = new Student("0001");
        firstStudent.setFirstName("Ada");
        firstStudent.setLastName("Lovelace");
        firstStudent.setCohort("Java-May-1845");

        Student secondStudent = new Student("0002");
        secondStudent.setFirstName("Charles");
        secondStudent.setLastName("Babbage");
        secondStudent.setCohort(".NET-May-1845");

        //add both to dao
        testDao.addStudent(firstStudent.getStudentID(), firstStudent);
        testDao.addStudent(secondStudent.getStudentID(), secondStudent);

        //remove first student - Ada
        Student removedStudent = testDao.removeStudent(firstStudent.getStudentID());

        //test that correct object was removed
        assertEquals(removedStudent, firstStudent, "The removed student should be ada");

        //get all students
        List<Student> allStudents = testDao.getAllStudents();

        //test general contents of the list
        assertNotNull(allStudents, "The list of students must not be null");
        assertEquals(1, allStudents.size(), "List of students should have 1 student.");

        //test specifics
        assertFalse(testDao.getAllStudents().contains(firstStudent), "The list of students should NOT include Ada");
        assertTrue(testDao.getAllStudents().contains(secondStudent), "The list of students should include Charles");

        //remove second student
        removedStudent = testDao.removeStudent(secondStudent.getStudentID());

        //test that correct object was removed
        assertEquals(removedStudent, secondStudent, "The removed student should be charles");

        //get all students again
        allStudents = testDao.getAllStudents();

        //test general contents of the list - it should be empty
        assertTrue(allStudents.isEmpty(), "The list of students should be empty.");

        //test 'get' both students - should be null
        Student retrievedStudent = testDao.getStudent(firstStudent.getStudentID());
        assertNull(retrievedStudent, "Ada was removed, should be null");

        retrievedStudent = testDao.getStudent(secondStudent.getStudentID());
        assertNull(retrievedStudent, "Charles was removed, should be null");
    }

}
