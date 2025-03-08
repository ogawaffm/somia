package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true)
public class VersioningColumn extends PseudoColumnRef<VersioningColumn> {
}
