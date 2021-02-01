package org.jenkinsci.plugins.skipcert;

import hudson.Extension;
import hudson.model.listeners.ItemListener;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Installs turst-all {@link TrustManager} to bypass HTTPS check
 *
 * @author Kohsuke Kawaguchi
 */
@Extension
public class ItemListenerImpl extends ItemListener {

    private static final Logger LOGGER = Logger.getLogger(ItemListenerImpl.class.getName());
    private static final NoCheckTrustManager TRUST_MANAGER = new NoCheckTrustManager();
    private static final NoCheckHostnameVerifier HOSTNAME_VERIFIER = new NoCheckHostnameVerifier();

    @Override
    public void onLoaded() {
        LOGGER.info("Bypassing certificate check");

        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new TrustManager[]{TRUST_MANAGER}, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
            // bypass host name check, too.
            HttpsURLConnection.setDefaultHostnameVerifier(HOSTNAME_VERIFIER);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            LOGGER.log(Level.WARNING, "Failed to bypass certificate check", e);
        }

    }


}
