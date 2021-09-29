package com.amihaeseisergiu;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amiha
 */
public class Tester {
    
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static Random rnd = new Random();
    
    public static void runTests(boolean server, int iterations, int maxString, int maxRep, boolean mock, boolean sync)
    {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1000);
        for (int i = 0; i < iterations; i++)
        {
            Runnable task = () -> {
                Instant start = Instant.now();
                String returned = sendRequest(server, randomString(rnd.nextInt(maxString - 1) + 1),
                                              rnd.nextInt(maxRep - 1) + 1, mock, sync);
                
                Instant finish = Instant.now();
                long timeElapsed = Duration.between(start, finish).toMillis();
                
                DataWriter.getInstance().log(server, "<" + returned + "> " + String.valueOf(timeElapsed));
            };
            executor.execute(task);
        }
        executor.shutdown();
    }
    
    private static String sendRequest(boolean server, String key, int value, boolean mock, boolean sync)
    {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = null;
            
            if(server)
            {
                request = HttpRequest.newBuilder()
                    .uri(URI.create("http://java-technologies-lab1.herokuapp.com/lab1-1//serve?key=" + 
                            key + "&value=" + String.valueOf(value)
                            + "&mock=" + String.valueOf(mock) + "&sync=" + String.valueOf(sync)))
                    .build();
            }
            else
            {
                request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/lab1/serve?key=" + key + "&value=" + String.valueOf(value)
                            + "&mock=" + String.valueOf(mock) + "&sync=" + String.valueOf(sync)))
                    .build();
            }
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() + " " + response.body();
        } catch (IOException ex) {
            return "rejected";
        } catch (InterruptedException ex) {
            return "rejected";
        }
    }
    
    public static String randomString(int len)
    {
       StringBuilder sb = new StringBuilder(len);
       for(int i = 0; i < len; i++)
       {
           sb.append(AB.charAt(rnd.nextInt(AB.length())));
       }
       return sb.toString();
    }
}
