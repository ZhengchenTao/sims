package com.tao.service;

import java.util.List;

import com.tao.model.Admin;
import com.tao.model.Teacher;

public interface AdminService {

	public void saveAdmin(Admin admin);

	public void updateAdmin(Admin admin);

	public Admin findAdminById(int id);

	public void deleteAdmin(Admin admin);

	public List<Admin> findAllList();

	public List<Admin> findByPage(int page, int rows);

	public int getCount();

	public Admin Login(Admin admin);

}
