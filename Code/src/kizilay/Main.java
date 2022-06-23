package kizilay;												// Çalışana emekli biri girince maaşı null olmalı, trigger la da yapılabilir olmazsa kodda optinal ;)
																// **update işleminde fetch leyip sonra güncellemek gerekir
import java.awt.EventQueue;										// sequence 1 den başlamalı, 13'ten başlıyo
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
					frame.setTitle("K�z�lay");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\TULPAR\\Desktop\\icons\\tkizilay.png"));
		setTitle("K\u0131z\u0131lay");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 607);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("\u00C7al\u0131\u015Fanlar Database");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalisanDB calisan;
				try {
					calisan = new CalisanDB();
					calisan.setVisible(true);
					calisan.setTitle("Calisanlar");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(10, 54, 195, 66);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Islem Database");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IslemDB islem;
				try {
					islem = new IslemDB();
					islem.setVisible(true);
					islem.setTitle("Islemler");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(10, 397, 195, 66);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Plazma database");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlazmaDb plazma;
				try {
					plazma = new PlazmaDb();
					plazma.setVisible(true);
					plazma.setTitle("Plazma");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(864, 54, 195, 66);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Ba\u015Fvuran Database");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				basvuranDB basvuran;
				try {
					basvuran = new basvuranDB();
					basvuran.setVisible(true);
					basvuran.setTitle("Basvuranlar");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_3.setBounds(864, 397, 195, 66);
		getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("K\u0131z\u0131lay Database Sistemi");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(267, 80, 543, 181);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_4 = new JButton("Kompleks Sorgular");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KompSorgu komp;
				try {
					komp = new KompSorgu();
					komp.setVisible(true);
					komp.setTitle("Kompleks Sorgular");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_4.setBounds(267, 333, 543, 72);
		getContentPane().add(btnNewButton_4);
	}
}
