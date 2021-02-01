package org.jenkinsci.plugins.skipcert;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

/**
 * Accepts all certificates as valid.
 *
 * @author Adrian Musante
 * @see HostnameVerifier
 */
class NoCheckTrustManager implements X509TrustManager {

    private static final X509Certificate[] acceptedIssuers = new X509Certificate[0];

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) {
        //Ignored
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) {
        //Ignored
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return acceptedIssuers;
    }
}
