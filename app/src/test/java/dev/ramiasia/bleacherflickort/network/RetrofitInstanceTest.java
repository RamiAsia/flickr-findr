package dev.ramiasia.bleacherflickort.network;

import org.junit.Test;

public class RetrofitInstanceTest {

    @Test
    public void getRetrofitInstance() {
        assert (RetrofitInstance.getRetrofitInstance() != null);
    }
}