package com.cssc.eos.util;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class MongoDBUtil {
    private static MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017/");

    public static MongoClient getClient(){
        return mongoClient;
    }



    public static void main(String[] args) {
        MongoClient client= MongoDBUtil.getClient();
        MongoDatabase db =client.getDatabase("EOS");
        MongoCollection<Document> collection= db.getCollection("accounts");
        MongoCursor<Document> cursor= collection.find(Filters.eq("name","eosio")).iterator();
        while (cursor.hasNext()){
            Document doc = cursor.next();
            System.out.println(doc.toJson());
        }
        cursor.close();
    }
}
