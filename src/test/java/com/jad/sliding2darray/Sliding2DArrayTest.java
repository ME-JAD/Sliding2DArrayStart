package com.jad.sliding2darray;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Sliding2DArrayTest {

  private final int nbRows = 5;
  private final int nbColumns = 10;
  private final IWorldImpl world = new IWorldImpl();
  private ISliding2DArray<Point> playerView;

  @BeforeEach
  void initEach() {
    this.playerView = new Sliding2DArray<>(this.nbRows, this.nbColumns, this.world);
  }

  @Test
  void getNbRows() {
    assertEquals(this.nbRows, this.playerView.getNbRows(), "getNbRows()");
  }

  @Test
  void getNbColumns() {
    assertEquals(this.nbColumns, this.playerView.getNbColumns(), "getNbColumns()");
  }

  @Test
  void get() {
    for (int row = 0; row < this.playerView.getNbRows(); row++) {
      for (int column = 0; column < this.playerView.getNbColumns(); column++) {
        assertEquals(this.world.get(row, column), this.playerView.get(row, column), "get(" + row + ", " + column + ")");
      }
    }
  }

  @Test
  void slide() {
    this.playerView.slide(5, 3);
    assertArrayEquals(
        this.world.getArray(this.playerView.getNbRows(), this.playerView.getNbColumns(), 5, 3),
        this.playerView.getArray(), "slide(5, 3)");
  }

  @Test
  void slideUp() {
    this.playerView.slideUp();
    assertArrayEquals(
        this.world.getArray(this.playerView.getNbRows(), this.playerView.getNbColumns(), -1, 0),
        this.playerView.getArray(), "slideUp()");
    for (int step = 0; step <= this.playerView.getNbRows(); step++) {
      this.playerView = new Sliding2DArray<>(this.nbRows, this.nbColumns, this.world);
      this.playerView.slideUp(step);
      assertArrayEquals(
          this.world.getArray(this.playerView.getNbRows(), this.playerView.getNbColumns(), -step, 0),
          this.playerView.getArray(), "slideUp(" + step + ")");
    }
  }

  @Test
  void slideDown() {
    this.playerView.slideDown();
    assertArrayEquals(
        this.world.getArray(this.playerView.getNbRows(), this.playerView.getNbColumns(), 1, 0),
        this.playerView.getArray(), "slideDown()");
    assertEquals(new Point(1, 0), this.playerView.get(0, 0));
    for (int step = 0; step <= this.playerView.getNbRows(); step++) {
      this.playerView = new Sliding2DArray<>(this.nbRows, this.nbColumns, this.world);
      this.playerView.slideDown(step);
      assertArrayEquals(
          this.world.getArray(this.playerView.getNbRows(), this.playerView.getNbColumns(), step, 0),
          this.playerView.getArray(), "slideDown(" + step + ")");
    }
  }

  @Test
  void slideLeft() {
    this.playerView.slideLeft();
    assertArrayEquals(
        this.world.getArray(this.playerView.getNbRows(), this.playerView.getNbColumns(), 0, -1),
        this.playerView.getArray(), "slideLeft()");
    for (int step = 0; step <= this.playerView.getNbColumns(); step++) {
      this.playerView = new Sliding2DArray<>(this.nbRows, this.nbColumns, this.world);
      this.playerView.slideLeft(step);
      assertArrayEquals(
          this.world.getArray(this.playerView.getNbRows(), this.playerView.getNbColumns(), 0, -step),
          this.playerView.getArray(), "slideLeft(" + step + ")");
    }
  }

  @Test
  void slideRight() {
    this.playerView.slideRight();
    assertArrayEquals(
        this.world.getArray(this.playerView.getNbRows(), this.playerView.getNbColumns(), 0, 1),
        this.playerView.getArray(), "slideRight()");
    for (int step = 0; step <= this.playerView.getNbColumns(); step++) {
      this.playerView = new Sliding2DArray<>(this.nbRows, this.nbColumns, this.world);
      this.playerView.slideRight(step);
      assertArrayEquals(
          this.world.getArray(this.playerView.getNbRows(), this.playerView.getNbColumns(), 0, step),
          this.playerView.getArray(), "slideRight(" + step + ")");
    }
  }

  private static class IWorldImpl implements IWorld<Point> {

    private static final int ROWS = 100;
    private static final int COLUMNS = 100;

    private static boolean isInside(final int row, final int column) {
      return (row >= 0) && (row < IWorldImpl.ROWS) && (column >= 0) && (column < IWorldImpl.COLUMNS);
    }

    @Override
    public int getNbRows() {
      return IWorldImpl.ROWS;
    }

    @Override
    public int getNbColumns() {
      return IWorldImpl.COLUMNS;
    }

    @Override
    public Point get(final int row, final int column) {
      return new Point(row, column);
    }

    public final Point[][] getArray(final int nbRows, final int nbColumns, final int startRow, final int startColumn) {
      final Point[][] array = new Point[nbRows][nbColumns];
      for (int row = 0; row < nbRows; row++) {
        for (int column = 0; column < nbColumns; column++) {
          if (IWorldImpl.isInside(row + startRow, column + startColumn)) {
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