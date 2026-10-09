package co.unicauca.bancopreguntas.access;

import co.unicauca.bancopreguntas.domain.EstadoPregunta;
import co.unicauca.bancopreguntas.domain.Question;
import co.unicauca.bancopreguntas.domain.QuestionDistractors;
import co.unicauca.bancopreguntas.domain.QuestionRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementacion "sencilla" del repositorio de preguntas, tal como
 * lo permite el enunciado del taller (no se usa base de datos
 * relacional, sino una estructura en memoria a modo de mapa).
 *
 * Si mas adelante se quiere migrar a una base de datos real, solo
 * hay que crear otra clase que implemente QuestionRepository sin
 * tocar el dominio ni la presentacion (principio de inversion de
 * dependencias).
 *
 * @author Sebastian
 */
public class QuestionImplRepository implements QuestionRepository {

    private final Map<String, Question> preguntas = new LinkedHashMap<>();

    public QuestionImplRepository() {
        cargarDatosDePrueba();
    }

    @Override
    public List<Question> obtenerTodas() {
        return new ArrayList<>(preguntas.values());
    }

    @Override
    public Question obtenerPorId(String id) {
        return preguntas.get(id);
    }

    @Override
    public void actualizar(Question pregunta) {
        preguntas.put(pregunta.getId(), pregunta);
    }

    /**
     * Datos "quemados" de ejemplo para poder probar la aplicacion
     * sin depender de una base de datos, siguiendo el mismo ejemplo
     * de la interfaz grafica propuesta en el anexo del taller
     * (pregunta sobre DDD).
     */
    private void cargarDatosDePrueba() {
        List<QuestionDistractors> opciones1 = new ArrayList<>();
        opciones1.add(new QuestionDistractors("A", "Diseñar bases de datos"));
        opciones1.add(new QuestionDistractors("B", "Modelar el dominio del negocio"));
        opciones1.add(new QuestionDistractors("C", "Eliminar UML"));
        opciones1.add(new QuestionDistractors("D", "Crear interfaces gráficas"));
        preguntas.put("P-001", new Question("P-001", "Pregunta sobre DDD",
                "¿Cuál es el objetivo principal de DDD?", opciones1, "B",
                EstadoPregunta.BORRADOR));

        List<QuestionDistractors> opciones2 = new ArrayList<>();
        opciones2.add(new QuestionDistractors("A", "Un patrón estructural"));
        opciones2.add(new QuestionDistractors("B", "Un patrón creacional"));
        opciones2.add(new QuestionDistractors("C", "Un patrón de comportamiento"));
        opciones2.add(new QuestionDistractors("D", "No es un patrón de diseño"));
        preguntas.put("P-002", new Question("P-002", "Pregunta sobre Observer",
                "El patrón Observer se clasifica como:", opciones2, "C",
                EstadoPregunta.PENDIENTE_REVISION));

        List<QuestionDistractors> opciones3 = new ArrayList<>();
        opciones3.add(new QuestionDistractors("A", "Presentación"));
        opciones3.add(new QuestionDistractors("B", "Dominio"));
        opciones3.add(new QuestionDistractors("C", "Acceso a datos"));
        opciones3.add(new QuestionDistractors("D", "Todas las anteriores"));
        preguntas.put("P-003", new Question("P-003", "Pregunta sobre capas",
                "¿Cuáles son capas típicas de una arquitectura en capas?",
                opciones3, "D", EstadoPregunta.BORRADOR));

        List<QuestionDistractors> opciones4 = new ArrayList<>();
        opciones4.add(new QuestionDistractors("A", "Vista"));
        opciones4.add(new QuestionDistractors("B", "Controlador"));
        opciones4.add(new QuestionDistractors("C", "Modelo"));
        opciones4.add(new QuestionDistractors("D", "Todas las anteriores"));
        preguntas.put("P-004", new Question("P-004", "Pregunta sobre MVC",
                "¿Cuáles son los componentes del micro patrón MVC?",
                opciones4, "D", EstadoPregunta.ELIMINADA));

        List<QuestionDistractors> opciones5 = new ArrayList<>();
        opciones5.add(new QuestionDistractors("A", "Acoplar vistas y datos"));
        opciones5.add(new QuestionDistractors("B", "Notificar cambios de estado a varios suscriptores"));
        opciones5.add(new QuestionDistractors("C", "Acceder a la base de datos"));
        opciones5.add(new QuestionDistractors("D", "Validar formularios"));
        preguntas.put("P-005", new Question("P-005", "Pregunta sobre propósito de Observer",
                "¿Cuál es el propósito principal del patrón Observer?",
                opciones5, "B", EstadoPregunta.PENDIENTE_REVISION));
    }
}
