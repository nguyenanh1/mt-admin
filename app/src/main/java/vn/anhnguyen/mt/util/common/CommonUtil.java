package vn.anhnguyen.mt.util.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import vn.anhnguyen.ticketmovie.R;
import vn.anhnguyen.ticketmovie.presentation.ui.custom.CustomTextView;

public class CommonUtil {

    private static CommonUtil instance_;

    public static CommonUtil instance() {
        if (instance_ == null) {
            instance_ = new CommonUtil();
        }
        return instance_;
    }

    public static void warningSnackBar(String mess, View view, Context context) {
        CommonUtil.showSnackbarCustom(mess
                , Color.WHITE
                , getColorFromRes(R.color.color_snack_bar, context)
                , view
                , getDrawableFromRes(R.mipmap.ic_warning_white, context));
    }

    public static void showSnackbarCustom(String messageContent,
                                          int textColor, int backgroundColor, View currentView, Drawable image) {
        Snackbar snackbar = Snackbar.make(currentView, "", Snackbar.LENGTH_LONG);
        try {
            Field mAccessibilityManagerField = BaseTransientBottomBar.class.getDeclaredField("mAccessibilityManager");
            mAccessibilityManagerField.setAccessible(true);
            AccessibilityManager accessibilityManager = (AccessibilityManager) mAccessibilityManagerField.get(snackbar);
            Field mIsEnabledField = AccessibilityManager.class.getDeclaredField("mIsEnabled");
            mIsEnabledField.setAccessible(true);
            mIsEnabledField.setBoolean(accessibilityManager, false);
            mAccessibilityManagerField.set(snackbar, accessibilityManager);
        } catch (Exception e) {
            Log.d("Snackbar", "Reflection error: " + e.toString());
        }


        // Get the Snackbar's layout view
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

        // Hide the text
        TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        // Inflate our custom view
        View snackView = LayoutInflater.from(currentView.getContext()).inflate(R.layout.snackbar_custom, null);

        // Configure the view
        ImageView imageView = snackView.findViewById(R.id.iv_noti_snackbar);
        imageView.setBackgroundDrawable(image);
        CustomTextView textViewTop = (CustomTextView) snackView.findViewById(R.id.tv_message_content_snackbar);
        textViewTop.setText(messageContent);
        textViewTop.setTextColor(textColor);
        snackView.setBackgroundColor(backgroundColor);

        // Add the view to the Snackbar's layout
        layout.addView(snackView, 0);

        layout.setBackgroundColor(backgroundColor);

        // Show the Snackbar
        snackbar.show();
    }

    public static Snackbar snackbarCustom(String messageContent,
                                          int textColor, int backgroundColor, View currentView, Drawable image) {
        Snackbar snackbar = Snackbar.make(currentView, "", Snackbar.LENGTH_LONG);
        try {
            Field mAccessibilityManagerField = BaseTransientBottomBar.class.getDeclaredField("mAccessibilityManager");
            mAccessibilityManagerField.setAccessible(true);
            AccessibilityManager accessibilityManager = (AccessibilityManager) mAccessibilityManagerField.get(snackbar);
            Field mIsEnabledField = AccessibilityManager.class.getDeclaredField("mIsEnabled");
            mIsEnabledField.setAccessible(true);
            mIsEnabledField.setBoolean(accessibilityManager, false);
            mAccessibilityManagerField.set(snackbar, accessibilityManager);
        } catch (Exception e) {
            Log.d("Snackbar", "Reflection error: " + e.toString());
        }
        // Get the Snackbar's layout view
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

        // Hide the text
        TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        // Inflate our custom view
        View snackView = LayoutInflater.from(currentView.getContext()).inflate(R.layout.snackbar_custom, null);

        // Configure the view
        ImageView imageView = (ImageView) snackView.findViewById(R.id.iv_noti_snackbar);
        imageView.setBackgroundDrawable(image);
        CustomTextView textViewTop = (CustomTextView) snackView.findViewById(R.id.tv_message_content_snackbar);
        textViewTop.setText(messageContent);
        textViewTop.setTextColor(textColor);
        snackView.setBackgroundColor(backgroundColor);

        // Add the view to the Snackbar's layout
        layout.addView(snackView, 0);

        layout.setBackgroundColor(backgroundColor);
        return snackbar;
    }

