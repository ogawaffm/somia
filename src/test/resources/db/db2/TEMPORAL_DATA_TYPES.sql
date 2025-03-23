DROP TABLE IF EXISTS TEST.TEMPORAL_DATA_TYPES;
CREATE TABLE TEST.TEMPORAL_DATA_TYPES
(
    DATE_COL         DATE,

    TIME_COL         TIME,

    -- DB2 has no second precision for TIME

    -- DB2 has no TIME WITHOUT TIME ZONE
    -- DB2 has no TIME WITH TIME ZONE

    -- default precision of 6
    TIMESTAMP_COL    TIMESTAMP,
    TIMESTAMP_0_COL  TIMESTAMP(0),
    TIMESTAMP_1_COL  TIMESTAMP(1),

    -- DB2 has no fractional seconds precision of 12
    TIMESTAMP_12_COL TIMESTAMP(12)

)