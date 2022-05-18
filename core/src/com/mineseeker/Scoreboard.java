package com.mineseeker;

public class Scoreboard {
    private int usedFlags;
    private int flaggedBombs;

    public Scoreboard(int usedFlags, int flaggedBombs) {
        this.usedFlags = usedFlags;
        this.flaggedBombs = flaggedBombs;
    }

    public int getUsedFlags() {return usedFlags;}
//    public void setUsedFlags(int flag) {usedFlags = usedFlags + flag;}
    public void addUsedFlag() {usedFlags++;}
    public void removeUsedFlag() {usedFlags--;}

    public int getFlaggedBombs() {return flaggedBombs;}

    public void addFlaggedBomb() {this.flaggedBombs++;}
    public void removeFlaggedBomb() {this.flaggedBombs--;}

    public void CheckForWin() {
        if (flaggedBombs == MineSeeker.TOTAL_MINES){
            MineSeeker.gameIsWon = true;
        }
    }
}
