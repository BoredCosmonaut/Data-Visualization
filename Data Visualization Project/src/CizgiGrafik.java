import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

import javax.swing.JPanel;

public class CizgiGrafik extends JPanel{
	int maxValue;
	int minValue;
	int di;
	
	DatabaseMethodsForTwoVariables TVA;
	HashMap<String,Integer> veriler;
	int hsSize;
	
	CizgiGrafik(int d,HashMap<String,Integer> veri, DatabaseMethodsForTwoVariables tw){
		this.di = d;
		this.veriler = tw.createHashMap();
		this.TVA = tw;
		hsSize = veriler.size();
		minValue = TVA.getVariable2ValueWithId(1);
		maxValue = TVA.getVariable2ValueWithId(1);
		
		this.setPreferredSize(new Dimension(di,di));
		System.out.println(veriler);
	}
	
	
	public void setMaxNMin() {
		for(int i = 0; i < veriler.size(); i++) {
			if(TVA.getVariable2ValueWithId(i + 1) < minValue) {
				minValue = TVA.getVariable2ValueWithId(i + 1);
			}
			if(TVA.getVariable2ValueWithId(i + 1) > maxValue) {
				maxValue = TVA.getVariable2ValueWithId(i + 1);
			}
		}
		//System.out.println(minValue + ", " + maxValue);
	}
	
	
	
	
	int startpoint_x = (int) Math.round((Double.valueOf(di/(hsSize + 1))));
	
	public void paint(Graphics g) {
		setMaxNMin();
		
		Graphics2D g2D = (Graphics2D) g;
		Graphics2D p = (Graphics2D) g;
		
		g2D.setColor(Color.BLACK);
		g2D.setStroke(new BasicStroke(3));
		g2D.drawLine(1, di, 1, 0);
		
		g2D.setStroke(new BasicStroke(3));
		g2D.drawLine(0, di - 2, di - 2, di - 2);
		
		p.setStroke(new BasicStroke(2));
		int prev_x = 4;
		int prev_y = 416;
		for(int i = 0; i < hsSize; i++) {
			
			int startpoint_y = (int) Math.round( Double.valueOf((di-4)*veriler.get(TVA.getVariable1ValueWithId(i + 1))) / Double.valueOf(maxValue));
			startpoint_y = di - startpoint_y;
			startpoint_y = Math.min(startpoint_y, 410); //410'dan büyük değerler x eksenine gömülüyor.
			p.setStroke(new BasicStroke(6));
			p.setColor(new Color(75, 75, 180)); //Mavi
			p.drawLine(startpoint_x*(i+1), startpoint_y, prev_x, prev_y);
			
			p.setStroke(new BasicStroke(5));
			p.setColor(new Color(210, 60, 60)); //Kırmızı
			if(i != 0) {
				p.drawOval((prev_x - 2), (prev_y - 2), 4, 4); // çemberi eski pozisyonunda bir kez daha çizmesinin nedeni çizginin üstünü kapatması için.
			}
			p.drawOval(startpoint_x*(i+1) - 2, startpoint_y - 2, 4, 4); // -2, çemberi merkezlemek için

			prev_x = startpoint_x*(i+1);
			prev_y = startpoint_y;
			//
		}
	}
	
}
