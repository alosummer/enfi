package cux.oracle.apps.pa.util;

import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;

import oracle.jbo.ViewObject;

public class ApplicationModuleUtil extends OAApplicationModuleImpl implements Util {
    public ApplicationModuleUtil() {
    }

    public Object getSqlValue(OAApplicationModule am, String sql) {
        return null;
    }

    public String defaultNullValue(String value) {
        value = ((value != null) || (!"".equals(value))) ? value : "-99999";
        return value;
    }

    public Object getSqlValue(String sql) {
        ViewObject vo = this.findViewObject("QueryVO");
        Object result = null;
        if (vo != null) {
            vo.remove();
        }
        vo = this.createViewObjectFromQueryStmt("QueryVO", sql);
        vo.setMaxFetchSize(-1);
        vo.executeQuery();
        if (vo.hasNext()) {
            result = vo.next().getAttribute(0);
        }
        return result;
    }
}
