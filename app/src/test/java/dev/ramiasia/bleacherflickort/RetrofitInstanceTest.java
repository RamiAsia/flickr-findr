package dev.ramiasia.bleacherflickort;

import org.junit.Test;

import dev.ramiasia.bleacherflickort.network.RetrofitInstance;

public class RetrofitInstanceTest {

    @Test
    public void getRetrofitInstance() {
        assert (RetrofitInstance.getRetrofitInstance() != null);
    }
}