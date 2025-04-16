BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE TEST.XML_DATA_TYPES';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;
/
CREATE TABLE TEST.XML_DATA_TYPES
(
    XMLTYPE_COL     XMLTYPE,
    URITYPE_COL     URITYPE,
    DBURITYPE_COL   DBURITYPE,
    XDBURITYPE_COL  XDBURITYPE,
    HTTPURITYPE_COL HTTPURITYPE

)