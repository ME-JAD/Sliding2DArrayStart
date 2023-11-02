package com.jad.sliding2darray;

/**
 * The interface World.
 *
 * @param <E> the type parameter
 */
public interface IWorld<E> {

  /**
   * Gets nb rows.
   *
   * @return the nb rows
   */
  int getNbRows();

  /**
   * Gets nb columns.
   *
   * @return the nb columns
   */
  int getNbColumns();

  /**
   * Get e.
   *
   * @param row    the row
   * @param column the column
   * @return the e
   */
  E get(final int row, final int column);
}
