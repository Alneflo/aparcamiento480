package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class CustomButton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean over;
	private Color color;
	private Color colorOver;
	private Color colorClick;
	private Color borderColor;
	private int radius = 20;
	
	public CustomButton() {
		init();
	}
	
	public CustomButton(String text) {
		setText(text);
		init();
	}
	
	public void init() {
		setColor(new Color(0,0,0,0));
		setColorOver(Color.LIGHT_GRAY);
		setColorClick(Color.WHITE);
		setBorderColor(new Color(0,0,0,0));
		setFocusable(false);
		setFocusPainted(false);
		setBorder(new EmptyBorder(0,0,0,0));
		setContentAreaFilled(false);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(colorOver);
				over = true;
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(color);
				over = false;
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				setBackground(colorClick);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(over) {
					setBackground(colorOver);
				}else {
					setBackground(color);
				}
			}
			
		});
	}
	
	public void setOver(boolean over) {
		this.over = over;
	}
	
	public void setColor(Color color) {
		this.color = color;
		setBackground(color);
	}
	
	public void setColorOver(Color colorOver) {
		this.colorOver = colorOver;
	}
	
	public void setColorClick(Color colorClick) {
		this.colorClick = colorClick;
	}
	
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public boolean getOver() {
		return over;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Color getColorOver() {
		return colorOver;
	}
	
	public Color getColorClick() {
		return colorClick;
	}
	
	public Color getBorderColor() {
		return borderColor;
	}
	
	public int getRadius() {
		return radius;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// Paint Border
		g2.setColor(borderColor);
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
		g2.setColor(getBackground());
		//Border set 2 Pixels
		g2.fillRoundRect(2, 2, getWidth()-4, getHeight()-4, radius, radius);
		super.paintComponent(g);
	}
	
}
