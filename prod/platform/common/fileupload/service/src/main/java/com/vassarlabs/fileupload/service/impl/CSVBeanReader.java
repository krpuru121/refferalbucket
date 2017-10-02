package com.vassarlabs.fileupload.service.impl;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.exceptions.CsvBadConverterException;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class CSVBeanReader<T> extends CsvToBean<T> {

    @Override
    public T processLine(MappingStrategy<T> mapper, String[] line) throws IllegalAccessException, InvocationTargetException, InstantiationException, IntrospectionException, CsvBadConverterException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, CsvConstraintViolationException {
        return super.processLine(mapper, line);
    }
}