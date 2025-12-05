public class Candidate {
    int votes;
    String name;
    public Candidate(String name){
        this.name = name;
        this.votes = 0;
    }
    public Candidate(String name, int votes){
        this.name = name;
        this.votes = votes;
    }
    public void addVote(){
        this.votes+=1;
    }

    public void addVotes(int votes){
        this.votes+=votes;
    }
    public int getVotes(){
        return this.votes;
    }
    public String getName(){
        return this.name;
    }

    public double percents(int totalvotes){
        if(totalvotes == 0) return 0.0;
        return (votes * 100.0) / totalvotes;
    }
    @Override
    public String toString(){
        return this.name + ": " + this.votes;
    }
}
