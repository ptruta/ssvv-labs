package org.example.service;

import org.example.domain.Student;
import org.example.repository.StudentRepo;
import org.example.validator.StudentValidator;
import org.example.validator.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
        Student s1 = new Student("1", "Cardas Andra", -111, "tpie2451@scs.ubbcluj.ro","Vescan Andreea");
        try {
            studentController.add(s1);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("\nGrupa invalida"));
        }
    }


}
