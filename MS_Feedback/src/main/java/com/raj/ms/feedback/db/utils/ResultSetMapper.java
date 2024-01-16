package com.raj.ms.feedback.db.utils;

import java.sql.ResultSet;

public interface ResultSetMapper<T> {
  public void map(ResultSet resultSet, T mapperDestObj);
}
