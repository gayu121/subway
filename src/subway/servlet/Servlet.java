package subway.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import subway.bean.Subway;
import subway.dao.*;
/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        request.setCharacterEncoding("UTF-8");
		String start=request.getParameter("start");
		String end=request.getParameter("end");
		Dao dao=new Dao();
		System.out.println(start);
		System.out.println(end);
		List<Subway> list_1=dao.list("first");
		List<Subway> list_2=dao.list("second");
		List<Subway> list_3=dao.list("third");
		List<Subway> list_4=dao.list("fourth");
		List<Subway> list_5=dao.list("fifth");
		List<Subway> list_6=dao.list("sixth");
		List<Subway> change_1=dao.findChange("first");
		List<Subway> change_2=dao.findChange("second");
		List<Subway> change_3=dao.findChange("third");
		List<Subway> change_4=dao.findChange("fourth");
		List<Subway> change_5=dao.findChange("fifth");
		List<Subway> change_6=dao.findChange("sixth");
		List<Subway> listGather=dao.Gather(list_1,list_2,list_3,list_4,list_5,list_6);
		List<Subway> listchange=dao.Gather(change_1,change_2,change_3,change_4,change_5,change_6);
		List<Subway> Startlist=dao.CheckStartEnd(start,listGather);
		List<Subway> Endlist=dao.CheckStartEnd(end,listGather);
		List<List<Subway>> result=dao.Judge(Endlist, Startlist, listGather, listchange);
		
		List<Subway> MinShort=new ArrayList();
		List<Subway> MinChange=new ArrayList();
		
		int shortline=0;
		for(int i=0;i<result.size()-1;i++)//确定最短路径
		{
			if(result.get(i).size()>result.get(i+1).size())
			{
				shortline=i+1;
			}
		}
		MinShort=result.get(shortline);
		int[] shortchange=new int[result.size()];
		for(int i=0;i<result.size();i++)
		{
			shortchange[i]=0;
			for(int t=0;t<result.get(i).size();t++)
			{
				if(result.get(i).get(t).getChange()!=null)
				{
					shortchange[i]++;
				}
			}
		}
		int shortmin=0;
		for(int i=0;i<result.size()-1;i++)
		{
			if(shortchange[i]>shortchange[i+1])
			{
				shortmin=i+1;
			}
		}
		MinChange=result.get(shortmin);
		request.setAttribute("MinChange", MinChange);
		request.setAttribute("MinShort", MinShort);
		request.getRequestDispatcher("find.jsp").forward(request,response);
		
//		for(int i=0;i<result.size();i++)
//		{
//			for(int t=0;t<result.get(i).size();t++)
//			{
//				System.out.print(result.get(i).get(t).getSname()+"->");
//			}
//			System.out.println("");
//		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
