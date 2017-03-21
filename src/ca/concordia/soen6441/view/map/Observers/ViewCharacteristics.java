package ca.concordia.soen6441.view.map.Observers;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import ca.concordia.soen6441.constants.Constants;
import ca.concordia.soen6441.d20.fighter.Fighter;

/**
 * implements the frame that shows the selected map object's characteristcs
 *
 */
public class ViewCharacteristics extends JFrame implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fighter fighter;
	private Constants constants;
	private JTextArea jTextArea ;
	
	/**
	 * constructor
	 * @param fighter the fighter to show its characteristics
	 */
	@SuppressWarnings("static-access")
	public ViewCharacteristics(Fighter fighter) {
		setFighter(fighter);
		//enable absolute positioning mode 
		setLayout(null);
		setName(fighter.getName());
		//setting up location
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); 
		setSize(dimension.width/2,dimension.height/2);
		setLocation(getWidth() / 2  , getHeight() / 2 );
		
		
		jTextArea = new JTextArea();
		initializeButton(jTextArea,"JTextArea",10,40,0,constants.LOCATION_HEIGHT_OFFSET,0);
		
		jTextArea.insert(fighter.show(false),0);
		setVisible(true);
	}

	@SuppressWarnings("static-access")
	public void initializeButton(JTextArea button ,String name , int sizeWidthOffset ,int sizeHeightOffset , int locationWidthOffset, int locationHeightOffset ,int heightMultiple){
		
		Dimension d = this.getSize();
		button.setSize(d.width/constants.TEXT_SIZE + sizeWidthOffset , d.height/constants.TEXT_SIZE + sizeHeightOffset);
		button.setLocation((d.width - button.getWidth())/2 + locationWidthOffset,(d.height- button.getHeight())/2 + (button.getHeight() * heightMultiple) + locationHeightOffset);
		getContentPane().add(button);
	}
	@Override
	public void update(Observable o, Object arg) {
		jTextArea.setText(null);
		Fighter f = (Fighter)o;
		jTextArea.insert(f.show(false),0);
	}

	/**
	 * @return the fighter
	 */
	public Fighter getFighter() {
		return fighter;
	}

	/**
	 * @param fighter the fighter to set
	 */
	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
	}
}
