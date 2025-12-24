## Отчет по лабораторной работе № 3

#### № группы: ПМ-2502  
#### Выполнил: Мануйло Данил Евгеньевич  
#### Вариант: 10  

### Содержание

- [Постановка задачи](#1-постановка-задачи)
- [Математическая модель](#2-математическая-модель)
- [Выбор структуры данных](#3-выбор-структуры-данных)
- [Алгоритм работы программы](#4-алгоритм-работы-программы)
- [Программа](#5-программа)
- [Анализ правильности решения](#6-анализ-правильности-решения)

---

### 1. Постановка задачи

Необходимо разработать программу на языке Java, моделирующую процесс выборов.  
Каждому кандидату соответствует имя и количество полученных голосов.

Программа должна обеспечивать выполнение следующих операций:
- добавление и удаление кандидатов;
- добавление одного или нескольких голосов кандидату;
- подсчёт общего количества голосов;
- вычисление процентного распределения голосов между кандидатами;
- определение победителя выборов;
- удаление кандидатов, не набравших заданного количества голосов;
- вывод текущих результатов выборов.

Для проверки корректности работы программы реализуется тестовый класс, демонстрирующий работу всех методов.

---

### 2. Математическая модель

Пусть имеется набор кандидатов
```math
C = {c_1, c_2, …, c_n}.
```
Каждый кандидат $c_i$ описывается парой значений:  
```math
c_i = (name_i, votes_i),
```
где $name_i$ — имя кандидата, $votes_i$ — количество полученных им голосов.

Общее количество голосов вычисляется как сумма голосов всех кандидатов:
$V$ = $votes_1$ + $votes_2$ + … + $votes_n$.

Процент голосов, полученных кандидатом ci, вычисляется по формуле:
$P_i$ = $\frac{votes_i}{V} \cdot  100\$ \%

Победителем выборов считается кандидат, имеющий наибольшее количество голосов.

---

### 3. Выбор структуры данных

Программа разделена на три логических блока, каждый из которых реализован отдельным классом:

- Candidate — описывает кандидата, хранит его имя и количество голосов, а также методы для работы с ними;
- Election — управляет процессом выборов, хранит список кандидатов и реализует методы анализа данных;
- Test — содержит тестовый набор команд для проверки корректности работы всех методов программы.

Для хранения кандидатов используется массив объектов класса Candidate с возможностью динамического расширения.

---

### 4. Алгоритм работы программы

1. Создаётся объект класса Election.
2. В список добавляются кандидаты (с нулевым или заданным числом голосов).
3. При необходимости кандидатам добавляются голоса.
4. Выполняется подсчёт общего количества голосов.
5. Для каждого кандидата вычисляется процент полученных голосов.
6. Определяется победитель выборов.
7. При необходимости удаляются кандидаты, не набравшие минимального количества голосов.
8. Все операции и результаты выводятся на экран.
9. Корректность работы программы проверяется с помощью тестового класса.

---

### 5. Программа

> Класс Candidate

```java
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
        this.votes = votes;
    }
    // добавление кандидату 1 голоса
    public void addVote(){
        this.votes+=1;
    }
    // добавление кандидату n голосов
    public void addVotes(int votes){
        this.votes+=votes;
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
```
> Класс Election
```java
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
    public int findIndex(String name){
        for (int i = 0; i < count; i++) {
            if(candidates[i].getName() == name){
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
        if (count == 0) return null;
        Candidate winner = candidates[0];
        for (int i = 0; i < count; i++) {
            if (candidates[i].getVotes() > winner.getVotes()) winner = candidates[i];
        }
        return winner;
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

```
> Класс Test
```java
public class Test {
    public static void main(String[] args) {

        System.out.println("ТЕСТ 1 - создание списка кандидатов и вывод списка");
        Election election = new Election(5);
        election.coutCandidates();

        System.out.println("\nТЕСТ 2 - добавление нового кандидата");
        election.addCandidate("Петров");

        System.out.println("\nТЕСТ 3 - добавление нового кандидата с заданным числом голосов");
        election.addCandidate("Иванов", 20);
        election.addCandidate("Бондаренко", 40);
        election.addCandidate("Сидоров", 10);
        election.addCandidate("Сидоров", 10); // повторный кандидат

        System.out.println("\nТЕСТ 4 - поиск кандидата по имени (индекс)");
        System.out.println(election.findIndex("Иванов"));

        System.out.println("\nТЕСТ 5 - удаление кандидата");
        election.removeCandidate("Петров");
        election.coutCandidates();

        System.out.println("\nТЕСТ 6 - добавление голоса кандидату");
        election.addVoteToCandidate("Сидоров");
        election.coutCandidates();

        System.out.println("\nТЕСТ 7 - добавление n голосов кандидату");
        election.addVotesToCandidate("Иванов", 3);
        election.coutCandidates();

        System.out.println("\nТЕСТ 8 - получение количества голосов кандидата");
        System.out.println(election.getCandidatesVotes("Иванов"));

        System.out.println("\nТЕСТ 9 - получение общего количества голосов");
        System.out.println(election.getTotalVotes());

        System.out.println("\nТЕСТ 10 - определение победителя голосования");
        System.out.println(election.getWinnerOfElection());

        System.out.println("\nТЕСТ 11 - получение процента голосов кандидата");
        System.out.println(election.getResCandidate("Сидоров"));

        System.out.println("\nТЕСТ 12 - вывод таблицы процентного распределения голосов");
        election.coutPercents();

        System.out.println("\nТЕСТ 13 - удаление кандидатов с малым количеством голосов");
        election.removeLosersVote(12);
        election.coutCandidates();

        System.out.println("\nТЕСТ 14 - удаление кандидатов с малым количеством голосов но в процентах");
        election.removeLosersPercents(50);
        election.coutCandidates();

        System.out.println("\nТЕСТ 15 - подсчёт голосов из массива имён");
        String[] k = new String[3];
        k[0] = "Фетисов";
        k[1] = "Конкина";
        k[2] = "Шпехт";
        election.bullitens(k);
        election.coutCandidates();
    }
}
```

### 6. Анализ правильности решения

Разработанная программа корректно реализует все поставленные задачи: добавление и удаление кандидатов, подсчет голосов, вычисление процентного распределения и определение победителя выборов.
Для проверки корректности работы программы используется класс Test, который демонстрирует работу всех методов и подтверждает правильность получаемых результатов.

Пример вывода программы:
```java
ТЕСТ 1 - создание списка кандидатов и вывод списка
Список кандидатов пуст

ТЕСТ 2 - добавление нового кандидата
Кандидат Петров добавлен. Проголосовавших: 0

ТЕСТ 3 - добавление нового кандидата с заданным числом голосов
Кандидат Иванов добавлен. Проголосовавших: 20
Кандидат Бондаренко добавлен. Проголосовавших: 40
Кандидат Сидоров добавлен. Проголосовавших: 10
Кандидат уже принимает участие

ТЕСТ 4 - поиск кандидата по имени (индекс)
1

ТЕСТ 5 - удаление кандидата
Иванов: 20
Бондаренко: 40
Сидоров: 10

ТЕСТ 6 - добавление голоса кандидату
Иванов: 20
Бондаренко: 40
Сидоров: 11

ТЕСТ 7 - добавление n голосов кандидату
Иванов: 23
Бондаренко: 40
Сидоров: 11

ТЕСТ 8 - получение количества голосов кандидата
23

ТЕСТ 9 - получение общего количества голосов
74

ТЕСТ 10 - определение победителя голосования
Бондаренко: 40

ТЕСТ 11 - получение процента голосов кандидата
14.864864864864865

ТЕСТ 12 - вывод таблицы процентного распределения голосов
Результаты выборов:
Иванов - 31,08% (голосов: 23)
Бондаренко - 54,05% (голосов: 40)
Сидоров - 14,86% (голосов: 11)
Всего голосов: 74

ТЕСТ 13 - удаление кандидатов с малым количеством голосов
Количество удаленных кандидатов: 1
Иванов: 23
Бондаренко: 40

ТЕСТ 14 - удаление кандидатов с малым количеством голосов но в процентах
Количество удаленных кандидатов: 1
Бондаренко: 40

ТЕСТ 15 - подсчёт голосов из массива имён
Кандидат Фетисов добавлен. Проголосовавших: 0
Кандидат Конкина добавлен. Проголосовавших: 0
Кандидат Шпехт добавлен. Проголосовавших: 0
Бондаренко: 40
Фетисов: 1
Конкина: 1
Шпехт: 1
```
