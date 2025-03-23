DROP TABLE IF EXISTS TEST.OTHER_DATA_TYPES;
CREATE TABLE TEST.OTHER_DATA_TYPES
(

    BOOLEAN_COL                BOOLEAN,
    UUID_COL                   UUID,

    JAVA_OBJECT_COL            JAVA_OBJECT,
    JAVA_OBJECT_1_COL          JAVA_OBJECT(1),
    JAVA_OBJECT_1000000000_COL JAVA_OBJECT(1000000000),

    OBJECT_COL                 OBJECT,
    OBJECT_1_COL               OBJECT(1),
    OBJECT_1000000000_COL      OBJECT(1000000000),

    OTHER_COL                  OTHER,
    OTHER_1_COL                OTHER(1),
    OTHER_1000000000_COL       OTHER(1000000000),

    JSON_COL                   JSON,
    JSON_1_COL                 JSON(1),
    JSON_1000000000_COL        JSON(1000000000),

    ENUM_A_B_C_COL             ENUM ('A', 'B', 'C')

    -- H2 does not support XML data type

)