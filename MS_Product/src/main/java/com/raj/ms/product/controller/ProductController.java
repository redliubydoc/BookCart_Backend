package com.raj.ms.product.controller;

import com.raj.ms.product.dto.request.ProductAdvanceSearchRequestDTO;
import com.raj.ms.product.dto.response.ResponseStatus;
import com.raj.ms.product.dto.response.*;
import com.raj.ms.product.service.ProductService;
import com.raj.ms.product.service.StorageService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/prdms")
public class ProductController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
  private final StorageService storageService;
  private final ProductService productService;

  @Autowired
  public ProductController(StorageService storageService, ProductService productService) {
    this.storageService = storageService;
    this.productService = productService;
  }

  @RequestMapping(method = RequestMethod.GET, path = "/language")
  public ResponseEntity<Response<List<LanguageResponseData>>> getLanguages() {

    ArrayList<LanguageResponseData> responseData = new ArrayList<>();

    productService.getLanguages(responseData);

    Response<List<LanguageResponseData>> responseBody = new Response.ResponseBuilder<List<LanguageResponseData>>()
      .setData(responseData)
      .setStatus(ResponseStatus.SUCCESS)
      .build();

    return ResponseEntity.status(200).body(responseBody);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/genre")
  public ResponseEntity<Response<List<GenreResponseData>>> getGenres() {

    ArrayList<GenreResponseData> responseData = new ArrayList<>();

    productService.getGenres(responseData);

    Response<List<GenreResponseData>> responseBody = new Response.ResponseBuilder<List<GenreResponseData>>()
      .setData(responseData)
      .setStatus(ResponseStatus.SUCCESS)
      .build();

    return ResponseEntity.status(200).body(responseBody);
  }

  @RequestMapping(method = RequestMethod.POST, path = "/product")
  public ResponseEntity<Response<ProductPagedResponseData>> getFeedbacksByIsbn(
      @RequestParam(required = false, defaultValue = "1") int pageNo,
      @RequestParam(required = false, defaultValue = "0x7fffffff") int pageSz,
      @RequestBody(required = false) ProductAdvanceSearchRequestDTO advanceSearch
  ) {

    if (advanceSearch == null) advanceSearch = new ProductAdvanceSearchRequestDTO();

    LOGGER.trace("pageNo :: " + pageNo);
    LOGGER.trace("pageSz :: " + pageSz);
    LOGGER.trace(advanceSearch.toString());

    ProductPagedResponseData responseData = new ProductPagedResponseData();
    responseData.setItems(new ArrayList<>());
    responseData.setPageNo(pageNo);
    responseData.setPageSz(pageSz);

    productService.getProducts(advanceSearch, responseData);

    Response<ProductPagedResponseData> responseBody = new Response.ResponseBuilder<ProductPagedResponseData>()
      .setData(responseData)
      .setStatus(ResponseStatus.SUCCESS)
      .build();

    return ResponseEntity.status(200).body(responseBody);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/product/{isbn}")
  public ResponseEntity<Response<ProductResponseData>> getProductByIsbn(@PathVariable String isbn) {

    ProductResponseData responseData = new ProductResponseData();
    responseData.setIsbn(isbn);

    productService.getProductByIsbn(responseData);

    Response<ProductResponseData> responseBody = new Response.ResponseBuilder<ProductResponseData>()
      .setData(responseData)
      .setStatus(ResponseStatus.SUCCESS)
      .build();

    return ResponseEntity.status(200).body(responseBody);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/product/{isbn}/8c7dd922ad47494fc02c388e12c00eac", produces = MediaType.IMAGE_JPEG_VALUE)
  public byte[] getProductFile(@PathVariable String isbn, HttpServletResponse response) throws IOException {

    return storageService.downloadFile("1WEzAhGbTcfm5zuXaOK2oEIqRTePPlCEH");
  }
}
