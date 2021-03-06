package com.dncomponents.client.components.table.columnclasses.checkboxcolumn;


import com.dncomponents.client.components.ColumnConfig;
import com.dncomponents.client.components.table.header.HeaderTableCheckBoxCell;

/**
 * Created by nikolasavic
 */
public class ColumnCheckBox<T> extends ColumnConfig<T, Boolean> {

    public ColumnCheckBox() {
        super(t -> Boolean.TRUE);
        this.setColumnWidth("25px");
        this.setHeaderCellFactory(HeaderTableCheckBoxCell::new);
        this.setCellFactory(c -> new TableCellCheckBox());
    }
}