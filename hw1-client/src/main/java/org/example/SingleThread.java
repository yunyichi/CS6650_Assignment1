package org.example;

import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.api.SwipeApi;
import io.swagger.client.model.SwipeDetails;

import java.util.concurrent.CountDownLatch;

public class SingleThread implements Runnable{
    private static final String baseUrl = "http://54.201.34.108:8080/hw1_server_war/";
    private DataGeneration dataGeneration;
    private CountDownLatch completed;
    private CountData countData;
    private static final int maxTry = 5;
    public SingleThread(DataGeneration dataGeneration, CountDownLatch completed, CountData countData) {
        this.dataGeneration = dataGeneration;
        this.completed = completed;
        this.countData = countData;
    }

    @Override
    public void run() {
        SwipeApi apiInstance = new SwipeApi();
        apiInstance.getApiClient().setBasePath(baseUrl);
        SwipeDetails body = dataGeneration.generateBody();
        String leftOrRight = dataGeneration.generateLeftOrRight();
//        int i = 0;
        for (int i = 0; i < maxTry; i++) {
            try {
                ApiResponse res = apiInstance.swipeWithHttpInfo(body, leftOrRight);
                System.out.println(res.getStatusCode());
                countData.incNumOfSuccessfulRequest();
                break;
            } catch (ApiException e) {
//                System.err.println("Exception when calling SwipeApi#swipe");
                if (i == maxTry-1) {
                    countData.incNumOfFailedRequest();
                }
            }
        }
        completed.countDown();

    }
}
