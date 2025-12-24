public class Test {
    public static void main(String[] args) {

        System.out.println("ТЕСТ 1 - создание списка кандидатов и вывод списка");
        Election election = new Election(5);
        election.coutCandidates();

        System.out.println("\nТЕСТ 2 - добавление нового кандидата");
        election.addCandidate("Петров");

        System.out.println("\nТЕСТ 3 - добавление нового кандидата с заданным числом голосов");
        election.addCandidate("Иванов", 20);
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
        election.removeLosers(12);
        election.coutCandidates();

        System.out.println("\nТЕСТ 14 - подсчёт голосов из массива имён");
        String[] k = new String[3];
        k[0] = "Фетисов";
        k[1] = "Конкина";
        k[2] = "Шпехт";
        election.bullitens(k);
        election.coutCandidates();
    }
}
