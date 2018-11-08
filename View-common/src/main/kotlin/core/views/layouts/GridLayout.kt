package core.views.layouts

import core.views.View
import core.views.propertyDelegates.LateInitVal
import core.views.propertyDelegates.ViewProperty
import utils.validators.Validator
import utils.validators.conditions.DC
import utils.validators.conditions.IC

class GridLayout: Layout() {

    class Cell {

        enum class VerticalAlignment {
            TOP,
            BOTTOM,
            CENTER
        }

        enum class HorizontalAlignment {
            START,
            END,
            CENTER
        }

        var row = -1
        var column = -1

        var rowSpan = 1
        var columnSpan = 1

        var verticalAlignment = VerticalAlignment.CENTER
        var horizontalAlignment = HorizontalAlignment.CENTER
    }

    val cells = mutableListOf<Cell>()

    var rowCount by LateInitVal<Int>()
    var columnCount by LateInitVal<Int>()

    var horizontalSpace by ViewProperty(0.0, Validator(DC.NON_NEGATIVE))
    var verticalSpace by ViewProperty(0.0, Validator(DC.NON_NEGATIVE))

    private var allocationMatrix by LateInitVal<List<MutableList<Pair<Int, Int>?>>>()

    override fun addChild(child: View): Boolean {
        initAllocationMatrix()
        for (i in 0 until rowCount) {
            for (j in 0 until columnCount) {
                if (allocationMatrix[i][j] == null) {
                    allocationMatrix[i][j] = i to j
                    val cell = Cell().apply {
                        row = i
                        column = j
                    }
                    return addChild(child, cell)
                }
            }
        }
        throw IllegalStateException("Grid is full")
    }

    fun addChild(child: View, cell: Cell): Boolean {
        initAllocationMatrix()
        // check bounds
        if (
            cell.row < 0
            || cell.column < 0
            || cell.row + cell.rowSpan - 1 > rowCount - 1
            || cell.column + cell.columnSpan - 1 > columnCount -1
        ) {
            throw IndexOutOfBoundsException()
        }

        val allocatedRows = cell.row..(cell.row + cell.rowSpan - 1)
        val allocatedColumns = cell.column..(cell.column + cell.columnSpan - 1)

        // check if space exists for the view
        for (i in allocatedRows) {
            for (j in allocatedColumns) {
                if (allocationMatrix[i][j] != null) {
                    throw IllegalArgumentException("Cannot position View")
                }
            }
        }

        // mark cells as allocated
        for (i in allocatedRows) {
            for (j in allocatedColumns) {
                allocationMatrix[i][j] = cell.row to cell.column
            }
        }

        cells.add(cell)
        return super.addChild(child)
    }

    private fun initAllocationMatrix() {
        try {
            allocationMatrix = List(rowCount) { _ ->
                MutableList<Pair<Int, Int>?>(columnCount) { null }
            }
        } catch (e: IllegalStateException) {}
    }
}
