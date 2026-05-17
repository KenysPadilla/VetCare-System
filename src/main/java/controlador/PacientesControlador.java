package controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PacientesControlador implements Initializable {

    @FXML private TextField txtBuscar;
    @FXML private TableView<?> tablaPacientes;
    @FXML private TableColumn<?, ?> colId;
    @FXML private TableColumn<?, ?> colNombre;
    @FXML private TableColumn<?, ?> colEspecie;
    @FXML private TableColumn<?, ?> colRaza;
    @FXML private TableColumn<?, ?> colSexo;
    @FXML private TableColumn<?, ?> colPeso;
    @FXML private TableColumn<?, ?> colEdad;
    @FXML private TableColumn<?, ?> colMicrochip;
    @FXML private TableColumn<?, ?> colPropietario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleBuscar() {
        System.out.println("Buscando: " + txtBuscar.getText());
    }

    @FXML
    private void handleNuevo() {
        System.out.println("Nuevo paciente");
    }

    @FXML
    private void handleEditar() {
        if (tablaPacientes.getSelectionModel().getSelectedItem() == null) {
            mostrarAlerta("Seleccione un paciente para editar.");
            return;
        }
        System.out.println("Editar paciente");
    }

    @FXML
    private void handleEliminar() {
        if (tablaPacientes.getSelectionModel().getSelectedItem() == null) {
            mostrarAlerta("Seleccione un paciente para eliminar.");
            return;
        }
        System.out.println("Eliminar paciente");
    }

    @FXML
    private void handleHistorial() {
        if (tablaPacientes.getSelectionModel().getSelectedItem() == null) {
            mostrarAlerta("Seleccione un paciente para ver su historial.");
            return;
        }
        System.out.println("Ver historial clínico");
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
