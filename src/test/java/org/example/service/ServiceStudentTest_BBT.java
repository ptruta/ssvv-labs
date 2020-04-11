package org.example.service;

import org.example.domain.Nota;
import org.example.domain.Student;
import org.example.domain.Teme;
import org.example.repository.NoteRepo;
import org.example.repository.StudentRepo;
import org.example.validator.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.StreamSupport;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ServiceStudentTest_BBT {

    private ServiceStudent studentController;
    private ServiceNote serviceNote;

    @Before
    public void setUp() {
        StudentRepo studentRepo = new StudentRepo(new StudentValidator(),"studenti.xml");
        studentController = new ServiceStudent(studentRepo);
        NoteRepo noteRepo = new NoteRepo(new NotaValidator());
        serviceNote = new ServiceNote(noteRepo);
    }

    //valid test for all

    @Test
    public void test_tc_1_addStudent(){
        long numberOfStudents = StreamSupport.stream(studentController.all().spliterator(), false).count();
        Student s = new Student("3", "Truta Patricia-Georgiana", 931, "tpie2451@scs.ubbcluj.ro","Vescan Andreea");
        studentController.add(s);
        Assert.assertEquals(numberOfStudents, StreamSupport.stream(studentController.all().spliterator(), false).count());
    }

    @Test
    public void test_tc_2_addStudent() {
        Student s1 = new Student("1", "Cardas Andra", -111, "tpie2451@scs.ubbcluj.ro","Vescan Andreea");
        try {
            studentController.add(s1);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("\nGrupa invalida"));
        }
    }

    // Test cases for ID

    //1
    @Test(expected = ValidationException.class)
    public void addStudentWithStringIDTest() {
        studentController.add(new Student("dada", "Truta Patricia-Georgiana", 931, "tpie2451@scs.ubbcluj.ro","Vescan Andreea"));
    }

    //2
    @Test(expected = ValidationException.class)
    public void addStudentWithCharIDTest() {
        studentController.add(new Student("d", "Cardas Andra-Malina", 931, "tpie2451@scs.ubbcluj.ro","Vescan Andreea"));
    }

    //3
    @Test(expected = ValidationException.class)
    public void addStudentWithEmptyIDTest() {
        studentController.add(new Student("", "Andreea Dorina", 931, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"));
    }

    //4
    @Test(expected = ValidationException.class)
    public void addStudentWithNullIDTest() {
        studentController.add(new Student(null, "Marian Alexandra", 931, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"));
    }

    // Tests for edge cases group

    //5
    @Test(expected = ValidationException.class)
    public void addStudentWithGroupNumberOutsideLowerLimitTest() {
        studentController.add(new Student("11", "Pop Andrei", 110, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"));
    }

    //6
    @Test(expected = ValidationException.class)
    public void addStudentWithGroupNumberAboveLowerLimitTest() {
        studentController.add(new Student("11", "Will Smith", 938, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"));
    }

    //7
    @Test(expected = ValidationException.class)
    public void addStudentWithMiddleNumberLessThan1Test() {
        studentController.add(new Student("11", "Johnny Depp", 101, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"));
    }

    //8
    @Test(expected = ValidationException.class)
    public void addStudentWithMiddleNumberGreaterThan3Test() {
        studentController.add(new Student("11", "Mihai Achim", 141, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"));
    }

    //9
    @Test(expected = ValidationException.class)
    public void addStudentWithLastNumberLessThan1Test() {
        studentController.add(new Student("11", "Mihai Achim", 130, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"));
    }

    //10
    @Test(expected = ValidationException.class)
    public void addStudentWithLastNumberGreaterThan7Test() {
        studentController.add(new Student("11", "Mihai Achim", 138, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"));
    }

    //11
    @Test
    public void addStudentWithGroupNumberOnLowerLimitTest() {
        studentController.add(new Student("11", "Brad Pitt", 111, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"));
        Assert.assertEquals(java.util.Optional.of(studentController.getSize()), java.util.Optional.of(5));
    }

    //12
    @Test
    public void addStudentWithGroupNumberOnHighestLimitTest() {
        studentController.add(new Student("11", "Dorel Mustan", 937, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"));
        Assert.assertEquals(java.util.Optional.of(studentController.getSize()), java.util.Optional.of(5));
    }

    // Test cases for mail

    //13
    @Test(expected = ValidationException.class)
    public void addStudentWithNoAroundCharInMail(){
        studentController.add(new Student("11", "Dorel Mustan", 931, "tpie2451scs.ubbcluj.ro", "Vescan Andreea"));
    }

    //14
    @Test(expected = ValidationException.class)
    public void addStudentWithNoDotsInMail(){
        studentController.add(new Student("11", "Dorel Mustan", 931, "tpie2451@scsubbclujro", "Vescan Andreea"));
    }

    // End tests for edge cases group good for each cases

    //15
    @Test
    public void addValidStudentCheckReturnValueTest() {
        studentController.add(new Student("11", "Andrada Dayana", 936, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"));
        Assert.assertEquals(java.util.Optional.of(studentController.getSize()), java.util.Optional.of(5));
    }

    //16
    @Test
    public void addValidStudentCheckAddEffectTest() {
        long numberOfStudents = StreamSupport.stream(studentController.all().spliterator(), false).count();
        studentController.add(new Student("11", "Andrada Valean", 936, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"));
        Assert.assertEquals(numberOfStudents, StreamSupport.stream(studentController.all().spliterator(), false).count());
    }

    // Test cases for Name

    //17
    @Test(expected = ValidationException.class)
    public void addStudentWithNoMatchingNameTest() {
        studentController.add(new Student("11", "32493458null", 931, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"));
    }

    //18
    @Test(expected = ValidationException.class)
    public void addStudentWithEmptyNameTest() {
        studentController.add(new Student("11", "", 931, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"));
    }

    // Test cases for professor name

    //19
    @Test(expected = ValidationException.class)
    public void addStudentWithNotMatchingProfNameTest() {
        studentController.add(new Student("11", "Truta Patricia-Georgiana", 931, "tpie2451@scs.ubbcluj.ro", "434534fkdsfs"));
    }

    //20
    @Test(expected = ValidationException.class)
    public void addStudentWithEmptyProfNameTest() {
        studentController.add(new Student("11", "Truta Patricia-Georgiana", 931, "tpie2451@scs.ubbcluj.ro", ""));
    }

    // TESTS FOR ADD GRADE

    @Test
    public void addValidGradeCheckReturnValueTest() {
        serviceNote.add(new Nota(new AbstractMap.SimpleEntry<String, Integer>("Dada", 1),new Student("11", "Truta Patricia-Georgiana", 931, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"),
                new Teme(1, "tema 4", 3, 4),10, 10),"23");
        Assert.assertEquals(java.util.Optional.of(serviceNote.getSize()), java.util.Optional.of(1));

    }

    @Test(expected = ValidationException.class)
    public void addGradeWithEmptyStudentIDTest() {
        serviceNote.add(new Nota(new AbstractMap.SimpleEntry<String, Integer>("papa", 1),new Student("", "Truta Patricia-Georgiana", 931, "tpie2451@scs.ubbcluj.ro", "Vescan Andreea"),
                new Teme(1, "tema 4", 3, 4),10, 25),"23");
    }

}
