import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import theboard.DrawablePanel;
import theboard.MainFrame;

public class DrawablePanelTest {


    @Test
    public void widthOfMainFrameCanNotBeNegative(){
        DrawablePanel dp = new DrawablePanel(new MainFrame(-800,800),100,100);
        Assert.assertFalse(dp.getMainFrame().getWidth() < 0);
    }

    @Test
    public void heightOfMainFrameCanNotBeNegative(){
        DrawablePanel dp = new DrawablePanel(new MainFrame(800,-800),100,100);
        Assert.assertFalse(dp.getMainFrame().getHeight() < 0);
    }
}
