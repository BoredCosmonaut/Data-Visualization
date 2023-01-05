import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

public class ColumnGraph extends JPanel {

	int maxValue;
	int minValue;
	int di;
	

	ArrayList<String> Keys = new ArrayList<String>();
	ArrayList<Integer> Values = new ArrayList<Integer>();
	public ColumnGraph(int d) {
		this.di = d;
		this.setPreferredSize(new Dimension(di + 20,di + 20));
		setLayout(null);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		Graphics2D p = (Graphics2D) g;
		
		if(!Values.isEmpty()) {
			minValue = Collections.min(Values);
			maxValue = Collections.max(Values);
			
			int startpoint_x = (int) Math.round((Double.valueOf(di/(Values.size() + 1))));
			int gap = (int) Math.round(10 * (Double.valueOf(startpoint_x)/20));
			gap = Math.min(gap,20);
			
			for(int i = 0; i <Values.size(); i++) {
				int startpoint_y = (int) Math.round( Double.valueOf((di-4)*Values.get(i)) / Double.valueOf(maxValue));
				startpoint_y = di - startpoint_y;
				startpoint_y = Math.min(startpoint_y, 410); //410'dan büyük değerler x eksenine gömülüyor.
				
				int offset = gap/2;
				p.setColor(new Color(40, 40, 150)); //Koyu Mavi
				p.fillRect(startpoint_x*(i+1) - offset, startpoint_y, (int)Math.round( gap*(1.3)) , di - startpoint_y);
				p.setColor(new Color(40, 40, 225)); //Mavi
				p.fillRect(startpoint_x*(i+1) - offset, startpoint_y, gap , di - startpoint_y);
				p.setColor(new Color(40, 40, 150)); //Koyu Mavi
				p.drawRect(startpoint_x*(i+1) - offset, startpoint_y, gap , di - startpoint_y);

			}
			
			g2D.setColor(Color.BLACK);
			g2D.setStroke(new BasicStroke(3));
			g2D.drawLine(1, di, 1, 0);
			
			g2D.setStroke(new BasicStroke(3));
			g2D.drawLine(0, di - 2, di - 2, di - 2);
		}
	}

}
