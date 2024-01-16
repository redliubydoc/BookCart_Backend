package com.raj.ms.product.db.util;

import java.sql.ResultSet;

public interface ResultSetMapper<T> {
  public void map(ResultSet resultSet, T mapperDestObj);
}
