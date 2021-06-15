package com.nicolasrodf.tmdbclientjava.util;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.nicolasrodf.tmdbclientjava.R;

public class Helper {

    public static void addFragmentAnim(FragmentManager fragmentManager, int containerViewId, Fragment fragment) {
        if (fragmentManager != null && fragment != null) {
            try {
                fragmentManager.beginTransaction()
                        .setCustomAnimations(
                                R.anim.slide_in,  // enter
                                R.anim.fade_out,  // exit
                                R.anim.fade_in,   // popEnter
                                R.anim.slide_out)  // popExit
                        .add(containerViewId, fragment, fragment.getClass().getSimpleName()).commit();
            } catch (IllegalStateException e) {
                Log.d("ERROR_addFragment", getMethodName());
                e.printStackTrace();
            }
        }
    }

    public static boolean removeFragmentAnim(FragmentManager fragmentManager, Fragment fragment) {
        if (fragmentManager != null && fragment != null) {
            try {
                fragmentManager.beginTransaction()
                        .setCustomAnimations(
                                0,  // enter
                                R.anim.fade_out,  // exit
                                0,   // popEnter
                                R.anim.slide_out) // popExit
                        .remove(fragment).commit();
                return true;
            } catch (IllegalStateException e) {
                Log.d("ERROR_replaceFragment ", getMethodName());
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void replaceFragment(FragmentManager fragmentManager, int containerViewId, Fragment fragment) {
        if (fragmentManager != null && fragment != null) {
            try {
                fragmentManager.beginTransaction().
                        replace(containerViewId, fragment).commit();
            } catch (IllegalStateException e) {
                Log.d("ERROR_rplaceFrgmt ", getMethodName());
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the method name for a depth in call stack. <br />
     * Utility function
     *
     * @return method name
     */
    public static String getMethodName() {
        final StackTraceElement[] ste = new Throwable().getStackTrace();
        StringBuffer buffer = new StringBuffer();
        for (StackTraceElement stackTraceElement : ste) {
            buffer.append("WallyMetodo: " + stackTraceElement.getMethodName() + "\n");
        }
        return buffer.toString();
    }

}
