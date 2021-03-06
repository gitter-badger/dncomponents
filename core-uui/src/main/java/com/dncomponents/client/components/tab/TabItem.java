package com.dncomponents.client.components.tab;

import com.dncomponents.client.components.core.BaseComponent;
import com.dncomponents.client.components.core.CanSelect;
import com.dncomponents.client.components.core.events.beforeselection.BeforeSelectionEvent;
import com.dncomponents.client.components.core.events.beforeselection.BeforeSelectionHandler;
import com.dncomponents.client.components.core.events.beforeselection.HasBeforeSelectionHandlers;
import com.dncomponents.client.components.core.events.selection.SelectionEvent;
import com.dncomponents.client.views.IsElement;
import com.dncomponents.client.views.core.ui.tab.TabItemView;
import com.dncomponents.client.views.core.ui.tab.TabItemViewSlots;
import com.google.gwt.event.shared.HandlerRegistration;
import elemental2.dom.Event;
import elemental2.dom.EventListener;
import elemental2.dom.HTMLElement;

/**
 * @author nikolasavic
 */
public class TabItem<T> extends BaseComponent<T, TabItemView> implements CanSelect, HasBeforeSelectionHandlers {

    final Tab<T> tab;
    boolean selected;

    public TabItem(Tab tab) {
        super(tab.getView().getTabItemView());
        this.tab = tab;
        setRenderer(tab.tabItemRenderer);
        bind();
    }

    public TabItem(Tab tab, TabItemView view) {
        super(view);
        this.tab = tab;
        setRenderer(tab.tabItemRenderer);
        bind();
    }

    public TabItem(Tab tab, String titleHtml, String contentHtml) {
        this(tab, tab.getView().getTabItemView());
        setTitle(titleHtml);
        setContent(contentHtml);
    }

    public TabItem(Tab tab, IsElement titleElement, IsElement contentElement) {
        this(tab, tab.getView().getTabItemView());
        setTitle(titleElement);
        setContent(contentElement);
    }

    public TabItem(Tab tab, HTMLElement titleElement, HTMLElement contentElement) {
        this(tab, tab.getView().getTabItemView());
        setTitle(titleElement);
        setContent(contentElement);
    }

    public TabItem(Tab tab, T userObject) {
        this(tab, tab.getView().getTabItemView());
        setUserObject(userObject);
    }

    public TabItem(Tab tab, T userObject, RenderTabItem<T> renderer) {
        this(tab, tab.getView().getTabItemView());
        tab.addItem(this);
        setRenderer(renderer);
        setUserObject(userObject);
    }

    private void bind() {
        view.addItemSelectedHandler(new EventListener() {
            @Override
            public void handleEvent(Event evt) {
                BeforeSelectionEvent.fire(tab, this);
                tab.setSelected(TabItem.this, !TabItem.this.isSelected(), true);
                SelectionEvent.fire(tab, this);
            }
        });
    }

    HTMLElement getTitle() {
        return view.getTabItemNav();
    }

    HTMLElement getContent() {
        return view.getTabItemContent();
    }


    @Override
    protected TabItemView getView() {
        return super.getView();
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean b) {
        selected = b;
        view.select(b);
    }

    public void setTitle(String html) {
        view.setItemTitleHtml(html);
    }

    public void setTitle(IsElement element) {
        view.setItemTitle(element.asElement());
    }

    public void setTitle(HTMLElement element) {
        view.setItemTitle(element);
    }

    public void setContent(String html) {
        view.setItemContent(html);
    }

    public void setContent(IsElement content) {
        view.setItemContent(content.asElement());
    }

    public void setContent(HTMLElement content) {
        view.setItemContent(content);
    }

    public int getOrder() {
        return tab.getItems().indexOf(this);
    }

    @Override
    public HandlerRegistration addBeforeSelectionHandler(BeforeSelectionHandler handler) {
        return handler.addTo(asElement());
    }

    public interface RenderTabItem<T> extends Renderer<T, TabItemViewSlots> {
    }

    public void setRenderer(RenderTabItem<T> renderer) {
        super.setRendererBase(renderer);
    }

    @Override
    public TabItemViewSlots getViewSlots() {
        return (TabItemViewSlots) super.getViewSlots();
    }
}