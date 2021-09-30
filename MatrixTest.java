import JavaTrix.Matrix;
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

}
