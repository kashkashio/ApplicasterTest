package com.kashkash.applicaster.applicaster.util;

import com.squareup.otto.Bus;

/**
 * Created by tsahkashkash on 25/06/16.
 */
public class BusProvider {

    private static Bus mInstance = null;

    private BusProvider() {

    }

    public static Bus getInstance() {
        if (mInstance == null) {
            mInstance = new Bus();
        }
        return mInstance;
    }
}
