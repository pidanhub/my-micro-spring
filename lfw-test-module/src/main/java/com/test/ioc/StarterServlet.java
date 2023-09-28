package com.test.ioc;

import com.lfw.ioc.context.AnnotationApplicationContext;
import com.test.ioc.controller.Controller;

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
@WebServlet ("/")
public class StarterServlet extends HttpServlet {
	
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Controller controller = (Controller) new AnnotationApplicationContext().getBean(Controller.class);
		resp.getWriter().write(controller.serve());
	}
	
}
