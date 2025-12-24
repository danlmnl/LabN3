public class Election {
    private Candidate[] candidates;
    private int count;

    // создание процесса выборов по умолчанию
    public Election(){
        candidates = new Candidate[10];
        count = 0;
    }

    // создание процесса выборов с заданным количеством кандидатов
    public Election(int total){
        candidates = new Candidate[total];
        count = 0;
    }

    // закрытый метод, изменяющий длину массива при добавлении и удалении кандидатов
    private void setlist(){
        if (count >= candidates.length) {
            Candidate[] m = new Candidate[count*2];
            for (int i = 0; i < count; i++) {
                m[i] = candidates[i];
            }
            candidates = m;
        }
    }
    // вывод кандидатов, участвующих в выборах, с их голосами
    public void coutCandidates(){
        if (count == 0) System.out.println("Список кандидатов пуст");
        else {
            for (int i = 0; i < count; i++) {
                System.out.println(candidates[i]);
            }
        }
    }
    // добавление кандидата в выборы по умолчанию
    public void addCandidate(String name){
        if(findIndex(name)!=-1) System.out.println("Кандидат уже принимает участие");
        else {
            setlist();
            candidates[count] = new Candidate(name);
            count++;
            System.out.println("Кандидат " + name + " добавлен. Проголосовавших: " + 0);
        }
    }
    // добавление кандидата в выборы с заданным количеством голосов
    public void addCandidate(String name, int votes) {
        if (findIndex(name) != -1) System.out.println("Кандидат уже принимает участие");
        else {
            if(votes<0)votes=0;
            setlist();
            candidates[count] = new Candidate(name, votes);
            count++;
            System.out.println("Кандидат " + name + " добавлен. Проголосовавших: " + votes);
        }
    }

    // поиск индекса кандидата по имени среди участвующих в выборах
    public int findIndex(String name) {
        for (int i = 0; i < count; i++) {
            if (candidates[i].getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }
    // удаление кандидата, участвующего в выборах, из выборов
    public void removeCandidate(String name){
        int ind = findIndex(name);
        if(ind == -1) System.out.println("Кандидат с таким именем не найден");
        else {
            for (int i = ind; i < count - 1; i++) {
                candidates[i] = candidates[i+1];
            }
            candidates[count-1] = null;
            count--;
        }
    }
    // добавление голоса кандидату, участвующему в выборах
    public void addVoteToCandidate(String name){
        int ind = findIndex(name);
        if(ind == -1) System.out.println("Кандидат с таким именем не найден");
        else {
            candidates[ind].addVote();
        }
    }
    // добавление заданного количества голосов кандидату, участвующему в выборах
    public void addVotesToCandidate(String name, int votes) {
        int ind = findIndex(name);
        if (ind == -1) System.out.println("Кандидат с таким именем не найден");
        else {
            if (votes>=0){
                candidates[ind].addVotes(votes);
            } else System.out.println("Некорректное количество голосов для добавления");
        }
    }
    // получение голосов кандидата, участвующего в выборах
    public int getCandidatesVotes(String name){
        int ind = findIndex(name);
        if(ind == -1) {
            System.out.println("Кандидат с таким именем не найден");
            return -1;
        }
        return candidates[ind].getVotes();
    }
    // подсчёт и получение общего количества людей, которые голосовали.
    public int getTotalVotes(){
        int n = 0;
        for (int i = 0; i < count; i++) {
            n+=candidates[i].getVotes();
        }
        return n;
    }
    // подсчет итогов выборов среди участвующих кандидатов
    public Candidate getWinnerOfElection() {
        if (count == 0) {
            System.out.println("Нет кандидатов для подведения итогов выборов");
            return null;
        }
        Candidate winner = candidates[0];
        for (int i = 0; i < count; i++) {
            if (candidates[i].getVotes() > winner.getVotes()) winner = candidates[i];
        }
        int k = 0;
        for (int i = 0; i < count; i++) {
            if(candidates[i].getVotes() == winner.getVotes())k++;
        }
        if(k==1) return winner;
        else {
            System.out.println("Определить победителя нельзя, одинаковое количество голосов у двух или более участников");
            return null;
        }
    }
    // полдсчет и получение проценного количества людей, проголосовавших за кандидата, относительно всех голосов
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
    // вывод таблицы кандидатов с их рейтингом в этих выборах
    public void coutPercents() {
        int total = getTotalVotes();
        if (count == 0) System.out.println("Список кандидатов пуст");
        else {
            System.out.println("Результаты выборов:");
            for (int i = 0; i < count; i++) {
                double percents = candidates[i].percents(total);
                System.out.printf("%s - %.2f%% (голосов: %d)%n", candidates[i].getName(), percents, candidates[i].getVotes());
            }
            System.out.println("Всего голосов: " + total);
        }
    }
    // удаление кандидатов, участвующих в выборах и не набравших определенного количества голосов
    public void removeLosersVote(int needvotes) {
        int rcount = 0;
        for (int i = count - 1; i >= 0; i--) {
            if (candidates[i].getVotes() < needvotes) {
                removeCandidate(candidates[i].getName());
                rcount++;
            }
        }
        System.out.println("Количество удаленных кандидатов: " + rcount);
    }
    //удаление кандидатов, не достигших определенного количества процентов
    public void removeLosersPercents(double needpercents) {
        int rcount = 0;
        for (int i = count - 1; i >= 0; i--) {
            if (candidates[i].percents(getTotalVotes())*1.0 < needpercents) {
                removeCandidate(candidates[i].getName());
                rcount++;
            }
        }
        System.out.println("Количество удаленных кандидатов: " + rcount);
    }
    // "процесс подсчета бюллетеней"
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
