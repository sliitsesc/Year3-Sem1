package exercise2;

public class Test {
    public static void main(String[] args) {
        Person person = new Person(
                "970163360V",
                "Chamod",
                "Perera",
                "1997-01-16",
                775633985
        );

        Student student = new StudentAdapter(person);
        System.out.println(student.getFullName());
        System.out.println(student.getAge());
        System.out.println(student.getContactNumber());
    }
}
