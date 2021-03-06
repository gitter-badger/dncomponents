package com.dncomponents.client.components;

import com.dncomponents.client.components.core.CellContext;
import com.dncomponents.client.components.table.TableCell;
import com.dncomponents.client.components.tree.TreeNode;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class TreeTableColumnConfig<T, M> extends ColumnConfig<TreeNode<T>, M> {
    public TreeTableColumnConfig(Function<T, M> fieldGetter) {
        super(tn -> {
            T userObject;
            try {
                userObject = tn.getUserObject();
                return fieldGetter.apply(userObject);
            } catch (Exception ex) {
                //cast exception
                return null;
            }
        });
    }

    //todo move to columnconfig
    public static <T, M> Comparator<T> getComparator2(Function<T, M> fieldGetter) {
        return Comparator.comparing(fieldGetter,
                Comparator.nullsFirst((o1, o2) -> {
                    if (!(o1 instanceof Comparable) || !(o2 instanceof Comparable)) //this happens for entities without comparable defined;
                        throw new IllegalArgumentException("Entity must be of Comparable type! Please define valid comparator for this column!");
                    Comparable<M> comparable1 = (Comparable<M>) o1;
                    Comparable<M> comparable2 = (Comparable<M>) o2;
                    return comparable1.compareTo((M) comparable2);
                }));

    }


    public TreeTableColumnConfig(ColumnConfig<T, M> columnConfig) {
        this(columnConfig.getFieldGetter());
        setFieldSetter((tn, m) -> {
            T userObject;
            try {
                userObject = tn.getUserObject();
                columnConfig.getFieldSetter().accept(userObject, m);
            } catch (Exception ex) {
                //cast exception
            }
        });
        setColumnName(columnConfig.getColumnName());
        setClazz(columnConfig.getClazz());
        setEditable(columnConfig.isEditable());
        setColumnWidth(columnConfig.getColumnWidth());
        setCellFactory(c -> {
            CellContext<T, M, Table<T>> context =
                    new CellContext<>(columnConfig, null, c.model.getUserObject(), (Table<T>) c.owner);
            return (TableCell<TreeNode<T>, M>) columnConfig.getCellFactory().getCell(context);
        });
        setHeaderCellFactory(() -> columnConfig.getHeaderCellFactory().getCell());
//        setComparator((o1, o2) ->
//                getComparator2(columnConfig.getFieldGetter())
//                        .compare(o1.getUserObject(), o2.getUserObject()));
    }

    public TreeTableColumnConfig(Function<T, M> fieldGetter, BiConsumer<T, M> fieldSetter) {
        this(fieldGetter);
        setFieldSetter((tn, m) -> {
            T userObject;
            try {
                userObject = tn.getUserObject();
                fieldSetter.accept(userObject, m);
            } catch (Exception ex) {
                //cast exception
            }
        });
    }


    private static <T> Optional<T> getUserObject(TreeNode<T> node) {
        T userObject;
        try {
            userObject = node.getUserObject();
        } catch (Exception ex) {
            //class cast exception
            userObject = null;
        }
        return Optional.ofNullable(userObject);
    }
}