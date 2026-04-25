import java.util.Random;

/**
 * PROJECT: OMNI-MALL MANAGEMENT SYSTEM v1.0.4 (LEGACY)
 * STATUS: OPERATIONAL / ARCHITECTURALLY DEBT-LADEN
 * * Instructions for Students:
 * This code runs, but it is not OOP nor SOLID friendly. Your goal is to
 * refactor it so that it follows the principles seen in class.
 *
 * You'll have to add Unit tests and Javadoc. You might discover bugs..
 */
public class UniversalMallEngine {

    // --- CONFIGURATION ---
    static final int MAP_SIZE = 100;
    static final int MAX_PEOPLE = 500;
    static final int MAX_STORES = 50;

    // --- PARALLEL ARRAYS: PEOPLE ---
    static int[] p_id = new int[MAX_PEOPLE];
    static int[] p_type = new int[MAX_PEOPLE]; // 0:Shopper, 1:Janitor, 2:Security
    static int[] p_x = new int[MAX_PEOPLE];
    static int[] p_y = new int[MAX_PEOPLE];
    static double[] p_money = new double[MAX_PEOPLE];
    static int[] p_hunger = new int[MAX_PEOPLE];
    static int[] p_stress = new int[MAX_PEOPLE];
    static boolean[] p_active = new boolean[MAX_PEOPLE];
    static String[] p_name = new String[MAX_PEOPLE];

    // --- PARALLEL ARRAYS: STORES ---
    static int[] s_type = new int[MAX_STORES]; // 10:Food, 11:Cloth, 12:Elec
    static int[] s_x = new int[MAX_STORES];
    static int[] s_y = new int[MAX_STORES];
    static double[] s_vault = new double[MAX_STORES];
    static int[] s_stock = new int[MAX_STORES];
    static int[] s_dirt = new int[MAX_STORES];
    static String[] s_name = new String[MAX_STORES];
    static boolean[] s_open = new boolean[MAX_STORES];

    // --- WORLD DATA ---
    static int[][] debrisMap = new int[MAP_SIZE][MAP_SIZE];
    static Random rng = new Random(2026);
    static int tickCount = 0;

    // --- POWER SYSTEM ---
    static boolean mallPower = true;
    static int genHealth = 100;
    static int blackoutTicks = 0;

    // --- REPUTATION SYSTEM ---
    static double mallReputation = 100.0;
    static final int TYPE_VIP = 3;

    // --- AD-DRONE SYSTEM ---
    static int[] dr_x = new int[5];
    static int[] dr_y = new int[5];
    static int[] dr_target = new int[5];
    static int[] dr_active = new int[5]; // 0: Off, 1: Flying, 2: Crashed

    public static void main(String[] args) throws InterruptedException {
        setupSimulation();

        // Run for 1000 ticks
        while (tickCount < 1000) {
            updateShoppers();
            updateJanitors();
            updateSecurity();
            updateStoreSystems();
            updatePowerGrid();
            updateAdDrones();

            if (tickCount % 100 == 0) {
                printMallStatus();
            }

            tickCount++;
            Thread.sleep(10);
        }
        System.out.println("SIMULATION ENDED: Mall Closed.");
    }

