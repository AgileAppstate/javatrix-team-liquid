package JavaTrix;

public class Matrix implements Cloneable, Serializable
{
    private double[][] vals; // Field to store Matrix data

    /**
     *
     * Constructs a Matrix object from a 2-D array
     *
     * @param A The array to be copied from
     */
    public Matrix(double[][] A)
    {
        //TODO
    }

    /**
     *
     * Constructs an m x n Matrix filled with scalar value s
     *
     * @param m The number of rows
     * @param n The number of columns
     * @param s The scalar value to fill the Matrix with
     */
    public Matrix(int m, int n, double s)
    {
        //TODO
    }

    /**
     *
     * Performs linear algebraic matrix multiplication
     *
     * @param B The matrix to multiply by the current matrix
     * 
     * @return The matrix with matrix multiplication applied
     */
    public Matrix times(Matrix B)
    {
        //TODO
    }

    /**
     *
     * Prints the matrix to stdout with elements lined up in
     * columns with a Fortran-like 'Fw.d' style format
     *
     * @param w The column width
     * @param d The number of digits after the decimal
     */
    public void print(int w, int d)
    {
        //TODO
    }
}
