import java.util.*;

public class Team {
    private String name;
    private Player goalkeeper;
    private List<Player> defenders;
    private List<Player> attackers;
    private int score;

    //Constructor με μόνο όνομα δημιουργεί κενή ομάδα-Δυνατότητα προσθήκης παικτών μετά
    public Team(String name){
        this.name = name;
        this.defenders = new ArrayList<>();
        this.attackers = new ArrayList<>();
        this.score = 0;
    }

    //Constructor που δέχεται και παίκτες κατευθείαν
    public Team(String name, Player goalkeeper, List<Player> defenders, List<Player> attackers){
        this.name = name;
        this.goalkeeper = goalkeeper;
        this.defenders = new ArrayList<>();
        this.attackers = new ArrayList<>();
        this.score = 0;
    }

    //Setter τερματοφύλακα
    public void setGoalkeeper(Player goalkeeper){
        this.goalkeeper = goalkeeper;
    }

    //Προσθήκη αμυντικού αν δεν είναι πλήρης η λίστα αμυντικών
    public void addDefender(Player defender){
        if(defenders.size() < 5){
            defenders.add(defender);
        }
    }
    //Προσθήκη επιθετικού αν δεν είναι πλήρης η λίστα επιθετικών
    public void addAttacker(Player attacker){
        if(attackers.size() < 5){
            attackers.add(attacker);
        }
    }

    //Γκολ ομάδας
    public void scoreGoal(){
        score++;
    }

    //Getters
    public String getName() {return name;}
    public Player getGoalkeeper() {return goalkeeper;}
    public List<Player> getDefenders() {return defenders;}
    public List<Player> getAttackers() {return attackers;}
    public int getScore() {return score;}

    //Τυχαία επιλογή αμυντικού
    public Player getRandomDefender() {
        Random random = new Random();
        return defenders.get(random.nextInt(defenders.size()));
    }

    //Tυχαία επιλογή επιθετικού
    public Player getRandomAttacker() {
        Random random = new Random();
        return attackers.get(random.nextInt(attackers.size()));
    }

    //Εμφάνιση ομάδας
    public void printTeam() {
        System.out.println("\n=== " + name + " ===");
        System.out.println("Τερματοφύλακας:");
        System.out.println("  " + goalkeeper);
        
        System.out.println("Αμυντικοί:");
        for (Player defender : defenders) {
            System.out.println("  " + defender);
        }
        
        System.out.println("Επιθετικοί:");
        for (Player attacker : attackers) {
            System.out.println("  " + attacker);
        }
    }

    //Καλύτερος Επιθετικός(επιστρέφει λίστα για ισοβαθμίες)
    public List<Player> getBestAttackers(){
        if (attackers.isEmpty()) {
            return new ArrayList<>();
        }
        
        //Βρίσκω το μέγιστο αριθμό γκολ
        int maxGoals = 0;
        for (Player attacker : attackers) {
            if (attacker.getGoalsScored() > maxGoals) {
                maxGoals = attacker.getGoalsScored();
            }
        }

        if (maxGoals == 0) {
        return new ArrayList<>();
        }
        
        //Επιλέγω όλους τους παίκτες με το μέγιστο αριθμό γκολ
        List<Player> bestAttackers = new ArrayList<>();
        for (Player attacker : attackers) {
            if (attacker.getGoalsScored() == maxGoals) {
                bestAttackers.add(attacker);
            }
        }
        
        return bestAttackers;
    }

    //Εναλλακτή αν δεν υπάρχει ισοβαθμία
    public Player getBestAttacker(){
        List<Player> bestAttackers = getBestAttackers();
        return bestAttackers.isEmpty() ? null : bestAttackers.get(0);
    }

    //Χειρότεροι αμυντικοί(επιστρέφει λίστα για ισοβαθμίες)
    public List<Player> getWorstDefenders() {
        if (defenders.isEmpty()) {
            return new ArrayList<>();
        }
        
        //Βρίσκω το μέγιστο αριθμό φορών που πέρασαν
        int maxTimesPassed = 0;
        for (Player defender : defenders) {
            if (defender.getTimesPassed() > maxTimesPassed) {
                maxTimesPassed = defender.getTimesPassed();
            }
        }
        
        //Επιλέγω όλους τους παίκτες με το μέγιστο αριθμό προσπεράσεων
        List<Player> worstDefenders = new ArrayList<>();
        for (Player defender : defenders) {
            if (defender.getTimesPassed() == maxTimesPassed) {
                worstDefenders.add(defender);
            }
        }
        
        return worstDefenders;
    }

    //Εναλλακτική αν δεν υπάρχει ισοβαθμία
    public Player getWorstDefender() {
        List<Player> worstDefenders = getWorstDefenders();
        return worstDefenders.isEmpty() ? null : worstDefenders.get(0);
    }
}
    
    
