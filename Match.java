import java.util.List;

public class Match {
    private Team homeTeam;
    private Team awayTeam;
    private boolean gameStarted;
    

    public Match(Team homeTeam, Team awayTeam){
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.gameStarted = false;
        
    }

    //Εμφάνιση καρτέλας αγώνα
    public void printDraft(){
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    IT'S MATCH DAY !!!                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        
        homeTeam.printTeam(); //            ΓΗΠΕΔΟΥΧΟΙ....

        System.out.println("\n                          VS                          ");
        
        awayTeam.printTeam(); //            ΦΙΛΟΞΕΝΟΎΜΕΝΟΙ
        
        System.out.println("\n" + "=".repeat(60));
    }
    
    public void startGame(){
            if(gameStarted){
                System.out.println("Το παιχνίδη έχει ξεκινήσει!");
                return;
            }

            gameStarted = true;
            System.out.println("\n⚽O ΔΙΑΙΤΗΤΗΣ ΣΦΥΡΊΖΕΙ ΤΗΝ ΈΝΑΡΞΗ ΤΟΥ ΑΓΏΝΑ⚽");
            System.out.println(homeTeam.getName() + " vs " + awayTeam.getName());

            playHalf(1);
            playHalf(2);

            printResults();
            printBestOffencePlayers();
            printWorstDefencePlayers();

        }



    //Ημίχρονο private γιατί θελω να υλοποιείται μόνο οταν καλώ την startGame()
    private void playHalf(int halfNumber){
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           " + halfNumber + "ο ΗΜΙΧΡΟΝΟ");
        System.out.println("=".repeat(50));

        

        //10 επιθέσεις ανα ημίχρονο εναλλάξ
        for(int i = 0; i<10; i++){
            if(i % 2 == 0){
                offense(homeTeam, awayTeam, i/2 + 1);
            }
            else{
                offense(awayTeam, homeTeam, i/2 + 2);
            }

            //Παύση για ρεαλιστικότητα
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\nΤέλος " + halfNumber + "ου ημιχρόνου");
        System.out.println("Σκορ: " + homeTeam.getName() + " " + homeTeam.getScore() +  " - " + awayTeam.getScore() + " " + awayTeam.getName());
    }

    //Επίθεση
    public void offense(Team attackingTeam, Team defendingTeam, int attackNumber){
        //Eπιλογή παικτών τυχαία
        Player attacker = attackingTeam.getRandomAttacker();
        Player defender = defendingTeam.getRandomDefender();
        Player goalkeeper = defendingTeam.getGoalkeeper();

        System.out.printf("\n⚽ Επίθεση #%d - %s:\n", attackNumber, attackingTeam.getName());
        System.out.printf("   Επιθετικός: %s (Ικανότητα: %d)\n", 
                         attacker.getName(), attacker.getSkill());
        System.out.printf("   Αμυντικός: %s (Ικανότητα: %d)\n", 
                         defender.getName(), defender.getSkill());


        //Συνθήκη που ο επιθετικός περνάει
        if (attacker.getSkill() > defender.getSkill()) {
            defender.wasPassed();
            System.out.printf("   ✅ Ο %s πέρασε τον %s!\n", 
                             attacker.getName(), defender.getName());
            
            //Σύγκριση με τερματοφύλακα
            System.out.printf("   Τερματοφύλακας: %s (Ικανότητα: %d)\n", 
                             goalkeeper.getName(), goalkeeper.getSkill());
        
            //Γκολ για αληθή συνθήκη με καταλόγηση σε παίκτη και ομάδα
            if (attacker.getSkill() > goalkeeper.getSkill()) {
                    attacker.scoreGoal();
                    attackingTeam.scoreGoal();
                    System.out.printf("   🥅 ΓΚΟΛ!!!! Ο %s σκοράρει!\n", attacker.getName());
                    System.out.printf("   Νέο σκορ: %s %d - %d %s\n", 
                                    homeTeam.getName(), homeTeam.getScore(),
                                    awayTeam.getScore(), awayTeam.getName());
                }
            //Αλλιώς απόκρουση
            else {
                System.out.printf("   🧤 Απόκρουση! Ο %s σώζει!\n", goalkeeper.getName());
            }
        }
        //Αλλιώς κόψιμο
        else {
            System.out.printf("   🛡️ Ο %s σταματάει την επίθεση!\n", defender.getName());
        }

    }

    //Εμφάνιση αποτελέσματος
    public void printResults() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    ΤΕΛΙΚΟ ΑΠΟΤΕΛΕΣΜΑ");
        System.out.println("=".repeat(60));
        
        System.out.println(homeTeam.getName() + "(" + homeTeam.getScore() + ") - " + awayTeam.getName() + "(" + awayTeam.getScore() + ")" );
        
        //Επιλογή νικητή με τα περισσότερα γκολ
        if (homeTeam.getScore() > awayTeam.getScore()) {
            System.out.println("🏆 ΝΙΚΗΤΗΣ: " + homeTeam.getName());
        } else if (awayTeam.getScore() > homeTeam.getScore()) {
            System.out.println("🏆 ΝΙΚΗΤΗΣ: " + awayTeam.getName());
        } else {
            System.out.println("🤝 ΙΣΟΠΑΛΙΑ");
        }
        
        System.out.println("=".repeat(60));
    }

