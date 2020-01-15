package com.dncomponents.material.client.list;

import com.dncomponents.UiField;
import com.dncomponents.UiTemplate;
import com.dncomponents.client.components.checkbox.CheckBox;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.views.core.ui.list.ListCellCheckBoxView;
import com.dncomponents.material.client.cell.BaseCellViewImpl;
import com.google.gwt.user.client.ui.HasValue;
import elemental2.dom.Element;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLTemplateElement;

/**
 * @author nikolasavic
 */
@UiTemplate
public class MdcListCellCheckBoxViewImpl extends BaseCellViewImpl implements ListCellCheckBoxView {

    @UiField
    HTMLElement root;

    @UiField
    CheckBox checkBox;

    @UiField
    HTMLElement valuePanel;

    public MdcListCellCheckBoxViewImpl(HTMLTemplateElement listItemCheckbox) {
        HtmlBinder uiBinder = HtmlBinder.get(MdcListCellCheckBoxViewImpl.class, this);
        uiBinder.setTemplateElement(listItemCheckbox);
        uiBinder.bind();
    }

    @Override
    public void setToValuePanel(Element element) {
        getValuePanel().innerHTML = "";
        getValuePanel().appendChild(element);
    }

    @Override
    public HTMLElement asElement() {
        return root;
    }

    @Override
    public HTMLElement getValuePanel() {
        return valuePanel;
    }

    @Override
    public HasValue<Boolean> getCheckbox() {
        return checkBox;
    }

    @Override
    public void setSelected(boolean b) {
        super.setSelected(b);
        checkBox.setValue(b);
    }
}