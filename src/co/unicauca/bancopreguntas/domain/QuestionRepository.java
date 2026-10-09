package co.unicauca.bancopreguntas.domain;

import java.util.List;

/**
 * Contrato que debe cumplir cualquier implementacion de
 * persistencia del banco de preguntas.
 *
 * Esta interfaz vive en la capa de dominio (siguiendo el estilo
 * "repository pattern" visto en clase) y su implementacion concreta
 * queda en la capa de acceso a datos, para que el dominio no dependa
 * de detalles de infraestructura.
 *
 * @author Sebastian
 */
public interface QuestionRepository {

    List<Question> obtenerTodas();

    Question obtenerPorId(String id);

    void actualizar(Question pregunta);
}
