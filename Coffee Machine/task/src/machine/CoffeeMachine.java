package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static Scanner sc = new Scanner(System.in);

    public enum TypeOfCoffee {
        ESPRESSO (250, 0, 16, 4),
        LATTE (350, 75, 20, 7),
        CAPPUCCINO (200, 100, 12, 6);
        public int water;
        public int milk;
        public int coffeeBeans;
        public int price;

        //Enum Constructor.
        TypeOfCoffee(int water, int milk, int coffeeBeans, int price) {
            //Global values for coffee.
            this.water = water;
            this.milk = milk;
            this.coffeeBeans = coffeeBeans;
            this.price = price;
        }

    }

    // Initial Amounts of Ingredients.
    public static int waterAmount = 400;
    public static int milkAmount = 540;
    public static int coffeeBeansAmount = 120;
    public static int disposableCupsAmount = 9;
    public static int money = 550;



    //My Methods - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public static void printCurrentState(int waterAmount,
                                         int milkAmount,
                                         int coffeeBeansAmount,
                                         int disposableCupsAmount,
                                         int money
    ) {
        System.out.println();
        System.out.println("The coffee machine has:\n"
                + waterAmount + " of water\n"
                + milkAmount + " of milk\n"
                + coffeeBeansAmount + " of coffee beans\n"
                + disposableCupsAmount + " of disposable cups\n"
                + money + " of money\n");
    }

    public static void take() {
        System.out.println("I gave you $" + money);
        money -= money;
    }

    public static void fill() {
        // Requests and gets input of the amounts of ingredients to add in the machine.
        System.out.println("Write how many ml of water do you want to add:");
        waterAmount += sc.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        milkAmount += sc.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        coffeeBeansAmount += sc.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        disposableCupsAmount += sc.nextInt();

    }

    public static void makeCoffee(TypeOfCoffee coffee) {
        if (waterAmount >= coffee.water
                && coffeeBeansAmount >= coffee.coffeeBeans
                && disposableCupsAmount >= 1) {
            System.out.println("I have enough resources, making you a coffee!");
            waterAmount -= coffee.water;
            milkAmount -= coffee.milk;
            coffeeBeansAmount -= coffee.coffeeBeans;
            disposableCupsAmount -= 1;
            money += coffee.price;
        }
        else if (waterAmount < coffee.water
                && milkAmount < coffee.milk
                && coffeeBeansAmount < coffee.coffeeBeans
                && disposableCupsAmount < 1) {
            System.out.println("Sorry, not enough resources!");
        }
        else if (waterAmount < coffee.water
                && milkAmount >= coffee.milk
                && coffeeBeansAmount >= coffee.coffeeBeans
                && disposableCupsAmount >= 1) {
            System.out.println("Sorry, not enough water!");
        }
        else if (waterAmount >= coffee.water
                && milkAmount >= coffee.milk
                && coffeeBeansAmount < coffee.coffeeBeans
                && disposableCupsAmount >= 1) {
            System.out.println("Sorry, not enough coffee beans!");
        }
        else if (waterAmount >= coffee.water
                && milkAmount >= coffee.milk
                && coffeeBeansAmount >= coffee.coffeeBeans
                && disposableCupsAmount < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        }
    }

    // Actions -----------------
    public void actions() {

        boolean on = true;
        while (on) {
            // Requests an action from the user.
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = sc.nextLine();
            switch (action) {
                case "fill":
                    fill();
                    break;
                case "take":
                    take();
                    break;
                case "remaining":
                    printCurrentState(waterAmount, milkAmount, coffeeBeansAmount,
                            disposableCupsAmount, money);
                    break;
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                    String typeOfCoffee = sc.next();

                    switch (typeOfCoffee) {
                        case "1":
                            makeCoffee(TypeOfCoffee.ESPRESSO);
                        case "2":
                            makeCoffee(TypeOfCoffee.LATTE);
                            break;
                        case "3":
                            makeCoffee(TypeOfCoffee.CAPPUCCINO);
                            break;
                        case "back":
                            break;
                        default:
                            break;
                    }
                    break;
                case "exit":
                    on = false;
                    break;
            }
        }

    }
    
    // Main Method - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    public static void main(String[] args) {
    CoffeeMachine cm1 = new CoffeeMachine();
    cm1.actions();

    }
}
