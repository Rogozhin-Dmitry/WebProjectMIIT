package ru.rogozhinda;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.rogozhinda.entities.*;
import ru.rogozhinda.services.*;

import java.time.LocalDate;
import java.util.*;

@Component
public class BdInit implements CommandLineRunner {
    private final CarService carService;
    private final DriverService driverService;
    private final RaceService raceService;
    private final RaceTeamService raceTeamService;
    private final ResultService resultService;
    private final TeamService teamService;
    private final Random random;

    public BdInit(CarService carService, DriverService driverService, RaceService raceService, RaceTeamService raceTeamService, ResultService resultService, TeamService teamService) {
        this.carService = carService;
        this.driverService = driverService;
        this.raceService = raceService;
        this.raceTeamService = raceTeamService;
        this.resultService = resultService;
        this.teamService = teamService;
        this.random = new Random();
    }

    @Override
    public void run(String... args) {
        Car[] cars = initCars();
        Driver[] drivers = initDrivers();
        Race[] races = initRaces();
        Team[] teams = initTeams(cars, drivers);
        RaceTeam[] raceTeams = initRaceTeams(races, teams);
        initResult(raceTeams);
    }

    private void initResult(RaceTeam[] raceTeams) {
        if (raceTeams == null) {
            return;
        }
        LocalDate todayLocal = LocalDate.now();
        Date today = new Date(todayLocal.getYear() - 1900, todayLocal.getMonth().getValue(), todayLocal.getDayOfMonth());
        Result[] results = new Result[250];
        for (int i = 0; i < 250; i++) {
            results[i] = this.generateResult(raceTeams[i], today);
        }
        resultService.saveAllResults(Arrays.stream(results).toList());
        for (int i = 0; i < 250; i++) {
            raceTeams[i].setResult(results[i]);
        }
        raceTeamService.saveAllRaceTeams(Arrays.stream(raceTeams).toList());
    }

    private Result generateResult(RaceTeam raceTeam, Date today) {
        Result result = new Result();
        result.setPosition(random.nextInt(1, 10));
        result.setLaps(random.nextInt(1, 25));
        result.setTime(random.nextInt(1, 250));
        result.setLapTime(random.nextInt(1, 25));
        result.setGap(random.nextInt(1, 100));
        result.setPoints(random.nextInt(0, 250));
        if (raceTeam.getRace().getDate().compareTo(today) > 0) {
            result.setStatus("Не приступил");
        } else {
            result.setStatus("Финишировал ");
        }
        result.setRaceTeam(raceTeam);
        return result;
    }


    private RaceTeam[] initRaceTeams(Race[] races, Team[] teams) {
        if (races == null || teams == null) {
            return null;
        }
        RaceTeam[] raceTeams = new RaceTeam[250];
        for (int i = 0; i < 250; i++) {
            raceTeams[i] = this.generateRaceTeams(races[i / 10], teams[i / 25]);
        }
        raceTeamService.saveAllRaceTeams(Arrays.stream(raceTeams).toList());
        return raceTeams;
    }

    private RaceTeam generateRaceTeams(Race race, Team team) {
        RaceTeam raceTeam = new RaceTeam();
        raceTeam.setRace(race);
        raceTeam.setTeam(team);
        raceTeam.setDriver(team.getDrivers().stream().toList().get(random.nextInt(10)));
        raceTeam.setCar(team.getCars().stream().toList().get(random.nextInt(10)));
        return raceTeam;
    }


    private Race[] initRaces() {
        if (raceService.countRaces() < 25) {
            Race[] races = new Race[25];
            for (int i = 0; i < 25; i++) {
                races[i] = this.generateRace();
            }
            raceService.saveAllRaces(Arrays.stream(races).toList());
            return races;
        }
        return null;
    }

    private Race generateRace() {
        List<String> COUNTRY = List.of("Япония", "Норвегия", "Германия", "Франция", "США", "Индия");
        List<String> CIRCUITS = List.of("Нюрбургринг", "Спа-Франкоршам", "Судзука", "Ле-Ман", "Маунт Панорама", "Сильверстоун");
        List<String> BROADCASTERS = List.of("F1News", "Autosport", "Motorsport", "AutoSportMedia");
        List<String> NAMES = List.of("гранпри", "финал", "регуляр", "амазия");
        Race race = new Race();
        String circuit = CIRCUITS.get(random.nextInt(CIRCUITS.size()));
        race.setName(NAMES.get(random.nextInt(NAMES.size())) + " " + circuit + " " + random.nextInt(1, 5));
        race.setLocation(COUNTRY.get(random.nextInt(COUNTRY.size())));
        race.setDuration(random.nextInt(1, 120));
        int year = random.nextInt(124, 126);
        int month;
        if (year == 124) {
            month = random.nextInt(10, 12);
        } else {
            month = random.nextInt(1, 3);
        }
        race.setDate(new Date(year, month, random.nextInt(1, 25)));
        race.setCircuit(circuit);
        race.setBroadcasters(BROADCASTERS.get(random.nextInt(BROADCASTERS.size())));
        return race;
    }

