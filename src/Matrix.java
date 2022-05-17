import java.util.ArrayList;
import java.util.List;

public class Matrix<T>{

    private final int rows;
    private final int columns;

    private final List<List<T>> table;

    public Matrix(int rows, int columns, List<List<T>> table) {
        this.rows = rows;
        this.columns = columns;

        this.table = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<T> list = new ArrayList<>(table.get(i));
            this.table.add(list);
        }

    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<List<T>> getTable() {
        List<List<T>> data = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<T> list = new ArrayList<>(this.table.get(i));
            data.add(list);
        }
        return data;
    }

    public T getItem(int row, int column) {
        if (row < 0 || row > this.rows
                || column < 0 || column > this.columns) {
            throw new IllegalArgumentException(String.format("value invalid for row or column:" +
                    "\nrows go from 0 - %d \n columns go from 0 - %s", this.rows, this.columns));
        }

        List<T> r = this.table.get(row);

        return r.get(column);
    }

    public List<T> getRow(int rowNumber) {
        if (rowNumber > this.rows || rowNumber < 0) {
            throw new IllegalArgumentException("value invalid for 'rowNumber' -> " + rowNumber);
        }

        List<T> r = new ArrayList<>();
        List<T> list = this.table.get(rowNumber);

        for (int i = 0; i < this.columns; i++) {
            r.add(list.get(i));
        }

        return r;
    }

    public List<T> getColumn(int columnNumber) {
        if (columnNumber > this.columns || columnNumber < 0) {
            throw new IllegalArgumentException("value invalid for 'columnNumber' -> " + columnNumber);
        }

        List<T> c = new ArrayList<>();

        for (int i = 0; i < this.rows; i++) {
            List<T> list = this.table.get(i);
            c.add(list.get(columnNumber));
        }

        return c;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        this.table.forEach(row -> {
            row.forEach(item -> sb.append(item).append(" "));
            sb.append("\n");
        });
        return sb.toString();
    }
}