import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import theboard.MainFrame;

public class MainFrameTest {

    private static MainFrame sampleMainFrame;

    @BeforeClass
    public static void normalMainFrameObjectCreation(){
        sampleMainFrame = new MainFrame(800,800);
    }

    @Test
    public void mainFrameShouldNotHasNegativeWidth(){
        MainFrame mf = new MainFrame(-1000,900);
        Assert.assertFalse(mf.getWidth() < 0);
    }

    @Test
    public void mainFrameShouldNotHasNegativeHeight(){
        MainFrame mf = new MainFrame(1000,-900);
        Assert.assertFalse(mf.getHeight() < 0);
    }

    @Test(expected = NullPointerException.class)
    public void paintDrawablePanelShouldNotWorkWithNullParameter(){
        MainFrame mf = new MainFrame(100,100);
        mf.paintDrawablePanel(null);
    }

    @Test
    public void udpManagerShouldNotBeNull(){
        Assert.assertNotNull(sampleMainFrame.getUdpManager());
    }

    @Test
    public void thiknessSliderShouldNotBeNull(){
        Assert.assertNotNull(sampleMainFrame.getThiknessSlider());
    }

    @Test
    public void valueLabelShouldNotBeNull(){
        Assert.assertNotNull(sampleMainFrame.getValueLabel());
    }

    @Test
    public void drawablePanelShouldNotBeNull(){
        Assert.assertNotNull(sampleMainFrame.getDrawablePanel());
    }


}
