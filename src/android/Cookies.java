/*
 * Copyright 2013 Ernests Karlsons
 * https://github.com/bez4pieci
 * http://www.karlsons.net
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.bez4pieci.cookies;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;

public class Cookies extends CordovaPlugin {

    private final String TAG = "UkgCookiesPlugin";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("clear".equals(action)) {
            this.clear(callbackContext);
            return true;
        }
        return false;
    }

    public void clear(CallbackContext callbackContext) {
        Log.i(TAG, "Clearing session cookies...");
        CookieManager.getInstance().removeSessionCookies(new ValueCallback<Boolean>() {
            @Override
            public void onReceiveValue(Boolean cleared) {
                Log.i(TAG, "Session cookie clearing complete. Cleared=[" + cleared + "]");
                callbackContext.success();
            }
        });
    }
}
