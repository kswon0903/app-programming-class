package raejin.appwidget4;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Implementation of App Widget functionality.
 */
public class MyAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Calendar mCalendar = Calendar.getInstance();
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);


        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_app_widget);
        views.setTextViewText(R.id.mText, mFormat.format(mCalendar.getTime()));

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.mText, pendingIntent);

        Intent intent1 = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent1 = PendingIntent.getActivity(context, 1, intent1, 0);
        views.setOnClickPendingIntent(R.id.mButton, pendingIntent1);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

