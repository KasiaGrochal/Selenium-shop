package configuration;

import configuration.model.DataBaseModel;
import configuration.model.EnvironmentModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class AppProperties {
    Logger logger = LoggerFactory.getLogger(AppProperties.class);
    YamlReader yamlReader = new YamlReader();

    public AppProperties() {
        setSystemPropertiesEnvironment();
        setSystemPropertiesDataBase();
    }

    private void setSystemPropertiesEnvironment() {
        Map<String, Object> environmentProperties = getEnvironment().getProperties();
        for (Map.Entry entry : environmentProperties.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
            logger.info("Loaded environment property: {} = {}", entry.getKey().toString(), entry.getValue().toString());
        }
        logger.info("Loaded environment properties total: {}", environmentProperties.size());
    }

    public EnvironmentModel getEnvironment() {
        try {
            System.getProperty("Env_Value");
            return getRemoteEnvironment();
        } catch (NullPointerException e) {
            return findActiveEnvironment();
        }
    }

    private EnvironmentModel getRemoteEnvironment() {
        switch (System.getProperty("Env_Value")) {
            case "test1":
                logger.info("Environment set remotely: test1");
                return yamlReader.getConfig().getEnvironment().getTest1();
            case "test2":
                logger.info("Environment set remotely: test2");
                return yamlReader.getConfig().getEnvironment().getTest2();
            case "prod":
                logger.info("Environment set remotely: prod");
                return yamlReader.getConfig().getEnvironment().getProd();
            default:
                return null;
        }
    }

    private EnvironmentModel findActiveEnvironment() {
        List<EnvironmentModel> listOfEnvironments = yamlReader.getConfig().getEnvironment().getListOfEnvironments();
        for (EnvironmentModel environmentModel : listOfEnvironments) {
            if (environmentModel.isActive()) {
                logger.info("Found active environment: {}",environmentModel.getEnvName());
                return environmentModel;
            }
        }
        logger.info("Environment was not specified. Default environment will be loaded: test1.");
        return yamlReader.getConfig().getEnvironment().getTest1(); //default-zmenic na czytanie z yamla
    }

    private void setSystemPropertiesDataBase() {
        Map<String, Object> dataBaseProperties = findActiveDataBase().getDbProperties();
        for (Map.Entry entry : dataBaseProperties.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
            logger.info("Loaded dataBase property: {} = {}", entry.getKey().toString(), entry.getValue().toString());
        }
        logger.info("Loaded dataBase properties total: {}", dataBaseProperties.size());
    }


    private DataBaseModel findActiveDataBase() {
        List<DataBaseModel> listOfDataBase = yamlReader.getConfig().getDataBase().getListOfDataBase();
        for (DataBaseModel dataBaseModel : listOfDataBase) {
            if (dataBaseModel.isActive()) {
                logger.info("Found active database: {}",dataBaseModel.getDbName());
                return dataBaseModel;
            }
        }
        logger.info("Database was not specified. Default database will be loaded: postgres");
        return yamlReader.getConfig().getDataBase().getPostgres(); //default-zmenic na czytanie z yamla
    }
}

