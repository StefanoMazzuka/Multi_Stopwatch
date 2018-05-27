package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Process;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	static Thread chronometerOne;
	static Thread chronometerTwo;
	static int state;
	static boolean stop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					state = 0;
					stop = false;
					View frame = new View();
					frame.setVisible(true);
					state = 0;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		setTitle("Claud's Stopwatch");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{190, 31, 190, 0};
		gbl_contentPane.rowHeights = new int[]{95, 104, 69, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lblWorkTime = new JLabel("Work Time");
		lblWorkTime.setBackground(Color.WHITE);
		lblWorkTime.setFont(new Font("XXII-ARMY", Font.PLAIN, 30));
		GridBagConstraints gbc_lblWorkTime = new GridBagConstraints();
		gbc_lblWorkTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblWorkTime.gridx = 0;
		gbc_lblWorkTime.gridy = 0;
		contentPane.add(lblWorkTime, gbc_lblWorkTime);

		JLabel lblFunTime = new JLabel("Fun Time");
		lblFunTime.setFont(new Font("XXII-ARMY", Font.PLAIN, 30));
		GridBagConstraints gbc_lblFunTime = new GridBagConstraints();
		gbc_lblFunTime.insets = new Insets(0, 0, 5, 0);
		gbc_lblFunTime.gridx = 2;
		gbc_lblFunTime.gridy = 0;
		contentPane.add(lblFunTime, gbc_lblFunTime);

		JLabel chronometerOneLabel = new JLabel("00:00:00");
		chronometerOneLabel.setFont(new Font("Consolas", Font.PLAIN, 30));
		GridBagConstraints gbc_chronometerOneLabel = new GridBagConstraints();
		gbc_chronometerOneLabel.insets = new Insets(0, 0, 5, 5);
		gbc_chronometerOneLabel.gridx = 0;
		gbc_chronometerOneLabel.gridy = 1;
		contentPane.add(chronometerOneLabel, gbc_chronometerOneLabel);
		chronometerOneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		chronometerOneLabel.setBackground(Color.CYAN);

		JButton runButton = new JButton("");
		runButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("run.png")));
		runButton.setOpaque(false);
		runButton.setContentAreaFilled(false);
		runButton.setFocusPainted(false);
		GridBagConstraints gbc_runButton = new GridBagConstraints();
		gbc_runButton.insets = new Insets(0, 0, 5, 5);
		gbc_runButton.gridx = 1;
		gbc_runButton.gridy = 1;
		contentPane.add(runButton, gbc_runButton);

		JLabel chronometerTwoLabel = new JLabel("00:00:00");
		chronometerTwoLabel.setFont(new Font("Consolas", Font.PLAIN, 30));
		GridBagConstraints gbc_chronometerTwoLabel = new GridBagConstraints();
		gbc_chronometerTwoLabel.insets = new Insets(0, 0, 5, 0);
		gbc_chronometerTwoLabel.gridx = 2;
		gbc_chronometerTwoLabel.gridy = 1;
		contentPane.add(chronometerTwoLabel, gbc_chronometerTwoLabel);
		chronometerTwoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		chronometerTwoLabel.setBackground(Color.CYAN);

		JButton stopButton = new JButton("");
		stopButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("stop.png")));
		stopButton.setOpaque(false);
		stopButton.setContentAreaFilled(false);
		stopButton.setFocusPainted(false);
		GridBagConstraints gbc_stopButton = new GridBagConstraints();
		gbc_stopButton.insets = new Insets(0, 0, 0, 5);
		gbc_stopButton.gridx = 0;
		gbc_stopButton.gridy = 2;
		contentPane.add(stopButton, gbc_stopButton);

		JButton resetButton = new JButton("");
		resetButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("reset.png")));
		resetButton.setOpaque(false);
		resetButton.setContentAreaFilled(false);
		resetButton.setFocusPainted(false);
		GridBagConstraints gbc_resetButton = new GridBagConstraints();
		gbc_resetButton.gridx = 2;
		gbc_resetButton.gridy = 2;
		contentPane.add(resetButton, gbc_resetButton);

		runButton.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub			
				switch (state) {
				case 0:
					chronometerOne = new Process(chronometerOneLabel);
					chronometerTwo = new Process(chronometerTwoLabel);	
					chronometerOne.start();		
					chronometerTwo.start();
					chronometerTwo.suspend();
					stop = false;
					state = 1;
					break;
				case 1:
					chronometerOne.suspend();
					chronometerTwo.resume();
					stop = false;
					state = 2;
					break;
				case 2:
					chronometerTwo.suspend();
					chronometerOne.resume();
					stop = false;
					state = 1;
				default:
					break;
				}
			}
		});
		stopButton.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!stop) {
					chronometerOne.suspend();
					chronometerTwo.suspend();
					stop = true;
					
					switch (state) {
					case 0:
						break;
					case 1:	
						state = 2;
						break;
					case 2:
						state = 1;			
					default:
						break;
					}
				}
			}
		});
		resetButton.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				chronometerOne.suspend();
				chronometerTwo.suspend();
				((Process) chronometerOne).setStop(true);	
				((Process) chronometerTwo).setStop(true);
				chronometerOneLabel.setText("00:00:00");
				chronometerTwoLabel.setText("00:00:00");
				state = 0;
			}
		});
	}
}