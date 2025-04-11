package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import lombok.NonNull;

import java.sql.ResultSet;

public class StandardMetadataReaderFactory implements org.velohaven.somia.jdbc.resultset.databasemetadata.reader.MetadataReaderFactory {

    @Override
    public AttributeReader createAttributeRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new AttributeReader(resultSet, strict);
    }

    @Override
    public BestIdentifierReader createBestRowIdentifierRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new BestIdentifierReader(resultSet, strict);
    }

    @Override
    public CatalogReader createCatalogRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new CatalogReader(resultSet, strict);
    }

    @Override
    public ClientInfoPropertyReader createClientInfoPropertyRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new ClientInfoPropertyReader(resultSet, strict);
    }

    @Override
    public ColumnPrivilegeReader createColumnPrivilegeRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new ColumnPrivilegeReader(resultSet, strict);
    }

    @Override
    public ColumnReader createColumnRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new ColumnReader(resultSet, strict);
    }

    @Override
    public CrossReferenceColumnReader createCrossReferenceColumnRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new CrossReferenceColumnReader(resultSet, strict);
    }

    @Override
    public ExportedKeyColumnReader createExportedKeyColumnRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new ExportedKeyColumnReader(resultSet, strict);
    }

    @Override
    public FunctionColumnReader createFunctionColumnRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new FunctionColumnReader(resultSet, strict);
    }

    @Override
    public FunctionReader createFunctionRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new FunctionReader(resultSet, strict);
    }

    @Override
    public ImportedKeyColumnReader createImportedKeyColumnRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new ImportedKeyColumnReader(resultSet, strict);
    }

    @Override
    public IndexInfoReader createIndexInfoRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new IndexInfoReader(resultSet, strict);
    }

    @Override
    public PrimaryKeyReader createPrimaryKeyRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new PrimaryKeyReader(resultSet, strict);
    }

    @Override
    public ProcedureColumnReader createProcedureColumnRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new ProcedureColumnReader(resultSet, strict);
    }

    @Override
    public PrimaryKeyReader createPrimaryKeyRowReader(@NonNull final ResultSet resultSet) {
        return createPrimaryKeyRowReader(resultSet, false);
    }

    @Override
    public ProcedureColumnReader createProcedureColumnRowReader(@NonNull final ResultSet resultSet) {
        return createProcedureColumnRowReader(resultSet, false);
    }

    @Override
    public ProcedureReader createProcedureRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new ProcedureReader(resultSet, strict);
    }

    @Override
    public PseudoColumnReader createPseudoColumnRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new PseudoColumnReader(resultSet, strict);
    }

    @Override
    public SchemaReader createSchemaRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new SchemaReader(resultSet, strict);
    }

    @Override
    public SuperTableReader createSuperTableRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new SuperTableReader(resultSet, strict);
    }

    @Override
    public SuperTypeReader createSuperTypeRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new SuperTypeReader(resultSet, strict);
    }

    @Override
    public TablePrivilegeReader createTablePrivilegeRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new TablePrivilegeReader(resultSet, strict);
    }

    @Override
    public TableReader createTableRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new TableReader(resultSet, strict);
    }

    @Override
    public TablePrivilegeReader createTablePrivilegeRowReader(@NonNull final ResultSet resultSet) {
        return createTablePrivilegeRowReader(resultSet, false);
    }

    @Override
    public TypeInfoReader createTypeInfoRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new TypeInfoReader(resultSet, strict);
    }

    @Override
    public TableTypeReader createTableTypeRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new TableTypeReader(resultSet, strict);
    }

    @Override
    public UDTReader createUDTRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new UDTReader(resultSet, strict);
    }

    @Override
    public VersionColumnReader createVersionColumnRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new VersionColumnReader(resultSet, strict);
    }

}
