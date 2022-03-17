package com.sg.classroster.service;

import com.sg.classroster.dao.*;
import com.sg.classroster.dto.Student;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ClassRosterServiceLayerImplTest {

    private ClassRosterServiceLayer testServ;

    public ClassRosterServiceLayerImplTest() {
        ClassRosterDao dao = new ClassRosterDaoStubImpl();
        ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();

        testServ = new ClassRosterServiceLayerImpl(dao, auditDao);
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCreateValidStudent() {
        //ARRANGE
        Student student = new Student("0002");
        student.setFirstName("Charles");
        student.setLastName("Babbage");
        student.setCohort(".NET-May-1845");

        //ACT
        try {
            testServ.createStudent(student);
        }
        catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException | ClassRosterPersistenceException e){
            //ASSERT
            fail("Student was valid. No exception should have been thrown.");
        }
    }

    @Test
    public void testCreateDuplicatedIdStudent(){
        //ARRANGE
        Student student = new Student("0001");
        student.setFirstName("Charles");
        student.setLastName("Babbage");
        student.setCohort(".NET-May-1845");

        //ACT
        try {
            testServ.createStudent(student);
            fail("Expected DupeId Exception was not thrown.");
        }
        catch (ClassRosterDataValidationException | ClassRosterPersistenceException e){
            //ASSERT
            fail("Incorrect exception was thrown.");
        }
        catch (ClassRosterDuplicateIdException e){
            return;
        }
    }

    @Test
    public void testCreateStudentInvalidData() throws Exception{
        // ARRANGE
        Student student = new Student("0002");
        student.setFirstName("");
        student.setLastName("Babbage");
        student.setCohort(".NET-May-1845");

        // ACT
        try {
            testServ.createStudent(student);
            fail("Expected ValidationException was not thrown.");
        } catch (ClassRosterDuplicateIdException
                | ClassRosterPersistenceException e) {
            // ASSERT
            fail("Incorrect exception was thrown.");
        } catch (ClassRosterDataValidationException e){
            return;
        }
    }

    @Test
    public void testGetAllStudents() throws Exception {
        //ARRANGE
        Student testClone = new Student("0001");
        testClone.setFirstName("Ada");
        testClone.setLastName("Lovelace");
        testClone.setCohort("Java-May-1845");

        //ACT & ASSERT
        assertEquals(1, testServ.getAllStudents().size(), "Should only have one student");
        assertTrue(testServ.getAllStudents().contains(testClone),"The one student should be Ada.");
    }

    @Test
    public void testGetStudent() throws Exception {
        //ARRANGE
        Student testClone = new Student("0001");
        testClone.setFirstName("Ada");
        testClone.setLastName("Lovelace");
        testClone.setCohort("Java-May-1845");

        //ACT & ASSERT
        Student shouldBeAda = testServ.getStudent("0001");
        assertNotNull(shouldBeAda, "Getting 0001 should not be null.");
        assertEquals(testClone, shouldBeAda, "Student stored under 0001 should be Ada.");

        Student shouldBeNull = testServ.getStudent("0042");
        assertNull(shouldBeNull, "Getting 0042 should be null.");
    }

    @Test
    public void testRemoveStudent() throws Exception {
        // ARRANGE
        Student testClone = new Student("0001");
        testClone.setFirstName("Ada");
        testClone.setLastName("Lovelace");
        testClone.setCohort("Java-May-1845");

        //ACT & ASSERT
        Student shouldBeAda = testServ.removeStudent("0001");
        assertNotNull(shouldBeAda, "Removing 0001 should not be null.");
        assertEquals(testClone, shouldBeAda, "Student removed from 0001 should be Ada.");

        Student shouldBeNull = testServ.removeStudent("0042");
        assertNull(shouldBeNull, "Removing 0042 should be null.");
    }

}
