package co.unicauca.bancopreguntas.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Clase base del patron Observer. Se encarga de administrar la
 * lista de observadores (vistas) y de notificarles cuando cambia
 * el estado de una pregunta.
 *
 * Las clases de presentacion que necesiten "avisar" a otras vistas
 * (por ejemplo GUIQuestions) extienden esta clase o la usan por
 * composicion.
 *
 * @author Sebastian
 */
public class Subject {

    private final List<Observer> observadores = new ArrayList<>();

    /**
     * Suscribe un nuevo observador para que reciba las notificaciones.
     */
    public void agregarObservador(Observer obs) {
        observadores.add(obs);
    }

    /**
     * Quita un observador de la lista de suscriptores.
     */
    public void removerObservador(Observer obs) {
        observadores.remove(obs);
    }

    /**
     * Notifica a todos los observadores registrados enviando el
     * conteo actualizado de preguntas por estado.
     */
    public void notificarObservadores(Map<String, Integer> conteoPorEstado) {
        for (Observer obs : observadores) {
            obs.actualizar(conteoPorEstado);
        }
    }
}
