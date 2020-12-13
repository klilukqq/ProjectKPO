import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import theboard.StartMenu;

public class StartMenuTest {

    private static StartMenu sm;

    @BeforeClass
    public static void startMenuDefaultCreation(){
        sm = new StartMenu();
    }

    @Test
    public void nameShouldNotBeNull(){
        Assert.assertNotNull(sm.getName());
    }

    @Test
    public void ipFieldShouldNotBeNull(){
        Assert.assertNotNull(sm.getIpField());
    }

    @Test
    public void nameFieldShouldBeNullAfterCreation(){
        Assert.assertNull(sm.getNameField());
    }

    @Test
    public void thiknessSliderShouldBeNullAfterCreation(){
        Assert.assertNull(sm.getThiknessSlider());
    }

    @Test
    public void valueLabelShouldBeNullAfterCreation(){
        Assert.assertNull(sm.getValueLabel());
    }
}
