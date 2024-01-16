package com.raj.ms.product.db.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class PreparedStatementRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(PreparedStatementRunner.class);

  private static final String FOR_LOGGING_QUERY_1 = "SQL query [";
  private static final String FOR_LOGGING_QUERY_2 = "]";

  private static final String FOR_LOGGING_QUERY_WITH_PARAMS_1 = "SQL query with params [";
  private static final String FOR_LOGGING_QUERY_WITH_PARAMS_2 = "]";
  private static final String FOR_LOGGING_PARAM_1 = "SQL query param (";
  private static final String FOR_LOGGING_PARAM_2 = ") [";
  private static final String FOR_LOGGING_PARAM_3 = "]";

  private static final String FOR_LOGGING_OUT_PARAM_TYPE_1 = "SQL query out param type (";
  private static final String FOR_LOGGING_OUT_PARAM_TYPE_2 = ") [";
  private static final String FOR_LOGGING_OUT_PARAM_TYPE_3 = "]";

  private static final String FOR_LOGGING_OUT_PARAM_1 = "SQL query out param (";
  private static final String FOR_LOGGING_OUT_PARAM_2 = ") [";
  private static final String FOR_LOGGING_OUT_PARAM_3 = "]";

  private static final String FOR_LOGGING_PLSQL_LOG_1 = "PLSQL logs [";
  private static final String FOR_LOGGING_PLSQL_LOG_2 = "]";

  public <T> T executeQuery(
    String query,
    DataSource dataSource,
    ResultSetMapper<T> resultSetMapper,
    T mapperDestObj
  ) {

    validateQuery(query);

    try (Connection connection = dataSource.getConnection()) {

      Statement stmt = connection.createStatement();
      ResultSet resultSet = stmt.executeQuery(query);
      resultSetMapper.map(resultSet, mapperDestObj);
    }
    catch (SQLException e) {

      // TODO: robust exception handling
      e.printStackTrace();
    }

    return mapperDestObj;
  }
  public <T> T executeQuery(
    String query,
    Object[] params,
    DataSource dataSource,
    ResultSetMapper<T> resultSetMapper,
    T mapperDestObj
  ) {

    validateQuery(query);

    try (Connection connection = dataSource.getConnection()) {

      PreparedStatement pStmt = connection.prepareStatement(query);
      preparePreparedStatement(pStmt, params);

      ResultSet resultSet = pStmt.executeQuery();
      resultSetMapper.map(resultSet, mapperDestObj);
    }
    catch (SQLException e) {

      // TODO: robust exception handling
      e.printStackTrace();
    }

    return mapperDestObj;
  }

//  public <T> T executeUpdate(
//    String query,
//    Object[] params,
//    String[] opColNames,
//    DataSource dataSource,
//    ResultSetMapper<T> resultSetMapper,
//    T mapperDestObj
//  ) {
//
//    try {
//
//      validateQuery(query);
//      validateParams(params);
//
//      Connection connection = dataSource.getConnection();
//      PreparedStatement pStmt = connection.prepareStatement(query, opColNames);
//      preparePreparedStatement(pStmt, params);
//
//      pStmt.executeUpdate();
//      resultSetMapper.map(pStmt.getGeneratedKeys(), mapperDestObj);
//    }
//    catch (SQLException e) {
//
//      // TODO: robust exception handling
//      e.printStackTrace();
//    }
//
//    return mapperDestObj;
//  }

  public <T> T executeProcedure(
    String query,
    Object[] inParams,
    int[] outParamTypes,
    DataSource dataSource,
    ResultSetMapper<T> resultSetMapper,
    T mapperDestObj
  ) {

    validateQuery(query);

    try (Connection connection = dataSource.getConnection()) {

      CallableStatement cStmt = connection.prepareCall(query);
      prepareCallableStatement(cStmt, inParams, outParamTypes);
      cStmt.execute();

      Object[] outParams = getOutParams(cStmt, outParamTypes.length, inParams.length);

      LOGGER.debug(FOR_LOGGING_PLSQL_LOG_1 + outParams[1] + FOR_LOGGING_PLSQL_LOG_2);

      ResultSet resultSet = (ResultSet) outParams[0];
      resultSetMapper.map(resultSet, mapperDestObj);
    }
    catch (SQLException e) {

      // TODO: robust exception handling
      e.printStackTrace();
    }

    return mapperDestObj;
  }

  private void validateQuery(String query) {

    LOGGER.debug(FOR_LOGGING_QUERY_1 + flattenQuery(query)  + FOR_LOGGING_QUERY_2);

    if (query == null || "".equalsIgnoreCase(query)) {
      // TODO: robust exception handling
      System.out.println("query is null");
    }
  }

  private void preparePreparedStatement(PreparedStatement pStmt, Object[] params) throws SQLException {

    if (params != null && params.length > 0) {
      for (int paramsCount = 0; paramsCount < params.length; paramsCount++) {
        LOGGER.debug(FOR_LOGGING_PARAM_1 + (paramsCount + 1) + FOR_LOGGING_PARAM_2 + params[paramsCount] + FOR_LOGGING_PARAM_3);
        pStmt.setObject((paramsCount + 1), params[paramsCount]);
      }
    }
  }

  private void prepareCallableStatement(CallableStatement cStmt, Object[] inParams, int[] outParamTypes) throws SQLException {

    if ((inParams != null && inParams.length > 0) &&
      (outParamTypes != null && outParamTypes.length > 0)) {

      for (int inParamsCount = 0; inParamsCount < inParams.length; inParamsCount++) {
        LOGGER.debug(FOR_LOGGING_PARAM_1 + (inParamsCount + 1) + FOR_LOGGING_PARAM_2 + inParams[inParamsCount] + FOR_LOGGING_PARAM_3);
        cStmt.setObject((inParamsCount + 1), inParams[inParamsCount]);
      }

      for (int outParamTypesCount = 0; outParamTypesCount < outParamTypes.length; outParamTypesCount++) {
        LOGGER.debug(FOR_LOGGING_OUT_PARAM_TYPE_1 + (inParams.length + outParamTypesCount + 1) + FOR_LOGGING_OUT_PARAM_TYPE_2 + outParamTypes[outParamTypesCount] + FOR_LOGGING_OUT_PARAM_TYPE_3);
        cStmt.registerOutParameter((inParams.length + outParamTypesCount + 1), outParamTypes[outParamTypesCount]);
      }
    }
  }

  private Object[] getOutParams(CallableStatement cStmt, int outParamsSize, int paramOffset) throws SQLException {

    if (paramOffset > 0) {
      Object[] outParams = new Object[outParamsSize];

      for (int outParamsCount = 0; outParamsCount < outParamsSize; outParamsCount++) {

        outParams[outParamsCount] = cStmt.getObject(outParamsCount + paramOffset + 1);
        LOGGER.debug(FOR_LOGGING_OUT_PARAM_1 + (outParamsCount + 1) + FOR_LOGGING_OUT_PARAM_2 + outParams[outParamsCount] + FOR_LOGGING_OUT_PARAM_3);
      }
      return outParams;
    }
    else {
      throw new SQLException();
    }
  }

  private String flattenQuery(String query) {
    return query.lines()
      .reduce("", (accumulator, line) -> line.strip().isBlank() ? accumulator : accumulator + " " + line.strip())
      .strip();
  }
}
