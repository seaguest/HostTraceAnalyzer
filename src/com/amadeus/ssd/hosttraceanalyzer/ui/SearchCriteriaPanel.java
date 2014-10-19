package com.amadeus.ssd.hosttraceanalyzer.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.amadeus.ssd.hosttraceanalyzer.input.Element;
import com.amadeus.ssd.hosttraceanalyzer.input.FilteringCondition;
import com.amadeus.ssd.hosttraceanalyzer.input.SearchCriteria;
import com.amadeus.ssd.hosttraceanalyzer.input.Segment;

public class SearchCriteriaPanel extends JPanel implements ActionListener{

	JButton btnSave = new JButton("Save");
	
	ItineraryPanel itineraryPanel = new ItineraryPanel();

	JTextArea filteringCondition = new JTextArea();

	SearchCriteria searchCriteria;
	
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
					frame.getContentPane().setLayout(new BorderLayout(0, 0));
					frame.setLocationRelativeTo(null);		

					
					SearchCriteriaPanel window = new SearchCriteriaPanel(null);
					frame.getContentPane().add(window);
					frame.pack();
					
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
	public SearchCriteriaPanel(SearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		JPanel panel = new JPanel();
		this.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(301, Short.MAX_VALUE)
					.addComponent(btnSave)
					.addGap(40))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(btnSave)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		btnSave.addActionListener(this);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Itinerary Criteria", null, panel_2, null);
		
		panel_2.add(itineraryPanel);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Filtering Condition", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane);
		filteringCondition.setLineWrap(true);
		
		filteringCondition.setText("");
		scrollPane.setViewportView(filteringCondition);
		
	}

	public boolean isInputValid() {
		for (int i = 0; i < itineraryPanel.elements.size(); i++) {
			ElementPanel elementPanel = itineraryPanel.elements.get(i);
			for (int j = 0; j < elementPanel.segments.size(); j++) {
				SegmentPanel segmentPanel = elementPanel.segments.get(j);

				if (segmentPanel.flight.getText() == null
						|| segmentPanel.flight.getText().trim().equals("")
						|| segmentPanel.rbd.getText() == null
						|| segmentPanel.rbd.getText().trim().equals("")
						|| segmentPanel.rbd.getText().trim().length() != 1) {
					return false;
				}
				
				String flight = segmentPanel.flight.getText().trim();		
				try{
					Integer.parseInt(flight.substring(2));					
				}catch(Exception e){
					return false;
				}				
				if(! Character.isLetter(segmentPanel.rbd.getText().trim().charAt(0))){
					return false;
				}
			}
		}
		return true;
	}
	
	
	public void fillSearchCriteriaAndFilteringCondition(){		
		for(int i = 0; i<itineraryPanel.elements.size(); i++){
			ElementPanel elementPanel = itineraryPanel.elements.get(i);
			Element element = new Element(i);
			for(int j = 0; j<elementPanel.segments.size(); j++){
				SegmentPanel segmentPanel = elementPanel.segments.get(j);
				Segment segment = new Segment(j, segmentPanel.flight.getText().trim(), segmentPanel.rbd.getText().trim(), segmentPanel.corporateCode.getText().trim());
				element.addSegment(segment); 
			}
			this.searchCriteria.addElement(element);
		}
		this.searchCriteria.setCondition(new FilteringCondition(filteringCondition.getText()));
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(btnSave == ae.getSource()){
		    JFrame root = (JFrame) this.getTopLevelAncestor();

			if(isInputValid()){
				fillSearchCriteriaAndFilteringCondition();				
				
				System.out.println(searchCriteria);
				
			    root.dispose();
			} else {
				// custom title, warning icon
				JOptionPane
						.showMessageDialog(
								root,
								"Valid input:\n"
										+ "1, flight should be AirlineCode (2 lettes) + flight number (EX: AF123).\n"
										+ " 2, RBD should be 1 letter.\n"
										+ "3, Filtering condition should be separated by ; .",
								"Invalid input!", JOptionPane.WARNING_MESSAGE);

			}
		}
		
	}
}
