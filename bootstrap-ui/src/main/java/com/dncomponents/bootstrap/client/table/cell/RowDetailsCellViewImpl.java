package com.dncomponents.bootstrap.client.table.cell;

import com.dncomponents.UiField;
import com.dncomponents.UiStyle;
import com.dncomponents.UiTemplate;
import com.dncomponents.client.components.table.columnclasses.rowexpandercolumn.RowDetailsCellView;
import com.dncomponents.client.components.core.HtmlBinder;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLTemplateElement;

/**
 * @author nikolasavic
 */
@UiTemplate
public class RowDetailsCellViewImpl extends TableCellViewImpl implements RowDetailsCellView {

    @UiField("open-close-element")
    HTMLElement openClosedElement;
    @UiStyle
    String openStyle;
    @UiStyle
    String closeStyle;

    HtmlBinder uiBinder = HtmlBinder.get(RowDetailsCellViewImpl.class, this);

    public RowDetailsCellViewImpl(String template) {
        uiBinder.setTemplateContent(template);
        uiBinder.bind();
    }

    public RowDetailsCellViewImpl(HTMLTemplateElement templateElement) {
        uiBinder.setTemplateElement(templateElement);
        uiBinder.bind();
    }

    @Override
    public void showRow(HasWidgets insertedRow, IsWidget rowDetailsWidget, int columnSize) {

    }

    @Override
    public void hideRow() {

    }

    @Override
    public void setOpened(boolean b) {
        if (!b)
            openClosedElement.className = openStyle;
        else
            openClosedElement.className = closeStyle;
    }

    @Override
    public HTMLElement getValuePanel() {
        return asElement();
    }
}