package co.unicauca.bancopreguntas.domain;

/**
 * Representa una opcion (distractor) de respuesta asociada a una
 * pregunta de seleccion multiple.
 *
 * Se maneja como clase aparte (y no como String suelto) porque el
 * profe pidio en el enunciado tener una entidad para las opciones,
 * pensando en que a futuro cada opcion pueda tener mas atributos
 * (ej. retroalimentacion, orden, etc).
 *
 * @author Sebastian
 */
public class QuestionDistractors {

    private String letra;   // A, B, C, D...
    private String texto;

    public QuestionDistractors(String letra, String texto) {
        this.letra = letra;
        this.texto = texto;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return letra + ". " + texto;
    }
}
