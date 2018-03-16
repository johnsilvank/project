import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.Toolkit;


public class ClipExample {
  public static void main(String[] args) {
  
  // try
		// {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		// }
		// catch (Exception ex)
		// {
			// System.out.println("Failed loading L&F: ");
			// System.out.println(ex);
			// ex.printStackTrace();
		// }
  
  
    JFrame f = new Clipping();
    f.show();
  }
}
class Clipping extends JFrame implements ActionListener, Runnable 
{
	Thread th;
	ImageIcon II;
  public Clipping() 
  {
	  
    setTitle("Terminal Mobility");
    setSize(900,700);
	this.setResizable(false);
    addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent event) {
    System.exit(0);
   }
 });
    
		

		
	
    Container contentPane = getContentPane();
  
    panel = new ClipPanel();
	
  panel.setBackground(new Color(159,182,205));

//panel.setBounds(100,200,400,400);  
 contentPane.add(panel);

 
 
 
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(159,182,205));
    ButtonGroup group = new ButtonGroup();
	
	
	
	
    button1 = new JButton("");
	button1.setIcon(new ImageIcon("p1.png"));
    button1.setFont(new Font("Times new roman",Font.BOLD,16));
	//button1.setForeground(Color.CYAN);
  //  button1.setBackground(Color.GREEN);
  button1.setText("Mobile Terminal 1");
  button1.setBounds(50,0,200,10); 
  //button1.setHorizontalTextPosition(SwingConstants.LEFT);
 buttonPanel.add(button1);
   // group.add(button1);
    button1.addActionListener(this);
	
    button2 = new JButton("");
	button2.setIcon(new ImageIcon("p2.png"));
    button2.setFont(new Font("Times new roman",Font.BOLD,16));
