package configuration.model;

import java.util.HashMap;
import java.util.Map;

public class DataBaseModel {
    public String dbName;
    public String host;
    public String user;
    public String dbPassword;
    public boolean active;

    public boolean isActive() {
        return active;
    }

    public String getDbName() {
        return dbName;
    }

    public String getHost() {
        return host;
    }

    public String getUser() {
        return user;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public Map<String, String> getDataBaseProperties() {
        Map<String, String> properties = new HashMap<>();
        properties.put("dbName", getDbName());
        properties.put("host", getHost());
        properties.put("user", getUser());
        properties.put("dbPassword", getDbPassword());
        return properties;
    }
}
