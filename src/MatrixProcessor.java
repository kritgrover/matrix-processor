import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("deprecation")
public class MatrixProcessor {

    public static <T extends Number> Matrix<T> sum(Matrix<T> a, Matrix<T> b) {
        if (a.getRows() != b.getRows()
                || a.getColumns() != b.getColumns()) {
            throw new IllegalArgumentException("The operation cannot be performed.");
        }
        int rows = a.getRows();
        int columns = a.getColumns();

        List<List<T>> data = new ArrayList<>();
        List<List<T>> aTable = a.getTable();
        List<List<T>> bTable = b.getTable();

        for (int i = 0; i < rows; i++) {
            List<T> r = new ArrayList<>();
            List<T> aR = aTable.get(i);
            List<T> bR = bTable.get(i);
            for (int j = 0; j < columns; j++) {
                r.add(add(aR.get(j), bR.get(j)));
            }
            data.add(r);
        }

        return new Matrix<>(rows, columns, data);
    }

    public static <T> Matrix<T> scalarMultiplication(Matrix<T> matrix, double scalar) {
        int rows = matrix.getRows();
        int columns = matrix.getColumns();

        List<List<T>> data = new ArrayList<>();
        List<List<T>> mTable = matrix.getTable();
        for (int i = 0; i < rows; i++) {
            List<T> r = new ArrayList<>();
            List<T> rTable = mTable.get(i);
            for (int j = 0; j < columns; j++) {
                r.add(multiplyByS(rTable.get(j), scalar));
            }
            data.add(r);
        }

        return new Matrix<>(rows, columns, data);
    }

    public static <T> Matrix<T> matrixMultiplication(Matrix<T> a, Matrix<T> b) {
        if (a.getColumns() != b.getRows()) {
            throw new IllegalArgumentException("The operation cannot be performed.");
        }

        int rows = a.getRows();
        int columns = b.getColumns();

        List<List<T>> data = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            List<T> r = a.getRow(i);
            List<T> rTable = new ArrayList<>();

            for (int j = 0; j < columns; j++) {
                List<T> c = b.getColumn(j);
                T sum = getZero(c.get(0));
                for (int k = 0; k < r.size(); k++) {
                    sum = add(sum, multiply(r.get(k), c.get(k)));
                }
                rTable.add(sum);
            }
            data.add(rTable);
        }

