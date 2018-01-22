package com.snappenio.mitosis.utility.url;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

@Component
public class URLReader {

    private static final Logger logger = LoggerFactory.getLogger(URLReader.class);

    private static final String USER_AGENT_KEY = "User-Agent";
    private static final String USER_AGENT_VALUE = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_2) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";

    public Optional<String> read(String destination) {
        logger.debug("[read] Reading in URL: {}", destination);

        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(destination);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty(USER_AGENT_KEY, USER_AGENT_VALUE);
            httpURLConnection.connect();

            if (isValidResponseCode(httpURLConnection.getResponseCode())) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                StringBuilder destinationContent = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    destinationContent.append(line);
                }

                return Optional.of(destinationContent.toString());
            }
        } catch (MalformedURLException e) {
            logger.error("[read] Malformed URL Exception: ", e);
        } catch (IOException e) {
            logger.error("[read] IO Exception: ", e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    logger.error("[read] Failed to close the buffer reader: ", e);
                }
            }
        }
        return Optional.empty();
    }

    private boolean isValidResponseCode(int responseCode) {
        return responseCode != HttpURLConnection.HTTP_NOT_FOUND;
    }

}
