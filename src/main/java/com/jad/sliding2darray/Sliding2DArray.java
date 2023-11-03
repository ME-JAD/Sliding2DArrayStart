package com.jad.sliding2darray;

/**
 * The type Sliding 2 d array.
 *
 * @param <E> the type parameter
 */
public class Sliding2DArray<E> implements ISliding2DArray<E> {

  private final int nbColumns;
  private final int nbRows;
  private final E[][] array;
  private final IWorld<E> world;
  private int columnPositionInWorld = 0;
  private int rowPositionInWorld = 0;

  /**
   * Instantiates a new Sliding 2 d array.
   *
   * @param nbRows    the nb rows
   * @param nbColumns the nb columns
   * @param world     the world
   */
  @SuppressWarnings("unchecked")
  public Sliding2DArray(final int nbRows, final int nbColumns, final IWorld<E> world) {
    this.nbColumns = nbColumns;
    this.nbRows = nbRows;
    this.array = (E[][]) new Object[nbRows][nbColumns];
    this.world = world;
    this.fill();
  }

  @Override
  public final int getNbColumns() {
    return this.nbColumns;
  }

  @Override
  public final int getNbRows() {
    return this.nbRows;
  }

  @Override
  public final E get(final int row, final int column) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public final void slide(final int row, final int column) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public final void slideUp() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public final void slideDown() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public final void slideLeft() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public final void slideRight() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public final void slideUp(final int steps) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public final void slideDown(final int steps) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public final void slideLeft(final int steps) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public final void slideRight(final int steps) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public final E[][] getArray() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  protected final void fill() {
    
  }
}