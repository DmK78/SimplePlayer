package ru.job4j.simpleplayer;

import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static List<Field> getFileNames() {
        List<Field> files = new ArrayList<>();
        Field[] fields = R.raw.class.getFields();
        for (int count = 0; count < fields.length; count++) {
            Log.i("Raw Asset: ", fields[count].getName());
            files.add(fields[count]);
        }
        return  files;
    }
}
