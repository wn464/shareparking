package com.project.service;

import com.project.Bean.ComplaintBean;
import com.project.Bean.PageBean;

public interface IComplaintService {
	public void addComplaint(ComplaintBean bean);
    public PageBean findByStatus(int status,int page,int size,String str);
    public void updateComplaint(int id,int type);
    public void deleteComplaint(int id);
    public int findComNum(int status);
    public PageBean findByDate(String begintime,String endtime,int page,int size,String str);
    public int findDateNum(String begintime,String endtime);
    public ComplaintBean findById(int id);
}
