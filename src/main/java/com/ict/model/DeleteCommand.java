package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;

public class DeleteCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 일처리 : idx를 받아서 비밀번호 가져오기 
		VO vo = DAO.getOneList(request.getParameter("idx"));
		
		// idx 와 비밀번호 저장
		request.setAttribute("vo", vo);
		
		return "view/delete.jsp";
	}
}
