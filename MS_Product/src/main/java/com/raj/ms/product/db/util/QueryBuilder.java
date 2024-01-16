package com.raj.ms.product.db.util;

public class QueryBuilder {

  public enum OPERATION {
    EQUAL_TO,
    LESS_THAN,
    GREATER_THAN,
    LESS_THAN_EQUAL_TO,
    GREATER_THAN_EQUAL_TO,
  }

  public static String paginateBy(String innerTable, int pageNo, int pageSz) {
    String paginatedTable =
    """
    select
      paginated_table_b.*
    from
      (
        select
          paginated_table_a.*,
          rownum row_no,
          count(*) over() item_count
        from
          (
            ${innerTable}
          ) paginated_table_a
      ) paginated_table_b
    where
      paginated_table_b.row_no > ${startRowNo} and
      paginated_table_b.row_no <= ${endRowNo}
    """;

    paginatedTable = paginatedTable.replace("${innerTable}", innerTable)
      .replace("${startRowNo}", String.valueOf((pageNo - 1) * pageSz))
      .replace("${endRowNo}", String.valueOf(pageNo * pageSz));

    return paginatedTable;
  }

  public static String filterBy (String innerTable, String whereConditions) {

    if (whereConditions == null || whereConditions.trim().isEmpty()) return innerTable;

    String filteredTable =
    """
    select
      *
    from
      (
        ${innerTable}
      )
    where
      ${whereConditions}
    """;

    filteredTable = filteredTable
      .replace("${innerTable}", innerTable)
      .replace("${whereConditions}", whereConditions);

    return filteredTable;
  }

  public static String orderBy (String innerTable, String orderByConditions) {

    if (orderByConditions == null || orderByConditions.trim().isEmpty()) return innerTable;

    String filteredTable =
    """
    select
      *
    from
      (
        ${innerTable}
      )
    order by
      ${orderByConditions}
    """;

    filteredTable = filteredTable
      .replace("${innerTable}", innerTable)
      .replace("${orderByConditions}", orderByConditions);

    return filteredTable;
  }
}
