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
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class ElementPanel extends JPanel implements ActionListener{

	ItineraryPanel parent;
	
	GridBagConstraints constraints = new GridBagConstraints();

	JButton btnAddASegment = new JButton("Add a segment");
	JButton btnDelete = new JButton("Delete this element");
 	
	JPanel segmentspanel = new JPanel(new GridBagLayout());
	JScrollPane scrollPane = new JScrollPane(segmentspanel);

	
	List<SegmentPanel> segments = new ArrayList<SegmentPanel>();

	public int index;

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
					
					ElementPanel window = new ElementPanel(null,0);
					frame.add(window);
					frame.setLocationRelativeTo(null);


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
	public ElementPanel(ItineraryPanel parent0, int index0) {
		parent = parent0;
		index = index0;

		this.setBorder(BorderFactory.createTitledBorder("Element " + index));
		this.setToolTipText("sample text");

		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
//        this.setPreferredSize(new Dimension(420, 200));

		setLayout(new BorderLayout(0, 0));

		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.NORTH);		
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		buttonPanel.add(btnAddASegment);
		JLabel label = new JLabel("                           ");
		buttonPanel.add(label);
		buttonPanel.add(btnDelete);
		
		add(scrollPane, BorderLayout.CENTER);
		btnAddASegment.addActionListener(this);
		btnDelete.addActionListener(this);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		SegmentPanel seg = new SegmentPanel(this, 1);
		segments.add(seg);
		segmentspanel.add(seg, constraints);
	}

	private void addSegment(int index) {
		constraints.gridy++;
		SegmentPanel seg = new SegmentPanel(this, index);
		segments.add(seg);
		segmentspanel.add(seg, constraints);
		segmentspanel.revalidate();
	}
 

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (btnAddASegment == ae.getSource()) {
			addSegment(segments.size() + 1);
			JFrame root = (JFrame) this.getTopLevelAncestor();
			root.pack();
			root.setLocationRelativeTo(null);
		}
		
		else if(btnDelete == ae.getSource()){
			parent.elements.remove(this);
			parent.elementsPanel.remove(this);
			JFrame root = (JFrame) parent.getTopLevelAncestor();
			root.pack();
			root.setLocationRelativeTo(null);

		}
	}
}
