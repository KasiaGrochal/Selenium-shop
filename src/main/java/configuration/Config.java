package configuration;

import configuration.browser.Browsers;
import configuration.model.DataBase;
import configuration.model.Environment;
import lombok.Data;

@Data
public class Config {
    public Environment environment;
    public DataBase dataBase;
    public Browsers browsers;

}
