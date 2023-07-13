
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import UI.CustomButton;
import UI.CustomMessage;
import Utils.Action;
import Utils.ParkingManager;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AppFrame extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel mainPanel;
	private JPanel inputPanel;
	private JLabel inputTip;
	private CustomMessage cM;
	private String licensePlate;
	private String fileName;
	private double price;
	private int result;
	private ParkingManager pK;
	private Action action;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppFrame frame = new AppFrame();
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
	public AppFrame() {
		init();
		setForeground(new Color(0, 0, 0));
		setTitle("Aparcamiento480");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\AlejandroNebotFlores\\git\\Practica1\\PracticaJava1Swing\\imgs\\480Icon.jpeg"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 578);
		setBackground(Color.WHITE);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 651, 110);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel mainMenuTitle = new JLabel("MENÚ APARCAMIENTO");
		mainMenuTitle.setBackground(new Color(117, 117, 117));
		mainMenuTitle.setIcon(new ImageIcon("C:\\Users\\AlejandroNebotFlores\\git\\Practica1\\PracticaJava1Swing\\imgs\\480Icon.png"));
		mainMenuTitle.setHorizontalAlignment(SwingConstants.CENTER);
		mainMenuTitle.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		mainMenuTitle.setForeground(new Color(0, 0, 0));
		mainMenuTitle.setBounds(0, 0, 651, 110);
		mainMenuTitle.setOpaque(true);
		panel.add(mainMenuTitle);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 86, 651, 453);
		contentPane.add(tabbedPane);
		
		mainPanelSetup();
		
		inputPanelSetup();
	}
	

	
	public void init() {
		licensePlate = "";
		fileName = "";
		price = 0.0;
		result = 50;
		pK = new ParkingManager();
	}
	
	public void mainPanelSetup() {
		mainPanel = new JPanel();
		tabbedPane.addTab("New tab", null, mainPanel, null);
		mainPanel.setLayout(null);
		
		CustomButton recordEntryButton = new CustomButton("REGISTRAR ENTRADA");
		recordEntryButton.setText("");
		recordEntryButton.setToolTipText("");
		recordEntryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action = Action.ENTRY;
				inputTip.setText("Matrícula:");
				tabbedPane.setSelectedIndex(1);
			}
		});
		recordEntryButton.setIcon(new ImageIcon("C:\\Users\\AlejandroNebotFlores\\git\\Practica1\\PracticaJava1Swing\\imgs\\EntryIcon.png"));
		recordEntryButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		recordEntryButton.setBounds(10, 11, 180, 180);
		mainPanel.add(recordEntryButton);
		
		CustomButton recordExitButton = new CustomButton("REGISTRAR SALIDA");
		recordExitButton.setText("");
		recordExitButton.setToolTipText("");
		recordExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action = Action.EXIT;
				inputTip.setText("Matrícula:");
				tabbedPane.setSelectedIndex(1);
			}
		});
		recordExitButton.setIcon(new ImageIcon("C:\\Users\\AlejandroNebotFlores\\git\\Practica1\\PracticaJava1Swing\\imgs\\ExitIcon.png"));
		recordExitButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		recordExitButton.setBounds(229, 11, 180, 180);
		mainPanel.add(recordExitButton);
		
		CustomButton registerOfficialButton = new CustomButton("REGISTRAR VEHÍCULO OFICIAL");
		registerOfficialButton.setText("");
		registerOfficialButton.setToolTipText("");
		registerOfficialButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action = Action.REGISTER_OFFICIAL;
				inputTip.setText("Matrícula:");
				tabbedPane.setSelectedIndex(1);
			}
		});
		registerOfficialButton.setIcon(new ImageIcon("C:\\Users\\AlejandroNebotFlores\\git\\Practica1\\PracticaJava1Swing\\imgs\\OfficialVehicleIcon.png"));
		registerOfficialButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		registerOfficialButton.setBounds(229, 217, 180, 180);
		mainPanel.add(registerOfficialButton);
		
		CustomButton registerResidentialButton = new CustomButton("REGISTRAR VEHÍCULO DE RESIDENTE");
		registerResidentialButton.setText("");
		registerResidentialButton.setToolTipText("");
		registerResidentialButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action = Action.REGISTER_RESIDENT;
				inputTip.setText("Matrícula:");
				tabbedPane.setSelectedIndex(1);
			}
		});
		registerResidentialButton.setIcon(new ImageIcon("C:\\Users\\AlejandroNebotFlores\\git\\Practica1\\PracticaJava1Swing\\imgs\\ResidentVehicleIcon.png"));
		registerResidentialButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		registerResidentialButton.setBounds(10, 217, 180, 180);
		mainPanel.add(registerResidentialButton);
		
		CustomButton startMonthButton = new CustomButton("NUEVO MES");
		startMonthButton.setText("");
		startMonthButton.setToolTipText("");
		startMonthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pK.startMonth();
			}
		});
		startMonthButton.setIcon(new ImageIcon("C:\\Users\\AlejandroNebotFlores\\git\\Practica1\\PracticaJava1Swing\\imgs\\MonthIcon.png"));
		startMonthButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		startMonthButton.setBounds(453, 217, 180, 180);
		mainPanel.add(startMonthButton);
		
		CustomButton createReportButton = new CustomButton("CREAR INFORME");
		createReportButton.setText("");
		createReportButton.setToolTipText("");
		createReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action = Action.CREATE_REPORT;
				inputTip.setText("Nombre del archivo:");
				if(!pK.getReportList().isEmpty()) {
					tabbedPane.setSelectedIndex(1);
				}else {
					result = 44;
					showPopUp(pK.getError(result));
				}
			}
		});
		createReportButton.setIcon(new ImageIcon("C:\\Users\\AlejandroNebotFlores\\git\\Practica1\\PracticaJava1Swing\\imgs\\DocumentIcon.png"));
		createReportButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		createReportButton.setBounds(453, 11, 180, 180);
		mainPanel.add(createReportButton);
		
		JLabel entryLabel = new JLabel("Introducir entrada de vehículo");
		entryLabel.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		entryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		entryLabel.setBounds(10, 190, 180, 14);
		mainPanel.add(entryLabel);
		
		JLabel exitLabel = new JLabel("Introducir salida de vehículo");
		exitLabel.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		exitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		exitLabel.setBounds(229, 190, 180, 14);
		mainPanel.add(exitLabel);
		
		JLabel reportLabel = new JLabel("Registrar vehículos residentes");
		reportLabel.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		reportLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reportLabel.setBounds(453, 190, 180, 14);
		mainPanel.add(reportLabel);
		
		JLabel residentLabel = new JLabel("Almacenar vehículo residente");
		residentLabel.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		residentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		residentLabel.setBounds(10, 395, 180, 14);
		mainPanel.add(residentLabel);
		
		JLabel officialLabel = new JLabel("Almacenar vehículo oficial");
		officialLabel.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		officialLabel.setHorizontalAlignment(SwingConstants.CENTER);
		officialLabel.setBounds(229, 395, 180, 14);
		mainPanel.add(officialLabel);
		
		JLabel monthLabel = new JLabel("Empezar mes");
		monthLabel.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monthLabel.setBounds(453, 395, 180, 14);
		mainPanel.add(monthLabel);
		
	}
	
	public void inputPanelSetup() {
		inputPanel = new JPanel();
		tabbedPane.addTab("New tab", null, inputPanel, null);
		inputPanel.setLayout(null);
	
		JTextField inputText = new JTextField();
		inputText.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		inputText.setBounds(232, 174, 310, 34);
		inputText.setColumns(10);
		inputPanel.add(inputText);
		
		inputTip = new JLabel("Matrícula:");
		inputTip.setHorizontalAlignment(SwingConstants.RIGHT);
		inputTip.setFont(new Font("Century Gothic", Font.BOLD, 16));
		inputTip.setBounds(20, 179, 202, 24);
		inputPanel.add(inputTip);
		
		JLabel noTextError = new JLabel("Introduzca algo de texto");
		noTextError.setFont(new Font("Century Gothic", Font.PLAIN, 17));
		noTextError.setForeground(Color.RED);
		noTextError.setHorizontalAlignment(SwingConstants.CENTER);
		noTextError.setBounds(10, 136, 629, 29);
		inputPanel.add(noTextError);
		noTextError.setVisible(false);
		
		CustomButton acceptButton = new CustomButton("Aceptar");
		acceptButton.setText("ACEPTAR");
		acceptButton.setFont(new Font("Century Gothic", Font.BOLD, 18));
		acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(action != Action.CREATE_REPORT) {
					licensePlate = inputText.getText();
				}else {
					fileName = inputText.getText();
				}
				
				if(!inputText.getText().isEmpty()) {
					setResult();
					tabbedPane.setSelectedIndex(0);
					inputText.setText("");
					noTextError.setVisible(false);
				}else {
					noTextError.setVisible(true);
				}
			}
		});
		acceptButton.setColor(Color.LIGHT_GRAY);
		acceptButton.setBounds(126, 262, 167, 58);
		inputPanel.add(acceptButton);
		
		JLabel inputTitle = new JLabel("INTRODUZCA LOS DATOS REQUERIDOS");
		inputTitle.setHorizontalAlignment(SwingConstants.CENTER);
		inputTitle.setFont(new Font("Century Gothic", Font.BOLD, 25));
		inputTitle.setBounds(0, 38, 639, 40);
		inputPanel.add(inputTitle);
		
		CustomButton backButton = new CustomButton("Aceptar");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		backButton.setText("VOLVER");
		backButton.setFont(new Font("Century Gothic", Font.BOLD, 18));
		backButton.setColor(Color.LIGHT_GRAY);
		backButton.setBounds(345, 262, 167, 58);
		inputPanel.add(backButton);
		
		
	}
	
	public String setMessage() {
		String text = "", vehicle;
		
		switch(pK.residentOrNonOrOfficial(licensePlate)){
		case 1:
			vehicle = "de residente";
			break;
		case 0:
			vehicle = "oficial";
			break;
		default:
			vehicle = "de no residente";
		}
		
		switch(action) {
		case ENTRY:
			text = "Entrada de vehiculo " + vehicle + " registrada";
			break;
		case EXIT:
			text = "Salida de vehiculo " + vehicle + " registrada";
			break;
		case REGISTER_OFFICIAL: case REGISTER_RESIDENT:
			text = "Vehiculo " + vehicle + " registrado";
			break;
		case CREATE_REPORT:
			text = "Archivo generado exitosamente";
			break;
		}
		
		return text;
	}
	
	public void setResult() {
		boolean official;
		String message;
		switch(action) {
		case ENTRY:
			result = pK.recordEntry(licensePlate);
			break;
		case EXIT:
			result = pK.recordExit(licensePlate);
			break;
		case REGISTER_OFFICIAL:
			official = true;
			result = pK.registerVehicle(licensePlate, official);
			break;
		case REGISTER_RESIDENT:
			official = false;
			result = pK.registerVehicle(licensePlate, official);
			break;
		case CREATE_REPORT:
			result = pK.createReport(fileName);
			break;
		}
		
		if(result<2){
			message = setMessage();
		}else {
			message = pK.getError(result);
		}
		
		showPopUp(message);
		
		if(result == -1 && action == Action.EXIT) {
			price = pK.getPrice();
			if(price == 0) {
				message = "No ha acumulado suficiente tiempo estacionado, asi que no se le cobrará";
			}else {
				message = "Debe pagar " + price + "€";
			}
			
			showPopUp(message);
			
			pK.removeNonResident(licensePlate);
		}
	}
	
	public void showPopUp(String message) {
		cM = new CustomMessage(message);
		cM.setVisible(true);
	}
}
