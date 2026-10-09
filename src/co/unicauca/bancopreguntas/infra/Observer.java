package co.unicauca.bancopreguntas.infra;

import java.util.Map;

/**
 * Interfaz del patron de diseno Observer.
 * Toda vista que quiera enterarse de los cambios de estado de las
 * preguntas debe implementar este contrato.
 *
 * @author Sebastian
 */
public interface Observer {

    /**
     * Metodo invocado por el Subject cada vez que hay un cambio
     * de estado en alguna pregunta del banco.
     *
     * @param conteoPorEstado mapa con la cantidad de preguntas
     *                        agrupadas por estado (borrador,
     *                        pendiente de revision, eliminada)
     */
    void actualizar(Map<String, Integer> conteoPorEstado);
}
