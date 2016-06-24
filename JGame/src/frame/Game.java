package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Game extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	public Game() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(33, 23, 371, 212);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnHost = new JButton("HOST");
		btnHost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modoHost();
			}
		});
		btnHost.setBounds(115, 29, 130, 45);
		panel.add(btnHost);
		
		JButton btnJoin = new JButton("JOIN");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modoJoin();
			}
		});
		btnJoin.setBounds(115, 85, 130, 45);
		panel.add(btnJoin);
		
		JButton btnSair = new JButton("SAIR");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		btnSair.setBounds(115, 141, 130, 45);
		panel.add(btnSair);
	}
	
	public void modoHost(){
		new Host().setVisible(true);
		dispose();
	}
	
	public void modoJoin(){
		new Join().setVisible(true);
		dispose();
	}
	
	public void sair(){
		System.exit(0);
	}
}
