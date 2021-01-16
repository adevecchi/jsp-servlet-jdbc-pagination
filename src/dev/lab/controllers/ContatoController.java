package dev.lab.controllers;

import java.util.List;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dev.lab.models.Contato;
import dev.lab.dao.ContatoDao;

@WebServlet("/contatos")
public class ContatoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("UTF8");
		String action = req.getParameter("cmd") != null ? req.getParameter("cmd") : "list";
		
		try {
			switch (action) {
				case "new":
					create(req, res);
					break;
				case "post":
					save(req, res);
					break;
				case "edit":
					edit(req, res);
					break;
				case "put":
					update(req, res);
					break;
				case "del":
					delete(req, res);
					break;
				default:
					list(req, res);
					break;
			}
		}
		catch (SQLException e) {
			throw new ServletException(e);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doGet(req, res);
	}
	
	private void list(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException, ServletException {
		int page = 1;
		int numRecords = 5;
		
		if (req.getParameter("page") != null)
			page = Integer.parseInt(req.getParameter("page"));
		
		ContatoDao dao = ContatoDao.getInstance();
		
		List<Contato> contatos = dao.findAll((page - 1) * numRecords, numRecords);
		
		int totalRecords = dao.getTotalRecords();
		int numPages = (int) Math.ceil(totalRecords * 1.0 / numRecords);
		
		req.setAttribute("contatos", contatos);
		req.setAttribute("numPages", numPages);
		req.setAttribute("currentPage", page);
		
		RequestDispatcher rd = req.getRequestDispatcher("contato/list.jsp");
		rd.forward(req, res);
	}
	
	private void create(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		session.setAttribute("referer", req.getHeader("referer"));
		req.setAttribute("referer", req.getHeader("referer"));
		RequestDispatcher rd = req.getRequestDispatcher("contato/form.jsp");
		rd.forward(req, res);
	}
	
	private void save(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException, ServletException {
		Contato contato = new Contato();
		contato.setNome(req.getParameter("nome"));
		contato.setEmail(req.getParameter("email"));
		contato.setTelefone(req.getParameter("telefone"));
		ContatoDao.getInstance().save(contato);
		HttpSession session = req.getSession(false);
		res.sendRedirect((String) session.getAttribute("referer"));
	}
	
	private void edit(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
		Contato contato = ContatoDao.getInstance().find(id);
		HttpSession session = req.getSession();
		session.setAttribute("referer", req.getHeader("referer"));
		req.setAttribute("contato", contato);
		RequestDispatcher rd = req.getRequestDispatcher("contato/form.jsp");
		rd.forward(req, res);
	}
	
	private void update(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
		Contato contato = new Contato();
		contato.setId(id);
		contato.setNome(req.getParameter("nome"));
		contato.setEmail(req.getParameter("email"));
		contato.setTelefone(req.getParameter("telefone"));
		ContatoDao.getInstance().update(contato);
		HttpSession session = req.getSession(false);
		res.sendRedirect((String) session.getAttribute("referer"));
	}
	
	private void delete(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
		ContatoDao.getInstance().delete(id);
		res.sendRedirect("contatos");
	}

}
