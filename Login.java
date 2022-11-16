import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField txtusuario;
	private JPasswordField txtcontraseña;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 622, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("INICIO DE SESIÓN");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel.setBounds(198, 11, 221, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("USUARIO");
		lblNewLabel_1.setBounds(97, 65, 48, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CONTRASEÑA");
		lblNewLabel_2.setBounds(97, 104, 75, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		txtusuario = new JTextField();
		txtusuario.setBounds(198, 62, 221, 20);
		frame.getContentPane().add(txtusuario);
		txtusuario.setColumns(10);
		
		txtcontraseña = new JPasswordField();
		txtcontraseña.setBounds(198, 101, 221, 20);
		frame.getContentPane().add(txtcontraseña);
		
		JButton btnIngresar = new JButton("INGRESAR");
		
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario=txtusuario.getText();
				String clave = new String(txtcontraseña.getPassword());
				Conexion co=new Conexion();
				co.conectar();
				ArrayList<LoginGetSet> re=co.login(usuario, clave);
				String datos[] = new String[2];
				for(LoginGetSet lo:re) {
					datos[0]=lo.getNombre();
					datos[1]=lo.getPassword();
				}
				if(re.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Acceso denegado");
				} else {
					Inicio ini=new Inicio();
					frame.dispose();
					ini.frame.setVisible(true);
				}
			}
		});
		btnIngresar.setBounds(330, 142, 89, 23);
		frame.getContentPane().add(btnIngresar);
	}
}
