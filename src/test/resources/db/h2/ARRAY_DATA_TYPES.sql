DROP TABLE IF EXISTS TEST.ARRAY_DATA_TYPES;
CREATE TABLE TEST.ARRAY_DATA_TYPES
(

    -- data types with no dimensions
    INT_ARRAY_COL                         INT ARRAY,
    INT_ARRAY_0_COL                       INT ARRAY[0],
    INT_ARRAY_65536_COL                   INT ARRAY[65536],

    -- data types with dimensions
    TIME_ARRAY_COL                        TIME ARRAY,
    TIME_F0_ARRAY_COL                     TIME(0) ARRAY,
    TIME_F6_ARRAY_COL                     TIME(6) ARRAY,
    TIME_F9_ARRAY_COL                     TIME(9) ARRAY,

    TIME_ARRAY_0_COL                      TIME ARRAY[0],
    TIME_F0_ARRAY_0_COL                   TIME(0) ARRAY[0],
    TIME_F6_ARRAY_0_COL                   TIME(6) ARRAY[0],
    TIME_F9_ARRAY_0_COL                   TIME(9) ARRAY[0],

    TIME_ARRAY_65536_COL                  TIME ARRAY[65536],
    TIME_F0_ARRAY_65536_COL               TIME(0) ARRAY[65536],
    TIME_F6_ARRAY_65536_COL               TIME(6) ARRAY[65536],
    TIME_F9_ARRAY_65536_COL               TIME(9) ARRAY[65536],

    -- data type with 2 dimensions
    DECIMAL_ARRAY_COL                     DECIMAL ARRAY,
    DECIMAL_1_ARRAY_COL                   DECIMAL(1) ARRAY,
    DECIMAL_1_0_ARRAY_COL                 DECIMAL(1, 0) ARRAY,
    DECIMAL_100000_ARRAY_COL              DECIMAL(100000) ARRAY,
    DECIMAL_100000_0_ARRAY_COL            DECIMAL(100000, 0) ARRAY,
    DECIMAL_100000_100000_ARRAY_COL       DECIMAL(100000, 100000) ARRAY,

    DECIMAL_ARRAY_0_COL                   DECIMAL ARRAY[0],
    DECIMAL_1_ARRAY_0_COL                 DECIMAL(1) ARRAY[0],
    DECIMAL_1_0_ARRAY_0_COL               DECIMAL(1, 0) ARRAY[0],
    DECIMAL_100000_ARRAY_0_COL            DECIMAL(100000) ARRAY[0],
    DECIMAL_100000_0_ARRAY_0_COL          DECIMAL(100000, 0) ARRAY[0],
    DECIMAL_100000_100000_ARRAY_0_COL     DECIMAL(100000, 100000) ARRAY[0],

    DECIMAL_ARRAY_65536_COL               DECIMAL ARRAY[65536],
    DECIMAL_1_ARRAY_65536_COL             DECIMAL(1) ARRAY[65536],
    DECIMAL_1_0_ARRAY_65536_COL           DECIMAL(1, 0) ARRAY[65536],
    DECIMAL_100000_ARRAY_65536_COL        DECIMAL(100000) ARRAY[65536],
    DECIMAL_100000_0_ARRAY_65536_COL      DECIMAL(100000, 0) ARRAY[65536],
    DECIMAL_100000_100000_ARRAY_65536_COL DECIMAL(100000, 100000) ARRAY[65536]

)