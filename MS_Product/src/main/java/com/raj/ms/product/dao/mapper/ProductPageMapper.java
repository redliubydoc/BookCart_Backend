package com.raj.ms.product.dao.mapper;

import com.raj.ms.product.dto.response.LanguageResponseData;
import com.raj.ms.product.dto.response.ProductPagedResponseData;
import com.raj.ms.product.dto.response.ProductResponseData;
import com.raj.ms.product.db.util.ResultSetMapper;
import org.springframework.stereotype.Component;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

@Component
public class ProductPageMapper implements ResultSetMapper<ProductPagedResponseData> {

  @Override
  public void map(ResultSet resultSet, ProductPagedResponseData productPagedResponseData) {

    try {

      while (resultSet.next()) {

        if (resultSet.isFirst())
          productPagedResponseData.setItemsCount(resultSet.getInt("item_count"));

        ProductResponseData productItem = new ProductResponseData();

        productItem.setIsbn(resultSet.getString("isbn"));
        productItem.setTitle(resultSet.getString("title"));
        productItem.setAuthor(resultSet.getString("author"));
        productItem.setEdition(resultSet.getInt("edition"));
        productItem.setLanguage(new LanguageResponseData(resultSet.getString("lng_cd"), resultSet.getString("lng_nm")));
        productItem.setGenres(new ArrayList<>());
        productItem.setDescription(resultSet.getString("description"));
        productItem.setDateOfRelease(resultSet.getDate("date_of_release"));
        productItem.setPrice(resultSet.getDouble("price"));
        productItem.setThumbnail(resultSet.getBytes("thumbnail"));

        productPagedResponseData.getItems().add(productItem);
      }
    }
    catch (SQLException e) {
      // TODO: robust exception handling
      e.printStackTrace();
    }
  }
}
