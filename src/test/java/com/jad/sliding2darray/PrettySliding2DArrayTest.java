package com.jad.sliding2darray;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jad.sliding2darray.PrettySliding2DArrayTest.PrettyPoint;
import java.awt.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PrettySliding2DArrayTest extends GenericSliding2DArrayTest<PrettyPoint> {

  PrettySliding2DArrayTest() {
    super(new AbstractWorld<>(100, 100) {
      @Override
      public PrettyPoint get(final int row, final int column) {
        return new PrettyPoint(row, column);
      }
    }, 5, 10);
  }

  @Test
  @DisplayName("Pretty test")
  @SuppressWarnings({"LawOfDemeter"})
  void prettyTest() {
    final StringBuilder playerViewString = new StringBuilder();
    final StringBuilder worldString = new StringBuilder();
    for (int row = 0; row < this.playerView.getNbRows(); row++) {
      for (int column = 0; column < this.playerView.getNbColumns(); column++) {
        playerViewString.append(this.playerView.get(row, column)).append("\t");
        worldString.append(this.world.get(row, column)).append("\t");
      }
      playerViewString.append("\n");
      worldString.append("\n");
    }
    assertEquals(worldString.toString(), playerViewString.toString(), "Pretty test");
  }

  static class PrettyPoint extends Point {

    PrettyPoint(final int x, final int y) {
      super(x, y);
    }

    @Override
    public String toString() {
      return "(" + this.x + ", " + this.y + ")";
    }
  }
}
