package com.dncomponents.bootstrap.client.pager;

import com.dncomponents.UiField;
import com.dncomponents.UiStyle;
import com.dncomponents.UiTemplate;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.components.textbox.IntegerBox;
import com.dncomponents.client.dom.DomUtil;
import com.dncomponents.client.dom.handlers.ClickHandler;
import com.dncomponents.client.views.core.ui.pager.PagerView;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLTemplateElement;

/**
 * @author nikolasavic
 */
@UiTemplate
public class PagerViewImpl implements PagerView {

    @UiField
    public HTMLElement root;
    @UiField
    public HTMLElement first;
    @UiField
    public HTMLElement previous;
    @UiField
    public HTMLElement next;
    @UiField
    public HTMLElement last;
    @UiField
    public HTMLElement textPanel;
    @UiField
    public IntegerBox numberField;
    @UiStyle
    public String disabledStyle;

    Presenter presenter;


    HtmlBinder uiBinder = HtmlBinder.get(PagerViewImpl.class, this);


    public PagerViewImpl(HTMLTemplateElement templateElement) {
        uiBinder.setTemplateElement(templateElement);
        uiBinder.bind();
        init();

    }

    private void init() {
        ((ClickHandler) mouseEvent -> presenter.previous()).addTo(previous);
        ((ClickHandler) mouseEvent -> presenter.next()).addTo(next);
        ((ClickHandler) mouseEvent -> presenter.first()).addTo(first);
        ((ClickHandler) mouseEvent -> presenter.last()).addTo(last);
        numberField.addValueChangeHandler(event -> presenter.setCurrentPage(event.getValue()));
    }

    @Override
    public void setText(String s) {
        textPanel.innerHTML = s;
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setPageNumber(int pageNumber) {
        numberField.setValue(pageNumber);
    }

    @Override
    public void enablePrevious(boolean b) {
        if (b)
            DomUtil.removeStyle(previous, disabledStyle);
        else
            DomUtil.addStyle(previous, disabledStyle);
    }

    @Override
    public void enableNext(boolean b) {
        if (b)
            DomUtil.removeStyle(next, disabledStyle);
        else
            DomUtil.addStyle(next, disabledStyle);
    }

    @Override
    public HTMLElement asElement() {
        return root;
    }
}