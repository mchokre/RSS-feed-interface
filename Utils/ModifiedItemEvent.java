/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.util.ArrayList;
import org.jdom2.Element;

/**
 *
 * @author mchokre
 */
public class ModifiedItemEvent extends EventObject {

    private ArrayList <PressRelease> pressReleaseList = new ArrayList<>();
    
    public ModifiedItemEvent(ArrayList <Element> itemList) {
        extractStringsFromElements(itemList);
    }
    
    @Override
    public ArrayList <PressRelease> getEventData() {
        return this.pressReleaseList;
    }

    @Override
    protected void extractStringsFromElements(Object object) {
        ArrayList <Element> itemList;
        itemList = (ArrayList <Element>) object;
        itemList.forEach(item -> {
            PressRelease pressRelease = new PressRelease();
            pressRelease.setTitle(item.getChildText("title"));
            pressRelease.setDescription(item.getChildText("description"));
            pressRelease.setDate(item.getChildText("pubDate"));
            pressRelease.setLink(item.getChildText("link"));
            pressReleaseList.add(pressRelease);
        });
    }
    
}
