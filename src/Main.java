import javax.security.auth.login.AccountException;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// yorumlar eklenecek
// eksiklikler: sorgulamalar - harf kontrolleri
//

/*
* Online Learning Platform implements an application that
* simply manages a platform that learner and teacher can
* meet there.
*
* @author Muhammed Didin
*
* @since 2019-11-08
*
* */
public class Main {

    public static void main(String[] args) {
        boolean isLogged = false;
        Trainee currentTrainee = new Trainee();
        List<Trainee> trainees = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<NonStudent> nonStudents = new ArrayList<>();
        Student trainee1 = new Student();
        trainee1.setName("Muhammed");
        trainee1.setPassword("aaa");
        trainee1.setPremium(true);
        trainee1.setStudent(true);
        trainee1.setInstution("METU");

        NonStudent trainee2 = new NonStudent();
        trainee2.setName("Ahmet");
        trainee2.setPassword("bbb");
        trainee2.setPremium(false);
        trainee2.setStudent(false);
        trainee2.setJob("Engineer");
        trainee2.setPosition("Senior");

        trainees.add(trainee1);
        trainees.add(trainee2);

        List<Instructor> instructors = new ArrayList<>();
        Instructor instrusctor1 = new Instructor();
        instrusctor1.setID(1);
        instrusctor1.setName("Instructor1");
        instrusctor1.setPassword("aaa");
        instructors.add(instrusctor1);

        Instructor instructor2 = new Instructor();
        instructor2.setID(2);
        instructor2.setName("Instructor2");
        instructor2.setPassword("bbb");

        instructors.add(instructor2);
        List<Course> courses =new ArrayList<>();
        Course course1 = new Course();
        course1.instructor = instrusctor1;
        course1.setName("Java");
        course1.setDuration(11.5f);
        course1.setPremium(true);
        courses.add(course1);

        Course course2 = new Course();
        course2.instructor = instructor2;
        course2.setName("Python");
        course2.setPremium(false);
        course2.setDuration(2.5f);

        courses.add(course2);
        int selection =0;
        while(selection != 11) {
            menu(isLogged);
            Scanner scanner = new Scanner(System.in);
            try{
                selection = scanner.nextInt();
            }catch (Exception exception){
                System.out.println("Exception Handled: "+ exception.getMessage());
            }


            if (selection == 1) {
                currentTrainee = new Trainee();
                currentTrainee = signUp(students,nonStudents);
                trainees.add(currentTrainee);
            }
            else if (selection == 2) {
                //currentTrainee = new Trainee();
                String currentName;
                System.out.printf("Please enter your name: ");
                Scanner scanner2  = new Scanner(System.in);
                currentName = scanner2.nextLine();
                int index = -1;
                for (Trainee trainee: trainees) {
                    index++;
                    if(trainee.getName().equals(currentName))
                        break;
                }
                currentTrainee = trainees.get(index);


                boolean result = login(trainees, currentTrainee);
                isLogged = result;
                if (result) {
                    System.out.println("Logged successfully!");
                    System.out.printf("Would you like to change your account to premium? (Yes, or No?): ");
                    Scanner scanner1 = new Scanner(System.in);
                    String wish = scanner1.nextLine();
                    boolean isPremium = currentTrainee.getPremium();
                    currentTrainee = changeToPremium(wish,currentTrainee);


                } else if (result == false) {
                    System.out.println("Error while logging!");
                }
            } else if (selection == 3) {
                listAllCourses(currentTrainee,courses);
                System.out.println("Which course you want to add?");
                Scanner scanWish = new Scanner(System.in);
                String wish = scanWish.nextLine().toLowerCase();
                currentTrainee = addCourse(isLogged, courses, currentTrainee, wish);


            } else if (selection == 4) {
                listEnrolledCourses(currentTrainee);
                System.out.println("Which course you want to delete?");
                Scanner scanWish = new Scanner(System.in);
                String wish = scanWish.nextLine();
                currentTrainee = deleteCourse(isLogged, courses, currentTrainee, wish);
            } else if (selection == 5) {
                System.out.println("Please enter the ID: ");
                Scanner scannerID = new Scanner(System.in);
                int currentID = scannerID.nextInt();
                getInstructorDetailes(currentID, instructors);
            } else if (selection == 6) {
                listAllCourses(currentTrainee, courses);
            } else if (selection == 7) {
                listEnrolledCourses(currentTrainee);
            }else if (selection == 8) {
                logout();
                isLogged = false;
                currentTrainee = null;

            }
            else if (selection == 9) {
                double credit =0;
                credit = credit(currentTrainee,students,nonStudents);
                System.out.println("Your credit: "+credit);
            }
            else if (selection == 10) {
                double montlyFee = 0;
                montlyFee = montlyFee(currentTrainee,students,nonStudents);
                System.out.println("Your montly Fee: " + montlyFee);
            }
            else if (selection == 11) {
                exit();
            }
        }


    }

