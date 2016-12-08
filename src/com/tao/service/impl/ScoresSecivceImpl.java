package com.tao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tao.dao.BaseDAO;
import com.tao.model.Scores;
import com.tao.service.ScoresService;
@Transactional
//启用事务机制
@Service("ScoresService")
public class ScoresSecivceImpl implements ScoresService {
	@Resource
	private BaseDAO<Scores> baseDAO;

	@Override
	public void saveScores(Scores scores) {
		baseDAO.save(scores);
	}

	@Override
	public void updateScores(Scores scores) {
		baseDAO.update(scores);
	}

	@Override
	public Scores findScoresById(int id) {
		return baseDAO.get(Scores.class, id);
	}

	@Override
	public void deleteScores(Scores scores) {
		baseDAO.delete(scores);
	}

	@Override
	public List<Scores> findAllList() {
		return baseDAO.find("from Scores");
	}

	@Override
	public List<Scores> findByPage(int page, int rows) {
		return baseDAO.findByPage(Scores.class, page, rows);
	}

	@Override
	public int getCount() {
		return baseDAO.getCount(Scores.class);
	}
}