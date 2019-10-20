
package controller;

import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;
public class DisplayPanel {
    
    private int currentIndex;
    private final ArrayList displayList; 
    
    public DisplayPanel() {
        this.currentIndex = 0;
        this.displayList = new ArrayList();
    }
    
    public int getCurrentIndex() {
        return this.currentIndex;
    }
    
    public void setCurrentIndex(int current) {
        this.currentIndex = current;
    }
    
    public boolean insertDisplay(AnchorPane newDisplay) {
        return this.displayList.add(newDisplay);
    }
    
    public boolean removeDisplay(int index) {
        return this.displayList.remove(index) != null;
    }
    
    /*Activa visualización del panel designado en la posicion ingresada*/
    public void activateDisplay(int index) {
        
        //Obtiene panel actual desde la lista
        AnchorPane currentDisplay = (AnchorPane) this.displayList.get(currentIndex);
        currentDisplay.setVisible(false);
        currentDisplay.setDisable(true);
        
        //Obtiene nuevo panel 
        AnchorPane newDisplay = (AnchorPane) this.displayList.get(index);
        setCurrentIndex(index);
        newDisplay.setVisible(true);
        newDisplay.setDisable(false);
    }
}