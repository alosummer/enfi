package cux.oracle.apps.ap.oie.entry.entity;

import oracle.jbo.domain.Number;

public class ToUpdateLineInfoformation {
    private Number lineId;
    private String category;
    private String attibute13;

    public ToUpdateLineInfoformation() {
    }

    public void setLineId(Number lineId) {
        this.lineId = lineId;
    }

    public Number getLineId() {
        return lineId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setAttibute13(String attibute13) {
        this.attibute13 = attibute13;
    }

    public String getAttibute13() {
        return attibute13;
    }
}
