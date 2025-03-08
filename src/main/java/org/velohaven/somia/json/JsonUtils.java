package org.velohaven.somia.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.SneakyThrows;
import org.velohaven.somia.model.NamedItemStore;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JsonUtils {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();

        VisibilityChecker<?> visibilityChecker = VisibilityChecker.Std.defaultInstance()
            .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
            .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
            .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
            .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
            .withCreatorVisibility(JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibilityChecker(visibilityChecker);

//        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        SimpleModule module = new SimpleModule();
        module.addSerializer(ResultSet.class, new ResultSetSerializer());
        module.addSerializer(Array.class, new SqlArraySerializer());
        module.addSerializer(NamedItemStore.class, new NamedItemStoreSerializer());
        mapper.registerModule(module);

    }

    @SneakyThrows
    public static String serialize(Object object) {
        return mapper.writeValueAsString(object);
    }

    Map<String, Object> resultSetRowToMap(ResultSet resultSet) throws SQLException {

        ResultSetMetaData metaData = resultSet.getMetaData();
        Map<String, Object> resultSetRowMap = new HashMap<>(metaData.getColumnCount());

        for (int i = 1; i < metaData.getColumnCount(); i++) {
            String columnName = metaData.getColumnName(i);
            Object columnValue = resultSet.getObject(i);
            resultSetRowMap.put(columnName, columnValue);
        }

        return resultSetRowMap;
    }
}
