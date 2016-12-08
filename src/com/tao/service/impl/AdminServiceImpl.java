package com.tao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tao.dao.BaseDAO;
import com.tao.model.Admin;
import com.tao.model.Student;
import com.tao.service.AdminService;

@Transactional
// 启用事务机制
@Service("AdminService")
public class AdminServiceImpl implements AdminService {
	@Resource
	private BaseDAO<Admin> baseDAO;

	@Override
	public void saveAdmin(Admin admin) {
		baseDAO.save(admin);
	}

	@Override
	public void updateAdmin(Admin admin) {
		baseDAO.update(admin);
	}

	@Override
	public Admin findAdminById(int id) {
		return baseDAO.get(Admin.class, id);
	}

	@Override
	public void deleteAdmin(Admin admin) {
		baseDAO.delete(admin);
	}

	@Override
	public List<Admin> findAllList() {
		return baseDAO.find("from Admin");
	}

	@Override
	public List<Admin> findByPage(int page, int rows) {
		return baseDAO.findByPage(Admin.class, page, rows);
	}

	@Override
	public int getCount() {
		return baseDAO.getCount(Admin.class);
	}

	@Override
	public Admin Login(Admin admin) {
		return baseDAO.find(Admin.class, admin);
	}
}
