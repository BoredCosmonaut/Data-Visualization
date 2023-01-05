import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.Color;

public class MainPage extends JFrame {

	private JPanel contentPane;
	AddDataPanel addDataPanel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
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
	public MainPage() {
		setBackground(new Color(248, 240, 248));
		setTitle("AutoDAG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddBase = new JButton("+");
		btnAddBase.setBounds(0, 690, 135, 43);
		contentPane.add(btnAddBase);
		
		JButton btnEdit = new JButton("EDIT");
		btnEdit.setBounds(145, 690, 135, 43);
		contentPane.add(btnEdit);
		
		addDataPanel = new AddDataPanel();
		addDataPanel.setVisible(true);
		addDataPanel.setBounds(0, 0, 1366, 690);
		contentPane.add(addDataPanel);
		
		
		this.setSize(1366, 768);

		
		btnAddBase.addActionListener(e ->{
			contentPane.remove(addDataPanel);
			addDataPanel = new AddDataPanel();
			contentPane.add(addDataPanel);
			addDataPanel.setBounds(0, 0, 1366, 690);
			addDataPanel.setVisible(true);
			revalidate();
			repaint();

		});
		
		btnEdit.addActionListener(e ->{
		//	addDataPanel.setVisible(!addDataPanel.isVisible());
			for(int i = 0; i < addDataPanel.JTBoxes.size(); i++) {
				addDataPanel.JTBoxes.get(i).setEditable(!addDataPanel.JTBoxes.get(i).isEditable());
			}
			if(!addDataPanel.CreateTableBtn.isVisible()) {
				addDataPanel.AddDataButton.setVisible(!addDataPanel.AddDataButton.isVisible());
			}
			
		});
	}
}
