package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true)
@Getter
public class Attribute extends AbstractColumn<Attribute> {

    @EqualsAndHashCode.Exclude
    UserDefinedType userDefinedType;

}
