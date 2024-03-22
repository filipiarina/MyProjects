package com.example.prjdb.domain.validate;

public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}