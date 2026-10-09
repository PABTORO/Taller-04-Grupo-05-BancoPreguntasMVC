package co.unicauca.bancopreguntas.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad de dominio que representa una pregunta del banco de
 * preguntas para las pruebas Saber Pro.
 *
 * @author Sebastian
 */
public class Question {

    private String id;
    private String nombre;
    private String pregunta;
    private List<QuestionDistractors> opciones;
    private String respuestaCorrecta; // letra de la opcion correcta
    private EstadoPregunta estado;

    public Question(String id, String nombre, String pregunta,
                     List<QuestionDistractors> opciones,
                     String respuestaCorrecta, EstadoPregunta estado) {
        this.id = id;
        this.nombre = nombre;
        this.pregunta = pregunta;
        this.opciones = (opciones != null) ? opciones : new ArrayList<>();
        this.respuestaCorrecta = respuestaCorrecta;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public List<QuestionDistractors> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<QuestionDistractors> opciones) {
        this.opciones = opciones;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public EstadoPregunta getEstado() {
        return estado;
    }

    public void setEstado(EstadoPregunta estado) {
        this.estado = estado;
    }

    /**
     * Concatena las opciones en un solo texto, una por linea,
     * util para mostrarlas en el JTextArea del formulario.
     */
    public String opcionesComoTexto() {
        StringBuilder sb = new StringBuilder();
        for (QuestionDistractors op : opciones) {
            sb.append(op.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        // Se usa para pintar el combo box de seleccion de preguntas
        return id + " - " + nombre;
    }
}
