BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE TEST.OTHER_DATA_TYPES';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;
/
CREATE TABLE TEST.OTHER_DATA_TYPES
(
    ROWID_COL        ROWID,

    UROWID_COL       UROWID,
    UROWID_1_COL     UROWID(1),
    UROWID_4000_COL  UROWID(4000),

    BFILE_COL        BFILE,

    /* moved from CHAR_DATA_TYPES.sql because LONG and LONG VARCHAR are not
       allowed in the same table                                            */
    LONG_VARCHAR_COL LONG VARCHAR,

    BOOLEAN_COL      BOOLEAN,
    JSON_COL         JSON

)