    private Team[] initTeams(Car[] cars, Driver[] drivers) {
        List<String> NAMES = List.of("Mercedes", "Red Bull", "Ferrari", "McLaren", "SAAB", "Aston Martin", "Alpine", "Kick", "Sauber", "Haas");
        if (cars == null || drivers == null) {
            return null;
        }
        if (teamService.countTeams() < 10) {
            Team[] teams = new Team[10];
            for (int i = 0; i < 10; i++) {
                teams[i] = this.generateTeam(NAMES.get(i),
                        Arrays.copyOfRange(cars, i * 10, (i + 1) * 10),
                        Arrays.copyOfRange(drivers, i * 10, (i + 1) * 10));
            }
            teamService.saveAllTeams(Arrays.stream(teams).toList());
            driverService.saveAllDrivers(Arrays.stream(drivers).toList());
            carService.saveAllCars(Arrays.stream(cars).toList());
            return teams;
        }
        return null;
    }

    private Team generateTeam(String name, Car[] cars, Driver[] drivers) {
        List<String> COUNTRY = List.of("Япония", "Норвегия", "Германия", "Франция", "Россия", "США", "Белоруссия", "Индия");
        Team team = new Team();
        team.setName(name);
        team.setCountry(COUNTRY.get(random.nextInt(COUNTRY.size())));
        team.setTrophies(random.nextInt(0, 50));
        team.setPoints(5 * random.nextInt(300, 1500));
        team.setFounded(new Date(random.nextInt(89, 110), random.nextInt(1, 12), random.nextInt(1, 25)));
        team.setDrivers(new HashSet<>(Arrays.asList(drivers)));
        for (Driver driver : drivers) {
            driver.setTeam(team);
        }
        team.setCars(new HashSet<>(Arrays.asList(cars)));
        for (Car car : cars) {
            car.setTeam(team);
        }
        return team;
    }

    private Car[] initCars() {
        if (carService.countCars() < 100) {
            Car[] cars = new Car[100];
            for (int i = 0; i < 100; i++) {
                cars[i] = this.generateCar();
            }
            carService.saveAllCars(Arrays.stream(cars).toList());
            return cars;
        }
        return null;
    }

    private Car generateCar() {
        String ENGINES = "jzdfevse";
        List<String> MODELS = List.of("BMW m3", "BMW m5", "Honda civic", "Toyota mark 2", "Audi rs 6", "Porsche 911", "SAAB 9-5");
        Car car = new Car();
        car.setModel(MODELS.get(random.nextInt(MODELS.size())));
        car.setEngine(String.valueOf(
                ENGINES.charAt(random.nextInt(ENGINES.length()))) +
                ENGINES.charAt(random.nextInt(ENGINES.length())) +
                5 * random.nextInt(20));
        car.setYear(random.nextInt(2010, 2024));
        car.setWeight(random.nextInt(900, 1600));
        car.setHorsepower(25 * random.nextInt(14, 40));
        return car;
    }

    private Driver[] initDrivers() {
        if (driverService.countDrivers() < 100) {
            Driver[] drivers = new Driver[100];
            for (int i = 0; i < 100; i++) {
                drivers[i] = this.generateDriver();
            }
            driverService.saveAllDrivers(Arrays.stream(drivers).toList());
            return drivers;
        }
        return null;
    }

    private Driver generateDriver() {
        List<String> NAMES = List.of("Bernard", "Leroy", "Eugene", "Robert", "Terry", "Christian", "Harold", "Anthony", "Daniel", "Jesse", "Иван", "Александр");
        List<String> SURNAMES = List.of("Farmer", "Jones", "Howard", "Arnold", "Meyer", "Ray", "Jones", "Ortiz", "Owens", "Young", "Иванов");
        List<String> COUNTRY = List.of("Япония", "Норвегия", "Германия", "Франция", "Россия", "США", "Белоруссия", "Индия");
        Driver driver = new Driver();
        driver.setName(NAMES.get(random.nextInt(NAMES.size())) + " " + SURNAMES.get(random.nextInt(SURNAMES.size())));
        driver.setNationality(COUNTRY.get(random.nextInt(COUNTRY.size())));
        driver.setNumber(random.nextInt(1, 80));
        int year = random.nextInt(89, 104);
        driver.setAge(124 - year);
        driver.setWeight(random.nextInt(60, 100));
        driver.setHeight(random.nextInt(150, 180));
        driver.setPoints(5 * random.nextInt(0, 300));
        driver.setBirthday(new Date(year, random.nextInt(1, 12), random.nextInt(1, 25)));
        return driver;
    }


}
