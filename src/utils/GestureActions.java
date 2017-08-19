package utils;

import org.openqa.selenium.Dimension;

public class GestureActions extends BaseCrossPlatformDriver {

  /*
   * @approx value 0.4 for small swipe,
   * 
   * @approx value 0.5 for medium swip,
   * 
   * @approx value 0.6 for large swip
   */
  public static void swipeVerticle(double approx) throws InterruptedException {
    Thread.sleep(2000);
    Dimension dimensions = driver.manage().window().getSize();
    System.out.println(dimensions);
    Double screenHeightStart = dimensions.getHeight() * approx;
    int scrollStart = screenHeightStart.intValue();
    System.out.println("Scroll start" + scrollStart);
    Double screenHeightEnd = dimensions.getHeight() * 0.2;
    int scrollEnd = screenHeightEnd.intValue();
    System.out.println("Scroll end" + scrollEnd);
    driver.swipe(dimensions.getWidth() / 2, scrollStart, dimensions.getWidth() / 2, scrollEnd, 0);
  }

  public static void swipeHorizonatal() {
    Dimension dimensions = driver.manage().window().getSize();
    System.out.println(dimensions);
    Double WidthStart = dimensions.getWidth() * 0.6;
    int scrollStart = WidthStart.intValue();
    System.out.println("Scroll start" + scrollStart);
    Double WidthEnd = dimensions.getWidth() * 0.2;
    int scrollEnd = WidthEnd.intValue();
    System.out.println("Scroll end" + scrollEnd);
    driver.swipe(scrollStart, dimensions.getHeight() / 2, scrollEnd, dimensions.getHeight() / 2,
        1000);
  }
}
