package com.jad.sliding2darray;

import java.awt.Point;

class PointSliding2DArrayTest extends GenericSliding2DArrayTest<Point> {

  PointSliding2DArrayTest() {
    super(new AbstractWorld<>(100, 100) {
      @Override
      public Point get(final int row, final int column) {
        return new Point(row, column);
      }
    }, 5, 10);
  }

}
