DROP TABLE IF EXISTS TEST.NON_UNIQUE_INDEX_ASC
/
CREATE TABLE TEST.NON_UNIQUE_INDEX_ASC
(
    COL_1 VARCHAR(10) NOT NULL
)
/
CREATE INDEX TEST.NON_UNIQUE_IDX_ASC ON TEST.NON_UNIQUE_INDEX_ASC (COL_1 ASC)
/