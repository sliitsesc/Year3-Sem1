package exercise2;

public class UniversityStudent implements Student {

    private String fullName;
    private int age;
    private int contactNumber;

    public UniversityStudent(Person person){
        Student student = new StudentAdapter(person);
        this.fullName = student.getFullName();
        this.age = student.getAge();
        this.contactNumber = student.getContactNumber();
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public int getContactNumber() {
        return contactNumber;
    }
}
