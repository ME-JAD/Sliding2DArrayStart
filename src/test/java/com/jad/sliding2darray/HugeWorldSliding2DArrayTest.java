package com.jad.sliding2darray;

import java.awt.Point;

class HugeWorldSliding2DArrayTest extends GenericSliding2DArrayTest<Point> {

  HugeWorldSliding2DArrayTest() {
    super(new AbstractWorld<Point>(1000000, 1000000) {
      @Override
      public Point get(final int row, final int column) {
        return new Point(row, column);
      }
    }, 100, 1000);
  }

}