    /**
     * menu method simply writes the menu
     * according to the @param isLogged variable.
     *
     */
    private static void menu(boolean isLogged){
        if(isLogged){
            System.out.println("Menu:");
            System.out.println("2) Login");
            System.out.println("3) Add Course");
            System.out.println("4) Delete Course");
            System.out.println("5) Get Instructor Detailes");
            System.out.println("6) List All Courses");
            System.out.println("7) List Enrolled Courses");
            System.out.println("8) Logout");
            System.out.println("9) Credit");
            System.out.println("10) Montly Fee");
            System.out.println("11) Exit");
            System.out.printf("Selection: ");
        }
        else{
            System.out.println("Welcome to Online Learning Platform!");
            System.out.println("1) Sign Up");
            System.out.println("2) Login");
            System.out.println("11) Exit");
            System.out.printf("Selection: ");
        }
    }

    /**
     * creates a trainee and adds to the proper list then returns the trainee.
     * @param students
     * @param nonStudents
     * @return
     */
    private static Trainee signUp(List<Student> students,List<NonStudent> nonStudents) {
        Trainee trainee = new Trainee();

        String name;
        Character gender;
        int age;
        String Email;
        String password;

        System.out.printf("What is your name: ");
        Scanner scanName = new Scanner(System.in);
        name = scanName.nextLine();
        while(name.length() < 3){
            System.out.printf("Invalid Name! What is your name: ");
            Scanner scanName1 = new Scanner(System.in);
            name = scanName1.nextLine();
        }
        System.out.printf("What is your gender: ");
        Scanner scanGender = new Scanner(System.in);
        gender = scanGender.next().toUpperCase().charAt(0);

        while(!gender.equals('F') && !gender.equals('M')){
            System.out.println("Please enter a valid gender");
            System.out.printf("What is your gender: ");
            Scanner scanGender1 = new Scanner(System.in);
            gender = scanGender1.next().toUpperCase().charAt(0);
        }

        System.out.printf("What is your age: ");
        try{
            Scanner scanAge = new Scanner(System.in);
            age = scanAge.nextInt();
        }catch (Exception e){
            System.out.printf("Invalid age!Please enter again: ");
            Scanner scanAge = new Scanner(System.in);
            age = scanAge.nextInt();
        }



        System.out.printf("Please enter your E-Mail: ");
        Scanner scanEmail = new Scanner(System.in);
        Email = scanEmail.nextLine();

        System.out.printf("Please enter your password: ");
        Scanner scanPassword = new Scanner(System.in);
        password = scanPassword.nextLine();
        int control=0;
        Boolean premium = false;
        while(control==0) {
            System.out.printf("Is your account premium?('Yes' or 'No'): ");
            Scanner scanPremium = new Scanner(System.in);
            String check = scanPremium.nextLine();
            check = check.toLowerCase();

            if (check.equals("yes")){
                premium = true;
                control = 1;
            }
            else if (check.equals("no")){
                premium = false;
                control =1;
            }

        }
        control=0;
        System.out.printf("Are you a student?('Yes' or 'No'): ");
        Scanner scanStudent = new Scanner(System.in);
        String scan = scanStudent.nextLine().toLowerCase();

        if(scan.equals("yes")){
            System.out.printf("What is your instution: ");
            Scanner scanInt = new Scanner(System.in);
            String instituon = scanInt.nextLine();

            Student student = new Student();
            student.setInstution(instituon);
            student.setPremium(premium);
            student.setName(name);
            student.setGender(gender);
            student.setAge(age);
            student.setEmail(Email);
            student.setPassword(password);
            student.setStudent(true);
            students.add(student);
            return student;
        }else{
            System.out.printf("What is your job: ");
            Scanner scanJob = new Scanner(System.in);
            String job = scanJob.nextLine();

            System.out.printf("What is your position: ");
            Scanner scanPosition = new Scanner(System.in);
            String position = scanPosition.nextLine();

            NonStudent nonStudent = new NonStudent();
            nonStudent.setPremium(premium);
            nonStudent.setJob(job);
            nonStudent.setPosition(position);
            nonStudent.setName(name);
            nonStudent.setGender(gender);
            nonStudent.setAge(age);
            nonStudent.setEmail(Email);
            nonStudent.setPassword(password);
            nonStudent.setStudent(false);
            nonStudents.add(nonStudent);
            return nonStudent;
        }

    }

