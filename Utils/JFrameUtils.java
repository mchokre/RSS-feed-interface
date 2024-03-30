package Utils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;
import java.awt.*;


/**
 *
 * @author mchokre
 */
public abstract class JFrameUtils extends JFrame {
    
    public JFrameUtils() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.WHITE);
    }
    
    protected abstract void drawPage();
    protected abstract void drawMainComponent();
    protected abstract void addTextualContent();
    protected abstract void addInputFields();
    protected abstract void addButtons();
    
}
