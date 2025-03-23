DROP TABLE IF EXISTS TEST.NUMERIC_DATA_TYPES;
CREATE TABLE TEST.NUMERIC_DATA_TYPES
(
    SMALLINT_COL         SMALLINT,
    INT_COL              INT,
    INTEGER_COL          INTEGER,
    BIGINT_COL           BIGINT,

    REAL_COL             REAL,

    FLOAT_COL            FLOAT,
    FLOAT_1_COL          FLOAT(1),
    FLOAT_24_COL         FLOAT(24),
    FLOAT_25_COL         FLOAT(25),
    FLOAT_53_COL         FLOAT(53),

    DOUBLE_COL           DOUBLE,
    DOUBLE_PRECISION_COL DOUBLE PRECISION,

    DECFLOAT_COL         DECFLOAT,
    DECFLOAT_16_COL      DECFLOAT(16),
    DECFLOAT_34_COL      DECFLOAT(34),

    DEC_COL              DEC,
    DECIMAL_COL          DECIMAL,
    NUMERIC_COL          NUMERIC,
    NUM_COL              NUM,

    DEC_1_COL            DEC(1),
    DECIMAL_1_COL        DECIMAL(1),
    NUMERIC_1_COL        NUMERIC(1),
    NUM_1_COL            NUM(1),

    DEC_1_0_COL          DEC(1, 0),
    DECIMAL_1_0_COL      DECIMAL(1, 0),
    NUMERIC_1_0_COL      NUMERIC(1, 0),
    NUM_1_0_COL          NUM(1, 0),

    DEC_1_1_COL          DEC(1, 1),
    DECIMAL_1_1_COL      DECIMAL(1, 1),
    NUMERIC_1_1_COL      NUMERIC(1, 1),
    NUM_1_1_COL          NUM(1, 1),

    DEC_31_COL           DEC(31),
    DECIMAL_31_COL       DECIMAL(31),
    NUMERIC_31_COL       NUMERIC(31),
    NUM_31_COL           NUM(31),

    DEC_31_0_COL         DEC(31, 0),
    DECIMAL_31_0_COL     DECIMAL(31, 0),
    NUMERIC_31_0_COL     NUMERIC(31, 0),
    NUM_31_0_COL         NUM(31, 0),

    DEC_31_1_COL         DEC(31, 1),
    DECIMAL_31_1_COL     DECIMAL(31, 1),
    NUMERIC_31_1_COL     NUMERIC(31, 1),
    NUM_31_1_COL         NUM(31, 1),

    DEC_31_31_COL        DEC(31, 31),
    DECIMAL_31_31_COL    DECIMAL(31, 31),
    NUMERIC_31_31_COL    NUMERIC(31, 31),
    NUM_31_31_COL        NUM(31, 31)

)