package maven_5_1;

public enum AppSingleton {

	 /**
     * L'enum qui contient le code du main.
     */
    ENVIRONNEMENT;
    /**
     * Execution du programme.
     */
    public void run() {

    }
    /**
     * Main.
     * @param args pour le main
     */
    public static void main(final String[] args) {
        ENVIRONNEMENT.run();
    }    	
}
