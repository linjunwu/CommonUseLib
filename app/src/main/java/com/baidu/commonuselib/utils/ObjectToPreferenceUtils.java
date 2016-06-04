package com.baidu.commonuselib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.baidu.commonuselib.annotation.Preference;

import java.lang.reflect.Field;

public final class ObjectToPreferenceUtils {
    private ObjectToPreferenceUtils() {

    }

    private static final String PREFERENCE = "bd_opcloud";

    private static final String UID = "uid";


    public static <T> void saveObjectToPreference(Context context,
                                                  T object, Class<T> tClass) throws IllegalAccessException{

        if (object == null) {
            return;
        }

        Field[] fields = tClass.getDeclaredFields();
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(tClass.getSimpleName(), Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(Preference.class)) {

                Class<?> type = fields[i].getType();
                if (type.isAssignableFrom(String.class)) {
                    editor.putString(fields[i].getName(), (String) fields[i].get(object));
                } else if (type.isAssignableFrom(int.class)) {
                    editor.putInt(fields[i].getName(), fields[i].getInt(object));
                } else if (type.isAssignableFrom(float.class)) {
                    editor.putFloat(fields[i].getName(), fields[i].getFloat(object));
                } else if (type.isAssignableFrom(boolean.class)) {
                    editor.putBoolean(fields[i].getName(), fields[i].getBoolean(object));
                } else if (type.isAssignableFrom(long.class)) {
                    editor.putLong(fields[i].getName(), fields[i].getLong(object));
                }

            }
        }
        editor.commit();

    }

    public static <T> T getObjectFromPreference(Context context,
                                                Class<T> tClass) throws InstantiationException, IllegalAccessException{

        T result = tClass.newInstance();

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);

        Field[] fields = tClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(Preference.class)) {

                Class<?> type = fields[i].getType();
                if (type.isAssignableFrom(String.class)) {
                    fields[i].set(result, sharedPreferences.getString(fields[i].getName(), null));
                } else if (type.isAssignableFrom(int.class)) {
                    fields[i].setInt(result, sharedPreferences.getInt(fields[i].getName(), 0));
                } else if (type.isAssignableFrom(float.class)) {
                    fields[i].setFloat(result, sharedPreferences.getFloat(fields[i].getName(), 0f));
                } else if (type.isAssignableFrom(boolean.class)) {
                    fields[i].setBoolean(result, sharedPreferences.getBoolean(fields[i].getName(), false));
                } else if (type.isAssignableFrom(long.class)) {
                    fields[i].setLong(result, sharedPreferences.getLong(fields[i].getName(), 0l));
                }

            }
        }

        return result;

    }


}
