package frame;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fases.Fase1;

public class Host extends JFrame {

	private JPanel contentPane;
	private JTextField txtIP;
	private JTextField txtNick;
	private JTextField txtPorta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Host frame = new Host();
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
	public Host() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(43, 39, 342, 188);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNick = new JLabel("NICK:");
		lblNick.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNick.setBounds(86, 29, 73, 25);
		panel.add(lblNick);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIp.setBounds(86, 65, 73, 25);
		panel.add(lblIp);
		
		JLabel lblPorta = new JLabel("PORTA:");
		lblPorta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPorta.setBounds(86, 101, 73, 25);
		panel.add(lblPorta);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modoJoin();
			}
		});
		btnEntrar.setBounds(86, 141, 73, 23);
		panel.add(btnEntrar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sair();
			}
		});
		btnSair.setBounds(170, 141, 73, 23);
		panel.add(btnSair);
		
		txtIP = new JTextField();
		txtIP.setBounds(157, 71, 86, 20);
		panel.add(txtIP);
		txtIP.setColumns(10);
		
		txtNick = new JTextField();
		txtNick.setBounds(157, 35, 86, 20);
		panel.add(txtNick);
		txtNick.setColumns(10);
		
		txtPorta = new JTextField();
		txtPorta.setBounds(157, 107, 86, 20);
		panel.add(txtPorta);
		txtPorta.setColumns(10);
	}
	
	public void modoJoin(){
		String nick = txtNick.getText();
		String ip = txtIP.getText();
		String porta = txtPorta.getText();
		//new Thread(new Fase1(nick, ip, Integer.parseInt(porta))).start();
		dispose();
	}
	
	public void sair(){
		new Game().setVisible(true);
		dispose();
	}

}
