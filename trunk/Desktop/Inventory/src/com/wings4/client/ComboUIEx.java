/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wings4.client;

/**
 *
 * @author ronnie
 */
import javax.swing.plaf.basic.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.io.Serializable;
import java.applet.Applet;

public class ComboUIEx extends BasicComboBoxUI{
	public void installUI( JComponent c ) {

		isMinimumSizeDirty = true;

		comboBox = (JComboBox)c;
		installDefaults();
		popup = createMyPopup();
		if ( comboBox.getRenderer() == null || comboBox.getRenderer() instanceof UIResource ) {
		comboBox.setRenderer( createRenderer() );
		}

		if ( comboBox.getEditor() == null || comboBox.getEditor() instanceof UIResource ) {
		comboBox.setEditor( createEditor() );
		}

		installComponents();
		installListeners();

		addEditor();

		if ( arrowButton != null ) {
		configureArrowButton();
		}
		if ( editor != null ) {
		configureEditor();
		}
		comboBox.setLayout( createLayoutManager() );
		comboBox.setRequestFocusEnabled( true );

	}
	protected ComboPopup createMyPopup() {
		WindowPopup popup = new WindowPopup( comboBox );
		return popup;
	}
}

class WindowPopup extends JWindow implements ComboPopup{
	JComboBox	 comboBox;
	protected MouseMotionListener mouseMotionListener;
	protected MouseListener mouseListener;
	protected KeyListener keyListener;
	JPanel panel;

	public WindowPopup ( JComboBox comboBox){
		this.comboBox = comboBox;
		panel = ( JPanel )getContentPane();
		panel.setLayout( new BorderLayout());
		panel.add( new JScrollPane(new JTable( 3 , 3 )) , BorderLayout.CENTER );
		this.setSize( 230,240 );
		this.setVisible( false );
		panel.setBorder( BorderFactory.createLineBorder( Color.black ) );
	}

	public void uninstallingUI(){
	}

	public MouseMotionListener getMouseMotionListener(){
		return new InvocationMouseMotionHandler();
	}

	public KeyListener getKeyListener(){
		return new InvocationKeyHandler();
	}

	public MouseListener getMouseListener(){
		return new InvocationMouseHandler();
	}

	public JList getList(){
		return null;
	}

	public void setVisible( boolean bFlag ){
		if( bFlag ){
			Point p = comboBox.getLocationOnScreen();
			Rectangle rect = comboBox.getBounds();
			this.setLocation( calculatePosition() );
		}

		super.setVisible( bFlag );
	}

	protected class InvocationMouseHandler extends MouseAdapter {
		public void mousePressed( MouseEvent e ) {
			if( isVisible() ){
				setVisible( false );
			}else{
				setVisible( true );
			}
		}
	}

	protected class InvocationMouseMotionHandler extends MouseMotionAdapter {
		public void mouseDragged( MouseEvent e ) {
		}
	}

	public class InvocationKeyHandler extends KeyAdapter {
		public void keyReleased( KeyEvent e ) {
		}
	}

	public Point calculatePosition(){
		Point p = comboBox.getLocationOnScreen();
		Rectangle rect = comboBox.getBounds();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowSize = this.getSize();

		if( ( p.y + rect.height + windowSize.height ) > screenSize.height ){
			if( p.x < 0 )p.x = 0;
				return new Point( p.x , p.y - windowSize.height );
		}else{
			if( p.x < 0 )p.x = 0;
			return new Point( p.x , p.y + rect.height );
		}
	} 
}

