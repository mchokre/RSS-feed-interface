/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import org.jdom2.Element;

/**
 *
 * @author mchokre
 */
public class AddPageEvent extends EventObject {
    
    private PressRelease pressRelease;
    
    public AddPageEvent() {}

    @Override
    public PressRelease getEventData() {
        return null;
    }

    @Override
    protected void extractStringsFromElements(Object object) {}
    
}