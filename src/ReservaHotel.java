import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Clase principal para el sistema de reservas de hotel.
 */
public class ReservaHotel extends Application {

    private TextField txtNombre, txtDNI, txtDireccion, txtCiudad, txtProvincia;
    private DatePicker fechaLlegada, fechaSalida;
    private Spinner<Integer> spnNumeroHuespedes;
    private ComboBox<String> cbTipoHabitacion, cbTipoPension;
    private CheckBox chkHabitacionFumador;
    private Label lblFumadores;
    private TextArea txtResumen;

    @Override
    public void start(Stage escenarioPrincipal) {
        escenarioPrincipal.setTitle("Sistema de Reservas de Hotel");

        // Crear la escena principal que contiene todos los elementos de la reserva
        VBox panelReserva = new VBox(20);
        panelReserva.setPadding(new Insets(15));

        // Panel de datos del cliente y de la reserva
        GridPane panelCliente = crearPanelCliente();
        GridPane panelDetallesReserva = crearPanelDetallesReserva();

        // Botones de Confirmar, Cancelar y Limpiar
        HBox cajaBotones = crearPanelBotones(escenarioPrincipal);

        // Panel de resumen
        txtResumen = new TextArea();
        txtResumen.setEditable(false);
        txtResumen.setWrapText(true);
        txtResumen.setPromptText("Aquí se mostrarán los detalles de la reserva.");
        txtResumen.setTooltip(new Tooltip("Resumen de la reserva"));

        // Agregar todos los paneles al panel de reserva
        panelReserva.getChildren().addAll(panelCliente, panelDetallesReserva, cajaBotones, txtResumen);

        // Configuración de la escena y visualización
        Scene escenaReserva = new Scene(panelReserva, 1075, 600);
        escenaReserva.getStylesheets().add("estilo.css"); // Cargar el archivo CSS
        escenarioPrincipal.setScene(escenaReserva);
        escenarioPrincipal.show();
    }

    /**
     * Crea y retorna un panel con botones para confirmar, cancelar y limpiar.
     */
    private HBox crearPanelBotones(Stage escenarioPrincipal) {
        Button btnConfirmar = new Button("Confirmar Reserva");
        btnConfirmar.getStyleClass().add("boton-confirmar");
        btnConfirmar.setTooltip(new Tooltip("Confirmar la reserva"));
        btnConfirmar.setOnAction(e -> confirmarReserva());

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.getStyleClass().add("boton-cancelar");
        btnCancelar.setTooltip(new Tooltip("Cancelar la aplicación"));
        btnCancelar.setOnAction(e -> escenarioPrincipal.close());

        Button btnLimpiar = new Button("Limpiar Campos");
        btnLimpiar.getStyleClass().add("boton-limpiar");
        btnLimpiar.setTooltip(new Tooltip("Limpiar todos los campos del formulario"));
        btnLimpiar.setOnAction(e -> limpiarCampos());

        return new HBox(10, btnConfirmar, btnCancelar, btnLimpiar);
    }

    /**
     * Crea y retorna un panel con los datos del cliente.
     */
    private GridPane crearPanelCliente() {
        GridPane cuadriculaCliente = new GridPane();
        cuadriculaCliente.setHgap(15);
        cuadriculaCliente.setVgap(10);
        cuadriculaCliente.setPadding(new Insets(10));

        // Etiqueta y campos para la información del cliente
        Label etiquetaTituloCliente = new Label("Información del Cliente");
        etiquetaTituloCliente.getStyleClass().add("etiqueta-titulo");

        txtNombre = crearCampoTexto("Ingresa tu nombre completo");
        txtDNI = crearCampoTexto("Ingresa tu DNI");
        txtDireccion = crearCampoTexto("Ingresa tu dirección");
        txtCiudad = crearCampoTexto("Ingresa tu ciudad");
        txtProvincia = crearCampoTexto("Ingresa tu provincia");

        cuadriculaCliente.add(etiquetaTituloCliente, 0, 0, 2, 1);
        cuadriculaCliente.add(crearEtiquetaConAtajo("Nombre:", "N", txtNombre), 0, 1);
        cuadriculaCliente.add(txtNombre, 1, 1);
        cuadriculaCliente.add(crearEtiquetaConAtajo("DNI:", "D", txtDNI), 2, 1);
        cuadriculaCliente.add(txtDNI, 3, 1);
        cuadriculaCliente.add(crearEtiquetaConAtajo("Dirección:", "R", txtDireccion), 0, 2);
        cuadriculaCliente.add(txtDireccion, 1, 2);
        cuadriculaCliente.add(crearEtiquetaConAtajo("Ciudad:", "C", txtCiudad), 2, 2);
        cuadriculaCliente.add(txtCiudad, 3, 2);
        cuadriculaCliente.add(crearEtiquetaConAtajo("Provincia:", "P", txtProvincia), 0, 3);
        cuadriculaCliente.add(txtProvincia, 1, 3);

        return cuadriculaCliente;
    }

    /**
     * Crea un campo de texto con un mensaje de aviso.
     */
    private TextField crearCampoTexto(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setTooltip(new Tooltip(promptText));
        return textField;
    }

