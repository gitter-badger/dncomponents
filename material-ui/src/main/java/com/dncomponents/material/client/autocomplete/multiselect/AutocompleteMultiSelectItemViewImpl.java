package com.dncomponents.material.client.autocomplete.multiselect;

import com.dncomponents.UiField;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.dom.DomUtil;
import com.dncomponents.client.dom.handlers.ClickHandler;
import com.dncomponents.client.views.core.ui.autocomplete.multiselect.AutocompleteMultiSelectItemView;
import com.dncomponents.client.views.core.ui.autocomplete.multiselect.AutocompleteMultiSelectItemViewSlots;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLTemplateElement;
import elemental2.dom.MouseEvent;

public class AutocompleteMultiSelectItemViewImpl implements AutocompleteMultiSelectItemView {

    @UiField
    HTMLElement root;
    @UiField
    HTMLElement mainPanel;
    @UiField
    HTMLElement removeBtn;

    HtmlBinder uiBinder = HtmlBinder.get(AutocompleteMultiSelectItemViewImpl.class, this);

    public AutocompleteMultiSelectItemViewImpl(String template) {
        uiBinder.setTemplateContent(template);
        uiBinder.bind();
    }

    public AutocompleteMultiSelectItemViewImpl(HTMLTemplateElement templateElement) {
        uiBinder.setTemplateElement(templateElement);
        uiBinder.bind();
    }

    @Override
    public void addRemoveClickHandler(ClickHandler clickHandler) {
        clickHandler.addTo(removeBtn);
        DomUtil.addHandler(removeBtn, new ClickHandler() {
            @Override
            public void onClick(MouseEvent mouseEvent) {
                mouseEvent.stopImmediatePropagation();
                mouseEvent.stopPropagation();
            }
        });
    }

    @Override
    public HTMLElement asElement() {
        return root;
    }

    AutocompleteMultiSelectItemViewSlots viewSlots = new AutocompleteMultiSelectItemViewSlots() {
        @Override
        public HTMLElement getMainSlot() {
            return mainPanel;
        }
    };

    @Override
    public AutocompleteMultiSelectItemViewSlots getViewSlots() {
        return viewSlots;
    }
}
