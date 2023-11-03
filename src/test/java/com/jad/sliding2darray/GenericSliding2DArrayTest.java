package com.jad.sliding2darray;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Disabled("Generic class test is disabled. Use specific test instead.")
class GenericSliding2DArrayTest<E> {

  protected final int nbRows;
  protected final int nbColumns;
  protected final AbstractWorld<E> world;
  protected ISliding2DArray<E> playerView;

  GenericSliding2DArrayTest(final AbstractWorld<E> world, final int nbRows, final int nbColumns) {
    this.world = world;
    this.nbRows = nbRows;
    this.nbColumns = nbColumns;
  }

  @BeforeEach
  void initEach() {
    this.playerView = new Sliding2DArray<E>(this.nbRows, this.nbColumns, this.world);
  }

  @Test
  @DisplayName("getNbRows()")
  void getNbRows() {
    assertEquals(this.nbRows, this.playerView.getNbRows(), "getNbRows()");
  }

  @Test
  @DisplayName("getNbColumns()")
  void getNbColumns() {
    assertEquals(this.nbColumns, this.playerView.getNbColumns(), "getNbColumns()");
  }

  @Test
  @DisplayName("get()")
  void get() {
    for (int row = 0; row < this.playerView.getNbRows(); row++) {
      for (int column = 0; column < this.playerView.getNbColumns(); column++) {
        assertEquals(this.world.get(row, column), this.playerView.get(row, column), "get(" + row + ", " + column + ")");
      }
    }
  }

  @Test
  @DisplayName("slide()")
  void slide() {
    this.playerView.slide(5, 3);
    assertArrayEquals(
        this.world.getArray(this.playerView.getNbRows(), this.playerView.getNbColumns(), 5, 3),
        this.playerView.getArray(), "slide(5, 3)");
  }

  @Test
  @DisplayName("slideUp()")
  void slideUp() {
    this.playerView.slideUp();
    assertArrayEquals(
        this.world.getArray(this.playerView.getNbRows(), this.playerView.getNbColumns(), -1, 0),
        this.playerView.getArray(), "slideUp()");
    for (int step = 0; step <= this.playerView.getNbRows(); step++) {
      this.playerView = new Sliding2DArray<E>(this.nbRows, this.nbColumns, this.world);
      this.playerView.slideUp(step);
      assertArrayEquals(
          this.world.getArray(this.playerView.getNbRows(), this.playerView.getNbColumns(), -step, 0),
          this.playerView.getArray(), "slideUp(" + step + ")");
    }
  }

  @Test
  @DisplayName("slideDown()")
  void slideDown() {
    this.playerView.slideDown();
    assertArrayEquals(
        this.world.getArray(this.playerView.getNbRows(), this.playerView.getNbColumns(), 1, 0),
        this.playerView.getArray(), "slideDown()");
    for (int step = 0; step <= this.playerView.getNbRows(); step++) {
      this.playerView = new Sliding2DArray<E>(this.nbRows, this.nbColumns, this.world);
      this.playerView.slideDown(step);
      assertArrayEquals(
          this.world.getArray(this.playerView.getNbRows(), this.playerView.getNbColumns(), step, 0),
          this.playerView.getArray(), "slideDown(" + step + ")");
    }
  }

  @Test
  @DisplayName("slideLeft()")
  void slideLeft() {
    this.playerView.slideLeft();
    assertArrayEquals(
        this.world.getArray(this.playerView.getNbRows(), this.playerView.getNbColumns(), 0, -1),
        this.playerView.getArray(), "slideLeft()");
    for (int step = 0; step <= this.playerView.getNbColumns(); step++) {
      this.playerView = new Sliding2DArray<E>(this.nbRows, this.nbColumns, this.world);
      this.playerView.slideLeft(step);
      assertArrayEquals(
          this.world.getArray(this.playerView.getNbRows(), this.playerView.getNbColumns(), 0, -step),
          this.playerView.getArray(), "slideLeft(" + step + ")");
    }
  }

  @Test
  @DisplayName("slideRight()")
  void slideRight() {
    this.playerView.slideRight();
    assertArrayEquals(
        this.world.getArray(this.playerView.getNbRows(), this.playerView.getNbColumns(), 0, 1),
        this.playerView.getArray(), "slideRight()");
    for (int step = 0; step <= this.playerView.getNbColumns(); step++) {
      this.playerView = new Sliding2DArray<E>(this.nbRows, this.nbColumns, this.world);
      this.playerView.slideRight(step);
      assertArrayEquals(
          this.world.getArray(this.playerView.getNbRows(), this.playerView.getNbColumns(), 0, step),
          this.playerView.getArray(), "slideRight(" + step + ")");
    }
  }

  protected abstract static class AbstractWorld<E> implements IWorld<E> {

    private final int rows;
    private final int columns;

    protected AbstractWorld(final int rows, final int columns) {
      this.rows = rows;
      this.columns = columns;
    }

    private boolean isInside(final int row, final int column) {
      return (row >= 0) && (row < this.rows) && (column >= 0) && (column < this.columns);
    }

    @Override
    public int getNbRows() {
      return this.rows;
    }

    @Override
    public int getNbColumns() {
      return this.columns;
    }

    @SuppressWarnings("unchecked")
    public final E[][] getArray(final int nbRows, final int nbColumns, final int startRow, final int startColumn) {
      final E[][] array = (E[][]) new Object[nbRows][nbColumns];
      for (int row = 0; row < nbRows; row++) {
        for (int column = 0; column < nbColumns; column++) {
          if (this.isInside(row + startRow, column + startColumn)) {
            array[row][column] = this.get(row + startRow, column + startColumn);
          } else {
            array[row][column] = null;
          }
        }
      }
      return array;
    }
  }
}