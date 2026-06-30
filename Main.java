public class Main {
    public static void main(String[] args) {
        Team homeTeam = createHomeTeam();
        Team awayTeam = createAwayTeam();
        Match match = new Match(homeTeam, awayTeam);
        match.printDraft();
        match.startGame();
    }

    private static Team createHomeTeam() {
        Team team = new Team("ΟΛΥΜΠΙΑΚΟΣ");

        team.setGoalkeeper(new Player(1, "Κωνσταντής Τζολάκης", 22, "Τερματοφύλακας", 80));

        team.addDefender(new Player(2, "Francisco Ortega", 26, "Αμυντικός", 55));
        team.addDefender(new Player(3, "Παναγιώτης Ρέτσος", 26, "Αμυντικός", 70));
        team.addDefender(new Player(4, "Lorenzo Pirola", 23, "Αμυντικός", 87));
        team.addDefender(new Player(5, "Julien Biancon", 25, "Αμυντικός", 72));
        team.addDefender(new Player(6, "Marko Stamenic", 23, "Αμυντικός", 65));
    
        team.addAttacker(new Player(7, "Χαράλαμπος Κωστούλας", 18, "Επιθετικός", 80));
        team.addAttacker(new Player(8, "Luis Palma", 25, "Επιθετικός", 78));
        team.addAttacker(new Player(9, "Ayoub El Kaabi", 32, "Επιθετικός", 82));
        team.addAttacker(new Player(10, "Kristoffer Velde", 25, "Επιθετικός", 60));
        team.addAttacker(new Player(11, "Roman Yaremchuck", 29, "Επιθετικός", 76));
    
        return team;
    }

     private static Team createAwayTeam() {
        Team team = new Team("ΠΑΟΚ");
        
        team.setGoalkeeper(new Player(1, "Dominik Kotarski", 25, "Τερματοφύλακας", 77));
        
        team.addDefender(new Player(2, "Jonny Otto", 31, "Αμυντικός", 75));
        team.addDefender(new Player(3, "Rahman Baba", 30, "Αμυντικός", 73));
        team.addDefender(new Player(4, "Ανδρέας Θεοδώρου", 25, "Αμυντικός", 70));
        team.addDefender(new Player(5, "Tomasz Kedziora", 31, "Αμυντικός", 85));
        team.addDefender(new Player(6, "Omar Colley", 32, "Αμυντικός", 59));
        
        team.addAttacker(new Player(7, "Kiril Despotov", 28, "Επιθετικός", 77));
        team.addAttacker(new Player(8, "Andrija Zivkovic", 28, "Επιθετικός", 81));
        team.addAttacker(new Player(9, "Γιάννης Κωνσταστέλιας", 22, "Επιθετικός", 79));
        team.addAttacker(new Player(10, "Fedor Chalov", 27, "Επιθετικός", 59));
        team.addAttacker(new Player(11, "Ally Samata", 32, "Επιθετικός", 54));
        
        return team;
    }
}
