package org.velohaven.somia.model;

import org.velohaven.somia.json.JsonUtils;

public interface WithToJson {

    default String toJson() {
        return JsonUtils.serialize(this);
    }

}
