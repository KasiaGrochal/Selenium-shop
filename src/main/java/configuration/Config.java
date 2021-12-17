package configuration;

import configuration.browser.Browsers;
import configuration.model.DataBase;
import configuration.model.Environment;

public class Config {
    public Environment environment;
    public DataBase dataBase;
    public Browsers browsers;

    public Browsers getBrowsers() {
        return browsers;
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public Environment getEnvironment() {
        return environment;
    }
}
