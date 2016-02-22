public class KnockKnockProtocol {
    private static final int waiting = 0;
    private static final int connected = 1;
    private static final int knockknock = 2;
    private static final int sentPhrase = 3;

    private int state = waiting;

    int theOutput = 0;
    public int processInput(int theInput) {

        if (state == waiting) {
        	if (theInput == 100)
        		theOutput = 200;
        	state = connected;
        }
        
        if (state == connected) {
        	if (theInput == 110) {
        		theOutput = 0;
        		state = waiting;
        	}
        	else if (theInput == 111) {
        		theOutput = 210;
        		state = knockknock;
        	}
        } 
        
        if (state == knockknock) 
            if (theInput == 120) {
                theOutput = 220;
                state = sentPhrase;
            }
            
        if (state == sentPhrase) {
            if (theInput == 130) {
                theOutput = 230;
                state = waiting;
            }
        }
        return theOutput;
    } // end processInput
} // end class
