DROP TABLE IF EXISTS TEST.STRUCT_UDT;
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLSTATE '42704' BEGIN
    END; -- Type does not exist
    EXECUTE IMMEDIATE 'DROP TYPE TEST.STRUCT_TYPE';
END;
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLSTATE '42704' BEGIN
    END; -- Type does not exist
    EXECUTE IMMEDIATE 'DROP TYPE TEST.SUB_STRUCT_TYPE';
END;
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLSTATE '42704' BEGIN
    END; -- Type does not exist
    EXECUTE IMMEDIATE 'DROP TYPE TEST.CHAR_10_TYPE';
END;

CREATE TYPE TEST.CHAR_10_TYPE AS CHAR(10);

CREATE TYPE TEST.SUB_STRUCT_TYPE AS (
    -- BOOLEAN and LOBS are not supported as fields in a structured type
    SUB_INTEGER_COL INTEGER,
    SUB_CHAR_10_TYPE_COL TEST.CHAR_10_TYPE
    ) MODE DB2SQL;

CREATE TYPE TEST.STRUCT_TYPE AS (
    INTEGER_COL INTEGER,
    TIMESTAMP_12_COL TIMESTAMP(12),
    CHAR_10_TYPE_COL TEST.CHAR_10_TYPE,
    SUB_STRUCT_TYPE_COL TEST.SUB_STRUCT_TYPE
    ) MODE DB2SQL;


CREATE TABLE TEST.STRUCT_UDT
(
    INTEGER_COL      INTEGER,
    STRUCT_TYPE_COL  TEST.STRUCT_TYPE,
    CHAR_10_TYPE_COL TEST.CHAR_10_TYPE
)