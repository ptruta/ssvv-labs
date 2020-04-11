package org.example.service;

import org.example.domain.Student;
import org.example.domain.Teme;
import org.example.repository.StudentRepo;
import org.example.repository.TemeRepo;
import org.example.validator.StudentValidator;
import org.example.validator.TemeValidator;
import org.example.validator.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ServiceTemeTest_WBT {

    private ServiceTeme serviceTeme;

    private TemeRepo temeRepo;

    @Before
    public void setUp() {
        temeRepo = new TemeRepo(new TemeValidator(),"teme1.xml");
        serviceTeme = new ServiceTeme(temeRepo);
    }

    @Test
    public void test_tc_invalidAssignment_addAssignment() {
        Teme t1 = new Teme(0, "", -10, -11);
        try {
            serviceTeme.add(t1);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("\nID invalid\nDeadline invalid\nSaptamana in care tema a fost primita este invalida"));
        }
    }

    @Test
    public void test_tc_validAssignment_addAssignment() {
        Teme t1 = new Teme(1, "Tema4", 11, 12);
        try {
            serviceTeme.add(t1);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("\nID invalid\nDeadline invalid\nSaptamana in care tema a fost primita este invalida"));
        }
    }

    // Tests for add Assignment

    @Test
    public void addValidAssignmentCheckReturnValueTest() {
        serviceTeme.add(new Teme(2, "tema 4", 3, 4));
        Assert.assertEquals(java.util.Optional.of(serviceTeme.getSize()), java.util.Optional.of(2));
    }

    @Test(expected = ValidationException.class)
        public void addAssignmentWithNullIDTest() {
        serviceTeme.add(new Teme(null, "tema 5", 7, 8));
    }

    @Test(expected = ValidationException.class)
    public void addAssignmentWithDeadlineLessThanStarting() {
        serviceTeme.add(new Teme(2, "", 6, 5));
    }

    @Test(expected = ValidationException.class)
    public void addAssignmentWithLessThan1DeadlineTest() {
        serviceTeme.add(new Teme(2, "tema c", 0, 1));
    }

    @Test(expected = ValidationException.class)
    public void addAssignmentWithLessThan1StartlineTest() {
        serviceTeme.add(new Teme(2, "tema l", 1, 0));
    }

    @Test
    public void addAssignmentWithExistingIDTest() {
        serviceTeme.add(new Teme(1, "Bill", 3, 4));
    }

}
