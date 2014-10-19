package com.amadeus.ssd.hosttraceanalyzer.ui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class ItineraryPanel extends JPanel implements ActionListener{
	GridBagConstraints constraints = new GridBagConstraints();

	JButton btnAddAnElement = new JButton("Add an element");
 	
	JPanel elementsPanel = new JPanel(new GridBagLayout());
	JScrollPane scrollPane = new JScrollPane(elementsPanel);

	
	List<ElementPanel> elements = new ArrayList<ElementPanel>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setBounds(100, 100, 450, 300);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
					ItineraryPanel window = new ItineraryPanel();
					frame.add(window);

					frame.pack();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					
					frame.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							System.exit(0);
						}
					});
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ItineraryPanel() {
		this.setBorder(BorderFactory.createTitledBorder("Itinerary "));
		this.setToolTipText("sample text");

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.NORTH);		
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		buttonPanel.add(btnAddAnElement);
		
		add(scrollPane, BorderLayout.CENTER);
		btnAddAnElement.addActionListener(this);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		ElementPanel element = new ElementPanel(this, 1);
		elements.add(element);
		elementsPanel.add(element, constraints);

	}


	private void addSegment(int index) {
		constraints.gridy++;
		ElementPanel element = new ElementPanel(this, index);
		elements.add(element);
		elementsPanel.add(element, constraints);
		elementsPanel.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (btnAddAnElement == ae.getSource()) {
			addSegment(elements.size() + 1);
			JFrame root = (JFrame) this.getTopLevelAncestor();
			root.pack();
			root.setLocationRelativeTo(null);
		}
	}
}
