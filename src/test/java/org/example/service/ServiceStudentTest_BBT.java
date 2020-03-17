package org.example.service;

import org.example.domain.Student;
import org.example.repository.StudentRepo;
import org.example.validator.StudentValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServiceStudentTest_BBT {

    private ServiceStudent studentController;

    @Before
    public void setUp() {
        StudentRepo studentRepo = new StudentRepo(new StudentValidator(),"studenti.xml");
        studentController = new ServiceStudent(studentRepo);
    }

    @Test
    public void test_tc_1_addStudent(){
        Student s = new Student("3", "Truta Patricia-Georgiana", 931, "tpie2451@scs.ubbcluj.ro","Vescan Andreea");
        studentController.add(s);
        Assert.assertEquals(java.util.Optional.of(studentController.getSize()), java.util.Optional.of(3));
    }

    @Test
    public void test_tc_2_addStudent() {
        Student s1 = new Student("4", "Cardas Andra", 931, "tpie2451@scs.ubbcluj.ro","Vescan Andreea");
        Student s2 = new Student("5", "Cardas Andra", 931, "tpie2451@scs.ubbcluj.ro","Vescan Andreea");
        studentController.add(s1);
        studentController.add(s2);
        Assert.assertEquals(java.util.Optional.ofNullable(studentController.getSize()), java.util.Optional.ofNullable(3));
    }
}