    static void setupSimulation() {
        // Initialize Stores
        for (int i = 0; i < 10; i++) {
            s_name[i] = "Food_Stall_" + i;
            s_type[i] = 10;
            s_x[i] = rng.nextInt(MAP_SIZE);
            s_y[i] = rng.nextInt(MAP_SIZE);
            s_stock[i] = 100;
            s_open[i] = true;
        }
        for (int i = 10; i < 20; i++) {
            s_name[i] = "Boutique_" + i;
            s_type[i] = 11;
            s_x[i] = rng.nextInt(MAP_SIZE);
            s_y[i] = rng.nextInt(MAP_SIZE);
            s_stock[i] = 50;
            s_open[i] = true;
        }

        // Initialize People
        for (int i = 0; i < 100; i++) {
            p_id[i] = i;
            p_name[i] = "Shopper_" + i;
            p_type[i] = 0;
            p_x[i] = 50; p_y[i] = 50;
            p_money[i] = 200.0;
            p_active[i] = true;
        }
        for (int i = 100; i < 110; i++) {
            p_name[i] = "Janitor_" + (i-100);
            p_type[i] = 1;
            p_x[i] = 0; p_y[i] = 0;
            p_active[i] = true;
        }
        for (int i = 110; i < 115; i++) {
            p_name[i] = "Guard_" + (i-110);
            p_type[i] = 2;
            p_x[i] = 25; p_y[i] = 25;
            p_active[i] = true;
        }
        // Initialize VIPs
        for (int i = 115; i < 120; i++) {
            p_name[i] = "VIP_" + (i-115);
            p_type[i] = TYPE_VIP;
            p_x[i] = 50; p_y[i] = 50;
            p_money[i] = 1000.0;
            p_active[i] = true;
        }
        // Initialize Drones
        for (int i = 0; i < 5; i++) {
            dr_x[i] = i * 20;
            dr_y[i] = 0;
            dr_target[i] = rng.nextInt(20);
            dr_active[i] = 1;
        }
    }

    static void updateShoppers() {
        for (int i = 0; i < MAX_PEOPLE; i++) {
            if (!p_active[i] || (p_type[i] != 0 && p_type[i] != TYPE_VIP)) continue;

            // Behavior Logic
            if (p_hunger[i] > 50) {
                // Seek Food
                int target = -1;
                for (int s = 0; s < 10; s++) { // Hardcoded search range
                    if (mallPower && s_open[s]) { target = s; break; }
                }
                if (target != -1) {
                    moveToward(i, s_x[target], s_y[target]);
                    if (p_x[i] == s_x[target] && p_y[i] == s_y[target]) {
                        double cost = (p_type[i] == TYPE_VIP) ? 50.0 : 10.0;
                        if (p_money[i] >= cost && s_stock[target] > 0) {
                            p_money[i] -= cost;
                            s_vault[target] += cost;
                            s_stock[target]--;
                            p_hunger[i] = 0;
                            // VIPs improve reputation when they buy food
                            if (p_type[i] == TYPE_VIP) mallReputation += 0.5;
                        }
                    }
                }
            } else {
                p_x[i] += rng.nextInt(3) - 1;
                p_y[i] += rng.nextInt(3) - 1;
                p_hunger[i]++;
                // VIPs move an extra step (Double Speed)
                if (p_type[i] == TYPE_VIP) {
                    p_x[i] += rng.nextInt(3) - 1;
                    p_y[i] += rng.nextInt(3) - 1;
                }
            }

            // Blackout stress
            if (!mallPower) {
                p_stress[i] += 2;
            }

            // Dirt generation
            if (rng.nextInt(100) < 5) {
                debrisMap[clamp(p_y[i])][clamp(p_x[i])]++;
            }

            // Stress from dirt
            if (debrisMap[clamp(p_y[i])][clamp(p_x[i])] > 2) {
                p_stress[i] += 5;
                if (p_type[i] == TYPE_VIP) p_stress[i] += 5; // VIPs hate dirt more
            }
        }
    }

    static void updateJanitors() {
        for (int i = 0; i < MAX_PEOPLE; i++) {
            if (!p_active[i] || p_type[i] != 1) continue;

            // Priority: Fix generator if it's failing
            if (genHealth < 50) {
                moveToward(i, 0, 0); // Generator located at 0,0
                if (p_x[i] == 0 && p_y[i] == 0) genHealth = Math.min(100, genHealth + 2);
                continue;
            }

            boolean foundDirt = false;
            // Scans local grid
            for (int r = -5; r < 5; r++) {
                for (int c = -5; c < 5; c++) {
                    int ty = clamp(p_y[i] + r);
                    int tx = clamp(p_x[i] + c);
                    if (debrisMap[ty][tx] > 0) {
                        moveToward(i, tx, ty);
                        if (p_x[i] == tx && p_y[i] == ty) {
                            debrisMap[ty][tx] = 0;
                        }
                        foundDirt = true;
                        break;
                    }
                }
                if (foundDirt) break;
            }
        }
    }

