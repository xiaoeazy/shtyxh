
package com.huan.HTed.core.json;

import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JacksonMapper extends ObjectMapper {

    public JacksonMapper() {
        super();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Date.class, new DateTimeSerializer());
        module.addDeserializer(Date.class, new DateTimeDeserializer());
        this.registerModule(module);
    }
}
