package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable{

	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerACtion() {
		System.out.println("onMenuItemSellerACtion");
	}

	@FXML
	public void onMenuItemDepartmentACtion() {
		loadView("/gui/DepartmentList.fxml");
	}

	@FXML
	public void onMenuItemAboutACtion() {
		loadView("/gui/About.fxml");
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	}

	private synchronized void loadView(String absoluteName) {
		
		try {
			//Fazendo a sobrebrosicao da tela principal com uma tela nova mantendo o menu
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);//Pega o primeiro filho do VBOX da janela principal q é o menu
			mainVBox.getChildren().clear();//Limpa toda a main VBox
			mainVBox.getChildren().add(mainMenu);//Adiciona o menu
			mainVBox.getChildren().addAll(newVBox.getChildren());//Adiciona tudo da tela nova na tela antiga
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view",e.getMessage(), AlertType.ERROR);
		}
		
	}
}
