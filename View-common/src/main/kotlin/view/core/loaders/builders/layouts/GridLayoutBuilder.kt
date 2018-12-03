package view.core.loaders.builders.layouts

import view.core.loaders.builders.ViewKeys
import view.core.views.layouts.GridLayout
import view.utils.extensions.nonNull
import view.utils.mapBased.keys.delegates.nullable.DoubleRWKey
import view.utils.mapBased.keys.delegates.nullable.EnumRWKey
import view.utils.mapBased.keys.delegates.nullable.IntRWKey
import view.utils.mapBased.keys.delegates.required.RequiredIntRWKey

open class GridLayoutBuilder: LayoutBuilder<GridLayout>() {

    class CellKeys: ViewKeys() {

        var row by RequiredIntRWKey
        var column by RequiredIntRWKey

        var rowSpan by IntRWKey
        var colSpan by IntRWKey

        var verticalAlignment by EnumRWKey(GridLayout.Cell.VerticalAlignment.values())
        var horizontalAlignment by EnumRWKey(GridLayout.Cell.HorizontalAlignment.values())
    }

    override val view = GridLayout()

    var rowCount by IntRWKey
    var columnCount by IntRWKey

    var horizontalSpace by DoubleRWKey
    var verticalSpace by DoubleRWKey

    override fun addChildrenToViews() {
        children.forEach { pair ->
            if (pair.second.isNotEmpty()) {
                val cell = GridLayout.Cell()
                val cellKeys = CellKeys()
                cellKeys.keys = pair.second.toMutableMap()
                cellKeys.apply {
                    cell.row = row
                    cell.column = column

                    rowSpan.nonNull { cell.rowSpan = it }
                    colSpan.nonNull { cell.columnSpan = it }

                    verticalAlignment.nonNull { cell.verticalAlignment = it }
                    horizontalAlignment.nonNull { cell.horizontalAlignment = it }
                }
                view.addChild(pair.first, cell)
            } else {
                view.addChild(pair.first)
            }
        }
    }

    override fun beforeProduction() {
        rowCount.nonNull { view.rowCount = it }
        columnCount.nonNull { view.columnCount = it }

        horizontalSpace.nonNull { view.horizontalSpace = it }
        verticalSpace.nonNull { view.verticalSpace = it }
        super.beforeProduction()
    }
}
