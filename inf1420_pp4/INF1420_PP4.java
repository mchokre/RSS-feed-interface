/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inf1420_pp4;

//Aplication classes
import Controlers.RSSData_Controler;
import Models.RSSData_Model;
import View.*;

/**
 *
 * @author mchokre
 */
public class INF1420_PP4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialise startup objects
        RSSData_Model model = new RSSData_Model();
        RSSData_Controler controler = new RSSData_Controler(model);
        HomePage homePage = new HomePage(controler);
        ModifyCardPage modifyCardPage  = new ModifyCardPage(controler);
        AddCardPage addCardPage = new AddCardPage(controler);
        
        //Add the View objects to the model subscribers queue
        model.addObserver(homePage);
        model.addObserver(modifyCardPage);
        model.addObserver(addCardPage);
        
        //Get RSS Feed data and notify the HomePage class
        model.loadApplicationData();
    }
    
}
