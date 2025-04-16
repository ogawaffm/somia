BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE TEST.CHAR_DATA_TYPES PURGE';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;
/
CREATE TABLE TEST.CHAR_DATA_TYPES
(

    -- with default size of 1 character
    CHAR_COL                                  CHAR,
    CHARACTER_COL                             CHARACTER,

    NCHAR_COL                                 NCHAR,
    NATIONAL_CHAR_COL                         NATIONAL CHAR,
    NATIONAL_CHARACTER_COL                    NATIONAL CHARACTER,

    -- with size of 1 character
    CHAR_1_COL                                CHAR(1),
    CHAR_VARYING_1_COL                        CHAR VARYING(1),
    CHARACTER_1_COL                           CHARACTER(1),
    CHARACTER_VARYING_1_COL                   CHARACTER VARYING(1),
    VARCHAR_1_COL                             VARCHAR(1),
    VARCHAR2_1_COL                            VARCHAR2(1),
    NCHAR_1_COL                               NCHAR(1),
    NCHAR_VARYING_1_COL                       NCHAR VARYING(1),
    NATIONAL_CHAR_VARYING_1_COL               NATIONAL CHAR VARYING(1),
    NATIONAL_CHARACTER_VARYING_1_COL          NATIONAL CHARACTER VARYING(1),
    NVARCHAR2_1_COL                           NVARCHAR2(1),

    -- with maximum size of 2000 characters
    CHAR_2000_COL                       CHAR(2000),
    CHAR_VARYING_2000_COL               CHAR VARYING(2000),
    CHARACTER_2000_COL                  CHARACTER(2000),
    CHARACTER_VARYING_2000_COL          CHARACTER VARYING(2000),
    VARCHAR_2000_COL                    VARCHAR(2000),
    NCHAR_1000_COL                      NCHAR(1000),
    NCHAR_VARYING_2000_COL              NCHAR VARYING(2000),
    NATIONAL_CHAR_VARYING_2000_COL      NATIONAL CHAR VARYING(2000),
    NATIONAL_CHARACTER_VARYING_2000_COL NATIONAL CHARACTER VARYING(2000),

    LONG_COL                            LONG,

    /* Since LONG VARCHAR also maps to Oracle LONG, it is not possible to use
       LONG VARCHAR aside from LONG in one table. For LONG VARCHAR, see
       OTHER_DATA_TYPES.sql.

       LONG_VARCHAR_COL                          LONG VARCHAR,

     */


    -- CLOB and NCLOB have no dimensions
    CLOB_COL                                  CLOB,
    NCLOB_COL                                 NCLOB

)