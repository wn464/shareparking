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
    public List<ComplaintBean> findByDate(@Param("begintime")String beginTime,@Param("endtime")String endTime,@Param("page")int page,@Param("size")int size);
    public int findDateNum(@Param("begintime")String beginTime,@Param("endtime")String endTime);
    public int findCreNum(int id);
    public ComplaintBean findById(int id);
}