    public static Snackbar showSnackbarCustomClickDismiss(String messageContent, int textColor, int backgroundColor, View currentView, Drawable image) {
        final Snackbar snackbar = Snackbar.make(currentView, "", Snackbar.LENGTH_INDEFINITE);
        try {
            Field mAccessibilityManagerField = BaseTransientBottomBar.class.getDeclaredField("mAccessibilityManager");
            mAccessibilityManagerField.setAccessible(true);
            AccessibilityManager accessibilityManager = (AccessibilityManager) mAccessibilityManagerField.get(snackbar);
            Field mIsEnabledField = AccessibilityManager.class.getDeclaredField("mIsEnabled");
            mIsEnabledField.setAccessible(true);
            mIsEnabledField.setBoolean(accessibilityManager, false);
            mAccessibilityManagerField.set(snackbar, accessibilityManager);
        } catch (Exception e) {
            Log.d("Snackbar", "Reflection error: " + e.toString());
        }
        // Get the Snackbar's layout view
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        // Hide the text
        TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);
        // Inflate our custom view
        View snackView = LayoutInflater.from(currentView.getContext()).inflate(R.layout.snackbar_custom, null);
        // Configure the view
        ImageView imageView = snackView.findViewById(R.id.iv_noti_snackbar);
        imageView.setBackgroundDrawable(image);
        CustomTextView textViewTop = (CustomTextView) snackView.findViewById(R.id.tv_message_content_snackbar);
        textViewTop.setText(messageContent);
        textViewTop.setTextColor(textColor);
        snackView.setBackgroundColor(backgroundColor);
        // Add the view to the Snackbar's layout
        layout.addView(snackView, 0);
        layout.setBackgroundColor(backgroundColor);
        snackbar.show();
        return snackbar;
    }

    public static void showToast(String messgage, Context ctxt) {
        if (ctxt == null)
            return;
        Toast.makeText(ctxt, messgage, Toast.LENGTH_LONG).show();
    }

    @NonNull
    public static String getStringFromRes(int resID, Context context) {
        if (context == null)
            return "";
        return context.getResources().getString(resID);
    }

    public static Drawable getDrawableFromRes(int resID, Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            return context.getResources().getDrawable(resID, context.getTheme());
        } else {
            return context.getResources().getDrawable(resID);
        }
    }

    public static int getColorFromRes(int resID, Context context) {
        return context.getResources().getColor(resID);
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void setupUI(View view, final Activity activity) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    CommonUtil.hideSoftKeyboard(activity);
                    return false;
                }
            });
        }

        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, activity);
            }
        }
    }

    public static void disconnectShowSnackBar(View view, Context context) {
        CommonUtil.showSnackbarCustom(getStringFromRes(R.string.str_disconnect_try_again, context)
                , Color.WHITE
                , getColorFromRes(R.color.color_snack_bar, context)
                , view
                , getDrawableFromRes(R.mipmap.ic_wifi_disconnect, context));
    }

    public static void disableClick(final Button btn, final int duration) {
        btn.setClickable(false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                btn.setClickable(true);
            }
        }).start();

    }

    public String convertDateToDDmmYYYY(String dateInYYYYmmDD) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat convert = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return convert.format(format.parse(dateInYYYYmmDD));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String convertDateToYYYYmmDD(String dateInDDmmYYYY) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat convert = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return convert.format(format.parse(dateInDDmmYYYY));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer covertDateToYYYYmmDD(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String dateS = format.format(date);
        try{
            int dateInt = Integer.parseInt(dateS);
            return dateInt;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String convertToDDMMYY(int partdate){
        int partday = partdate % 100;
        int partdaym= partdate /100;
        int dd = partday;
        int parttmonth = partdaym % 100;
        int mm = parttmonth;
        int partyear = partdate/10000;
        int yy = partyear;
        return dd+" Th"+mm+" "+yy;
    }

    public void showAlertDialog(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Bạn có muốn thoát khỏi?");
        builder.setNegativeButton("HỦY BỎ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("ĐỒNG Ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Activity activity = (Activity) context;
                activity.onBackPressed();
            }
        });
        builder.create().show();
    }
}
