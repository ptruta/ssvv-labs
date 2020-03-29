package org.example.exceptions;


public class InvalidStudentException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidStudentException() {
        super();
    }

    public InvalidStudentException(String msg) {
        super(msg);
    }

}