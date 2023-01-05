import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class AddDataPanel extends JPanel {
	static JTextField DataBaseNameTF = new JTextField();
	static JTextField Var1TF = new JTextField();
	static JTextField Var2TF = new JTextField();
	static JTextField v1tf = new JTextField();
	static JTextField v2tf = new JTextField();
	JLabel idCountLbl = new JLabel("1");
	static JButton CreateTableBtn = new JButton("Create");
	private JLabel ErrorMessage = new JLabel("");;
	static JButton AddDataButton = new JButton("+");
	
	LineGraph LG;
	ColumnGraph CG;
	PieChart PC;
	
	ArrayList<JTextField> JTBoxes = new ArrayList<JTextField>();
	ArrayList<String> Variable1s = new ArrayList<String>();
	ArrayList<Integer> Variable2s = new ArrayList<Integer>();
	
	DatabaseMethodsForTwoVariables twoVariableAccess;
	
	int index = 0;

	boolean EditOn = false;
	private final JButton ShowGraphs = new JButton("Show Datas");
	private final JPanel panel = new JPanel();
	private final JPanel DataViewPanel = new JPanel();
	static JLabel DataViLabel0 = new JLabel("DataName - Value");
	
	/**
	 * Create the panel.
	 */
	
	
	public AddDataPanel() {
		DatabaseConnection connection = new DatabaseConnection();
		twoVariableAccess = new DatabaseMethodsForTwoVariables(connection.connect(), "", "", "");
		
		setBackground(new Color(255, 250, 255));
		this.setPreferredSize(new Dimension(1366, 690));
		setLayout(null);
		ShowGraphs.setBounds(156, 0, 119, 23);
		add(ShowGraphs);
		
		
		LG = new LineGraph(420);
		LG.setBounds(4, 47, 420, 420);
		add(LG);
		
		PC = new PieChart(420);
		PC.setBounds(884, 47, 420, 420);
		add(PC);
		panel.setBackground(new Color(255, 250, 255));
		
		panel.setBounds(276, 0, 1080, 740);
		add(panel);
		panel.setLayout(null);
		v1tf.setBackground(new Color(221, 244, 242));
		v1tf.setBounds(58, 142, 225, 36);
		panel.add(v1tf);
		
		v1tf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		v1tf.setHorizontalAlignment(SwingConstants.CENTER);
		v1tf.setColumns(10);
		v2tf.setBackground(new Color(225, 235, 236));
		v2tf.setBounds(284, 142, 225, 36);
		panel.add(v2tf);
		
		v2tf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		v2tf.setHorizontalAlignment(SwingConstants.CENTER);
		v2tf.setColumns(10);
		AddDataButton.setBounds(224, 180, 120, 26);
		panel.add(AddDataButton);
		
		JButton recheckBut = new JButton("");
		recheckBut.setBounds(182, 34, 24, 24);
		panel.add(recheckBut);
		recheckBut.setIcon(new ImageIcon("C:\\Users\\eraya\\Desktop\\v\\vvo\\arrow.png"));
		recheckBut.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DataBaseNameTF.setBackground(new Color(221, 244, 242));
		DataBaseNameTF.setBounds(240, 10, 600, 48);
		panel.add(DataBaseNameTF);
		
		//DataBaseNameTF = new JTextField();
		DataBaseNameTF.setFont(new Font("Tahoma", Font.PLAIN, 28));
		DataBaseNameTF.setHorizontalAlignment(SwingConstants.CENTER);
		DataBaseNameTF.setEditable(true);
		DataBaseNameTF.setText("cs");
		DataBaseNameTF.setColumns(10);
		DataBaseNameTF.setEditable(true);
		Var1TF.setBackground(new Color(221, 244, 242));
		Var1TF.setBounds(240, 57, 300, 42);
		panel.add(Var1TF);
		
		Var1TF.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Var1TF.setText("ds");
		Var1TF.setEditable(true);
		Var1TF.setHorizontalAlignment(SwingConstants.CENTER);
		Var1TF.setColumns(10);
		Var1TF.setEditable(true);
		Var2TF.setBackground(new Color(221, 244, 242));
		Var2TF.setBounds(540, 57, 300, 42);
		panel.add(Var2TF);
		
		Var2TF.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Var2TF.setText("gs");
		Var2TF.setEditable(true);
		Var2TF.setHorizontalAlignment(SwingConstants.CENTER);
		Var2TF.setColumns(10);
		Var2TF.setEditable(true);
		CreateTableBtn.setBounds(490, 108, 100, 26);
		panel.add(CreateTableBtn);
		ErrorMessage.setBounds(863, 10, 291, 36);
		panel.add(ErrorMessage);
		
		ErrorMessage.setForeground(new Color(255, 0, 0));
		ErrorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		idCountLbl.setBounds(21, 142, 36, 36);
		panel.add(idCountLbl);
		

		idCountLbl.setFont(new Font("Tahoma", Font.PLAIN, 32));
		idCountLbl.setHorizontalAlignment(JLabel.RIGHT);
		DataViewPanel.setBackground(new Color(245, 240, 245));
		DataViewPanel.setBounds(0, 23, 275, 667);
		
		add(DataViewPanel);
		DataViewPanel.setLayout(null);
		DataViLabel0.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DataViLabel0.setBounds(10, 11, 255, 18);
		
		DataViewPanel.add(DataViLabel0);
		
		CG = new ColumnGraph(420);
		CG.setBounds(444, 47, 420, 420);
		add(CG);
		idCountLbl.setVisible(false);
		
		
		idCountLbl.setVisible(false);
		CreateTableBtn.setVisible(true);
		
		CreateTableBtn.setVisible(true);
		
		CreateTableBtn.addActionListener(e ->{
			if(!DataBaseNameTF.getText().equals("")) {
				CreateTableBtn.setVisible(false);
				DataBaseNameTF.setEditable(false);
				Var1TF.setEditable(false);
				Var2TF.setEditable(false);
				idCountLbl.setVisible(true);
				v2tf.setVisible(true);
				v1tf.setVisible(true);
				AddDataButton.setVisible(true);
				recheckBut.setVisible(true);
				
				twoVariableAccess.setTableName(DataBaseNameTF.getText());
				twoVariableAccess.setVariable1(Var1TF.getText());
				twoVariableAccess.setVariable2(Var2TF.getText());
				twoVariableAccess.createTableWith2Variables();
				
				DataViLabel0.setText(Var1TF.getText() + " --- " + Var2TF.getText());
			}

			}
		);
		recheckBut.setVisible(false);
		
		recheckBut.addActionListener(e ->{
			LG.Keys.clear();
			LG.Values.clear();
			
			CG.Keys.clear();
			CG.Values.clear();
			
			PC.Keys.clear();
			PC.Values.clear();
			PC.Datas.clear();
			PC.totalValue = 0;
			for(int i = 0; i < JTBoxes.size(); i++) {
				if(i%2 == 0) {
					if(i == 0) {
						index = 1;
					} else {
						index = i/2 + 1;
					}
					
					twoVariableAccess.updateVariable1(index,JTBoxes.get(i).getText());
					Variable1s.add(JTBoxes.get(i).getText());
					LG.Keys.add(JTBoxes.get(i).getText());
					CG.Keys.add(JTBoxes.get(i).getText());
					PC.Keys.add(JTBoxes.get(i).getText());
				}
				else if (i%2 == 1) {
					twoVariableAccess.updateVariable2(index, Integer.valueOf(JTBoxes.get(i).getText()));
					Variable2s.add(Integer.valueOf(JTBoxes.get(i).getText()));
					LG.Values.add(Integer.valueOf(JTBoxes.get(i).getText()));
					CG.Values.add(Integer.valueOf(JTBoxes.get(i).getText()));
					PC.Values.add(Integer.valueOf(JTBoxes.get(i).getText()));
					PC.Datas.add(Integer.valueOf(JTBoxes.get(i).getText()));
					PC.totalValue += Integer.valueOf(JTBoxes.get(i).getText());
				}
				PC.Datas.sort(null);
			}
			
			int x = DataViLabel0.getX();
			int y = DataViLabel0.getY();
			for(int i = 0; i < Variable1s.size(); i++) {
				JLabel DataViLabel = new JLabel();
				//System.out.println(Variable1s.get(i) + ":    " + Integer.toString(Variable2s.get(i)));
				DataViLabel.setText(Variable1s.get(i) + ":    " + Integer.toString(Variable2s.get(i)));
				y += 16;
				DataViLabel.setBounds(x, y, 255, 18);
				DataViLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				DataViewPanel.add(DataViLabel);
			}
			DataViewPanel.setVisible(false);
			DataViewPanel.setVisible(true);
		});
		AddDataButton.setVisible(false);
		AddDataButton.setVisible(false);
		
		AddDataButton.addActionListener(e ->{
			boolean canContinue = true;
			if(v1tf.getText().equals("") || v2tf.getText().equals("")) {
				ErrorMessage.setText("VARIABLES CAN'T BE EMPTY");
				canContinue = false;
			}
			if(canContinue) {
				ErrorMessage.setText("");
				int lx = idCountLbl.getX();
				int x = v1tf.getX();
				int x1 = v2tf.getX();
				int bx = AddDataButton.getX();
				int y = v1tf.getY();
				int by = AddDataButton.getY();
				

				
				
				JTextField prTF1 = new JTextField(v1tf.getText());
				prTF1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				prTF1.setHorizontalAlignment(SwingConstants.CENTER);
				prTF1.setBounds(x, y, 225, 36);
				prTF1.setEditable(false);
				panel.add(prTF1);
				JTBoxes.add(prTF1);
				
				JTextField prTF2 = new JTextField(v2tf.getText());
				prTF2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				prTF2.setHorizontalAlignment(SwingConstants.CENTER);
				prTF2.setBounds(x1, y, 225, 36);
				prTF2.setEditable(false);
				panel.add(prTF2);
				JTBoxes.add(prTF2);
				
				JLabel prvIDlabel = new JLabel(idCountLbl.getText());
				prvIDlabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
				prvIDlabel.setBounds(lx, y, 36, 36);
				prvIDlabel.setHorizontalAlignment(JLabel.RIGHT);
				panel.add(prvIDlabel);
				
				//System.out.println(JTBoxes.size());
				
				
				y += 36;
				by += 36;
				v1tf.setBounds(x, y, 225, 36);
				v2tf.setBounds(x1, y, 225, 36);
				AddDataButton.setBounds(bx, by, 120, 26);
				idCountLbl.setBounds(lx, y, 36, 36);
				
				twoVariableAccess.addData(Integer.valueOf(idCountLbl.getText()), v1tf.getText(), Integer.valueOf(v2tf.getText()));
				
				v1tf.setText("");
				v2tf.setText("");
				idCountLbl.setText(String.valueOf(Integer.valueOf(idCountLbl.getText()) + 1));
				
				if(idCountLbl.getText().equals("16")) {
					x = 586;
					x1 = 811;
					lx = 540;
					bx = 751;
					y = 142;
					by = 180;
					
					
					v1tf.setBounds(x, y, 225, 36);
					v2tf.setBounds(x1, y, 225, 36);
					AddDataButton.setBounds(751, by, 120, 26);
					idCountLbl.setBounds(lx, y, 36, 36);
				}
				
				
				
				if(idCountLbl.getText().equals("31")) {
					AddDataButton.setEnabled(false);
					v1tf.setVisible(false);
					v2tf.setVisible(false);
					idCountLbl.setVisible(false);
				}
				
				
			}
			

		});
		v2tf.setVisible(false);
		v2tf.setVisible(false);
		v1tf.setVisible(false);
		v1tf.setVisible(false);
		LG.setVisible(false);
		ShowGraphs.addActionListener(e ->{
			panel.setVisible(!panel.isVisible());
			if(panel.isVisible()) {
				LG.setVisible(false);
				CG.setVisible(false);
				PC.setVisible(false);
				DataViewPanel.setVisible(true);
			} else {
				LG.setVisible(true);
				CG.setVisible(true);
				PC.setVisible(true);
				DataViewPanel.setVisible(false);
			}
			
		});
	}
}
