import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

import javax.swing.ImageIcon;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
				licensePlate = JOptionPane.showInputDialog("Matrícula: ");
				result = pK.recordEntry(licensePlate);
				messagePop(false, true, false);
			}
		});
		recordEntryButton.setIcon(new ImageIcon("C:\\Users\\AlejandroNebotFlores\\git\\Practica1\\PracticaJava1Swing\\imgs\\EntryIcon.png"));
		recordEntryButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		recordEntryButton.setBounds(10, 81, 312, 63);
		panel.add(recordEntryButton);
		
		JButton recordExitButton = new JButton("REGISTRAR SALIDA");
		recordExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message;
				licensePlate = JOptionPane.showInputDialog("Matrícula: ");
				result = pK.recordExit(licensePlate);
				messagePop(true,false, false);
				if(result == -1) {
					if(price == 0) {
						message = "No ha acumulado suficiente tiempo estacionado, asi que no se le cobrará";
					}else {
						message = "Debe pagar " + price + "€";
					}
					JOptionPane.showMessageDialog(null, message, "Registrado", JOptionPane.INFORMATION_MESSAGE);
					pK.removeNonResident(licensePlate);
				}
				
				JOptionPane.showOptionDialog(recordExitButton, "A", "B", ALLBITS, ABORT, null, getComponentListeners(), e);
			}
		});
		recordExitButton.setIcon(new ImageIcon("C:\\Users\\AlejandroNebotFlores\\git\\Practica1\\PracticaJava1Swing\\imgs\\ExitIcon.png"));
		recordExitButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		recordExitButton.setBounds(415, 81, 312, 63);
		panel.add(recordExitButton);
		
		JButton registerOfficialButton = new JButton("REGISTRAR VEHÍCULO OFICIAL");
		registerOfficialButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean official = true;
				licensePlate = JOptionPane.showInputDialog("Matrícula: ");
				result = pK.registerVehicle(licensePlate, official);
				messagePop(false, false, true);
			}
		});
		registerOfficialButton.setIcon(new ImageIcon("C:\\Users\\AlejandroNebotFlores\\git\\Practica1\\PracticaJava1Swing\\imgs\\OfficialVehicleIcon.png"));
		registerOfficialButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		registerOfficialButton.setBounds(10, 190, 312, 63);
		panel.add(registerOfficialButton);
		
		JButton registerResidentialButton = new JButton("REGISTRAR VEHÍCULO DE RESIDENTE");
		registerResidentialButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean official = false;
				licensePlate = JOptionPane.showInputDialog("Matrícula: ");
				result = pK.registerVehicle(licensePlate, official);
				messagePop(false, false, true);
			}
		});
		registerResidentialButton.setIcon(new ImageIcon("C:\\Users\\AlejandroNebotFlores\\git\\Practica1\\PracticaJava1Swing\\imgs\\ResidentVehicleIcon.png"));
		registerResidentialButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		registerResidentialButton.setBounds(415, 190, 312, 63);
		panel.add(registerResidentialButton);
		
		JButton startMonthButton = new JButton("NUEVO MES");
		startMonthButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pK.startMonth();
			}
		});
		startMonthButton.setIcon(new ImageIcon("C:\\Users\\AlejandroNebotFlores\\git\\Practica1\\PracticaJava1Swing\\imgs\\MonthIcon.png"));
		startMonthButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		startMonthButton.setBounds(10, 292, 312, 63);
		panel.add(startMonthButton);
		
		JButton createReportButton = new JButton("CREAR INFORME");
		createReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!pK.getReportList().isEmpty()) {
					do {
						fileName = JOptionPane.showInputDialog("Nombre del archivo: ");
						if(fileName.replaceAll(" ", "").isEmpty()) {
							JOptionPane.showMessageDialog(null, "Introduzca un nombre con al menos un carácter", "Error", ERROR);
						}
					} while (fileName.isEmpty());
					pK.createReport(fileName);
				}else {
					result = 44;
					messagePop(false, false, false);
				}
			}
		});
		createReportButton.setIcon(new ImageIcon("C:\\Users\\AlejandroNebotFlores\\git\\Practica1\\PracticaJava1Swing\\imgs\\DocumentIcon.png"));
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
	
	public String setMessage(boolean exit, boolean entry, boolean register) {
		String text;
		
		switch(pK.residentOrNonOrOfficial(licensePlate)){
		case 1:
			if(exit) {
				text = "Salida de vehiculo de residente registrada";
			}else if(entry){
				text = "Entrada de vehiculo de residente registrada";
			}else {
				text = "Vehiculo de residente registrado";
			}
			break;
		case 0:
			if(exit) {
				text = "Salida de vehiculo oficial registrada";
			}else if(entry){
				text = "Entrada de vehiculo oficial registrada";
			}else {
				text = "Vehiculo oficial registrado";
			}
			break;
		default:
			if(exit) {
				price = pK.getPrice();
				text = "Salida de vehiculo no residente registrado";
			}else {
				text = "Entrada de vehiculo no residente registrada";
			}
		}
		return text;
	}
	
	public void messagePop(boolean exit, boolean entry, boolean register) {
		String message, title;
		int imgOb;
		if(result<2) {
			message = setMessage(exit, entry, register);
			imgOb = JOptionPane.INFORMATION_MESSAGE;
			title = "Registrado";
		}else {
			message = pK.getError(result);
			imgOb = JOptionPane.ERROR_MESSAGE;
			title = "Error";
		}
		JOptionPane.showMessageDialog(null, message, title, imgOb);
	}
}
