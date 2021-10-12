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
    public void testConstructor02()
    {
        double[][] testVals = {{0.,0.,0.},{0.,0.,0.},{0.,0.,0.}};

        Matrix A = new Matrix(3,3);

        assertEquals(A.getVals(), testVals);
    }

    @Test
    public void testConstructor03()
    {
        double[] testVals = {1.,2.,3.,4.,5.,6.,7.,8.,9.};

        Matrix A = new Matrix(testVals, 3);

        double[][] correctVals = {{1.,4.,7.},{2.,5.,8.},{3.,6.,9.}};
        
        assertEquals(A.getVals(), correctVals);
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
    public void testIdentity()
    {
        Matrix A = Matrix.identity(3);

        double[][] correctVals = {{1.,0.,0.},{0.,1.,0.},{0.,0.,1.}};
        
        assertEquals(A.getVals(), correctVals);
    }
}
