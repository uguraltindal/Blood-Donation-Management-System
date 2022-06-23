package kizilay;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class basvuranDB extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_0;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JLabel lblNewLabel_14;
	private JLabel lblNewLabel_15;
	private JLabel lblNewLabel_16;
	private JLabel lblNewLabel_17;
	private JLabel lblNewLabel_18;
	private final DefaultTableModel basvuran;
	private JLabel lblNewLabel_19;
	private JLabel lblNewLabel_20;
	private JLabel lblNewLabel_21;
	private JCheckBox chckbxNewCheckBox;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					basvuranDB frame = new basvuranDB();
					frame.setVisible(true);
					frame.setTitle("Basvuranlar");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public basvuranDB() throws SQLException{
		setTitle("Kizilay");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\TULPAR\\Desktop\\icons\\tkizilay.png"));
		
		// Postgre SQL DB connection
		String user="postgres"; // Your postgre ID and Password
		String password="1801";
		
		// Your host path to created DB environment ex: //localhost:xxxx/yourdbname
		Connection conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/kizilaytest",user,password); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1081, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Basvuran Table");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(921, 255, 138, 35);
		contentPane.add(lblNewLabel);
		
		ArrayList<String> fetch_index = new ArrayList<String>();
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("ad");
		fields.add("soyad");
		fields.add("tc");
		fields.add("dogumt");
		fields.add("cinsiyet");
		fields.add("kang");
		fields.add("tarih");
		fields.add("covid");
		fields.add("odul");
		
		Object[] columnNames = {"Ad", "Soyad", "TC", "Dogum Tarihi", "Cinsiyet", "Kan Grubu", "Tarih","Covid","Odul"};
		basvuran = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(basvuran);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(40, 115, 850, 312);
		contentPane.add(sp);

		JButton btnNewButton = new JButton("Fetch");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag=false;
				String temp="";
				String and = " AND ";
				fetch_index.clear();
				if(textField_0.getText().length()!=0) {
					temp+="ad="+"'"+textField_0.getText()+"'";
					fetch_index.add(textField_0.getText());
					flag=true;}
				else fetch_index.add("NULL");
				if(textField_1.getText().length()!=0) {
					if(flag==true)
						temp+=and;
					temp+="soyad="+"'"+textField_1.getText()+"'";
					fetch_index.add(textField_1.getText());
					flag=true;
				}
				else fetch_index.add("NULL");
				if(textField_2.getText().length()!=0) {
					if(flag==true)
						temp+=and;
					temp+="tc="+"'"+textField_2.getText()+"'";
					fetch_index.add(textField_2.getText());
					flag=true;	
				}
				else fetch_index.add("NULL");
				if(textField_3.getText().length()!=0) {
					if(flag==true)
						temp+=and;
					temp+="dogumt="+"'"+textField_3.getText()+"'";
					fetch_index.add(textField_3.getText());
					flag=true;}
				else fetch_index.add("NULL");
				if(textField_4.getText().length()!=0) {
					if(flag==true)
						temp+=and;
					temp+="cinsiyet="+"'"+textField_4.getText()+"'";
					fetch_index.add(textField_4.getText());
					flag=true;}
				else fetch_index.add("NULL");	
				if(textField_5.getText().length()!=0) {
					if(flag==true)
						temp+=and;
					temp+="kang="+"'"+textField_5.getText()+"'";
					fetch_index.add(textField_5.getText());
					flag=true;}
				else fetch_index.add("NULL");
				if(textField_6.getText().length()!=0) {
					if(flag==true)
						temp+=and;
					temp+="tarih="+"'"+textField_6.getText()+"'";
					fetch_index.add(textField_6.getText());
					flag=true;}
				else fetch_index.add("NULL");
				if(textField_7.getText().length()!=0) {
					if(flag==true)
						temp+=and;
					temp+="covid="+"'"+textField_7.getText()+"'";
					fetch_index.add(textField_7.getText());
					flag=true;}
				else fetch_index.add("NULL");
				if(textField_16.getText().length()!=0) {
					temp+="odul="+"'"+textField_16.getText()+"'";
					fetch_index.add(textField_16.getText());
					flag=true;}
				else fetch_index.add("NULL");
				String query;
				if(flag==true)
					query="select * from basvuran "+"where "+temp;
				else{
					query="select * from basvuran ";
				}
				
				java.sql.Statement s;
				try {
					s = conn.createStatement();
					
					ResultSet r=s.executeQuery(query);
					String temp2="";
					DefaultTableModel dm = (DefaultTableModel)table.getModel();  // Ã–nceki sorgu sonucu
					while(dm.getRowCount() > 0){								 // oluÅŸan satÄ±rlarÄ±
					    dm.removeRow(0);										 // temizleme kÄ±smÄ±
					}
					while(r.next()) { 
						temp2 +=r.getString(1)+" ";
						String ad = r.getString(1);
						temp2 +=r.getString(2)+" ";
						String soyad = r.getString(2);
						temp2 +=r.getString(3)+" ";
						String tc = r.getString(3);
						temp2 +=r.getString(4)+" ";
						String dogumT = r.getString(4);
						temp2 +=r.getString(5)+" ";
						String cinsiyet = r.getString(5);
						temp2 +=r.getString(6)+" ";
						String kang = r.getString(6);
						temp2 +=r.getString(7)+" ";
						String tarih = r.getString(7);
						temp2 +=r.getString(8);
						String covid = r.getString(8);
						temp2 +=r.getString(9);
						String odul = r.getString(9);

					    Object[] row ={ad, soyad, tc, dogumT, cinsiyet, kang, tarih, covid,odul};
					    basvuran.addRow(row);
						
						System.out.println(temp2);
						temp2 = "";
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(915, 23, 144, 52);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp="DELETE FROM basvuran ";
				boolean flag=false;
				String and=" AND ";
				String query="";
				if(textField_0.getText().length()!=0) {
					query+="ad="+"'"+textField_0.getText()+"'";
					flag=true;}
				if(textField_1.getText().length()!=0) {
					if(flag==true)
						query+=and;
					query+="soyad="+"'"+textField_1.getText()+"'";
					flag=true;
				}
				if(textField_2.getText().length()!=0) {
					if(flag==true)
						query+=and;
					query+="tc="+"'"+textField_2.getText()+"'";
					flag=true;	
				}
				if(textField_3.getText().length()!=0) {
					if(flag==true)
						query+=and;
					query+="dogumt="+"'"+textField_3.getText()+"'";
					flag=true;}
				if(textField_4.getText().length()!=0) {
					if(flag==true)
						query+=and;
					query+="cinsiyet="+"'"+textField_4.getText()+"'";
					flag=true;}
					
				if(textField_5.getText().length()!=0) {
					if(flag==true)
						query+=and;
					query+="kang="+"'"+textField_5.getText()+"'";
					flag=true;}
				if(textField_6.getText().length()!=0) {
					if(flag==true)
						query+=and;
					query+="tarih="+"'"+textField_6.getText()+"'";
					flag=true;}
				if(textField_7.getText().length()!=0) {
					if(flag==true)
						query+=and;
					query+="covid="+"'"+textField_7.getText()+"'";
					flag=true;}
				if(textField_16.getText().length()!=0) {
					if(flag==true)
						query+=and;
					query+="odul="+"'"+textField_16.getText()+"'";
					flag=true;}
				String temp2="";
				if(flag==true) {
					temp2=" Where ";
				}
				query=temp+temp2+query;
				
				java.sql.Statement s;
				try {
					s = conn.createStatement();
					s.executeQuery(query);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(921, 89, 138, 66);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Insert");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp="";
				temp="insert into basvuran values (";
				if(textField_0.getText().length()!=0) {
					temp+="'"+textField_0.getText()+"'"+",";}
				if(textField_1.getText().length()!=0) {
					temp+="'"+textField_1.getText()+"'"+",";}
				if(textField_2.getText().length()!=0) {
					temp+="'"+textField_2.getText()+"'"+",";}
				if(textField_3.getText().length()!=0) {
					temp+="'"+textField_3.getText()+"'"+",";}
				if(textField_4.getText().length()!=0) {
					temp+="'"+textField_4.getText()+"'"+",";}
				if(textField_5.getText().length()!=0) {
					temp+="'"+textField_5.getText()+"'"+",";}
				if(textField_6.getText().length()!=0) {
					temp+="'"+textField_6.getText()+"'"+",";}
				if(textField_7.getText().length()!=0) {
					temp+="'"+textField_7.getText()+"'";}/*
				if(textField_16.getText().length()!=0) {
					temp+="'"+textField_16.getText()+"'";}*/
				temp+=");";
				java.sql.Statement s=null;
						
				try {
					s = conn.createStatement();
					ResultSet r = s.executeQuery(temp);
					s.executeQuery(temp);
					
					conn.setAutoCommit(false);
					conn.commit();
					s.close();
				} catch (SQLException e1) {
					try {
						if(s.getWarnings() == null) {
							JOptionPane optionPane = new JOptionPane("Ýþlem Baþarýlý",JOptionPane.INFORMATION_MESSAGE);
							JDialog dialog = optionPane.createDialog("Bilgilendirme");
							dialog.setAlwaysOnTop(true); // to show top of all other application
							dialog.setVisible(true); // to visible the dialog
						}
						else {
							JOptionPane optionPane = new JOptionPane("Ýþlem Baþarýsýz",JOptionPane.WARNING_MESSAGE);
							JDialog dialog = optionPane.createDialog("Uyarý");
							dialog.setAlwaysOnTop(true); // to show top of all other application
							dialog.setVisible(true); // to visible the dialog
						}
						
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						//e2.printStackTrace();
					}
					
					//e1.printStackTrace();
				}				
			}
		});
		btnNewButton_2.setBounds(921, 166, 138, 66);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("UPDATE");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "UPDATE basvuran SET ";
				boolean flag = false;
				if(textField_8.getText().length()!=0) {
					query += (fields.get(0)+"="+"'"+textField_8.getText()+"'"+",");
					flag=true;
				}
				if(textField_9.getText().length()!=0) {
					query += (fields.get(1)+"="+"'"+textField_9.getText()+"'"+",");
					flag=true;
				}
				if(textField_10.getText().length()!=0) {
					query += (fields.get(2)+"="+"'"+textField_10.getText()+"'"+",");
					flag=true;
				}
				if(textField_11.getText().length()!=0) {
					query += (fields.get(3)+"="+"'"+textField_11.getText()+"'"+",");
					flag=true;
				}
				if(textField_12.getText().length()!=0) {
					query += (fields.get(4)+"="+"'"+textField_12.getText()+"'"+",");
					flag=true;
				}
				if(textField_13.getText().length()!=0) {
					query += (fields.get(5)+"="+"'"+textField_13.getText()+"'"+",");
					flag=true;
				}
				if(textField_14.getText().length()!=0) {
					query += (fields.get(6)+"="+"'"+textField_14.getText()+"'"+",");
					flag=true;
				}
				if(textField_15.getText().length()!=0) {
					query += (fields.get(7)+"="+"'"+textField_15.getText()+"'"+",");
					flag=true;
				}/*
				if(textField_17.getText().length()!=0) {
					query += (fields.get(8)+"="+"'"+textField_17.getText()+"'"+",");
					flag=true;
				}*/
				if(query.charAt(query.length()-1)==',') {
					query=query.substring(0,query.length()-1);
				}
				boolean flag2=false;
				for(int i=0;i<fetch_index.size();i++) {
					if(!fetch_index.get(i).equals("NULL")) {
						flag2=true;
						break;
					}
				}
				if(flag==false) {
					return;
				}
				else {
					int count=0;
					if(flag2==true) query += " WHERE ";
					for (int i = 0; i < fetch_index.size(); i++) {
						if(! fetch_index.get(i).equals("NULL")) {
							if(count!=0)	query += " and ";
							//if(i==7)	query += (fields.get(i)+"="+fetch_index.get(i));
							query += (fields.get(i)+"="+"'"+fetch_index.get(i)+"'");
							count++;
						}
					}
					java.sql.Statement s;
					try {
						s = conn.createStatement();
						s.executeQuery(query);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.setBounds(915, 460, 144, 59);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("X");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_4.setBackground(Color.RED);
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.setBounds(1004, 531, 55, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("RESET");
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_0.getText().length()!=0) {
					textField_0.setText(null);
				}
				if(textField_1.getText().length()!=0) {
					textField_1.setText(null);
				}
				if(textField_2.getText().length()!=0) {
					textField_2.setText(null);
				}
				if(textField_3.getText().length()!=0) {
					textField_3.setText(null);
				}
				if(textField_4.getText().length()!=0) {
					textField_4.setText(null);
				}
				if(textField_5.getText().length()!=0) {
					textField_5.setText(null);
				}
				if(textField_6.getText().length()!=0) {
					textField_6.setText(null);
				}
				if(textField_7.getText().length()!=0) {
					textField_7.setText(null);
				}
				if(textField_16.getText().length()!=0) {
					textField_16.setText(null);
				}
			}
		});
		btnNewButton_5.setBounds(921, 301, 138, 42);
		contentPane.add(btnNewButton_5);
		
		textField_0 = new JTextField();
		textField_0.setBounds(40, 58, 70, 20);
		contentPane.add(textField_0);
		textField_0.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(130, 58, 70, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(220, 58, 80, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(310, 58, 70, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(400, 58, 70, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(490, 58, 70, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(580, 58, 70, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(670, 58, 70, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(760, 58, 70, 20);
		contentPane.add(textField_16);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(40, 480, 70, 20);
		contentPane.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(130, 480, 70, 20);
		contentPane.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(220, 480, 80, 20);
		contentPane.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(310, 480, 70, 20);
		contentPane.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(400, 480, 70, 20);
		contentPane.add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(490, 480, 70, 20);
		contentPane.add(textField_13);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(580, 480, 70, 20);
		contentPane.add(textField_14);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(670, 480, 70, 20);
		contentPane.add(textField_15);
		
		textField_17 = new JTextField();
		textField_17.setColumns(10);
		textField_17.setBounds(760, 480, 70, 20);
		contentPane.add(textField_17);
		
		lblNewLabel_1 = new JLabel("ad");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(40, 23, 70, 20);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("soyad");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(129, 23, 70, 20);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("tc");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(220, 23, 70, 20);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("dogumt");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(310, 23, 70, 20);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("cinsiyet");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(400, 23, 70, 20);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("kang");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(490, 23, 70, 20);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("tarih");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(580, 23, 70, 20);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("covid");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(670, 23, 70, 20);
		contentPane.add(lblNewLabel_8);
		
		lblNewLabel_17 = new JLabel("odul");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_17.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_17.setBounds(760, 23, 70, 20);
		contentPane.add(lblNewLabel_17);
		
		lblNewLabel_9 = new JLabel("ad");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(25, 440, 92, 20);
		contentPane.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("soyad");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setBounds(118, 440, 92, 20);
		contentPane.add(lblNewLabel_10);
		
		lblNewLabel_11 = new JLabel("tc");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setBounds(208, 440, 92, 20);
		contentPane.add(lblNewLabel_11);
		
		lblNewLabel_12 = new JLabel("dogumt");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setBounds(298, 440, 92, 20);
		contentPane.add(lblNewLabel_12);
		
		lblNewLabel_13 = new JLabel("cinsiyet");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_13.setBounds(390, 440, 92, 20);
		contentPane.add(lblNewLabel_13);
		
		lblNewLabel_14 = new JLabel("kang");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_14.setBounds(478, 440, 92, 20);
		contentPane.add(lblNewLabel_14);
		
		lblNewLabel_15 = new JLabel("tarih");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_15.setBounds(568, 440, 92, 20);
		contentPane.add(lblNewLabel_15);
		
		lblNewLabel_16 = new JLabel("covid");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_16.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_16.setBounds(658, 440, 92, 20);
		contentPane.add(lblNewLabel_16);
		
		lblNewLabel_18 = new JLabel("odul");
		lblNewLabel_18.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_18.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_18.setBounds(748, 440, 92, 20);
		contentPane.add(lblNewLabel_18);
		
		lblNewLabel_19 = new JLabel("Update i\u015Flemi \u00F6ncesinde");
		lblNewLabel_19.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_19.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_19.setBounds(895, 349, 159, 24);
		contentPane.add(lblNewLabel_19);
		
		lblNewLabel_20 = new JLabel("g\u00FCncellenmek istenen verilerin");
		lblNewLabel_20.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_20.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_20.setBounds(895, 372, 172, 35);
		contentPane.add(lblNewLabel_20);
		
		lblNewLabel_21 = new JLabel("Fetch edilmesi gerekir!");
		lblNewLabel_21.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_21.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_21.setBounds(895, 405, 159, 24);
		contentPane.add(lblNewLabel_21);
		
		chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBackground(Color.LIGHT_GRAY);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_3.setEnabled(true);
				chckbxNewCheckBox.setVisible(false);
				lblNewLabel_19.setVisible(false);
				lblNewLabel_20.setVisible(false);
				lblNewLabel_21.setVisible(false);
			}
		});
		chckbxNewCheckBox.setBounds(1028, 413, 31, 23);
		contentPane.add(chckbxNewCheckBox);
	}
}
