package co.unicauca.bancopreguntas.presentation;

import co.unicauca.bancopreguntas.access.QuestionImplRepository;
import co.unicauca.bancopreguntas.domain.EstadoPregunta;
import co.unicauca.bancopreguntas.domain.Question;
import co.unicauca.bancopreguntas.domain.QuestionService;
import co.unicauca.bancopreguntas.infra.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Ventana principal de la aplicacion. Cumple el rol de Vista y de
 * Controlador del micro patron MVC (en Swing es comun juntarlos en
 * la misma clase, tal como se vio en el ejemplo del docente), y
 * ademas actua como Subject dentro del patron Observer: cada vez
 * que el usuario cambia el estado de una pregunta, notifica a las
 * demas vistas (GUIObserver1 y GUIObserver2).
 *
 * @author Sebastian
 */
public class GUIQuestions extends JFrame {

    // ---- MVC: el "modelo" con el que trabaja esta vista/controlador ----
    private final QuestionService service;
    private final Subject subject;

    // ---- Componentes graficos ----
    private JComboBox<Question> cbxPreguntas;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextArea txtPregunta;
    private JTextArea txtOpciones;
    private JTextField txtRespuesta;
    private JLabel lblEstadoActual;
    private JComboBox<EstadoPregunta> cbxNuevoEstado;
    private JButton btnCargar;
    private JButton btnActualizarEstado;

