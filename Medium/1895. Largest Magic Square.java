/*
LeetCode 1895. Largest Magic Square
Difficulty: Medium

A magic square is a square subgrid where:
- All rows have the same sum
- All columns have the same sum
- Both diagonals have the same sum

Approach:
- Try all possible square sizes from largest to smallest
- For each size, slide a window over the grid
- Validate whether the current subgrid is a magic square
- Return immediately when the largest valid square is found

Time Complexity: O(n^4)
Space Complexity: O(1)
*/

class Solution {

    public int largestMagicSquare(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        // Maximum possible size of a square
        int maxSize = Math.min(rows, cols);

        // Try larger squares first to get the largest result early
        for (int size = maxSize; size >= 2; size--) {

            // Iterate over all possible top-left positions
            for (int r = 0; r + size - 1 < rows; r++) {
                for (int c = 0; c + size - 1 < cols; c++) {

                    // Check if the current subgrid forms a magic square
                    if (isMagic(grid, r, c, size)) {
                        return size;
                    }
                }
            }
        }

        // If no magic square larger than 1 exists
        return 1;
    }

    private boolean isMagic(int[][] grid, int r, int c, int size) {

        // Calculate the target sum using the first row
        int targetSum = 0;
        for (int i = 0; i < size; i++) {
            targetSum += grid[r][c + i];
        }

        // Check both diagonals
        int diag1 = 0, diag2 = 0;
        for (int i = 0; i < size; i++) {
            diag1 += grid[r + i][c + i];
            diag2 += grid[r + i][c + size - 1 - i];
        }

        if (diag1 != targetSum || diag2 != targetSum) {
            return false;
        }

        // Check all rows
        for (int i = 1; i < size; i++) {
            int rowSum = 0;
            for (int j = 0; j < size; j++) {
                rowSum += grid[r + i][c + j];
            }
            if (rowSum != targetSum) {
                return false;
            }
        }

        // Check all columns
        for (int i = 0; i < size; i++) {
            int colSum = 0;
            for (int j = 0; j < size; j++) {
                colSum += grid[r + j][c + i];
            }
            if (colSum != targetSum) {
                return false;
            }
        }

        // All conditions satisfied
        return true;
    }
}
