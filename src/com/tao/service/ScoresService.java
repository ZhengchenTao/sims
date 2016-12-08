package com.tao.service;

import java.util.List;

import com.tao.model.Scores;

public interface ScoresService {
	public void saveScores(Scores scores);

	public void updateScores(Scores scores);

	public Scores findScoresById(int id);

	public void deleteScores(Scores scores);

	public List<Scores> findAllList();

	public List<Scores> findByPage(int page, int rows);

	public int getCount();
}
