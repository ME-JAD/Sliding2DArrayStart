package com.jad.sliding2darray;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HugeWorldSliding2DArrayTest extends GenericSliding2DArrayTest<Point> {

  private static final int NB_ROWS = 100;
  private static final int NB_COLUMNS = 1000;
  private static int nbCallsToGet = 0;

  HugeWorldSliding2DArrayTest() {
    super(new AbstractWorld<Point>(1000000, 1000000) {
      @Override
      public Point get(final int row, final int column) {
        HugeWorldSliding2DArrayTest.nbCallsToGet++;
        return new Point(row, column);
      }
    }, HugeWorldSliding2DArrayTest.NB_ROWS, HugeWorldSliding2DArrayTest.NB_COLUMNS);
  }

  @Test
  @DisplayName("Effective rotation test")
  @SuppressWarnings({"LawOfDemeter"})
  void effectiveRotationTest() {
    HugeWorldSliding2DArrayTest.nbCallsToGet = 0;
    this.playerView.slideDown();
    assertEquals(HugeWorldSliding2DArrayTest.NB_COLUMNS,
        HugeWorldSliding2DArrayTest.nbCallsToGet, "Effective rotation slideDown() test");

    HugeWorldSliding2DArrayTest.nbCallsToGet = 0;
    this.playerView.slideUp();
    assertEquals(HugeWorldSliding2DArrayTest.NB_COLUMNS,
        HugeWorldSliding2DArrayTest.nbCallsToGet, "Effective rotation slideUp() test");

    HugeWorldSliding2DArrayTest.nbCallsToGet = 0;
    this.playerView.slideRight();
    assertEquals(HugeWorldSliding2DArrayTest.NB_ROWS,
        HugeWorldSliding2DArrayTest.nbCallsToGet, "Effective rotation slideRight() test");

    HugeWorldSliding2DArrayTest.nbCallsToGet = 0;
    this.playerView.slideLeft();
    assertEquals(HugeWorldSliding2DArrayTest.NB_ROWS,
        HugeWorldSliding2DArrayTest.nbCallsToGet, "Effective rotation slideLeft() test");
  }
}
