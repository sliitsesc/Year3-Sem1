package exercise4;

public class EmployeeCertification extends EmployeeQualification {

    private Employee employee;

    public EmployeeCertification(Employee employee) {
        this.employee = employee;
    }

    @Override
    double getSalary() {
        return employee.getSalary() + 30000;
    }

    @Override
    public String getDescription() {
        return employee.getDescription();
    }
}
