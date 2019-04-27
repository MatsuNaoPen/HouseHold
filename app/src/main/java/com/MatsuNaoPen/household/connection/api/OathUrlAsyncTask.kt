package com.MatsuNaoPen.household.connection.api

import android.os.AsyncTask
import com.MatsuNaoPen.household.view.activity.LoginActivity
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider
import oauth.signpost.exception.OAuthCommunicationException
import oauth.signpost.exception.OAuthExpectationFailedException
import oauth.signpost.exception.OAuthMessageSignerException
import oauth.signpost.exception.OAuthNotAuthorizedException

/**
 * 認証URLの取得
 *
 * Created by DevUser on 2017/12/15.
 */
class OAuthUrlAsyncTask constructor(
        val callbackUrl: String, var consumer: CommonsHttpOAuthConsumer,
        var mProvider: CommonsHttpOAuthProvider, val mCallback: LoginActivity.AccessCallback)
    : AsyncTask<Void, Void, Void>() {

    val mConsumer = consumer

    var mAuthUrl: String = ""

    override fun doInBackground(vararg arg0: Void): Void? {
        try {
            // AccessToken取得
            mAuthUrl = mProvider.retrieveRequestToken(mConsumer, callbackUrl)
        } catch (e: OAuthMessageSignerException) {
            e.printStackTrace();
        } catch (e: OAuthNotAuthorizedException) {
            e.printStackTrace()
        } catch (e: OAuthExpectationFailedException) {
            e.printStackTrace()
        } catch (e: OAuthCommunicationException) {
            e.printStackTrace()
        }

        return null
    }

    override fun onPostExecute(v: Void?) {
        mCallback.onComplete(mAuthUrl)
    }
}