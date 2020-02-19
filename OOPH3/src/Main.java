public class Main {
    /**
     * @param args
     */
    public static void main (String[] args) {
        Parser parser = Parser.getInstance(args);
        parser.doMagic();
    }
}
