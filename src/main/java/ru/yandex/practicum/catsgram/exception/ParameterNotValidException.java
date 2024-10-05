package ru.yandex.practicum.catsgram.exception;

public class ParameterNotValidException extends IllegalArgumentException{
    String parameter;
    String reason;

    public ParameterNotValidException(String parameter, String reason) {
        this.parameter = parameter;
        this.reason = reason;
    }

    public ParameterNotValidException(String s, String parameter, String reason) {
        super(s);
        this.parameter = parameter;
        this.reason = reason;
    }

    public ParameterNotValidException(String message, Throwable cause, String parameter, String reason) {
        super(message, cause);
        this.parameter = parameter;
        this.reason = reason;
    }

    public ParameterNotValidException(Throwable cause, String parameter, String reason) {
        super(cause);
        this.parameter = parameter;
        this.reason = reason;
    }

    public String getParameter() {
        return parameter;
    }

    public String getReason() {
        return reason;
    }
}
