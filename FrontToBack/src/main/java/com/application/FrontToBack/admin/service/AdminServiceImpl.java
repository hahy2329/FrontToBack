package com.application.FrontToBack.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.FrontToBack.admin.dao.AdminDAO;
import com.application.FrontToBack.admin.dto.AdminDTO;
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDAO;
	
	@Override
	public AdminDTO getAdminDetail(AdminDTO adminDTO) throws Exception {
		return adminDAO.getAdminDetail(adminDTO);
	}

}
