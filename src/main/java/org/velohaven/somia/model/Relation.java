package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class Relation<T extends Relation<T>> extends AbstractColumn<T> {

    private AbstractColumn<?> referencedColumn = null;


}
