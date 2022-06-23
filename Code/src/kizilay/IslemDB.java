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

public class IslemDB extends JFrame {

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
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JLabel lblNewLabel_14;
	private JLabel lblNewLabel_15;
	private final DefaultTableModel islem;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IslemDB frame = new IslemDB();
					frame.setVisible(true);
					frame.setTitle("Ä°ÅŸlemler");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public IslemDB() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\TULPAR\\Desktop\\icons\\tkizilay.png"));

		
		String user="postgres";
		String password="1801";
		Connection conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/kizilaytest",user,password);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1081, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u0130\u015Flem Table");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(921, 255, 138, 35);
		contentPane.add(lblNewLabel);
		
		ArrayList<String> fetch_index = new ArrayList<String>();
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("islemid");
		fields.add("islemtipi");
		fields.add("islemtc");
		fields.add("kangr");
		fields.add("kanaldigitc");
		fields.add("issn");
		fields.add("tarih");
		
		Object[] columnNames = {"Ýslem ID", "Ýslem Tipi", "Ýslem TC", "Kan Grubu", "Kan Aldigi TC", "SSN", "tarih"};
		islem = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(islem);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(40, 115, 850, 312);
		contentPane.add(sp);
		
		JButton btnNewButton = new JButton("Fetch");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag=false;
				String temp="";
				String and = " AND ";
				fetch_index.clear();
				if(textField_0.getText().length()!=0) {
					temp+="islemid="+"'"+textField_0.getText()+"'";
					fetch_index.add(textField_0.getText());
					flag=true;}
				else fetch_index.add("NULL");
				if(textField_1.getText().length()!=0) {
					if(flag==true)
						temp+=and;
					temp+="islemtipi="+"'"+textField_1.getText()+"'";
					fetch_index.add(textField_1.getText());
					flag=true;
				}
				else fetch_index.add("NULL");
				if(textField_2.getText().length()!=0) {
					if(flag==true)
						temp+=and;
					temp+="islemtc="+"'"+textField_2.getText()+"'";
					fetch_index.add(textField_2.getText());
					flag=true;	
				}
				else fetch_index.add("NULL");
				if(textField_3.getText().length()!=0) {
					if(flag==true)
						temp+=and;
					temp+="kangr="+"'"+textField_3.getText()+"'";
					fetch_index.add(textField_3.getText());
					flag=true;}
				else fetch_index.add("NULL");
				if(textField_4.getText().length()!=0) {
					if(flag==true)
						temp+=and;
					temp+="kanaldigitc="+"'"+textField_4.getText()+"'";
					fetch_index.add(textField_4.getText());
					flag=true;}
				else fetch_index.add("NULL");	
				if(textField_5.getText().length()!=0) {
					if(flag==true)
						temp+=and;
					temp+="issn="+"'"+textField_5.getText()+"'";
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
				
				String query;
				if(flag==true)
					query="select * from islem "+"where "+temp;
				else{
					query="select * from islem ";
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
						String islemid = r.getString(1);
						temp2 +=r.getString(2)+" ";
						String islemtipi = r.getString(2);
						temp2 +=r.getString(3)+" ";
						String islemtc = r.getString(3);
						temp2 +=r.getString(4)+" ";
						String kangr = r.getString(4);
						temp2 +=r.getString(5)+" ";
						String kanaldigitc = r.getString(5);
						temp2 +=r.getString(6)+" ";
						String issn = r.getString(6);
						temp2 +=r.getString(7)+" ";
						String tarih = r.getString(7);

					    Object[] row ={islemid, islemtipi, islemtc, kangr, kanaldigitc, issn, tarih};
					    islem.addRow(row);
						
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
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 18));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp="DELETE FROM islem ";
				boolean flag=false;
				String and=" AND ";
				String query="";
				if(textField_0.getText().length()!=0) {
					query+="islemid="+"'"+textField_0.getText()+"'";
					flag=true;}
				if(textField_1.getText().length()!=0) {
					if(flag==true)
						query+=and;
					query+="islemtipi="+"'"+textField_1.getText()+"'";
					flag=true;
				}
				if(textField_2.getText().length()!=0) {
					if(flag==true)
						query+=and;
					query+="islemtc="+"'"+textField_2.getText()+"'";
					flag=true;	
				}
				if(textField_3.getText().length()!=0) {
					if(flag==true)
						query+=and;
					query+="kangr="+"'"+textField_3.getText()+"'";
					flag=true;}
				if(textField_4.getText().length()!=0) {
					if(flag==true)
						query+=and;
					query+="kanaldigitc="+"'"+textField_4.getText()+"'";
					flag=true;}
					
				if(textField_5.getText().length()!=0) {
					if(flag==true)
						query+=and;
					query+="issn="+"'"+textField_5.getText()+"'";
					flag=true;}
				if(textField_6.getText().length()!=0) {
					if(flag==true)
						query+=and;
					query+="tarih="+"'"+textField_6.getText()+"'";
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
		btnNewButton_2.setFont(new Font("Dialog", Font.BOLD, 18));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp="";
				temp="insert into islem values (";
				temp+="nextval('seq'),";
				if(textField_1.getText().length()!=0) {
					temp+="'"+textField_1.getText()+"'"+",";}
				if(textField_2.getText().length()!=0) {
					temp+="'"+textField_2.getText()+"'"+",";}
				if(textField_3.getText().length()!=0) {
					temp+="'"+textField_3.getText()+"'"+",";}
				if(textField_4.getText().length()!=0) {
					temp+="'"+textField_4.getText()+"'"+",";}
				else
					temp+="null"+",";
				if(textField_5.getText().length()!=0) {
					temp+="'"+textField_5.getText()+"'"+",";}
				if(textField_6.getText().length()!=0) {
					temp+="'"+textField_6.getText()+"'";}
				temp+=");";
				
				java.sql.Statement s=null;				
				try {
					s = conn.createStatement(); 
					s.executeQuery(temp);
					conn.setAutoCommit(false);
					conn.commit();
					
					s.close();
				} catch (SQLException e1) {
					//e1.printStackTrace();
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
					
				}				
			}
		});
		btnNewButton_2.setBounds(921, 166, 138, 66);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("UPDATE");
		btnNewButton_3.setFont(new Font("Dialog", Font.BOLD, 18));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "UPDATE islem SET ";
				boolean flag = false;
				if(textField_7.getText().length()!=0) {
					query += (fields.get(0)+"="+"'"+textField_7.getText()+"'"+",");
					flag=true;
				}
				if(textField_8.getText().length()!=0) {
					query += (fields.get(1)+"="+"'"+textField_8.getText()+"'"+",");
					flag=true;
				}
				if(textField_9.getText().length()!=0) {
					query += (fields.get(2)+"="+"'"+textField_9.getText()+"'"+",");
					flag=true;
				}
				if(textField_10.getText().length()!=0) {
					query += (fields.get(3)+"="+"'"+textField_10.getText()+"'"+",");
					flag=true;
				}
				if(textField_11.getText().length()!=0) {
					query += (fields.get(4)+"="+"'"+textField_11.getText()+"'"+",");
					flag=true;
				}
				if(textField_12.getText().length()!=0) {
					query += (fields.get(5)+"="+"'"+textField_12.getText()+"'"+",");
					flag=true;
				}
				if(textField_13.getText().length()!=0) {
					query += (fields.get(6)+"="+"'"+textField_13.getText()+"'");
					flag=true;
				}
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
		btnNewButton_3.setBounds(915, 460, 144, 59);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.setEnabled(false);
		
		JButton btnNewButton_4 = new JButton("X");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_4.setBackground(Color.RED);
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.setBounds(1008, 531, 51, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("RESET");
		btnNewButton_5.setFont(new Font("Dialog", Font.BOLD, 18));
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
			}
		});
		btnNewButton_5.setBounds(921, 301, 138, 42);
		contentPane.add(btnNewButton_5);
		
		textField_0 = new JTextField();
		textField_0.setBounds(27, 58, 92, 20);
		contentPane.add(textField_0);
		textField_0.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(147, 58, 92, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(267, 58, 101, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(387, 58, 92, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(507, 58, 92, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(627, 58, 92, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(747, 58, 92, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(27, 499, 92, 20);
		contentPane.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(147, 499, 92, 20);
		contentPane.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(267, 499, 101, 20);
		contentPane.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(387, 499, 92, 20);
		contentPane.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(507, 499, 101, 20);
		contentPane.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(627, 499, 92, 20);
		contentPane.add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(747, 499, 92, 20);
		contentPane.add(textField_13);
		
		lblNewLabel_1 = new JLabel("islemid");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(27, 14, 92, 20);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("islemtipi");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(147, 14, 92, 20);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("islemtc");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(267, 14, 92, 20);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("kangr");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(387, 14, 92, 20);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("kanaldigitc");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(507, 14, 92, 20);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("issn");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(627, 14, 92, 20);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("tarih");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(747, 14, 92, 20);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_9 = new JLabel("islemid");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_9.setBounds(27, 452, 92, 20);
		contentPane.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("islemtipi");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_10.setBounds(147, 452, 92, 20);
		contentPane.add(lblNewLabel_10);
		
		lblNewLabel_11 = new JLabel("islemtc");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_11.setBounds(267, 455, 92, 20);
		contentPane.add(lblNewLabel_11);
		
		lblNewLabel_12 = new JLabel("kangr");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_12.setBounds(387, 455, 92, 20);
		contentPane.add(lblNewLabel_12);
		
		lblNewLabel_13 = new JLabel("kanaldigitc");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_13.setBounds(507, 455, 92, 20);
		contentPane.add(lblNewLabel_13);
		
		lblNewLabel_14 = new JLabel("issn");
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_14.setBounds(627, 455, 92, 20);
		contentPane.add(lblNewLabel_14);
		
		lblNewLabel_15 = new JLabel("tarih");
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_15.setBounds(747, 455, 92, 20);
		contentPane.add(lblNewLabel_15);
		
	
		
		JLabel lblNewLabel_8 = new JLabel("Update i\u015Flemi \u00F6ncesinde");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_8.setBounds(895, 353, 159, 24);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_16 = new JLabel("g\u00FCncellenmek istenen verilerin");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_16.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_16.setBounds(895, 376, 172, 35);
		contentPane.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("Fetch edilmesi gerekir!");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_17.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_17.setBounds(895, 409, 159, 24);
		contentPane.add(lblNewLabel_17);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBackground(Color.LIGHT_GRAY);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_3.setEnabled(true);
				chckbxNewCheckBox.setVisible(false);
				lblNewLabel_8.setVisible(false);
				lblNewLabel_16.setVisible(false);
				lblNewLabel_17.setVisible(false);
			}
		});
		chckbxNewCheckBox.setBounds(1028, 417, 31, 23);
		contentPane.add(chckbxNewCheckBox);
	}
}
