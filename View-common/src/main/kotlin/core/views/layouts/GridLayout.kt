package core.views.layouts

import core.views.View
import core.views.propertyDelegates.LateInitVal
import core.views.propertyDelegates.ViewProperty
import utils.validators.Validator
import utils.validators.conditions.DC

/**
 * A layout where child views are positioned in a rectangular grid.
 *
 * A grid is divided into rows and columns. Views are placed inside a [Cell] where a cell can occupy more than one
 * row or column.
 */
open class GridLayout: Layout() {

    /**
     * Represents a container for a child view.
     *
     * A grid cell can occupy more than one row and column. In addition to that, it determines how the view is
     * positioned inside the cell.
     */
    class Cell {

        /**
         * Represents the view's vertical alignment inside the cell.
         */
        enum class VerticalAlignment {

            /**
             * Aligns the view to the top edge of the cell.
             */
            TOP,

            /**
             * Aligns the view to the bottom edge of the cell.
             */
            BOTTOM,

            /**
             * Centers the view vertically in the cell.
             */
            CENTER
        }

        /**
         * Represents the view's horizontal alignment inside the cell.
         */
        enum class HorizontalAlignment {

            /**
             * Aligns the view to the start edge of the cell.
             */
            START,

            /**
             * Aligns the view to the end edge of the cell.
             */
            END,

            /**
             * Centers the view horizontally in the cell.
             */
            CENTER
        }

        /**
         * The index of the starting row the cell.
         */
        var row = -1

        /**
         * The index of the starting columns of the cell.
         */
        var column = -1

        /**
         * The number of rows the cell occupies.
         */
        var rowSpan = 1

        /**
         * The number of columns the cell occupies.
         */
        var columnSpan = 1

        /**
         * The vertical alignment of the view inside the cell.
         */
        var verticalAlignment = VerticalAlignment.CENTER

        /**
         * The horizontal alignment of the view inside the cell.
         */
        var horizontalAlignment = HorizontalAlignment.CENTER
    }

    /**
     * Contains the cells in a grid.
     *
     * When this the layout is rendered, the i-th child view will reside in the i-th cell.
     */
    val cells = mutableListOf<Cell>()

    /**
     * The numbers of rows in the grid.
     */
    var rowCount by LateInitVal<Int>()

    /**
     * The number of columns in the grid.
     */
    var columnCount by LateInitVal<Int>()

    /**
     * The space between the grid rows.
     */
    var horizontalSpace by ViewProperty(0.0, Validator(DC.NON_NEGATIVE))

    /**
     * The space between the grid columns.
     */
    var verticalSpace by ViewProperty(0.0, Validator(DC.NON_NEGATIVE))

    private var allocationMatrix by LateInitVal<List<MutableList<Pair<Int, Int>?>>>()

    /**
     * Adds a view to the layout.
     *
     * When a view is added using this method, it would be placed in cell occupying a single row and column.
     *
     * @param child The view to be added.
     * @return True if the child was added, false otherwise.
     *
     * @throws IllegalStateException If the grid is full.
     */
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

    /**
     * Adds a view to the layout.
     *
     * @param child The view to be added.
     * @param cell The cell to contain the view.
     * @return True if the child was added, false otherwise.
     *
     * @throws IndexOutOfBoundsException If the cell lies outside of the grid bounds.
     * @throws IllegalStateException If there is no space for the cell in the grid.
     */
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
