DROP TABLE IF EXISTS TEST.BINARY_DATA_TYPES
/
CREATE TABLE TEST.BINARY_DATA_TYPES
(
    -- with default size of 1 byte
    BINARY_COL                                  BINARY,
    BINARY_VARYING_COL                          BINARY VARYING,
    VARBINARY_COL                               VARBINARY,

    -- with size of 1 byte
    BINARY_1_COL                                BINARY(1),
    BINARY_VARYING_1_COL                        BINARY VARYING(1),
    VARBINARY_1_COL                             VARBINARY(1),

    -- with maximum size of 1000000000 bytes
    BINARY_1000000000_COL                       BINARY(1000000000),
    BINARY_VARYING_1000000000_COL               BINARY VARYING(1000000000),
    VARBINARY_1000000000_COL                    VARBINARY(1000000000),

    -- with default size of 1 byte
    BLOB_COL                                    BLOB,
    BINARY_LARGE_OBJECT_COL                     BINARY LARGE OBJECT,

    -- with default size of 1 byte
    BLOB_1_COL                                  BLOB(1),
    BINARY_LARGE_OBJECT_1_COL                   BINARY LARGE OBJECT(1),

    -- with maximum size of 1 k bytes
    BLOB_1K_COL                                 BLOB(1 K),
    BINARY_LARGE_OBJECT_1K_COL                  BINARY LARGE OBJECT(1 K),

    -- with maximum size of 1000000000 bytes
    BLOB_9223372036854775807_COL                BLOB(9223372036854775807),
    BINARY_LARGE_OBJECT_9223372036854775807_COL BINARY LARGE OBJECT(9223372036854775807)

)
