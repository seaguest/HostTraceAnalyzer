package com.amadeus.ssd.hosttraceanalyzer.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.LayoutStyle.ComponentPlacement;

import com.amadeus.ssd.hosttraceanalyzer.input.Setting;

public class SettingPage extends JPanel implements ActionListener{
	private JTextField inputPath;
	private JTextField outputPath;

	
	JButton btnInput = new JButton("Browse");
	JButton btnOutput = new JButton("Browse");
	JButton btnSave = new JButton("Save");

	Setting setting;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame;
					frame = new JFrame();
					frame.setTitle("Setting");
					frame.setBounds(100, 100, 450, 300);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.getContentPane().setLayout(new BorderLayout(0, 0));
					
					SettingPage window = new SettingPage(null);
					frame.add(window);
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SettingPage(Setting setting) {
		this.setting = setting;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JPanel panel = new JPanel();
		JPanel panel_1 = new JPanel();
		this.add(panel, BorderLayout.WEST);
		this.add(panel_1, BorderLayout.EAST);

		JLabel lblInputFolder = new JLabel("Input Folder");
		
		inputPath = new JTextField();
		inputPath.setColumns(10);
		
		JLabel lblOutputFolder = new JLabel("OutPut Folder");
		
		outputPath = new JTextField();
		outputPath.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOutputFolder)
						.addComponent(lblInputFolder)
						.addComponent(outputPath, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
						.addComponent(inputPath, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addComponent(lblInputFolder)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(inputPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(lblOutputFolder)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(outputPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(110, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		

		btnInput.addActionListener(this);
		btnOutput.addActionListener(this);
		btnSave.addActionListener(this);

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(12)
							.addComponent(btnSave))
						.addComponent(btnInput)
						.addComponent(btnOutput))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(48)
					.addComponent(btnInput)
					.addGap(59)
					.addComponent(btnOutput)
					.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
					.addComponent(btnSave)
					.addGap(27))
		);
		panel_1.setLayout(gl_panel_1);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (btnInput == ae.getSource()) {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("choosertitle");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				try {
					inputPath.setText(chooser.getSelectedFile().getCanonicalPath());
					setting.setInputPath(inputPath.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			} else {
				System.out.println("No Selection ");
			}
			
		}else if(btnOutput == ae.getSource()){

			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("choosertitle");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				try {
					outputPath.setText(chooser.getSelectedFile().getCanonicalPath());
					setting.setOutputPath(outputPath.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			} else {
				System.out.println("No Selection ");
			}
			
		}else if(btnSave == ae.getSource()){			
			System.out.println(setting);

			JFrame root = (JFrame) this.getTopLevelAncestor();
		    root.dispose();
		    
		}
		
	}
}
