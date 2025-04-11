package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.oracle;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.TableEntity;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.KnownMetaDataReader;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetTablesColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link TableEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getTables}.
 */
public class OracleTableReader extends org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.TableReader {

    protected OracleTableReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    @Override
    protected void transfer(TableEntity row) throws SQLException {
        row.tableCatalog = readStringByIndex(TABLE_CAT);
        row.tableSchema = readStringByIndex(TABLE_SCHEM);
        row.tableName = readStringByIndex(TABLE_NAME);
        row.tableType = readStringByIndex(TABLE_TYPE);
        row.remarks = readStringByIndex(REMARKS);

        // Oracle does not support these columns
        // see https://docs.oracle.com/en/database/oracle/oracle-database/23/jajdb/oracle/jdbc/OracleDatabaseMetaData.html#getTables(java.lang.String,java.lang.String,java.lang.String,java.lang.String%5B%5D)
        row.typeCatalog = null;
        row.typeSchema = null;
        row.typeName = null;
        row.selfReferencingColName = null;
        row.refGeneration = null;
    }

}