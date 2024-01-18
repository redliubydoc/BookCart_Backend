package com.raj.ms.product.dao.mapper;

import com.raj.ms.product.db.util.ResultSetMapper;
import com.raj.ms.product.dto.response.GenreResponseData;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class GenreListMapper implements ResultSetMapper<List<GenreResponseData>> {
  @Override
  public void map(ResultSet resultSet, List<GenreResponseData> genreResponseDataList) {

    try {
      while (resultSet.next()) {

        GenreResponseData genreResponseData = new GenreResponseData();

        genreResponseData.setCode(resultSet.getString("gen_cd"));
        genreResponseData.setName(resultSet.getString("gen_nm"));

        genreResponseDataList.add(genreResponseData);
      }
    }
    catch (SQLException e) {
      // TODO: robust exception handling
      e.printStackTrace();
    }
  }
}