    static void updateSecurity() {
        for (int i = 0; i < MAX_PEOPLE; i++) {
            if (!p_active[i] || p_type[i] != 2) continue;

            for (int j = 0; j < 100; j++) {
                if (p_active[j] && p_stress[j] > 100) {
                    moveToward(i, p_x[j], p_y[j]);
                    if (p_x[i] == p_x[j] && p_y[i] == p_y[j]) {
                        System.out.println("SECURITY: Removed " + p_name[j] + " for causing a scene.");
                        // Removing a VIP is a major PR disaster
                        if (p_type[j] == TYPE_VIP) mallReputation -= 20.0;
                        p_active[j] = false;
                    }
                    break;
                }
            }
        }
    }

    static void updateStoreSystems() {
        for (int s = 0; s < MAX_STORES; s++) {
            if (s_name[s] == null) continue;
            if (s_stock[s] <= 0) s_open[s] = false;
            if (mallPower && tickCount % 50 == 0) s_stock[s] += 5; // Passive restock
        }
    }

    static void updateAdDrones() {
        for (int i = 0; i < 5; i++) {
            if (dr_active[i] == 0) continue;

            // Power Failure Logic: Drones crash if power is out
            if (!mallPower && dr_active[i] == 1) {
                dr_active[i] = 2; // Set to crashed
                debrisMap[dr_y[i]][dr_x[i]] += 15; // Create heavy debris
                System.out.println("DRONE_" + i + " CRASHED at (" + dr_x[i] + "," + dr_y[i] + ")");
                continue;
            }

            // Recovery Logic: If power is back and it was crashed, janitors "reset" them
            if (mallPower && dr_active[i] == 2) {
                if (debrisMap[dr_y[i]][dr_x[i]] == 0) { // Janitor cleared the crash site
                    dr_active[i] = 1;
                    dr_x[i] = 0; dr_y[i] = 0; // Respawn at base
                }
                continue;
            }

            if (dr_active[i] == 1) {
                // Move toward target store
                int ts = dr_target[i];
                if (dr_x[i] < s_x[ts]) dr_x[i]++; else if (dr_x[i] > s_x[ts]) dr_x[i]--;
                if (dr_y[i] < s_y[ts]) dr_y[i]++; else if (dr_y[i] > s_y[ts]) dr_y[i]--;

                // If reached store, hover and influence shoppers
                if (dr_x[i] == s_x[ts] && dr_y[i] == s_y[ts]) {
                    for (int p = 0; p < MAX_PEOPLE; p++) {
                        if (p_active[p] && Math.abs(p_x[p] - dr_x[i]) < 10 && Math.abs(p_y[p] - dr_y[i]) < 10) {
                            // Drones "suggest" food, increasing hunger so they go to the target store
                            p_hunger[p] += 2;
                        }
                    }
                    // Chance to pick a new target store
                    if (rng.nextInt(100) < 5) {
                        dr_target[i] = rng.nextInt(20);
                    }
                }

                // Chance to malfunction and generate minor dirt (exhaust)
                if (rng.nextInt(100) < 2) {
                    debrisMap[dr_y[i]][dr_x[i]]++;
                }
            }
        }
    }

    static void updatePowerGrid() {
        if (blackoutTicks > 0) {
            blackoutTicks--;
            if (blackoutTicks == 0) {
                mallPower = true;
                System.out.println(">> POWER RESTORED");
            }
        } else {
            // Decay health and check for failure
            if (tickCount % 5 == 0) genHealth--;
            if (rng.nextInt(120) > genHealth) {
                mallPower = false;
                blackoutTicks = 30 + rng.nextInt(40);
                System.out.println(">> BLACKOUT! Generator Health: " + genHealth);
            }
        }
    }