button2.setText("Mobile Terminal 2");
    //button2.setBackground(Color.RED);
	 button2.setBounds(100,0,10,10); 
	buttonPanel.add(button2);
   // group.add(button2);
    button2.addActionListener(this);
	
    button3 = new JButton("");
	button3.setIcon(new ImageIcon("p3.png"));
    button3.setFont(new Font("Times new roman",Font.BOLD,16));
	button3.setText("Mobile \nTerminal 3");
   // button3.setBackground(Color.MAGENTA);
   button3.setBounds(200,0,10,10); 
    buttonPanel.add(button3);
  //  group.add(button3);
    button3.addActionListener(this);
	
    button4 = new JButton("");
	button4.setIcon(new ImageIcon("p4.png"));
    button4.setFont(new Font("Times new roman",Font.BOLD,16));
	button4.setText("Mobile Terminal 4");
  //  button4.setBackground(Color.YELLOW);
  button4.setBounds(300,0,10,10); 
    buttonPanel.add(button4);
   // group.add(button4);
    button4.addActionListener(this);
	
 button5= new JButton("   Exit  ");
 button5.setFont(new Font("Times new roman",Font.BOLD,16));
  button5.setBackground(Color.GREEN);
  //  buttonPanel.add(button5);
  //  group.add(button5);
    button5.addActionListener(this);
	
	panel.setBounds(0,75,900,800);  
    contentPane.add(buttonPanel);
    	buttonPanel.setBounds(0,50,100,100);
	
  }
  

  public void start ()
	{
		
		 th = new Thread (this);
		
		th.start ();
		
		
	}
 
  public void stop ()
	{
	  
	}

	public void destroy()
	{

	}

	public void run ()
	{		
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
			while (true)
		{	
				
			if(nodeprocess==1)	{
				
			if(panel.pos_y==200)
			{
			}
			else
			{
				panel.pos_x ++;
				panel.pos_y ++;
				panel.pos_x0 ++;
				panel.pos_y0 ++;
				panel.pos_x1 --;
				panel.pos_y1 --;
				panel.pos_x1a --;
				panel.pos_y1a --;
				
				
				
			}
			 
			}
			else if(nodeprocess==2)
				
			{
				
				
				if(panel.pos_x2==300)
			{
			}
			else
			{
				panel.pos_x2--;
				panel.pos_x2a --;
				if(panel.pos_y1==200)
				{
				}
				else
				{
				panel.pos_y1++;
				panel.pos_y1a++;
				}
							
			}
			}
			else if(nodeprocess==3)
			{
				if(panel.pos_y3==450)
				{
				}
				else
				{
					panel.pos_y3++;
					panel.pos_y3a++;
					
					if(panel.pos_y==400)
					{
					}
					else
					{
					panel.pos_y ++;
					panel.pos_y0 ++;
					}
					
				}
			}
			
			else if(nodeprocess==4)
			{
			
				if(panel.pos_x1==500)
				{
				}
				else
				{
					panel.pos_x1++;
					panel.pos_x1a++;
					
					if(panel.pos_y3==120)
					{
					
					}
					else
					{
						panel.pos_y3--;
						panel.pos_y3a--;
					}
					
					
				}
			}
			
			repaint();
			try
			{
				
				Thread.sleep (10);
			}
			catch (InterruptedException ex)
			{
				}
					
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		}
	}		

 public void actionPerformed(ActionEvent e) {
  
    if(e.getSource()==button1)
    {
    	nodeprocess=1;
    	start();
    	BufferedOutputStream bos = null;
    	byte[] byteArray;
    	String strLine = null;
	    String newline = "\n";
    	String  sendstr;
	 	   Socket client = null;
		    StringBuffer buffer = new StringBuffer();
		 	int Port = 1000;
				try {
				client = new Socket("127.0.0.1", Port);
				bos = new BufferedOutputStream(client.getOutputStream());
	    		String a="Next Terminal : Mobile 2, Distance :"+Math.sqrt(((panel.pos_x-panel.pos_x1)*(panel.pos_x-panel.pos_x1))+((panel.pos_y-panel.pos_y1)*(panel.pos_y-panel.pos_y1))) +" metre";
	               
	               sendstr=a.toString();
	               
	                sendstr+=buffer.toString();
	                
	    		byteArray = sendstr.getBytes();
	    		bos.write(byteArray, 0, byteArray.length);
	    		bos.flush();
	    		bos.close();
	    		client.close();
	    		    			
			}
			
			catch (UnknownHostException e1)
			{
								
			}
					 
			catch (Exception e1) {}
			
    }
    
    if(e.getSource()==button2)
    {
    	nodeprocess=4;
    	start();
    	BufferedOutputStream bos = null;
    	byte[] byteArray;
    	String strLine = null;
	    String newline = "\n";
    	String  sendstr;
	 	   Socket client = null;
		    StringBuffer buffer = new StringBuffer();
		 	int Port = 1004;
				try {
				client = new Socket("127.0.0.1", Port);
				bos = new BufferedOutputStream(client.getOutputStream());
	    		String a="Next Terminal : Mobile 4, Distance "+Math.sqrt(((panel.pos_x2-panel.pos_x1)*(panel.pos_x2-panel.pos_x1))+((panel.pos_y2-panel.pos_y1)*(panel.pos_y2-panel.pos_y1))) +" metre";

	               
	               sendstr=a.toString();
	               
	                sendstr+=buffer.toString();
	                
	    		byteArray = sendstr.getBytes();
	    		bos.write(byteArray, 0, byteArray.length);
	    		bos.flush();
	    		bos.close();
	    		client.close();
	    		    			
			}
			
			catch (UnknownHostException e1)
			{
								
			}
					 
			catch (Exception e1) {}
		   }
    if(e.getSource()==button3)
    {
    	nodeprocess=2;
    	start();
    }
    if(e.getSource()==button4)
    {
    	nodeprocess=3;
    	start();
    }
    if(e.getSource()==button5)
    {
    	System.exit(0);
    }
  }


  public JButton button1;
  public JButton button2;
  public JButton button3;
  public JButton button4;
  public JButton button5;
  int nodeprocess;
  public ClipPanel panel;
}
class ClipPanel extends JPanel {
	    public int pos_x = 25;
	    public int pos_y =25;
	    
	    public int pos_x0 = 110;
	    public int pos_y0 = 110;
	        
	    public int pos_x1 = 200;
	    public int pos_y1 = 300;
	    public int pos_x1a = 485;
	    public int pos_y1a = 385;
	    
