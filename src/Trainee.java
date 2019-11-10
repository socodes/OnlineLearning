import java.util.ArrayList;
import java.util.List;

public class Trainee extends Person {
     List<Course> enrolled = new ArrayList<>();

    private boolean  isPremium;
    private boolean isStudent;

    /**
     * setters and getters
     *
     */
    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public void setEnrolled(Course enrolled) {
        this.enrolled.add(enrolled);
    }

    public void setPremium(boolean premium) {
        this.isPremium = premium;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setGender(Character gender) {
        super.setGender(gender);
    }

    @Override
    public void setAge(int age) {
        super.setAge(age);
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    public List<Course> getEnrolled() {
        return enrolled;
    }


    public boolean getPremium() {

        return isPremium;
    }
}
