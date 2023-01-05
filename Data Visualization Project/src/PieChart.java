import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class PieChart extends JPanel {

	int maxValue;
	int minValue;
	int di;
	
	ArrayList<String> Keys = new ArrayList<String>();
	ArrayList<Integer> Values = new ArrayList<Integer>();
	ArrayList<Integer> Datas = new ArrayList<Integer>();
	double totalValue = 0;
	
	public PieChart(int d) {
		this.di = di;

		Datas.sort(null);
		//System.out.println(Datas);
		this.setPreferredSize(new Dimension(di,di));
		setLayout(null);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		Graphics2D p = (Graphics2D) g;
		//System.out.println(Datas);
		int start_ang = 90;
		int angle = 0;
		if(!Values.isEmpty()) {

			for(int i = 0; i < Datas.size(); i++) {
				Random rand = new Random();
				Color rgb = new Color(rand.nextInt(200), rand.nextInt(200), rand.nextInt(200));
				if(i%3 == 0) {
					rgb = new Color(255, rand.nextInt(190)+20, rand.nextInt(10));
				}
				else if(i%3 == 1) {
					rgb = new Color(rand.nextInt(10), 255, rand.nextInt(190) + 20);
				}
				else if(i%3 == 2) {
					rgb = new Color(rand.nextInt(190) + 20, rand.nextInt(10), 255);
				}
				
				rgb = rgb.brighter();
				p.setColor(rgb);
				
				angle = (int) Math.ceil( Double.valueOf((360*Datas.get(i)/totalValue)));		
				p.fillArc(0, 0, 420, 420, start_ang, -angle);
				start_ang = start_ang - angle;
			}
			g2D.setColor(Color.DARK_GRAY);
			g2D.setStroke(new BasicStroke(3));
			g2D.drawOval(210 - 21, 210 - 21, 42, 42);
			g2D.drawOval(0, 0, 420, 420);
		}
		}

}
