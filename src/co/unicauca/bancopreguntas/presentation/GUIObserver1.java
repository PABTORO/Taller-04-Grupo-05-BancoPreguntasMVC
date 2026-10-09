package co.unicauca.bancopreguntas.presentation;

import co.unicauca.bancopreguntas.infra.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Vista observadora #1: muestra en texto cuantas preguntas hay
 * registradas por cada estado. Se actualiza automaticamente cada
 * vez que el Subject (GUIQuestions) notifica un cambio de estado.
 *
 * @author Sebastian
 */
public class GUIObserver1 extends JFrame implements Observer {

    private JLabel lblTitulo;
    private JPanel panelDatos;

    public GUIObserver1() {
        super("Vista 1 - Estadísticas por estado");
        initComponents();
        setSize(360, 260);
        setLocation(760, 60);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        lblTitulo = new JLabel("Preguntas por estado", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        panelDatos = new JPanel();
        panelDatos.setLayout(new GridLayout(0, 2, 10, 10));
        panelDatos.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(panelDatos, BorderLayout.CENTER);
    }

    /**
     * Metodo del patron Observer: cada vez que cambia el estado de
     * una pregunta, se refresca la tabla de conteos.
     */
    @Override
    public void actualizar(Map<String, Integer> conteoPorEstado) {
        panelDatos.removeAll();
        for (Map.Entry<String, Integer> entry : conteoPorEstado.entrySet()) {
            panelDatos.add(new JLabel(entry.getKey() + ":"));
            JLabel valor = new JLabel(String.valueOf(entry.getValue()));
            valor.setFont(new Font("SansSerif", Font.BOLD, 14));
            panelDatos.add(valor);
        }
        panelDatos.revalidate();
        panelDatos.repaint();
    }
}
