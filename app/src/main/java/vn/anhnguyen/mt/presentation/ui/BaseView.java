package vn.anhnguyen.mt.presentation.ui;



/**
 * <p>
 * This interface represents a basic view. All views should implement these common methods.
 * </p>
 */
public interface BaseView {

    /**
     * This is a general method used for showing some kind of progress during a background task. For example, this
     * method should showLoading a progress bar and/or disable buttons before some background work starts.
     */
    void showProgress();

    /**
     * This is a general method used for hiding progress information after a background task finishes.
     */
    void hideProgress();

    /**
     * This method is used for showing error messages on the UI.
     *
     * @param message The error message to be displayed.
     */
    void showError(String message);

    /**
     * This method is used for showing warning messages on the UI.
     *
     * @param message The error message to be displayed.
     */
    void showWarning(String message);

    /**
     * This method is used for showing toast messages on the UI.
     *
     * @param message The error message to be displayed.
     */
    void showToast(String message);

    void gotoHome();

    void showAlertDialog();

    void gotoLogin(boolean fromHome);

    void gotoLogin(boolean fromHome, String message);

    void showSnackBarToast(String message);

    void showNoInternetSnackBar(String message);

//    void showCustomDialog(String message);
//
//    void hideCustomDialog();
//
//    void wsError(ServerErrorObj serverErrorObj);

}
