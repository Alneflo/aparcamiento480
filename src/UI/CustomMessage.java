package UI;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CustomMessage extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public CustomMessage(String text) {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 150);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel Mensaje = new JLabel(text);
		Mensaje.setBounds(0, 21, 474, 17);
		Mensaje.setHorizontalAlignment(SwingConstants.CENTER);
		Mensaje.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		panel.add(Mensaje);
		
		CustomButton acceptButton = new CustomButton("Aceptar");
		acceptButton.setBounds(191, 56, 89, 33);
		acceptButton.setColor(Color.LIGHT_GRAY);
		acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(acceptButton);
	}
}
