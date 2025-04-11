package org.velohaven.somia.jdbc.resultset.databasemetadata.reader;

import lombok.NonNull;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.AttributeReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.BestIdentifierReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.CatalogReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.ClientInfoPropertyReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.ColumnPrivilegeReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.ColumnReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.CrossReferenceColumnReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.ExportedKeyColumnReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.FunctionColumnReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.FunctionReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.ImportedKeyColumnReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.IndexInfoReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.PrimaryKeyReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.ProcedureColumnReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.ProcedureReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.PseudoColumnReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.SchemaReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.SuperTableReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.SuperTypeReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.TablePrivilegeReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.TableReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.TableTypeReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.TypeInfoReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.UDTReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.VersionColumnReader;

import java.sql.ResultSet;

public class RdbmsAwareReaderFactory implements MetadataReaderFactory {

    public AttributeReader createAttributeRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createAttributeRowReader(resultSet, strict);
    }

    public BestIdentifierReader createBestRowIdentifierRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createBestRowIdentifierRowReader(resultSet, strict);
    }

    public CatalogReader createCatalogRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createCatalogRowReader(resultSet, strict);
    }

    public ClientInfoPropertyReader createClientInfoPropertyRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createClientInfoPropertyRowReader(resultSet, strict);
    }

    public ColumnPrivilegeReader createColumnPrivilegeRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createColumnPrivilegeRowReader(resultSet, strict);
    }

    public ColumnReader createColumnRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createColumnRowReader(resultSet, strict);
    }

    public CrossReferenceColumnReader createCrossReferenceColumnRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createCrossReferenceColumnRowReader(resultSet, strict);
    }

    public ExportedKeyColumnReader createExportedKeyColumnRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createExportedKeyColumnRowReader(resultSet, strict);
    }

    public FunctionColumnReader createFunctionColumnRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createFunctionColumnRowReader(resultSet, strict);
    }

    public FunctionReader createFunctionRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createFunctionRowReader(resultSet, strict);
    }

    public ImportedKeyColumnReader createImportedKeyColumnRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createImportedKeyColumnRowReader(resultSet, strict);
    }

    public IndexInfoReader createIndexInfoRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createIndexInfoRowReader(resultSet, strict);
    }

    public PrimaryKeyReader createPrimaryKeyRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createPrimaryKeyRowReader(resultSet, strict);
    }

    public ProcedureColumnReader createProcedureColumnRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createProcedureColumnRowReader(resultSet, strict);
    }

    public PrimaryKeyReader createPrimaryKeyRowReader(@NonNull final ResultSet resultSet) {
        return createPrimaryKeyRowReader(resultSet, false);
    }

    public ProcedureColumnReader createProcedureColumnRowReader(@NonNull final ResultSet resultSet) {
        return createProcedureColumnRowReader(resultSet, false);
    }

    public ProcedureReader createProcedureRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createProcedureRowReader(resultSet, strict);
    }

    public PseudoColumnReader createPseudoColumnRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createPseudoColumnRowReader(resultSet, strict);
    }

    public SchemaReader createSchemaRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createSchemaRowReader(resultSet, strict);
    }

    public SuperTableReader createSuperTableRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createSuperTableRowReader(resultSet, strict);
    }

    public SuperTypeReader createSuperTypeRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createSuperTypeRowReader(resultSet, strict);
    }

    public TablePrivilegeReader createTablePrivilegeRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createTablePrivilegeRowReader(resultSet, strict);
    }

    public TableReader createTableRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createTableRowReader(resultSet, strict);
    }

    public TablePrivilegeReader createTablePrivilegeRowReader(@NonNull final ResultSet resultSet) {
        return createTablePrivilegeRowReader(resultSet, false);
    }

    public TypeInfoReader createTypeInfoRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createTypeInfoRowReader(resultSet, strict);
    }

    public TableTypeReader createTableTypeRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createTableTypeRowReader(resultSet, strict);
    }

    public UDTReader createUDTRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createUDTRowReader(resultSet, strict);
    }

    public VersionColumnReader createVersionColumnRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return MetadataReaderFactory.createRowReaderFactory(resultSet).createVersionColumnRowReader(resultSet, strict);
    }

}
