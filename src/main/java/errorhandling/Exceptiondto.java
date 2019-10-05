package errorhandling;


public class Exceptiondto {

    public Exceptiondto(int code, String description) {
        this.code = code;
        this.message = description;
    }
    private int code;
    private String message;
}
