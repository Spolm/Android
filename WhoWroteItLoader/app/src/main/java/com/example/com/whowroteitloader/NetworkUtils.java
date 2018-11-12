package com.example.com.whowroteitloader;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    // URI Base del Books API
    private static final String BOOK_BASE_URL =
            "https://www.googleapis.com/books/v1/volumes?";
    // Parametros del string de búsqueda
    private static final String QUERY_PARAM = "q";
    // Parametro que limita los resultados buscados
    private static final String MAX_RESULTS = "maxResults";
    // Parametro para filtrar el tipo de impresión
    private static final String PRINT_TYPE = "printType";

    static String getBookInfo(String queryString){

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;

        try {
            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();

            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            // Create a buffered reader from that input stream.
            reader = new BufferedReader(new InputStreamReader(inputStream));
            // Use a StringBuilder to hold the incoming response.
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                // Debido a que es JSON, agregar una nueva línea no es necesario (no
                // afecta la conversion) pero hace la depuración mucho mas sencilla
                // si hace un print out del buffer completo.
                builder.append("\n");
            }

            if (builder.length() == 0) {
                // Stream estaba vacío. Nada que convertir.
                return null;
            }
            bookJSONString = builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d(LOG_TAG, bookJSONString);

        return bookJSONString;
    }

}
