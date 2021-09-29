import JavaTrix.Matrix;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Customtest
{
    @Test
    public void testConstructor01()
    {
        double[][] vals = {{1.,2.,3.},{4.,5.,6.},{7.,8.,9.}};

        Matrix A = new Matrix(vals);

        assertEquals(A.getVals(), vals);
    }
}
