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
import service.GameServidor;

public class Join extends JFrame {

	private JPanel contentPane;
	private JTextField txtNick;
	private JTextField txtPorta;
	private JLabel lblNick;
	private JLabel lblPorta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Join frame = new Join();
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
	public Join() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(34, 27, 366, 200);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNick = new JLabel("NICK: ");
		lblNick.setFont(new Font("Tunga", Font.PLAIN, 20));
		lblNick.setBounds(91, 42, 51, 37);
		panel.add(lblNick);
		
		lblPorta = new JLabel("PORTA:");
		lblPorta.setFont(new Font("Tunga", Font.PLAIN, 20));
		lblPorta.setBounds(91, 81, 69, 37);
		panel.add(lblPorta);
		
		txtNick = new JTextField();
		txtNick.setBounds(152, 50, 86, 20);
		panel.add(txtNick);
		txtNick.setColumns(10);
		
		txtPorta = new JTextField();
		txtPorta.setBounds(152, 89, 86, 20);
		panel.add(txtPorta);
		txtPorta.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modoHost();
			}
		});
		btnEntrar.setBounds(91, 131, 69, 23);
		panel.add(btnEntrar);
		
		JButton btnNewButton = new JButton("Sair");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sair();
			}
		});
		btnNewButton.setBounds(170, 131, 68, 23);
		panel.add(btnNewButton);
	}
	
	public void modoHost(){
		String nick = txtNick.getText();
		String porta = txtPorta.getText();
		new GameServidor(Integer.parseInt(porta));
		//new Thread(new Fase1(nick, Integer.parseInt(porta))).start();
		dispose();
	}
	
	public void sair(){
		new Game().setVisible(true);
		dispose();
	}
	
}
