package org.velohaven.somia.model;

import lombok.NonNull;

import java.util.Comparator;

public interface DatabaseObjectIdentity extends Comparable<DatabaseObjectIdentity>, WithName {

    String catalogName();

    String schemaName();

    @Override
    String name();

    @Override
    default String fullQuotedIdentifier() {
        return NameUtils.getFullQuotedIdentifier(catalogName(), schemaName(), name());
    }

    @Override
    default int compareTo(@NonNull DatabaseObjectIdentity otherDatabaseObjectIdentity) {
        return Comparator.comparing(DatabaseObjectIdentity::catalogName)
            .thenComparing(DatabaseObjectIdentity::schemaName)
            .thenComparing(DatabaseObjectIdentity::name)
            .compare(this, otherDatabaseObjectIdentity);
    }

}
