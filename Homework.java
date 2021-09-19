import stanford.karel.SuperKarel;
/**
 * Level 1: Put beepers only in the odd outside spots (e.g. 1x2, 2x1 are considered odd), then print the number how many you've put, and then
 * collect them all (do not duplicate beepers)
 * Level 2: Put beepers on all even spots, then print the number how many you've put, and finally collect them all
 * Level 3: Divide the map (using beepers) into 2 or 4 equal chambers (rectangles surrounded by walls or beepers),
 * depending on the map; see solution in 7x7; please note that you cannot put duplicate beepers.
 * Make sure to clean the map and print how many beepers you've put, then collect them all.
 *
 *
 */
public class Homework extends SuperKarel {
    //declare variable
    int Beepers;
    int IndexX;
    int IndexY;
    int MaxIndexX;
    int MaxIndexY;
    @Override
    //Execution start code from here
    public void run() {
        setBeepersInBag(1000);
        initiate();
        PutBeepersInTheCorrectPlace("Level1");
        initiate();
        PutBeepersInTheCorrectPlace("Level2");
        PutBeepersInTheCorrectPlace("Level3");
    }
    //Initiate of the variable
    private void initiate(){
        Beepers=0;//Number of beepers in the certain state
        IndexX=1;//Current index X of the karel
        IndexY=1;//Current index Y of the karel
        MaxIndexX=1000000;//Max index X of the karel initially I took randomly value
        MaxIndexY=1000000;//Max index Y of the karel initially I took randomly value
    }
    //This function Splited the problem to three levels
    private void PutBeepersInTheCorrectPlace(String Levels) {
        switch (Levels){
            case "Level1":{
                while (true){
                    PutOddOutsideSpots();
                    if(facingEast()){
                        turnLeft();
                        if(frontIsBlocked()){
                            MaxIndexY=IndexY;
                            break;
                        }
                        else {
                            move();
                            IndexY+=1;
                            if(frontIsBlocked()){
                                MaxIndexY=IndexY;
                            }
                            turnLeft();
                        }
                        IndexX=MaxIndexX;
                    }//in this Block, Karel is trying  to move to next row or if karel arrived to the final row break and finish while loop.
                    else if(facingWest()){
                        turnRight();
                        if(frontIsBlocked()){
                            MaxIndexY=IndexY;
                            break;
                        }
                        else {
                            move();
                            IndexY+=1;
                            if(frontIsBlocked()){
                                MaxIndexY=IndexY;
                            }
                            turnRight();
                        }
                        IndexX=1;
                    }//in this Block, Karel is trying  to move to next row or if karel arrived to the final row break and finish while loop.
                }
                CollectOfBeepers("Way1");
                ReturnPointOrigin();
            }break;
            case "Level2":{
                while (true){
                    PutEvenSpots();
                    if(facingEast()){
                        turnLeft();
                        if(frontIsBlocked()){
                            MaxIndexY=IndexY;
                            break;
                        }
                        else {
                            move();
                            IndexY+=1;
                            if(frontIsBlocked()){
                                MaxIndexY=IndexY;
                            }
                            turnLeft();
                        }
                        IndexX=MaxIndexX;
                    }//in this Block, Karel is trying  to move to next row or if karel arrived to the final row break and finish while loop.
                    else if(facingWest()){
                        turnRight();
                        if(frontIsBlocked()){
                            MaxIndexY=IndexY;
                            break;
                        }
                        else {
                            move();
                            IndexY+=1;
                            if(frontIsBlocked()){
                                MaxIndexY=IndexY;
                            }
                            turnRight();
                        }
                        IndexX=1;
                    }//in this Block, Karel is trying  to move to next row or if karel arrived to the final row break and finish while loop.
                }
                CollectOfBeepers("Way2");
                ReturnPointOrigin();
            }break;
            case "Level3":{
                DivideTheMap();
                ReturnPointOrigin();
                CollectOfBeepers("Way3");
                ReturnPointOrigin();
            }break;
        }
    }
    //There are three ways to collect beepers
    private void CollectOfBeepers(String Way) {
        switch (Way){
            // Way 1: collect beepers present on the edge of the map
            case "Way1":{
                while (Beepers!=0){
                    turnRight();
                    MoveAndPickBeeperstower();
                }
            }break;
            //Way 2:collect beepers present on the each row specifically on the even spots of the map
            case "Way2":{
                // karel will be junction between first column and final row.
                if(IndexY==MaxIndexY&&IndexX==MaxIndexX){
                    turnLeft();
                }
                //karel will be junction between final column and final row.
                else if(IndexX==1&&IndexY==MaxIndexY){
                    turnRight();
                }
                MoveAndPickBeeperstower();
                while (Beepers != 0) {
                    if(frontIsBlocked()){
                        if(facingEast()){
                            turnRight();
                            move();
                            IndexY-=1;
                            turnRight();
                            IndexX=MaxIndexX;
                        }//in this Block, Karel is trying  to move to next row.
                        else if(facingWest()){
                            turnLeft();
                            move();
                            IndexY-=1;
                            turnLeft();
                            IndexX=1;
                        }//in this Block, Karel is trying  to move to next row.
                    }

                    MoveAndPickBeeperstower();
                }//stop when number of beepers ==0




            }break;
            //Way 3: collect beepers present on the middle map.
            case "Way3":{

                if(MaxIndexX%2==0){
                    if(MaxIndexX !=2){
                        MoveSpecificLocation(MaxIndexX/2);
                        turnLeft();
                        MoveAndPickBeeperstower();
                        turnRight();
                        move();
                        IndexX++;
                        turnRight();
                        MoveAndPickBeeperstower();
                    }
                }//this block select two columns present in the middle and pick beepers.
                else {
                    if(MaxIndexX!=1){
                        MoveSpecificLocation((MaxIndexX/2)+1);
                        turnLeft();
                        MoveAndPickBeeperstower();
                    }
                }//this block select one column present in the middle and pick beepers.
                if(MaxIndexY%2==0){
                    if(MaxIndexY/2>1){
                        if(IndexY==1){
                            while (!(facingNorth())){
                                turnLeft();
                            }
                        }//this block in order to control direction karel when karel is in the first row it should direction to the north
                        else if(IndexY==MaxIndexY) {
                            while (!(facingSouth())){
                                turnLeft();
                            }
                        }//this block in order to control direction karel when karel is in the final row it should direction to the south
                        MoveSpecificLocation(MaxIndexY/2);
                        turnLeft();
                        MoveAndPickBeeperstower();
                        turnRight();
                        move();
                        turnRight();
                        MoveAndPickBeeperstower();
                        turnRight();
                        move();
                        turnRight();
                        MoveAndPickBeeperstower();
                    }
                }
                else {
                    if(MaxIndexY!=1){
                        if(IndexY==1){
                            while (!(facingNorth())){
                                turnLeft();
                            }
                        }//this block in order to control direction karel when karel is in the first row it should direction to the north
                        else if(IndexY==MaxIndexY) {
                            while (!(facingSouth())){
                                turnLeft();
                            }
                        }//this block in order to control direction karel when karel is in the final row it should direction to the south
                        MoveSpecificLocation((MaxIndexY/2)+1);
                        turnRight();
                        MoveAndPickBeeperstower();
                        turnLeft();
                        turnLeft();
                        MoveAndPickBeeperstower();
                    }
                }



            }break;
        }


    }
    //This function responsible about Divide the map (using beepers) into 2 or 4 equal chambers.
    private void DivideTheMap() {
        if(MaxIndexX%2==0){
            if(MaxIndexX!=2){
                MoveSpecificLocation(MaxIndexX/2);
                turnLeft();
                MoveAndPutBeeperstower();
                turnRight();
                move();
                IndexX++;
                turnRight();
                MoveAndPutBeeperstower();
            }
        }//this block select two columns present in the middle and fill them up  by beepers.
        else {
            if(MaxIndexX!=1){
                MoveSpecificLocation((MaxIndexX/2)+1);
                turnLeft();
                MoveAndPutBeeperstower();
            }
        }//this block select one column present in the middle and fill it up  by beepers.
        if(MaxIndexY%2==0){
            if(MaxIndexY !=2){
                if(IndexY==1){
                    while (!(facingNorth())){
                        turnLeft();
                    }
                }//this block in order to control direction karel when karel is in the first row it should direction to the north
                else if(IndexY==MaxIndexY) {
                    while (!(facingSouth())){
                        turnLeft();
                    }
                }//this block in order to control direction karel when karel is in the final row it should direction to the south
                MoveSpecificLocation(MaxIndexY/2);
                turnLeft();
                MoveAndPutBeeperstower();
                turnRight();
                move();
                turnRight();
                MoveAndPutBeeperstower();
                turnRight();
                move();
                turnRight();
                MoveAndPutBeeperstower();
            }
        }
        else {
            if(MaxIndexY!=1){
                if(IndexY==1){
                    while (!(facingNorth())){
                        turnLeft();
                    }
                }//this block in order to control direction karel when karel is in the first row it should direction to the north
                else if(IndexY==MaxIndexY) {
                    while (!(facingSouth())){
                        turnLeft();
                    }
                }//this block in order to control direction karel when karel is in the final row it should direction to the south
                MoveSpecificLocation((MaxIndexY/2)+1);
                turnRight();
                MoveAndPutBeeperstower();
                turnLeft();
                turnLeft();
                MoveAndPutBeeperstower();
            }
        }
    }
    //fill the row or column by beepers.
    private void MoveAndPutBeeperstower() {
        while (frontIsClear()){
            if(noBeepersPresent()){
                putBeeper();
            }
            move();
            ChangeIndex();
        }
        if(noBeepersPresent()){
            putBeeper();
        }
    }
    //arriving karel to the middle of Max index(X or Y).
    private void MoveSpecificLocation(int NumberOfMoves) {
        while (NumberOfMoves!=1){
            move();
            ChangeIndex();
            NumberOfMoves--;

        }

    }
    //Return to the point origin through move on the edge of the map
    private void ReturnPointOrigin() {
        while (true){
            if(IndexY==1&&IndexX==1){
                if(!(facingEast())){
                    turnLeft();
                }
                else {
                    break;
                }
            }
            else  {
                if(frontIsBlocked()){
                    turnLeft();
                }
                else {
                    break;
                }
                MoveToWord();
            }
        }
    }
    //Move ToWord until karel front is blocked.
    private void MoveToWord() {
        while (frontIsClear()){
            move();
            ChangeIndex();
        }
    }
    //Move and pick beepers in the specific row or column.
    private void MoveAndPickBeeperstower() {
        while (frontIsClear()){
            if(beepersPresent()){
                pickBeeper();
                Beepers--;
            }
            move();
            ChangeIndex();
        }
        if(beepersPresent()){
            pickBeeper();
            Beepers--;

        }
    }
    //changing Index based on karel movement
    private void ChangeIndex() {
        if(facingWest()){
            IndexX--;
        }
        else if(facingEast()){
            IndexX++;
        }
        else if(facingNorth()){
            IndexY++;
        }
        else if(facingSouth()){
            IndexY--;
        }
    }
    //This function represent even Spots and it is move until become karel front is blocked.
    private void PutEvenSpots() {
        while (frontIsClear()){
            CheckEvenAndPutBeeper();
            move();
            ChangeIndex();
        }
        if(IndexY==1){
            MaxIndexX=IndexX;
        }
        CheckEvenAndPutBeeper();
    }
    //This function represent Odd Outside Spots and it is move until become karel front is blocked.
    private void PutOddOutsideSpots(){
        while (frontIsClear()){
            PickAndPutBeeper();
            move();
            ChangeIndex();
        }
        if(IndexY==1){
            MaxIndexX=IndexX;
        }

        PickAndPutBeeper();
    }
    // This function put beeper in the odd out side spots place.
    // it is check At the first row ,the final row ,the first column and the final column.
    private void PickAndPutBeeper() {
        if(IndexY==1||IndexY==MaxIndexY||IndexX==1||IndexX==MaxIndexX){
            if((IndexX+IndexY)%2!=0){
                if (noBeepersPresent()) {
                    putBeeper();
                }
                Beepers++;
            }
            else{
                if (beepersPresent()) {
                    pickBeeper();
                }
            }
        }
        else {
            if (beepersPresent()) {
                pickBeeper();
            }
        }
    }
    // This function put beeper in the even spots place.
    // it is check the total value of indexX and indexY if it was an even
    //put beeper otherwise don't put  beeper.
    private void CheckEvenAndPutBeeper(){
        if((IndexX+IndexY)%2==0){
            if (noBeepersPresent()) {
                putBeeper();
                Beepers++;
            }
        }
    }
}


