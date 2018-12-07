import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestSudoku {

    SudokuKiller solver;

    @Before
    public void setUp() throws Exception {
        solver = new SudokuKiller();
    }

    @After
    public void tearDown() throws Exception {
        solver = null;
    }

    @Test
    public void testEmptySudoku() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                solver.setValue(r, c, 0);
            }
        }
        boolean trueIfSolved = solver.solve();
        assertTrue("Solve doesn't return true", trueIfSolved);
    }

    @Test
    public void testFigure1() {
        solver.setValue(0, 2, 8);
        solver.setValue(0, 5, 9);
        solver.setValue(0, 7, 6);
        solver.setValue(0, 8, 2);
        solver.setValue(1, 8, 5);
        solver.setValue(2, 0, 1);
        solver.setValue(2, 2, 2);
        solver.setValue(2, 3, 5);
        solver.setValue(3, 3, 2);
        solver.setValue(3, 4, 1);
        solver.setValue(3, 7, 9);
        solver.setValue(4, 1, 5);
        solver.setValue(4, 6, 6);
        solver.setValue(5, 0, 6);
        solver.setValue(5, 7, 2);
        solver.setValue(5, 8, 8);
        solver.setValue(6, 0, 4);
        solver.setValue(6, 1, 1);
        solver.setValue(6, 3, 6);
        solver.setValue(6, 5, 8);
        solver.setValue(7, 0, 8);
        solver.setValue(7, 1, 6);
        solver.setValue(7, 4, 3);
        solver.setValue(7, 6, 1);
        solver.setValue(8, 6, 4);
        boolean trueIfSolved = solver.solve();
        assertTrue("Solve doesn't return true", trueIfSolved);
        System.out.println("Figure 1 solution:");
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                System.out.print(solver.getValue(r, c) + " ");
            }
            System.out.print("\n");
        }
    }

    @Test
    public void testUnsolvable() {
        solver.setValue(0, 0, 1);
        solver.setValue(0, 1, 2);
        solver.setValue(0, 2, 3);
        solver.setValue(1, 0, 4);
        solver.setValue(1, 1, 5);
        solver.setValue(1, 2, 6);
        solver.setValue(2, 3, 7);
        boolean trueIfSolved = solver.solve();
        assertFalse("Unsolvable returned true", trueIfSolved);
    }
}
