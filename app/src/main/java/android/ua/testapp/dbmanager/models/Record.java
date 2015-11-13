package android.ua.testapp.dbmanager.models;

public class Record {

    private int id;
    private String message;

    public Record() {
    }

    public Record(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
