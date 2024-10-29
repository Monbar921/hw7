package org.example;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class HomeWork {
    private final HarryPotterCastle harryPotterCastle;

    public HomeWork() {
        this.harryPotterCastle = new HarryPotterCastle();
    }

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу
     * <a href="https://acm.timus.ru/problem.aspx?space=1&num=1439">https://acm.timus.ru/problem.aspx?space=1&num=1439</a>
     */
    public List<Integer> getOriginalDoorNumbers(int maxDoors, List<Action> actionList) {
        List<Integer> doorNumbers = new ArrayList<>();

        for (int i = 0; i < maxDoors; ++i){
            harryPotterCastle.add();
        }

        for (Action action : actionList){
            if(action.isLook){
                int doorNumber = harryPotterCastle.findRoom(action.doorNumber);
                doorNumbers.add(doorNumber);
            }else {
                harryPotterCastle.remove(action.doorNumber);
            }
        }

        return doorNumbers;
    }

    /**
     * <h1>Задание 2.</h1>
     * Решить задачу <br/>
     * <a href="https://acm.timus.ru/problem.aspx?space=1&num=1521">https://acm.timus.ru/problem.aspx?space=1&num=1521</a><br/>
     * <h2>Пошагово</h2>
     * Для 5 3 входных данных:<br/>
     * _ -> 3 позиции<br/>
     * _ 1 2 <b>3</b> 4 5 => 3 <br/>
     * <b>1</b> 2 _ 4 5 => 1 <br/>
     * _ 2 4 <b>5</b> => 5 <br/>
     * <b>2</b> 4 _ => 2 <br/>
     * _ <b>4</b> => 4
     */
    public List<Integer> getLeaveOrder(int maxUnits, int leaveInterval) {
            List<Integer> soldiersOrder = new ArrayList<>();

            List<Integer> soldiers = new LinkedList<>();
            for (int i = 1; i <= maxUnits; i++) {
                soldiers.add(i);
            }

            ListIterator<Integer> iterator = soldiers.listIterator();

            while (soldiers.size() > 0) {
                if (!iterator.hasNext()) {
                    iterator = soldiers.listIterator();
                }
                for (int i = 0; i < leaveInterval; i++) {
                    if (!iterator.hasNext()) {
                        iterator = soldiers.listIterator();
                    }
                    iterator.next();
                }

                int removedSoldier = iterator.previous();
                soldiersOrder.add(removedSoldier);
                iterator.remove();

                if (!iterator.hasNext()) {
                    iterator = soldiers.listIterator();
                }
            }

            return soldiersOrder;

    }

}
