package com.kiparo.data.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonFile {

    public static final String ERROR_404_NOT_FOUND_BODY = "error_404_not_found_response_body.json";
    public static final String NORMAL_200_REPOS_BY_USERNAME_BODY = "normal_200_repos_by_username_response_body.json";

    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */
    public static String getJson(String path) throws IOException {
        InputStream jsonInput = JsonFile.class.getClassLoader().getResourceAsStream("api/" + path);
        return readFromInputStream(jsonInput);
    }

    private static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
