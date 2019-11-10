public class Student extends Trainee implements Performance {
    String instution;

    public void setInstution(String _instution){
        this.instution = _instution;
    }

    public String getInstution() {
        return instution;
    }

    @Override
    //compiler does not accept float
    public double credit(Trainee currentTrainee) {
        int count=0;
        for (Course course:currentTrainee.enrolled) {
            if(course.getPremium()){
                count++;
            }

        }
        return count * 0.8;
    }

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

        return (a-b);
    }
}
