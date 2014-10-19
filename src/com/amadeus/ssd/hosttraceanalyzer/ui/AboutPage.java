package com.amadeus.ssd.hosttraceanalyzer.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;

public class AboutPage extends JPanel {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frmHelp = new JFrame();
					frmHelp.setTitle("Help");
					frmHelp.setBounds(100, 100, 450, 464);
					frmHelp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					AboutPage window = new AboutPage();
					frmHelp.getContentPane().add(window);

					frmHelp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AboutPage() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
        this.setPreferredSize(new Dimension(400, 400));

		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);

		JTextArea textArea = new JTextArea(
				"\nHostTraceAnalyzer V1.0 \n\n\n"
						+ "This Tool is aiming at facilitating PTR/IR investigation, finding more easily the recommendation chosen by the user.\n\n"
						+ "With all flight number, airline code, fare type, we can find the original HT which returns the recommendation, instead of find one by one among many host traces, this can improve the productivity.\n\n"
						+ "Copyright © 2014-2024 Amadeus R&D-SSP-MTS-SSD, All Rights Reserved.\n\n");
		textArea.setBackground(SystemColor.inactiveCaption);
		textArea.setWrapStyleWord(true);
		textArea.setTabSize(80);
		textArea.setLineWrap(true);

		panel.setLayout(new BorderLayout()); // give your JPanel a BorderLayout

		JScrollPane scroll = new JScrollPane(textArea); // place the JTextArea
														// in a scroll pane
		panel.add(scroll, BorderLayout.CENTER); // add the JScrollPane to the
												// panel

	}
}
