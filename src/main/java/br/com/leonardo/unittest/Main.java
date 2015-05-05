package br.com.leonardo.unittest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


public class Main extends HttpServlet{
    
	public static void main( String[] args ) {
		try{
			//Server server = new Server(Integer.valueOf(System.getenv("PORT")));
			Server server = new Server(9000);
			ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
			context.setContextPath("/");
			server.setHandler(context);
			context.addServlet(new ServletHolder(new Main()),"/*");
			server.start();
			server.join();
			
			System.out.println("Subi na porta " + System.getenv("PORT"));
	    
		}catch(Exception e){
			System.out.println("Erro = [" + e.getMessage() + "]");
		}
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.getWriter().print("Ola Mundo Unitario");
		
	}
	
	
	
}
