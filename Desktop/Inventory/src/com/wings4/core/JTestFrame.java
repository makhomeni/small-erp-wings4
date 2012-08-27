/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wings4.core;

/**
 *
 * @author ronnie
 */
//http://forums.codeguru.com/archive/index.php/t-35341.html
import javax.swing.*;

public class JTestFrame extends JFrame{
	public JTestFrame(){
		getContentPane().setLayout(null);
		setSize(426,266);
		getContentPane().add(JComboBox1);
		JComboBox1.setBounds(84,96,300,24);
	}
	public static void main(String[] str){
		new JTestFrame().setVisible( true );
	}

	JTableCombo JComboBox1 = new JTableCombo();
}
