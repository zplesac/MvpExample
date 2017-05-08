package co.infinum.mvpexample.ui.shared;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import co.infinum.mvpexample.MvpExampleApp;
import co.infinum.mvpexample.di.components.AppComponent;

public abstract class BaseActivity extends AppCompatActivity implements BaseMvp.View {

    private BaseMvp.ProgressView progressView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(MvpExampleApp.getInstance().getAppComponent());
        initProgressDialog();
    }

    private void initProgressDialog() {
        progressView = new ProgressDialogDelegate(this);
    }

    protected abstract void injectDependencies(AppComponent appComponent);

    @Override
    public void showProgress() {
        progressView.showProgress();
    }

    @Override
    public void hideProgress() {
        progressView.dismissProgress();
    }

    @Override
    public void showError(String message) {
        if (!isFinishing()) {
            hideProgress();
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
        }
    }
}
