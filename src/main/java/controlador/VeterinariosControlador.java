package controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class VeterinariosControlador implements Initializable {

    @FXML private TextField txtBuscar;
    @FXML private TableView<?> tablaVeterinarios;
    @FXML private TableColumn<?, ?> colCedula;
    @FXML private TableColumn<?, ?> colNombre;
    @FXML private TableColumn<?, ?> colApellido;
    @FXML private TableColumn<?, ?> colEspecialidad;
    @FXML private TableColumn<?, ?> colLicencia;
    @FXML private TableColumn<?, ?> colTelefono;
    @FXML private TableColumn<?, ?> colCorreo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleBuscar() {
        System.out.println("Buscando: " + txtBuscar.getText());
    }

    @FXML
    private void handleNuevo() {
        System.out.println("Nuevo veterinario");
    }

    @FXML
    private void handleEditar() {
        if (tablaVeterinarios.getSelectionModel().getSelectedItem() == null) {
            mostrarAlerta("Seleccione un veterinario para editar.");
            return;
        }
        System.out.println("Editar veterinario");
    }

    @FXML
    private void handleEliminar() {
        if (tablaVeterinarios.getSelectionModel().getSelectedItem() == null) {
            mostrarAlerta("Seleccione un veterinario para eliminar.");
            return;
        }
        System.out.println("Eliminar veterinario");
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
