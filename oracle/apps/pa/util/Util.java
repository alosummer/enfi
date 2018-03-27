package cux.oracle.apps.pa.util;

import oracle.apps.fnd.framework.OAApplicationModule;

public interface Util {
    public Object getSqlValue(OAApplicationModule am, String sql);

    public String defaultNullValue(String value);

    public Object getSqlValue(String sql);
}
