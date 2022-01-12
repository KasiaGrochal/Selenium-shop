package configuration.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class Environment {
    private EnvironmentModel test1;
    private EnvironmentModel test2;
    private EnvironmentModel prod;


    public List<EnvironmentModel> getListOfEnvironments() {
        List<EnvironmentModel> listOfEnvironments = new ArrayList<>();
        listOfEnvironments.add(getProd());
        listOfEnvironments.add(getTest1());
        listOfEnvironments.add(getTest2());
        return listOfEnvironments;
    }
}