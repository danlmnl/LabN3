public class Candidate {
    int votes;
    String name;
    // создание кандидата по умолчанию
    public Candidate(String name){
        this.name = name;
        this.votes = 0;
    }
    // создание кандидата с заданным количеством голосов
    public Candidate(String name, int votes){
        this.name = name;
        if(votes >= 0){
            this.votes = votes;
        } else {
            this.votes = 0;
        }
    }
    // добавление кандидату 1 голоса
    public void addVote(){
        this.votes+=1;
    }
    // добавление кандидату n голосов
    public void addVotes(int votes){
        if(votes >= 0){
            this.votes+=votes;
        }
    }
    // получение голосов кандидата
    public int getVotes(){
        return this.votes;
    }
    // получение имени кандидата
    public String getName(){
        return this.name;
    }
    // процентное отношение проголосовавших за кандидата
    public double percents(int totalvotes){
        if(totalvotes == 0) return 0.0;
        return (votes * 100.0) / totalvotes;
    }
    // перпеисанный метод для вывода
    @Override
    public String toString(){
        return this.name + ": " + this.votes;
    }
}
