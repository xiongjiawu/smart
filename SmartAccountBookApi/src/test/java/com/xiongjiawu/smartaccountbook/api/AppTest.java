package com.xiongjiawu.smartaccountbook.api;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AppTest {

    @Test
    public void test(){
        List<String> scoreInterval = new ArrayList<>();
        for (int i = 0; i < 100; i+=10) {
            String interval = i+","+(i+10);
            scoreInterval.add(interval);
        }
        System.out.println(scoreInterval);
    }

}
