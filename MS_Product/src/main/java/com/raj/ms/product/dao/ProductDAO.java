package com.raj.ms.product.dao;

import com.raj.ms.product.constant.QueryConstant;
import com.raj.ms.product.dao.mapper.GenreListMapper;
import com.raj.ms.product.dao.mapper.LanguageListMapper;
import com.raj.ms.product.dao.mapper.ProductMapper;
import com.raj.ms.product.dao.mapper.ProductPageMapper;
import com.raj.ms.product.db.util.PreparedStatementRunner;
import com.raj.ms.product.db.util.QueryBuilder;
import com.raj.ms.product.dto.request.ProductAdvanceSearchRequestDTO;
import com.raj.ms.product.dto.response.GenreResponseData;
import com.raj.ms.product.dto.response.LanguageResponseData;
import com.raj.ms.product.dto.response.ProductPagedResponseData;
import com.raj.ms.product.dto.response.ProductResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class ProductDAO {

  private final DataSource dataSource;
  private final PreparedStatementRunner preparedStatementRunner;
  private final ProductPageMapper productPageMapper;
  private final GenreListMapper genreListMapper;
  private final LanguageListMapper languageListMapper;
  private final ProductMapper productMapper;

  @Autowired
  public ProductDAO(
    DataSource dataSource,
    PreparedStatementRunner preparedStatementRunner,
    ProductPageMapper productPageMapper,
    GenreListMapper genreListMapper,
    LanguageListMapper languageListMapper,
    ProductMapper productMapper
  ) {

    this.dataSource = dataSource;
    this.preparedStatementRunner = preparedStatementRunner;
    this.productPageMapper = productPageMapper;
    this.genreListMapper = genreListMapper;
    this.languageListMapper = languageListMapper;
    this.productMapper = productMapper;
  }

  public void getProducts(ProductAdvanceSearchRequestDTO advanceSearch, ProductPagedResponseData responseData) {

    boolean whereConditionsAndFlag = false;
    String whereConditions = "";
    String orderByConditions = "";

    // snippet for filtering starts here
    if (advanceSearch.getFilterBy() != null) {

      for (ProductAdvanceSearchRequestDTO.Filter filter : advanceSearch.getFilterBy()) {

        switch (filter.getType()) {

          case LANGUAGE -> {

            if (whereConditionsAndFlag) {
              whereConditions += " and lng_cd = '" + filter.getQuery() + "'";
            }
            else {
              whereConditionsAndFlag = true;
              whereConditions += " lng_cd = '" + filter.getQuery() + "'";
            }

          }
          case GENRE -> {

            String innerQuery = "select isbn from tbook_genre where gen_cd = '" + filter.getQuery() + "'";

            if (whereConditionsAndFlag) {
              whereConditions += " and isbn in (" + innerQuery + ")";
            }
            else {
              whereConditionsAndFlag = true;
              whereConditions += "isbn in (" + innerQuery + ")";
            }

          }
          case PRICE -> {

            int lowerLimit = Integer.parseInt(filter.getQuery().strip().split(":")[0]);
            int upperLimit = Integer.parseInt(filter.getQuery().strip().split(":")[1]);

            if (whereConditionsAndFlag) {
              whereConditions += " and price >= " + lowerLimit + " and price <= " + upperLimit;
            }
            else {
              whereConditionsAndFlag = true;
              whereConditions += "price >= " + lowerLimit + " and price <= " + upperLimit;
            }

          }
        }
      }
    }
    // snippet for filtering ends here

    // snippet for sorting starts here
    if (advanceSearch.getSortBy() != null) {
      switch (advanceSearch.getSortBy().getType()) {

        case PRICE -> orderByConditions = "price " + (
          advanceSearch.getSortBy().getOrder() == ProductAdvanceSearchRequestDTO.SortOrder.ASC ? "asc" : "desc"
        );
        case RATING, DATE_OF_RELEASE -> orderByConditions = "date_of_release " + (
          advanceSearch.getSortBy().getOrder() == ProductAdvanceSearchRequestDTO.SortOrder.ASC ? "asc" : "desc"
        );

      }
    }
    else {
      orderByConditions = "date_of_release desc";
    }
    // snippet for sorting ends here

    if (advanceSearch.getSearchBy() != null) {

      String query = advanceSearch.getSearchBy().getQuery().toLowerCase();

      switch (advanceSearch.getSearchBy().getType()) {

        case ISBN -> {
          if (whereConditionsAndFlag) {
            whereConditions += " and isbn like '" + query + "%'";
          }
          else {
            whereConditionsAndFlag = true;
            whereConditions += "isbn like '" + query + "%'";
          }
        }
        case TITLE -> {
          if (whereConditionsAndFlag) {
            whereConditions += " and lower(title) like '" + query + "%'";
          }
          else {
            whereConditionsAndFlag = true;
            whereConditions += "lower(title) like '" + query + "%'";
          }
        }
        case AUTHOR -> {
          if (whereConditionsAndFlag) {
            whereConditions += " and lower(author) like '" + query + "%'";
          }
          else {
            whereConditionsAndFlag = true;
            whereConditions += "lower(author) like '" + query + "%'";
          }
        }
        case GENRE -> {

          String innerQuery = "select distinct a.isbn from tbook_genre a, tgenre b where a.gen_cd = b.gen_cd and lower(b.gen_nm) like '" + query + "%'";

          if (whereConditionsAndFlag) {
            whereConditions += " and isbn in (" + innerQuery + ")";
          }
          else {
            whereConditionsAndFlag = true;
            whereConditions += "isbn in (" + innerQuery + ")";
          }
        }

      }

    }

    String query1 = QueryConstant.GET_PRODUCTS;
    query1 = QueryBuilder.filterBy(query1, whereConditions);
    query1 = QueryBuilder.orderBy(query1, orderByConditions);
    query1 = QueryBuilder.paginateBy(query1, responseData.getPageNo(), responseData.getPageSz());

    preparedStatementRunner.executeQuery(query1, dataSource, productPageMapper, responseData);

    for (ProductResponseData item : responseData.getItems()) {

      String query2 = QueryConstant.GET_GENRES_BY_ISBN;

      Object[] params2 = new Object[1];
      params2[0] = item.getIsbn();

      preparedStatementRunner.executeQuery(query2, params2, dataSource, genreListMapper, item.getGenres());
    }
  }

  public void getProductByIsbn(ProductResponseData responseData) {

    String query1 = QueryConstant.GET_PRODUCT_BY_ISBN;

    Object[] params1 = new Object[1];
    params1[0] = responseData.getIsbn();

    preparedStatementRunner.executeQuery(query1, params1, dataSource, productMapper, responseData);

    String query2 = QueryConstant.GET_GENRES_BY_ISBN;

    Object[] params2 = new Object[1];
    params2[0] = responseData.getIsbn();

    preparedStatementRunner.executeQuery(query2, params2, dataSource, genreListMapper, responseData.getGenres());
  }

  public void getGenres(List<GenreResponseData> responseData) {
    preparedStatementRunner.executeQuery(QueryConstant.GET_GENRES, dataSource, genreListMapper, responseData);
  }

  public void getLanguages(List<LanguageResponseData> responseData) {
    preparedStatementRunner.executeQuery(QueryConstant.GET_LANGUAGES, dataSource, languageListMapper, responseData);
  }
}
