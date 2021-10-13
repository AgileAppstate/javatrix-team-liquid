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
     * Constructs an m x n Matrix filled with 0's
     *
     * @param m The number of rows
     * @param n The number of columns
     */
    public Matrix(int m, int n)
    {
        vals = new double[m][n];
    }

    /**
     *
     * Constructs an m x n Matrix filled with values from provided 
     * 2d array and fills anything missing with 0's. 
     * 
     * @param A 2d array of doubles to be stored within matrix
     * @param m The number of rows
     * @param n The number of columns
     */
    public Matrix(double[][] A, int m, int n)
    {
        vals = new double[m][n];
        int A_m = A.length;
        int A_n = A[0].length;
        
        for (int i = 0; i < m && i < A_m; i++) 
        {
            for (int j = 0; j < n && j < A_n; j++)
            {
                vals[i][j] = A[i][j];
            }
        }
    }

    /**
     *
     * Constructs a Matrix with n columns filled with values from provided 
     * packed vals array. 
     * 
     * @param A array of doubles to be stored within matrix
     * @param n The number of columns
     */
    public Matrix(double[] A, int n)
    {
        if (A.length % n == 0)
        {
            int m = A.length / n;
            vals = new double[m][n];
            for (int i = 0; i < A.length; i++) 
            {
                vals[i%m][(int)(i/m)] = A[i];
            }
        } else 
        {
            throw new IllegalArgumentException("Array length must be a multiple of n");
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
        
        return Math.floor(dotprod * 100) / 100;
    }

    /**
     *
     * Create identity matrix of given size. 
     * (must be square matrix)
     *
     * @param j number of rows and columns 
     *
     * @return The identity matrix of size j by j
     */
    public static Matrix identity(int j) 
    {
        double[][] identityVals = new double[j][j];
        
        for(int i = 0; i < j; i++) 
        {
            identityVals[i][i] = 1.;
        }
        
        return new Matrix(identityVals);
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

    /**
     * 
     * Getter for "sub" matrix defined by provided indicies
     *
     * @return The vals in the defined sub matrix
     */
    public double[][] getMatrix(int i0, int i1, int j0, int j1)
    {
        int m_sub = i1 - i0;
        int n_sub = j1 - j0;

        System.out.println(m_sub);
        System.out.println(n_sub);
        System.out.println(vals.length);
        System.out.println(vals[0].length);

        if (m_sub < 0 || n_sub < 0 || j1 >= vals.length || i1 >= vals[0].length) 
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        double[][] subVals = new double[m_sub+1][n_sub+1];
        for (int i = i0; i <= i1; i++)
        {
            for (int j = j0; j<= j1; j++)
            {
                System.out.println("inner for loop: i = " + i + " and i-i0 = " + (i - i0));
                System.out.println("                j = " + j + " and j-j0 = " + (j - j0));
                subVals[j-j0][i-i0] = vals[j][i];
            }
        }
        return subVals;
    }
}
