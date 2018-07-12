package com.xueqing.demo.springdubbo.service;

import com.xueqing.demo.springdubbo.entity.Email;
import com.xueqing.demo.springdubbo.entity.Result;

public interface IMailService {
    /**
     * 纯文本
     * @Author  XueQing Wang
     * @param mail
     * @throws Exception  void
     * @Date	2018年7月11日
     * 更新日志
     * 2018年7月11日  XueQing Wang 首次创建
     */
    public void send(Email mail) throws Exception;
    /**
     * 富文本
     * @Author  XueQing Wang
     * @param mail
     * @throws Exception  void
     * @Date	2018年7月11日
     * 更新日志
     * 2018年7月11日  XueQing Wang 首次创建
     *
     */
    public void sendHtml(Email mail) throws Exception;
    /**
     * 模版发送 freemarker
     * @Author  XueQing Wang
     * @param mail
     * @throws Exception  void
     * @Date	2018年7月11日
     * 更新日志
     * 2018年7月11日  XueQing Wang 首次创建
     *
     */
    public void sendFreemarker(Email mail) throws Exception;
    /**
     * Redis 队列
     * @Author  XueQing Wang
     * @param mail
     * @throws Exception  void
     * @Date	2017年8月13日
     * 更新日志
     * 2017年8月13日  XueQing Wang 首次创建
     *
     */
    public void sendRedisQueue(Email mail) throws Exception;

}
