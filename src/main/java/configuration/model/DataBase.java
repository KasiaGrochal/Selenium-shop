package configuration.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    Logger logger = LoggerFactory.getLogger(DataBase.class);

    public DataBaseModel postgres;
    public DataBaseModel mySql;

    public DataBaseModel getPostgres() {
        return postgres;
    }

    public DataBaseModel getDbMySql() {
        return mySql;
    }

    public List<DataBaseModel> getListOfDataBase() {
        List<DataBaseModel> listOfDataBase = new ArrayList<>();
        listOfDataBase.add(getPostgres());
        listOfDataBase.add(getDbMySql());
        return listOfDataBase;
    }
}
