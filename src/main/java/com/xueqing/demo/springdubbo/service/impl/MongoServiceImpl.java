package com.xueqing.demo.springdubbo.service.impl;

import com.mongodb.DB;
import com.xueqing.demo.springdubbo.service.MongoService;
import com.xueqing.demo.springdubbo.utils.MongoDBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.OutputStream;

@Service
public class MongoServiceImpl implements MongoService {
    public static final String module = "SpringBoot";
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public DB getDB() {
        return mongoTemplate.getDb();
    }

    @Override
    public String uploadFile(File file) {
        DB db = mongoTemplate.getDb();
        String s = MongoDBUtils.saveFile(db, module, file);
        return s;
    }

    @Override
    public void getFile(String id, OutputStream outputStream) {
        DB db = mongoTemplate.getDb();
        MongoDBUtils.getFile(db, module, id, outputStream);
    }


    @Override
    public void delFile(String id) {
        DB db = mongoTemplate.getDb();
        MongoDBUtils.deleteFile(db, module, id);
    }

}
