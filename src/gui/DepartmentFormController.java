package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

public class DepartmentFormController implements Initializable {

	// criando uma dependencia para o departamento
	private Department entity;
	
	
	//declaração dos campos para interação com o form
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private Label labelErrorName;
	
	@FXML
	private Button btSave;
	
	@FXML
	private Button btCancel;
	
	//implementando o método set da entity
	public void setDepartment(Department entity) {
		this.entity = entity;
	}
	
	
	//Declaração dos métodos para tratar as açoes dos botões 
	@FXML
	public void onBtSaveActioon() {
		System.out.println("onBtSaveAction");
	}
	
	@FXML
	public void onBtCancelAction() {
		System.out.println("onBtCancelAction");
	}
	
	
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	//chama o controlador de restrições
		initializeNodes();
	}

	//iniciamos inserindo restrições
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}
	
	//popular as textbox do formulario com as informações do objeto entity
	public void updateFormData() {
		//programação defensiva pra testar se o entity está nulo
		if(entity ==null) {
			throw new IllegalStateException("Entity was null");
		}
		//fim da verificação
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
		
	}
}
