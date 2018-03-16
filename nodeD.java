import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ProgressMonitor;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.*;
import javax.swing.*;



public class nodeD implements ActionListener
{
	
	String text;
	DataOutputStream output;

	BufferedInputStream bis;
	
	BufferedOutputStream bos;

	byte[] receivedData;
	int in;
	
	String strLine;
	public Font f = new Font("Cambria" , Font.BOLD , 18);	
	public Font f1 = new Font("Cambria", Font.BOLD , 28);
	public Font f2 = new Font("Cambria", Font.BOLD , 18);
	public Font f3 = new Font("Cambria", Font.BOLD , 15);
	public JLabel T3= new JLabel("An Efficient Paging Scheme for Terminal Mobility using Personal Mobility ");
	public JLabel T4= new JLabel("Management Information in Interworked Fixed and Mobile Networks");
	
    public JLabel T1= new JLabel("Mobile Terminal 4");
	public JLabel T2= new JLabel();
	public JLabel l = new JLabel("Received File");
	//public JLabel selectfile= new JLabel("Select the File     :");
	public JLabel selectpath= new JLabel("Select the Path   :");
	public JLabel Availablenode= new JLabel("Nearest Mobile Terminal");
	public JLabel pathdisplay= new JLabel();
	
	public JButton browse = new JButton("Select Data");
	public JButton btn = new JButton("Transfer");
	//public JButton btn1 = new JButton("Exit");
	public JTextArea tf = new JTextArea();
	public JScrollPane pane = new JScrollPane();
	
	public JTextArea tf1 = new JTextArea();
	public JScrollPane pane1 = new JScrollPane();
	
	
	public JLabel jLabel11 ;
	public JLabel jLabel12 ;
	public JFrame jf;
	ImageIcon ii;
	public Container c;
	JPanel jp=new JPanel();
	//JPanel jp1=new JPanel();
	
	
	
