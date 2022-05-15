package com;

//import com.Bill;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


@WebServlet("/BillsAPI")
public class BillsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Bill billObj = new Bill();
	
	public BillsAPI() {
		super();
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String output = billObj.insertBill( request.getParameter("acc_no"),
				request.getParameter("customer_name"), request.getParameter("address"), request.getParameter("qty"),
				request.getParameter("total_price"), request.getParameter("date_time"));
		response.getWriter().write(output);
	}

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {

				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		{
			Map paras = getParasMap(request);
			String output = billObj.updateBill(paras.get("hididSave").toString(), paras.get("id").toString(),
					paras.get("acc_no").toString(), paras.get("customer_name").toString(),
					paras.get("address").toString(), paras.get("qty").toString(),
					paras.get("total_price").toString(), paras.get("date_time").toString());
			response.getWriter().write(output);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		String output = billObj.deletebill(paras.get("id").toString());
		response.getWriter().write(output);
	}

}
