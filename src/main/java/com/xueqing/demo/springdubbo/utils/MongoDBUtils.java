package com.xueqing.demo.springdubbo.utils;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * MongoDB 文件操作工具类
 *
 * @author XueQing Wang
 * @data 2018年7月13日 15:40:42
 */
public class MongoDBUtils {

    /**
     * 保存文件到MongoDB中
     *
     * @param db           MongoDB 数据源
     * @param uploadModule 上传的模块名称
     * @param file         保存的文件
     * @return Mongo中 文件的ObjectId
     * @author XueQing Wang
     */
    public static String saveFile(DB db, String uploadModule, File file) {
        try {

            GridFS gridFS = new GridFS(db, uploadModule + "_files");

            GridFSInputFile oneFile = gridFS.createFile(file);
            oneFile.setFilename(file.getName());

            // 配置文件属性
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            BasicDBObject metadata = new BasicDBObject();
            metadata.put("uploadDate", df.format(new Date()));
            metadata.put("uploadSysModule", uploadModule);
            oneFile.setMetaData(metadata);    //添加属性

            oneFile.save();    //保存文件

            file.delete();
            // 返回信息
            Object id1 = oneFile.getId();

            return id1.toString();
        } catch (Exception e) {
            return "";
        }
    }


    public static void getFile(DB db, String uploadModule, String id, OutputStream outputStream) {
        // 存储文件信息
        GridFS gridFS = new GridFS(db, uploadModule + "_files");

        // 读取文件
        GridFSDBFile outFile = gridFS.findOne(new ObjectId(id));

        try {
            if(outFile!=null){
                outFile.writeTo(outputStream);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除MongoDB中的文件
     *
     * @param db
     * @param uploadModule
     * @param id
     * @author XueQing Wang
     */
    public static void deleteFile(DB db, String uploadModule, String id) {

        // 存储文件信息
        GridFS gridFS = new GridFS(db, uploadModule + "_files");

        // 读取文件
        GridFSDBFile outFile = gridFS.findOne(new ObjectId(id));
        //System.out.println(outFile);
        if (outFile != null) {
            gridFS.remove(outFile);
        }
    }
}
