package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.Bean.ComplaintBean;


public interface ComplaintDao {
	public void addComplaint(ComplaintBean bean);
    public List<ComplaintBean> findByStatus(@Param("status")int status,@Param("page")int page,@Param("size")int size);
    public void updateComplaint(int id);
    public void deleteComplaint(int id);
    public int findComNum(@Param("status")int status);
    public List<ComplaintBean> findByDate(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("page")int page,@Param("size")int size);
    public int findDateNum(@Param("beginTime")String beginTime,@Param("endTime")String endTime);
    public int findCreNum(int id);
}
