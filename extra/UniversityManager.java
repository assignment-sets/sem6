import java.util.ArrayList;
import java.util.List;

abstract class UniversityMember {
    private String id;
    private String name;
    private String email;

    public UniversityMember(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public abstract void displayDetails();
}

class Student extends UniversityMember {
    private String major;
    private int year;

    public Student(String id, String name, String email, String major, int year) {
        super(id, name, email);
        this.major = major;
        this.year = year;
    }

    public String getMajor() {
        return major;
    }

    public int getYear() {
        return year;
    }

    @Override
    public void displayDetails() {
        System.out.println("Student ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Major: " + this.major + ", Year: " + this.year);
    }
}

class Undergraduate extends Student {
    public Undergraduate(String id, String name, String email, String major, int year) {
        super(id, name, email, major, year);
    }

    @Override
    public void displayDetails() {
        System.out.println("Undergraduate Student:");
        super.displayDetails();
    }
}

class Postgraduate extends Student {
    public Postgraduate(String id, String name, String email, String major, int year) {
        super(id, name, email, major, year);
    }

    @Override
    public void displayDetails() {
        System.out.println("Postgraduate Student:");
        super.displayDetails();
    }
}

class Faculty extends UniversityMember {
    private String department;

    public Faculty(String id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public void displayDetails() {
        System.out.println("Faculty ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Department: " + department);
    }
}

class TenuredFaculty extends Faculty {
    public TenuredFaculty(String id, String name, String email, String department) {
        super(id, name, email, department);
    }

    @Override
    public void displayDetails() {
        System.out.println("Tenured Faculty:");
        super.displayDetails();
    }
}

class AdjunctFaculty extends Faculty {
    public AdjunctFaculty(String id, String name, String email, String department) {
        super(id, name, email, department);
    }

    @Override
    public void displayDetails() {
        System.out.println("Adjunct Faculty:");
        super.displayDetails();
    }
}

public class UniversityManager {
    private List<UniversityMember> members;

    public UniversityManager() {
        members = new ArrayList<>();
    }

    public void addMember(UniversityMember member) {
        members.add(member);
    }

    public void displayAllMembers() {
        for (UniversityMember member : members) {
            member.displayDetails();
            System.out.println("--------------");
        }
    }

    public static void main(String[] args) {
        UniversityManager manager = new UniversityManager();

        manager.addMember(new Undergraduate("U001", "Alice", "alice@univ.edu", "CS", 2));
        manager.addMember(new Postgraduate("P001", "Bob", "bob@univ.edu", "Math", 1));
        manager.addMember(new TenuredFaculty("F001", "Dr. Smith", "smith@univ.edu", "Physics"));
        manager.addMember(new AdjunctFaculty("F002", "Dr. Brown", "brown@univ.edu", "Chemistry"));

        manager.displayAllMembers();
    }
}
