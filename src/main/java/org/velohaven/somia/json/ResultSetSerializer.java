package org.velohaven.somia.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetSerializer extends StdSerializer<ResultSet> {

    public ResultSetSerializer() {
        super(ResultSet.class);
    }

    @Override
    public void serialize(ResultSet resultSet, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
        throws IOException {

        jsonGenerator.writeStartArray();

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            boolean isFirst = true;

            while (resultSet.next()) {
                if (!isFirst) {
                    jsonGenerator.writeObject(",");
                } else {
                    isFirst = false;
                }
                jsonGenerator.writeStartObject();
                for (int i = 1; i <= columnCount; i++) {
                    jsonGenerator.writeFieldName(metaData.getColumnName(i));
                    jsonGenerator.writeObject(resultSet.getObject(i));
                }
                jsonGenerator.writeEndObject();
            }

        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }

        jsonGenerator.writeEndArray();
    }

}
