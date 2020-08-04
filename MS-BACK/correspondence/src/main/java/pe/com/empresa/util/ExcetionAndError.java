package pe.com.empresa.util;

public enum ExcetionAndError {

    MSG_SUCCESS("Mensaje enviado con exito"),
    MSG_ERROR("Mensaje fallo en el envio");

    private String description;

    private ExcetionAndError(String error) {
        description = error;
    }

    public String getDescription() {
        return description;
    }
}
