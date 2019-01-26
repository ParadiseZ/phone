package org.lanqiao.phone.service.impl;


import org.lanqiao.phone.mapper.INewsDao;
import org.lanqiao.phone.pojo.News;
import org.lanqiao.phone.pojo.UserIdAndType;
import org.lanqiao.phone.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements INewsService {
    @Autowired
    INewsDao newsDao;
    @Override
    public Integer getUnreadNewsAllNum(Integer u_Id) {
        return newsDao.getUnreadNewsAllNum(u_Id);
    }

    @Override
    public List<News> getAllNews(int u_Id,String u_IdType,int s_Id,String s_IdType) {
        return newsDao.getAllNews(u_Id,u_IdType,s_Id,s_IdType);
    }

    @Override
    public String getUserNameByNum(Integer u_Id, String u_IdType) {
       if("个人".equals(u_IdType)){
           return newsDao.getUserNameFromUser(u_Id);
       }else if("商家".equals(u_IdType)){
           return newsDao.getUserNameFromShop(u_Id);
       }else return newsDao.getUserNameFromBackstage(u_Id);
    }

    @Override
    public List<UserIdAndType> getIdAndType(int u_id, String u_idType) {
       List<UserIdAndType> asSender = newsDao.getIdAndTypeBySend(u_id,u_idType);
       List<UserIdAndType> asReciever = newsDao.getIdAndTypeByRec(u_id,u_idType);
       if(asSender.size()>0){
           for(UserIdAndType uit:asSender){
               asReciever.add(uit);
           }
       }
       return asReciever;
    }

    @Override
    public void inserNews(int u_Id,String u_IdType,int s_Id,String s_IdType,String n_news,String n_state){
        newsDao.inserNews(u_Id,u_IdType,s_Id,s_IdType,n_news,n_state);
    }
}
