package com.dncomponents.client.components.core;

import com.dncomponents.client.components.core.selectionmodel.AbstractMultiSelectionGroup;
import com.dncomponents.client.components.core.selectionmodel.MultiSelectionModel;
import com.dncomponents.client.views.core.pcg.View;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

import java.util.List;

public abstract class BaseComponentMultiSelection<T, V extends View, C extends HasUserValue<T> & CanSelect>
        extends BaseComponent<T, V> implements MultiSelectionModel<C> {


    protected AbstractMultiSelectionGroup<T, C> selectionGroup = new AbstractMultiSelectionGroup<T, C>() {
        @Override
        public void setSelectedInView(C model, boolean b) {
//            setSelectedModel(model, b); //todo #1?
            model.setSelected(b);
        }
    };

    public BaseComponentMultiSelection(V ui) {
        super(ui);
    }

    @Override
    public boolean setSelected(C c, boolean b, boolean fireEvent) {
        return selectionGroup.setSelected(c, b, fireEvent);
    }

    @Override
    public boolean setSelected(C c, boolean b) {
        return setSelected(c, b, false);
    }

    @Override
    public boolean isSelected(C c) {
        return selectionGroup.isSelected(c);
    }

    @Override
    public HasValue<List<C>> getHasValue() {
        return selectionGroup.getHasValue();
    }

    @Override
    public List<C> getItems() {
        return selectionGroup.getItems();
    }

    @Override
    public List<C> getSelection() {
        return selectionGroup.getSelection();
    }

    @Override
    public HandlerRegistration addSelectionHandler(SelectionHandler<List<C>> handler) {
        return selectionGroup.addSelectionHandler(handler);
    }

    public void addItem(C item) {
        selectionGroup.addItem(item);
    }

    public void removeItem(C item) {
        selectionGroup.removeItem(item);
    }

    public void removeAllItems() {
        selectionGroup.getItems().clear();
    }

    public void addItems(C... items) {
        selectionGroup.addItems(items);
    }

    public void addEntityItems(List<T> items) {
        items.forEach(t -> addItem(createItem(t)));
    }

    protected abstract C createItem(T t);

    public MultiSelectionModel<T> getEntitySelectionModel() {
        return selectionGroup.getEntitySelectionModel();
    }

}