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
	
	int index; 
	
	JTextField flight;
	JTextField rbd;
	JTextField corporateCode;
	JTextField fareBasis;

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
        this.setPreferredSize(new Dimension(424, 96));
		
		JLabel lblFlight = new JLabel("Flight");		
		JLabel lblRbd = new JLabel("RBD");		
		JLabel lblCoporateCode = new JLabel("coporateCode");
		flight = new JTextField();
		flight.setColumns(6);		
		rbd = new JTextField();
		rbd.setColumns(1);		
		corporateCode = new JTextField();
		corporateCode.setColumns(8);
		
		btnDelete.addActionListener(this);
		
		fareBasis = new JTextField();
		fareBasis.setColumns(6);
		
		JLabel lblFarebasis = new JLabel("fareBasis");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(flight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFlight))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(rbd, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRbd))
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(corporateCode, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCoporateCode))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(fareBasis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
							.addComponent(btnDelete)
							.addGap(20))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblFarebasis)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFlight)
						.addComponent(lblRbd)
						.addComponent(lblCoporateCode)
						.addComponent(lblFarebasis))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(flight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rbd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDelete)
						.addComponent(corporateCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(fareBasis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(14, Short.MAX_VALUE))
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
