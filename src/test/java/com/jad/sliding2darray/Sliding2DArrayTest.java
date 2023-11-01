package com.jad.sliding2darray;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Sliding2DArrayTest {

  private static final Array2DImpl ARRAY_2D_IMPL = new Array2DImpl();
  private static final int NB_ROWS = 5;
  private static final int NB_COLUMNS = 10;
  private ISliding2DArray<Point> slidingArray;

  @BeforeEach
  void initEach() {
    this.slidingArray = new Sliding2DArray<>(Sliding2DArrayTest.NB_ROWS,
        Sliding2DArrayTest.NB_COLUMNS, Sliding2DArrayTest.ARRAY_2D_IMPL);
  }

  @Test
  void getNbRows() {
    assertEquals(Sliding2DArrayTest.NB_ROWS, this.slidingArray.getNbRows());
  }

  @Test
  void getNbColumns() {
    assertEquals(Sliding2DArrayTest.NB_COLUMNS, this.slidingArray.getNbColumns());
  }

  @Test
  void get() {
    for (int row = 0; row < this.slidingArray.getNbRows(); row++) {
      for (int column = 0; column < this.slidingArray.getNbColumns(); column++) {
        assertEquals(Sliding2DArrayTest.ARRAY_2D_IMPL.get(row, column), this.slidingArray.get(row, column));
      }
    }
    this.testValues();
  }

  private void testValues() {
    assertArrayEquals(
        Sliding2DArrayTest.ARRAY_2D_IMPL.getArray(this.slidingArray.getNbRows(), this.slidingArray.getNbColumns(),
            this.slidingArray.getSliderRowPosition(), this.slidingArray.getSliderColumnPosition()),
        this.slidingArray.getArray());
  }

  @Test
  void slide() {
    this.slidingArray.slide(5, 3);
    this.testValues();
  }

  @Test
  void slideUp() {
    this.slidingArray.slideUp();
    this.testValues();
    for (int step = 0; step <= this.slidingArray.getNbRows(); step++) {
      this.slidingArray.slideUp(step);
      this.testValues();
    }
  }

  @Test
  void slideDown() {
    this.slidingArray.slideDown();
    this.testValues();
    for (int step = 0; step <= this.slidingArray.getNbRows(); step++) {
      this.slidingArray.slideDown(step);
      this.testValues();
    }
  }

  @Test
  void slideLeft() {
    this.slidingArray.slideLeft();
    this.testValues();
    for (int step = 0; step <= this.slidingArray.getNbColumns(); step++) {
      this.slidingArray.slideLeft(step);
      this.testValues();
    }
  }

  @Test
  void slideRight() {
    this.slidingArray.slideRight();
    this.testValues();
    for (int step = 0; step <= this.slidingArray.getNbColumns(); step++) {
      this.slidingArray.slideRight(step);
      this.testValues();
    }
  }

  static class Array2DImpl implements Array2D<Point> {

    private static final int ROWS = 100;
    private static final int COLUMNS = 100;

    private static boolean isOutOfBounds(final int row, final int column) {
      return (row >= 0) && (row < Array2DImpl.ROWS) && (column >= 0) && (column < Array2DImpl.COLUMNS);
    }

    @Override
    public int getNbRows() {
      return Array2DImpl.ROWS;
    }

    @Override
    public int getNbColumns() {
      return Array2DImpl.COLUMNS;
    }

    @Override
    public Point get(final int row, final int column) {
      return new Point(row, column);
    }

    public final Point[][] getArray(final int nbRows, final int nbColumns, final int startRow, final int startColumn) {
      final Point[][] array = new Point[nbRows][nbColumns];
      for (int row = 0; row < nbRows; row++) {
        for (int column = 0; column < nbColumns; column++) {
          if (Array2DImpl.isOutOfBounds(row + startRow, column + startColumn)) {
            array[row][column] = new Point(row + startRow, column + startColumn);
          } else {
            array[row][column] = null;
          }
        }
      }
      return array;
    }
  }
}