package configuration.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class DataBaseModel {
    boolean active;
    private String dbName;

    Map<String, Object> dbProperties = new LinkedHashMap<>();

    public boolean isActive() {
        return active;
    }

    @JsonAnySetter
    void setDbProperties(String key, Object value) {
        dbProperties.put(key, value);
    }
    @JsonAnyGetter
    public Map<String, Object> getDbProperties() {
        return dbProperties;
    }
}
