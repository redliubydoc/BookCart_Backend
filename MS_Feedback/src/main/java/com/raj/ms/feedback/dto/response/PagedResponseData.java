package com.raj.ms.feedback.dto.response;

import java.util.List;

public interface PagedResponseData<E> {

  public List<E> getItems();

  public int getPageNo();

  public int getPageSz();

  public int getItemsCount();
}
