package com.dncomponents.client.components.tree;


import com.dncomponents.client.components.BaseCell;
import com.dncomponents.client.components.Tree;
import com.dncomponents.client.components.tree.checkbox.TreeCellCheckboxParent;
import com.dncomponents.client.components.tree.checkbox.TreeCellCheckboxSimple;
import com.dncomponents.client.views.core.ui.tree.BaseTreeCellView;
import com.dncomponents.client.views.core.ui.tree.HasTreeUi;
import com.dncomponents.client.views.core.ui.tree.TreeUi;

/**
 * Created by nikolasavic
 */
public abstract class AbstractTreeCell<T, M> extends BaseCell<TreeNode<T>, M> {

    public AbstractTreeCell() {
    }

    public AbstractTreeCell(BaseTreeCellView cellView) {
        super(cellView);
    }

    public static <T, M> AbstractTreeCell<T, M> getCell(TreeNode<T> treeNode, boolean checkBox) {
        if (checkBox)
            return getCellCheckBox(treeNode);
        else
            return getCell(treeNode);
    }

    private static <T, M> AbstractTreeCell<T, M> getCellCheckBox(TreeNode treeNode) {
        if (treeNode.isLeaf()) {
            return new TreeCellCheckboxSimple<>();
        } else {
            return new TreeCellCheckboxParent<>();
        }
    }

    public static <T, M> AbstractTreeCell<T, M> getCell(TreeNode treeNode) {
        if (treeNode.isLeaf()) {
            return new TreeCellSimple();
        } else {
            return new TreeCellParent();
        }
    }

    @Override
    public void draw() {
        super.draw();
        getCellView().setPadding(getModel().getLevel() * 10 + 5);
    }

    protected AbstractTreeCell(BaseCellBuilder<TreeNode<T>, M, ?> builder) {
        super(builder);
    }

    @Override
    public Tree getOwner() {
        return (Tree) super.getOwner();
    }

    @Override
    protected TreeUi getUi() {
        return ((HasTreeUi) super.getUi()).getTreeUi();
    }

    @Override
    public BaseTreeCellView getCellView() {
        return (BaseTreeCellView) super.getCellView();
    }

    public void setVisible(boolean b) { //TODO
        cellView.asElement().setAttribute("style", "display: none;");
    }


    public static abstract class Builder<T, M> extends BaseCellBuilder<TreeNode<T>, M, Builder<T, M>> {
        @Override
        public abstract AbstractTreeCell<T, M> build();
    }

}
