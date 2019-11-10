public class NonStudent extends Trainee implements Performance {
    private String job;
    private String position;

    /**
     *
     * setters and getters.
     */
    public void setJob(String job) {
        this.job = job;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition(){
        return position;
    }

    public String getJob() {
        return job;
    }

    /**
     * calculates the credit
     * @param currentTrainee
     * @return
     */
    @Override
    public double credit(Trainee currentTrainee) {
        int count=0;
        for (Course course:currentTrainee.enrolled) {
            if(course.getPremium()){
                count++;
            }

        }
        return count * 0.4;
    }

    /**
     * calculates the bill.
     * @param currentTrainee
     * @return
     */
    @Override
    public double monthlyFee(Trainee currentTrainee) {
        int count=0,a;
        double b;
        for (Course course:currentTrainee.enrolled) {
            if(course.getPremium()){
                count++;
            }

        }
        a=count * 10;
        b=credit(currentTrainee)-a;

        return (a-b) *2 ;
    }
}
