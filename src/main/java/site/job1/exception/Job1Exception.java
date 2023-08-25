package site.job1.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class Job1Exception extends RuntimeException {

    public final Map<String, String> validation = new HashMap<>();

    public Job1Exception(String message) {
        super(message);
    }

    public Job1Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
