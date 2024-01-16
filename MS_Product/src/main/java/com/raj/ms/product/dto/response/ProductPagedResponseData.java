package com.raj.ms.product.dto.response;

import java.util.List;

public class ProductPagedResponseData implements PagedResponseData<ProductResponseData> {

  private List<ProductResponseData> items;
  private int pageNo;
  private int pageSz;
  private int itemsCount;

  @Override
  public List<ProductResponseData> getItems() {
    return items;
  }

  public void setItems(List<ProductResponseData> items) {
    this.items = items;
  }

  @Override
  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  @Override
  public int getPageSz() {
    return pageSz;
  }

  public void setPageSz(int pageSz) {
    this.pageSz = pageSz;
  }

  @Override
  public int getItemsCount() {
    return itemsCount;
  }

  public void setItemsCount(int itemsCount) {
    this.itemsCount = itemsCount;
  }


}