    /**
     * checks the name and password. If they are exists and matched returns true.
     * @param trainees
     * @param currentTrainee
     * @return boolean
     */
    private static boolean login(List<Trainee> trainees,Trainee currentTrainee){
        String currentPassword;

        System.out.printf("Please enter your password: ");
        Scanner scanner1 = new Scanner(System.in);
        currentPassword = scanner1.nextLine();
        if(currentTrainee.getPassword().equals(currentPassword)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * adds selected course to trainee's course list.
     * @param isLogged
     * @param courses
     * @param currentTrainee
     * @param wish
     * @return Trainee
     */
    private static Trainee addCourse(boolean isLogged,List<Course> courses,Trainee currentTrainee,String wish){
        if(isLogged){
            int index=-1;
            for (Course course: courses) {
                index++;
                if(course.getName().toLowerCase().equals(wish))
                    break;
                }

            if(index == -1) {
                System.out.println("Error while searching the course!");
                return currentTrainee;
            }
            Course course = courses.get(index);

            if(courses.contains(course) && !currentTrainee.enrolled.contains(course)){
                currentTrainee.enrolled.add(course);
                System.out.println("Course founded and added successfully!");
            }else if(currentTrainee.enrolled.contains(course)){
                System.out.println("You already have that course!");
            }
            else{
                System.out.println("Error while searching the courses!");
            }
        }
        return currentTrainee;
    }

    /**
     * removes selected course from trainee's course list.
     * @param isLogged
     * @param courses
     * @param currentTrainee
     * @param wish
     * @return Trainee
     */
    private static Trainee deleteCourse(boolean isLogged,List<Course> courses,Trainee currentTrainee,String wish){
        if(currentTrainee.enrolled.isEmpty()){
            return currentTrainee;
        }
        int index=-1;
        for (Course course:currentTrainee.enrolled) {
            index++;
            if(course.getName().equals(wish)){
                currentTrainee.enrolled.remove(index);
                System.out.println("Course removed succesfully!");
                break;
            }
        }



        return currentTrainee;
    }

    /**
     * writes selected Instructor's informations.
     * @param currentId
     * @param instructors
     */
    private static void getInstructorDetailes(int currentId,List<Instructor> instructors){
        Instructor currentInstructor = new Instructor();
        currentInstructor.setID(currentId);
        int index = -1;
        for (Instructor instructor:instructors) {
            index++;
            if(instructor.getID()== currentId)
                break;
        }
        currentInstructor = instructors.get(index);
        if(currentInstructor.getID() == currentId){
            System.out.println("Instructor ID: "+currentInstructor.getID() +"\nInstructor Name: "+ currentInstructor.getName()+"\nInstructor Age:"+ currentInstructor.getAge()+"\nInstructor Email: "+ currentInstructor.getEmail() +"\nInstrcutor Gender: "+ currentInstructor.getGender());

        }else{
            System.out.println("Error occured while searching the instructor");
        }

    }

    /**
     * makes trainee premium
     * @param wish
     * @param currentTrainee
     * @return
     */
    private static Trainee changeToPremium(String wish,Trainee currentTrainee){
        wish = wish.toLowerCase();
        if(wish.equals("yes")){
            if(currentTrainee.getPremium()){
                System.out.println("Your account is already premium.");
            }else{
                currentTrainee.setPremium(true);
                System.out.println("Your account setted as premium!");
            }
        }
        return currentTrainee;
    }

    /**
     * writes all courses and their details.
     * @param currentTrainee
     * @param courses
     */
    private static void listAllCourses(Trainee currentTrainee,List<Course> courses){
        if(currentTrainee.getPremium()){
            for (Course course:courses) {
                if(course.getPremium()){
                    System.out.println(course.getName() +" - Premium");
                }else System.out.println(course.getName() +" - Not Premium");

            }
        }
        else{
            for (Course course:courses) {
                if(!course.getPremium()){
                    System.out.println(course.getName() +" - Not Premium");
                }

            }
        }
    }

    /**
     * writes trainee's enrolled courses
     * @param currentTrainee
     */
    private static void listEnrolledCourses(Trainee currentTrainee){
        if(currentTrainee.enrolled.isEmpty() || currentTrainee.enrolled == null){
            System.out.println("You do not have any courses!\n");
        }
        for (Course course:currentTrainee.enrolled) {
            System.out.println("\nCourse name: " + course.getName()+"\nCourse Instructor: "+
                    course.instructor.getName() +"\nCourse duration: "+  course.getDuration()+"\nIs course premium: " + course.getPremium());

        }
    }

    /**
     * makes trainee logged out.
     */
    private static void logout(){
        menu(false);
    }

    /**
     * calculates credit of trainee.
     * @param currentTrainee
     * @param students
     * @param nonStudents
     * @return
     */
    private static double credit(Trainee currentTrainee,List<Student> students,List<NonStudent> nonStudents){

        int count = 0;
        for(Course course: currentTrainee.enrolled) {
            if (course.getPremium()) {
                count++;
            }
        }
        if(currentTrainee.isStudent()){
            return  count*0.8;
        }

        else{
            return count *0.4;
        }
    }

    /**
     * calculates trainee's bill.
     * @param currentTrainee
     * @param students
     * @param nonStudents
     * @return
     */
    private static double montlyFee(Trainee currentTrainee,List<Student> students, List<NonStudent> nonStudents){
        int count = 0,a;
        double b;
        for (Course course:currentTrainee.enrolled) {
            if(course.getPremium()){
                count++;
            }

        }
        if(currentTrainee.isStudent()) {
            a = count * 10;
            b = credit(currentTrainee,students,nonStudents) - a;

            return (a - b);
        }else{
            a=count * 10;
            b=credit(currentTrainee,students,nonStudents)-a;

            return (a-b) *2 ;
        }
    }

    /**
     * closes the program
     */
    private static void exit(){
        System.out.println("Good bye!");
    }
}