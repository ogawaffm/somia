package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true)
@Getter
public class ReferencingKey extends Key {

    boolean noActionOnUpdate = true;
    boolean noActionOnDelete = true;

    boolean cascadeOnUpdate = false;
    boolean cascadeOnDelete = false;

    boolean setNullOnUpdate = false;
    boolean setNullOnDelete = false;

    boolean setDefaultOnUpdate = false;
    boolean setDefaultOnDelete = false;

    boolean initiallyDeferred = false;
    boolean initiallyImmediate = false;
    boolean notDeferrable = false;

    /**
     * The unique key of this table that is exported to the other table
     */
    Key exportedKey;

    /**
     * The unique key the other table imported to this table
     */

    Key importedKey;

}
