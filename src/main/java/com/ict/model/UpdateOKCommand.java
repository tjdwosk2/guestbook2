package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UpdateOKCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		try {
			String path = request.getServletContext().getRealPath("upload");
			MultipartRequest mr = 
					new MultipartRequest(request, path, 100*1024*1024,
							"utf-8", new DefaultFileRenamePolicy());
			VO vo = new VO();
			vo.setIdx(mr.getParameter("idx"));
			vo.setName(mr.getParameter("name"));
			vo.setSubject(mr.getParameter("subject"));
			vo.setContent(mr.getParameter("content"));
			vo.setEmail(mr.getParameter("email"));
			vo.setPwd(mr.getParameter("pwd"));
			
			String old_f_name = mr.getParameter("old_f_name");
			
			// 첨부파일 없으면 이전파일로 대치
			if(mr.getFile("f_name") == null) {
				vo.setF_name(old_f_name);
			}else {
				vo.setF_name(mr.getFilesystemName("f_name"));
			}
			int result = DAO.getUpdate(vo);
			
			if(result>0) {
				return "MyController?cmd=onelist&idx="+vo.getIdx();
			}else {
				return "view/error.jsp";
			}
		} catch (Exception e) {
			return "view/error.jsp";
		}
		
	}
}
