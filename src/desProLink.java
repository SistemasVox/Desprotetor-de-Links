import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class desProLink extends JFrame {

	private JPanel contentPane;
	private ButtonGroup rds;
	private ArrayList<JRadioButton> buttons = new ArrayList<>();
	private JTextArea txt1;
	private JTextArea txt2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					desProLink frame = new desProLink();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public desProLink() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Desprotetor de Links");
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 11, 707, 30);
		contentPane.add(lblTitulo);

		JRadioButton rd1 = new JRadioButton("Rosa");
		rd1.setHorizontalAlignment(SwingConstants.LEFT);
		rd1.setFont(new Font("Tahoma", Font.BOLD, 12));
		rd1.setBounds(10, 60, 109, 23);
		contentPane.add(rd1);

		JRadioButton rd3 = new JRadioButton("Branco");
		rd3.setHorizontalAlignment(SwingConstants.LEFT);
		rd3.setFont(new Font("Tahoma", Font.BOLD, 12));
		rd3.setBounds(232, 60, 109, 23);
		contentPane.add(rd3);

		JRadioButton rd2 = new JRadioButton("Verde");
		rd2.setHorizontalAlignment(SwingConstants.LEFT);
		rd2.setFont(new Font("Tahoma", Font.BOLD, 12));
		rd2.setBounds(121, 60, 109, 23);
		contentPane.add(rd2);

		JLabel lblDes = new JLabel("Link Protegido");
		lblDes.setHorizontalAlignment(SwingConstants.CENTER);
		lblDes.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDes.setBounds(10, 90, 707, 30);
		contentPane.add(lblDes);

		txt1 = new JTextArea();
		txt1.setWrapStyleWord(true);
		txt1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				try {
					String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard()
							.getData(DataFlavor.stringFlavor);
					txt1.setText(data);
				} catch (Exception e2) {
					txt1.setText(e2.getMessage());
				}

			}
		});
		txt1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		txt1.setLineWrap(true);
		txt1.setBounds(10, 131, 707, 39);
		contentPane.add(txt1);

		JLabel lblLivre = new JLabel("Link Livre");
		lblLivre.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivre.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblLivre.setBounds(10, 181, 707, 30);
		contentPane.add(lblLivre);

		txt2 = new JTextArea();
		txt2.setWrapStyleWord(true);
		txt2.setLineWrap(true);
		txt2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		txt2.setBounds(10, 222, 707, 39);
		contentPane.add(txt2);

		JButton btnLimpar = new JButton("Desproteger");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int c = 0;
				for (int i = 0; i < buttons.size(); i++) {
					if (buttons.get(i).isSelected()) {
						c++;
					}
				}
				if (c > 0) {
					for (int i = 0; i < buttons.size(); i++) {
						if (buttons.get(i).isSelected()) {
							desproteger(i);
						}
					}
				} else {
					txt2.setText("Selecione a cor!!!!!!!!");
				}
			}
		});
		btnLimpar.setFont(new Font("X-Files", Font.BOLD | Font.ITALIC, 20));
		btnLimpar.setBounds(10, 272, 170, 120);
		contentPane.add(btnLimpar);

		JButton btnCrtlc = new JButton("Ctrl + C");
		btnCrtlc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(txt2.getText().trim());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}
		});
		btnCrtlc.setFont(new Font("X-Files", Font.BOLD | Font.ITALIC, 20));
		btnCrtlc.setBounds(190, 272, 170, 120);
		contentPane.add(btnCrtlc);

		rds = new ButtonGroup();
		rds.add(rd1);
		rds.add(rd2);
		rds.add(rd3);
		buttons.add(rd1);
		buttons.add(rd2);
		buttons.add(rd3);

		JButton btnLimpar_1 = new JButton("Limpar");
		btnLimpar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt1.setText("");
				txt2.setText("");
			}
		});
		btnLimpar_1.setFont(new Font("X-Files", Font.BOLD | Font.ITALIC, 20));
		btnLimpar_1.setBounds(370, 272, 170, 120);
		contentPane.add(btnLimpar_1);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(NORMAL);
			}
		});
		btnSair.setFont(new Font("X-Files", Font.BOLD | Font.ITALIC, 20));
		btnSair.setBounds(547, 272, 170, 120);
		contentPane.add(btnSair);
		attJrBtts();
	}

	private void attJrBtts() {
		try {
			List<String> links = Files.readAllLines(Paths.get("links.txt"));
			for (int i = 0; i < links.size(); i++) {
				String[] tag = links.get(i).split("->");
				buttons.get(i).setText(tag[0]);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}

	protected void desproteger(int i) {
		try {
			List<String> links = Files.readAllLines(Paths.get("links.txt"));
			String[] tag = links.get(i).split("->");
			String[] s_f = txt1.getText().trim().split("/");
			txt2.setText(tag[tag.length - 1] + s_f[s_f.length - 1]);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
