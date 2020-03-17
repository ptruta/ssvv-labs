package org.example.validator;

public interface Validator<E> {
    public String validate(E el);
}
