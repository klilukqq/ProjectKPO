import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import theboard.Primitive2D;

import java.awt.*;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;


public class primitive2DTest {

    private static Primitive2D p1;
    private static List<Primitive2D> primitives = new ArrayList<>();

    @BeforeClass
    public static void normalPrimitive2DObjectCreation() {
        p1 = new Primitive2D(Color.BLACK, 1);
    }

    @Test
    public void createdPrimitive2DObjectShouldHaveGivenColor(){
        Assert.assertEquals(Color.BLACK, p1.getColor());
    }

    @Test
    public void createdPrimitive2DObjectShouldHaveGivenThickness(){
        Assert.assertEquals(1,p1.getThikness());
    }

    @Test
    public void newPrimitive2DObjectShouldHaveEmptyPointsArray(){
        Assert.assertEquals(0,p1.getPointsCount());
    }

    @Test
    public void addPointMethodShouldAddNewPoint(){
        p1.addPoint(new Point(0,0));
        Assert.assertEquals(1,p1.getPointsCount());
    }

    @Test(expected = OutOfMemoryError.class)
    public void additionOfMAX_POINTSPointsShouldCauseOutOfMemoryException(){
        Primitive2D  pd = new Primitive2D(Color.BLACK,1);
        for (int i = 0; i < Primitive2D.MAX_POINTS; i++) {
            pd.addPoint(new Point(i+1,i+1));
        }
    }

    @Test
    public void additionOfPintsEqualsToMaxResolutionShouldWork(){
        Primitive2D  pd = new Primitive2D(Color.BLACK,1);
        for (int i = 0; i < 1900*950; i++) {
            pd.addPoint(new Point(i,i));
        }
    }

    @Test
    public void pointsInPointsListShouldBeExactlyThePintsWeAdded(){
        Primitive2D  pd = new Primitive2D(Color.BLACK,1);
        pd.addPoint(new Point(0,0));
        pd.addPoint(new Point(1,1));
        pd.addPoint(new Point(2,2));
        Assert.assertEquals(new Point(0,0),pd.getPoint(0));
        Assert.assertEquals(new Point(1,1),pd.getPoint(1));
        Assert.assertEquals(new Point(2,2),pd.getPoint(2));
    }

    @Test
    public void pointsListWeReturnFromPrimitive2DObjectShouldBeSameAsExpected(){
        Primitive2D  pd = new Primitive2D(Color.BLACK,1);
        List<Point> points = new ArrayList<Point>();
        for (int i = 0; i < 2; i++) {
            pd.addPoint(new Point(i,i));
            points.add(new Point(i,i));
        }
        Assert.assertEquals(points,pd.getPoints());
    }

    @Test
    public void pointsArrayAfterCreatingAnObjectIsNotNull() {
        Primitive2D  pd = new Primitive2D(Color.BLACK,1);
        Assert.assertNotNull(pd.getPoints());
    }

//    @Before
//    public void weShouldNotBeAbleToAddEmptyValues() throws Exception{
//        primitives.add(new Primitive2D(Color.RED,1));
//        primitives.add(new Primitive2D(Color.YELLOW,2));
//        primitives.add(new Primitive2D(Color.GREEN,3));
//
//        primitives.add(new Primitive2D(null,1));
//        primitives.add(new Primitive2D(Color.BLACK,0));
//
//    }
//
//    @Test
//    public void newPrimitive_EMPTY_COLOR(){
//        for(Primitive2D primitive2D: primitives){
//            if (primitive2D.getColor() == null) {
//                Assert.fail("Попытка создания примитива с пустым цветом");
//            }
//        }
//    }
//
//    @Test
//    public void newPrimitive_ZERO_THIKNESS(){
//        for(Primitive2D primitive2D: primitives){
//            if (primitive2D.getThikness() == 0) {
//                Assert.fail("Попытка создания примитива с нулевой толщиной");
//            }
//        }
//    }

}
