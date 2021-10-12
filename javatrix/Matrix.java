package javatrix;

import java.io.Serializable;
import java.util.Arrays;

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
        vals = A.clone();
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
        vals = new double[m][n];
        
        for (double[] row: vals) 
        {
            Arrays.fill(row, s);
        }
    }

    /**
     *
     * Calculates the dot product of two vectors
     *
     * @param a vector defined by array
     * @param b vector defined by array
     *
     * @return The dot product of a*b
     */
    private static double dotProduct(double[] a, double[] b) 
    {
        double dotprod = 0.0;
        
        for(int i = 0; i < a.length; i++) 
        {
            dotprod += a[i] * b[i];
        }
        
        return dotprod;
    }

    /**
     *
     * Performs linear algebraic matrix multiplication. 
     * Dot product of each row of this matrix by each col of B matrix. 
     *
     * @param B The matrix to multiply by the current matrix
     * 
     * @return The matrix with matrix multiplication applied
     */
    public Matrix times(Matrix B)
    {
        
        double[][] bVals = B.getVals(); // Used to grab columns for dot product multiplications
        int m = this.vals.length; // Rows of product matrix
	int n = bVals[0].length; // Cols of product matrix
	double[][] prod = new double[m][n];

	// These vector lengths should be the same. Calculating them both 
	// so that checking for valid multplication later is easier to implement. 
	double[] rowVector = new double[this.vals[0].length];
	double[] colVector = new double[bVals.length];

	for (int i = 0; i < m; i++) 
        {
		rowVector = this.vals[i];
		for (int j = 0; j < n; j++) 
            	{
			for (int k = 0; k < bVals.length; k++) 
                	{
				colVector[k] = bVals[k][j];
			}

                	prod[i][j] = Matrix.dotProduct(rowVector, colVector);
		}
	}
        return new Matrix(prod);
    }

    /**
     *
     * Performs matrix multiplication with scalar and provided matrix. 
     *
     * @param s The scalar to mulitpily current matrix by
     *
     * @return The matrix resulting from the multiplication
     */
    public Matrix times(double s) 
    {
	    double[][] prod = new double[this.vals.length][this.vals[0].length];

	    for (int i = 0; i < this.vals.length; i++) 
	    {
		    for (int j = 0; j < this.vals[0].length; j++)
		    {
			    prod[i][j] = this.vals[i][j] * s;
		    }
	    }

	    return new Matrix(prod);
    }

    /**
     *
     * Takes the transpose of the current matrix
     *
     * @return The transposed matrix
     */
    public Matrix transpose()
    {
        double[][] currVals = this.getVals();
        double[][] transposeVals = new double[currVals[0].length][currVals.length];

        for(int i = 0; i < currVals[0].length; i++)
        {
            for(int j = 0; j < currVals.length; j++)
            {
                transposeVals[i][j] = currVals[j][i];
            }
        }

        return new Matrix(transposeVals);
    }

    /**
     *
     * Returns the row dimension of the current matrix
     *
     * @return The row dimension
     */
    public int getRowDimension()
    {
        return this.getVals().length;
    }

    /**
     *
     * Returns the column dimension of the current matrix
     *
     * @return The column dimension
     */
    public int getColumnDimension()
    {
        return this.getVals()[0].length;
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
        int largestLength = 0;

        // Looping over Matrix to determine largest value length to
        // ensure proper printing
        for(int i = 0; i < this.vals.length; i++)
        {
            for(int j = 0; j < this.vals[i].length; j++)
            {
                int valueLength = String.valueOf(this.vals[i][j]).length(); // Calculating length of matrix value

                if(valueLength > largestLength)
                {
                    largestLength = valueLength; // Setting the largest length
                }
            }
        }

        if(largestLength > w)
        {
            w += largestLength - w + 1; // Changing the width value if it's too small to hold the largest length value. 1 is added to account for decimal
        }

        for(int i = 0; i < this.vals.length; i++)
        {
            for(int j = 0; j < this.vals[i].length; j++)
            {
                System.out.printf("%" + w + "." + d + "f ", this.vals[i][j]); // Printing with proper column width and decimal places
            }

            System.out.println();
        }
    }

    /**
     *
     * Getter for vals
     *
     * @return The vals in the current Matri
     */
    public double[][] getVals()
    {
        return vals;
    }
}
