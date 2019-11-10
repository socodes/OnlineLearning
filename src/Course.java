public class Course {
    Instructor instructor = new Instructor();
    private String name;
    private float duration;
    private boolean premium;

    /**
     * setters and getters
     */
    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public float getDuration() {
        return duration;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public boolean getPremium() {
        return this.premium;
    }
}
