package org.velohaven.somia.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.velohaven.somia.model.NamedItemStore;

import java.io.IOException;

@SuppressWarnings("rawtypes")
public class NamedItemStoreSerializer extends StdSerializer<NamedItemStore> {

    public NamedItemStoreSerializer() {
        super(NamedItemStore.class);
    }

    @Override public void serialize(final NamedItemStore value, final JsonGenerator gen, SerializerProvider provider)
        throws IOException {

        boolean isFirst = true;

        gen.writeStartArray();
        for (Object element : value) {
            gen.writeObject(element);
        }
        gen.writeEndArray();
    }

}


