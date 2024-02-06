package fr.eilco.booksprojects.database;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class ClassConverter {
    @TypeConverter
    public static List<String> fromString(String value) {
        // Convertit la chaîne de la base de données en liste de chaînes
        List<String> list = new ArrayList<>();
        if (value != null && !value.isEmpty()) {
            String[] items = value.split(",");
            for (String item : items) {
                list.add(item);
            }
        }
        return list;
    }

    @TypeConverter
    public static String toString(List<String> list) {
        // Convertit la liste de chaînes en une chaîne pour la base de données
        StringBuilder value = new StringBuilder();
        if (list != null) {
            for (String item : list) {
                value.append(item);
                value.append(",");
            }
        }
        return value.toString();
    }
}
