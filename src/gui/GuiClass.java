package gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuiClass extends JFrame{
	
	public GuiClass() {
		setTitle("Bleep");
		setSize(500,   200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setVisible(true);
		setLayout(new BorderLayout());
		
		//JPanel p1 = new JPanel(new GridLayout(4, 3));
		//for(int i  =  1;  i <= 9; i++) {
			//p1.add(new JButton("" + i));
		//}
		//p1.add(new JButton("" + 0));
		//p1.add(new JButton("Start"));
		//p1.add(new JButton("End"));
		
		//JPanel  p2 = new JPanel(new BorderLayout());
		//p2.add(new JTextField("Timer"), BorderLayout.NORTH);
		//p2.add(p1,  BorderLayout.CENTER);
		
		//add(new JButton("Put Food here"), BorderLayout.WEST);
		//add(p2,  BorderLayout.CENTER);
		//JButton  btn  =  new JButton("Ok");
		//JButton  btn2 =  new JButton("Cancel");
		//JButton  btn3 =  new JButton("Submit");
		//JButton  btn4 =  new JButton("Exit");
		//JButton  btn5 =  new JButton("Redo");
		
		//setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));
		//setLayout(new GridLayout(1,2);
		//setLayout(new BorderLayout());
		//add(btn, BorderLayout.EAST);
		//add(btn2,BorderLayout.WEST);
		//add(btn3 , BorderLayout.NORTH);
		//add(btn4 , BorderLayout.SOUTH);
		//add(btn5 , BorderLayout.CENTER);
		setVisible(true);
	}
}
