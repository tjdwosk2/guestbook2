package com.ict.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;

public class ListCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 전체 목록 가져오기
		List<VO> list =  DAO.getList();
		
		request.setAttribute("list", list);
		
		// 클라이언트의 갈 곳
		return "view/list.jsp";
	}
}
