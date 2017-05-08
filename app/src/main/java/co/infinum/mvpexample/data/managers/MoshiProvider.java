package co.infinum.mvpexample.data.managers;

import com.squareup.moshi.Moshi;

public class MoshiProvider {

    private static MoshiProvider instance;

    private Moshi moshi;

    private MoshiProvider() {
        moshi = new Moshi.Builder().build();
    }

      public Moshi getMoshi() {
        return moshi;
    }

    public static MoshiProvider getInstance() {
        if (instance == null) {
            instance = new MoshiProvider();
        }

        return instance;
    }
}
