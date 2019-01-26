package org.lanqiao.phone.service;

import org.apache.ibatis.annotations.Param;
import org.lanqiao.phone.pojo.News;
import org.lanqiao.phone.pojo.UserIdAndType;

import java.util.List;

public interface INewsService {
    public Integer getUnreadNewsAllNum(Integer u_Id);
    public List<News> getAllNews(int u_Id,String u_IdType,int s_Id,String s_idType);
    public String getUserNameByNum(Integer u_Id,String u_IdType);
    public List<UserIdAndType> getIdAndType(int u_id,String u_idType);
    public void inserNews(int u_Id,String u_IdType,int s_Id,String s_IdType,String n_news,String n_state);
}
