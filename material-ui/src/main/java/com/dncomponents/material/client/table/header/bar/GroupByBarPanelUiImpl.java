package com.dncomponents.material.client.table.header.bar;

import com.dncomponents.UiField;
import com.dncomponents.UiTemplate;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.views.core.ui.table.headers.bar.panel.GroupByBarPanelUi;
import com.dncomponents.client.views.core.ui.table.headers.bar.panel.GroupByBarPanelView;
import com.dncomponents.client.views.core.ui.table.headers.bar.panelitems.SortBarItemView;
import com.dncomponents.material.client.table.header.bar.baritem.SortBarItemViewImpl;
import elemental2.dom.HTMLTemplateElement;

/**
 * @author nikolasavic
 */
@UiTemplate
public class GroupByBarPanelUiImpl implements GroupByBarPanelUi {

    @UiField("table-bar-item")
    HTMLTemplateElement tableBarItem;
    @UiField("table-bar-panel")
    HTMLTemplateElement tableBarPanel;

    GroupByBarPanelView groupByBarPanelView;
    HtmlBinder uiBinder = HtmlBinder.get(GroupByBarPanelUiImpl.class, this);

    public GroupByBarPanelUiImpl(String template) {
        uiBinder.setTemplateContent(template);
        uiBinder.bind();
    }

    public GroupByBarPanelUiImpl(HTMLTemplateElement templateElement) {
        uiBinder.setTemplateElement(templateElement);
        uiBinder.bind();
    }

    @Override
    public SortBarItemView getSortBarItemView() {
        return new SortBarItemViewImpl(tableBarItem);
    }

    @Override
    public GroupByBarPanelView getRootView() {
        if (groupByBarPanelView == null)
            groupByBarPanelView = new GroupByBarPanelViewImpl(tableBarPanel);
        return groupByBarPanelView;
    }
}
