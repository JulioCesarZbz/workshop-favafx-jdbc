package gui.util;

import java.awt.event.ActionEvent;

import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	public static Stage currentStage(javafx.event.ActionEvent event) {
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}
	
	//converter o valor do textbox para Int
	public static Integer tryParseToInt(String str) {
		// se for lançado qualquer caractere diferente de numero, a aplicação retorna um valor nulo
		try {
		return Integer.parseInt(str);
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
		
}
