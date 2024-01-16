package com.raj.ms.product.service;

import com.raj.ms.product.dao.ProductDAO;
import com.raj.ms.product.dto.request.ProductAdvanceSearchRequestDTO;
import com.raj.ms.product.dto.response.GenreResponseData;
import com.raj.ms.product.dto.response.LanguageResponseData;
import com.raj.ms.product.dto.response.ProductPagedResponseData;
import com.raj.ms.product.dto.response.ProductResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

  private final ProductDAO productDAO;

  @Autowired
  public ProductService(ProductDAO productDAO) {
    this.productDAO = productDAO;
  }

  public void getProducts(ProductAdvanceSearchRequestDTO advanceSearch, ProductPagedResponseData responseData) {
    productDAO.getProducts(advanceSearch, responseData);
  }

  public void getGenres(List<GenreResponseData> responseData) {
    productDAO.getGenres(responseData);
  }

  public void getLanguages(List<LanguageResponseData> responseData) {
    productDAO.getLanguages(responseData);
  }

  public void getProductByIsbn(ProductResponseData responseData) {
    productDAO.getProductByIsbn(responseData);
  }
}
