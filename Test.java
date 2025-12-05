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
        election.removeLosers(5);
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

