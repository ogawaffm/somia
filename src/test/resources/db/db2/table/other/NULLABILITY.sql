DROP TABLE IF EXISTS TEST.NULLABILITY
/
CREATE TABLE TEST.NULLABILITY
(
    NULLABLE_COL     VARCHAR(10),
    NOT_NULLABLE_COL VARCHAR(10) NOT NULL
)