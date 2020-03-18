/**
 * Sample Skeleton for 'IndoNumero.fxml' Controller Class
 */

package it.polito.tdp.indonumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroController {
	
	private int NMAX = 100;
	private int TMAX = 7;
	private int segreto = 0;
	private int tentavi = 0;
	 
	private boolean InGame = false;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNuova"
    private Button btnNuova; // Value injected by FXMLLoader

    @FXML // fx:id="txtCurr"
    private TextField txtCurr; // Value injected by FXMLLoader

    @FXML // fx:id="txtMax"
    private TextField txtMax; // Value injected by FXMLLoader

    @FXML // fx:id="boxGioco"
    private HBox boxGioco; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativo"
    private TextField txtTentativo; // Value injected by FXMLLoader

    @FXML // fx:id="bntProva"
    private Button bntProva; // Value injected by FXMLLoader

    @FXML // fx:id="txtLog"
    private TextArea txtLog; // Value injected by FXMLLoader

    @FXML
    void handleNuova(ActionEvent event) {
    	
    	this.segreto = (int) (Math.random() * NMAX) + 1;
    	this.tentavi = 0;
    	this.InGame = true;
    	btnNuova.setDisable(true);
    	boxGioco.setDisable(false);
    	txtCurr.setText(String.format("%d", this.tentavi));
    	txtMax.setText(String.format("%d", this.TMAX));
    	txtLog.setText(("Indovina un numero da 1 a ") + this.NMAX);
    	txtTentativo.setText(null);

    }

    @FXML
    void handleProva(ActionEvent event) {
    	
    	String numS = txtTentativo.getText();
    	if (numS.length() == 0)  {
    		txtLog.appendText("Devi inserire un numero valido\n");
    		this.tentavi = 0;
        	this.InGame = true;
        	btnNuova.setDisable(true);
        	boxGioco.setDisable(false);
        	txtCurr.setText(String.format("%d", this.tentavi));
        	txtMax.setText(String.format("%d", this.TMAX));
    		return; 	
    	}
    	
    	try {
    		int num = Integer.parseInt(numS);
    		if (num < 0 || num > this.NMAX ) {
    			// fuori range
    			txtLog.appendText("Valore fuori dai limiti!!\n");
    			return; }
    		if (num==this.segreto) {
    			// ha indovinato
    			txtLog.appendText("Bravo hai vinto!!\n");
    			// chiudi finestra
    			btnNuova.setDisable(false);
    	 		boxGioco.setDisable(true);
    	 		this.InGame = false;	
    		} else {
    			// tentativo errato
    			this.tentavi ++; 
    			txtCurr.setText(String.format("%d", this.tentavi));
    			if (this.tentavi == this.TMAX) {
    				txtLog.appendText("Numero massimo di tentativi\n");
    				txtLog.appendText("Il numero segreto era : " + this.segreto );
    				// chiudi finestra
    				btnNuova.setDisable(false);
    				boxGioco.setDisable(true);
    				this.InGame = false;	    			
    			} else {
    				// sono ancora in gioco
    				if (num < this.segreto) {
    					// troppo basso
    					txtLog.appendText("Numero basso\n");
    				} else {
    					// troppo alto	
    					txtLog.appendText("Numero alto\n");
    				}
    			}
    		}	
    	} catch(NumberFormatException ex) {
    		txtLog.appendText("Devi inserire soltanto numeri\n");   
    		return; 	
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtCurr != null : "fx:id=\"txtCurr\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert boxGioco != null : "fx:id=\"boxGioco\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert bntProva != null : "fx:id=\"bntProva\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'IndoNumero.fxml'.";

    }
}
