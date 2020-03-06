package exercise4;

public abstract class Employee {

    private String description = "General Employee Details";

    abstract double getSalary();

    public String getDescription(){
        return description;
    }
}