    public GUIQuestions(QuestionService service, Subject subject) {
        super("Banco de Preguntas Saber Pro - Gestión de preguntas");
        this.service = service;
        this.subject = subject;
        initComponents();
        cargarComboPreguntas();
        setSize(480, 620);
        setLocation(200, 60);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ---- Panel superior: selector de pregunta ----
        JPanel panelSelector = new JPanel(new BorderLayout(5, 5));
        panelSelector.setBorder(BorderFactory.createTitledBorder("Seleccionar pregunta"));
        cbxPreguntas = new JComboBox<>();
        btnCargar = new JButton("Cargar pregunta");
        panelSelector.add(cbxPreguntas, BorderLayout.CENTER);
        panelSelector.add(btnCargar, BorderLayout.EAST);

        // ---- Panel central: formulario de la pregunta ----
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.Y_AXIS));
        panelForm.setBorder(BorderFactory.createTitledBorder("Formulario de pregunta"));

        txtId = new JTextField();
        txtId.setEditable(false);
        txtNombre = new JTextField();
        txtNombre.setEditable(false);
        txtPregunta = new JTextArea(3, 20);
        txtPregunta.setLineWrap(true);
        txtPregunta.setEditable(false);
        txtOpciones = new JTextArea(5, 20);
        txtOpciones.setEditable(false);
        txtRespuesta = new JTextField();
        txtRespuesta.setEditable(false);
        lblEstadoActual = new JLabel("-");
        lblEstadoActual.setFont(new Font("SansSerif", Font.BOLD, 13));

        panelForm.add(campoConEtiqueta("Id:", txtId));
        panelForm.add(campoConEtiqueta("Nombre:", txtNombre));
        panelForm.add(campoConEtiqueta("Pregunta:", new JScrollPane(txtPregunta)));
        panelForm.add(campoConEtiqueta("Opciones:", new JScrollPane(txtOpciones)));
        panelForm.add(campoConEtiqueta("Respuesta correcta:", txtRespuesta));
        panelForm.add(campoConEtiqueta("Estado actual:", lblEstadoActual));

        // ---- Panel inferior: cambio de estado ----
        JPanel panelEstado = new JPanel(new BorderLayout(5, 5));
        panelEstado.setBorder(BorderFactory.createTitledBorder("Cambiar estado"));
        cbxNuevoEstado = new JComboBox<>(EstadoPregunta.values());
        btnActualizarEstado = new JButton("Actualizar estado");
        panelEstado.add(cbxNuevoEstado, BorderLayout.CENTER);
        panelEstado.add(btnActualizarEstado, BorderLayout.EAST);

        JPanel panelSurTotal = new JPanel(new BorderLayout());
        panelSurTotal.add(panelForm, BorderLayout.CENTER);
        panelSurTotal.add(panelEstado, BorderLayout.SOUTH);

        add(panelSelector, BorderLayout.NORTH);
        add(panelSurTotal, BorderLayout.CENTER);

        // ---- Eventos (rol de Controlador) ----
        btnCargar.addActionListener(this::onCargarPregunta);
        btnActualizarEstado.addActionListener(this::onActualizarEstado);
    }

    private JPanel campoConEtiqueta(String etiqueta, JComponent campo) {
        JPanel p = new JPanel(new BorderLayout(5, 2));
        p.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
        p.add(new JLabel(etiqueta), BorderLayout.NORTH);
        p.add(campo, BorderLayout.CENTER);
        return p;
    }

    private void cargarComboPreguntas() {
        cbxPreguntas.removeAllItems();
        for (Question q : service.listarPreguntas()) {
            cbxPreguntas.addItem(q);
        }
    }

    /**
     * Handler del boton "Cargar pregunta": trae del servicio la
     * pregunta elegida y llena el formulario (rol de Vista).
     */
    private void onCargarPregunta(ActionEvent e) {
        Question seleccionada = (Question) cbxPreguntas.getSelectedItem();
        if (seleccionada == null) {
            return;
        }
        txtId.setText(seleccionada.getId());
        txtNombre.setText(seleccionada.getNombre());
        txtPregunta.setText(seleccionada.getPregunta());
        txtOpciones.setText(seleccionada.opcionesComoTexto());
        txtRespuesta.setText(seleccionada.getRespuestaCorrecta());
        lblEstadoActual.setText(seleccionada.getEstado().getEtiqueta());
        cbxNuevoEstado.setSelectedItem(seleccionada.getEstado());
    }

    /**
     * Handler del boton "Actualizar estado": delega en el servicio
     * de dominio el cambio de estado y luego notifica a las vistas
     * observadoras (patron Observer).
     */
    private void onActualizarEstado(ActionEvent e) {
        String id = txtId.getText();
        if (id == null || id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Primero debe cargar una pregunta.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        EstadoPregunta nuevoEstado = (EstadoPregunta) cbxNuevoEstado.getSelectedItem();
        service.cambiarEstado(id, nuevoEstado);

        // refrescar la vista actual con el nuevo estado
        lblEstadoActual.setText(nuevoEstado.getEtiqueta());
        cargarComboPreguntas(); // por si el toString cambiara con el estado

        // notificar a las demas vistas (Observer)
        subject.notificarObservadores(service.conteoPorEstado());
    }

    /**
     * Punto de entrada de la aplicacion de escritorio. Aca se arma
     * "a mano" el arbol de dependencias entre capas (equivalente a
     * lo que en un proyecto mas grande haria un contenedor de
     * inyeccion de dependencias).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // capa de acceso a datos
            QuestionImplRepository repository = new QuestionImplRepository();
            // capa de dominio
            QuestionService service = new QuestionService(repository);
            // capa transversal: subject del patron observer
            Subject subject = new Subject();

            // capa de presentacion
            GUIQuestions ventanaPrincipal = new GUIQuestions(service, subject);
            GUIObserver1 vistaEstadisticas = new GUIObserver1();
            GUIObserver2 vistaGrafica = new GUIObserver2();

            subject.agregarObservador(vistaEstadisticas);
            subject.agregarObservador(vistaGrafica);

            // primer calculo para que las vistas no arranquen vacias
            subject.notificarObservadores(service.conteoPorEstado());

            ventanaPrincipal.setVisible(true);
            vistaEstadisticas.setVisible(true);
            vistaGrafica.setVisible(true);
        });
    }
}
