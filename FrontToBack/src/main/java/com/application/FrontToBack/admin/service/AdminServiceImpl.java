package com.application.FrontToBack.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.FrontToBack.admin.dao.AdminDAO;
import com.application.FrontToBack.admin.dto.AdminDTO;
import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeDTO;
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDAO;
	
	@Override
	public AdminDTO getAdminDetail(AdminDTO adminDTO) throws Exception {
		return adminDAO.getAdminDetail(adminDTO);
	}

	@Override
	public List<KnowledgeDTO> getAdminKnowledgeList() throws Exception {
		 return adminDAO.getAdminKnowledgeList();
	}

}
