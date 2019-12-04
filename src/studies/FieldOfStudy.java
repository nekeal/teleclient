package studies;

import APIClient.Client;

import java.util.ArrayList;

public class FieldOfStudy {
    private String name;
    private String slug;
    private int id;
    private ArrayList<Subject> subjects;
    public FieldOfStudy(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public void fetchSubjects(){
        //Docelowo zaciąganie przedmiotów z API
        subjects = SubjectFactory.getSubjects(this, 10);
    }

    public ArrayList<Subject> getSubjects(){
        if(subjects == null)
            fetchSubjects();
        return subjects;
    }
    public int getId() {
        return id;
    }

    public void addSubject(Subject subject){
        if(subjects==null){
            fetchSubjects();
        }
        subjects.add(subject);
        Client.saveSubject(subject, this);
    }
    public void removeSubject(Subject subject){
//        Client.delSubject TODO: zmienic zeby usuwalo przedmiot z api
        subjects.remove(subject);
    }

    public FieldOfStudy save(String name, String slug){
        this.name = name;
        this.slug = slug;
        System.out.println("zapisywanie kierunku " + this.toString());
        // TODO: metoda z Clienta do zapisania kierunku studiow
        return this;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(name);
        s.append(" (");
        s.append(slug);
        s.append(")");
        return s.toString();
    }


}
