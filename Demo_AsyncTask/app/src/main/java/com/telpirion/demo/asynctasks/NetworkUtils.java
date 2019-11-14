package com.telpirion.demo.asynctasks;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    /*

    Other potentially interesting services:

    ## Google

    Google Fonts:
    https://developers.google.com/apis-explorer/#p/webfonts/v1/webfonts.webfonts.list?_h=2&

    Google Civic Information:
    https://developers.google.com/apis-explorer/#p/civicinfo/v2/

    PageSpeed Insights
    https://developers.google.com/apis-explorer/#p/pagespeedonline/v5/

    Poly
    https://developers.google.com/apis-explorer/#p/poly/v1/poly.assets.list?_h=6&


    ## Third party

    Random User Generator:
    https://randomuser.me/

    Library of Congress
    http://loc.gov/pictures/search/?q=<query>&fo=json

    */


    private static final String TAG =
            NetworkUtils.class.getSimpleName();

    private static String doGetURL(Uri uri) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String results = "";

        try {

            URL requestURL = new URL(uri.toString());

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
                // Since it's JSON, adding a newline isn't necessary (it won't
                // affect parsing) but it does make debugging a *lot* easier
                // if you print out the completed buffer for debugging.
                builder.append("\n");
            }

            if (builder.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }

            results = builder.toString();

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
        return results;
    }

    public static String getBookInfo(String queryString) {

        // Base URL for Books API.
        final String BOOK_BASE_URL =
                "https://www.googleapis.com/books/v1/volumes?";
        // Parameter for the search string.
        final String QUERY_PARAM = "q";
        // Parameter that limits search results.
        final String MAX_RESULTS = "maxResults";
        // Parameter to filter by print type.
        final String PRINT_TYPE = "printType";

        Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, queryString)
                .appendQueryParameter(MAX_RESULTS, "10")
                .appendQueryParameter(PRINT_TYPE, "books")
                .build();

        return doGetURL(builtURI);
    }

    public static String getTrafficCameraInfo() {

        // https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2

        final String SDOT_BASE_URL =
                "https://web6.seattle.gov/Travelers/api/Map/Data";
        final String QUERY_PARAM1 = "zoomId";
        final String QUERY_PARAM2 = "type";

        Uri builtURI = Uri.parse(SDOT_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM1, "13")
                .appendQueryParameter(QUERY_PARAM2, "2")
                .build();
        return doGetURL(builtURI);
    }
}
