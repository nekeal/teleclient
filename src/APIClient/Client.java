package APIClient;

import studies.FieldOfStudy;
import studies.FieldOfStudyFactory;
import studies.Subject;

import java.util.ArrayList;

public class Client {

    public static ArrayList<FieldOfStudy> fetchFieldOfStudy(){
        return FieldOfStudyFactory.getFieldOfStudy(4);
    }
    public static void saveSubject(Subject subject, FieldOfStudy field){
        // request do api z zapisaniem przedmiotu
        System.out.println("request do api z przedmiotem");
        }
}
