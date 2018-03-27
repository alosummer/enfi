package cux.oracle.apps.pa.util;

import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;

import oracle.jbo.ViewObject;

public class ControllerUtil extends OAControllerImpl implements Util {
    public ControllerUtil() {
    }

    public Object getSqlValue(OAApplicationModule am, String sql) {
        ViewObject vo = am.findViewObject("QueryVO");
        Object result = null;
        if (vo != null) {
            vo.remove();
        }
        vo = am.createViewObjectFromQueryStmt("QueryVO", sql);
        vo.setMaxFetchSize(-1);
        vo.executeQuery();
        if (vo.hasNext()) {
            result = vo.next().getAttribute(0);
        }
        return result;
    }

    public String defaultNullValue(String value) {
        value = ((value != null) && (!"".equals(value))) ? value : "-99999";
        return value;
    }

    public Object getSqlValue(String sql) {
        return null;
    }
}
