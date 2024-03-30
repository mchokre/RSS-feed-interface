/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

//Aplication classes
import Controlers.RSSData_Controler;
import Utils.AddPageEvent;
import Utils.AddedItemEvent;
import Utils.ApplicationStartupData;
import Utils.CancelEvent;
import Utils.DeletedItemEvent;
import Utils.ModifyPageEvent;
import Utils.JFrameUtils;
import Utils.ModifiedItemEvent;
import Utils.Observer;
import Utils.PressRelease;
// Java API classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 *
 * @author mchokre
 */
public class HomePage extends JFrameUtils implements Observer, ActionListener {

    private RSSData_Controler controler;
    private ArrayList<PressRelease> pressReleaseList;
    private PressRelease currentPress;
    private JLabel pageTitle;
    private JPanel mainContent, cardPanel, cardTextContent, cardButtons;
    private JButton addPressRelease, consult, modify, delete;
    private GridBagConstraints gbc = new GridBagConstraints();
    
    public HomePage(RSSData_Controler controler) {
        this.controler = controler;
    }

    @Override
    public void notify(Object event) {
        if(event instanceof  ApplicationStartupData) {
            setVisible(true);
            //ApplicationStartupData
            ApplicationStartupData startupData = (ApplicationStartupData) event;
            pressReleaseList = startupData.getEventData();
            //Draw main components
            drawPage();
            //Draw each card component
            pressReleaseList.forEach(press -> {
                currentPress = press;
                drawMainComponent();
                currentPress = null;
            });
        }
        if(event instanceof DeletedItemEvent) {
            //DeletedCardEvent
            DeletedItemEvent deleteEventData = (DeletedItemEvent) event;
            pressReleaseList = deleteEventData.getEventData();
            //Remove all components and refresh the JPanel
            mainContent.removeAll();
            mainContent.validate();
            mainContent.repaint();
            //Draw each card component
            pressReleaseList.forEach(press -> {
                currentPress = press;
                drawMainComponent();
                currentPress = null;
            });
        }
        if(event instanceof ModifyPageEvent) {
            setVisible(false);
        }
        if(event instanceof ModifiedItemEvent) {
            //DeletedCardEvent
            ModifiedItemEvent modifiedItemEvent = (ModifiedItemEvent) event;
            pressReleaseList = modifiedItemEvent.getEventData();
            //Remove all components and refresh the JPanel
            mainContent.removeAll();
            mainContent.validate();
            mainContent.repaint();
            //Draw each card component
            pressReleaseList.forEach(press -> {
                currentPress = press;
                drawMainComponent();
                currentPress = null;
            });
            setVisible(true);
        }
        if(event instanceof AddPageEvent) {
            setVisible(false);
        }
        if(event instanceof AddedItemEvent) {
            //AddedItemEvent
            AddedItemEvent addedItemEvent = (AddedItemEvent) event;
            pressReleaseList = addedItemEvent.getEventData();
            //Remove all components and refresh the JPanel
            mainContent.removeAll();
            mainContent.validate();
            mainContent.repaint();
            //Draw each card component
            pressReleaseList.forEach(press -> {
                currentPress = press;
                drawMainComponent();
                currentPress = null;
            });
            setVisible(true);
        }
        if(event instanceof CancelEvent) {
            setVisible(true);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //User clicked the delete button
        if("delete".equals(e.getActionCommand())) {
            String cardTitle = getCardTitle(e.getSource());
            handleDeleteButtonAction(cardTitle);
        } else if("consult".equals(e.getActionCommand())) {
            String cardTitle = getCardTitle(e.getSource());
            controler.consultPressRelease(cardTitle);
        } else if("modify".equals(e.getActionCommand())) {
            String cardTitle = getCardTitle(e.getSource());
            controler.modifyRequest(cardTitle);
        } else if("add".equals(e.getActionCommand())) {
            controler.addRequest();
        }
    }

    @Override
    protected void drawPage() {
        gbc.insets = new Insets(10,10,10,10);
        // Add page title component
        pageTitle = new JLabel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        pageTitle.setText("Communiqués de presse de l'Université TÉLUQ");
        pageTitle.setFont(new Font("Arial", Font.BOLD, 20));
        add(pageTitle, gbc);
        // Add main content component
        mainContent = new JPanel();
        mainContent.setPreferredSize(new Dimension(1280, 775));
        mainContent.setLayout(new FlowLayout());
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainContent.setBackground(Color.WHITE);
        add(mainContent, gbc);
        // Add button component
        addPressRelease = new JButton();
        addPressRelease.setPreferredSize(new Dimension(200,75));
        gbc.gridx = 0;
        gbc.gridy = 2;
        addPressRelease.setBackground(new java.awt.Color(95, 158, 160));
        addPressRelease.setBorder(BorderFactory.createEmptyBorder());
        addPressRelease.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addPressRelease.setText("Ajouter un évènement  +");
        addPressRelease.setFont(new Font("Arial", Font.BOLD, 15));
        addPressRelease.addActionListener(this);
        addPressRelease.setActionCommand("add");
        add(addPressRelease, gbc);
    }
    
    @Override
    protected void drawMainComponent() {
        //Press release JPanel
        cardPanel = new JPanel();
        cardPanel.setPreferredSize(new Dimension(400, 150));
        cardPanel.setLayout(new GridBagLayout());
        cardPanel.setBackground(new java.awt.Color(245,245,245));
        mainContent.add(cardPanel);
        //JPanel to group textuel components
        cardTextContent = new JPanel();
        cardTextContent.setPreferredSize(new Dimension(325,150));
        cardTextContent.setLayout(new GridBagLayout());
        cardPanel.add(cardTextContent);
        addTextualContent();
        //JPanel to group buttons
        cardButtons = new JPanel();
        cardButtons.setPreferredSize(new Dimension(75,150));
        cardPanel.add(cardButtons);
        addButtons();
        //Refresh
        mainContent.revalidate();
        mainContent.repaint();
    }
    
    @Override
    protected void addTextualContent() {
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Add card title
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel title = new JLabel();
        title.setText(currentPress.getTitle());
        title.setFont(new Font("Arial", Font.BOLD, 10));
        title.setHorizontalAlignment(SwingConstants.LEFT);
        cardTextContent.add(title, gbc);
        // Add card description
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel description = new JLabel();
        description.setText(currentPress.getDescription());
        description.setFont(new Font("Arial", Font.PLAIN, 10));
        cardTextContent.add(description, gbc);
        // Add card date
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel date = new JLabel();
        date.setText(currentPress.getDate());
        date.setFont(new Font("Arial", Font.PLAIN, 10));
        cardTextContent.add(date, gbc);
        //Refresh JPanel
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    @Override
    protected void addButtons() {
        //Include consult card button
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        consult = new JButton();
        consult.setPreferredSize(new Dimension(75,30));
        consult.setBackground(new java.awt.Color(0,206,209));
        consult.setBorder(BorderFactory.createEmptyBorder());
        consult.setCursor(new Cursor(Cursor.HAND_CURSOR));
        consult.setText("Consulter");
        consult.setFont(new Font("Arial", Font.BOLD, 10));
        consult.setForeground(Color.WHITE);
        consult.addActionListener(this);
        consult.setActionCommand("consult");
        cardButtons.add(consult, gbc);
        //Include modify card button
        this.gbc.gridx = 0;
        this.gbc.gridy = 1;
        modify = new JButton();
        modify.setPreferredSize(new Dimension(75,30));
        modify.setBackground(new java.awt.Color(30,144,255));
        modify.setBorder(BorderFactory.createEmptyBorder());
        modify.setCursor(new Cursor(Cursor.HAND_CURSOR));
        modify.setText("Modifier");
        modify.setFont(new Font("Arial", Font.BOLD, 10));
        modify.setForeground(Color.WHITE);
        modify.addActionListener(this);
        modify.setActionCommand("modify");
        cardButtons.add(modify, gbc);
        //Include delete card button
        this.gbc.gridx = 0;
        this.gbc.gridy = 2;
        delete = new JButton();
        delete.setPreferredSize(new Dimension(75,30));
        delete.setBackground(new java.awt.Color(220,20,60));
        delete.setBorder(BorderFactory.createEmptyBorder());
        delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        delete.setText("Supprimer");
        delete.setFont(new Font("Arial", Font.BOLD, 10));
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        delete.setActionCommand("delete");
        cardButtons.add(delete);
        //Refresh JPanel
        cardPanel.revalidate();
        cardPanel.repaint();
    }
    
    @Override
    protected void addInputFields() {
        
    }
        
    private String getCardTitle(Object source) {
        JButton pressedButton = (JButton) source;
        JPanel buttonPanel = (JPanel) pressedButton.getParent();
        JPanel currentCardPanel = (JPanel) buttonPanel.getParent();
        JPanel textPanel = (JPanel) currentCardPanel.getComponent(0);
        JLabel cardTitle = (JLabel) textPanel.getComponent(0);
        
        return cardTitle.getText();
    }

    private void handleDeleteButtonAction(String cardTitle) {
        Object[] options = {"Oui", "Annuler"};
        int decision = JOptionPane.showOptionDialog(
            this, 
            """
            Voulez-vous supprimer cet évènement RSS : 
            Titre: 
            """,
            "Panneau de confirmation", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            null);
        switch(decision) {
            case JOptionPane.YES_OPTION:
            this.controler.deleteCard(cardTitle); 
            break;
            case JOptionPane.NO_OPTION:
                System.out.println("The user pressed the Cancel button");
                break;
            default:
                System.out.println("The user did not select an option");
        }
    }
    
}
    