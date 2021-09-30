import JavaTrix.Matrix;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MatrixTest
{
    @Test
    public void testConstructor01()
    {
        double[][] testVals = {{1.,2.,3.},{4.,5.,6.},{7.,8.,9.}};

        Matrix A = new Matrix(testVals);

        assertEquals(A.getVals(), testVals);
    }
}
