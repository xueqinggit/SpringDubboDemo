package com.xueqing.demo.springdubbo.service;

import com.mongodb.DB;

import java.io.File;
import java.io.OutputStream;

public interface MongoService {
    DB getDB();

    String uploadFile(File file);


    void getFile(String id, OutputStream outputStream);

    void delFile(String id);
}
