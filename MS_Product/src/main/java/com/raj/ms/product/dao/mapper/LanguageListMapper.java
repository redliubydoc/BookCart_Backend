package com.raj.ms.product.dao.mapper;

import com.raj.ms.product.db.util.ResultSetMapper;
import com.raj.ms.product.dto.response.GenreResponseData;
import com.raj.ms.product.dto.response.LanguageResponseData;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class LanguageListMapper implements ResultSetMapper<List<LanguageResponseData>> {
  @Override
  public void map(ResultSet resultSet, List<LanguageResponseData> languageResponseDataList) {

    try {
      while (resultSet.next()) {

        LanguageResponseData languageResponseData = new LanguageResponseData();

        languageResponseData.setCode(resultSet.getString("lng_cd"));
        languageResponseData.setName(resultSet.getString("lng_nm"));

        languageResponseDataList.add(languageResponseData);
      }
    }
    catch (SQLException e) {
      // TODO: robust exception handling
      e.printStackTrace();
    }
  }
}
