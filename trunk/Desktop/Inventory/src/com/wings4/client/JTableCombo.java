/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wings4.client;

/**
 *
 * @author ronnie
 */
import javax.swing.*;
import java.awt.*;

public class JTableCombo extends JComboBox{
	public JTableCombo(){
		super();
		this.setUI( new ComboUIEx());
	}
}
