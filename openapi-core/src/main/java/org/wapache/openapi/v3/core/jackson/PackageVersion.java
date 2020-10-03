package org.wapache.openapi.v3.core.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.util.VersionUtil;

public final class PackageVersion implements Versioned {
    public static final Version VERSION = VersionUtil.parseVersion(
            "0.10.8", "org.wapache.openapi", "openapi-core");

    @Override
    public Version version() {
        return VERSION;
    }
}
