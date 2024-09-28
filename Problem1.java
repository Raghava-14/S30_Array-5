//Time = O(n)
//Space = O(1)

class Solution {
    public boolean isRobotBounded(String instructions) {
        // Initialize the initial position and direction of the robot
        int x = 0, y = 0; // starting at (0,0)
        int dx = 0, dy = 1; // facing North (direction vector (0, 1))

        // Execute the instructions
        for (char c : instructions.toCharArray()) {
            if (c == 'G') { // move one step forward in the current direction
                x += dx;
                y += dy;
            } else if (c == 'L') { // turn 90 degrees counterclockwise
                int temp = dx;
                dx = -dy;
                dy = temp;
            } else if (c == 'R') { // turn 90 degrees clockwise
                int temp = dx;
                dx = dy;
                dy = -temp;
            }
        }

        // Check if the robot returns to the initial position or not facing North
        // If the robot is facing North, it will eventually move away from the initial position and not come back.
        // If the robot returns to the initial position, it will be bounded in a circle because any further movement 
        // will only lead it to repeat the previous path.
        if (x == 0 && y == 0) { // if the robot is at the starting position
            return true; // it is bounded in a circle
        } else if (dx != 0 || dy != 1) { // if the robot is not facing North
            return true; // it is bounded in a circle
        } else {
            return false; // otherwise, it is not bounded in a circle
        }
    }
}
