package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteModel implements Model {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse rsponse) {
		// TODO Auto-generated method stub
		request.setAttribute("msg", "삭제 기능!!");
		return "view/delete.jsp";
	}

}
