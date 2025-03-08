package org.velohaven.somia.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;

public class SqlArraySerializer extends StdSerializer<Array> {

    public SqlArraySerializer() {
        super(Array.class);
    }

    @Override
    public void serialize(Array value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        try {
            Object[] array = (Object[]) value.getArray();
            gen.writeStartArray();
            for (Object obj : array) {
                gen.writeObject(obj);
            }
            gen.writeEndArray();
        } catch (SQLException e) {
            throw new IOException("Error serializing SQL Array", e);
        }
    }
}