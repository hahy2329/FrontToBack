package com.application.FrontToBack.admin.dao;

import java.util.List;

import com.application.FrontToBack.admin.dto.AdminDTO;
import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeDTO;

public interface AdminDAO {
	
	public AdminDTO getAdminDetail(AdminDTO adminDTO) throws Exception;
	public List<KnowledgeDTO> getAdminKnowledgeList() throws Exception;

}
