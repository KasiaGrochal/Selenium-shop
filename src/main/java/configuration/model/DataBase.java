package configuration.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Data
public class DataBase {

    Logger logger = LoggerFactory.getLogger(DataBase.class);

    private DataBaseModel postgres;
    private DataBaseModel mySql;


    public List<DataBaseModel> getListOfDataBase() {
        List<DataBaseModel> listOfDataBase = new ArrayList<>();
        listOfDataBase.add(getPostgres());
        listOfDataBase.add(getMySql());
        return listOfDataBase;
    }
}
