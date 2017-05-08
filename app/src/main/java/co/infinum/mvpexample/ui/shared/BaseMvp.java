package co.infinum.mvpexample.ui.shared;

public interface BaseMvp {

    interface View {

        void showProgress();

        void hideProgress();

        void showError(String message);
    }

    interface Presenter {

        void cancel();
    }

    interface ProgressView {

        void showProgress(String message);

        void showProgress();

        void dismissProgress();

        boolean isShown();
    }

    interface Interactor {

        void cancel();

        void reset();
    }

    interface ErrorListener {

        void onError(String errorMessage);
    }
}
