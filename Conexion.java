import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class Conexion {
String bd="ASUMA";
String url="jdbc:mysql://database-pevau.cobadwnzalab.eu-central-1.rds.amazonaws.com:3306/";
String user="grupo04";
String password="45DG7pKxGyvmsxd5";
String driver="com.mysql.cj.jdbc.Driver";
Connection cx;
PreparedStatement ps;
ResultSet rs;

public Conexion() {
	
}

public Connection conectar() {
	try {
		Class.forName(driver);
		cx=DriverManager.getConnection(url, user, password);
		System.out.println("Se conectó a BD " + bd);
	} catch (ClassNotFoundException | SQLException ex) {
		System.out.println("No se conectó a BD " + bd);
		Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
	} 
	return cx;
	
}

public void desconectar() {
	try {
		cx.close();
	} catch (SQLException ex) {
		Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
	}
}

public ArrayList<LoginGetSet> login(String usuario, String clave){
	ArrayList<LoginGetSet> res=new ArrayList<>();
	try {
		ps=cx.prepareStatement("SELECT * FROM grupo04DB.Usuarios WHERE Usuario=? AND Password=? ");
		ps.setString(1,usuario);
		ps.setString(2,clave);
		rs=ps.executeQuery();
		while(rs.next()) {
			LoginGetSet lo=new LoginGetSet();
			lo.setNombre(rs.getString("Usuario"));
			lo.setPassword(rs.getString("Password"));
			res.add(lo);
		}
		if(res.isEmpty()) {
			System.out.println("Acceso denegado");
		} else {
			System.out.println("Login existoso");
		}
	} catch(Exception e) {
		
	}
	return res;
}

public static void main(String[] args) {
	Conexion conexion=new Conexion();
	conexion.conectar();
}
}