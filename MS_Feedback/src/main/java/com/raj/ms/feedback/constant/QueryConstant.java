package com.raj.ms.feedback.constant;

public class QueryConstant {

  public static final String GET_FEEDBACKS_BY_ISBN =
    """
    select
      b.*,
      c.*,
      count(*) over() review_count
    from
      (
        select
          a.*,
          round((avg(rating) over()), 1) average_rating,
          count(*) over() feedback_count
        from
          (
            select
              feedback_id,
              isbn,
              user_id,
              username,
              rating,
              date_of_feedback,
              review
            from
              tfeedback
            where
              isbn = ?
            order by
              date_of_feedback desc
          ) a
      ) b,
      (
        select * from (select rating from tfeedback where isbn = ?)
        pivot (count(rating) star_count for rating in (1, 2, 3, 4, 5))
      ) c
    where
      b.review is not null
    """;

  public static final String GET_FEEDBACKS_BY_ISBN_PGN =
    """
    select
      e.*
    from
      (
        select
          d.*,
          rownum row_no
        from
          (
            select
              b.*,
              c.*,
              count(*) over() review_count
            from
              (
                select
                  a.*,
                  round((avg(rating) over()), 1) average_rating,
                  count(*) over() feedback_count
                from
                  (
                    select
                      feedback_id,
                      isbn,
                      user_id,
                      username,
                      rating,
                      date_of_feedback,
                      review
                    from
                      tfeedback
                    where
                      isbn = ?
                    order by
                      date_of_feedback desc
                  ) a
              ) b,
              (
                select * from (select rating from tfeedback where isbn = ?)
                pivot (count(rating) star_count for rating in (1, 2, 3, 4, 5))
              ) c
            where
              b.review is not null
            order by
              b.date_of_feedback desc
          ) d
      ) e
    where
      e.row_no > ? and
      e.row_no <= ?
    """;

  public static final String GET_FEEDBACKS_BY_ISBN_AND_FEEDBACK_ID =
    "select * from tfeedback where isbn = ? and feedback_id = ?";

  public static final String PR_INSERT_FEEDBACK =
    "{call pk_feedback_mgnt.pr_insert_feedback(?, ?, ?, ?, ?, ?, ?)}";
}
