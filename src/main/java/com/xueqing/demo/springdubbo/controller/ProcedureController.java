package com.xueqing.demo.springdubbo.controller;

import com.xueqing.demo.springdubbo.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

@Controller
public class ProcedureController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/getOrderNo")
    @ResponseBody
    public Result getOrderNo(String station) {
        try {
            String orderNo = (String) jdbcTemplate.execute(
                    new CallableStatementCreator() {
                        @Override
                        public CallableStatement createCallableStatement(Connection con) throws SQLException {
                            String storedProc = "{call generate_orderNo(?,?)}";// 调用的sql
                            CallableStatement cs = con.prepareCall(storedProc);
                            cs.setString(1, station);// 设置输入参数的值
                            cs.registerOutParameter(2, Types.VARCHAR);// 注册输出参数的类型
                            return cs;
                        }
                    }, new CallableStatementCallback() {
                        @Override
                        public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                            cs.execute();
                            return cs.getString(2);// 获取输出参数的值
                        }
                    });

            return Result.ok().put("orderNo", orderNo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }
}
