package br.com.mysnake.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;

import br.com.mysnake.repo.MemoryGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class Game extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea txtFundo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
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
	public Game() {
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				int keyCode = e.getKeyCode();
				
				MemoryGame.getInstance().criarComando(keyCode);
				
			}
		});
		setBackground(Color.BLACK);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 815, 517);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		txtFundo = new JTextArea();
		txtFundo.setEnabled(false);
		txtFundo.setBorder(new LineBorder(Color.WHITE));
		txtFundo.setBackground(Color.BLACK);
		txtFundo.setWrapStyleWord(true);
		txtFundo.setLineWrap(true);
		txtFundo.setForeground(Color.WHITE);
		contentPane.add(txtFundo);
		
		setLocationRelativeTo(null);
		
	}
	
	public JTextArea getTxtFundo() {
		return txtFundo;
	}
	
}
