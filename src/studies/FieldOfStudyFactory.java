package studies;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FieldOfStudyFactory {

    public static ArrayList<FieldOfStudy> getFieldOfStudy(int count){
        String[] names = {"Elektronika i telekomunikacja", "Teleinformatyka", "Elektronika", "Informatyka"};
        String[] slugs = {"EiT", "Ti", "El", "It"};
        ArrayList fields = new ArrayList();
        for(int i = 0; i<count; i++){
            fields.add(new FieldOfStudy(names[i], slugs[i]));
        }
        return fields;
    }
}
