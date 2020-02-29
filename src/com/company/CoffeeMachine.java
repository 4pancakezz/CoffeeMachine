package com.company;

import java.util.Scanner;

public class CoffeeMachine {

    private Scanner in;
    private int water, milk, beans, cups, money;

    //variables for checking if has enough resources
    private int xWater, xMilk, xBeans, xMoney;

    public CoffeeMachine(int water, int milk, int beans, int cups, int money) {
        this.in = new Scanner(System.in);
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMaker = new CoffeeMachine(400, 540, 120, 9, 550);
        boolean status;

        do {
            status = coffeeMaker.chooseAction();
        } while (status);
    }

    public boolean chooseAction() {
        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
        String action = in.next();

        switch (action) {
            case "buy":
                buyAction();
                break;

            case "fill":
                fillAction();
                break;

            case "take":
                takeAction();
                break;

            case "remaining":
                display();
                break;

            case "exit":
                return false;

            default:
                System.out.println("Please, choose the correct action.");
        }

        return true;
    }

    private void display() {
        System.out.println("\nThe coffee machine has:\n" +
                String.format("%d of water\n", this.water) +
                String.format("%d of milk\n", this.milk) +
                String.format("%d of coffee beans\n", this.beans) +
                String.format("%d of disposable cups\n", this.cups) +
                String.format("%d of money", this.money));
    }

    private void takeAction() {
        System.out.printf("I gave you $%d\n", money);
        take();
    }

    private void take() {
        this.money = 0;
    }

    private void fillAction() {
        System.out.println("\nWrite how many ml of water do you want to add:");
        int water = in.nextInt();

        System.out.println("Write how many ml of milk do you want to add:");
        int milk = in.nextInt();

        System.out.println("Write how many grams of coffee beans do you want to add:");
        int beans = in.nextInt();

        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int cups = in.nextInt();

        fill(water, milk, beans, cups);
    }

    private void fill(int water, int milk, int beans, int cups) {
        this.water += water;
        this.milk += milk;
        this.beans += beans;
        this.cups += cups;
    }

    private void buyAction() {
        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        if (in.hasNextInt()) {
            int coffeeType = in.nextInt();
            buy(coffeeType);
        } else if (in.hasNext("back")) {
            //read next word just for avoid bugs in next reading from terminal..
            String back = in.next();
            return;
        } else {
            //also reading to avoid bugs..
            String unnecessary = in.next();
            System.out.println("Try again..");
        }
    }

    private void buy(int coffeeType) {
        switch (coffeeType) {
            case 1:
                setXResources(250, 0, 16, 4);
                break;

            case 2:
                setXResources(350, 75, 20, 7);
                break;

            case 3:
                setXResources(200, 100, 12, 6);
                break;

            default:
                System.out.println("Try tot choose coffee from the list..");
                return;
        }

        if (canMakeCoffee())
            makeCoffee();
    }

    private boolean canMakeCoffee() {
        if (water < xWater) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (milk < xMilk) {
            System.out.println("Sorry, not enough milk!");
            return false;
        } else if (beans < xBeans) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        } else if (cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        } else return true;
    }

    private void makeCoffee() {
        System.out.println("I have enough resources, making you a coffee!");

        this.water -= xWater;
        this.milk -= xMilk;
        this.beans -= xBeans;
        this.money += xMoney;
        this.cups--;
    }

    private void setXResources(int xWater, int xMilk, int xBeans, int xMoney) {
        this.xWater = xWater;
        this.xMilk = xMilk;
        this.xBeans = xBeans;
        this.xMoney = xMoney;
    }
}
