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

public class PlazmaDb extends JFrame {

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
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private final DefaultTableModel plazma;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JCheckBox chckbxNewCheckBox;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlazmaDb frame = new PlazmaDb();
					frame.setVisible(true);
					frame.setTitle("Plazma");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PlazmaDb() throws SQLException {
		setTitle("Plazma");
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
		
		JLabel lblNewLabel = new JLabel("Plazma Table");
		lblNewLabel.setBounds(921, 255, 138, 35);
		contentPane.add(lblNewLabel);
		
		ArrayList<String> fetch_index = new ArrayList<String>();
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("ptc");
		fields.add("tedaviTipi");
		fields.add("iyilesentc");
		fields.add("covidbas");
		fields.add("covidbit");
		
		Object[] columnNames = {"Plazma TC", "Tedavi Tipi", "Iyilesen TC", "Covid Baslangic", "Covid Bitis"};
		plazma = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(plazma);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(40, 115, 850, 312);
		contentPane.add(sp);
		
		JButton btnNewButton = new JButton("Fetch");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag=false;
				String temp="";
				String and = " AND ";
				fetch_index.clear();
				if(textField_0.getText().length()!=0) {
					temp+="ptc="+"'"+textField_0.getText()+"'";
					fetch_index.add(textField_0.getText());
					flag=true;}
				else fetch_index.add("NULL");
				if(textField_1.getText().length()!=0) {
					if(flag==true)
						temp+=and;
					temp+="tedaviTipi="+"'"+textField_1.getText()+"'";
					fetch_index.add(textField_1.getText());
					flag=true;
				}
				else fetch_index.add("NULL");
				if(textField_2.getText().length()!=0) {
					if(flag==true)
						temp+=and;
					temp+="iyilesentc="+"'"+textField_2.getText()+"'";
					fetch_index.add(textField_2.getText());
					flag=true;	
				}
				else fetch_index.add("NULL");
				if(textField_3.getText().length()!=0) {
					if(flag==true)
						temp+=and;
					temp+="covidbas="+"'"+textField_3.getText()+"'";
					fetch_index.add(textField_3.getText());
					flag=true;}
				else fetch_index.add("NULL");
				if(textField_4.getText().length()!=0) {
					if(flag==true)
						temp+=and;
					temp+="covidbit="+"'"+textField_4.getText()+"'";
					fetch_index.add(textField_4.getText());
					flag=true;}
				else fetch_index.add("NULL");	
				
				String query;
				if(flag==true)
					query="select * from plazma "+"where "+temp;
				else{
					query="select * from plazma ";
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
						String ptc = r.getString(1);
						temp2 +=r.getString(2)+" ";
						String tedaviTipi = r.getString(2);
						temp2 +=r.getString(3)+" ";
						String iyilesentc = r.getString(3);
						temp2 +=r.getString(4)+" ";
						String covidbas = r.getString(4);
						temp2 +=r.getString(5)+" ";
						String covidbit = r.getString(5);

					    Object[] row ={ptc, tedaviTipi, iyilesentc, covidbas, covidbit};
					    plazma.addRow(row);
						
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
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp="DELETE FROM plazma ";
				boolean flag=false;
				String and=" AND ";
				String query="";
				if(textField_0.getText().length()!=0) {
					query+="ptc="+"'"+textField_0.getText()+"'";
					flag=true;}
				if(textField_1.getText().length()!=0) {
					if(flag==true)
						query+=and;
					query+="tedaviTipi="+"'"+textField_1.getText()+"'";
					flag=true;
				}
				if(textField_2.getText().length()!=0) {
					if(flag==true)
						query+=and;
					query+="iyilesentc="+"'"+textField_2.getText()+"'";
					flag=true;	
				}
				if(textField_3.getText().length()!=0) {
					if(flag==true)
						query+=and;
					query+="covidbas="+"'"+textField_3.getText()+"'";
					flag=true;}
				if(textField_4.getText().length()!=0) {
					if(flag==true)
						query+=and;
					query+="covidbit="+"'"+textField_4.getText()+"'";
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
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp="";
				temp="insert into plazma values (";
				if(textField_0.getText().length()!=0) {
					temp+="'"+textField_0.getText()+"'"+",";}
				if(textField_1.getText().length()!=0) {
					temp+="'"+textField_1.getText()+"'"+",";}
				if(textField_2.getText().length()!=0) {
					temp+="'"+textField_2.getText()+"'"+",";}
				if(textField_3.getText().length()!=0) {
					temp+="'"+textField_3.getText()+"'"+",";}
				if(textField_4.getText().length()!=0) {
					temp+="'"+textField_4.getText()+"'";}
				temp+=");";
				java.sql.Statement s=null;
		
				try {
					s = conn.createStatement(); 
					s.executeQuery(temp);
					conn.setAutoCommit(false);
					conn.commit();
					s.close();
				} catch (SQLException e1) {/*
					
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
					}*/
					e1.printStackTrace();
				}				
			}
		});
		btnNewButton_2.setBounds(921, 166, 138, 66);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("UPDATE");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "UPDATE plazma SET ";
				boolean flag = false;
				if(textField_5.getText().length()!=0) {
					query += (fields.get(0)+"="+"'"+textField_5.getText()+"'"+",");
					flag=true;
				}
				if(textField_6.getText().length()!=0) {
					query += (fields.get(1)+"="+"'"+textField_6.getText()+"'"+",");
					flag=true;
				}
				if(textField_7.getText().length()!=0) {
					query += (fields.get(2)+"="+"'"+textField_7.getText()+"'"+",");
					flag=true;
				}
				if(textField_8.getText().length()!=0) {
					query += (fields.get(3)+"="+"'"+textField_8.getText()+"'"+",");
					flag=true;
				}
				if(textField_9.getText().length()!=0) {
					query += (fields.get(4)+"="+"'"+textField_9.getText()+"'"+",");
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
					System.out.println(query);
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
		btnNewButton_4.setBounds(996, 531, 63, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("RESET");
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
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
			}
		});
		btnNewButton_5.setBounds(921, 301, 138, 42);
		contentPane.add(btnNewButton_5);
		
		textField_0 = new JTextField();
		textField_0.setBounds(70, 58, 92, 20);
		contentPane.add(textField_0);
		textField_0.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(250, 58, 92, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(416, 58, 104, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(569, 58, 92, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(732, 58, 92, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(70, 487, 92, 20);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(250, 487, 92, 20);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(416, 487, 104, 20);
		contentPane.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(569, 487, 92, 20);
		contentPane.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(732, 487, 92, 20);
		contentPane.add(textField_9);
		
		lblNewLabel_1 = new JLabel("ptc");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(70, 23, 92, 20);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("tedaviTipi");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(250, 23, 92, 20);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("iyilesentc");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(416, 23, 92, 20);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("covidbas");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(569, 23, 92, 20);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("covidbit");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(732, 23, 92, 20);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_9 = new JLabel("ptc");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_9.setBounds(70, 444, 92, 20);
		contentPane.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("tedaviTipi");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_10.setBounds(250, 444, 92, 20);
		contentPane.add(lblNewLabel_10);
		
		lblNewLabel_11 = new JLabel("iyilesentc");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_11.setBounds(416, 444, 92, 20);
		contentPane.add(lblNewLabel_11);
		
		lblNewLabel_12 = new JLabel("covidbas");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_12.setBounds(569, 444, 92, 20);
		contentPane.add(lblNewLabel_12);
		
		lblNewLabel_13 = new JLabel("covidbit");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_13.setBounds(732, 444, 92, 20);
		contentPane.add(lblNewLabel_13);
		
		lblNewLabel_6 = new JLabel("Update i\u015Flemi \u00F6ncesinde");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(900, 363, 159, 24);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("g\u00FCncellenmek istenen verilerin");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(900, 386, 172, 35);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("Fetch edilmesi gerekir!");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(900, 419, 159, 24);
		contentPane.add(lblNewLabel_8);
		
		chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBackground(Color.LIGHT_GRAY);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_3.setEnabled(true);
				chckbxNewCheckBox.setVisible(false);
				lblNewLabel_6.setVisible(false);
				lblNewLabel_7.setVisible(false);
				lblNewLabel_8.setVisible(false);
			}
		});
		chckbxNewCheckBox.setBounds(1033, 427, 31, 23);
		contentPane.add(chckbxNewCheckBox);
	}
}
