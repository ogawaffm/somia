package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Columns<C extends NamedItem<C>> extends NamedItemStore<Columns<C>, C> {
}
