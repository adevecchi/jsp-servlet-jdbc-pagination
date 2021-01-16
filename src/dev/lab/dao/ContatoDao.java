package dev.lab.dao;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dev.lab.models.Contato;

public class ContatoDao {
	
	private static final String url = "jdbc:mysql://localhost:3306/contatos_jsp_servlet?useSSL=false&useTimezone=true&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
	//private static final String url = "jdbc:mysql://localhost:3306/contatos_jsp_servlet?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
	private static final String user = "root";
	private static final String pass = "devecchi";
	
	private static ContatoDao instance = null;
	
	private Connection conn = null;
	private PreparedStatement pstmtGet;
	private PreparedStatement pstmtAll;
	private PreparedStatement pstmtIns;
	private PreparedStatement pstmtUpd;
	private PreparedStatement pstmtDel;
	private PreparedStatement pstmtTotal;
	
	private int totalRecords;
	
	private ContatoDao() throws SQLException {
		String get = "select * from contatos where id = ?";
		//String all = "select * from contatos";
		String all = "select sql_calc_found_rows * from contatos limit ?, ?";
		String ins = "insert into contatos (nome, email, telefone) values (?, ?, ?)";
		String upd = "update contatos set nome = ?, email = ?, telefone = ? where id = ?";
		String del = "delete from contatos where id = ?";
		String total = "select found_rows()";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			
			pstmtGet = conn.prepareStatement(get);
			pstmtAll = conn.prepareStatement(all);
			pstmtIns = conn.prepareStatement(ins);
			pstmtUpd = conn.prepareStatement(upd);
			pstmtDel = conn.prepareStatement(del);
			pstmtTotal = conn.prepareStatement(total);
		}
		catch (ClassNotFoundException e) {
			throw new SQLException("No driver available");
		}
	}
	
	public static ContatoDao getInstance() throws SQLException {
		if (instance == null)
			instance = new ContatoDao();
		return instance;
	}
	
	public List<Contato> findAll(int offset, int records) throws SQLException {
		ResultSet rs;
		Contato contato;
		List<Contato> contatos = new ArrayList<Contato>();
		
		synchronized (pstmtAll) {
			pstmtAll.clearParameters();
			pstmtAll.setInt(1, offset);
			pstmtAll.setInt(2, records);
			rs = pstmtAll.executeQuery();
		}
		
		while (rs.next()) {
			contato = new Contato();
			contato.setId(rs.getInt("id"));
			contato.setNome(rs.getString("nome"));
			contato.setEmail(rs.getString("email"));
			contato.setTelefone(rs.getString("telefone"));
			
			contatos.add(contato);
		}
		
		rs.close();
		
		synchronized (pstmtTotal) {
			rs = pstmtTotal.executeQuery();
		}
		
		if (rs.next()) 
			this.totalRecords = rs.getInt(1);
		
		return contatos;
	}
	
	public Contato find(int id) throws SQLException {
		ResultSet rs;
		Contato contato = null;
		
		synchronized (pstmtGet) {
			pstmtGet.setInt(1, id);
			rs = pstmtGet.executeQuery();
		}
		
		if (rs.next()) {
			contato = new Contato();
			contato.setId(id);
			contato.setNome(rs.getString("nome"));
			contato.setEmail(rs.getString("email"));
			contato.setTelefone(rs.getString("telefone"));
		}
		
		return contato;
	}
	
	public boolean save(Contato contato) throws SQLException {
		boolean rowAffected = false;
		
		synchronized (pstmtIns) {
			pstmtIns.clearParameters();
			pstmtIns.setString(1, contato.getNome());
			pstmtIns.setString(2, contato.getEmail());
			pstmtIns.setString(3, contato.getTelefone());
			
			rowAffected = pstmtIns.executeUpdate() > 0;
		}
		
		return rowAffected;
	}
	
	public boolean update(Contato contato) throws SQLException {
		boolean rowAffected = false;
		
		synchronized (pstmtUpd) {
			pstmtUpd.clearParameters();
			pstmtUpd.setString(1, contato.getNome());
			pstmtUpd.setString(2, contato.getEmail());
			pstmtUpd.setString(3, contato.getTelefone());
			pstmtUpd.setInt(4, contato.getId());
			
			rowAffected = pstmtUpd.executeUpdate() > 0;
		}
		
		return rowAffected;
	}
	
	public boolean delete(int id) throws SQLException {
		boolean rowAffected = false;
		
		synchronized (pstmtDel) {
			pstmtDel.clearParameters();
			pstmtDel.setInt(1, id);
			
			rowAffected = pstmtDel.executeUpdate() > 0;
		}
		
		return rowAffected;
	}
	
	public int getTotalRecords() {
		return totalRecords;
	}
	
	public void destroy() {
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {}
		}
	}

}
