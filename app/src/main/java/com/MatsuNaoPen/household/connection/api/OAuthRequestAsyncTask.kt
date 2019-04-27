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
 * Created by DevUser on 2017/12/15.
 */

class OAuthRequestAsyncTask constructor(
        val oauthVerifier: String, consumer: CommonsHttpOAuthConsumer, val provider: CommonsHttpOAuthProvider, val mCallback: LoginActivity.RequestCallback)
    : AsyncTask<Void, Void, Void>() {
    var mConsumer = consumer


    override fun doInBackground(vararg arg0: Void): Void? {
        //OAuth認証
        try {
//            val mProvider = CommonsHttpOAuthProvider(request_token_url, access_token_url, authrize_url)
//            val mProvider = DefaultOAuthProvider(request_token_url, access_token_url, authrize_url)
            provider.retrieveAccessToken(mConsumer, oauthVerifier)
        } catch (e: OAuthMessageSignerException) {
            e.printStackTrace()
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
        mCallback.onCompleteOAuthRequest(mConsumer)
    }
}
