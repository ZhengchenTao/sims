package com.tao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tao.dao.BaseDAO;
import com.tao.model.Classes;
import com.tao.model.Student;
import com.tao.service.ClassesService;

@Transactional
// 启用事务机制
@Service("ClassesService")
public class ClassesServiceImpl implements ClassesService {
	@Resource
	private BaseDAO<Classes> baseDAO;

	@Override
	public void saveClasses(Classes classes) {
		baseDAO.save(classes);
	}

	@Override
	public void updateClasses(Classes classes) {
		baseDAO.update(classes);
	}

	@Override
	public Classes findClassesById(int id) {
		return baseDAO.get(Classes.class, id);
	}

	@Override
	public void deleteClasses(Classes classes) {
		baseDAO.delete(classes);
	}

	@Override
	public List<Classes> findAllList() {
		return baseDAO.find("from Classes");
	}

	@Override
	public List<Classes> findByPage(int page, int rows) {
		return baseDAO.findByPage(Classes.class, page, rows);
	}

	@Override
	public int getCount() {
		return baseDAO.getCount(Classes.class);
	}
}
