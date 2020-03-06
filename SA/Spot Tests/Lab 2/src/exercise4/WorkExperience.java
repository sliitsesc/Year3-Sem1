package exercise4;

public class WorkExperience extends EmployeeQualification {

    private Employee employee;

    public WorkExperience(Employee employee) {
        this.employee = employee;
    }

    @Override
    double getSalary() {
        return employee.getSalary() + 20000;
    }

    @Override
    public String getDescription() {
        return employee.getDescription();
    }
}
