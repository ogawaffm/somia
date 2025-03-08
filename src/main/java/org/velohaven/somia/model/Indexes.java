package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Indexes extends NamedItemStore<Indexes, Index> {

    public Indexes() {
        super();
    }

}
