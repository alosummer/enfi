package cux.oracle.apps.per.review.webui;

import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;

public class CreateReviewGrpExCO extends CreateReviewGrpCO {
    public CreateReviewGrpExCO() {
    }

    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        OAMessageChoiceBean msOrgID = 
            (OAMessageChoiceBean)webBean.findIndexedChildRecursive("OrgId");
        msOrgID.setPickListCacheEnabled(false);
    }


}
