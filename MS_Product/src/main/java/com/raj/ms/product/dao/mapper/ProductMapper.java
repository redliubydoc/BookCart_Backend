package com.raj.ms.product.dao.mapper;

import com.raj.ms.product.db.util.ResultSetMapper;
import com.raj.ms.product.dto.response.LanguageResponseData;
import com.raj.ms.product.dto.response.ProductResponseData;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class ProductMapper implements ResultSetMapper<ProductResponseData> {

  @Override
  public void map(ResultSet resultSet, ProductResponseData productResponseData) {

    try {

      while (resultSet.next()) {

        productResponseData.setIsbn(resultSet.getString("isbn"));
        productResponseData.setTitle(resultSet.getString("title"));
        productResponseData.setAuthor(resultSet.getString("author"));
        productResponseData.setEdition(resultSet.getInt("edition"));
        productResponseData.setLanguage(new LanguageResponseData(resultSet.getString("lng_cd"), resultSet.getString("lng_nm")));
        productResponseData.setGenres(new ArrayList<>());
        productResponseData.setDescription(resultSet.getString("description"));
        productResponseData.setDateOfRelease(resultSet.getDate("date_of_release"));
        productResponseData.setPrice(resultSet.getDouble("price"));
        productResponseData.setThumbnail(resultSet.getBytes("thumbnail"));
      }
    }
    catch (SQLException e) {
      // TODO: robust exception handling
      e.printStackTrace();
    }
  }
}
