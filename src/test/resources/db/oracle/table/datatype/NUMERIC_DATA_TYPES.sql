BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE TEST.NUMERIC_DATA_TYPES';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;
/
CREATE TABLE TEST.NUMERIC_DATA_TYPES
(

    SMALLINT_COL         SMALLINT,
    INT_COL              INT,
    INTEGER_COL          INTEGER,

    REAL_COL             REAL,

    FLOAT_COL            FLOAT,
    FLOAT_1_COL          FLOAT(1),
    FLOAT_24_COL         FLOAT(24),
    FLOAT_25_COL         FLOAT(25),
    FLOAT_53_COL         FLOAT(53),
    FLOAT_126_COL        FLOAT(126),

    DOUBLE_PRECISION_COL DOUBLE PRECISION,

    BINARY_FLOAT_COL     BINARY_FLOAT,
    BINARY_DOUBLE_COL    BINARY_DOUBLE,

    NUMBER_COL           NUMBER,
    DEC_COL              DEC,
    DECIMAL_COL          DECIMAL,
    NUMERIC_COL          NUMERIC,

    NUMBER_1_COL         NUMBER(1),
    DEC_1_COL            DEC(1),
    DECIMAL_1_COL        DECIMAL(1),
    NUMERIC_1_COL        NUMERIC(1),

    NUMBER_1_0_COL       NUMBER(1, 0),
    DEC_1_0_COL          DEC(1, 0),
    DECIMAL_1_0_COL      DECIMAL(1, 0),
    NUMERIC_1_0_COL      NUMERIC(1, 0),

    NUMBER_1_1_COL       NUMBER(1, 1),
    DEC_1_1_COL          DEC(1, 1),
    DECIMAL_1_1_COL      DECIMAL(1, 1),
    NUMERIC_1_1_COL      NUMERIC(1, 1),

    NUMBER_38_COL        NUMBER(38),
    DEC_38_COL           DEC(38),
    DECIMAL_38_COL       DECIMAL(38),
    NUMERIC_38_COL       NUMERIC(38),

    NUMBER_38_0_COL      NUMBER(38, 0),
    DEC_38_0_COL         DEC(38, 0),
    DECIMAL_38_0_COL     DECIMAL(38, 0),
    NUMERIC_38_0_COL     NUMERIC(38, 0),

    NUMBER_38_1_COL      NUMBER(38, 1),
    DEC_38_1_COL         DEC(38, 1),
    DECIMAL_38_1_COL     DECIMAL(38, 1),
    NUMERIC_38_1_COL     NUMERIC(38, 1),

    NUMBER_38_38_COL     NUMBER(38, 38),
    DEC_38_38_COL        DEC(38, 38),
    DECIMAL_38_38_COL    DECIMAL(38, 38),
    NUMERIC_38_38_COL    NUMERIC(38, 38)

)