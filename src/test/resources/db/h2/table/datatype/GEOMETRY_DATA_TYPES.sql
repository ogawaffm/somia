DROP TABLE IF EXISTS TEST.GEOMETRY_DATA_TYPES
/
CREATE TABLE TEST.GEOMETRY_DATA_TYPES
(
    GEOMETRY_COL                      GEOMETRY,

    -- point
    GEOMETRY_POINT_COL                GEOMETRY(POINT),
    GEOMETRY_POINT_4326_COL           GEOMETRY(POINT, 4326),

    GEOMETRY_POINTM_COL               GEOMETRY(POINTM),
    GEOMETRY_POINTM_4326_COL          GEOMETRY(POINTM, 4326),

    GEOMETRY_POINT_M_COL              GEOMETRY(POINT M),
    GEOMETRY_POINT_M_4326_COL         GEOMETRY(POINT M, 4326),

    GEOMETRY_POINTZ_COL               GEOMETRY(POINTZ),
    GEOMETRY_POINTZ_4326_COL          GEOMETRY(POINTZ, 4326),

    GEOMETRY_POINT_Z_COL              GEOMETRY(POINT Z),
    GEOMETRY_POINT_Z_4326_COL         GEOMETRY(POINT Z, 4326),

    -- linestring
    GEOMETRY_LINESTRING_COL           GEOMETRY(LINESTRING),
    GEOMETRY_LINESTRING_4326_COL      GEOMETRY(LINESTRING, 4326),

    GEOMETRY_LINESTRINGM_COL          GEOMETRY(LINESTRINGM),
    GEOMETRY_LINESTRINGM_4326_COL     GEOMETRY(LINESTRINGM, 4326),

    GEOMETRY_LINESTRING_M_COL         GEOMETRY(LINESTRING M),
    GEOMETRY_LINESTRING_M_4326_COL    GEOMETRY(LINESTRING M, 4326),

    GEOMETRY_LINESTRINGZ_COL          GEOMETRY(LINESTRINGZ),
    GEOMETRY_LINESTRINGZ_4326_COL     GEOMETRY(LINESTRINGZ, 4326),

    GEOMETRY_LINESTRING_Z_COL         GEOMETRY(LINESTRING Z),
    GEOMETRY_LINESTRING_Z_4326_COL    GEOMETRY(LINESTRING Z, 4326),

    -- polygon
    GEOMETRY_POLYGON_COL              GEOMETRY(POLYGON),
    GEOMETRY_POLYGON_4326_COL         GEOMETRY(POLYGON, 4326),

    GEOMETRY_POLYGONM_COL             GEOMETRY(POLYGONM),
    GEOMETRY_POLYGON_M4326_COL        GEOMETRY(POLYGONM, 4326),

    GEOMETRY_POLYGON_M_COL      GEOMETRY(POLYGON M),
    GEOMETRY_POLYGON_M_4326_COL GEOMETRY(POLYGON M, 4326),

    GEOMETRY_POLYGONZ_COL      GEOMETRY(POLYGONZ),
    GEOMETRY_POLYGON_Z4326_COL GEOMETRY(POLYGONZ, 4326),

    GEOMETRY_POLYGON_Z_COL      GEOMETRY(POLYGON Z),
    GEOMETRY_POLYGON_Z_4326_COL GEOMETRY(POLYGON Z, 4326),

    -- multipoint
    GEOMETRY_MULTIPOINT_COL      GEOMETRY(MULTIPOINT),
    GEOMETRY_MULTIPOINT_4326_COL GEOMETRY(MULTIPOINT, 4326),

    GEOMETRY_MULTIPOINTM_COL      GEOMETRY(MULTIPOINTM),
    GEOMETRY_MULTIPOINTM_4326_COL GEOMETRY(MULTIPOINTM, 4326),

    GEOMETRY_MULTIPOINT_M_COL      GEOMETRY(MULTIPOINT M),
    GEOMETRY_MULTIPOINT_M_4326_COL GEOMETRY(MULTIPOINT M, 4326),

    GEOMETRY_MULTIPOINTZ_COL       GEOMETRY(MULTIPOINTZ),
    GEOMETRY_MULTIPOINTZ_4326_COL  GEOMETRY(MULTIPOINTZ, 4328),

    GEOMETRY_MULTIPOINT_Z_COL      GEOMETRY(MULTIPOINT Z),
    GEOMETRY_MULTIPOINT_Z_4326_COL GEOMETRY(MULTIPOINT Z, 4326),

    -- multilinestring
    GEOMETRY_MULTILINESTRING_COL      GEOMETRY(MULTILINESTRING),
    GEOMETRY_MULTILINESTRING_4326_COL GEOMETRY(MULTILINESTRING, 4326),

    GEOMETRY_MULTILINESTRINGM_COL      GEOMETRY(MULTILINESTRINGM),
    GEOMETRY_MULTILINESTRINGM_4326_COL GEOMETRY(MULTILINESTRINGM, 4326),

    GEOMETRY_MULTILINESTRING_M_COL      GEOMETRY(MULTILINESTRING M),
    GEOMETRY_MULTILINESTRING_M_4326_COL GEOMETRY(MULTILINESTRING M, 4326),

    GEOMETRY_MULTILINESTRINGZ_COL      GEOMETRY(MULTILINESTRINGZ),
    GEOMETRY_MULTILINESTRINGZ_4326_COL GEOMETRY(MULTILINESTRINGZ, 4326),

    GEOMETRY_MULTILINESTRING_Z_COL      GEOMETRY(MULTILINESTRING Z),
    GEOMETRY_MULTILINESTRING_Z_4326_COL GEOMETRY(MULTILINESTRING Z, 4326),

    -- multipolygon
    GEOMETRY_MULTIPOLYGON_COL       GEOMETRY(MULTIPOLYGON),
    GEOMETRY_MULTIPOLYGON_4326_COL       GEOMETRY(MULTIPOLYGON, 4326),

    GEOMETRY_MULTIPOLYGONM_COL      GEOMETRY(MULTIPOLYGONM),
    GEOMETRY_MULTIPOLYGONM_4326_COL GEOMETRY(MULTIPOLYGONM, 4326),

    GEOMETRY_MULTIPOLYGON_M_COL      GEOMETRY(MULTIPOLYGON M),
    GEOMETRY_MULTIPOLYGON_M_4326_COL GEOMETRY(MULTIPOLYGON M, 4326),

    GEOMETRY_MULTIPOLYGONZ_COL      GEOMETRY(MULTIPOLYGONZ),
    GEOMETRY_MULTIPOLYGONZ_4326_COL GEOMETRY(MULTIPOLYGONZ, 4326),

    GEOMETRY_MULTIPOLYGON_Z_COL      GEOMETRY(MULTIPOLYGON Z),
    GEOMETRY_MULTIPOLYGON_Z_4326_COL GEOMETRY(MULTIPOLYGON Z, 4326),

    -- geometrycollection
    GEOMETRY_GEOMETRYCOLLECTION_COL      GEOMETRY(GEOMETRYCOLLECTION),
    GEOMETRY_GEOMETRYCOLLECTION_4236_COL GEOMETRY(GEOMETRYCOLLECTION, 4326),

    GEOMETRY_GEOMETRYCOLLECTIONM_COL      GEOMETRY(GEOMETRYCOLLECTIONM),
    GEOMETRY_GEOMETRYCOLLECTIONM_4236_COL GEOMETRY(GEOMETRYCOLLECTIONM, 4236),

    GEOMETRY_GEOMETRYCOLLECTION_M_COL      GEOMETRY(GEOMETRYCOLLECTION M),
    GEOMETRY_GEOMETRYCOLLECTION_M_4236_COL GEOMETRY(GEOMETRYCOLLECTION M, 4236),

    GEOMETRY_GEOMETRYCOLLECTIONZ_COL      GEOMETRY(GEOMETRYCOLLECTIONZ),
    GEOMETRY_GEOMETRYCOLLECTIONZ_4326_COL GEOMETRY(GEOMETRYCOLLECTIONZ, 4326),

    GEOMETRY_GEOMETRYCOLLECTION_Z_COL      GEOMETRY(GEOMETRYCOLLECTION Z),
    GEOMETRY_GEOMETRYCOLLECTION_Z_4326_COL GEOMETRY(GEOMETRYCOLLECTION Z, 4326)
)
