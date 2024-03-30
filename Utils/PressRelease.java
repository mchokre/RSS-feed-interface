/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author mchokre
 */
public class PressRelease {
    
    private String title, description, date, link;
    
    public PressRelease() {
        
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public String getLink() {
        return link;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public void setLink(String link) {
        this.link = link;
    }
    
}
