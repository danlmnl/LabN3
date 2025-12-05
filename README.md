## Отчет по лабораторной работе № 3

#### № группы: `ПМ-2502`

#### Выполнил: `Мануйло Данил Евгеньевич`

#### Вариант: `10`

### Cодержание:

- [Постановка задачи](#1-постановка-задачи)
- [Выбор структуры данных](#2-выбор-структуры-данных)
- [Программа](#3-программа)
- [Анализ правильности решения](#4-анализ-правильности-решения)

### 1. Постановка задачи

Необходимо реализовать процесс выборов. Каждому кандидату соответствует некоторое количество голосов. Реализовать функции добавления, удаления и анализа
данных, включая вычисление процентного распределения голосов между кандидатами.

### 2. Выбор структуры данных

Задачу можно разбить на три блока: Кандидат, Выборы и Тесты. Каждый блок реализуем через отдельный класс с собственными методами. В классе Кандидат опишем структуру кандидата и работу с его данными. В классе Выборы будут реализованы методы, которые будут использованы для управления процессом выборов. В классе Тест пропишем тестовый вариант выборов, в котором будут использованы все методы работы с выборами и кандидатами.

### 3. Программа 

> Класс Кандидат.
```java
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
```
> Класс Выборы.
```java
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
```
> Класс Тест
```java
public class Test {
    public static void main(String[] args) {
        //        ТЕСТ 1 - создание списка кандидатов и вывод списка
        Election election = new Election(5);
        election.coutCandidates();
        //        ТЕСТ 2 - добавление нового кандидата
        election.addCandidate("Петров");
        //        ТЕСТ 3 - добавление нового кандидата с заданным числом голосов
        election.addCandidate("Иванов", 20);
        election.addCandidate("Сидоров", 10);
        election.addCandidate("Сидоров", 10); // добавление одного и того же кандидата вызывает текстовое сообщение
        //        ТЕСТ 4 - поиск кандидата по имени (индекс)
        System.out.println(election.findIndex("Иванов"));
        //        ТЕСТ 5 - удаление кандидата
        election.removeCandidate("Петров");
        election.coutCandidates();
        //        ТЕСТ 6 - добавление голоса кандидату
        election.addVoteToCandidate("Сидоров");
        election.coutCandidates();
        //        ТЕСТ 7 - добавление n голосов кандидату
        election.addVotesToCandidate("Иванов", 3);
        election.coutCandidates();
        //        ТЕСТ 8 - получить количество голосов за кандидата
        System.out.println(election.getCandidatesVotes("Иванов"));
        //        ТЕСТ 9 - получить общее количество голосов
        System.out.println(election.getTotalVotes());
        //        ТЕСТ 10 - определение победителя голосования
        System.out.println(election.getWinnerOfElection());
        //        ТЕСТ 11 - получить процент проголосовавших за кандидата
        System.out.println(election.getResCandidate("Сидоров"));
        //        ТЕСТ 12 - вывод таблицы с процентами проголосовавших за кандидатов
        election.coutPercents();
        //        ТЕСТ 13 - удаление кандидатов с малым количеством голосов
        election.removeLosers(15);
        election.coutCandidates();
        //        ТЕСТ 14 - подсчет голосов из массива имён
        String[] k = new String[3];
        k[0] = new String("Ибрагимов");
        k[1] = new String("Осипов");
        k[2] = new String("Шпехт");
        election.bullitens(k);
        election.coutCandidates();


    }
}
```

### 4. Анализ правильности решения
Программа работает корректно со всеми методами, реализует все необходимые задачи, а также проверяет данные на корректность. Для проверки программы используется класс Тест. 
