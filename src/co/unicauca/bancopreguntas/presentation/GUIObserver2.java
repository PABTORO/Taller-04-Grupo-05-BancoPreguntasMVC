package co.unicauca.bancopreguntas.presentation;

import co.unicauca.bancopreguntas.infra.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Vista observadora #2: dibuja una grafica de pastel (pie chart)
 * con el porcentaje de preguntas en cada estado. Se implemento el
 * dibujo "a mano" con Graphics2D (arcos) para no depender de
 * librerias externas de graficas.
 *
 * @author Sebastian
 */
public class GUIObserver2 extends JFrame implements Observer {

    private PanelPastel panelPastel;

    public GUIObserver2() {
        super("Vista 2 - Gráfica de pastel");
        panelPastel = new PanelPastel();
        setLayout(new BorderLayout());
        add(panelPastel, BorderLayout.CENTER);
        setSize(420, 420);
        setLocation(760, 340);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    @Override
    public void actualizar(Map<String, Integer> conteoPorEstado) {
        panelPastel.setDatos(conteoPorEstado);
        panelPastel.repaint();
    }

    /**
     * Panel interno encargado unicamente de pintar la grafica.
     * Se separo del JFrame para mantener la logica de dibujo
     * aislada (responsabilidad unica).
     */
    private static class PanelPastel extends JPanel {

        private Map<String, Integer> datos = new LinkedHashMap<>();
        private final Color[] colores = {
                new Color(66, 133, 244),   // azul - borrador
                new Color(251, 188, 5),    // amarillo - pendiente de revision
                new Color(234, 67, 53)     // rojo - eliminada
        };

        void setDatos(Map<String, Integer> datos) {
            this.datos = datos;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int total = 0;
            for (int v : datos.values()) {
                total += v;
            }

            int diametro = 240;
            int x = 30, y = 40;

            if (total == 0) {
                g2.drawString("Sin datos para graficar todavía", x, y + diametro / 2);
                return;
            }

            double anguloInicio = 0;
            int i = 0;
            int leyendaY = y;
            for (Map.Entry<String, Integer> entry : datos.entrySet()) {
                double porcentaje = entry.getValue() * 100.0 / total;
                double anguloExtent = porcentaje * 360.0 / 100.0;

                g2.setColor(colores[i % colores.length]);
                g2.fillArc(x, y, diametro, diametro, (int) Math.round(anguloInicio), (int) Math.round(anguloExtent));

                // leyenda a la derecha
                g2.fillRect(x + diametro + 30, leyendaY, 14, 14);
                g2.setColor(Color.BLACK);
                g2.drawString(String.format("%s: %.0f%%", entry.getKey(), porcentaje),
                        x + diametro + 50, leyendaY + 12);

                anguloInicio += anguloExtent;
                leyendaY += 26;
                i++;
            }

            g2.setColor(Color.DARK_GRAY);
            g2.drawOval(x, y, diametro, diametro);
        }
    }
}
