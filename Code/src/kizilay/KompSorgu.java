package kizilay;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.Font;

public class KompSorgu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private TextField textField;
	private JButton btnNewButton_1;
	private JButton btnNewButton;
	private Choice choice;
	private TextField textField_1;
	private Label label;
	private Label label_1;
	private JButton btnNewButton_2;
	private DefaultTableModel basvuran;
	private DefaultTableModel islem;
	private DefaultTableModel stok;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KompSorgu frame = new KompSorgu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void stokbul(Connection conn, String query,DefaultTableModel stok,String Kangr) {
		java.sql.Statement s;
		try {
			
			s = conn.createStatement();
			ResultSet r = s.executeQuery(query);
			
			while(r.next()) {
				String stoksay = r.getString(1);
				Object[] row = {Kangr,stoksay};
				stok.addRow(row);
			}
			
		}
	 catch (SQLException e1) {
		e1.printStackTrace();
	}
	}
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public KompSorgu() throws SQLException{
		setTitle("Kompleks Sorgular");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\TULPAR\\Desktop\\icons\\tkizilay.png"));
		String user="postgres";
		String password="1801";
		Connection conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/kizilaytest",user,password);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 620);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNewButton = new JButton("Sorgu Getir");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(choice.getSelectedIndex()==2) {
					Object[] columnNames = {"Ad", "Soyad", "TC", "Dogum Tarihi", "Cinsiyet", "Kan Grubu", "Tarih","Covid","Odul"};
					basvuran = new DefaultTableModel(columnNames, 0);
					JTable table = new JTable(basvuran);
					JScrollPane sp = new JScrollPane(table);
					sp.setBounds(28, 37, 850, 312);
					contentPane.add(sp);
					
					String query;
					query = "select b.* from basvuran b, islem i where b.tc=i.islemtc and islemtipi='Kan Verme' group by b.tc having count(*)>9 order by count(*) desc";
					
					java.sql.Statement s;
					
					try {
						s = conn.createStatement();
						ResultSet r=s.executeQuery(query);
						
						while(r.next()) {
							String ad = r.getString(1);
							String soyad = r.getString(2);
							String tc = r.getString(3);
							String dogumT = r.getString(4);
							String cinsiyet = r.getString(5);
							String kang = r.getString(6);
							String tarih = r.getString(7);
							String covid = r.getString(8);
							String odul = r.getString(9);

						    Object[] row ={ad, soyad, tc, dogumT, cinsiyet, kang, tarih, covid,odul};
						    basvuran.addRow(row);
							
					}
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
					}
				}
				else if(choice.getSelectedIndex()==0 || choice.getSelectedIndex()==1) {
					
					Object[] columnNames = {"Ýslem ID", "Ýslem Tipi", "Ýslem TC", "Kan Grubu", "Kan Aldigi TC", "SSN", "tarih"};
					islem = new DefaultTableModel(columnNames, 0);
					JTable table = new JTable(islem);
					JScrollPane sp = new JScrollPane(table);
					sp.setBounds(28, 37, 850, 312);
					contentPane.add(sp);
					
					String query;
					query = "SELECT * FROM islem WHERE tarih > '" + textField.getText() + "'" + " and " + "tarih < '"+ textField_1.getText() +"'";
					
					if(choice.getSelectedIndex()==0)
						query += " and islemtipi='Kan Verme'";
					else
						query += " and islemtipi='Kan Alma'";
					
					java.sql.Statement s;
					try {
						s = conn.createStatement();
						
						ResultSet r=s.executeQuery(query);
						
						while(r.next()) {
							String islemid = r.getString(1);
							String islemtipi = r.getString(2);
							String islemtc = r.getString(3);
							String kangr = r.getString(4);
							String kanaldigitc = r.getString(5);
							String issn = r.getString(6);
							String tarih = r.getString(7);

						    Object[] row ={islemid, islemtipi, islemtc, kangr, kanaldigitc, issn, tarih};
						    islem.addRow(row);
							
						    query = "";
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				else if(choice.getSelectedIndex() == 4) {
					Object[] columnNames = {"Kan Grubu","Unite Sayisi"};
					stok = new DefaultTableModel(columnNames, 0);
					JTable table = new JTable(stok);
					JScrollPane sp = new JScrollPane(table);
					sp.setBounds(28, 37, 850, 312);
					contentPane.add(sp);
					
					String query;
					if (textField.getText().length() == 0) {
						stokbul(conn,"select unitebul('A+');",stok,"A+");
						stokbul(conn,"select unitebul('A-');",stok,"A-");
						stokbul(conn,"select unitebul('B+');",stok,"B+");
						stokbul(conn,"select unitebul('B-');",stok,"B-");
						stokbul(conn,"select unitebul('AB+');",stok,"AB+");
						stokbul(conn,"select unitebul('AB-');",stok,"AB-");
						stokbul(conn,"select unitebul('0+');",stok,"0+");
						stokbul(conn,"select unitebul('0-');",stok,"0-");
					}
					else {
						query = "select unitebul('"+textField.getText()+"');";
						stokbul(conn,query,stok,textField.getText());
					}	
				}
				else if(choice.getSelectedIndex() == 3) {
					Object[] columnNames = {"Ad", "Soyad", "TC", "Dogum Tarihi", "Cinsiyet", "Kan Grubu", "Tarih","Covid","Odul"};
					basvuran = new DefaultTableModel(columnNames, 0);
					JTable table = new JTable(basvuran);
					JScrollPane sp = new JScrollPane(table);
					sp.setBounds(28, 37, 850, 312);
					contentPane.add(sp);
					
					String query = "select b.* from basvuran b, islem aldi, islem verdi where aldi.islemtc=b.tc and aldi.islemtc=verdi.islemtc and aldi.islemtipi!=verdi.islemtipi group by b.tc";
					
					java.sql.Statement s;
					try {
						s = conn.createStatement();
						ResultSet r = s.executeQuery(query);
						
						while(r.next()) {
							String ad = r.getString(1);
							String soyad = r.getString(2);
							String tc = r.getString(3);
							String dogumT = r.getString(4);
							String cinsiyet = r.getString(5);
							String kang = r.getString(6);
							String tarih = r.getString(7);
							String covid = r.getString(8);
							String odul = r.getString(9);
							
							Object[] row ={ad, soyad, tc, dogumT, cinsiyet, kang, tarih, covid,odul};
							basvuran.addRow(row);
							query = "";
						}
					}
				 catch (SQLException e1) {
					e1.printStackTrace();
				}
					
				}
				else {
					Object[] columnNames = {"Ad","Soyad","TC"};
					basvuran = new DefaultTableModel(columnNames, 0);
					JTable table = new JTable(basvuran);
					JScrollPane sp = new JScrollPane(table);
					sp.setBounds(28, 37, 850, 312);
					contentPane.add(sp);
					
					String query="SELECT TC, ad, soyad FROM COVIDLILER UNION SELECT TC, ad, soyad FROM BASVURAN WHERE tarih>'2020-01-01' and tarih<'2021-01-01'";
					
					java.sql.Statement s;
					
					try {
						s = conn.createStatement();
						ResultSet r=s.executeQuery(query);
						
						while(r.next()) {
							String ad = r.getString(1);
							String soyad = r.getString(2);
							String tc = r.getString(3);

						    Object[] row ={ad, soyad, tc};
						    basvuran.addRow(row);
							
					}
					} catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(717, 458, 159, 42);
		contentPane.add(btnNewButton);
		btnNewButton.setEnabled(false);
		
		textField = new TextField();
		textField.setBounds(28, 440, 328, 80);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton_1 = new JButton("X");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(791, 532, 85, 21);
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.setForeground(Color.WHITE);
		contentPane.add(btnNewButton_1);
		
		choice = new Choice();
		choice.setBounds(28, 386, 848, 50);
		contentPane.add(choice);
		
		textField_1 = new TextField();
		textField_1.setBounds(381, 439, 290, 81);
		contentPane.add(textField_1);
		
		label = new Label("X Parametresi");
		label.setBounds(28, 413, 328, 21);
		contentPane.add(label);
		
		label_1 = new Label("Y Parametresi");
		label_1.setBounds(381, 412, 290, 21);
		contentPane.add(label_1);
		
		btnNewButton_2 = new JButton("Se\u00E7");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				btnNewButton.setEnabled(true);
				if (choice.getSelectedIndex() == 0 || choice.getSelectedIndex() == 1) {
					label.setText("X Tarihini Giriniz");
					label_1.setText("Y Tarihini Giriniz");
					textField_1.setEditable(true);
					textField.setEditable(true);
				}
				else if (choice.getSelectedIndex() == 2 || choice.getSelectedIndex() == 3 || choice.getSelectedIndex() == 5) {
					label.setText("");
					textField_1.setEditable(false);
					textField.setEditable(false);
					label_1.setText("");
				}
				else{
					label.setText("X Kan Grubunu Giriniz");
					textField_1.setEditable(false);
					textField.setEditable(true);
					label_1.setText("");
				}
			}
		});
		btnNewButton_2.setBounds(778, 410, 85, 21);
		contentPane.add(btnNewButton_2);
		choice.add("X Tarihinden Y Tarihine Kadar Kan Verenler");
		choice.add("X Tarihinden Y Tarihine Kadar Kan Alanlar");
		choice.add("Madalya Alanlar");
		choice.add("Hem Kan Alýp Hem Kan Veren Kiþiler");
		choice.add("X Kan Grubundaki Ünite Sayýsý (Boþ Býrakýlýrsa Bütün Kan Gruplarý Gösterilecek)");	
		choice.add("Covid geçirmiþ genç-yetiþkin veya 2020 yýlýnda Kýzýlay'a baþvurmuþ kiþilerin ad, soyad ve kimlik numaralarýný listeleyiniz.");
		
		
	}
}