    /**
     * Crea y retorna una etiqueta con atajo de teclado asociada a un campo de texto.
     */
    private Label crearEtiquetaConAtajo(String texto, String atajo, TextField campoTexto) {
        Label etiqueta = new Label(texto);
        etiqueta.setMnemonicParsing(true); // Activar el parsing de atajos
        etiqueta.setText("_" + texto); // Usar "_" para indicar el carácter de acceso rápido
        etiqueta.setLabelFor(campoTexto);
        etiqueta.setTooltip(new Tooltip("Presiona Alt+" + atajo + " para seleccionar"));
        etiqueta.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        return etiqueta;
    }

    /**
     * Crea y retorna un panel con los detalles de la reserva.
     */
    private GridPane crearPanelDetallesReserva() {
        GridPane cuadriculaReserva = new GridPane();
        cuadriculaReserva.setHgap(15);
        cuadriculaReserva.setVgap(10);
        cuadriculaReserva.setPadding(new Insets(10));

        // Etiqueta y campos para los detalles de la reserva
        Label etiquetaTituloReserva = new Label("Detalles de la Reserva");
        etiquetaTituloReserva.getStyleClass().add("etiqueta-titulo");

        fechaLlegada = new DatePicker();
        fechaLlegada.setTooltip(new Tooltip("Selecciona la fecha de llegada."));

        fechaSalida = new DatePicker();
        fechaSalida.setTooltip(new Tooltip("Selecciona la fecha de salida."));

        spnNumeroHuespedes = new Spinner<>(1, 10, 1);
        spnNumeroHuespedes.setTooltip(new Tooltip("Selecciona el número de huéspedes."));

        cbTipoHabitacion = new ComboBox<>();
        cbTipoHabitacion.getItems().addAll("Doble de uso Individual", "Doble", "Junior Suite", "Suite");
        cbTipoHabitacion.setTooltip(new Tooltip("Selecciona el tipo de habitación."));

        chkHabitacionFumador = new CheckBox("Habitación para fumadores");
        chkHabitacionFumador.setTooltip(new Tooltip("Selecciona si la habitación es para fumadores."));
        chkHabitacionFumador.setOnAction(e -> lblFumadores.setVisible(chkHabitacionFumador.isSelected()));

        lblFumadores = new Label("En virtud de la Ley de Sanidad se informa a los clientes de que sólo podrán fumar en las habitaciones reservadas para tal fin.");
        lblFumadores.setVisible(false);

        cbTipoPension = new ComboBox<>();
        cbTipoPension.getItems().addAll("Alojamiento y Desayuno", "Media Pensión", "Pensión Completa");
        cbTipoPension.setTooltip(new Tooltip("Selecciona el régimen de alojamiento."));

        // Agregar campos al panel de detalles
        cuadriculaReserva.add(etiquetaTituloReserva, 0, 0, 2, 1);
        cuadriculaReserva.add(new Label("Fecha de Llegada:"), 0, 1);
        cuadriculaReserva.add(fechaLlegada, 1, 1);
        cuadriculaReserva.add(new Label("Fecha de Salida:"), 2, 1);
        cuadriculaReserva.add(fechaSalida, 3, 1);
        cuadriculaReserva.add(new Label("Número de Huéspedes:"), 0, 2);
        cuadriculaReserva.add(spnNumeroHuespedes, 1, 2);
        cuadriculaReserva.add(new Label("Tipo de Habitación:"), 2, 2);
        cuadriculaReserva.add(cbTipoHabitacion, 3, 2);
        cuadriculaReserva.add(chkHabitacionFumador, 0, 3);
        cuadriculaReserva.add(lblFumadores, 1, 3, 3, 1);
        cuadriculaReserva.add(new Label("Tipo de Pensión:"), 0, 4);
        cuadriculaReserva.add(cbTipoPension, 1, 4);

        return cuadriculaReserva;
    }

    /**
     * Limpia los campos de entrada.
     */
    private void limpiarCampos() {
        txtNombre.clear();
        txtDNI.clear();
        txtDireccion.clear();
        txtCiudad.clear();
        txtProvincia.clear();
        fechaLlegada.setValue(null);
        fechaSalida.setValue(null);
        spnNumeroHuespedes.getValueFactory().setValue(1);
        cbTipoHabitacion.getSelectionModel().clearSelection();
        chkHabitacionFumador.setSelected(false);
        lblFumadores.setVisible(false);
        cbTipoPension.getSelectionModel().clearSelection();
        txtResumen.clear();
    }

    /**
     * Confirma la reserva y muestra los detalles en el área de resumen.
     */
    private void confirmarReserva() {
        String resumen = String.format("Nombre: %s\nDNI: %s\nDirección: %s\nCiudad: %s\nProvincia: %s\nFecha de Llegada: %s\nFecha de Salida: %s\nNúmero de Huéspedes: %d\nTipo de Habitación: %s\nFumador: %s\nTipo de Pensión: %s",
                txtNombre.getText(),
                txtDNI.getText(),
                txtDireccion.getText(),
                txtCiudad.getText(),
                txtProvincia.getText(),
                fechaLlegada.getValue(),
                fechaSalida.getValue(),
                spnNumeroHuespedes.getValue(),
                cbTipoHabitacion.getValue(),
                chkHabitacionFumador.isSelected() ? "Sí" : "No",
                cbTipoPension.getValue()
        );
        txtResumen.setText(resumen);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
