package com.raj.ms.product.constant;

public class QueryConstant {

  public static final String GET_PRODUCTS =
    """
    select
      a.isbn,
      a.title,
      a.author,
      a.edition,
      a.lng_cd,
      b.lng_nm,
      a.description,
      a.date_of_release,
      a.price,
      a.thumbnail
    from
      tbook a,
      tlanguage b
    where
      a.lng_cd = b.lng_cd
    """;

  public static final String GET_PRODUCT_BY_ISBN =
    """
    select
      a.isbn,
      a.title,
      a.author,
      a.edition,
      a.lng_cd,
      b.lng_nm,
      a.description,
      a.date_of_release,
      a.price,
      a.thumbnail
    from
      tbook a,
      tlanguage b
    where
      a.lng_cd = b.lng_cd and
      a.isbn = ?
    """;

  public static final String GET_GENRES_BY_ISBN =
    """
    select
      b.isbn,
      a.gen_cd,
      a.gen_nm
    from
      tgenre a,
      tbook_genre b
    where
      a.gen_cd = b.gen_cd and
      b.isbn = ?
    """;

  public static final String GET_GENRES =
    """
    select distinct
      a.gen_cd,
      a.gen_nm
    from
      tgenre a,
      tbook_genre b
    where
      a.gen_cd = b.gen_cd
    """;

  public static final String GET_LANGUAGES =
    """
    select distinct
      a.lng_cd,
      a.lng_nm
    from
      tlanguage a,
      tbook b
    where
      a.lng_cd = b.lng_cd
    """;
}
