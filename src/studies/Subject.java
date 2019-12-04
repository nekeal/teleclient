package studies;

import javax.swing.*;
import java.util.stream.IntStream;

public class Subject {
    private String name;
    private int semester;

    public Subject(String name, int semester) {
        this.name = name;
        this.semester = semester;
    }

    public String getName() {
        return name;
    }
    public int getSemester() {
        return semester;
    }

    public Subject save(String name, int semester, FieldOfStudy field){
        this.name = name;
        this.semester = semester;
        System.out.println("zapisywanie przedmiotu " + this.toString());
        // TODO: request do api razem z kierunkiem studiow do ktorego jest przypisany ten przedmiot
        return this;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
