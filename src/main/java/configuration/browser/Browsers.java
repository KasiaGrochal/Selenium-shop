package configuration.browser;

import lombok.Data;

@Data
public class Browsers {
    Browser chrome;
    Browser firefox;
    Browser ie;
    Browser edge;

    public Browser getActiveBrowser() {
        Browser activeBrowser = null;
        if (isNotNull(getChrome())) activeBrowser = getChrome();
        if (isNotNull(getFirefox())) activeBrowser = getFirefox();
        if (isNotNull(getIe())) activeBrowser = getIe();
        if (isNotNull(getEdge())) activeBrowser = getEdge();
        return activeBrowser;
    }

    private boolean isNotNull(Browser browser) {
        return browser != null;
    }
}
