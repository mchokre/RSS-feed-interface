/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlers;

//Aplication classes
import Models.RSSData_Model;
import Utils.PressRelease;
import java.util.ArrayList;

/**
 *
 * @author mchokre
 */
public class RSSData_Controler {
    
    RSSData_Model model;

    public RSSData_Controler(RSSData_Model model) {
        this.model = model;
    }

    public void deleteCard(String cardTitle) {
        model.deleteItem(cardTitle);
    }

    public void consultPressRelease(String cardTitle) {
        model.processLinkRequest(cardTitle);
    }

    public void modifyRequest(String cardTitle) {
        model.getCardContent(cardTitle);
    }

    public void modifyCard(String ID, PressRelease pressRelease) {
        model.modifyItem(ID, pressRelease);
    }
    
    public void addRequest() {
        model.addPageRequest();
    }

    void addCard(PressRelease pressRelease) {
        model.addItem(pressRelease);
    }
    
    public void cancel() {
        model.cancel();
    }
 
}
