package com.amadeus.ssd.hosttraceanalyzer.ui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.amadeus.ssd.hosttraceanalyzer.input.SearchCriteria;
import com.amadeus.ssd.hosttraceanalyzer.input.Setting;

public class HostTraceAnalyzerMain extends JFrame implements ActionListener{

	
	JButton btnSearchCriteria = new JButton("Search Criteria");
	JButton btnGo = new JButton("Go");
	
	
	JMenuItem mntmSetting = new JMenuItem("    Setting    ");
	JMenuItem mntmAbout = new JMenuItem("    About    ");
	
	JTextArea outputLog = new JTextArea();


	Setting setting = new Setting();
	SearchCriteria searchCriteria = new SearchCriteria();

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HostTraceAnalyzerMain window = new HostTraceAnalyzerMain();
					window.pack();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HostTraceAnalyzerMain() {
		setTitle("HostTraceAnalyzer V1.0");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setPreferredSize(new Dimension(420, 400));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		this.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel menuPanel = new JPanel();
		panel.add(menuPanel, BorderLayout.NORTH);
		menuPanel.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		menuPanel.add(menuBar, BorderLayout.NORTH);
		menuBar.add(mntmSetting);		
		menuBar.add(mntmAbout);
		
		JPanel bodyPanel = new JPanel();
		panel.add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel middlePanel = new JPanel();
		bodyPanel.add(middlePanel, BorderLayout.CENTER);
		middlePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel mainPanel = new JPanel();
		middlePanel.add(mainPanel, BorderLayout.NORTH);
		
		
		JPanel checkBoxPanel = new JPanel();		
		JCheckBox chckbxOneShot = new JCheckBox("One Shot");
		
		JCheckBox chckbxOneWayCombinable = new JCheckBox("One Way Combinable");
		GroupLayout gl_checkBoxPanel = new GroupLayout(checkBoxPanel);
		gl_checkBoxPanel.setHorizontalGroup(
			gl_checkBoxPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_checkBoxPanel.createSequentialGroup()
					.addGap(14)
					.addComponent(chckbxOneShot)
					.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
					.addComponent(chckbxOneWayCombinable)
					.addGap(100))
		);
		gl_checkBoxPanel.setVerticalGroup(
			gl_checkBoxPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_checkBoxPanel.createSequentialGroup()
					.addGroup(gl_checkBoxPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxOneShot)
						.addComponent(chckbxOneWayCombinable))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		checkBoxPanel.setLayout(gl_checkBoxPanel);
		GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
		gl_mainPanel.setHorizontalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_mainPanel.createSequentialGroup()
					.addGap(19)
					.addComponent(btnSearchCriteria)
					.addPreferredGap(ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
					.addComponent(btnGo)
					.addGap(62))
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addComponent(checkBoxPanel, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_mainPanel.setVerticalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(checkBoxPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGo)
						.addComponent(btnSearchCriteria))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		mainPanel.setLayout(gl_mainPanel);
		
		JPanel outputPanel = new JPanel();
		middlePanel.add(outputPanel, BorderLayout.CENTER);
		outputPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		outputPanel.add(scrollPane, BorderLayout.CENTER);
		
		outputLog.setText("");
		outputLog.setLineWrap(true);
		scrollPane.setViewportView(outputLog);
		
		mntmSetting.addActionListener(this);
		mntmAbout.addActionListener(this);
		btnSearchCriteria.addActionListener(this);
		btnGo.addActionListener(this);		
		
		this.pack();
		this.setLocationRelativeTo(null);
	}
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(mntmSetting == ae.getSource()){
			JFrame frame = new JFrame("Setting");
			frame.getContentPane().add(new SettingPage(setting));
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}else if(mntmAbout == ae.getSource()){
			JFrame frame = new JFrame("About");
			frame.getContentPane().add(new AboutPage());
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}else if(btnSearchCriteria == ae.getSource()){
			JFrame frame = new JFrame("Search Criteria");
			frame.getContentPane().add(new SearchCriteriaPanel(searchCriteria));
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}else if(btnGo == ae.getSource()){
			outputLog.setText(setting.toString());
			outputLog.append("\n");
			outputLog.append(searchCriteria.toString());
			
		}
		
	}
}
