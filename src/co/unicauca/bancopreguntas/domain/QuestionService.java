package co.unicauca.bancopreguntas.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Servicio de dominio para el banco de preguntas. Contiene la
 * logica de negocio (que no le corresponde ni a la vista ni al
 * repositorio): listar preguntas, cambiar estado y calcular las
 * estadisticas que consumen las vistas observadoras.
 *
 * @author Sebastian
 */
public class QuestionService {

    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public List<Question> listarPreguntas() {
        return repository.obtenerTodas();
    }

    public Question buscarPorId(String id) {
        return repository.obtenerPorId(id);
    }

    /**
     * Cambia el estado de una pregunta y la persiste a traves del
     * repositorio.
     */
    public void cambiarEstado(String idPregunta, EstadoPregunta nuevoEstado) {
        Question pregunta = repository.obtenerPorId(idPregunta);
        if (pregunta == null) {
            throw new IllegalArgumentException("No existe la pregunta con id " + idPregunta);
        }
        pregunta.setEstado(nuevoEstado);
        repository.actualizar(pregunta);
    }

    /**
     * Calcula cuantas preguntas hay en cada estado. Este es el dato
     * que se envia a los observadores (vista de estadisticas y
     * vista de grafica de pastel) cada vez que hay un cambio.
     */
    public Map<String, Integer> conteoPorEstado() {
        Map<String, Integer> conteo = new LinkedHashMap<>();
        for (EstadoPregunta e : EstadoPregunta.values()) {
            conteo.put(e.getEtiqueta(), 0);
        }
        for (Question q : repository.obtenerTodas()) {
            String etiqueta = q.getEstado().getEtiqueta();
            conteo.put(etiqueta, conteo.get(etiqueta) + 1);
        }
        return conteo;
    }
}
