import javatrix.Matrix;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MatrixTest
{
   
    @Test
    public void testConstructor00() 
    {
	    double testScalar = 2.0;
	    int m = 2;
	    int n = 2;

	    Matrix matrix = new Matrix(m, n, testScalar);

	    double[][] correctVals = {{2.,2.},{2.,2.}};

	    assertEquals(matrix.getVals(), correctVals);
    }

    @Test
    public void testConstructor01()
    {
        double[][] testVals = {{1.,2.,3.},{4.,5.,6.},{7.,8.,9.}};

        Matrix A = new Matrix(testVals);

        assertEquals(A.getVals(), testVals);
    }
	
    @Test
    public void testTimesMatrix00()
    {
        Matrix A = new Matrix(3, 2, 5.);
      	Matrix B = new Matrix(2, 3, 3.);
        Matrix prod;
        
        prod = A.times(B);
        
        double[][] correctVals = {{30.,30.,30.},{30.,30.,30.},{30.,30.,30.}};
        
        assertEquals(prod.getVals(), correctVals);

        double[][] aVals = {{2.,5.,2.,12.,7.},{4.,32.,3.,0.,2.},{9.,3.,10.,9.,3.}};
        double[][] bVals = {{3.,2.},{35.,2.},{0.,3.},{7.,7.},{4.,5.}};

        A = new Matrix(aVals);
        B = new Matrix(bVals);

        prod = A.times(B);

        correctVals = new double[][]{{293., 139.},{1140.,91.},{207.,132.}};

        assertEquals(prod.getVals(), correctVals);
    }

    @Test
    public void testTimeScalar00()
    {
	    double scalar = 2.;
	    Matrix A = new Matrix(3, 2, 4.);
	    Matrix prod;

	    prod = A.times(scalar);

	    double[][] correctVals = {{8.,8.},{8.,8.},{8.,8.}};

	    assertEquals(prod.getVals(), correctVals);
    }

    @Test
    public void testTranspose()
    {
        double[][] aVals = {{2.,5.,2.,12.,7.},{4.,32.,3.,0.,2.},{9.,3.,10.,9.,3.}};

        Matrix A = new Matrix(aVals);

        Matrix transposeA = A.transpose();

        double[][] correctVals = {{2.,4.,9.},{5.,32.,3.},{2.,3.,10.},{12.,0.,9},{7.,2.,3}};

        assertEquals(transposeA.getVals(), correctVals);
    }

    @Test
    public void testGetRowDimension()
    {
        Matrix A = new Matrix(2, 3, 5.);
        
        double[][] bVals = {{2.,5.,2.,12.,7},{4.,32.,3.,0.,2.},{9.,3.,10.,9.,3.}};

        Matrix B = new Matrix(bVals);

        assertEquals(A.getRowDimension(), 2);
        assertEquals(B.getRowDimension(), 3);
    }

    @Test
    public void testGetColumnDimension()
    {
        Matrix A = new Matrix(2, 3, 5.);
        
        double[][] bVals = {{2.,5.,2.,12.,7},{4.,32.,3.,0.,2.},{9.,3.,10.,9.,3.}};

        Matrix B = new Matrix(bVals);

        assertEquals(A.getColumnDimension(), 3);
        assertEquals(B.getColumnDimension(), 5);
    }
}
