package co.infinum.mvpexample.data.managers;

import android.content.Context;

import co.infinum.mvpexample.di.qualifiers.AppContext;

public class AndroidStringManager implements StringManager {

    @AppContext
    private Context context;

    public AndroidStringManager(Context context) {
        this.context = context;
    }

    @Override
    public String getString(int stringRes) {
        return context.getString(stringRes);
    }
}
