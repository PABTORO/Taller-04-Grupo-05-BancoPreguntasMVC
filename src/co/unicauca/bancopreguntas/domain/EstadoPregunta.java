package co.unicauca.bancopreguntas.domain;

/**
 * Estados posibles de una pregunta dentro del banco de preguntas.
 *
 * @author Sebastian
 */
public enum EstadoPregunta {
    BORRADOR("Borrador"),
    PENDIENTE_REVISION("Pendiente de revisión"),
    ELIMINADA("Eliminada");

    private final String etiqueta;

    EstadoPregunta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    @Override
    public String toString() {
        return etiqueta;
    }
}
