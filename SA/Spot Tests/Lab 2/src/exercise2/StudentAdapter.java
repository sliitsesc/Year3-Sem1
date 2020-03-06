package exercise2;

import java.time.LocalDate;
import java.time.Period;

public class StudentAdapter implements Student{

    Person person;

    public StudentAdapter(Person person) {
        this.person = person;
    }

    @Override
    public String getFullName() {
        return person.getFirstName() + " " + person.getLastName();
    }

    @Override
    public int getAge() {
        LocalDate today = LocalDate.now();
        Period period = Period.between(LocalDate.parse(person.getDob()), today);
        return period.getYears();
    }

    @Override
    public int getContactNumber() {
        return person.getPhoneNumber();
    }
}
