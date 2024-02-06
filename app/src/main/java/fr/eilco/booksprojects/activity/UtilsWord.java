package fr.eilco.booksprojects.activity;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Random;

import fr.eilco.booksprojects.R;

public final class UtilsWord {

    public static String getRandomWord(Context context) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.liste_mots);
        ArrayList<String> mots = new ArrayList<>();

        // Lire le fichier
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                mots.add(line);
            }
        } catch (IOException e) {
            Log.e("Random", e.getMessage());
            e.printStackTrace();
        }

        // Générer un mot au hasard
        if (!mots.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(mots.size());
            return mots.get(index);
        } else {
            return null;
        }
    }


}