	    public int pos_x2 = 400;
	    public int pos_y2 = 50;
	    public int pos_x2a = 785;
	    public int pos_y2a = 135;
	    
	    public int pos_x3 = 450;
	    public int pos_y3 = 400;
	    public int pos_x3a = 835;
	    public int pos_y3a = 485;
	    
	    public int radius = 90;
	    public int radius0 = -15;
	    
	    public int radius1 = 90;
	    public int radius1a = -15;
	    
	    public int radius2 = 90;
	    public int radius2a = -15;
	    
	    public int radius3= 90;
	    public int radius3a = -15;
        public void paint(Graphics g) {
        super.paint(g);
			JLabel img = new JLabel();
		ImageIcon II = new ImageIcon(this.getClass().getResource("map3.jpg"));
		img.setIcon(II);
		img.setBounds(0,0,912,600);
		//img.setBackground(new Color(132,145,200));
		this.add(img);
		
        Graphics2D g2d = (Graphics2D)g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                          RenderingHints.VALUE_ANTIALIAS_ON);

    g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                         RenderingHints.VALUE_RENDER_QUALITY);

		
		
						 
						 
    int w = getWidth();
    int h = getHeight();

	Image img1 = Toolkit.getDefaultToolkit().getImage("p1.png");
    g2d.drawImage(img1,pos_x, pos_y, this);
	
	Image img2 = Toolkit.getDefaultToolkit().getImage("p2.png");
    g2d.drawImage(img2,pos_x1, pos_y1, this);
	
	Image img3 = Toolkit.getDefaultToolkit().getImage("p4.png");
    g2d.drawImage(img3,pos_x2, pos_y2, this);
	
	Image img4 = Toolkit.getDefaultToolkit().getImage("p3.png");
    g2d.drawImage(img4,pos_x3, pos_y3, this);
	
	
	
	
    Rectangle2D circle = new Rectangle2D.Double (pos_x, pos_y, radius, radius);
    Rectangle2D circle0 = new Rectangle2D.Double (pos_x0, pos_y0, radius0, radius0);
    
    Rectangle2D circle1 = new Rectangle2D.Double (pos_x1, pos_y1, radius, radius);
    Rectangle2D circle1a = new Rectangle2D.Double (pos_x1a, pos_y1a, radius1a, radius1a);
    
    Rectangle2D circle2 = new Rectangle2D.Double (pos_x2, pos_y2, radius, radius);
    Rectangle2D circle2a = new Rectangle2D.Double (pos_x2a, pos_y2a, radius2a, radius2a);
    
    Rectangle2D circle3= new Rectangle2D.Double (pos_x3, pos_y3, radius3, radius3);
    Rectangle2D circle3a = new Rectangle2D.Double (pos_x3a, pos_y3a, radius3a, radius3a);
   

 
     g2d.setColor(new Color(155,55,144));
    // g2d.fill(circle);
	 
     g2d.setColor(Color.PINK);
    // g2d.fill(circle0);
    
     g2d.setColor(Color.RED);
     //g2d.fill(circle1);
	 
     g2d.setColor(Color.BLACK);
    // g2d.fill(circle1a);
	 
     g2d.setColor(Color.MAGENTA);
    // g2d.fill(circle2);
	 
     g2d.setColor(Color.BLACK);
    // g2d.fill(circle2a);
	 
     g2d.setColor(Color.YELLOW);
    // g2d.fill(circle3);
	 
     g2d.setColor(Color.BLACK);
    // g2d.fill(circle3a);

 
  //  g2d.draw(circle);
   // g2d.draw(circle0);
   // g2d.draw(circle1);
   // g2d.draw(circle1a);
   // g2d.draw(circle2);
    
   // g2d.draw(circle2a);
    
  //  g2d.draw(circle3);
  //  g2d.draw(circle3a);
    
  

  }
  public void step() {
  	
  	int w = getWidth();
      int h = getHeight();
      
     if(pos_x==500)
      	 
		{
			
		}
		else
		{
     
			pos_x0=pos_x0+100;
			pos_y0=pos_y0+100;
			pos_x=pos_x+100;
			pos_y=pos_y+100;
		}
     
  }
  
  
  
}