    //Καλύτεροι επιθετικοι απο κάθε ομαδα και καλυτερος σκορερ
    public void printBestOffencePlayers() {
        System.out.println("\n🌟 ΚΑΛΥΤΕΡΟΙ ΕΠΙΘΕΤΙΚΟΙ:");
        
        //Ανάκτηση τοπ σκόρερ από κάθε ομάδα
        List<Player> homeBest = homeTeam.getBestAttackers();
        List<Player> awayBest = awayTeam.getBestAttackers();
        
        //Εμφάνιση καλύτερων από κάθε ομάδα
        System.out.printf("   %s:\n", homeTeam.getName());
        if (homeBest.isEmpty()) {
        System.out.println("     ❌ Δεν βρέθηκε σκόρερ");
        } 
        else {
            for (Player player : homeBest) {
                System.out.printf("     %s - %d γκολ\n", player.getName(), player.getGoalsScored());
            }
        }
        
         System.out.printf("   %s:\n", awayTeam.getName());
        if (awayBest.isEmpty()) {
            System.out.println("     ❌ Δεν βρέθηκε σκόρερ");
        }  
        else {
            for (Player player : awayBest) {
                System.out.printf("     %s - %d γκολ\n", player.getName(), player.getGoalsScored());
            }
        }
        
        //Βρίσκω τον/τους καλύτερο/ους
        int homeMaxGoals = homeBest.isEmpty() ? 0 : homeBest.get(0).getGoalsScored();
        int awayMaxGoals = awayBest.isEmpty() ? 0 : awayBest.get(0).getGoalsScored();
        
        //Συνθήκη για καλύτερο σκορερ γηπεδούχο
        if (homeMaxGoals > awayMaxGoals) {
            System.out.printf("   🥇 Καλύτερος/οι σκόρερ από %s:\n", homeTeam.getName());
            for (Player player : homeBest) {
                System.out.printf("     %s με %d γκολ\n", player.getName(), player.getGoalsScored());
            }
        } 
        //Συνθήκη για καλύτερο σκόρερ φιλοξενούμενο
        else if (awayMaxGoals > homeMaxGoals) {
            System.out.printf("   🥇 Καλύτερος/οι σκόρερ από %s:\n", awayTeam.getName());
            for (Player player : awayBest) {
                System.out.printf("     %s με %d γκολ\n", player.getName(), player.getGoalsScored());
            }
        } 
        //Συνθήκη για κανέναν σκόρερ, σπάνια
        else if (homeTeam.getScore() == 0 && awayTeam.getScore() == 0) {
            System.out.println("🤓ΕΠΙΘΕΤΙΚΟΙ ΜΕ ΜΥΟΠΙΑ🤓");
            System.out.println("Λευκή ισοπαλία, δεν υπάρχουν σκόρερς");
        } 
        //Συνθήκη για ίσα τέρματα
        else {
            System.out.printf("   🤝 Ισοπαλία στα γκολ με %d γκολ:\n", homeMaxGoals);
            System.out.printf("     Από %s: ", homeTeam.getName());
            for (int i = 0; i < homeBest.size(); i++) {
                System.out.printf("%s", homeBest.get(i).getName());
                if (i < homeBest.size() - 1) System.out.print(", ");
            }
            System.out.printf("\n     Από %s: ", awayTeam.getName());
            for (int i = 0; i < awayBest.size(); i++) {
                System.out.printf("%s", awayBest.get(i).getName());
                if (i < awayBest.size() - 1) System.out.print(", ");
            }
            System.out.println();
        }
    }

