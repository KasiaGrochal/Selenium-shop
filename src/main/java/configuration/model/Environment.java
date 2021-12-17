package configuration.model;

import java.util.ArrayList;
import java.util.List;

public class Environment {
    public EnvironmentModel test1;
    public EnvironmentModel test2;
    public EnvironmentModel prod;

    public EnvironmentModel getTest1() {
        return test1;
    }

    public EnvironmentModel getTest2() {
        return test2;
    }

    public EnvironmentModel getProd() {
        return prod;
    }

    public List<EnvironmentModel> getListOfEnvironments() {
        List<EnvironmentModel> listOfEnvironments = new ArrayList<>();
        listOfEnvironments.add(getProd());
        listOfEnvironments.add(getTest1());
        listOfEnvironments.add(getTest2());
        return listOfEnvironments;
    }
}