        return new Matrix<>(rows, columns, data);
    }


    public static <T> Matrix<T> mdTransposition(Matrix<T> matrix) {

        int rows = matrix.getRows();
        int columns = matrix.getColumns();
        List<List<T>> data = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            List<T> row = matrix.getRow(i);
            for (int j = 0; j < row.size(); j++) {
                if (i == 0) {
                    List<T> rTable = new ArrayList<>();
                    rTable.add(row.get(j));
                    data.add(rTable);
                } else {
                    List<T> rTable = data.get(j);
                    rTable.add(row.get(j));
                    data.set(j, rTable);
                }

            }
        }

        return new Matrix<>(rows, columns, data);
    }


    public static <T> Matrix<T> sdTransposition(Matrix<T> matrix) {

        int rows = matrix.getRows();
        int columns = matrix.getColumns();
        List<List<T>> data = new ArrayList<>();

        for (int i = rows - 1; i >= 0; i--) {
            List<T> row = matrix.getRow(i);
            Collections.reverse(row);
            for (int j = 0; j < row.size(); j++) {
                if (i == rows - 1) {
                    List<T> rTable = new ArrayList<>();
                    rTable.add(row.get(j));
                    data.add(rTable);
                } else {
                    List<T> rTable = data.get(j);
                    rTable.add(row.get(j));
                    data.set(j, rTable);
                }

            }
        }

        return new Matrix<>(rows, columns, data);
    }


    public static <T> Matrix<T> vlTransposition(Matrix<T> matrix) {

        int rows = matrix.getRows();
        int columns = matrix.getColumns();
        List<List<T>> data = new ArrayList<>();

        for (int i = columns - 1; i >= 0; i--) {
            List<T> column = matrix.getColumn(i);
            for (int j = 0; j < column.size(); j++) {
                if (i == columns - 1) {
                    List<T> rTable = new ArrayList<>();
                    rTable.add(column.get(j));
                    data.add(rTable);
                } else {
                    List<T> rTable = data.get(j);
                    rTable.add(column.get(j));
                    data.set(j, rTable);
                }

            }
        }

        return new Matrix<>(rows, columns, data);
    }


    public static <T> Matrix<T> hlTransposition(Matrix<T> matrix) {

        int rows = matrix.getRows();
        int columns = matrix.getColumns();
        List<List<T>> data = new ArrayList<>();

        for (int i = rows - 1; i >= 0; i--) {
            List<T> row = matrix.getRow(i);
            data.add(row);
        }

        return new Matrix<>(rows, columns, data);
    }


    public static <T> T determinant(Matrix<T> matrix) {
        if (matrix.getRows() != matrix.getColumns()) {
            throw new IllegalArgumentException(
                    "The determinant can be computed only from the elements of a square matrix.");
        }


        if (matrix.getRows() == 1) {
            return matrix.getItem(0, 0);
        }


        if (matrix.getRows() == 2) {
            T a00 = matrix.getItem(0, 0);
            T a11 = matrix.getItem(1, 1);
            T a10 = matrix.getItem(1, 0);
            T a01 = matrix.getItem(0, 1);
            T mD = multiply(a00, a11);
            T sD = multiply(a10, a01);
            sD = multiplyByS(sD, -1);
            return add(mD, sD);
        }


        List<T> row0 = matrix.getRow(0);
        T det = getZero(row0.get(0));
        for (int j = 0; j < row0.size(); j++) {
            int signal = (int) Math.pow(-1, j);
            Matrix<T> minor = getMatrixExcluding(0, j, matrix);
            T item = row0.get(j);
            T detMinor = determinant(minor);
            detMinor = multiplyByS(detMinor, signal);
            item = multiply(item, detMinor);
            det = add(det, item);
        }

        return det;
    }

    public static <T> Matrix<Double> inverseMatrix(Matrix<T> matrix) {


        List<List<Double>> data = new ArrayList<>();
        for (int i = 0; i < matrix.getRows(); i++) {
            List<Double> r_i = new ArrayList<>();
            for (int j = 0; j < matrix.getColumns(); j++) {
                T item = matrix.getItem(i, j);
                if (item instanceof Integer) {
                    int itemInt = (Integer) item;
                    r_i.add((double) itemInt);
                } else if (item instanceof Double) {
                    double itemDouble = (Double) item;
                    r_i.add(itemDouble);
                }
            }
            data.add(r_i);
        }
        Matrix<Double> matrixDouble = new Matrix<>(matrix.getRows(), matrix.getColumns(), data);


        Double det = determinant(matrixDouble);
        if (det == 0) {
            throw new IllegalArgumentException("This matrix doesn't have an inverse.");
        }

        double oneOverDet = 1.0 / det;

        Matrix<Double> cof = cofactorMatrix(matrixDouble);
        cof = mdTransposition(cof);

        return scalarMultiplication(cof, oneOverDet);
    }


    private static <T> Matrix<T> cofactorMatrix(Matrix<T> matrix) {

        int rows = matrix.getRows();
        int columns = matrix.getColumns();
        List<List<T>> data = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            List<T> r_i = matrix.getRow(i);
            List<T> rC = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                int signal = (int) Math.pow(-1, i + j);
                Matrix<T> m0 = getMatrixExcluding(i, j, matrix);
                T det = determinant(m0);
                det = multiplyByS(det, signal);
                rC.add(det);
            }
            data.add(rC);
        }

        return new Matrix<>(rows, columns, data);
    }


    private static <T> Matrix<T> getMatrixExcluding(int row, int column, Matrix<T> matrix) {
        List<List<T>> data = new ArrayList<>();
        for (int i = 0; i < matrix.getRows(); i++) {
            if (i != row) {
                List<T> r0 = new ArrayList<>();
                for (int j = 0; j < matrix.getColumns(); j++) {
                    if (j != column) {
                        T item = matrix.getItem(i, j);
                        r0.add(item);
                    }
                }
                data.add(r0);
            }
        }

        return new Matrix<>(matrix.getRows() - 1, matrix.getColumns() - 1,  data);
    }

    private static <T> T add(T x, T y){

        if (x == null || y == null) {
            return null;
        }

        if (x instanceof Double) {
            return (T) new Double((Double) x + (Double) y);
        } else if (x instanceof Integer) {
            return (T)new Integer((Integer) x + (Integer) y);
        } else {
            throw new IllegalArgumentException("Type " + x.getClass() + " is not supported by this method");
        }
    }

    private static <T> T multiplyByS(T x, double s){

        if (x == null) {
            return null;
        }

        if (x instanceof Double) {
            return (T) new Double((Double) x * s);
        } else if (x instanceof Integer) {
            return (T)new Integer((Integer) x * (int)s);
        } else {
            throw new IllegalArgumentException("Type " + x.getClass() + " is not supported by this method");
        }
    }

    private static <T> T multiply(T x, T y){

        if (x == null || y == null) {
            return null;
        }

        if (x instanceof Double) {
            return (T) new Double((Double) x * (Double) y);
        } else if (x instanceof Integer) {
            return (T)new Integer((Integer) x * (Integer) y);
        } else {
            throw new IllegalArgumentException("Type " + x.getClass() + " is not supported by this method");
        }
    }

    private static <T> T getZero(T data){
        if (data instanceof Double) {
            return (T) new Double(0);
        } else if (data instanceof Integer) {
            return (T) new Integer(0);
        }

        return null;
    }

}
