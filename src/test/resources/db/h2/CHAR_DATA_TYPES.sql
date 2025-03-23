DROP TABLE IF EXISTS TEST.CHAR_DATA_TYPES;
CREATE TABLE TEST.CHAR_DATA_TYPES
(

    -- with default size of 1 character
    CHAR_COL                                                CHAR,
    CHAR_VARYING_COL                                        CHAR VARYING,
    CHARACTER_COL                                           CHARACTER,
    CHARACTER_VARYING_COL                                   CHARACTER VARYING,
    VARCHAR_COL                                             VARCHAR,
    VARCHAR_CASESENSITIVE_COL                               VARCHAR_CASESENSITIVE,
    VARCHAR_IGNORECASE_COL                                  VARCHAR_IGNORECASE,
    NCHAR_COL                                               NCHAR,
    NCHAR_VARYING_COL                                       NCHAR VARYING,
    NATIONAL_CHAR_VARYING_COL                               NATIONAL CHAR VARYING,
    NATIONAL_CHARACTER_VARYING_COL                          NATIONAL CHARACTER VARYING,

    -- with size of 1 character
    CHAR_1_COL                                              CHAR(1),
    CHAR_VARYING_1_COL                                      CHAR VARYING(1),
    CHARACTER_1_COL                                         CHARACTER(1),
    CHARACTER_VARYING_1_COL                                 CHARACTER VARYING(1),
    VARCHAR_1_COL                                           VARCHAR(1),
    VARCHAR_CASESENSITIVE_1_COL                             VARCHAR_CASESENSITIVE(1),
    VARCHAR_IGNORECASE_1_COL                                VARCHAR_IGNORECASE(1),
    NCHAR_1_COL                                             NCHAR(1),
    NCHAR_VARYING_1_COL                                     NCHAR VARYING(1),
    NATIONAL_CHAR_VARYING_1_COL                             NATIONAL CHAR VARYING(1),
    NATIONAL_CHARACTER_VARYING_1_COL                        NATIONAL CHARACTER VARYING(1),

    -- with maximum size of 1000000000 characters
    CHAR_1000000000_COL                                     CHAR(1000000000),
    CHAR_VARYING_1000000000_COL                             CHAR VARYING(1000000000),
    CHARACTER_1000000000_COL                                CHARACTER(1000000000),
    CHARACTER_VARYING_1000000000_COL                        CHARACTER VARYING(1000000000),
    VARCHAR_1000000000_COL                                  VARCHAR(1000000000),
    VARCHAR_CASESENSITIVE_1000000000_COL                    VARCHAR_CASESENSITIVE(1000000000),
    VARCHAR_IGNORECASE_1000000000_COL                       VARCHAR_IGNORECASE(1000000000),
    NCHAR_1000000000_COL                                    NCHAR(1000000000),
    NCHAR_VARYING_1000000000_COL                            NCHAR VARYING(1000000000),
    NATIONAL_CHAR_VARYING_1000000000_COL                    NATIONAL CHAR VARYING(1000000000),
    NATIONAL_CHARACTER_VARYING_1000000000_COL               NATIONAL CHARACTER VARYING(1000000000),

    -- with default size of 1 character
    CLOB_COL                                                CLOB,
    CHAR_LARGE_OBJECT_COL                                   CHAR LARGE OBJECT,
    CHARACTER_LARGE_OBJECT_COL                              CHARACTER LARGE OBJECT,
    NATIONAL_CHARACTER_LARGE_OBJECT_COL                     NATIONAL CHARACTER LARGE OBJECT,
    NCLOB_COL                                               NCLOB,

    -- with size of 1 character
    CLOB_1_COL                                              CLOB(1),
    CHAR_LARGE_OBJECT_1_COL                                 CHAR LARGE OBJECT(1),
    CHARACTER_LARGE_OBJECT_1_COL                            CHARACTER LARGE OBJECT(1),
    NATIONAL_CHARACTER_LARGE_OBJECT_1_COL                   NATIONAL CHARACTER LARGE OBJECT(1),
    NCLOB_1_COL                                             NCLOB(1),

    -- with maximum size
    CLOB_9223372036854775807_COL                            CLOB(9223372036854775807),
    CHAR_LARGE_OBJECT_9223372036854775807_COL               CHAR LARGE OBJECT(9223372036854775807),
    CHARACTER_LARGE_OBJECT_9223372036854775807_COL          CHARACTER LARGE OBJECT(9223372036854775807),
    NATIONAL_CHARACTER_LARGE_OBJECT_9223372036854775807_COL NATIONAL CHARACTER LARGE OBJECT(9223372036854775807),
    NCLOB_9223372036854775807_COL                           NCLOB(9223372036854775807)

)