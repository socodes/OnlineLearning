public abstract class Person {
    private String name;
    private Character gender;
    private int age;
    private String Email;
    private String password;

    /**
     *
     * setters and getters
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public Character getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getEmail(){
        return Email;
    }
    public String getPassword(){
        return password;
    }
}
