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
public class ModifyPageEvent extends EventObject {
    
    private PressRelease pressRelease;
    
    public ModifyPageEvent(Element item) {
        extractStringsFromElements(item);
    }

    @Override
    public PressRelease getEventData() {
        
        return pressRelease;
    }

    @Override
    protected void extractStringsFromElements(Object object) {
        Element item;
        item = (Element) object;
        pressRelease = new PressRelease();
        pressRelease.setTitle(item.getChildText("title"));
        pressRelease.setDescription(item.getChildText("title"));
        pressRelease.setDate(item.getChildText("title"));
        pressRelease.setLink(item.getChildText("link"));
    }
    
}
