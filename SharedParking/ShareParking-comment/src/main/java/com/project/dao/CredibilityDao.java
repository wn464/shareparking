package com.project.dao;

import com.project.Bean.CredibilityBean;

public interface CredibilityDao {
	public CredibilityBean findById(int id);
    public void addCredibility(CredibilityBean bean);
}
