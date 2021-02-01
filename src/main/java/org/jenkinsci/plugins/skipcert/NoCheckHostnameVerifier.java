package org.jenkinsci.plugins.skipcert;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Ignore hostname verification.
 *
 * @author Adrian Musante
 * @see HostnameVerifier
 */
class NoCheckHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }
}
