package renatoarg.androidtest.util;

import android.content.Context;
import android.util.DisplayMetrics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by renato on 13/04/2017.
 */

public class Utils {

    public static String dateFormater(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("MMM d, y");
        Date formattedDate = null;
        try {
            formattedDate = dateFormat.parse(date.substring(0, 10));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormat2.format(formattedDate);
    }

    public static String getScreenDensity(Context context) {
        String density = "";
        switch (context.getResources().getDisplayMetrics().densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                density = "default";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                density = "medium";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                density = "high";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                density = "standard";
                break;
            default:
                density = "high";
                break;
        }
        return density;
    }

}
