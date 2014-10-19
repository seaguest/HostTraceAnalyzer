package com.amadeus.ssd.hosttraceanalyzer.ui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class SegmentPanel extends JPanel implements ActionListener{
	JButton btnDelete = new JButton("delete");

	ElementPanel parent;
	
	public int index; 
	
	JTextField flight;
	JTextField rbd;
	JTextField corporateCode;

	/**
	 * Create the application.
	 */
	public SegmentPanel(ElementPanel parent0,int index0 ) {
		parent = parent0;
		index = index0;
		this.setBorder(BorderFactory.createTitledBorder("Segment " + index));
		this.setToolTipText("sample text");

		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {				
        this.setPreferredSize(new Dimension(405, 96));
		
		JLabel lblFlight = new JLabel("Flight");		
		JLabel lblRbd = new JLabel("RBD");		
		JLabel lblCoporateCode = new JLabel("Coporate Code");
		flight = new JTextField();
		flight.setColumns(6);		
		rbd = new JTextField();
		rbd.setColumns(1);		
		corporateCode = new JTextField();
		corporateCode.setColumns(8);
		
		btnDelete.addActionListener(this);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(flight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFlight))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(rbd, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRbd))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(corporateCode, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnDelete))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblCoporateCode)))
					.addGap(110))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRbd)
						.addComponent(lblCoporateCode)
						.addComponent(lblFlight))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(flight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rbd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(corporateCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDelete))
					.addContainerGap(98, Short.MAX_VALUE))
		);
		this.setLayout(groupLayout);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (btnDelete == ae.getSource()) {
			parent.segments.remove(this);
			parent.segmentspanel.remove(this);
			JFrame root = (JFrame) parent.getTopLevelAncestor();
			root.pack();
			root.setLocationRelativeTo(null);
		}
	}

}