	public Font f0 = new Font("Cambria" , Font.BOLD , 25);
	//public Font f1 = new Font("Cambria" , Font.BOLD , 18);
	nodeD()
	{
		
				
		jf = new JFrame("Mobile Terminal 4");
		c = jf.getContentPane();
		c.setLayout(null);
		jf.setSize(1050,600);
		
		
		jf.setResizable(false);
		
		JLabel img = new JLabel();
		ImageIcon II = new ImageIcon(this.getClass().getResource("bg.png"));

		img.setIcon(II);
		img.setBounds(0,-140,1050,800);
		img.setBackground(new Color(132,145,200));
		
		
		c.setBackground(new Color(25,155,123));
		
		//JTabbedPane jTabbedPane1 = new JTabbedPane();
	//	JPanel jPanel1= new JPanel();
		
		
		//jPanel1.setBackground(Color.DARK_GRAY);
		
		//JPanel jPanel2 = new JPanel();
		
		
	//	jPanel2.setBackground(new Color(157,159,141));
		
		
		
		jLabel11=new JLabel();
		
		jLabel11.setBounds(380,60,350,45);
		
		
		
		jLabel11.setForeground(Color.RED);
         
		jLabel11.setFont(f0);
		
       jLabel12=new  JLabel("Mobile Terminal 3 ");
		
		jLabel12.setForeground(new Color(25,25,25));
         
		jLabel12.setFont(f1);
		
		pathdisplay.setBounds(400,230,572,18);
		pathdisplay.setForeground(Color.RED);
		pathdisplay.setFont(f);
		
		Availablenode.setBounds(600,230,272,38);
		Availablenode.setForeground(Color.RED);
		Availablenode.setFont(f);
		
		//selectfile.setBounds(200,180,172,18);
	//	selectfile.setFont(f);
	//	selectfile.setForeground(Color.CYAN);
		
		selectpath.setBounds(200,210,172,18);
		selectpath.setFont(f);
		selectpath.setForeground(Color.RED);
		
		T1.setBounds(400,100,950,50);
		T2.setBounds(210,50,950,50);
		T3.setBounds(50,10,950,50);
		T4.setBounds(80,50,950,50);
		
		T1.setForeground(new Color(139,69,19));
		T2.setForeground(Color.RED);
		
		T3.setForeground(new Color(65,105,225));
		T4.setForeground(new Color(65,105,225));
		l.setBounds(720,330,250,35);
		
	
		
		
		l.setForeground(new Color(6,45,14));
		
		
		l.setFont(f2);
		
		
		//jp.setBounds(120,125,810,500);
		//jp.setBackground(Color.DARK_GRAY);
		//jp1.setBounds(118,123,814,504);
		
	   tf.setColumns(20);
	   tf.setRows(10);
	   tf.setFont(f);
	   tf.setBackground(new Color(248,248,255));
	   tf.setForeground(Color.BLUE);
	   tf.setName("tf");
	   pane.setBounds(180,250,250,200);
		
	   pane.setName("pane");
	   pane.setViewportView(tf);
	   
	   
	   browse.setBounds(200,130,150,65);
	   browse.setFont(f);
	   
	   btn.setBounds(630,412,120,80);
	   btn.setFont(f);
	   
	 //  btn1.setBounds(500,560,150,35);
	 //  btn1.setFont(f);
	      
	   tf1.setColumns(20);
	   tf1.setRows(10);
	   tf1.setFont(f);
	   tf1.setBackground(new Color(248,248,255));
	   tf1.setForeground(new Color(120,0,0));
	   tf1.setName("tf");
	   pane1.setBounds(600,260,260,150);
		
	   pane1.setName("pane");
	   pane1.setViewportView(tf1);
	      
		T1.setFont(f1);
		T2.setFont(f1);
		T3.setFont(f1);
		T4.setFont(f1);
		browse.addActionListener(this);	
		btn.addActionListener(this);
		//btn1.addActionListener(this);
		btn.setMnemonic(KeyEvent.VK_S); 
		jf.show();
		c.add(pane, BorderLayout.CENTER);
		c.add(pane1, BorderLayout.CENTER);
		//c.add(selectfile);
		c.add(selectpath);
		c.add(pathdisplay);
		c.add(Availablenode);
		c.add(browse);
		c.add(btn);
		//c.add(btn1);
		c.add(T1);
		c.add(T3);
		c.add(T4);
		c.add(jp);
		//c.add(jp1);
			c.add(img);	
				
		jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent win) {
				System.exit(0);
			}
		});		
		
		int[] ports = new int[] { 1005,1006 };

		for (int i = 0; i < 2; i++) {
			Thread t = new Thread(new PortListener(ports[i]));
			t.setName("Listener-" + ports[i]);
			t.start();

		}
			
	}
	
	
	public static void main(String args[])
	{
	new nodeD();
		
		
	}
	

	 
	class PortListener implements Runnable {
		
		BufferedOutputStream bos = null;
		
		ServerSocket server;
		Socket connection;
		BufferedReader br = null;
		int port;

		public PortListener(int port) {
			this.port = port;
		}

		public void run() {
			if(this.port==1005)
			{
				
				try { 
					   
					    server = new ServerSocket(1005);
						
					     while (true)
					   {
						                   connection = server.accept();
						
						                   br = new BufferedReader(
								           new InputStreamReader(new BufferedInputStream(
										   connection.getInputStream())));
						
						                      String strLine;
						                     // Read File Line By Line
						                   StringBuffer buffer = new StringBuffer();
						                   System.out.println("hi");
						                   while ((strLine = br.readLine()) != null)
						                  {
							                  // Print the content on the console
							                   System.out.println(strLine);
							                   buffer.append(strLine + "\n");

						                   }
					                               	br.close();
						                            connection.close();
						                            String a=buffer .toString();
						                          //  int len=a.length();
						    		                  
						    		               //   String aString = Integer.toString(len);
						    		               //   System.out.println(aString);
						                            String b=a.substring(0,23);
						                            String c=a.substring(24,42);
						                           // String d=a.substring(43,66);
						                           // String e=a.substring(68,86);
						                            
						                            
						                          //  tf1.setText(b.toString()+"\n"+c.toString());//+"\n"+d.toString()+"\n"+e.toString());	
						                            
						                           tf1.setText(buffer.toString());	
						                            String ev=tf1 .toString();
						                            text=a.substring(17,23);
						                            System.out.println(text);
						                          // String noded="Available Node : Node B , Distance 4.5 metre,Available Node : Node C , Distance 4 metre";
							         	               
						         	               
						    		              
						    		               //   String nodebsendstr;//=nodebString.toString();
						    		               //  nodebsendstr=noded.toString();;
						    		                
						                			
						/******************************************************************/
						/*******************************************************************/
					   }	                      
				  }       
				           catch (IOException e)
				            {
				        	   
				             }   
				           finally
				           {
				        	   
				            }
				
				
			           }
			else if(this.port==1006)
			{

				try { 
					 
					
					    server = new ServerSocket(port);
						
					     while (true)
					   {
						                   connection = server.accept();
						
						                   br = new BufferedReader(
								           new InputStreamReader(new BufferedInputStream(
										   connection.getInputStream())));
						
						                      String strLine;
						                     // Read File Line By Line
						                   StringBuffer buffer = new StringBuffer();
						                   System.out.println("hi");
						                   while ((strLine = br.readLine()) != null)
						                  {
							                  // Print the content on the console
							                   System.out.println(strLine);
							                   buffer.append(strLine + "\n");

						                   }
					                               	br.close();
						                            connection.close();
						                            
						                          
						                           tf.setText(buffer.toString());	
										
						/******************************************************************/
						/*******************************************************************/
						                      
						    
						   
						    
						
						
					        }
				  }       
				           catch (IOException e)
				            {
				        	   
				             }   
				           finally
				           {
				        	   
				            }
				
				
			           
				
			}
			
		}
		
		
		
		}
	
	public void actionPerformed(ActionEvent e)
	 {
	
		 if (e.getSource()==browse)
		    {
		          JFileChooser chooser = new JFileChooser();
				       try
				          {
		    		        File f = new File(new File("filename.txt").getCanonicalPath());

		   		        chooser.setSelectedFile(f);
						
		                } 
		        catch (IOException e1) 
		               {
		                }
		 
		                   chooser.showOpenDialog(browse);
		                   int retval = chooser.showOpenDialog(browse);
		                   if (retval == JFileChooser.APPROVE_OPTION){
		                   File field = chooser.getSelectedFile();
		                   pathdisplay.setText(field.getAbsolutePath());
		     }		    

		              File curFile = chooser.getSelectedFile();

		           try
		                 {

		               FileInputStream fstream = new FileInputStream(curFile);
				          DataInputStream ins = new DataInputStream(fstream);
		               BufferedReader br = new BufferedReader(new InputStreamReader(ins));
		               StringBuffer buffer = new StringBuffer();

		                while ((strLine = br.readLine()) != null)   {

		                  System.out.println (strLine);
		                 buffer.append(strLine+ "\n");
		   

		                   }
		               tf.setText(buffer.toString());

		//
		            }
		                catch (Exception e1)
		                {
		               System.err.println("Error: " + e1.getMessage());
		              }
		           }
		 if (e.getSource()==btn)
		    {
			 
			 System.out.println(text);
    	
					
					int Port = 1007;
									
					Socket client=null;
					try {
						client = new Socket("127.0.0.1", Port);
						
			    		bos = new BufferedOutputStream(client.getOutputStream()); 
			    		byte[] byteArray = tf.getText().getBytes();
			    		bos.write(byteArray, 0, byteArray.length);
			    		bos.flush();
			    		bos.close();
			    		client.close();
					} catch (UnknownHostException e1) {
						
						e1.printStackTrace();
					} catch (Exception e1) {}
						
					finally {
						if (bos != null) {
							try {
								bos.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						try {
							client.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							
					}	
		    }
		// if(e.getSource()==btn1)
		 {
		//	 System.exit(0);
		 }
	 }
	
	
}

