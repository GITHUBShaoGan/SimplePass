package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class DBGen {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.slut.simplepass");
        addNote(schema);
        new DaoGenerator().generateAll(schema, "G:\\SimplePass\\SimplePass\\app\\src\\main\\java-gen");
    }

    private static void addNote(Schema schema) {
        Entity authInfo = schema.addEntity("AuthInfo");
        authInfo.addIdProperty().autoincrement();
        authInfo.addStringProperty("pwd");
        authInfo.addStringProperty("reservation");
        authInfo.addLongProperty("create_stamp");
        authInfo.addLongProperty("update_stamp");
    }

}
