public class Player {

    private int shirtNum;
    private String name;
    private int age;
    private String position;
    private int skill;
    private int goalsScored;
    private int timesPassed;

    //Constructor
    public Player(int shirtNum, String name, int age, String position, int skill){
        this.shirtNum = shirtNum;
        this.name = name;
        this.age = age;
        this.position = position;
        this.skill = skill;
        this.goalsScored = 0;
        this.timesPassed = 0;
    }

    // Getters
    public int getShirtNum() {return shirtNum;}
    public String getName() {return name;}
    public int getAge() {return age;}
    public String getPosition() {return position;}
    public int getSkill() {return skill;}
    public int getGoalsScored() { return goalsScored;}
    public int getTimesPassed() { return timesPassed;}

    //Μεθοδοι για στατιστικά
    public void scoreGoal(){goalsScored++;}
    public void wasPassed(){timesPassed++;}

    @Override
    public String toString(){
        return String.format("Νο. %d - %s (%d ετών) - %s - Ικανότητα: %d", 
                                        shirtNum, name, age, position, skill);
    }


}