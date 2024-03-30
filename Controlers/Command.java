/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlers;

import Utils.ICommand;
import Utils.PressRelease;

/**
 *
 * @author mchokre
 */
public class Command implements ICommand {
    
    private RSSData_Controler controler;
    
    public Command(RSSData_Controler controler) {
        this.controler = controler;
    }

    @Override
    public void save(String ID, PressRelease pressRelease) {
        controler.modifyCard(ID, pressRelease);
    }
    
    @Override
    public void save(PressRelease pressRelease) {
        controler.addCard(pressRelease);
    }
    
    @Override
    public void cancel() {
        controler.cancel();
    }
    
}
