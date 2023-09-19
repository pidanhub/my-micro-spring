package com.lfw.ioc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * @Author Zzs
 * @Description 测试项目是否能跑起来
 * @DateTime 2023/9/14 19:16
 */
@WebServlet("/")
public class StarterServlet extends HttpServlet {
	
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write("Hello IoC!");
	}
	
}
