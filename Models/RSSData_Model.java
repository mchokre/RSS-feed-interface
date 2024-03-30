/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

//Application classes
import Utils.AddPageEvent;
import Utils.AddedItemEvent;
import Utils.CancelEvent;
import Utils.Observer;
import Utils.ApplicationStartupData;
import Utils.DeletedItemEvent;
import Utils.ModifiedItemEvent;
import Utils.ModifyPageEvent;
import Utils.PressRelease;
import java.awt.Desktop;
// Java API classes
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author mchokre
 */
public class RSSData_Model {
    
    private ArrayList<Observer> observers;
    private ArrayList<Element> itemList;
    
    public RSSData_Model() {
        this.observers = new ArrayList<>();
        this.itemList = new ArrayList<>();
    }
    
    public void loadApplicationData() {
        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = RSSFeedToFile();
            Document jdomDoc = (Document) builder.build(xmlFile);
            Element root = jdomDoc.getRootElement();
            createElementList(root.getChild("channel"));
            writeToFile();
            notifyObservers(new ApplicationStartupData(itemList));
        } catch(IOException | JDOMException e) {
        }
    }
    
    public void deleteItem(String title) {
        for(int i = 0; i < itemList.size(); i++) {
            if(itemList.get(i).getChildText("title").equals(title)) {
                Element currentElement = itemList.get(i);
                itemList.remove(currentElement);
                currentElement.getParent().removeContent(currentElement);
            }
        }
        writeToFile();
        notifyObservers(new DeletedItemEvent(itemList));
    }
    
    public void processLinkRequest(String title) {
        itemList.forEach(item -> {
            if(item.getChildText("title").equals(title)) {
                try {
                    Desktop.getDesktop().browse(new URL(item.getChildText("link")).toURI());
                } catch(IOException | URISyntaxException e) {
                }
            }
        });
    }
    
    public void getCardContent(String cardTitle) {
        itemList.forEach(item -> {
            if(item.getChildText("title").equals(cardTitle)) {
                notifyObservers(new ModifyPageEvent(item));
            }
        });
    }
    
    public void modifyItem(String ID, PressRelease pressRelease) {
        itemList.forEach(item -> {
            if(item.getChildText("title").equals(ID)) {
                item.getChild("title").setText(pressRelease.getTitle());
                item.getChild("description").setText(pressRelease.getDescription());
                item.getChild("link").setText(pressRelease.getLink());
            }
        });
        writeToFile();
        notifyObservers(new ModifiedItemEvent(itemList));
    }
    
    public void addPageRequest() {
        notifyObservers(new AddPageEvent());
    }
    
    public void addItem(PressRelease pressRelease) {
        Element newPressRelease = new Element("item");
        Element title = new Element("title");
        title.setText(pressRelease.getTitle());
        Element link = new Element("link");
        link.setText(pressRelease.getLink());
        Element guid = new Element("guid");
        guid.setText(link.getText());
        Element pubDate = new Element("pubDate");
        Date currentDate = new Date();
        pubDate.setText(currentDate.toString());
        Element description = new Element("description");
        description.setText(pressRelease.getDescription());
        newPressRelease.addContent(title);
        newPressRelease.addContent(link);
        newPressRelease.addContent(guid);
        newPressRelease.addContent(pubDate);
        newPressRelease.addContent(description);
        Element channel = (Element) itemList.getFirst().getParent();
        channel.addContent(7, newPressRelease);
        itemList.removeAll(itemList);
        createElementList(channel);
        writeToFile();
        notifyObservers(new AddedItemEvent(itemList));
    }
    
    public void cancel() {
        notifyObservers(new CancelEvent(itemList));
    }
    
    public synchronized void addObserver(Observer o) {
        observers.add(o);
    }
    
    public synchronized void removeObserver(Observer o) {
        observers.remove(o);
    }
    
    protected void notifyObservers(Object event) {
        for(Observer observer : observers) {
            observer.notify(event);
        }
    }
    
    private File RSSFeedToFile() throws IOException {
        URL url = new URL("https://www.teluq.ca/site/infos/rss/communiques.php");
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream("XMLDocument.xml");
        byte[] buffer = new byte[1024];
        int count = 0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
        
        return new File("XMLDocument.xml");
    }

    private void createElementList(Element channel) {
        List <Element> listOfItems = channel.getChildren("item");
        Iterator i = listOfItems.iterator();
        while(i.hasNext()) {
            Element currentItem = (Element) i.next();
            this.itemList.add(currentItem);
        }
    }

    private void writeToFile() {
        try {
            FileWriter myWriter = new FileWriter("flux.txt");
            Iterator i = itemList.iterator();
            while(i.hasNext()) { 
                Element currentItem = (Element) i.next();
                myWriter.write( " Titre: "       + currentItem.getChildText("title")    
                                                 + "\n" +
                                " Lien: "        + currentItem.getChildText("link")     
                                                 + "\n" +
                                " Guid: "        + currentItem.getChildText("guid")     
                                                 + "\n" +
                                " Date: "        + currentItem.getChildText("pubDate")  
                                                 + "\n" +
                                " Description: " + currentItem.getChildText("description") 
                                                 + "\n\n\n\n\n\n\n"
                              );
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
        
}
