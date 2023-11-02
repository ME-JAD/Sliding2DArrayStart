package com.jad.sliding2darray;

/**
 * The interface Sliding 2 d array.
 *
 * @param <E> the type parameter
 */
public interface ISliding2DArray<E> {

  /**
   * Gets nb columns.
   *
   * @return the nb columns
   */
  int getNbColumns();

  /**
   * Gets nb rows.
   *
   * @return the nb rows
   */
  int getNbRows();

  /**
   * Get e.
   *
   * @param row    the row
   * @param column the column
   * @return the E value
   */
  E get(int row, int column);

  /**
   * Slide.
   *
   * @param row    the row
   * @param column the column
   */
  void slide(int row, int column);

  /**
   * Slide up.
   */
  void slideUp();

  /**
   * Slide down.
   */
  void slideDown();

  /**
   * Slide left.
   */
  void slideLeft();

  /**
   * Slide right.
   */
  void slideRight();

  /**
   * Slide up.
   *
   * @param steps the steps
   */
  void slideUp(int steps);

  /**
   * Slide down.
   *
   * @param steps the steps
   */
  void slideDown(int steps);

  /**
   * Slide left.
   *
   * @param steps the steps
   */
  void slideLeft(int steps);

  /**
   * Slide right.
   *
   * @param steps the steps
   */
  void slideRight(int steps);

  /**
   * Get array e [ ] [ ].
   *
   * @return the e [ ] [ ]
   */
  E[][] getArray();
}
