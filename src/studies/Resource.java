package studies;

public class Resource extends SavedModel<String, String, Subject> {
    private String description;
    private String link;

    public Resource(String description, String link) {
        this.description = description;
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    @Override
    public SavedModel save(String description, String link, Subject subject){
        this.description = description;
        this.link = link;
//        Client.saveResource(description, link, subject) TODO
        return this;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(description);
        s.append(" ");
        s.append(link);
//        s.append("");
        return s.toString();
    }
}
