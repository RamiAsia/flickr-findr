package dev.ramiasia.flickrfindr;

import org.junit.Test;

import dev.ramiasia.flickrfindr.network.RetrofitInstance;

public class RetrofitInstanceTest {

    @Test
    public void getRetrofitInstance() {
        assert (RetrofitInstance.getRetrofitInstance() != null);
    }
}