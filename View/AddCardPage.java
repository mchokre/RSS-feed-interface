/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

//Aplication classes
import Controlers.Command;
import Controlers.RSSData_Controler;
import Utils.AddPageEvent;
import Utils.AddedItemEvent;
import Utils.CancelEvent;
import Utils.ICommand;
import Utils.JFrameUtils;
import Utils.ModifiedItemEvent;
import Utils.ModifyPageEvent;
import Utils.Observer;
import Utils.PressRelease;
// Java API classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author mchokre
 */
public class AddCardPage extends JFrameUtils implements Observer, ActionListener {
    
    private RSSData_Controler controler;
    private ICommand saveCommand, cancelCommand;
    private PressRelease currentPress;
    private JLabel pageTitle;
    private JPanel mainContent, labelFields, inputFields;
    private JButton save, cancel;
    private GridBagConstraints gbc = new GridBagConstraints();
    
    public AddCardPage(RSSData_Controler controler) {
        this.controler = controler;
        currentPress = new PressRelease();
        saveCommand = new Command(controler);
        cancelCommand = new Command(controler);
    }
    
    @Override
    public void notify(Object event) {
        if(event instanceof AddPageEvent) {
            //ApplicationStartupData
            //Draw components
            drawPage();
            //Draw form components
            drawMainComponent();
            setVisible(true);
        }
        if(event instanceof AddedItemEvent || event instanceof CancelEvent) {
            getContentPane().removeAll();
            validate();
            repaint();
            setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField title, description, link;
        title = (JTextField) inputFields.getComponent(0);
        description = (JTextField) inputFields.getComponent(1);
        link = (JTextField) inputFields.getComponent(2);
        if(e.getSource() == save) {
            currentPress.setTitle(title.getText());
            currentPress.setDescription(description.getText());
            currentPress.setLink(link.getText());
            saveCommand.save(currentPress);
        }
        if(e.getSource() == cancel) {
            cancelCommand.cancel();
        }
    }
    
    @Override
    protected void drawPage() {
        gbc.weighty = 0;
        gbc.weightx = 0;
        gbc.fill = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10,10,10,10);
        // Set page title component
        pageTitle = new JLabel();
        pageTitle.setText("Modifier un évènement du fils RSS");
        pageTitle.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(pageTitle);
        //Set main content components
        mainContent = new JPanel();
        mainContent.setPreferredSize(new Dimension(480, 320));
        mainContent.setLayout(new GridBagLayout());
        mainContent.setBackground(new java.awt.Color(245,245,245));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(mainContent, gbc);
        //Add buttons
        addButtons();
    }
    
    @Override
    protected void drawMainComponent() {
        gbc.weighty = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        //Label component holder
        labelFields = new JPanel();
        labelFields.setPreferredSize(new Dimension(80, 320));
        labelFields.setLayout(new GridBagLayout());
        labelFields.setBackground(new java.awt.Color(245,245,245));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainContent.add(labelFields, gbc);
        addTextualContent();
        //Input component holder
        inputFields = new JPanel();
        inputFields.setPreferredSize(new Dimension(400, 320));
        inputFields.setLayout(new GridBagLayout());
        inputFields.setBackground(new java.awt.Color(245,245,245));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        mainContent.add(inputFields, gbc);
        addInputFields();
    }
    
    @Override
    protected void addTextualContent() {
        gbc.weighty = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_START;
        //Title label
        JLabel title = new JLabel("Titre ", JLabel.TRAILING);
        gbc.gridx = 0;
        gbc.gridy = 0;
        labelFields.add(title, gbc);
        //Description label
        JLabel description = new JLabel("Description ", JLabel.TRAILING);
        gbc.gridx = 0;
        gbc.gridy = 1;
        labelFields.add(description, gbc);
        //Link label
        JLabel link = new JLabel("Lien ", JLabel.TRAILING);
        gbc.gridx = 0;
        gbc.gridy = 2;
        labelFields.add(link, gbc);
    }

    @Override
    protected void addInputFields() {
        gbc.weighty = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_START;
        //Title input field
        JTextField titleField = new JTextField(10);
        titleField.setPreferredSize(new Dimension(300, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputFields.add(titleField, gbc);
        //Description input field
        JTextField descriptionField = new JTextField(10);
        descriptionField.setPreferredSize(new Dimension(300, 50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputFields.add(descriptionField, gbc);
        //Link input field
        JTextField linkField = new JTextField(10);
        linkField.setPreferredSize(new Dimension(300, 50));
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputFields.add(linkField, gbc);
    }

    @Override
    protected void addButtons() {
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonContainer = new JPanel();
        buttonContainer.setPreferredSize(new Dimension(480, 50));
        buttonContainer.setLayout(new GridBagLayout());
        buttonContainer.setBackground(Color.WHITE);
        add(buttonContainer, gbc);
        //Add save button
        save = new JButton();
        save.setBackground(new java.awt.Color(255,253,175));
        save.setText("Enregistrer");
        save.addActionListener(this);
        buttonAppearance(save);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonContainer.add(save, gbc);
        //Add cancel button
        cancel = new JButton();
        cancel.setBackground(new java.awt.Color(95, 158, 160));
        cancel.setText("Annuler");
        cancel.addActionListener(this);
        buttonAppearance(cancel);
        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonContainer.add(cancel, gbc);
    }

    private void buttonAppearance(JButton button) {
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        button.setPreferredSize(new Dimension(100, 50));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("Arial", Font.BOLD, 10));
    }
    
}