    static void moveToward(int i, int tx, int ty) {
        if (p_x[i] < tx) p_x[i]++; else if (p_x[i] > tx) p_x[i]--;
        if (p_y[i] < ty) p_y[i]++; else if (p_y[i] > ty) p_y[i]--;
        p_x[i] = clamp(p_x[i]);
        p_y[i] = clamp(p_y[i]);
    }

    static int clamp(int val) {
        return Math.max(0, Math.min(MAP_SIZE - 1, val));
    }

    static void printMallStatus() {
        System.out.println("=========================================");
        System.out.println("       OMNI-MALL EXECUTIVE SUMMARY       ");
        System.out.println("=========================================");

        // 1. System Health Section
        System.out.println("[SYSTEMS]");
        System.out.print("  Power Status: " + (mallPower ? "STABLE" : "CRITICAL BLACKOUT"));
        System.out.println(" | Generator Health: " + genHealth + "%");
        System.out.println("  Mall Reputation Score: " + String.format("%.2f", mallReputation));

        // 2. Population Breakdown (Procedural headcount)
        int shoppers = 0, janitors = 0, guards = 0, vips = 0;
        for (int i = 0; i < MAX_PEOPLE; i++) {
            if (!p_active[i]) continue;
            if (p_type[i] == 0) shoppers++;
            else if (p_type[i] == 1) janitors++;
            else if (p_type[i] == 2) guards++;
            else if (p_type[i] == TYPE_VIP) vips++;
        }
        System.out.println("[POPULATION]");
        System.out.println("  Shoppers: " + shoppers + " | VIPs: " + vips);
        System.out.println("  Security: " + guards + " | Janitors: " + janitors);

        // 3. Environmental Analysis
        long totalDirt = 0;
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                totalDirt += debrisMap[y][x];
            }
        }

        // Find the "Filthiest Store" (Nested search around every store coordinate)
        int filthiestStore = -1;
        int maxLocalDirt = -1;
        for (int s = 0; s < MAX_STORES; s++) {
            if (s_name[s] == null) continue;
            int localDirt = 0;
            for (int r = -3; r <= 3; r++) {
                for (int c = -3; c <= 3; c++) {
                    localDirt += debrisMap[clamp(s_y[s] + r)][clamp(s_x[s] + c)];
                }
            }
            if (localDirt > maxLocalDirt) { maxLocalDirt = localDirt; filthiestStore = s; }
        }
        System.out.println("[ENVIRONMENT]");
        System.out.println("  Total Mall Debris: " + totalDirt + " units");
        if (filthiestStore != -1) {
            System.out.println("  Cleanliness Alert: " + s_name[filthiestStore] + " region is FILTHY (" + maxLocalDirt + " units)");
        }

        // 4. Financials Section
        double foodRev = 0, boutiqueRev = 0, totalRev = 0;
        int best = 0;
        for (int s = 0; s < MAX_STORES; s++) {
            if (s_name[s] == null) continue;
            totalRev += s_vault[s];
            if (s_type[s] == 10) foodRev += s_vault[s];
            else if (s_type[s] == 11) boutiqueRev += s_vault[s];
            if (s_vault[s] > s_vault[best]) best = s;
        }
        System.out.println("[FINANCIAL PERFORMANCE]");
        System.out.println("  Total Revenue: $" + String.format("%.2f", totalRev));
        System.out.println("  Category Breakdown -> Food: $" + foodRev + " | Boutiques: $" + boutiqueRev);
        if (s_name[best] != null) {
            System.out.println("  Top Performer: " + s_name[best] + " ($" + String.format("%.2f", s_vault[best]) + ")");
        }
        System.out.println("=========================================\n");
    }
}