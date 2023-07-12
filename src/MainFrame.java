import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private String licensePlate;
	private ParkingManager pK;
	private int result;
	private double price;
	private String fileName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setResizable(false);
		init();
		
		setBackground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 763, 467);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton recordEntryButton = new JButton("REGISTRAR ENTRADA");
		recordEntryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean entry = true;
				int vehicle = -1;
				platePopUp(entry, vehicle);
			}
		});
		recordEntryButton.setIcon(new ImageIcon("C:\\Users\\AlejandroNebotFlores\\eclipse-workspace\\PracticaJava1Swing\\imgs\\EntryIcon.png"));
		recordEntryButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		recordEntryButton.setBounds(10, 81, 312, 63);
		panel.add(recordEntryButton);
		
		JButton recordExitButton = new JButton("REGISTRAR SALIDA");
		recordExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean entry = false;
				int vehicle = -1;
				platePopUp(entry, vehicle);
			}
		});
		recordExitButton.setIcon(new ImageIcon("C:\\Users\\AlejandroNebotFlores\\eclipse-workspace\\PracticaJava1Swing\\imgs\\ExitIcon.png"));
		recordExitButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		recordExitButton.setBounds(415, 81, 312, 63);
		panel.add(recordExitButton);
		
		JButton registerOfficialButton = new JButton("REGISTRAR VEHÍCULO OFICIAL");
		registerOfficialButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean entry = false;
				int vehicle = 0;
				platePopUp(entry, vehicle);
			}
		});
		registerOfficialButton.setIcon(new ImageIcon("C:\\Users\\AlejandroNebotFlores\\eclipse-workspace\\PracticaJava1Swing\\imgs\\OfficialVehicleIcon.png"));
		registerOfficialButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		registerOfficialButton.setBounds(10, 190, 312, 63);
		panel.add(registerOfficialButton);
		
		JButton registerResidentialButton = new JButton("REGISTRAR VEHÍCULO DE RESIDENTE");
		registerResidentialButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean entry = false;
				int vehicle = 1;
				platePopUp(entry, vehicle);
			}
		});
		registerResidentialButton.setIcon(new ImageIcon("C:\\Users\\AlejandroNebotFlores\\eclipse-workspace\\PracticaJava1Swing\\imgs\\ResidentVehicleIcon.png"));
		registerResidentialButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		registerResidentialButton.setBounds(415, 190, 312, 63);
		panel.add(registerResidentialButton);
		
		JButton startMonthButton = new JButton("NUEVO MES");
		startMonthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pK.startMonth();
			}
		});
		startMonthButton.setIcon(new ImageIcon("C:\\Users\\AlejandroNebotFlores\\eclipse-workspace\\PracticaJava1Swing\\imgs\\MonthIcon.png"));
		startMonthButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		startMonthButton.setBounds(10, 292, 312, 63);
		panel.add(startMonthButton);
		
		JButton createReportButton = new JButton("CREAR INFORME");
		createReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!pK.getReportList().isEmpty()) {
					reportPopUp();
				}else {
					messagePopUp(pK.getError(44), true);
				}
			}
		});
		createReportButton.setIcon(new ImageIcon("C:\\Users\\AlejandroNebotFlores\\eclipse-workspace\\PracticaJava1Swing\\imgs\\DocumentIcon.png"));
		createReportButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		createReportButton.setBounds(415, 292, 312, 63);
		panel.add(createReportButton);
		
		JLabel mainMenuTitle = new JLabel("MENÚ APARCAMIENTO");
		mainMenuTitle.setHorizontalAlignment(SwingConstants.CENTER);
		mainMenuTitle.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		mainMenuTitle.setForeground(new Color(0, 0, 0));
		mainMenuTitle.setBounds(0, 0, 717, 72);
		panel.add(mainMenuTitle);
		
	}
	
	public void init() {
		licensePlate = "";
		fileName = "";
		price = 0.0;
		result = 50;
		pK = new ParkingManager();
	}
	
	public void showMessage() {
		switch(result) {
		case 1:
			messagePopUp("Vehículo de residente registrado correctamente", false);
			break;
		case 0:
			messagePopUp("Vehículo oficial registrado correctamente", false);
			break;
		case -1:
			messagePopUp("Vehículo registrado correctamente", false);
			break;
		default:
			if(!pK.getError(result).isEmpty()) {
				messagePopUp(pK.getError(result), true);
			}
		}
	}
	
	public void platePopUp(boolean entry, int vehicle) {
		JFrame popUpWindow;
		JPanel panelContent, panel;
		
		popUpWindow = new JFrame();
		popUpWindow.setType(Type.POPUP);
		popUpWindow.setVisible(true);
		popUpWindow.setBackground(SystemColor.activeCaption);
		
		popUpWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		popUpWindow.setBounds(100, 100, 600, 250);
		popUpWindow.setResizable(false);
		panelContent = new JPanel();
		
		popUpWindow.setContentPane(panelContent);
		panelContent.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel = new JPanel();
		panelContent.add(panel);
		panel.setLayout(null);
		panel.setBackground(SystemColor.activeCaption);
		
		JLabel plateTitle = new JLabel("INTRODUCE LA MATRÍCULA");
		plateTitle.setHorizontalAlignment(SwingConstants.CENTER);
		plateTitle.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		plateTitle.setForeground(new Color(0, 0, 0));
		plateTitle.setBounds(10, 5, 600, 80);
		panel.add(plateTitle);
		
		JTextField licenseInput = new JTextField();
		licenseInput.setBounds(150, 100, 400, 20);
		licenseInput.setColumns(7);
		panel.add(licenseInput);
		
		JLabel licenseLabel = new JLabel("MATRÍCULA: ");
		licenseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		licenseLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		licenseLabel.setForeground(new Color(0, 0, 0));
		licenseLabel.setBounds(50, 100, 100, 20);
		panel.add(licenseLabel);
		
		JLabel errorLabel = new JLabel("La matrícula no es válida (0000AAA)");
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		errorLabel.setForeground(new Color(255, 0, 0));
		errorLabel.setBounds(150, 130, 300, 20);
		panel.add(errorLabel);
		errorLabel.setVisible(false);
		
		JButton acceptButton = new JButton("ACEPTAR");
		acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorLabel.setVisible(false);
				licensePlate = licenseInput.getText();
				if(Utilities.validLicensePlate(licensePlate)) {
					popUpWindow.dispose();
					if(entry) {
						result = pK.recordEntry(licensePlate);
					}else if(vehicle == -1){
						result = pK.recordExit(licensePlate);
						price = pK.getPrice();
					}else {
						boolean official;
						switch(vehicle) {
						case 0:
							official = true;
							result = pK.registerVehicle(licensePlate, official);
							break;
						case 1:
							official = false;
							result = pK.registerVehicle(licensePlate, official);
							break;
						}
					}
					showMessage();
				}else {
					errorLabel.setVisible(true);
				}
			}
		});
		
		acceptButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		acceptButton.setBounds(250, 150, 120, 30);
		panel.add(acceptButton);
	}
	
	public void messagePopUp(String message, boolean error) {
		JFrame popUpWindow;
		JPanel panelContent, panel;
		int red = 0;
		
		popUpWindow = new JFrame();
		popUpWindow.setType(Type.POPUP);
		popUpWindow.setVisible(true);
		popUpWindow.setBackground(SystemColor.activeCaption);
		
		popUpWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		popUpWindow.setBounds(100, 100, 700, 180);
		popUpWindow.setResizable(false);
		panelContent = new JPanel();
		
		popUpWindow.setContentPane(panelContent);
		panelContent.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel = new JPanel();
		panelContent.add(panel);
		panel.setLayout(null);
		panel.setBackground(SystemColor.activeCaption);
		
		if(error) {
			red = 255;
		}
		
		JLabel messageTitle = new JLabel(message.toUpperCase());
		messageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		messageTitle.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		messageTitle.setForeground(new Color(red, 0, 0));
		messageTitle.setBounds(0, 0, 700, 80);
		panel.add(messageTitle);
		
		JButton acceptButton = new JButton("ACEPTAR");
		acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUpWindow.dispose();
				if(price > 0) {
					messagePopUp("Debe pagar " + price + "€", false);
					price = 0;
				}
			}
		});
		
		acceptButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		acceptButton.setBounds(330, 80, 120, 30);
		panel.add(acceptButton);
	}
	
	public void reportPopUp() {
		JFrame popUpWindow;
		JPanel panelContent, panel;
		
		popUpWindow = new JFrame();
		popUpWindow.setType(Type.POPUP);
		popUpWindow.setVisible(true);
		popUpWindow.setBackground(SystemColor.activeCaption);
		
		popUpWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		popUpWindow.setBounds(100, 100, 600, 250);
		popUpWindow.setResizable(false);
		panelContent = new JPanel();
		
		popUpWindow.setContentPane(panelContent);
		panelContent.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel = new JPanel();
		panelContent.add(panel);
		panel.setLayout(null);
		panel.setBackground(SystemColor.activeCaption);
		
		JLabel nameTitle = new JLabel("INTRODUCE EL NOMBRE DEL ARCHIVO");
		nameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		nameTitle.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		nameTitle.setForeground(new Color(255, 0, 0));
		nameTitle.setBounds(0, 0, 600, 80);
		panel.add(nameTitle);
		
		JTextField nameInput = new JTextField();
		nameInput.setBounds(150, 100, 400, 20);
		nameInput.setColumns(7);
		panel.add(nameInput);
		
		JLabel nameLabel = new JLabel("NOMBRE: ");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		nameLabel.setForeground(new Color(0, 0, 0));
		nameLabel.setBounds(50, 100, 100, 20);
		panel.add(nameLabel);
		
		JLabel errorLabel = new JLabel("El nombre debe tener al menos un carácter");
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		errorLabel.setForeground(new Color(255, 0, 0));
		errorLabel.setBounds(150, 130, 300, 20);
		panel.add(errorLabel);
		errorLabel.setVisible(false);
		
		JButton acceptButton = new JButton("ACEPTAR");
		acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorLabel.setVisible(false);
				if(!nameInput.getText().isBlank()) {
					fileName = nameInput.getText();
					result = pK.createReport(fileName);
					if(result == 1) {
						messagePopUp("Informe creado correctamente", false);
					}
					popUpWindow.dispose();
				}else {
					errorLabel.setVisible(true);
				}
			}
		});
		
		acceptButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		acceptButton.setBounds(250, 150, 120, 30);
		panel.add(acceptButton);
	}
}
