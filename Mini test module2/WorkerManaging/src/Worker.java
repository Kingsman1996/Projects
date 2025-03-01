public abstract class Worker implements Comparable<Worker> {
    private int id;
    private String name;
    private int age;
    private String phoneNumber;
    private String mailAddress;

    public Worker() {
    }

    public Worker(int id, String name, int age, String phoneNumber, String mailAddress) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.mailAddress = mailAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public abstract double getRealSalary();

    @Override
    public int compareTo(Worker other) {
        return Double.compare(getRealSalary(), other.getRealSalary());
    }

    @Override
    public String toString() {
        return "ID: " + id
                + ", Name: " + name
                + ", Age: " + age
                + ", Phone: " + phoneNumber
                + ", Email: " + mailAddress;
    }
}
