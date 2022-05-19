package processor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("1. Add Matrices");
        System.out.println("2. Multiply Matrix by a Constant");
        System.out.println("3. Multiply Matrices");
        System.out.println("4. Transpose Matrix");
        System.out.println("5. Calculate Determinant");
        System.out.println("6. Calculate Inverse of a Matrix");
        System.out.println("7. Exit");
        int dec = in.nextInt();

        while (dec != 7) {

            switch(dec) {

                case 1:
                    Addition();
                    break;

                case 2:
                    Multi_Const();
                    break;

                case 3:
                    Multi_Mat();
                    break;

                case 4:
                    Trans_Mat();
                    break;

                case 5:
                    Deter();
                    break;

                case 6:
                    Inverse();
                    break;

                default:
                    System.out.println("Option invalid");
                    break;
            }
            System.out.println();
            System.out.println("Enter Choice:");
            dec = in.nextInt();
        }
        in.close();
    }


    public static double[][] buildMat(int row, int col) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Matrix: ");
        double[][] mat = new double[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mat[i][j] = in.nextDouble();
            }
        }

        return mat;
    }


    public static void Addition(){

        Scanner in = new Scanner(System.in);

        System.out.println("Enter dimensions of Matrix 1: ");
        int row1 = in.nextInt();
        int col1 = in.nextInt();

        double[][] mat1 = buildMat(row1, col1);

        System.out.println("Enter dimensions of Matrix 2: ");
        int row2 = in.nextInt();
        int col2 = in.nextInt();

        if ((row1 != row2) || (col1 != col2)) {
            System.out.println("Cannot add matrices with the given dimensions.");
        }

        else {
            double[][] mat2 = buildMat(row2, col2);
            System.out.println("Added Matrix: ");
            double[][] mat = new double[row2][col2];
            for (int i = 0; i < row2; i++) {
                for (int j = 0; j < col2; j++) {
                    mat[i][j] = mat1[i][j] + mat2[i][j];
                    System.out.print(mat[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void Multi_Const(){

        Scanner in = new Scanner(System.in);

        System.out.println("Enter dimensions of Matrix: ");
        int row1 = in.nextInt();
        int col1 = in.nextInt();

        double[][] mat = buildMat(row1, col1);

        System.out.println("Enter Constant: ");
        int n = in.nextInt();

        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                mat[i][j] = mat[i][j] * n;
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void Multi_Mat(){
        Scanner in = new Scanner(System.in);

        System.out.println("Enter dimensions of Matrix 1: ");
        int row1 = in.nextInt();
        int col1 = in.nextInt();

        double[][] mat1 = buildMat(row1, col1);

        System.out.println("Enter dimensions of Matrix 2: ");
        int row2 = in.nextInt();
        int col2 = in.nextInt();

        if (col1 != row2) {
            System.out.println("Cannot multiply matrices with given dimensions.");
        }

        else {
            double[][] mat2 = buildMat(row2, col2);
            System.out.println("Multiplied Matrix: ");
            double[][] mat = new double[row1][col2];
            for(int i = 0; i < row1; i++) {
                for (int j = 0; j < col2; j++) {
                    for (int k = 0; k < col1; k++) {
                        mat[i][j] += mat1[i][k] * mat2[k][j];
                    }
                    System.out.print(mat[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void Trans_Mat() {
        Scanner in = new Scanner(System.in);
        System.out.println("Transpose matrix along: ");
        System.out.println("1. Main Diagonal");
        System.out.println("2. Side Diagonal");
        System.out.println("3. Vertical Line");
        System.out.println("4. Horizontal Line");
        int choice = in.nextInt();

        if (choice == 1) {

            System.out.println("Enter dimensions of Matrix: ");
            int row1 = in.nextInt();
            int col1 = in.nextInt();

            double[][] mat1 = buildMat(row1, col1);

            double[][] mat_transposed = new double[col1][row1];
            for (int i = 0; i < col1; i++) {
                for (int j = 0; j < row1; j++) {
                    mat_transposed[i][j] = mat1[j][i];
                    System.out.print(mat_transposed[i][j] + " ");
                }
                System.out.println();
            }
        }

        else if (choice == 2) {

            System.out.println("Enter dimensions of Matrix: ");
            int row1 = in.nextInt();
            int col1 = in.nextInt();

            double[][] mat1 = buildMat(row1, col1);

            double[][] mat_transposed = new double[col1][row1];
            for (int i = 0; i < col1; i++) {
                for (int j = 0; j < row1; j++) {
                    mat_transposed[i][j] = mat1[col1 - 1 - j][row1 - 1 - i];
                    System.out.print(mat_transposed[i][j] + " ");
                }
                System.out.println();

            }
        }

        else if (choice == 3) {

            System.out.println("Enter dimensions of Matrix: ");
            int row1 = in.nextInt();
            int col1 = in.nextInt();

            double[][] mat1 = buildMat(row1, col1);

            double[][] mat_transposed = new double[col1][row1];
            for (int i = 0; i < col1; i++) {
                for (int j = 0; j < row1; j++) {
                    mat_transposed[i][j] = mat1[i][col1 - 1 - j];
                    System.out.print(mat_transposed[i][j] + " ");
                }
                System.out.println();

            }
        }

        else if (choice == 4) {

            System.out.println("Enter dimensions of Matrix: ");
            int row1 = in.nextInt();
            int col1 = in.nextInt();

            double[][] mat1 = buildMat(row1, col1);

            double[][] mat_transposed = new double[col1][row1];
            for (int i = 0; i < col1; i++) {
                for (int j = 0; j < row1; j++) {
                    mat_transposed[i][j] = mat1[row1 - 1 - i][j];
                    System.out.print(mat_transposed[i][j] + " ");
                }
                System.out.println();

            }
        }

        else{
            System.out.println("Invalid Choice");
            System.out.println();
        }
    }

    public static void Deter(){
        Scanner in = new Scanner(System.in);

        System.out.println("Enter dimensions of Matrix: ");
        int row1 = in.nextInt();
        int col1 = in.nextInt();

        double[][] mat1 = buildMat(row1, col1);

        double det = determinant(mat1);
        System.out.println(det);

    }

    public static double determinant(double[][] arr) {

        double result = 0;

        if (arr.length == 1) {
            result = arr[0][0];
            return result;
        }

        if (arr.length == 2) {
            result = arr[0][0] * arr[1][1] - arr[0][1] * arr[1][0];
            return result;
        }

        for (int i = 0; i < arr[0].length; i++) {
            double[][] temp = new double[arr.length - 1][arr[0].length - 1];

            for (int j = 1; j < arr.length; j++) {
                for (int k = 0; k < arr[0].length; k++) {

                    if (k < i) {
                        temp[j - 1][k] = arr[j][k];
                    }

                    else if (k > i) {
                        temp[j - 1][k - 1] = arr[j][k];
                    }
                }
            }
            result += arr[0][i] * Math.pow(-1, i) * determinant(temp);
        }
        return result;
    }

    public static void Inverse(){
        Scanner in = new Scanner(System.in);

        System.out.println("Enter dimensions of Matrix: ");
        int row1 = in.nextInt();
        int col1 = in.nextInt();

        double[][] mat1 = buildMat(row1, col1);

        double[][] mat_inv = invert(mat1);
        double[][] result = new double[row1][col1];

        for (int i = 0; i < mat_inv.length; i++) {
            for (int j = 0; j < col1; j++) {
                result[i][j] = mat_inv[i][j];
                System.out.println(result[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static double[][] invert(double[][] a) {
        int n = a.length;
        double[][] x = new double[n][n];
        double[][] b = new double[n][n];
        int[] index = new int[n];
        for (int i=0; i<n; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle
        gaussian(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k] -= a[index[j]][i]*b[index[i]][k];

        // Perform backward substitutions
        for (int i=0; i<n; ++i) {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k)
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

// Method to carry out the partial-pivoting Gaussian
// elimination.  Here index[] stores pivoting order.

    public static void gaussian(double[][] a, int[] index) {
        int n = index.length;
        double[] c = new double[n];

        // Initialize the index
        for (int i=0; i<n; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        for (int i=0; i<n; ++i)
        {
            double c1 = 0;
            for (int j=0; j<n; ++j)
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        // Search the pivoting element from each column
        int k = 0;
        for (int j=0; j<n-1; ++j)
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i)
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1)
                {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i)
            {
                double pj = a[index[i]][j]/a[index[j]][j];

                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
    }

}