    //Χειρότεροι αμυντικοί απο κάθε ομάδα
    public void printWorstDefencePlayers() {
        System.out.println("\n🔻 ΧΕΙΡΟΤΕΡΟΙ ΑΜΥΝΤΙΚΟΙ:");
        
        //Ανάκτηση χειρότερων από κάθε ομάδα
        List<Player> homeWorst = homeTeam.getWorstDefenders();
        List<Player> awayWorst = awayTeam.getWorstDefenders();
        
        //Εμφάνιση χειρότερων από κάθε ομάδα
        System.out.printf("   %s:\n", homeTeam.getName());
        for (Player player : homeWorst) {
            System.out.printf("     %s - Πέρασαν %d φορές\n", player.getName(), player.getTimesPassed());
        }
        
        System.out.printf("   %s:\n", awayTeam.getName());
        for (Player player : awayWorst) {
            System.out.printf("     %s - Πέρασαν %d φορές\n", player.getName(), player.getTimesPassed());
        }
        
        //Βρίσκv τον/τους χειρότερο/ους
        int homeMaxPassed = homeWorst.isEmpty() ? 0 : homeWorst.get(0).getTimesPassed();
        int awayMaxPassed = awayWorst.isEmpty() ? 0 : awayWorst.get(0).getTimesPassed();
        
        //Συθνήκη για χειρότερο γηπεδούχο
        if (homeMaxPassed > awayMaxPassed) {
            System.out.printf("   🔻 Χειρότερος/οι αμυντικός/οί από %s:\n", homeTeam.getName());
            for (Player player : homeWorst) {
                System.out.printf("     %s\n", player.getName());
            }
        } 
        //Συνθήκη για χειρότερο φιλοξενούμενο
        else if (awayMaxPassed > homeMaxPassed) {
            System.out.printf("   🔻 Χειρότερος/οι αμυντικός/οί από %s:\n", awayTeam.getName());
            for (Player player : awayWorst) {
                System.out.printf("     %s\n", player.getName());
            }
        } 
        //Συνθήκη για 0 περάσματα, ακόμα πιο σπάνιο
        else if (homeMaxPassed == 0 && awayMaxPassed == 0) {
            System.out.println("🛡️⛓ΑΜΥΝΑ ΑΠΟ ΑΤΣΑΛΙ⛓🛡️");
            System.out.println("Κανένας επιθετικός δεν μπόρεσε να περάσει την άμυνα του αντιπάλου!");
        } 
        //Συνθήκη για ίσες φορές προσπεράσματος
        else {
            System.out.printf("   🤝 Ισοπαλία στις προσπεράσεις με %d φορές:\n", homeMaxPassed);
            System.out.printf("     Από %s: ", homeTeam.getName());
            for (int i = 0; i < homeWorst.size(); i++) {
                System.out.printf("%s", homeWorst.get(i).getName());
                if (i < homeWorst.size() - 1) System.out.print(", ");
            }
            System.out.printf("\n     Από %s: ", awayTeam.getName());
            for (int i = 0; i < awayWorst.size(); i++) {
                System.out.printf("%s", awayWorst.get(i).getName());
                if (i < awayWorst.size() - 1) System.out.print(", ");
            }
            System.out.println();
        }
    }
   
}
