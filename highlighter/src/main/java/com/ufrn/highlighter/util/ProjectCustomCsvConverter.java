package com.ufrn.highlighter.util;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.ufrn.highlighter.model.Project;

public class ProjectCustomCsvConverter extends AbstractBeanField<Project, String> {

    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return null;
    }
}
