package com.danarossa;

import com.mongodb.*;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class Main {

    public static void main(String[] args) throws UnknownHostException, ParseException {
        Main main = new Main();
        main.listAllDocumentsInDB();
        System.out.println("");
        main.selectWithConditions();
        System.out.println("");
        main.aggregation();
    }


    public void listAllDocumentsInDB() throws UnknownHostException {
        System.out.println(1);
        MongoClient mongoClient = new MongoClient();
        DB db = mongoClient.getDB("lab8DB");
        DBCollection files = db.getCollection("files");
        System.out.println("files number");
        System.out.println(files.getCount());
        files.find().forEach((el) -> System.out.println(el.toString()));

        System.out.println("");
        DBCollection folders = db.getCollection("folders");
        System.out.println("folders number");
        System.out.println(folders.getCount());
        folders.find().forEach((el) -> System.out.println(el.toString()));
        mongoClient.close();
    }


    public void selectWithConditions() throws UnknownHostException, ParseException {
        System.out.println(2);
        // select all folders, created in 2014 by root that have at least one subfolder
        MongoClient mongoClient = new MongoClient();
        DB db = mongoClient.getDB("lab8DB");

        DBCollection folders = db.getCollection("folders");

        //folders.find().forEach((el) -> System.out.println(el.toString()));
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<>();
        obj.add(new BasicDBObject("tags.created_by", "danarossa"));

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2016-06-15");
        obj.add(new BasicDBObject("created", BasicDBObjectBuilder.start("$gte", date).get()));
        obj.add(new BasicDBObject("child_folders", BasicDBObjectBuilder.start("$size", 0).get()));
        andQuery.put("$and", obj);

        System.out.println(andQuery.toString());

        DBCursor cursor = folders.find(andQuery);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
        mongoClient.close();

    }


    public void aggregation() throws UnknownHostException {
        System.out.println(3);
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("lab8DB");

        List<BasicDBObject> list = Arrays.asList(
//                new BasicDBObject(
//                        "$project", new Document("name", 1).append("files", "1")),
                new BasicDBObject("$lookup",
                        new BasicDBObject("from", "files")
                                .append("localField", "files")
                                .append("foreignField", "_id")
                                .append("as", "new_array")
                ),
                new BasicDBObject("$unwind", "$new_array"),
                new BasicDBObject("$group",
                        new BasicDBObject("_id", "$name")
                                .append("totalSize", new BasicDBObject("$sum", "$new_array.tags.size"))),
                new BasicDBObject("$sort", new Document("totalSize", -1))
        );

        MongoCollection<Document> folders = db.getCollection("folders");
        AggregateIterable<Document> aggregate = folders.aggregate(list);

        for (Document document : aggregate) {
            System.out.println(document);
        }
        mongoClient.close();

    }





}
