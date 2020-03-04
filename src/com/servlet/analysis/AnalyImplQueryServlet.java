package com.servlet.analysis;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DepartmentDAO;
import com.dao.OperecordDAO;
import com.dao.ResproDAO;
import com.dao.impl.DepartmentDAOImpl;
import com.dao.impl.OperecordDAOImpl;
import com.dao.impl.ResproDAOImpl;
import com.domain.Department;
import com.domain.Operecord;
import com.domain.Respro;

public class AnalyImplQueryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AnalyImplQueryServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		//String type = (String)request.getSession().getAttribute("proleixing");
		String proname = request.getParameter("proname");
		String prodep = request.getParameter("prodep");
		String apptime1 = request.getParameter("apptime1");
		String apptime2 = request.getParameter("apptime2");
		String type = request.getParameter("protype");
		
		
		Department dep = (Department)request.getSession().getAttribute("userinfo");
		int role = dep.getDeprole();
		int depnum = dep.getDepnum();
		String sql = null;
		int protype = -1;
		try {
			
			protype=Integer.parseInt(type);
			//sql+=" and DATEDIFF(task.end_time,now())<=5 and DATEDIFF(task.end_time,now())>=0 ";
			 sql="select * from respro,department where pro_con=1 and respro.prodep=department.depnum ";
				
				
					
			 	
			 if(proname!=null && !"".equals(proname)){
						sql += " and proname like '%"+proname+"%' ";
					}
					
					if(prodep!=null && !"".equals(prodep)){
						sql += " and depname like '%"+prodep+"%' ";
					}
					
					if(protype!=0)
						sql += " and protype = '"+protype+"' ";
					if(!apptime1.equals(""))
					sql+=" and DATEDIFF('"+apptime1+"',respro.apptime)<=0 ";
					if(!apptime2.equals(""))
						sql+=" and DATEDIFF(respro.apptime,'"+apptime2+"')<=0 ";
					
					
					if(role==2)
						sql+=" and resman = '"+depnum+"' ";
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(sql);
			ResproDAO dao = new ResproDAOImpl();
			ArrayList<Respro> listall = new ArrayList<Respro>();
			listall = dao.getAllpro(sql);
			ArrayList<String> liststr = new ArrayList<String>();
			int i;
			DepartmentDAO dao2 = new DepartmentDAOImpl();
			for(i=0;i<listall.size();i++)
			{
				
				Respro pro = new Respro();
				pro = listall.get(i);
				liststr.add(dao2.getdep(pro.getProdep()).getDepname());
			}
			
			
			Operecord ope = new Operecord();
			//Department dep = (Department)request.getSession().getAttribute("userinfo");
			ope.setOpeman(dep.getDepnum());
			ope.setOpecontent("��ѯʵʩ��Ŀ");
			
			Date now = new Date();//��ȡ��ǰʱ��
		    DateFormat d2 = DateFormat.getDateTimeInstance();
		    String str = d2.format(now);
		    ope.setOpetime(str);
		    ope.setOpetype(4);
		    OperecordDAO dao3  = new OperecordDAOImpl();
		    dao3.addope(ope);
			
		    
		    
		    int pageall = 1;
			int page = 1;
			List listx = null;
			List liststrx =null;
			
			if (listall != null && listall.size()>=5) {
				// ��ҳ��

				pageall = (int) Math.ceil((double) listall.size() / 5); // ���ڷ�ҳ��ʾ
				System.out.println(pageall);
				listx = listall.subList((page - 1) * 5, page * 5);
				liststrx = liststr.subList((page - 1) * 5, page * 5);
				
			}
			else if(listall.size() >= 1){
				listx = listall.subList((page - 1) * 5, listall.size());
				liststrx = liststr.subList((page - 1) * 5, listall.size());
				
			}
			
			request.getSession().setAttribute("listx", listx);
			request.getSession().setAttribute("liststrx", liststrx);
			
			request.getSession().setAttribute("pageall", pageall);
			request.getSession().setAttribute("curpage", page);
			//request.getSession().setAttribute("listall", listall);
			request.getSession().setAttribute("listall", listall);
			request.getSession().setAttribute("liststr", liststr );
		    
		    
		    
			
			request.getRequestDispatcher("/analysis/impl.jsp").forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
