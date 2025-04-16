DROP TABLE IF EXISTS TEST.UNIQUE_INDEX_ASC_INCLUDE
/
CREATE TABLE TEST.UNIQUE_INDEX_ASC_INCLUDE
(
    COL_1 VARCHAR(10) NOT NULL,
    COL_2 VARCHAR(10) NOT NULL
)
/
CREATE UNIQUE INDEX TEST.UNIQUE_IDX_ASC_INCLUDE ON TEST.UNIQUE_INDEX_ASC_INCLUDE (COL_1 ASC) INCLUDE (COL_2)
/