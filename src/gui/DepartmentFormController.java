package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {

	// criando uma dependencia para o departamento
	private Department entity;
	
	// criando uma dependencia para o DepartmentService
	private DepartmentService service;
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
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
	
	//implementando o metodo set do service
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	public void subscribeDataChamngeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	//Declaração dos métodos para tratar as açoes dos botões 
	@FXML
	public void onBtSaveActioon(ActionEvent event) {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();
			//salvando no banco de dados
			service.saveOrUpdate(entity);
			notifyDataChangeListeners();
			//fechar a janela apos salvar
			Utils.currentStage(event).close();
		}
		catch(DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
		
	}

	private Department getFormData() {
		Department obj = new Department();
		
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setName(txtName.getText());
		
		return obj;
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
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
