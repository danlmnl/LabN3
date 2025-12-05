public class Election {
    private Candidate[] candidates;
    private int count;

    public Election(){
        candidates = new Candidate[10];
        count = 0;
    }

    public Election(int total){
        candidates = new Candidate[total];
        count = 0;
    }

    private void setlist(){
        if (count >= candidates.length) {
            Candidate[] m = new Candidate[count*2];
            for (int i = 0; i < count; i++) {
                m[i] = candidates[i];
            }
            candidates = m;
        }
    }

    public void coutCandidates(){
        if (count == 0) System.out.println("Список кандидатов пуст");
        else {
            for (int i = 0; i < count; i++) {
                System.out.println(candidates[i]);
            }
        }
    }

    public void addCandidate(String name){
        if(findIndex(name)!=-1) System.out.println("Кандидат уже принимает участие");
        else {
            setlist();
            candidates[count] = new Candidate(name);
            count++;
            System.out.println("Кандидат " + name + " добавлен. Проголосовавших: " + 0);
        }
    }
    public void addCandidate(String name, int votes){
        if(findIndex(name)!=-1) System.out.println("Кандидат уже принимает участие");
        else {
            setlist();
            candidates[count] = new Candidate(name, votes);
            count++;
            System.out.println("Кандидат " + name + " добавлен. Проголосовавших: " + votes);
        }
    }


    public int findIndex(String name){
        for (int i = 0; i < count; i++) {
            if(candidates[i].getName() == name){
                return i;
            }
        }
        return -1;
    }

    public void removeCandidate(String name){
        int ind = findIndex(name);
        if(ind == -1) System.out.println("Кандидат с таким именем не найден");
        else {
            for (int i = ind; i < count - 1; i++) {
                candidates[i] = candidates[i+1];
            }
            candidates[count-1] = null;
            count--;
            setlist();
        }
    }
    public void addVoteToCandidate(String name){
        int ind = findIndex(name);
        if(ind == -1) System.out.println("Кандидат с таким именем не найден");
        else {
            candidates[ind].addVote();
        }
    }
    public void addVotesToCandidate(String name, int votes){
        int ind = findIndex(name);
        if(ind == -1) System.out.println("Кандидат с таким именем не найден");
        else {
            candidates[ind].addVotes(votes);
        }
    }

    public int getCandidatesVotes(String name){
        int ind = findIndex(name);
        if(ind == -1) {
            System.out.println("Кандидат с таким именем не найден");
            return -1;
        }
        return candidates[ind].getVotes();
    }

    public int getTotalVotes(){
        int n = 0;
        for (int i = 0; i < count; i++) {
            n+=candidates[i].getVotes();
        }
        return n;
    }

    public Candidate getWinnerOfElection(){
        if(count == 0) return null;
        Candidate winner = candidates[0];
        for (int i = 0; i < count; i++) {
            if(candidates[i].getVotes() > winner.getVotes())winner = candidates[i];
        }
        return winner;
    }

    public double getResCandidate(String name){
        int ind = findIndex(name);
        if(ind == -1) {
            System.out.println("Кандидат с таким именем не найден");
            return -1.0;
        }
        int total = getTotalVotes();
        if(total == 0) return 0.0;
        return candidates[ind].percents(total);
    }

    public void coutPercents(){
        int total = getTotalVotes();
        if(count == 0) System.out.println("Список кандидатов пуст");
        else {
            System.out.println("Результаты выборов:");
            for (int i = 0; i < count; i++) {
                double percents = candidates[i].percents(total);
                System.out.printf("%s - %.2f%% (голосов: %d)%n", candidates[i].getName(), percents, candidates[i].getVotes());
            }
            System.out.println("Всего голосов: " + total);
        }
    }

    public void removeLosers(int needvotes){
        int rcount = 0;
        for (int i = count - 1; i >= 0 ; i--) {
            if (candidates[i].getVotes() < needvotes){
                removeCandidate(candidates[i].getName());
                rcount++;
            }
        }
        System.out.println("Количество удаленных кандидатов: " + rcount);
    }

    public void bullitens(String[] names){
        for (int i = 0; i < names.length; i++) {
            int ind = findIndex(names[i]);
            if(ind==-1){
                addCandidate(names[i]);
                ind = findIndex(names[i]);
            }
            candidates[ind].addVote();
        }
    }


}
